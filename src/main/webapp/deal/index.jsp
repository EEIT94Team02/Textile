<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deal</title>
</head>
<body>
	<c:if test="${not empty dealList}">
		<table>
			<thead>
				<tr>
					<td>dealId</td>
					<td>memberName</td>
					<td>dealDate</td>
					<td>totalCost</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${dealList}" var="dealBean" varStatus="dealStatus">
				<tr>
					<td>${dealBean.dealId}</td>
					<td>${dealBean.memberBean.mName}</td>
					<td>${dealBean.dealDate}</td>
					<td>${dealBean.totalCost}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>