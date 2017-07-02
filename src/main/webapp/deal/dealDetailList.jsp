<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易明細</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<style>
#body {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
.container {
	padding: 7px;
	margin: 5px;
	width: 30%;
	height: 30%;
	border: .5px solid #778899;
	box-shadow: 5px 5px 5px #778899;
}
#left li {
	list-style-type: none;
	line-height: 5em;
}
</style>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<c:if test="${not empty dealDetailList}" >
					<c:forEach items="${dealDetailList}" var="ddBean" varStatus="ddStatus">
						<c:url value="/store/pShowImg.do" var="showImg" scope="page" >
							<c:param name="productId" value="${ddBean.dealDetailPK.productId}" />
						</c:url>
							<div class="container">
								<img src="${showImg}" width="200" />
								<p>訂單編號：&nbsp;${ddBean.dealDetailPK.dealId}</p>
								<p>商品名稱：&nbsp;${ddBean.productBean.productName}</p>
								<p>購買數量：&nbsp;${ddBean.amount}&nbsp;個</p>
							</div>
					</c:forEach>
				</c:if>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
				<ul>
					<li>
						<input type="button" class="btn btn-info btn-lg" name="returnDeal" value="返回交易記錄" />
					</li>
				</ul>
			</div>
		</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script>
$(function() {
	$(':button[name="returnDeal"]').on('click', function() {
		location.href='dealList.do';
	});
});
</script>
</html>