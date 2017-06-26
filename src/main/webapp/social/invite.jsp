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
	<h1>Textile SocialList invite</h1>	
		<form action='<c:url value="invite.do"/>' method="post">
			<table>
				
				<tr>
					<td>朋友id：</td>
					<td><input type="text" name="acquaintenceId" value="${param.acquaintenceId}" /></td>
					<td>${SocialListInviteErrors.acquaintenceId}</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="submit" value="邀請"></td>
					<td>${SocialListInviteErrors.insert}</td>
				</tr>
			</table>
		</form>

</body>
</html>