<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="list.do"/>' method="post">
		<table>
			<tr>
				<td>請輸入會員ID：</td>
				<td><input type="text" name="albumno" value="${param.albumno}" /></td>
				<td>${QQQ.albumno}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>