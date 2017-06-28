<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<div id="body" style="padding-top:40px;background-image: url(../image/background/reportbackground.jpg);">
			<div style="opacity: 0.6; position: absolute; background-color: #1C1C1C; color: #FFFFFF; width: 100%">
				<table style="border:1px">
					<tbody>
					<thead>
						<tr>
							<th style="border-bottom: 1px solid #ddd">回報編號</th>
							<th style="border-bottom: 1px solid #ddd">回報日期</th>
							<th style="border-bottom: 1px solid #ddd">回報類別</th>
							<th style="border-bottom: 1px solid #ddd">回報內容</th>
							<th style="border-bottom: 1px solid #ddd">客服回覆</th>
<!-- 							<th style="border-bottom: 1px solid #ddd">回覆狀況</th> -->
<!-- 							<th style="border-bottom: 1px solid #ddd">回報圖片</th> -->
						</tr>
					</thead>
					<c:forEach var="sList" items="${situationList}">
						<c:url value="reportreplydetail.do" var="link" scope="page">
							<c:param name="reptNo" value="${sList.reptNo}"></c:param>
							<c:param name="mId" value="${sList.mId}"></c:param>
							<c:param name="reptDate" value="${sList.reptDate}"></c:param>
							<c:param name="reptType" value="${sList.reptType}"></c:param>
							<c:param name="reptDetail" value="${sList.reptDetail}"></c:param>
							<c:param name="replyDetail" value="${sList.reptDetail}"></c:param>
							<c:param name="situation" value="${sList.situation}"></c:param>
						</c:url>
						<tr>
							<td style="border-bottom: 1px solid #ddd;width:6%;text-align:center;vertical-align:middle">${sList.reptNo}</td>
							<td style="border-bottom: 1px solid #ddd;width:8%;text-align:center;vertical-align:middle"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${sList.reptDate}" /></td>
							<td style="border-bottom: 1px solid #ddd;width:10%;text-align:center;vertical-align:middle">${sList.reptType}</td>
							<td style="border-bottom: 1px solid #ddd;width:45%">${sList.reptDetail}</td>
							<td style="border-bottom: 1px solid #ddd;width:8%;text-align:center;vertical-align:middle" id="report"><a href="${link}">回覆</a></td>
<%-- 							<td>${sList.situation?'已回覆':'未回覆'}</td> --%>
<%-- 							<td><c:forEach var="rImg" items="${reportimg}"> --%>
<%-- 									<c:if test="${rImg.reptNo==sList.reptNo}"> --%>
<%-- 										<img src="..${rImg.imgPath}" width="300" height="225"> --%>
<%-- 									</c:if> --%>
<%-- 								</c:forEach></td> --%>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>