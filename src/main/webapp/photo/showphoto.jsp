<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/lightbox.css"/>'>
<script src='<c:url value="/js/lightbox.js"/>'></script>
<title>Welcome, Textile</title>
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
				<li><c:if test="${not empty user}">
						<c:out escapeXml="false" value="<a href='../check/logout.do'>${user.mName}</a>" />
					</c:if></li>
			</ul>
		</div>
	</div>
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
				<li><c:if test="${not empty user}">
						<c:out escapeXml="false" value="<a href='../check/logout.do'>${user.mName}</a>" />
					</c:if></li>
			</ul>
		</div>
	</div>
	<div id="center">
		<div id="left">
			<div class="actions">
				<ul>
					<c:url value="/photo/album/select.do" var="selectalbum">
						<c:param name="albumno" value="${user.mId}"></c:param>
					</c:url>
					<li class="list"><a href="${album}">我的相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/albuminsert.v'/>">創建相簿</a></li>
					<li class="list"><a href="${selectalbum}">瀏覽相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/upload.v'/>">上傳照片</a></li>
				</ul>
			</div>
		</div>

		<!--預留給聊天室的區塊-->
		<div id="right">
			<p>預留給聊天室的區塊</p>
			<p><a href='<c:url value="/photo/index.v"/>'>首頁</a></p>
			<p><a href='<c:url value="/photo/select.v"/>'>搜尋照片(要登入)</a></p>
			<p><a href='<c:url value="/photo/upload.v"/>'>上傳照片(要登入)</a></p>
			<p><a href='<c:url value="/photo/update.v"/>'>更新照片(要登入)</a></p>
			<p><a href='<c:url value="/photo/delete.v"/>'>刪除照片(要登入)</a></p>
		</div>


		<div id="body">
			<c:if test="${not empty PhotoList}">
				<div>
					<c:forEach var="row" items="${PhotoList}">
						<c:url value="/showphoto.v" var="photo">
							<c:param name="photono" value="${row.photono}"></c:param>
							<c:param name="respath" value="${row.respath}"></c:param>
							<c:param name="photoname" value="${row.photoname}"></c:param>
							<c:param name="interpretation" value="${row.interpretation}"></c:param>
							<c:param name="position" value="${row.position}"></c:param>
						</c:url>
						<figure style="display: inline-block">
							<a href='<c:url value="${row.respath}"/>' data-lightbox="photo" data-title="${row.photoname}"><img src='${photo}' title="${row.interpretation}" alt="${row.photoname}" width="120px"></a>
							<figcaption style="margin-left: 40px">${row.photoname}</figcaption>
						</figure>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>