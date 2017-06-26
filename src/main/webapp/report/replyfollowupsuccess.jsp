<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Welcome, Textile</title>
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/lightbox.css"/>'>
<link href="/css/lightbox.css" rel="stylesheet">
<script src='<c:url value="/js/lightbox.js"/>'></script>
<script src="/js/lightbox.js"></script>
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
					<li class="list" id="reportcreate"><a href="createreport.v">填寫回報單</a></li>
					<li class="list" id="reportcreate"><a href="reportlist.do">查詢個人回報資訊</a></li>
					<c:if test="${user.mValidManager=='Y'}">
						<li class="list" id="reportcreate"><a href="../report/situationlist.do">查詢所有未處理回報單</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<!--預留給聊天室的區塊-->
		<div id="right">預留給聊天室的區塊</div>
		<div id="body" style="background-image: url(../image/background/reportbackground.jpg); opacity: 0.8; background-color: #1C1C1C; color: #9D9D9D">
			<div>
				<br> <br> <br> <br> <br>
				<center>
					<h3>客服覆成功</h3>
					<table border="1" width="100%">
						<thead>
							<tr>
								<th>回報日期</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptUpDate}" /></th>							</tr>
						</tbody>
					</table>
					
					<!-- 	圖片	 -->
				<center>
					<div width="100%">
						<table style="width: 100%">
							<tr>
								<th style="width: 50%; text-align: center; vertical-align: middle">回報內容</th>
								<c:if test="${not empty reportImg}">
									<th style="width: 50%; text-align: center; vertical-align: middle">回報圖片</th>
								</c:if>
							</tr>
							<tr>
								<td style="width: 50%;" id="reportlistfont">${report.reptUpDetail}</td>
								<c:if test="${not empty reportImg}">
									<td>
									<c:forEach var="rImg" items="${reportImg}">
										<figure style="display: inline-block"> 
										<a href='<c:url value="..${rImg.imgUpPath}"/>' data-lightbox="main"> 
										<img src='..${rImg.imgUpPath}' width="150" height="100"></a> </figure>
									</c:forEach>
									</td>
								</c:if>
							</tr>
						</table>
					</div>
				</center>
					<br><br><br><br>
					<center>
					<div width="100%">
						<table border="1" style="width: 100%">
							<tr>
								<th style="width: 50%; text-align: center; vertical-align: middle;font-size: 25px;">回覆內容</th>
							</tr>
							<tr>
								<td style="width: 50%;text-align: justify; vertical-align: middle;font-size: 18px" id="reportlistfont">${report.replyUpDetail}</td>
							</tr>
						</table>
					</div>
				</center>

					<p id="report">
						<a href="../index.v">回首頁</a>
					</p>
					<p id="report">
						<a href="../report/situationlist.do">回未處理回報頁面</a>
					</p>
				</center>
			</div>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>