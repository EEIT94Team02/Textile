<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物品欄</title>
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
	height: 40%;
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
				<c:choose>
					<c:when test="${not empty iList}">
						<c:forEach items="${iList}" var="iBean" varStatus="iStatus">
						<c:url value="/store/pShowImg.do" var="showImg" scope="page">
							<c:param name="productId" value="${iBean.productBean.productId}"/>
						</c:url>
						<div class="container">
							<img src="${showImg}" width="200" />
							<p>${iBean.productBean.productName}</p>
							<p>${iBean.productBean.rewardPoints}&nbsp;點積分</p>
							<p>擁有&nbsp;${iBean.amount}&nbsp;個</p>
						</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
 						<div class="noItem"> 
							<h2>查無資料</h2>
							<input type="button" class="headTo btn btn-info btn-lg" name="store" value="前往商店" />
							<input type="button" class="headTo btn btn-primary btn-lg" name="index" value="回首頁" />
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
				<ul>
					<li>
						<input type="button" class="btn btn-info btn-lg" name="giftSending" value="送禮" />
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
	$(':button').on('click', function() {
		var action = $(this).attr('name');
		if (action == "giftSending") {
			location.href='${pageContext.request.contextPath}/gift/giftSendingProceed.v';	
		} else if (action == "store") {
			location.href='${pageContext.request.contextPath}/store/pList.do';
		} else {
			location.href='${pageContext.request.contextPath}/';
		}
	});
});
</script>
</html>