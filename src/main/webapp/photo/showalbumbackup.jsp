<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
<title>Welcome, Textile</title>
<script>


</script>

</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
	<div id="center">
		<div id="left">
			<div class="actions">
				<ul>
					<li class="list"><a href="<c:url value='/photo/albumselect.v'/>">我的相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/albumselect.v'/>">查詢相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/albuminsert.v'/>">創建相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/albumupdate.v'/>">更新相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/albumdelete.v'/>">刪除相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/upload.v'/>">上傳照片</a></li>
				</ul>
			</div>
		</div>

		<!--預留給聊天室的區塊-->
		<div id="right">預留給聊天室的區塊</div>


		<div id="body">
			<c:if test="${not empty Albumresult}">
				${Albumresult}
			</c:if>
			<c:if test="${not empty AlbumList}">
				<div>
					<c:forEach var="row" items="${AlbumList}">
						<c:url value="/photo/list.do" var="album">
							<c:param name="albumno" value="${row.albumno}"></c:param>
							<c:param name="createtime" value="${row.createtime}"></c:param>
							<c:param name="albumname" value="${row.albumname}"></c:param>
							<c:param name="introduction" value="${row.introduction}"></c:param>
							<c:param name="visibility" value="${row.visibility}"></c:param>
							<c:param name="mId" value="${row.mId}"></c:param>
						</c:url>
						<figure style="display: inline-block">
							<a href="${album}"><img src='<c:url value="/image/albumimg.jpg"/>' title="${row.introduction}" alt="${row.albumname}" width="120px"> </a>
							<figcaption style="margin-left: 40px">${row.albumname}</figcaption>
						</figure>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${empty AlbumList}">
			</c:if>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>