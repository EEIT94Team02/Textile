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
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<script src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id="center">
		<div id="left">
			<div class="actions">
				<c:url value="/photo/album/list.do" var="myalbum">
					<c:param name="mId" value="${user.mId}"></c:param>
				</c:url>
				<c:url value="/photo/album/select.do" var="selectalbum">
					<c:param name="mId" value="${user.mId}"></c:param>
				</c:url>
				<c:url value="/photo/album/friend.do" var="friendalbum">
					<c:param name="mId" value="${user.mId}"></c:param>
				</c:url>
				<c:url value="/photo/album/list.do" var="album">
					<c:param name="mId" value="${mysecuremId}"></c:param>
				</c:url>
				<c:url value="/activity/myAct.do" var="myAct">
				</c:url>
				<c:url value="/activity/allAct.do" var="allAct">
				</c:url>
				<ul style="font-weight: bold;">
					<li class="list"><a href="${myalbum}">我的相簿列表</a></li>
					<li class="list"><a href="<c:url value='/photo/albuminsert.v'/>">創建相簿</a></li>
					<li class="list"><a href="${friendalbum}">好友相簿</a></li>
					<li class="list"><a href="${selectalbum}">瀏覽相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/upload.v'/>">上傳照片</a></li>
					<li class="list"><a href="${myAct}">我的活動列表</a></li>
					<li class="list"><a href="${allAct}">活動列表</a></li>
					<li class="list"><a href="<c:url value='/activity/createAct.v'/>">開團招募</a></li>
					<li class="list"><a href="<c:url value='/activity/historyActivity.v'/>">歷史活動</a></li>
				</ul>
			</div>
		</div>

		<div id="right">
			<jsp:include page="/rightInclude.jsp" />
		</div>
		<div id="body">
			<c:if test="${not empty AlbumList}">
				<div>
					<h3>${user.mName}的相簿</h3>
					<c:forEach var="row" items="${AlbumList}">
						<c:url value="/photo/list.do" var="album">
							<c:param name="albumno" value="${row.introduction}"></c:param>
						</c:url>
						<c:url value="/photo/album/delete.do" var="albumdelete">
							<c:param name="albumno" value="${row.introduction}"></c:param>
						</c:url>
						<figure style="display: inline-block">
							<a href="${album}"> <img src='<c:url value="/image/albumimg.jpg"/>' title="${row.introduction}" alt="${row.albumname}" width="80px"></a>
							<figcaption style="text-align: center; margin-top: 5px">${row.albumname}<img onclick="doCheck('${albumdelete}')" src='<c:url value="/image/cancel.png"/>' title="${row.introduction}" alt="${row.albumname}" width="15px">
							</figcaption>
						</figure>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
	<script>
		function doCheck(obj) {
			swal({
				title : "你確定要刪除相簿嗎？",
				text : "確定要刪除相簿及所屬的所有照片嗎?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "btn-danger",
				confirmButtonText : "確認刪除",
				closeOnConfirm : false
			}, function() {
				swal("成功執行!", "你已刪除此相簿及所屬的照片", "success");
				location.href = obj;
			});
		}
	</script>
</body>
</html>