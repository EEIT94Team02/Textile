<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Store</title>
</head>
<body>
	<c:if test="${not empty pList}">
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
				<c:forEach items="${sessionScope.pList}" var="pBean" varStatus="pStatus">
					<c:url value="/product/single.do" var="link" scope="page">
						<c:param name="productId" value="${pBean.productId}"/>
						<c:param name="productName" value="${pBean.productName}"/>
						<c:param name="unitPrice" value="${pBean.unitPrice}"/>
						<c:param name="category" value="${pBean.category}"/>
						<c:param name="intro" value="${pBean.intro}"/>
						<c:param name="status" value="${pBean.status}"/>
						<c:param name="rewardPoints" value="${pBean.rewardPoints}"/>
					</c:url>
					<c:url value="/product/showImg.do" var="showImg" scope="page">
						<c:param name="productId" value="${pBean.productId}"/>
					</c:url>
					<tr>
						<td><a href="${link}">${pBean.productId}</a></td>
						<td>${pBean.productName}</td>
						<td>${pBean.unitPrice}</td>
						<td>${pBean.category}</td>
						<td>${pBean.intro}</td>
						<td>${pBean.status}</td>
						<td><img src="${showImg}"/></td>
						<td>${pBean.rewardPoints}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>