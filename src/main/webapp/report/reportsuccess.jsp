<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<c:if test="${not empty success}" scope="page">
<h2>回報成功</h2>
<table border="1">
	<tr><td>會員暱稱</td><td>${user.mName}</td></tr>
	<tr><td>回報類別</td><td>${success.reptType}</td></tr>
	<tr><td>回報內容</td><td>${success.reptDetail}</td></tr>	
</table>
</c:if>
<c:forEach var="img" items="${image}">	
	<img src="../${img.imgPath}">
</c:forEach>
</center>
</body>
</html>