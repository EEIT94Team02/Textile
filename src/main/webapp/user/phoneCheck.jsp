<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check phone, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<form id="phoneCheck" action="phoneCheck.do" method="post">
		<input type="hidden" name="m" value="phoneCheck" /> 系統已發送驗證碼至您的手機，請輸入驗證碼： <input id="q" name="q" type="text"
			size="13" maxlength="8" required />。 <br />
		<p></p>
		<input id="submit" name="submit" value="提交" type="submit" /> <br />
		<p></p>
	</form>
</body>
</html>