<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<style type="text/css">
body {
	background-image: url("<c:url value = '/image/background/4.jpg'/>");
	background-size: cover;
	background-attachment: fixed;
	background-repeat: no-repeat;
	position: relative;
}

.hero-text {
	text-align: center;
	position: absolute;
	top: 10%;
	left: 50%;
	transform: translate(-45%, 150%);
	color: #FFFF00;
	text-shadow: 0.3em 0.2em 0.15em #333;
}

.hero-text button {
	-moz-box-shadow: inset -1px 6px 13px 2px #29bbff;
	-webkit-box-shadow: inset -1px 6px 13px 2px #29bbff;
	box-shadow: inset -1px 6px 13px 2px #29bbff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #2dabf9
		), color-stop(1, #0875fa));
	background: -moz-linear-gradient(top, #2dabf9 5%, #0875fa 100%);
	background: -webkit-linear-gradient(top, #2dabf9 5%, #0875fa 100%);
	background: -o-linear-gradient(top, #2dabf9 5%, #0875fa 100%);
	background: -ms-linear-gradient(top, #2dabf9 5%, #0875fa 100%);
	background: linear-gradient(to bottom, #2dabf9 5%, #0875fa 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#2dabf9',
		endColorstr='#0875fa', GradientType=0);
	background-color: #2dabf9;
	-moz-border-radius: 17px;
	-webkit-border-radius: 17px;
	border-radius: 17px;
	border: 0px solid #0b0e07;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	padding: 8px 30px;
	text-decoration: none;
	text-shadow: -1px 2px 0px #263666;
}

.hero-text button:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #0875fa
		), color-stop(1, #2dabf9));
	background: -moz-linear-gradient(top, #0875fa 5%, #2dabf9 100%);
	background: -webkit-linear-gradient(top, #0875fa 5%, #2dabf9 100%);
	background: -o-linear-gradient(top, #0875fa 5%, #2dabf9 100%);
	background: -ms-linear-gradient(top, #0875fa 5%, #2dabf9 100%);
	background: linear-gradient(to bottom, #0875fa 5%, #2dabf9 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0875fa',
		endColorstr='#2dabf9', GradientType=0);
	background-color: #0875fa;
}

.hero-text button:active {
	position: relative;
	top: 1px;
}

p {
	color: #C63300;
	text-shadow: 0.05em 0.07em 0.01em #333;
	font-size: 30px;
	font-weight: bold;
	font-family: Microsoft JhengHei;
}

.cover {
	background: #FFD1A4;
	padding: 4px 50px;
	opacity: 0.88;
}

.img {
	border-radius: 20px;
	height: 65px;
}
</style>
</head>
<body>

	<div id="header">
		<div class="section">
			<c:url value="/photo/album/list.do" var="album">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<c:url value="/activity/myAct.do" var="myAct">
			</c:url>
			<a href="<c:url value ='/index.jsp' />"><img src="<c:url value ='/image/background/logo2.jpg' />" class="img" /></a>
			<ul>
				<li><c:if test="${not empty user}">
						<c:if test='${sessionScope.user.mValidManager == "Y"}'>
							<a href="<c:url value='/manager/'/>">後臺</a>
						</c:if>
					</c:if></li>
				<li><a href="<c:url value ='/user/' />">會員</a></li>
				<li><a href="${album}">相簿</a></li>
				<li><a href="${myAct}">活動</a></li>
				<li><a href="<c:url value ='/store/pList.do' />">商店</a></li>
				<li><a href="<c:url value ='/item/iList.do' />">物品欄</a></li>
				<li><a href="<c:url value ='/gift/gListAll.do' />">禮物</a></li>
				<li><a href="<c:url value ='/deposit/dList.do' />">儲值</a></li>
				<li><a href="<c:url value ='/deal/dealList.do' />">交易紀錄</a></li>
				<li><a href="<c:url value ='/report/' />">回報</a></li>
				<li><a href="<c:url value ='/announcement/' />">公告</a></li>
				<li><c:if test="${not empty user}">
						<c:url var="x" value="/check/logout.do" />
						<c:out escapeXml="false" value="<a href='${x}'>${user.mName}</a>" />
					</c:if></li>

			</ul>
		</div>
	</div>

	<div>
		<div class="hero-text">
			<div class="cover">
				<h1 style="font-size: 50px">Wellcome to Textile!!</h1>
				<p>想要認識誰嗎?</p>
				<c:if test="${empty user}">
					<button onclick="location.href='check/register.v'">按我註冊!!</button>
					<c:set var="x" value="&nbsp;&nbsp;&nbsp;&nbsp;" />
					<c:out escapeXml="false" value="${x}" />
					<button onclick="location.href='check/login.r'">趕快登入!!</button>
				</c:if>

			</div>
		</div>
	</div>

	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>

</body>
</html>