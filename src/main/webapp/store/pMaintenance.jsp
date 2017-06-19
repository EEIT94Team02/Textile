<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
	document.getElementsByClassName("statusSelect")[pCount-1].selectedIndex = index;
}
function setCategoryIndex(pCount, index) {
	document.getElementsByClassName("categorySelect")[pCount-1].selectedIndex = index;
}
</script>
<body>
<c:if test="${not empty pMList}">
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
				<c:url value="pShowImg.do" var="showImg" scope="page">
					<c:param name="productId" value="${pBean.productId}"/>
				</c:url>
				<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/pMaintain.do'>" escapeXml="false" />
				<tr>
					<td><input type="hidden" name="productId" value="${pBean.productId}" /></td>
					<td>
						<input type="text" name="productName" maxlength="20" value="${pBean.productName}" />
						<span>${errors[pBean.productId].pNa}</span>
					</td>
					<td>
						<input type="text" name="unitPrice" value="${pBean.unitPrice}" />
						<span>${errors[pBean.productId].pUP}</span>
					</td>
					<td>
						<select class="categorySelect" name="category">
							<option value="送禮用">送禮用</option>
							<option value="自用">自用</option>
						</select>
					</td>
					<c:choose>
						<c:when test='${pBean.category == "送禮用"}'>
							<c:out value="<script>setCategoryIndex(${pStatus.count}, 0)</script>" escapeXml="false" />
						</c:when>
						<c:otherwise>
							<c:out value="<script>setCategoryIndex(${pStatus.count}, 1)</script>" escapeXml="false" />
						</c:otherwise>
					</c:choose>
					<td>
						<input type="text" name="intro" maxLength="20" value="${pBean.intro}" />
						<span>${errors[pBean.productId].pIn}</span>
					</td>
					<td>
						<select class="statusSelect" name="status">
							<option value="false">下架</option>
							<option value="true">上架</option>
						</select>
					</td>
					<c:choose>
						<c:when test="${pBean.status}">
							<c:out value="<script>setStatusIndex(${pStatus.count}, 1)</script>" escapeXml="false" />
						</c:when>
						<c:otherwise>
							<c:out value="<script>setStatusIndex(${pStatus.count}, 0)</script>" escapeXml="false" />
						</c:otherwise>
					</c:choose>
					<td><img src="${showImg}" height="200" /><input type="hidden" name="img" value="${pBean.img}" /></td>
					<td>
						<input type="text" name="rewardPoints" value="${pBean.rewardPoints}"/>
						<span>${errors[pBean.productId].pRP}</span>
					</td>
					<td>
						<input type="submit" name="maintainAction" value="Update" />
						<input type="submit" name="maintainAction" value="Delete" />
					</td>
				</tr>
				<c:out value="</form>" escapeXml="false" />
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
							<option value="1" selected="true">上架</option>
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
</c:if>
</body>
</html>