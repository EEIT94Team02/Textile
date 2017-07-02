<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/jacky.css"/>'>
<title>Welcome, Textile</title>
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
			<c:if test="${not empty user}">
				<div align="center">
					<h3>
						<c:out value="創建一個屬於  ${user.mName} 的相簿" />
					</h3>
					<form action='<c:url value="/photo/album/create.do"/>' method="post">
						<table style="padding: 10px">
							<tr style="margin: 10px; padding: 5px; height: 3em;">
								<td><label style="color: black; font-size: 16px">相簿名稱：</label></td>
								<td><input type="text" name="albumname" placeholder="請輸入相簿名稱" value="${param.albumname}" /></td>
								<td>${albumCRDErrors.albumname}</td>
							</tr>
							<tr style="margin: 10px; padding: 5px; height: 3em;">
								<td><label style="color: black; font-size: 16px">相簿簡介：</label></td>
								<td><input type="text" name="introduction" placeholder="請輸入相簿簡介" value="${param.introduction}" /></td>
								<td>${albumCRDErrors.introduction}</td>
							</tr>
							<tr style="margin: 10px; padding: 5px; height: 3em;">
								<td><label style="color: black; font-size: 16px">隱私設定：</label></td>
								<td><select name="visibility">
										<option value="公開" selected="selected">公開</option>
										<option value="好友">好友</option>
										<option value="私人">私人</option>
								</select></td>
							</tr>
							<tr style="margin: 10px; padding: 5px; height: 3em;">
								<td></td>
								<td><input class="btn" type="submit" value="建立"></td>
								<td>${albumCRDErrors.create}</td>
							</tr>
						</table>
					</form>
				</div>
			</c:if>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
</html>