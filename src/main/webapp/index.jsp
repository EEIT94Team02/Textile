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
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
</head>
<body>
	<div id="header">
		<div class="section">
			<c:url value="/photo/album/list.do" var="album">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<ul>
				<li><c:if test="${not empty user}">
						<c:if test='${sessionScope.user.mValidManager == "Y"}'>
							<a href="manager/">後臺</a>
						</c:if>
					</c:if></li>
				<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
				<li><a href="<c:url value ='/user/' />">會員</a></li>
				<li><a href="${album}">相簿</a></li>
				<li><a href="<c:url value ='/activity/' />">活動</a></li>
				<li><a href="<c:url value ='/store/' />">商店</a></li>
				<li><a href="<c:url value ='/report/' />">回報</a></li>
				<li><a href="<c:url value ='/announcement/' />">公告</a></li>
				<li><c:if test="${empty user}">
						<a href="check/register.v">註冊</a>
						<c:out escapeXml="false" value="<a href='check/login.r'>(登入)</a>" />
					</c:if> <c:if test="${not empty user}">
						<c:out escapeXml="false" value="<a href='check/logout.do'>${user.mName}</a>" />
					</c:if></li>
			</ul>
		</div>
	</div>
	<div id="center">
		<div id="body">
			<p>暫時存放</p>
			<p>
				<a href="theme/">主題(要登入)</a>
			</p>
			<p>
				<a href="social/">社交名單(要登入)</a>
			</p>
			<p>
				<a href="announcement/">公告維護(要登入)</a>
			</p>
			<input type="button" onclick="doMyAlbum()" value="test">
		</div>
	</div>
	<div id="footer">this is footer</div>
	
</body>
</html>