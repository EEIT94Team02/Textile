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
		<div id="body" style="font-family:Microsoft JhengHei">
			<center>
			<div style="padding-top:40px;padding-bottom:570px;opacity: 0.9; color: #FFFFFF; width: 100%;background-image: url(../image/background/reportbackgroundvertical.jpg)">
				<table style="padding-top:40px;border:1px;width:80%;padding-bottom:20px">
					<tbody>
					<thead>
						<tr style="font-size: 22px">
							<th style="border-bottom: 1px solid #ddd;background-color: black;color: white">回報編號</th>
							<th style="border-bottom: 1px solid #ddd;background-color: black;color: white">回報日期</th>
							<th style="border-bottom: 1px solid #ddd;background-color: black;color: white">回報種類</th>
						</tr>
					</thead>
					<tr style="font-size: 20px">
						<c:url value="../report/reportreply.do" var="link" scope="page">
							<c:param name="reptNo" value="${report.reptNo}"></c:param>
							<c:param name="mId" value="${report.mId}"></c:param>
							<c:param name="reptDate" value="${report.reptDate}"></c:param>
							<c:param name="reptType" value="${report.reptType}"></c:param>
							<c:param name="reptDetail" value="${report.reptDetail}"></c:param>
							<c:param name="replyDetail" value="${report.reptDetail}"></c:param>
							<c:param name="situation" value="${report.situation}"></c:param>
						</c:url>
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
						<table style="width:80%;padding-top:40px;padding-bottom:40px">
							<tr>
								<c:choose>
    						<c:when test="${not empty reportImg}">
    							<th style="width: 33%; text-align: center; vertical-align: middle;font-size:25px">回報內容</th>
								<th style="width: 33%; text-align: center; vertical-align: middle"></th>
								<th style="width: 33%; text-align: center; vertical-align: middle;font-size:25px">回報圖片</th>
    							</c:when>
    						<c:otherwise>
    							<th style="text-align: center; vertical-align: middle;font-size:22px">回報內容</th>
    						</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<c:choose>
    								<c:when test="${not empty reportImg}">
    							<td style="width: 33%; text-align: center; vertical-align: middle;font-size: 20px" id="reportlistfont">${report.reptDetail}</td>
								<td style="width: 33%; text-align: center; vertical-align: middle;font-size: 20px" id="reportlistfont"></td>
								<td><c:forEach var="rImg" items="${reportImg}">
											<figure style="display: inline-block"> 
											<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
											<img src='..${rImg.imgPath}' width="150" height="100" style="margin-right:-100px;padding-top:15px"></a> </figure>
									</c:forEach></td>
    							</c:when>
    								<c:otherwise>
    							<td style="text-align: center; vertical-align: middle;font-size: 20px" id="reportlistfont">${report.reptDetail}</td>
    								</c:otherwise>
								</c:choose>
							</tr>
						</table>
					</div>
				</center>
				<!-- 	客服回覆	 -->
				<div >
					<table style="height: 150px;width: 80%">
<!-- 						<tr style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle"> -->
<!-- 							<th style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle; font-size: 23px;">客服回覆</th> -->
<!-- 						</tr> -->
						<tr>
							<c:choose>
								<c:when test="${not empty report.replyDetail}">
									<tr>
									<th style="font-size: 20px;width: 80%">客服回覆</th>
									</tr>
									<th style="font-size: 18px;text-align:initial">${report.replyDetail}</th>
								</c:when>
								<c:otherwise>
									<th style="text-align: center; vertical-align: middle;font-size: 22px" id="report"><a href="${link}">回覆</a></th>
								</c:otherwise>
							</c:choose>
						</tr>
					</table>
				</div>
				<c:if test="${not empty reportDetail}">
					<center>
						<h2 style="color: #FE2E64;padding-top:40px;">回報單後續內容</h2>
					</center>
				</c:if>

				<c:forEach var="rDetail" items="${reportDetail}">
					<div>
						<table style="width:80%;border-style:ridge">
							<tbody>
							<thead>
								<tr>
									<th style="width: 50%; text-align: center; vertical-align: middle;background-color: black;color: white;font-size: 25px">回報日期</th>
									<th style="width: 50%; text-align: center; vertical-align: middle;background-color: black;color: white;font-size: 25px">主旨</th>
								</tr>
							</thead>
							<tr>
								<c:url value="../report/reportreplyfollowup.do" var="follow" scope="page">
									<c:param name="reptUpNo" value="${rDetail.reptUpNo}"></c:param>
									<c:param name="reptUpDate" value="${rDetail.reptUpDate}"></c:param>
									<c:param name="reptUpDetail" value="${rDetail.reptUpDetail}"></c:param>
									<c:param name="replyUpDetail" value="${rDetail.replyUpDetail}"></c:param>
									<c:param name="reptNo" value="${rDetail.reptNo}"></c:param>
								</c:url>
								<td style="width: 50%; text-align: center; vertical-align: middle;background-color: black;color: white;color: #94f200;font-size: 20px"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${rDetail.reptUpDate}" /></td>
								<td style="width: 50%; text-align: center; vertical-align: middle;background-color: black;color: white;color: #0fff93;font-size: 20px">${rDetail.reptUpDetail}</td>
							</tr>
							</tbody>
						</table>
					</div>

					<!-- 圖片 -->
					<c:if test="${not empty reportDetailImg}">	
					<center>
						<div style="padding-top:40px">
							<table>					
										<tr>
											<th>回報圖片</th>
										</tr>	
								<td>
								<c:forEach var="rImg" items="${reportDetailImg}">
									<c:if test="${rDetail.reptUpNo==rImg.reptUpNo}">
											<figure style="display: inline-block">
											<a href='<c:url value="..${rImg.imgUpPath}"/>' data-lightbox="Detail">
											<img src='..${rImg.imgUpPath}' width="300" height="250">
											</a> 
											</figure>
									</c:if>
								</c:forEach>
								</td>
							</table>
						</div>
					</center>
					</c:if>
					<!-- 管理員回覆 -->
						<table style="padding-bottom:40px;width: 80%">
							<tr>
								<th style="font-size: 20px;border-bottom: 1px solid #ddd">客服回覆</th>
							</tr>
							<tr>
								<c:choose>
									<c:when test="${not empty rDetail.replyUpDetail}">
										<th style="font-size: 18px;text-align:initial">${rDetail.replyUpDetail}</th>
									</c:when>
									<c:otherwise>
										<th style="text-align: center; vertical-align: middle;font-size: 22px;" id="report"><a href="${follow}">回覆</a></th>
									</c:otherwise>
								</c:choose>
							</tr>
						</table>
				</c:forEach>
			</div>
			</center>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>