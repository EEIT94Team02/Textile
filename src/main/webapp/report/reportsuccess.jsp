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
</head>
<body>
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
					<c:if test="${user.mValidManager!='Y'}">
					<li class="list" id="reportcreate"><a href="createreport.v">填寫回報單</a></li>
					<li class="list" id="reportcreate"><a href="reportlist.do">查詢個人回報資訊</a></li>
					</c:if>
					<c:if test="${user.mValidManager=='Y'}">
					<li class="list" id="reportcreate">
					<a href="../report/situationlist.do">查詢所有未處理回報單</a>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
		<!--預留給聊天室的區塊-->
		<div id="right"><jsp:include page="/rightInclude.jsp" /></div>
		<div id="body" style="background-image: url(../image/background/reportbackground.jpg); opacity: 0.8; background-color: #1C1C1C; color: #9D9D9D">
			<div>
				<br> <br> <br> <br> <br>
				<center>
					<c:if test="${not empty success}">
						<h1 style="color:#ffcc00">回報成功</h1>
						<table style="border-bottom: 1px solid #ddd;width: 80%">
						<thead>
							<tr>
								<th style="border-bottom: 1px solid #ddd;width: 26%; text-align: center; vertical-align: middle;font-size: 20px;background-color: black;color: white">會員暱稱</th>
								<th style="border-bottom: 1px solid #ddd;width: 26%; text-align: center; vertical-align: middle;font-size: 20px;background-color: black;color: white">回報類別</th>
								<th style="border-bottom: 1px solid #ddd;width: 26%; text-align: center; vertical-align: middle;font-size: 20px;background-color: black;color: white">回報日期</th>	
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 26%; text-align: center; vertical-align: middle;font-size: 18px;background-color: black;color: #ed6600">${user.mName}</td>
								<td style="width: 26%; text-align: center; vertical-align: middle;font-size: 18px;background-color: black;color: #ed6600">${success.reptType}</td>
								<td style="width: 26%; text-align: center; vertical-align: middle;font-size: 18px;background-color: black;color: #ed6600"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${success.reptDate}" /></td>
							<tr>
						</tbody>
						</table>
					</c:if>			
						<br><br>
				<center>
					<div width="100%">
						<table style="border-color:#ff0000;width:80%;height: 250px">
							<tr>
								<c:choose>
								<c:when test="${not empty image}">
									<th style="width: 26%; text-align: center; vertical-align: middle;font-size: 18px;color:#a7f400">回報內容</th>
									<th style="width: 26%; text-align: center; vertical-align: middle;font-size: 18px;color:#FF5151"></th>
									<th style="width: 26%; text-align: center; vertical-align: middle;font-size: 18px;color:#a7f400">回報圖片</th>
								</c:when>
								<c:otherwise>
									<th style="text-align: center; vertical-align: middle;font-size: 23px;color:#94f200">回報內容</th>
								</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<c:choose>
								<c:when test="${not empty image}">
									<td style="width: 26%;color:#c8f4e3;font-size: 18px" id="reportlistfont">${success.reptDetail}</td>
									<td style="width: 26%" id="reportlistfont"></td>
									<td>
									<c:forEach var="rImg" items="${image}">
										<figure style="display: inline-block"> 
										<a href='<c:url value="../${rImg.imgPath}"/>' data-lightbox="main"> 
										<img src='../${rImg.imgPath}' width="150" height="100"></a> </figure>
									</c:forEach>
									</td>
								</c:when>
								<c:otherwise>
									<td style="color:#FF5151; text-align: center;font-size: 20px" id="reportlistfont">${success.reptDetail}</td>
								</c:otherwise>
								</c:choose>
							</tr>
						</table>
					</div>
				</center>
					<br><br><br><br>
					<p id="report">
						<a href="../index.v">回首頁</a>
					</p>
				</center>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>