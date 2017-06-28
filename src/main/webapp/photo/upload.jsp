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
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
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
				<ul>
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
			<form action='<c:url value="upload.do"/>' enctype="multipart/form-data" method="post">
				<table>
					<tr>
						<td><label style="color: black; font-size: 20px">選擇相簿 :</label></td>
						<td><select name="albumno">
								<c:forEach var="row" items="${AlbumList}">
									<option value="${row.albumno}">${row.albumname}</option>
								</c:forEach>
						</select></td>
						<td>${photoCRDErrors.albumno}</td>
					</tr>
					<tr>
						<td><label style="color: black; font-size: 20px">照片名稱 :</label></td>
						<td><input type="text" name="photoname" maxlength="10" placeholder="10個字內的照片名稱" value="${param.photoname}"></td>
						<td>${photoCRDErrors.photoname}</td>
					</tr>
					<tr>
						<td><label style="color: black; font-size: 20px">照片類別 :</label></td>
						<td><input type="radio" checked="checked" name="position" value="一般">一般 <input type="radio" name="position" value="大頭貼">設為大頭貼</td>
						<td></td>
					</tr>
					<tr>
						<td><label style="color: black; font-size: 20px">上傳檔案 :</label></td>
						<td><input type="file" name="file" multiple accept="image/*"></td>
						<td>${photoCRDErrors.file}</td>
					</tr>
					<tr>
						<td><label style="color: black; font-size: 20px">照片敘述 :</label></td>
						<td><textarea name="interpretation" placeholder="照片說明"></textarea></td>
					</tr>
					<tr>
						<td><input type="submit" value="upload"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
</html>