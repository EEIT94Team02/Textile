<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Welcome, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
</head>
<body>
	<c:url value="/photo/album/list.do" var="album">
		<c:param name="mId" value="${mysecuremId}"></c:param>
	</c:url>
	<c:url value="/activity/myAct.do" var="myAct">
	</c:url>
	<c:url value="/activity/allAct.do" var="allAct">
	</c:url>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
	<div id="center">
		<div id="left">
			<div class="actions">
				<ul>
					<c:url value="/photo/album/list.do" var="album">
						<c:param name="mId" value="${mysecuremId}"></c:param>
					</c:url>
					<c:url value="/photo/album/select.do" var="selectalbum">
						<c:param name="albumno" value="${mysecuremId}"></c:param>
					</c:url>
					<li class="list"><a href="${album}">我的相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/albuminsert.v'/>">創建相簿</a></li>
					<li class="list"><a href="${selectalbum}">瀏覽相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/upload.v'/>">上傳照片</a></li>
				</ul>
			</div>
		</div>

		<!--預留給聊天室的區塊-->
		<div id="right">預留給聊天室的區塊</div>
		<div id="body">
			<c:if test="${not empty AlbumList}">
				<div>
					<c:forEach var="row" items="${AlbumList}">
						<c:url value="/photo/list.do" var="album">
							<c:param name="albumno" value="${row.introduction}"></c:param>
						</c:url>
						<c:url value="/photo/album/delete.do" var="albumdelete">
							<c:param name="albumno" value="${row.introduction}"></c:param>
						</c:url>
						<figure style="display: inline-block">
							<a href="${album}"><img src='<c:url value="/image/albumimg.jpg"/>' title="${row.introduction}" alt="${row.albumname}" width="120px"> </a>
							<div style="width: 120px; text-align: center">
								<span style="margin-left: 30px">${row.albumname}</span> <img style="margin-left: 10px" onclick="doCheck('${albumdelete}')" src='<c:url value="/image/cancel.png"/>' title="${row.introduction}" alt="${row.albumname}" width="15px">
							</div>
						</figure>
					</c:forEach>
				</div>
			</c:if>
			<%-- 			<c:if test="${not empty FriendList}"> --%>
			<%-- 				<c:forEach var="Friend" items="${FriendList}"> --%>
			<%-- 					${Friend.mbean.mName} --%>
			<%-- 				</c:forEach> --%>
			<%-- 			</c:if> --%>
		</div>
	</div>
	<div id="footer">this is footer</div>
	<script>
		function doCheck(obj) {
			answer = confirm("你確定要刪除嗎？");
			if (answer)
				location.href = obj;
		}
	</script>
</body>
</html>