<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded; charset=UTF-8">
<title>Welcome, Textile.</title>
<link rel="stylesheet" href="../css/jquery-ui-1.12.1.css">
<link rel="stylesheet" href="../css/jquery-ui-timepicker-addon.css">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.12.1.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
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
	<h1>Textile</h1>
	<div id="center">
		<div id="body">
	<form action='<c:url value="insert.do"/>' method="post">
		<table>

			<tr>
				<td>公告主旨：</td>
				<td><input type="text" name="gist" value="${param.gist}" /></td>
				<td>${AnnouncementInsertErrors.gist}</td>
			</tr>
			<tr>
				<td>公告內容：</td>
				<td><input type="text" name="msg" value="${param.msg}" /></td>
				<td>${AnnouncementInsertErrors.introduction}</td>
			</tr>
			<tr>
				<td>開始時間：</td>
				<td><input type="text" id="start" name="startTime" class="ui-datetimepicker" placeholder="請點擊選擇開始時間" /></td>
				<td>${AnnouncementInsertErrors.startTime}</td>
			</tr>
			<tr>
				<td>結束時間：</td>
				<td><input type="text" id="end" name="endTime" class="ui-datetimepicker" placeholder="請點擊選擇結束時間" /></td>
				<td>${AnnouncementInsertErrors.endTime}</td>
			</tr>
			<tr>
				<td>發布時間：</td>
				<td><input type="hidden" name="relTime" value="${param.relTime}" /></td>
			</tr>

			<tr>
				<td>公告種類：</td>
				<td><select name="a_type">
						<option value="新聞" selected="selected">新聞</option>
						<option value="系統">系統</option>
						<option value="活動">活動</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="建立"></td>
				<td>${AnnouncementInsertErrors.insert}</td>
			</tr>
		</table>
	</form>
	</div>
	</div>

</body>
</html>