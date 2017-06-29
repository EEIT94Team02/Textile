<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>儲值</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
#body {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	padding-top: 3em;
}
.serialNumForm {
	padding: 7px;
	margin: 10px;
	width: 70%;
	justify-content: center;
	text-align: center;
}
.serialNumInput {
	length: 350px;
}
.serialErrorMessage {
	color: red;
}
input[type="submit"], .serialNumInput {
	display: block;
}
</style>
</head>

<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<form id="serialNumForm" action="deposit.do" method="POST">
					<h3>請輸入序號</h3>
					<input type="text" class="serialNumInput" maxlength="31" name="serialNum" value="" autocomplete="off" required="true" />
					<c:if test="${not empty depositMessages}">
						<span class="serialErrorMessage">錯誤：${depositMessages.serialNumError}</span>
					</c:if>
					<input type="submit" class="btn btn-success btn-lg" value="儲值" />
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
</script>
</html>