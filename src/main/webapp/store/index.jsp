<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Store</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
#body {
	padding: 15px;
}
.productList {
	justify-content: space-around;
/* 	align-content: center; */
	display: flex;
}
.container {
	border: 1px solid pink;
	position: relative;
	width: 80%;
}
.image {
	opacity: 1;
	transition: 0.5s ease;
	display: block;
	width: 100%;
	height: 100%;
}
.middle {
	opacity: 0;
	transition: 0.5s ease;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	width: 100%;
	height: 100%;
}
.container:hover .image {
	opacity: 0.3;
}
.container:hover .middle {
	opacity: 1;
}
.info {
	background-color: #FFB6C1;
	color: #778899;
	font-size: 16px;
	padding: 16px 16px;
}
</style>
</head>
<body>
	<div id="header">
			<div class="section">
				<c:url value="/photo/album/list.do" var="album">
					<c:param name="mId" value="${user.mId}"></c:param>
				</c:url>
				<ul>
					<li>
						<c:if test="${not empty user}">
							<c:if test='${sessionScope.user.mValidManager == "Y"}'>
								<a href="manager/">後臺</a>
							</c:if>
						</c:if>
					</li>
					<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
					<li><a href="<c:url value ='/user/' />">會員</a></li>
					<li><a href="${album}">相簿</a></li>
					<li><a href="<c:url value ='/activity/' />">活動</a></li>
					<li><a href="<c:url value ='/store/pList.do' />">商店</a></li>
					<li><a href="<c:url value ='/deposit/' />">儲值</a></li>
					<li><a href="<c:url value ='/report/' />">回報</a></li>
					<li><a href="<c:url value ='/announcement/' />">公告</a></li>
					<li>
						<c:if test="${empty user}">
							<a href="check/register.v">註冊</a>
							<c:out escapeXml="false" value="<a href='check/login.r'>(登入)</a>" />
						</c:if>
						<c:if test="${not empty user}">
							<c:out escapeXml="false" value="<a href='check/logout.do'>${user.mName}</a>" />
						</c:if>
					</li>
				</ul>
			</div>
		</div>
		<div id="center">
			<div id="body">
				<c:if test='${sessionScope.user.mValidManager == "Y"}'>
					<h3><a href=" <c:url value='/manager/pShowMaintain.do'/> ">Maintain</a></h3>
				</c:if>
				<h3><a href=" <c:url value='/store/cart.v' /> ">Cart</a></h3>
				<c:if test="${not empty pList}">
					<div class="productList">
						<c:forEach items="${pList}" var="pBean" varStatus="pStatus">
							<c:url value="pShowImg.do" var="showImg" scope="page">
								<c:param name="productId" value="${pBean.productId}"/>
							</c:url>
							<c:if test="${pBean.status}">
								<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/cart.do'>" escapeXml="false" />
									<div class="container">
										<img class="image" src="${showImg}" />
										<div class="middle">
											<div class="info">
												<p>${pBean.productName}</p>
												<p>每個&nbsp;${pBean.unitPrice}&nbsp;點數&nbsp;&nbsp;&nbsp;每個可獲得&nbsp;${pBean.rewardPoints}&nbsp;積分</p>
												<p>${pBean.category}</p>
												<p>${pBean.intro}</p>
												<p>
													<select name="amount" style="display: block">
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
													<input type="hidden" name="productId" value="${pBean.productId}" />
													<input type="submit" name="cartAction" class="btn btn-success btn-sm" value="加入購物車" />
												</p>
											</div>
										</div>
									</div>
								<c:out value="</form>" escapeXml="false" />
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
	<div id="footer">this is footer</div>
</body>
</html>