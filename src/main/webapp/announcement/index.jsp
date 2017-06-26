<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<p>
			<a href='<c:url value="insert.v"/>'>新增公告</a>
		</p>
		<p>
			<a href='<c:url value="list.v"/>'>查詢公告</a>
		</p>
		<p>
			<a href='<c:url value="update.v"/>'>修改公告</a>
		</p>
		<div>
			<label>查詢公告</label>
		</div>
		<div>
			<label>公告類別</label>
		</div>

		<div>
			<input type="radio" name="a_type" />系統 <input type="radio" name="a_type" />新聞 <input type="radio" name="a_type" />活動
			<input type="radio" name="a_type" />商城
			<c:out value=""></c:out>
		</div>
		<div>
			<label>公告時間</label> <input type="text" name="startTime" value="">
			
			<span>${errors.ooo}</span> <input type="submit" name="announcement" value="開始查詢">

		</div>
		<br>
		<div>
			<label>列出活動中的公告</label> <input type="submit" name="announcement" value="Select">

		</div>

		<br>
		<div>
			<label>列出已結束公告活動</label> <input type="submit" name="endTime" value="Select">
		</div>
		<br>
		<div>
			<label>新增修改公告</label>

		</div>
		
		</div>
	</div>
	<div id="footer">this is footer</div>
	
	

</body>
</html>



