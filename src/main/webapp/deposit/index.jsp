<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>儲值首頁</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<style>
#body {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	padding-top: 3em;
}
.depositForm {
	padding: 7px;
	margin: 10px;
}
.btn-lg {
	line-height: 1em;
	font-size: 5em;
}
</style>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<form id="depositForm">
					<button type="button" class="btn btn-success btn-lg" id="deposit">我要儲值</button>
					<button type="button" class="btn btn-primary btn-lg" id="showRecord">儲值紀錄</button>
					<input type="hidden" name="memberId" value="${sessionScope.user.mId}" />
				</form>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
			</div>
		</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script>
$('button').on('click', function() {
	var action = $(this).attr('id')
	if (action == 'showRecord') {
		$('#depositForm').attr('action', 'dList.do').attr('method', 'POST').submit();
	} else {
		location.href='depositProceed.v';
	}
	
});
</script>
</html>