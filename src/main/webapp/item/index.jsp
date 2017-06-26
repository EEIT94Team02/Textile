<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
	<input type="button" class="btn btn-info btn-lg" value="送禮" />
	<c:choose>
		<c:when test="${not empty iList}">
			<table>
				<thead>
					<tr>
						<th>productId</th>
						<th>productName</th>
						<th>memberId</th>
						<th>memberName</th>
						<th>remain amount</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${iList}" var="iBean" varStatus="iStatus">
						<tr>
							<td>${iBean.itemPK.productId}</td>
							<td>${iBean.productBean.productName}</td>
							<td>${iBean.itemPK.memberId}</td>
							<td>${iBean.memberBean.mName}</td>
							<td>${iBean.amount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h2>查無資料</h2>
			<h3><a href="${pageContext.request.contextPath}/store/pList.do">前往購物</a></h3>
			<h3><a href="${pageContext.request.contextPath}/">返回首頁</a></h3>
		</c:otherwise>
	</c:choose>
</body>
<script>
$(function() {
	$(':button').on('click', function() {
		location.replace('/Textile/gift/giftSendingProceed.v');
	});
});
</script>
</html>