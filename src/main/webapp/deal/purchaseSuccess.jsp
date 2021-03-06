<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購買成功</title>
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
	padding-top: 3em;
}
.container {
	padding: 7px;
	margin: 10px;
	text-align: center;
}
</style>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<div class="container">
					<c:if test="${not empty purchaseSuccess}">
						<h2>${purchaseSuccess}</h2>
						<input type="button" class="headTo btn btn-success btn-lg" name="item" value="前往物品欄" />
						<input type="button" class="headTo btn btn-info btn-lg" name="index" value="返回首頁" />
					</c:if>
				</div>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
			</div>
		</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script>
$(function() {
	$('.headTo').on('click', function() {
		var headTo = $(this).attr('name');
		if (headTo == "item") {
			location.href="${pageContext.request.contextPath}/item/iList.do";
		} else {
			location.href="${pageContext.request.contextPath}/";
		}
	});
});
</script>
</html>