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
		<div id="body">
			<div style="padding-top:40px;padding-bottom:40px;opacity: 0.9; color: #FFFFFF; width: 100%;background-image: url(../image/background/reportbackground.jpg)">
				<center>
					<h2>先前回報內容</h2>
					<form action='<c:url value="reportUpDate.do" />' enctype="multipart/form-data" method="post">
						<table style="width:80%;border-style: solid;">
							<tbody>
							<thead>
								<tr style="font-size: 22px">
									<th style="width: 26%">會員暱稱</th>
									<th style="width: 26%">回報日期</th>
									<th style="width: 26%">回報種類</th>
								</tr>
							</thead>
							<tr style="font-size: 20px">
								<th>${user.mName}<input type="hidden" name="reptNo" value="${report.reptNo}"><input type="hidden" name="mId" value="${report.mId}"></th>
								<th><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${report.reptDate}" /> <input type="hidden" name="reptDate" value="${report.reptDate}"></th>
								<th>${report.reptType}<input type="hidden" name="reptType" value="${report.reptType}"><input type="hidden" name="reptDetail" value="${report.reptDetail}"></th>
							</tbody>
						</table>
						<center>
						
					<div>
						<table style="width:80%;padding-top:30px;font-size:20px">
							<tr>
								<th style="width: 26%; text-align: center; vertical-align: middle">回報內容</th>
								<th style="width: 26%; text-align: center; vertical-align: middle"></th>
								<c:if test="${not empty reportImg}">
								<th style="width: 26%; text-align: center; vertical-align: middle">回報圖片</th>
								</c:if>
							</tr>
							<tr>
								<td style="width: 26%;" id="reportlistfont">${report.reptDetail}</td>
								<td style="width: 26%;" id="reportlistfont"></td>
								<c:if test="${not empty reportImg}">
									<td><c:forEach var="rImg" items="${reportImg}">
											<c:if test="${rImg.reptNo==report.reptNo}">
<%-- 												<img src="..${rImg.imgPath}" width="290" height="225"> --%>
													<figure style="display: inline-block"> 
													<a href='<c:url value="..${rImg.imgPath}"/>' data-lightbox="main"> 
													<img src='..${rImg.imgPath}' width="150" height="100" style="margin:-50px;padding-top:50px"></a> </figure>
											</c:if>
										</c:forEach></td>
								</c:if>
							</tr>
						</table>
					</div>
					<br> <br> <br>
				<c:if test="${not empty report.replyDetail}">
				<div>
					<table width="100%">
						<tr style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle">
							<th style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle; font-size: 18px;">客服回覆</th>
						</tr>
						<tr>
							<td style="font-size: 16px;">${report.replyDetail}</td>
						</tr>
					</table>
				</div>
				</c:if>
				</center>
					<br> <br> <br>						
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
					<br> <br> <br>
				<c:if test="${not empty rDetail.replyUpDetail}">
				<div>
					<table width="100%">
						<tr style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle">
							<th style="border-bottom: 1px solid #ddd; text-align: center; vertical-align: middle; font-size: 18px;">客服回覆</th>
						</tr>
						<tr>
							<td style="font-size: 16px;">${rDetail.replyUpDetail}</td>
						</tr>
					</table>
				</div>
				</c:if>
					
					
					</c:forEach>
						<br> <br> <br>						
						<textarea name="reptUpDetail" rows="10" cols="100" placeholder="請詳述您的問題。" ></textarea>
						<span class="error">${errors.message}</span><br> <label class="fontSize">
						上傳圖片：</label> <Input Type="file" name="file" multiple accept="image/*">
						<input type="submit" value="送出" >
						<h6>可上傳檔案格式：BMP、GIF、JPG、LOG、PCX、PDF、PNG、SPX、TGA、TXT、SC2REPLAY與STORMREPLAY。您最多可附加4個檔案，每個檔案大小不可超出5MB。</h6>
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