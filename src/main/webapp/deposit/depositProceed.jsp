<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deposit Proceed</title>
<style>
.serialNumInput {
	length: 350px;
}
.serialErrorMessage {
	color: red;
}
</style>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<body>
	<h3>請輸入序號</h3>
	<form id="serialNumForm" action="deposit.do" method="POST">
		<input type="text" class="serialNumInput" maxlength="31" name="serialNum" value="" autocomplete="off" required="true" />
		<c:if test="${not empty depositMessages}">
			<span class="serialErrorMessage">錯誤：${depositMessages.serialNumError}</span>
		</c:if>
		<input type="submit" class="btn btn-success btn-lg" value="儲值" />
	</form>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(function() {
	$(':submit').css('display', 'block');
});
</script>
</html>