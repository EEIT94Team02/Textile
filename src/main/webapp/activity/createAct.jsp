<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded; charset=UTF-8">
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
<style type="text/css">
.ui-datetimepicker {
	background: #FFFFFF;
	border: 1px solid #AAAAAA;
	color: #0000AA;
}
</style>
<title>Welcome, Textile.</title>
</head>
<body>
	<h1>Textile</h1>
	<img src="">
	<c:if test="${not empty user}">
		<form action='<c:url value="/activity/create.do"/>' method="post">
			<table>
				<tr>
					<td>活動名稱：</td>
					<td><input type="text" name="activityname" value="${param.activityname}" /></td>
					<td>${activityCRDErrors.activityname}</td>
				</tr>
				<tr>
					<td>開始時間：</td>
					<td><input type="text" id="begin" name="begintime" class="ui-datetimepicker" placeholder="請點擊選擇開始時間"></td>
					<td>${activityCRDErrors.begintime}</td>
				</tr>
				<tr>
					<td>結束時間：</td>
					<td><input type="text" id="end" name="endtime" class="ui-datetimepicker" placeholder="請點擊選擇結束時間"></td>
					<td>${activityCRDErrors.endtime}</td>
				</tr>
				<tr>
					<td>活動地點：</td>
					<td><input type="text" name="place" value="${param.place}" /></td>
					<td>${activityCRDErrors.place}</td>
				</tr>
				<tr>
					<td>活動內容：</td>
					<td><textarea name="interpretation">${param.interpretation}</textarea></td>
					<td>${activityCRDErrors.interpretation}</td>
				</tr>
				<tr>
					<td>活動設定：</td>
					<td><select name="visibility">
							<option value="公開" selected="selected">公開</option>
							<option value="好友">好友</option>
							<option value="私人">私人</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="建立"></td>
					<td>${activityCRDErrors.create}</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>