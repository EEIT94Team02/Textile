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
<link href="../css/lightbox.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src='<c:url value="/js/lightbox.js"/>'></script>
<script src='<c:url value="/js/bootstrap.min.js"/>'></script>
<style type="text/css">
body{}
	.bodyDiv{
	position: absolute;
	top: 70px;
	left: 21%;
	bottom: 40px;
	min-height: 1000px;
	background: #FFD1A4;
	width: 63%;
	overflow: auto;
	opacity: 0.8;
	font-weight: bold;
	}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div>
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
		<div class="bodyDiv" style="background-image: url(../image/background/reportbackground.jpg)">
			<center>
			<div style="opacity: 0.8; color: #FFFFFF; width: 100% ;height: 500%">
				<br> <br> <br> <br> <br>
				<form action='<c:url value="../report/replysuccess.do" />' method="post">
					<table style="border:1px;width:80%;padding-bottom:20px">
						<tbody>
						<thead>
							<tr style="font-size: 22px">
								<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: white">回報編號</th>
								<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: white">回報日期</th>
								<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: white">回報種類</th>
							</tr>
						</thead>
						<tr style="font-size: 20px">
							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #94f200">${report.reptNo}<input type="hidden" name="reptNo" value="${report.reptNo}"></th>
							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #94f200"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptDate}" /> 
							<input type="hidden" name="reptDate" value="${report.reptDate}">
							<input type="hidden" name="reptType" value="${report.reptType}">
							<input type="hidden" name="reptDetail" value="${report.reptDetail}"> 
							</th>
<%-- 							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #94f200">${report.reptType} --%>
<%-- 							<input type="hidden" name="reptType" value="${report.reptType}"> --%>
<%-- 							<input type="hidden" name="reptDetail" value="${report.reptDetail}">  --%>
<%-- 							<input type="hidden" name="reptDetail" value="${report.reptDetail}"></th> --%>
							<c:choose>
    						<c:when test="${report.reptType=='會員'}">
    							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #ff5b0f">${report.reptType}</th>
    						</c:when>
    						<c:when test="${report.reptType=='相簿'}">
       							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #00bdf1">${report.reptType}</th>
    						</c:when>
    						<c:when test="${report.reptType=='活動'}">
       							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #bf51ff">${report.reptType}</th>
    						</c:when>
    						<c:when test="${report.reptType=='商店'}">
       							<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #ff4988">${report.reptType}</th>
    						</c:when>
    						<c:otherwise>
        						<th style="border-bottom: 1px solid #ddd; width: 26%; text-align: center; vertical-align: middle;background-color: black;color: #ffdb0f">${report.reptType}</th>
    						</c:otherwise>
							</c:choose>
							
						</tr>
						</tbody>
					</table>
			<!-- 	圖片	 -->
				<center>
					<div style="padding-top: 50px">
						<table style="width: 80%;padding-bottom:40px">
							<tr style="font-size: 22px">
								<c:choose>
    								<c:when test="${not empty reportImg}">
    								<th style="width: 26%; text-align: center; vertical-align: middle; font-size:20px">回報內容</th>
									<th style="width: 26%; text-align: center; vertical-align: middle"></th>
									<th style="width: 26%; text-align: center; vertical-align: middle; font-size:20px">回報圖片</th>
    							</c:when>
    							<c:otherwise>
        							<th style="text-align: center; vertical-align: middle; font-size:20px">回報內容</th>
    							</c:otherwise>
								</c:choose>
								
							</tr>
							<tr>
							
								<c:choose>
    								<c:when test="${not empty reportImg}">
    								<th style="width: 26%; text-align: center; vertical-align: middle; font-size:20px" id="reportlistfont">${report.reptDetail}</th>
									<th style="width: 26%; text-align: center; vertical-align: middle"></th>
									<td>
									<c:forEach var="rImg" items="${reportImg}">
										<figure style="display: inline-block"> 
										<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
										<img src='..${rImg.imgPath}' width="300" height="250" style="margin-right:-100px;padding-top:15px"></a>
										</figure>
									</c:forEach>
									</td>
    							</c:when>
    							<c:otherwise>
        								<td style="text-align: center; vertical-align: middle; font-size:20px" id="reportlistfont">${report.reptDetail}</td>
    							</c:otherwise>
								</c:choose>
							
<%-- 								<td style="width: 50%;" id="reportlistfont">${report.reptDetail}</td> --%>
<%-- 								<c:if test="${not empty reportImg}"> --%>
<!-- 									<td> -->
<%-- 									<c:forEach var="rImg" items="${reportImg}"> --%>
<!-- 										<figure style="display: inline-block">  -->
<%-- 										<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main">  --%>
<%-- 										<img src='..${rImg.imgPath}' width="150" height="100"></a> </figure> --%>
<%-- 									</c:forEach> --%>
<!-- 									</td> -->
<%-- 								</c:if> --%>
							</tr>
						</table>
					</div>
				</center>
				<center>
					<h3 style="padding-top: 100px">客服回覆</h3>
					<textarea name="replyDetail" style="color: black" rows="10" cols="100" placeholder="請填寫回覆內容"></textarea>
					<br> <input type="Submit" class="btn btn-success" id="ButtonSubmit" value="送出" />
				</center>
				</form>
			</div>
			</center>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>