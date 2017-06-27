<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<style type="text/css">

body {
	background-image: url("<c:url value = '/image/background/4.jpg'/>");
	background-size: cover;
	background-attachment: fixed;
	background-repeat: no-repeat;
}
</style>
</head>
<body>

	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>

	<div id="center"></div>

	<div id="footer">this is footer</div>

</body>
</html>