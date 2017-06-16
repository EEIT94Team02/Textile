<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Store</title>
</head>
<body>
	<c:if test="${not empty pList}">
	<form method="POST" action="">
		<table>
			<thead>
				<tr>
					<th>productId</th>
					<th>productName</th>
					<th>unitPrice</th>
					<th>category</th>
					<th>intro</th>
					<th>img</th>
					<th>rewardPoints</th>
					<th>amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.pList}" var="pBean" varStatus="pStatus">
					<c:url value="single.do" var="link" scope="page">
						<c:param name="productId" value="${pBean.productId}"/>
						<c:param name="productName" value="${pBean.productName}"/>
						<c:param name="unitPrice" value="${pBean.unitPrice}"/>
						<c:param name="category" value="${pBean.category}"/>
						<c:param name="intro" value="${pBean.intro}"/>
						<c:param name="status" value="${pBean.status}"/>
						<c:param name="rewardPoints" value="${pBean.rewardPoints}"/>
					</c:url>
					<c:url value="showImg.do" var="showImg" scope="page">
						<c:param name="productId" value="${pBean.productId}"/>
					</c:url>
					<c:if test="${pBean.status}">
						<tr>
							<td><a href="${link}">${pBean.productId}</a></td>
							<td>${pBean.productName}</td>
							<td>${pBean.unitPrice}</td>
							<td>${pBean.category}</td>
							<td>${pBean.intro}</td>
							<td><img src="${showImg}" height="200"/></td>
							<td>${pBean.rewardPoints}</td>
							<td>
								<select>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
								<input type="button" value="kart"/>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</form>
	</c:if>
</body>
</html>