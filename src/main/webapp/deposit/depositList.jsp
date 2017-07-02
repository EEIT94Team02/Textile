<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>儲值紀錄</title>
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
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<c:choose>
					<c:when test="${not empty dList}">
						<c:forEach items="${dList}" var="dBean" varStatus="dStatus">
							<div class="container">
								<p>儲值日期：&nbsp;${dBean.depositDate}</p>
								<p>儲值金額：&nbsp;${dBean.depositAmount}&nbsp;元</p>
								<p>獲得點數：&nbsp;${dBean.virtualPoints}&nbsp;點</p>
							</div>
						</c:forEach>
						</tbody>
					</c:when>
					<c:otherwise>
						<div class="noRecord">
							<h2>查無紀錄。</h2>
							<input type="button" class="headTo btn btn-info btn-lg" name="deposit" value="前往儲值" />
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
						<form id="depositCondition" action="dList.do">
							<fieldset class="dateSet">
								<legend></legend>
								<label for="afterDate">此日期之後</label>
								<input type="text" id="afterDate" name="depositDateAfter" autocomplete="off" />
								<label for="beforeDate">此日期之前</label>
								<input type="text" id="beforeDate" name="depositDateBefore" autocomplete="off" />
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
	var depositCondition = $('#depositCondition');
	depositCondition.hide();
	
	// action
	$('#condition').on('click', function() {
		depositCondition.slideToggle();
	});
	
	// date related
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
		$(':text[name*="depositDate"]').datepicker('option', 'maxDate', null)
		.datepicker('option', 'minDate', null).val('');
	});
	
	// no record
	$('.headTo').on('click', function() {
		var headTo = $(this).attr('name');
		if (headTo == 'deposit') {
			location.href='index.v';
		} else {
			location.href='${pageContext.request.contextPath}/'
		}
	});
});
</script>
</html>