<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item</title>
</head>
<body>
	<c:if test="${not empty iList}">
		<table>
			<thead>
				<tr>
					<td>productId</td>
					<td>productName</td>
					<td>memberId</td>
					<td>memberName</td>
					<td>amount</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${iList}" var="iBean" varStatus="iStatus">
					<tr>
						<td>${iBean.itemPK.productId}</td>
						<td>${iBean.productBean.productName}</td>
						<td>${iBean.itemPK.memberId}</td>
						<td>${iBean.memberBean.mName}</td>
						<td>${iBean.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>