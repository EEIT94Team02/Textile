<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>送禮紀錄</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.12.1.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
fieldset[class*="Set"] {
	line-height: 1em;
	width: 300px;
	background-color: #B0C4DE;
	border: 1px solid #B0C4DE;
	box-shadow: 3px 3px 3px #B0C4DE;
}
#body {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
.container {
	padding: 7px;
	margin: 5px;
	width: 40%;
	height: 15%;
	border: .5px solid #778899;
	box-shadow: 5px 5px 5px #778899;
}
#left li {
	list-style-type: none;
	line-height: 5em;
}
.giftDetail {
	float: right;
}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<c:choose>
					<c:when test="${not empty gList}">
						<c:forEach items="${gList}" var="gBean" varStatus="gStatus">
							<div class="container">
								<p><input type="hidden" value="${gBean.giftId}" /></p>
								<p>送禮人：&nbsp;${gBean.giverBean.mName}</p>
								<p>收禮人：&nbsp;${gBean.recipientBean.mName}</p>
								<p>送禮日期：&nbsp;${gBean.giveDate}</p>
								<p>
									<input type="button" class="giftDetail btn btn-primary btn-md" name="giftDetail" value="送禮明細" />
								</p>
							</div>
						</c:forEach>
					<form id="giftDetailForm" action="giftDetail.do" method="POST">
						<input type="hidden" name="giftId" value="" />
					</form>
					</c:when>
					<c:otherwise>
						<div class="noRecord">
							<h3>查無紀錄。</h3>
							<input type="button" class="headTo btn btn-info btn-lg" name="gift" value="前往送禮" />
							<input type="button" class="headTo btn btn-info btn-lg" name="store" value="前往商店" />
							<input type="button" class="headTo btn btn-primary btn-lg" name="index" value="回首頁" />
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
				<ul>
					<li>
						<input type="button" class="btn btn-info btn-lg" id="condition" value="條件查詢" />
						<input type="button" class="btn btn-info btn-lg" id="sending" value="送禮" />
					</li>
					<li>
						<form id="giftCondition" method="POST" action="gCondition.do" >
							<fieldset class="radioSet">
								<legend></legend>
								<label for="allRecord">所有紀錄</label>
								<input type="radio" id="allRecord" rname="giverId" name="recordType" />
								<label for="giveRecord">送禮紀錄</label>
								<input type="radio" id="giveRecord" rname="giverId" name="recordType" />
								<label for="receiveRecord">收禮紀錄</label>
								<input type="radio" id="receiveRecord" rname="recipientId" name="recordType" />
								<input type="hidden" id="postId" name="giverId" value="${sessionScope.user.mId}" />
							</fieldset>
							<fieldset class="nameSet">
								<legend></legend>
								<label for="giverName">送禮人姓名</label>
								<input type="text" id="giverName" maxlength="20" name="giverName" />
								<label for="recipientName">收禮人姓名</label>
								<input type="text" id="recipientName" maxlength="20" name="recipientName" />
							</fieldset>
							<fieldset class="dateSet">
								<legend></legend>
								<label for="afterDate">此日期之後</label>
								<input type="text" id="afterDate" name="giveDateAfter" autocomplete="off" />
								<label for="beforeDate">此日期之前</label>
								<input type="text" id="beforeDate" name="giveDateBefore" autocomplete="off" />
							</fieldset>
							<input type="submit" class="btn btn-primary btn-md" value="條件查詢" />
							<input type="reset" class="btn btn-danger btn-md" value="清除條件" />
						</form>
					</li>
				</ul>
			</div>
		</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script>
$(function() {
	// default
	$('#giftCondition').hide();
	$(':submit').prop('disabled', true);
	
	// action button
	$('#condition').on('click', function() {
		$('#giftCondition').slideToggle();
	});
	$('#sending').on('click', function() {
		location.replace('headToGiftSending.do');
	});
	
	// name related
	$('.nameSet').hide();
	
	// id related
	$(':radio').checkboxradio({
		icon:false
	}).on('click', function() {
		$('#postId').attr('name' ,$(this).attr('rname'));
		if ($(':submit').prop('disabled')) {
			$(':submit').prop('disabled', false);
		}
	}).on('click.gr', function() {
		$('.nameSet').slideDown();		
	});
	$('#giveRecord').on('click.disableGN', function() {
		resetName();
		$('#giverName').prop('disabled', true);	
	});
	$('#receiveRecord').on('click.disableRN', function() {
		resetName();
		$('#recipientName').prop('disabled', true);
	});
	$('#allRecord').on('click.all', function() {
		$('.nameSet').slideUp();
		resetDate();
		$('input[id*="Name"]').prop('disabled', true);
	}).off('click.gr');
	function resetName() {
		$('input[id*="Name"]').prop('disabled', false).val('');
	};
	
	// date related
	$('input[id="afterDate"]').datepicker({
		dateFormat:"yy-mm-dd",
		contrainInput:false,
		showAnim:"slideDown",
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$('input[id="beforeDate"]').datepicker('option', 'minDate', $(this).val())
		}
	});
	$('input[id="beforeDate"]').datepicker({
		dateFormat:"yy-mm-dd",
		contrainInput:false,
		showAnim:"slideDown",
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$('input[id="afterDate"]').datepicker('option', 'maxDate', $(this).val())
		}
	});
	function resetDate() {
		$('input[id*="Date"]').datepicker('option', 'maxDate', null)
		.datepicker('option', "minDate", null)
		.val('');
	};
	
	// reset reinforce
	$('input[type="reset"]').on('click', function() {
		$('.nameSet').slideUp();
		$('#postId').attr('name', '');
		$(':submit').prop('disabled', true);
		resetDate();
		resetName();
	});
	
	// gift detail query
	$(':button[name="giftDetail"]').on('click', function() {
		var giftId = $(this).parents('div[class="container"]').find('p:eq(0)').find(':hidden').val();
		$(':hidden[name="giftId"]').val(giftId);
		$('#giftDetailForm').submit();
	});
	
	// no record
	$('.headTo').on('click', function() {
		var headTo = $(this).attr('name');
		if (headTo == "gift") {
			location.href='${pageContext.request.contextPath}/gift/giftSendingProceed.v';
		} else if (headTo == "store") {
			location.href='${pageContext.request.contextPath}/store/pList.do';
		} else {
			location.href='${pageContext.request.contextPath}/';
		}
	});
});
</script>
</html>