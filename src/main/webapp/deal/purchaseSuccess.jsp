<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Purchase Success</title>
</head>
<body>
	<c:if test="${not empty purchaseSuccess}">
		<h2>${purchaseSuccess}</h2>
		<h3><a href="${pageContext.request.contextPath}/item/iList.do">前往物品欄</a></h3>
		<h3><a href="${pageContext.request.contextPath}/">返回首頁</a></h3>
	</c:if>
</body>
</html>