<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
</head>
<body>
	<img src="../image/404.jpg" title="404 Not Found" alt="Sorry, the page doesn't exist." />
	<c:if test="${not empty exceptionFromServer}">
		<c:out escapeXml="false" value="<p>OK! Give you some tips: ${ exceptionFromServer }</p>" />
	</c:if>
</body>
</html>