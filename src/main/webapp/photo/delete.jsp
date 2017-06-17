<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="/photo/album/delete.do"/>' method="post">
		<table>
			<tr>
				<td>相簿編號：</td>
				<td><input type="text" name="albumno" value="${param.albumno}" /></td>
				<td>${albumCRDErrors.delete}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="刪除"></td>
				<td></td>
			</tr>
		</table>
	</form>

	<hr>

	<form action='<c:url value="/photo/album/deleteAll.do"/>' method="post">
		<table>
			<tr>
				<td>請輸入會員ID：</td>
				<td><input type="text" name="mId" value="${param.mId}" /></td>
				<td>${albumCRDErrors.deleteAll}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="刪除全部"></td>
				<td></td>
			</tr>
		</table>
	</form>


</body>
</html>