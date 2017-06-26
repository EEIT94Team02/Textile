<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
					<li class="list" id="reportcreate">
					<a href="../report/situationlist.do">查詢所有未處理回報單</a>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
		<!--預留給聊天室的區塊-->
		<div id="right">預留給聊天室的區塊</div>
		<div id="body" style="opacity: 0.8; position: absolute; background-image: url(../image/background/reportbackground.jpg); color: #E6E6E6">
			<form action='<c:url value="createNewReport.do" />' enctype="multipart/form-data" method="post">
				<div id="reportcreate">類別</div>
				<select name="reptType" id="reportcreate">
					<option value="會員">會員</option>
					<option value="相簿">相簿</option>
					<option value="活動">活動</option>
					<option value="商店">商店</option>
					<option value="儲值">儲值</option>
				</select><span class="error">${errors.reportType}</span> <br>
				<div id="reportcreate">狀況敘述</div>
				<textarea name="reptDetail" rows=40% cols=80% placeholder="請詳述您的問題。" ></textarea>
				<div style="display: inline" class="error" id="reportcreate">${errors.message}</div>
				<br>
				<label class="fontSize" id="reportcreate">上傳圖片：</label> 
				<Input Type="file" name="file" multiple accept="image/*" >
				<input type="submit" value="送出" style="width:120px;height:30px;font-size:20px;position:relative;bottom:-10px; left:-25px;">
				<input type="reset" value="清除內容" id="reportreset" style="width:120px;height:30px;font-size:20px;position:relative;bottom:-10px; left:-10px;">
								<h6>可上傳檔案格式：BMP、GIF、JPG、LOG、PCX、PDF、PNG、SPX、TGA、TXT、SC2REPLAY與STORMREPLAY。您最多可附加4個檔案，每個檔案大小不可超出5MB。</h6>
				<!--   <input type="button" value="Clear" onclick="clearForm()"> -->
				<h3>
					<span class="error" id="reportcreate">${errors.action}</span>
				</h3>
			</form>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>