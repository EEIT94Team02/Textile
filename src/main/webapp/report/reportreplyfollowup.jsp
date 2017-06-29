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
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src='<c:url value="/js/lightbox.js"/>'></script>
<script src="/js/lightbox.js"></script>
<script src='<c:url value="/js/bootstrap.min.js"/>'></script>
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
		<div id="body">
			<div style="padding-top:40px;padding-bottom:320px;opacity: 0.9; color: #FFFFFF; width: 100%;background-image: url(../image/background/reportbackground.jpg)">
				<center>
					<h3>客服回覆</h3>
					
					<form action='<c:url value="../report/replyfollowupsuccess.do" />' method="post">
						<table style="width:50%;border-style: ridge">
							<tbody>
							<thead>
								<tr style="font-size: 22px">
									<th style="background-color: black;color: white;text-align:center">回報日期</th>
								</tr>
							</thead>
							<tr style="font-size: 20px">
								<th style="background-color: black;color: #94f200;text-align:center"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptUpDate}" /> <input type="hidden" name="reptUpNo" value="${report.reptUpNo}"> <input type="hidden" name="reptDate" value="${report.reptUpDate}"></th>
							</tr>
							</tbody>
						</table>
						
						<!-- 	圖片	 -->
				
					<div style="padding-bottom: 30px">
						<table style="width:80%;padding-top:30px;font-size:20px">
							<tr>
								<c:choose>
    								<c:when test="${not empty reportImg}">
    									<th style="width: 50%; text-align: center; vertical-align: middle">回報內容</th>
										<th style="width: 50%; text-align: center; vertical-align: middle">回報圖片</th>
    								</c:when>    						
    							<c:otherwise>
    									<th style="text-align: center; vertical-align: middle">回報內容</th>
    							</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<c:choose>
    								<c:when test="${not empty reportImg}">
    									<td style="width: 50%;text-align: center; vertical-align: middle;font-size:20px" id="reportlistfont">${report.reptUpDetail}</td>
										<td>
											<c:forEach var="rImg" items="${reportImg}">
												<figure style="display: inline-block"> 
												<a href='<c:url value="..${rImg.imgUpPath}"/>' data-lightbox="main"> 
												<img src='..${rImg.imgUpPath}' width="150" height="100"></a> </figure>
											</c:forEach>
										</td>
											
    								</c:when>    						
    							<c:otherwise>
    									<td style="text-align: center; vertical-align: middle;font-size:20px" id="reportlistfont">${report.reptUpDetail}</td>
    							</c:otherwise>
								</c:choose>
							
								
							</tr>
						</table>
					</div>

					<textarea name="replyUpDetail" rows="10" cols="100" placeholder="請填寫回覆內容" style="color: black"></textarea>
					<br> <input type="Submit" id="ButtonSubmit" class="btn btn-success" value="送出" />		
				</form>
				</center>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>