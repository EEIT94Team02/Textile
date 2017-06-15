<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty particular}">
		<c:url value="/store/showImg.do" var="link" scope="page">
			<c:param name="productId" value="${particular.productId}"/>
		</c:url>
		<table>
			<thead>
				<tr>
					<th>productId</th>
					<th>productName</th>
					<th>unitPrice</th>
					<th>category</th>
					<th>intro</th>
					<th>status</th>
					<th>img</th>
					<th>rewardPoints</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${particular.productId}</td>
					<td>${particular.productName}</td>
					<td>${particular.unitPrice}</td>
					<td>${particular.category}</td>
					<td>${particular.intro}</td>
					<td>${particular.status}</td>
					<td><img src="${link}" /></td>
					<td>${particular.rewardPoints}</td>
				</tr>
			</tbody>
		</table>
	</c:if>
</body>
</html>