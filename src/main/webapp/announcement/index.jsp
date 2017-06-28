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
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-1.12.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/jquery-ui-1.12.1.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<script src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<script src="<c:url value = '/js/social.list.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
	<div id="center">
		<div id="body">
			<fieldset>
				<div>
					<label>查詢公告</label>
				</div>
				<div>
					<label>公告類別</label>
				</div>

				<div>
					<input type="radio" name="a_type" />系統 <input type="radio" name="a_type" />新聞 <input type="radio" name="a_type" />活動
					<input type="radio" name="a_type" />商城
					<c:out value=""></c:out>
				</div>
				<div>
					<label>公告時間</label> <input type="text" name="startTime" value=""> <span>${errors.ooo}</span> <input
						type="submit" name="announcement" value="開始查詢">
				</div>
				<br>
				<div>
					<label>列出活動中的公告</label> <input type="submit" name="announcement" value="Select">
				</div>

				<br>
				<div>
					<label>列出已結束公告活動</label> <input type="submit" name="endTime" value="Select">
				</div>
				<br>
				<div>
					<label>新增修改公告</label>

				</div>
			</fieldset>
		</div>
	</div>
	<div id="left">
		<div class="actions">
			<ul>
				<li class="list">公告</li>
				<li class="list"><a href='<c:url value="/manager/insert.v"/>'>新增</a></li>
				<li class="list"><a href='<c:url value="list.v"/>'>查詢</a></li>
				<li class="list"><a href='<c:url value="/manager/update.v"/>'>修改</a></li>
			</ul>
		</div>
	</div>
	<div id="right">
		<jsp:include page="/rightInclude.jsp" />
	</div>
	<div id="footer">this is footer</div>
</body>
</html>



