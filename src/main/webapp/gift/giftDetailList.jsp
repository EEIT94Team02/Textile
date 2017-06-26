<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gift Detail</title>
</head>
<body>
	<h3><a href="gListAll.do">返回送禮記錄</a></h3>
	<c:if test="${not empty giftDetailList}">
		<table>
			<thead>
				<tr>
					<th>giftId</th>
					<th>productName</th>
					<th>amount</th>
				</tr>
			</thead>
			<c:forEach items="${giftDetailList}" var="gdBean" varStatus="gdStatus">
				<tbody>
					<tr>
						<td>${gdBean.giftDetailPK.giftId}</td>
						<td>${gdBean.productBean.productName}</td>
						<td>${gdBean.amount}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>