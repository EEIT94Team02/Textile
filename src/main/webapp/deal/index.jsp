<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易紀錄</title>
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
input[class*="dealDetail"] {
	float: right;
}
.dealDetail {
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
					<c:when test="${not empty dealList}">
						<c:forEach items="${dealList}" var="dealBean" varStatus="dealStatus">
							<div class="container">
								<p><input type="hidden" value="${dealBean.dealId}" /></p>
								<p>交易編號：&nbsp;${dealBean.dealId}</p>
								<p>交易日期：&nbsp;${dealBean.dealDate}</p>
								<p>總花費：&nbsp;${dealBean.totalCost}&nbsp;點</p>
								<p>
									<input type="button" class="dealDetail btn btn-primary btn-sm" name="dealDetail" value="交易明細" />
								</p>
							</div>
						</c:forEach>
						<form id="dealDetailForm" action="dealDetail.do" method="POST">
							<input type="hidden" name="dealId" />
							<input type="hidden" name="memberId" value="${sessionScope.user.mId}" />
						</form>
					</c:when>
					<c:otherwise>
						<div class="noRecord">
							<h2>查無紀錄。</h2>
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
					</li>
					<li>
						<form id="dealCondition" action="dealList.do" method="POST">
							<fieldset class="dateSet">
								<legend></legend>
								<label for="afterDate">此日期之後</label>
								<input type="text" id="afterDate" name="dealDateAfter" autocomplete="off" />
								<label for="beforeDate">此日期之前</label>
								<input type="text" id="beforeDate" name="dealDateBefore" autocomplete="off" />
							</fieldset>
							<input type="submit" class="btn btn-primary btn-md" value="開始查詢" />
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
	$('#dealCondition').hide();
	
	// action
	$('#condition').on('click', function() {
		$('#dealCondition').slideToggle();
	});
	
	// datepicker related config
	$(':text[name*="After"]').datepicker({
		dateFormat:'yy-mm-dd',
		contrainInput:false,
		showAnim:'slideDown',
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$(':text[name*="Before"]').datepicker('option', 'minDate', $(this).val());
		}
	});
	$(':text[name*="Before"]').datepicker({
		dateFormat:'yy-mm-dd',
		contrainInput:false,
		showAnim:'slideDown',
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$(':text[name*="After"]').datepicker('option', 'maxDate', $(this).val());
		}
	});
	
	// reinforce reset
	$(':reset').on('click', function() {
		$(':text[name*="dealDate"]').datepicker('option', 'maxDate', null)
		.datepicker('option', 'minDate', null).val('');
	});
	
	// deal detail query
	$(':button[name="dealDetail"]').on('click', function() {
		var dealId = $(this).parents('div[class="container"]').find('p:eq(0)').find(':hidden').val();
		$(':hidden[name="dealId"]').val(dealId);
		$('#dealDetailForm').submit();
	});
	
	// no record
	$('.headTo').on('click', function() {
		var headTo = $(this).attr('name');
		if (headTo == "store") {
			location.href='${pageContext.request.contextPath}/store/pList.do';
		} else {
			location.href='${pageContext.request.contextPath}/';
		}
	});
});
</script>
</html>