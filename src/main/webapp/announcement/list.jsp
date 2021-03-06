<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-1.12.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/jquery-ui-1.12.1.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<script src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<script src="<c:url value = '/js/social.list.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript">
	$(function() {
		$('#start').datetimepicker({
			dateFormat : "yy-mm-dd",
			timeFormat : "HH:mm"

		});
		$('#end').datetimepicker({
			dateFormat : "yy-mm-dd",
			timeFormat : "HH:mm"
		});

	});
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
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

	<div id="body">
		<form action="<c:url value="list.do"/>" method="post">
			<table>
				<tr>
					<td>請輸入公告ID：</td>
					<td><input type="text" name="a_id" value="${param.a_id}" /></td>
					<td>${AnnouncementSearchErrors.a_id}</td>
				</tr>
				<tr>
					<td>請輸入公告類別：</td>
					<td><select name="a_type">
							<option value="新聞" selected="selected">新聞</option>
							<option value="系統">系統</option>
							<option value="活動">活動</option>
					</select></td>
					<td>${AnnouncementSearchErrors.a_type}</td>
				</tr>
				<tr>
					<td>請輸入公告主旨：</td>
					<td><input type="text" name="gist" value="${param.gist}" /></td>
					<td>${AnnouncementSearchErrors.gist}</td>
				</tr>
				<tr>
					<td>請輸入公告開始時間：</td>
					<td><input type="text" id="start" name="startTime" class="ui-datetimepicker" placeholder="請點擊選擇開始時間" /></td>
					<td>${AnnouncementSearchErrors.startTime}</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="查詢"></td>
					<td></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>