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
		<div id="body" style="background-image: url(../image/background/reportbackground.jpg)">
			<div style="opacity: 0.7; background-color: #1C1C1C; color: #FFFFFF; width: 100% ;height: 500%">
				<br> <br> <br> <br> <br>
				<form action='<c:url value="../report/replysuccess.do" />' method="post">
					<table border="1" width="100%">
						<tbody>
						<thead>
							<tr>
								<th>回報編號</th>
								<th>回報日期</th>
								<th>回報種類</th>
							</tr>
						</thead>
						<tr>
							<th>${report.reptNo}<input type="hidden" name="reptNo" value="${report.reptNo}"></th>
							<th><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptDate}" /> <input type="hidden" name="reptDate" value="${report.reptDate}"></th>
							<th>${report.reptType}<input type="hidden" name="reptType" value="${report.reptType}"><input type="hidden" name="reptDetail" value="${report.reptDetail}"> <input type="hidden" name="reptDetail" value="${report.reptDetail}"></th>
						</tr>
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
								<td style="width: 50%;" id="reportlistfont">${report.reptDetail}</td>
								<c:if test="${not empty reportImg}">
									<td>
									<c:forEach var="rImg" items="${reportImg}">
										<figure style="display: inline-block"> 
										<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
										<img src='..${rImg.imgPath}' width="150" height="100"></a> </figure>
									</c:forEach>
									</td>
								</c:if>
							</tr>
						</table>
					</div>
				</center>
				<center>
					<h3>客服回覆</h3>
					<textarea name="replyDetail" rows="10" cols="100" placeholder="請填寫回覆內容"></textarea>
					<br> <input type="Submit" id="ButtonSubmit" value="送出" />
				</center>
				</form>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>