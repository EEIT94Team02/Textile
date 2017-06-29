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
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/lightbox.css"/>'>
<link href="/css/lightbox.css" rel="stylesheet">
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
		<div class="bodyDiv">
			<div style="padding-top:15px;padding-bottom:280px;opacity: 0.9; color: #FFFFFF; width: 100%;background-image: url(../image/background/reportbackground.jpg)">
				<center>
					<h2>先前回報內容</h2>
					<form action='<c:url value="reportUpDate.do" />' enctype="multipart/form-data" method="post">
						<table style="width:80%;border-style: solid">
							<tbody>
							<thead>
								<tr style="font-size: 22px">
									<th style="width: 26%;background-color: black;color: white;text-align:center">會員暱稱</th>
									<th style="width: 26%;background-color: black;color: white;text-align:center">回報日期</th>
									<th style="width: 26%;background-color: black;color: white;text-align:center">回報種類</th>
								</tr>
							</thead>
							<tr style="font-size: 20px">
								<th style="background-color: black;color: #94f200;text-align:center">${user.mName}<input type="hidden" name="reptNo" value="${report.reptNo}"><input type="hidden" name="mId" value="${report.mId}"></th>
								<th style="background-color: black;color: #94f200;text-align:center">
								<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptDate}" /> 
								<input type="hidden" name="reptDate" value="${report.reptDate}">
								<input type="hidden" name="reptType" value="${report.reptType}">
								<input type="hidden" name="reptDetail" value="${report.reptDetail}"></th>
<%-- 								<th style="background-color: black;color: #94f200">${report.reptType}<input type="hidden" name="reptType" value="${report.reptType}"><input type="hidden" name="reptDetail" value="${report.reptDetail}"></th> --%>
							
								<c:choose>
    								<c:when test="${report.reptType=='會員'}">
    							<th style="background-color: black;color: #ff5b0f;text-align:center">${report.reptType}</th>
    							</c:when>
    								<c:when test="${report.reptType=='相簿'}">
       							<th style="background-color: black;color: #00bdf1;text-align:center">${report.reptType}</th>
    							</c:when>
    								<c:when test="${report.reptType=='活動'}">
       							<th style="background-color: black;color: #bf51ff;text-align:center">${report.reptType}</th>
    							</c:when>
    								<c:when test="${report.reptType=='商店'}">
       							<th style="background-color: black;color: #ff4988;text-align:center">${report.reptType}</th>
    							</c:when>
    							<c:otherwise>
        						<th style="background-color: black;color: #ffdb0f;text-align:center">${report.reptType}</th>
    							</c:otherwise>
								</c:choose>
							
							</tbody>
						</table>
						<center>
						
					<div style="padding-top: 20px">
						<table style="width:80%;padding-top:30px;font-size:20px">
							<tr>
<!-- 								<th style="width: 26%; text-align: center; vertical-align: middle">回報內容</th> -->
<!-- 								<th style="width: 26%; text-align: center; vertical-align: middle"></th> -->
<%-- 								<c:if test="${not empty reportImg}"> --%>
<!-- 								<th style="width: 26%; text-align: center; vertical-align: middle">回報圖片</th> -->
<%-- 								</c:if> --%>
								
								<c:choose>
    								<c:when test="${not empty reportImg}">
    									<th style="width: 26%; text-align: center; vertical-align: middle">回報內容</th>
										<th style="width: 26%; text-align: center; vertical-align: middle"></th>
										<th style="width: 26%; text-align: center; vertical-align: middle">回報圖片</th>
    								</c:when>    						
    							<c:otherwise>
										<th style="text-align: center; vertical-align: middle">回報內容</th>
    							</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<c:choose>
    								<c:when test="${not empty reportImg}">
    									<td style="width: 26%;font-size:20px" id="reportlistfont">${report.reptDetail}</td>
										<td style="width: 26%;font-size:20px" id="reportlistfont"></td>
										<c:if test="${not empty reportImg}">
											<td><c:forEach var="rImg" items="${reportImg}">
												<c:if test="${rImg.reptNo==report.reptNo}">
<%-- 												<img src="..${rImg.imgPath}" width="290" height="225"> --%>
													<figure style="display: inline-block"> 
													<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
													<img src='..${rImg.imgPath}' width="300" height="225" style="margin:-50px;padding-top:50px"></a> </figure>
											</c:if>
										</c:forEach>
									</td>
								</c:if>
    								</c:when>    						
    							<c:otherwise>
    									<td style="text-align: center; vertical-align: middle;font-size:20px" id="reportlistfont">${report.reptDetail}</td>
    							</c:otherwise>
								</c:choose>
					
							</tr>
						</table>
					</div>
					<br> <br> <br>
				<c:if test="${not empty report.replyDetail}">
				<div style="padding-top:25px">
					<table style="height: 150px;width: 80%">
						<tr >
							<th style="font-size: 20px;text-align:center;border-bottom: 1px solid #ddd">客服回覆</th>
						</tr>
						<tr>
							<td style="font-size: 18px;text-align:initial">${report.replyDetail}</td>
						</tr>
					</table>
				</div>
				</c:if>
				</center>
					<br> <br> <br>
					<center>
							<h3 style="color:#FE2E64">先前回報內容</h3>
					</center>						
					<c:forEach var="rDetail" items="${reportDetail}">
					<table style="width:80%;border-style: solid">
						<tbody>
						<thead>
							<tr>
								<th style="width: 33%; text-align: center; vertical-align: middle;font-size: 22px;background-color: black;color: #ffffff">回報日期</th>
								<th style="width: 33%; text-align: center; vertical-align: middle;font-size: 22px;background-color: black;color: #ffffff">主旨</th>
								<!-- 									<th style="border-bottom: 1px solid #ddd; width: 33%; text-align: center; vertical-align: middle"></th> -->
							</tr>
						</thead>
						<tr>
							<td style="width: 50%; text-align: center; vertical-align: middle;font-size: 18px;background-color: black;color: #94f200"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${rDetail.reptUpDate}" /></td>
							<td style="width: 50%; text-align: center; vertical-align: middle;font-size: 18px;background-color: black;color: #94f200">${rDetail.reptUpDetail}</td>
					</table>
					<!-- 圖片 -->
					<center>
						<div style="padding-top:40px">
							<table>
								<c:if test="${not empty reportDetailImg}">					
										<tr>
											<th style=";font-size: 22px">回報圖片</th>
										</tr>
								</c:if>
								<td>
								<c:forEach var="rImg" items="${reportDetailImg}">
									<c:if test="${rDetail.reptUpNo==rImg.reptUpNo}">
<%-- 											<img src="..${rImg.imgUpPath}" width="300" height="225"> --%>
											<figure style="display: inline-block"> 
											<a href='<c:url value="..${rImg.imgUpPath}"/>' data-lightbox="main"> 
											<img src='..${rImg.imgUpPath}' width="300" height="225" style="margin-right:-100px;padding-top:15px"></a> </figure>
									</c:if>
								</c:forEach>
								</td>
							</table>
						</div>
					</center>
					<br> <br> <br>
				<c:if test="${not empty rDetail.replyUpDetail}">
				<div style="padding-bottom: 40px">
					<table width="80%">
						<tr style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle">
							<th style="font-size: 20px;width: 80%;text-align:center">客服回覆</th>
						</tr>
						<tr>
							<td style="font-size: 18px;text-align:initial">${rDetail.replyUpDetail}</td>
						</tr>
					</table>
				</div>
				</c:if>
					
					
					</c:forEach>
						<br> <br> <br>						
						<textarea name="reptUpDetail" rows="10" cols="100" style="color: black" placeholder="請詳述您的問題。"  ></textarea>
						<span class="error">${errors.message}</span>
						 <br>
						<label class="fontSize" style="margin-right: 750px">
						上傳圖片：</label> <Input Type="file" name="file" multiple accept="image/*" style="margin-right: 560px">
						<h6>可上傳檔案格式：BMP、GIF、JPG、LOG、PCX、PDF、PNG、SPX、TGA、TXT、SC2REPLAY與STORMREPLAY。您最多可附加4個檔案，每個檔案大小不可超出5MB。</h6>
						<input type="submit" class="btn btn-success" value="送出" >
						<h3>
							<span class="error">${errors.action}</span>
						</h3>
					</form>
				</center>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="/footerInclude.jsp" /></div>
</body>
</html>