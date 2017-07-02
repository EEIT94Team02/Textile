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
		<div id="body" style="font-family:Microsoft JhengHei">
			<div style="padding-top:40px;padding-bottom:570px;opacity: 0.9; color: #FFFFFF; width: 100%;background-image: url(../image/background/reportbackgroundvertical.jpg)">
				<center>
					<table style="padding-top:40px;border:1px;width:80%;padding-bottom:20px">
						<thead>
							<tr style="font-size: 22px">
								<th style="border-bottom: 1px solid #ddd;background-color: black;color: white">回報編號</th>
								<th style="border-bottom: 1px solid #ddd;background-color: black;color: white">回報日期</th>
								<th style="border-bottom: 1px solid #ddd;background-color: black;color: white">回報種類</th>
							</tr>
						</thead>
						<tbody>
							<tr style="font-size: 20px">
								<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #94f200">${report.reptNo}</th>
								<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #94f200"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptDate}" /></th>
								<c:choose>
    								<c:when test="${report.reptType=='會員'}">
    							<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #ff5b0f">${report.reptType}</th>
    								</c:when>
    						<c:when test="${report.reptType=='相簿'}">
       							<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #00bdf1">${report.reptType}</th>
    								</c:when>
    						<c:when test="${report.reptType=='活動'}">
       							<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #bf51ff">${report.reptType}</th>
    								</c:when>
    						<c:when test="${report.reptType=='商店'}">
       							<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #ff4988">${report.reptType}</th>
    								</c:when>
    						<c:otherwise>
        						<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle;background-color: black;color: #ffdb0f">${report.reptType}</th>
    						</c:otherwise>
							</c:choose>
							</tr>
						</tbody>
					</table>
					
								<!-- 	圖片	 -->
				<center>
					<div>
						<table style="width: 80%;padding-bottom:40px">
							<tr style="font-size: 22px">
								<th style="width: 33%; text-align: center; vertical-align: middle">回報內容</th>
								<th style="width: 33%; text-align: center; vertical-align: middle"></th>
								<c:if test="${not empty reportImg}">
									<th style="width: 33%; text-align: center; vertical-align: middle">回報圖片</th>
								</c:if>
							</tr>
							<tr>
								<td style="width: 33%;text-align: center; vertical-align: middle;font-size: 18px" id="reportlistfont">${report.reptDetail}</td>
								<td style="width: 33%;text-align: center; vertical-align: middle" id="reportlistfont"></td>
								<c:if test="${not empty reportImg}">
									<td><c:forEach var="rImg" items="${reportImg}">
											<figure style="display: inline-block"> 
											<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
											<img src='..${rImg.imgPath}' width="150" height="100" style="margin-right:-100px;padding-top:15px"></a> </figure>
										</c:forEach></td>
								</c:if>
							</tr>
						</table>
					</div>
				</center>
					<br>
					<br>
					<br>
					<br>
					<center>
					<h2 style="color:#ff0f33;font-size:30px">回覆成功</h2>
					<div>
						<table style="width:60%;height:200px;border-style: solid">
							<tr>
								<th style="text-align: center; vertical-align: middle;font-size: 22px;">客服回覆訊息</th>
							</tr>
							<tr>
								<td style="text-align: center; vertical-align: middle;font-size: 18px" id="reportlistfont">${report.replyDetail}</td>
							</tr>
						</table>
					</div>
				</center>
					
					<p id="report" style="padding-top:40px">
						<a href="../index.v">回首頁</a>
					</p>
					<p id="report">
						<a href="../report/situationlist.do">回未處理回報頁面</a>
					</p>
				</center>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>