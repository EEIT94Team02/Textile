<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty gList}">
		<table>
			<thead>
				<tr>
					<td>giftId</td>
					<td>giverName</td>
					<td>recipientName</td>
					<td>giveDate</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${gList}" var="gBean" varStatus="gStatus">
					<tr>
						<td>${gBean.giftId}</td>
						<td>${gBean.giverBean.mName}</td>
						<td>${gBean.recipientBean.mName}</td>
						<td>${gBean.giveDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>