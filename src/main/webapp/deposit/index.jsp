<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deposit</title>
</head>
<body>
	<c:if test="${not empty dList}">
		<table>
			<thead>
				<tr>
					<td>depositId</td>
					<td>memberName</td>
					<td>depositDate</td>
					<td>depositAmount</td>
					<td>virtualPoints</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${dList}" var="dBean" varStatus="dStatus">
				<tr>
					<td>${dBean.depositId}</td>
					<td>${dBean.memberBean.mName}</td>
					<td>${dBean.depositDate}</td>
					<td>${dBean.depositAmount}</td>
					<td>${dBean.virtualPoints}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>