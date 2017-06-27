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
		<div id="body" style="background-image:url(../image/background/reportbackground.jpg)">
		<br>
		<br>
		<br>
		<div style="opacity:0.6;position:absolute;background-color:#1C1C1C;color:#FFFFFF;width:100%">
			<table style="line-height:40px;width:100%;rules:rows">
				<tbody>
				<thead>
					<tr>
						<th style="border-bottom: 1px solid #ddd">回報日期</th>
						<th style="border-bottom: 1px solid #ddd">回報類別</th>
<!-- 						<th width="20%">回報內容</th> -->
<!-- 						<th width="20%">管理員回覆</th> -->
						<th style="border-bottom: 1px solid #ddd">回覆狀況</th>
<!-- 						<th>回報圖片</th> -->
						<th style="border-bottom: 1px solid #ddd">詳細內容</th>
						<th style="border-bottom: 1px solid #ddd">補充回報單內容</th>
					</tr>
				</thead>
				<c:forEach var="rList" items="${reportList}">
					<c:url value="reportSwitchPage.do" var="link" scope="session">
						<c:param name="reptNo" value="${rList.reptNo}"></c:param>
						<c:param name="mId" value="${rList.mId}"></c:param>
						<c:param name="reptDate" value="${rList.reptDate}"></c:param>
						<c:param name="reptType" value="${rList.reptType}"></c:param>
						<c:param name="reptDetail" value="${rList.reptDetail}"></c:param>
						<c:param name="replyDetail" value="${rList.reptDetail}"></c:param>
						<c:param name="situation" value="${rList.situation}"></c:param>
					</c:url>
					<c:url value="reportdetail.do" var="detail" scope="session">
						<c:param name="reptNo" value="${rList.reptNo}"></c:param>
						<c:param name="mId" value="${rList.mId}"></c:param>
						<c:param name="reptDate" value="${rList.reptDate}"></c:param>
						<c:param name="reptType" value="${rList.reptType}"></c:param>
						<c:param name="reptDetail" value="${rList.reptDetail}"></c:param>
						<c:param name="replyDetail" value="${rList.reptDetail}"></c:param>
						<c:param name="situation" value="${rList.situation}"></c:param>
					</c:url>
					<tr>
						<td style="border-bottom: 1px solid #ddd;width:5%"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${rList.reptDate}" /> <c:set var="rList" scope="session" value="${reportList}" /></td>
						<td style="border-bottom: 1px solid #ddd;width:12%;text-align:center;vertical-align:middle">${rList.reptType}</td>
<%-- 						<td valign="top">${rList.reptDetail}</td> --%>
<%-- 						<td valign="top">${rList.replyDetail}</td> --%>
						<td style="border-bottom: 1px solid #ddd;width:5%;text-align:center;vertical-align:middle">${rList.situation?'已回覆':'未回覆'}</td>
<%-- 						<td><c:forEach var="rImg" items="${reportimg}"> --%>
<%-- 								<c:if test="${rImg.reptNo==rList.reptNo}"> --%>
<%-- 									<img src="..${rImg.imgPath}" width="150" height="105"> --%>
<%-- 								</c:if> --%>
<%-- 							</c:forEach></td> --%>
						<td style="border-bottom: 1px solid #ddd;width:10%;text-align:center;vertical-align:middle" id="report"><a href="${detail}">詳細內容</a></td>
						<td style="border-bottom: 1px solid #ddd;width:10%;text-align:center;vertical-align:middle" id="report"><a href="${link}">補充回報內容</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>