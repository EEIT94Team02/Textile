<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="/activity/delete.do"/>' method="post">
		<table>
			<tr>
				<td>相簿編號：</td>
				<td><input type="text" name="activityno" value="${param.activityno}" /></td>
				<td>${activityCRDErrors.delete}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="刪除"></td>
				<td></td>
			</tr>
		</table>
	</form>


</body>
</html>