<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Insert title here</title>
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
				<li class="list"><a href='<c:url value="insert.v"/>'>新增公告</a></li>
				<li class="list"><a href='<c:url value="list.v"/>'>查詢公告</a></li>
				<li class="list"><a href='<c:url value="update.v"/>'>修改公告</a></li>
			</ul>
		</div>
	</div>
	<div id="center">
		<div id="body">
			<form action='<c:url value="update.do"/>' method="post">
				<table>
					<tr>
						<td>請輸入公告ID：</td>
						<td><input type="text" name="a_id" value="${param.a_id}" /></td>
						<td>${AnnouncementUpdateErrors.a_id}</td>
					</tr>
					<tr>
						<td>請選擇公告類別：</td>
						<td><select name="a_type">
								<option value="新聞" selected="selected">新聞</option>
								<option value="系統">系統</option>
								<option value="活動">活動</option>
						</select></td>
						<td>${AnnouncementUpdateErrors.a_type}</td>
					</tr>
					<tr>
						<td>請輸入公告主旨：</td>
						<td><input type="text" name="gist" value="${param.gist}" /></td>
						<td>${AnnouncementUpdateErrors.gist}</td>
					</tr>
					<tr>
						<td>請輸入公告內容：</td>
						<td><input type="text" name="msg" value="${param.msg}" /></td>
						<td>${AnnouncementUpdateErrors.msg}</td>
					</tr>
					<tr>
						<td>請輸入公告開始時間：</td>
						<td><input type="text" id="start" name="startTime" class="ui-datetimepicker" placeholder="請點擊選擇開始時間" /></td>
						<td>${AnnouncementUpdateErrors.startTime}</td>
					</tr>
					<tr>
						<td>請輸入結束時間：</td>
						<td><input type="text" id="end" name="endTime" class="ui-datetimepicker" placeholder="請點擊選擇結束時間" /></td>
						<td>${AnnouncementUpdateErrors.endTime}</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="查詢"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>