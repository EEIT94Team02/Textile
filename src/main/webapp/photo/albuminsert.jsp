<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<title>Welcome, Textile</title>
<style>
</style>
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
		<div id="right">預留給聊天室的區塊</div>
		
		<div id="body">
			<c:if test="${not empty user}">
				<div>
				<p style="color:black ; font-size: 20px"><c:out value="創建一個屬於  ${user.mName} 的相簿"/></p></div>
				<form action='<c:url value="/photo/album/create.do"/>' method="post">
					<table>
						<tr style="margin: 5px ; padding: 5px">
							<td><label style="color:black ; font-size: 20px">相簿名稱：</label></td>
							<td><input type="text" name="albumname" placeholder="請輸入相簿名稱" value="${param.albumname}" /></td>
							<td>${albumCRDErrors.albumname}</td>
						</tr>
						<tr style="margin: 5px ; padding: 5px">
							<td><label style="color:black ; font-size: 20px">相簿簡介：</label></td>
							<td><input type="text" name="introduction" placeholder="請輸入相簿簡介" value="${param.introduction}" /></td>
							<td>${albumCRDErrors.introduction}</td>
						</tr>
						<tr style="margin: 5px ; padding: 5px">
							<td><label style="color:black ; font-size: 20px">隱私設定：</label></td>
							<td><select name="visibility">
									<option value="公開" selected="selected">公開</option>
									<option value="好友">好友</option>
									<option value="私人">私人</option>
							</select></td>
						</tr>
						<tr style="margin: 5px ; padding: 5px">
							<td></td>
							<td><input type="submit" value="建立"></td>
							<td>${albumCRDErrors.create}</td>
						</tr>
					</table>
				</form>
			</c:if>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>