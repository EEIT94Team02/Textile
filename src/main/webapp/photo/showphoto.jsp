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
	<c:url value="/photo/album/list.do" var="album">
		<c:param name="mId" value="${user.mId}"></c:param>
	</c:url>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
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
		<div id="right">
			<jsp:include page="/rightInclude.jsp" />
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
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
</html>