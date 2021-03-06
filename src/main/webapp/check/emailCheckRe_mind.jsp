<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remind checking email, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
<style type="text/css">
body {
	background-image: url("../image/background/10.jpg");
	margin: auto;
	margin-top: 15%;
	height: 250px;
	border: 2px;
	text-align: center;
	line-height: 50px;
	font-weight: bolder;
	font-size: 30px;
	font-family: Microsoft JhengHei;
}
</style>
</head>
<body>
	您尚未驗證信箱，請到您的信箱收信驗證，或
	<a href="${emailCheckRe_sendUrl}">按此</a>重新發送驗證信。
</body>
</html>