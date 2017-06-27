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
<link rel=stylesheet type="text/css" href="<c:url value = '/css/jacky.css'/>">
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
		<div id="right">預留給聊天室的區塊</div>
		<div id="body">
			<form action='<c:url value="createNewReport.do" />' enctype="multipart/form-data" method="post">
				<div id="reportcreate">類別</div>
				<select name="reptType" id="reportcreate">
					<option value="會員">會員</option>
					<option value="相簿">相簿</option>
					<option value="活動">活動</option>
					<option value="商店">商店</option>
					<option value="儲值">儲值</option>
				</select><span class="error">${errors.reportType}</span><Input Type="file" name="file" multiple accept="image/*"> <br>
				<div id="reportcreate">狀況敘述</div>
				<textarea name="reptDetail" rows=20 cols=100 placeholder="請詳述您的問題。"></textarea>
				<br>
				<div style="display: inline" class="error" id="reportcreate">${errors.message}</div>
				 <input type="submit" class="btn" value="送出"> <input type="reset" class="btn" name="submit"
					value="清除內容" id="reportreset">
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