<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome, Textile.</title>
</head>
<body>
	<h1>Textile</h1>
	<c:if test="${empty user}">
		<c:out escapeXml="false" value="&nbsp;&nbsp;&nbsp;&nbsp;" />
		<c:out escapeXml="false" value="<a href='check/login.jsp'>(登入)</a>" />
	</c:if>
	<c:if test="${not empty user}">
		<c:out value="${user.mName}" />
		<c:out escapeXml="false" value="&nbsp;&nbsp;&nbsp;&nbsp;" />
		<c:out escapeXml="false" value="<a href='check/logout.do'>(登出)</a>" />
	</c:if>
</body>
</html>