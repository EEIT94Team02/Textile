<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deposit Success</title>
</head>
<body>
	<c:if test="${not empty depositMessages.depositSuccess}">
		<h2>${depositMessages.depositSuccess}</h2>
		<c:if test="${not empty sessionScope.cartList}">
			<h3><a href="${pageContext.request.contextPath}/store/cart.v">返回購物車</a></h3>
		</c:if>
		<h3><a href="${pageContext.request.contextPath}/store/pList.do">前往購物</a></h3>
		<h3><a href="${pageContext.request.contextPath}/">返回首頁</a></h3>
	</c:if>
</body>
</html>