<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="search.do"/>' method="post">
		<table>
			<tr>
				<td>請輸入圖片名稱：</td>
				<td><input type="text" name="photoname" value="${param.photoname}" /></td>
				<td>${searchPhotoErrors.photoname}</td>
			</tr>
			<tr>
				<td>請輸入圖片說明：</td>
				<td><input type="text" name="interpretation" value="${param.interpretation}" /></td>
				<td>${searchPhotoErrors.interpretation}</td>
			</tr>
			<tr>
				<td>請輸入分類：</td>
				<td><input type="text" name="position" value="${param.position}" /></td>
				<td>${searchPhotoErrors.position}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td>${searchPhotoErrors.search}</td>
			</tr>
		</table>
	</form>
</body>
</html>