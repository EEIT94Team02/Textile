<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deal Detail</title>
</head>
<body>
	<h3><a href="dealList.do">返回交易記錄</a></h3>
	<c:if test="${not empty dealDetailList}" >
		<table>
			<thead>
				<tr>
					<td>dealId</td>
					<td>prouctName</td>
					<td>amount</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dealDetailList}" var="ddBean" varStatus="ddStatus">
					<tr>
						<td>${ddBean.dealDetailPK.dealId}</td>
						<td>${ddBean.productBean.productName}</td>
						<td>${ddBean.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>