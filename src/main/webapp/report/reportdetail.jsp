<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<li class="list" id="reportcreate"><a href="createreport.v">填寫回報單</a></li>
					<li class="list" id="reportcreate"><a href="reportlist.do">查詢個人回報資訊</a></li>
					<c:if test="${user.mValidManager=='Y'}">
						<li class="list" id="reportcreate"><a href="../report/situationlist.do">查詢所有未處理回報單</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<!--預留給聊天室的區塊-->
		<div id="right"><jsp:include page="/rightInclude.jsp" /></div>
		<div id="body" style="font-size: 18px;">
			<div style="padding-top:40px;padding-bottom:40px;opacity: 0.9; color: #FFFFFF; width: 100%;background-image: url(../image/background/reportbackground.jpg)">
				<div>
					<table width="100%">
						<tbody>
						<thead>
							<tr>
								<th style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: white">會員暱稱</th>
								<th style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: white">回報日期</th>
								<th style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: white">回報類別</th>
								<th style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: white">回覆狀況</th>
								<!-- 								<th>管理員回覆</th> -->
							</tr>
						</thead>
						<tr>
							<td style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: #94f200" id="reportlistfont">${user.mName}</td>
							<td style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: #94f200" id="reportlistfont"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptDate}" /></td>
							<td style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: #94f200" id="reportlistfont">${report.reptType}</td>
<%-- 							<td style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: #94f200" id="reportlistfont">${report.situation?'已回覆':'未回覆'}</td> --%>
							<c:choose>
            					<c:when test="${report.situation}">
                					<td style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: #94f200" id="reportlistfont">已回覆</td>
            					</c:when>
           				 		<c:otherwise>
                 					<td style="border-bottom: 1px solid #ddd; width: 25%; text-align: center; vertical-align: middle;background-color: black;color: #ff0000" id="reportlistfont">未回覆</td>
           					 </c:otherwise>
        					</c:choose>
							
							
							<%-- 							<td>${report.replyDetail}</td> --%>
						</tr>
						</tbody>
					</table>
				</div>
				<center>
					<div>
						<table style="width:100%;padding-top:15px;">
							<tr style="font-size:22px">
								<th style="width: 50%; text-align: center; vertical-align: middle">回報內容</th>
								<c:if test="${not empty reportImg}">
								<th style="width: 50%; text-align: center; vertical-align: middle">回報圖片</th>
								</c:if>
							</tr>
							<tr style="font-size:20px">
								<td style="width: 50%; text-align: center; vertical-align: middle" id="reportlistfont">${report.reptDetail}</td>
								<c:if test="${not empty reportImg}">
									<td><c:forEach var="rImg" items="${reportImg}">
											<c:if test="${rImg.reptNo==report.reptNo}">
<%-- 												<img src="..${rImg.imgPath}" width="290" height="225"> --%>
												<figure style="display: inline-block"> 
												<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
												<img src='..${rImg.imgPath}' width="150" height="100" style="margin-right:-100px;padding-top:15px"></a> </figure>
											</c:if>
										</c:forEach></td>
								</c:if>
							</tr>
						</table>
					</div>
				</center>
				<c:if test="${not empty report.replyDetail}">
				<div>
					<table style="width:100%;border-style: ridge">
						<tr style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle">
							<th style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle; font-size: 18px;">管理員回覆</th>
						</tr>
						<tr>
							<td style="font-size: 16px;">${report.replyDetail}</td>
						</tr>
					</table>
				</div>
				</c:if>
				<br> <br> <br>
				<c:if test="${not empty reportDetail}">
					<center>
					<h3 style="color:#FE2E64">回報單後續處理內容</h3>
					</center>
				</c:if>	
				<c:forEach var="rDetail" items="${reportDetail}">
					<table width="100%">
						<tbody>
						<thead>
							<tr>
								<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle">回報日期</th>
								<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle">主旨</th>
								<!-- 									<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle"></th> -->
							</tr>
						</thead>
						<tr>
							<td style="width: 50%; text-align: center; vertical-align: middle"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${rDetail.reptUpDate}" /></td>
							<td style="width: 50%; text-align: center; vertical-align: middle">${rDetail.reptUpDetail}</td>
					</table>
					<!-- 圖片 -->
					<center>
						<div>
							<table border="1" width="100%">
								<c:if test="${not empty reportDetailImg}">					
										<tr>
											<th>回報圖片</th>
										</tr>
								</c:if>
								<td>
								<c:forEach var="rImg" items="${reportDetailImg}">
									<c:if test="${rDetail.reptUpNo==rImg.reptUpNo}">
											<img src="..${rImg.imgUpPath}" width="300" height="225">
									</c:if>
								</c:forEach>
								</td>
							</table>
						</div>
					</center>
					<!-- 管理員回覆 -->
					<c:if test="${not empty rDetail.replyUpDetail}">
							<table width="100%;border-style: ridge">
								<tr>
									<th style="text-align: center; vertical-align: middle">管理員回覆</th>
								</tr>
								<tr>
									<td style="text-align: center; vertical-align: middle">${rDetail.replyUpDetail}</td>
								</tr>
							</table>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>