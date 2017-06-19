<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Maintenance</title>
<style>
.mainList, .mainList th, td {
	border: 3px solid lightblue;
	border-collapse: collapse;
}
input[type="button"], input[type="submit"] {
	display: block;
}
</style>
</head>
<script>
function setStatusIndex(pCount, index) {
// 	console.log(pCount);
	document.getElementsByClassName("statusSelect")[pCount-1].selectedIndex = index;
}
function setCategoryIndex(pCount, index) {
	document.getElementsByClassName("categorySelect")[pCount-1].selectedIndex = index;
}
</script>
<body>
<c:if test="${not empty pList}">
	<form method="POST" action="">
		<table class="mainList">
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
					<th>maintain</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.pList}" var="pBean" varStatus="pStatus">
					<c:url value="showImg.do" var="showImg" scope="page">
						<c:param name="productId" value="${pBean.productId}"/>
					</c:url>
					<tr>
						<td>${pBean.productId}</td>
						<td><input type="text" name="productName" maxlength="20" value="${pBean.productName}"/></td>
						<td><input type="text" name="unitPrice" value="${pBean.unitPrice}"/></td>
						<td>
							<select class="categorySelect" name="category">
								<option value="送禮用">送禮用</option>
								<option value="自用">自用</option>
							</select>
						</td>
						<c:choose>
							<c:when test='${pBean.category == "送禮用"}'>
								<script>setCategorySelect(${pStatus.count}, 0)</script>
							</c:when>
							<c:otherwise>
								<script>setCategorySelect(${pStatus.count}, 1)</script>
							</c:otherwise>
						</c:choose>
						<td><input type="text" name="intro" maxLength="20" value="${pBean.intro}"/></td>
						<td>
							<select class="statusSelect" name="status">
								<option value="0">下架</option>
								<option value="1">上架</option>
							</select>
						</td>
						<c:choose>
							<c:when test="${pBean.status}">
								<script>setStatusIndex(${pStatus.count}, 1)</script>
							</c:when>
							<c:otherwise>
								<script>setStatusIndex(${pStatus.count}, 0)</script>
							</c:otherwise>
						</c:choose>
						<td><img src="${showImg}" height="200" /></td>
						<td><input type="text" name="rewardPoints" value="${pBean.rewardPoints}"/></td>
						<td>
							<input type="submit" name="maintainAction" value="Update" />
							<input type="submit" name="maintainAction" value="Delete" />
						</td>
					</tr>
				</c:forEach>
					<tr>
						<td><input type="hidden" name="productId" value="${param.productId}" /></td>
						<td><input type="text" name="productName" value="${param.productName}" placeholder="productName" /></td>
						<td><input type="text" name="unidPrice" value="${param.unitPrice}" placeholder="unidPrice" /></td>
						<td><input type="text" name="category" value="${param.category}" placeholder="category" /></td>
						<td><input type="text" name="intro" value="${param.intro}" placeholder="intro" /></td>
						<td>
							<select name="status">
								<option value="0">下架</option>
								<option value="1" selected=true>上架</option>
							</select>
						</td>
						<td><input type="text" name="img" value="" placeholder="img" /></td>
						<td><input type="text" name="rewardPoints" value="${param.rewardPoints}" placeholder="rewardPoints" /></td>
						<td>
							<input type="submit" name="maintainAction" value="Insert" />
							<input type="button" value="Clear" />
						</td>
					</tr>
			</tbody>
		</table>
	</form>
	</c:if>
</body>
</html>