<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE >
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
		$('#login').datetimepicker({
			dateFormat : "yy-mm",
		});
		

	});
</script>
</head>
<body>

	<h3>SocailList Table</h3>
	<p>
		<a href="invite.v">邀請朋友</a>
	</p>
	<form action="<c:url value="index.do"/>" method="post">
		<table>
			<tr>
				<td>列出社交名單</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="查詢"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<form action="<c:url value="delete.do"/>" method="post">
		<table>
			<tr>
				<td>刪除好友：</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="text" name="acquaintenceId" value="${delete}"></td>
				<td><input type="submit" name="submit" value="刪除"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<form action="<c:url value="search.do"/>" method="post">
		<table>
			<tr>
				<td>條件搜尋：</td>
			</tr>
			<tr>
				<td>名子:</td>
				<td><input type="text" name="mName" value="${search}"></td>
			</tr>
			<tr>
				<td>種類:</td>
				<td><select name="s_type">
						<option value="" selected="selected"></option>
						<option value="好友">好友</option>
						<option value="黑單">黑單</option>
						<option value="追蹤">追蹤</option>
						<option value="未確認">交友邀請</option>
				</select></td>
			</tr>
			<tr>
				<td>群組:</td>
				<td><input type="text" name="s_group" value="${search}"></td>
			</tr>
			<tr>
				<td>結交時間:</td>
				<td><input type="text" id="login" name="login" class="ui-datetimepicker" placeholder="請點擊選擇開始時間" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="條件查詢"></td>
			</tr>
		</table>
	</form>
	<p>
		<a href='<c:url value="/social/select.v"/>'>搜尋朋友列表</a>
	</p>



</body>
</html>
