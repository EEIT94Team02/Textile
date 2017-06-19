<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/jquery-ui-1.12.1.css">
<link rel="stylesheet" href="../css/jquery-ui-timepicker-addon.css">
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.12.1.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript">
	$(function() {
		$('#begin').datetimepicker({
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
	<form action='<c:url value="/activity/select.do"/>' method="post">
		<table>
			<tr>
				<td>活動編號：</td>
				<td><input type="text" name="activityno" value="${param.activityno}" ></td>
				<td>${activitySelectErrors.activityno}</td>
			</tr>
			<tr>
				<td>活動名稱：</td>
				<td><input type="text" name="activityname" value="${param.activityname}" ></td>
				<td>${activitySelectErrors.activityname}</td>
			</tr>
			<tr>
				<td>開始時間：</td>
				<td><input type="text" id="begin" name="begintime" class="ui-datetimepicker" placeholder="請點擊選擇開始時間"></td>
				<td>${activitySelectErrors.begintime}</td>
			</tr>
			<tr>
				<td>結束時間：</td>
				<td><input type="text" id="end" name="endtime" class="ui-datetimepicker" placeholder="請點擊選擇結束時間"></td>
				<td>${activitySelectErrors.endtime}</td>
			</tr>
			<tr>
				<td>活動地點：</td>
				<td><input type="text" name="place" value="${param.place}" /></td>
				<td>${activitySelectErrors.place}</td>
			</tr>
			<tr>
				<td>活動內容：</td>
				<td><textarea name="interpretation">${param.interpretation}</textarea></td>
				<td>${activitySelectErrors.interpretation}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td>${activitySelectErrors.select}</td>
			</tr>
		</table>
	</form>

	<hr>

	<form action='<c:url value="/activity/partner.do"/>' method="post">
		<table>
			<tr>
				<td>活動編號：</td>
				<td><input type="text" name="activityno" value="${param.activityno}" /></td>
				<td>${activitySelectErrors.activityno}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td>${activitySelectErrors.partner}</td>
			</tr>
		</table>
	</form>

	<hr>

	<form action='<c:url value="/activity/partake.do"/>' method="post">
		<table>
			<tr>
				<td>會員編號：</td>
				<td><input type="text" name="mId" value="${param.mId}" /></td>
				<td>${activitySelectErrors.mId}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td>${activitySelectErrors.partake}</td>
			</tr>
		</table>
	</form>

</body>
</html>