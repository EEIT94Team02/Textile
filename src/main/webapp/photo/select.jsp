<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='<c:url value="album/default.do"/>' method="post">
		<table>
			<tr>
				<td>請輸入會員ID：</td>
				<td><input type="text" name="mId" value="${param.mId}" /></td>
				<td>${albumInsertErrors.albumname}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<hr>
	<form action='<c:url value="album/select.do"/>' method="post">
		<table>
			<tr>
				<td>相簿ID：</td>
				<td><input type="text" name="albumno" value="${param.albumno}" /></td>
				<td>${albumInsertErrors.albumname}</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="查詢"></td>
				<td></td>
			</tr>
		</table>
	</form>

	<hr>
	<form action='<c:url value="album/search.do"/>' method="post">
		<table>
			<tr>
				<td>建立日期：</td>
				<td><select name="year">
						<option value="2017" selected="selected">2017</option>
						<option value="2016">2016</option>
						<option value="2015">2015</option>
				</select> 年<select name="month">
						<option value="3" selected="selected">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select> 月<select name="day">
						<option value="10" selected="selected">10</option>
						<option value="20">20</option>
						<option value="25">25</option>
				</select>日</td>
				<td>${XXX.albumname}</td>
			</tr>
			<tr>
				<td>相簿名稱：</td>
				<td><input type="text" name="albumname" value="${param.albumname}" /></td>
				<td>${XXX.albumname}</td>
			</tr>
			<tr>
				<td>相簿介紹：</td>
				<td><input type="text" name="introduction" value="${param.albumno}" /></td>
				<td>${XXX.introduction}</td>
			</tr>
			<tr>
				<td>隱私設定：</td>
				<td><select name="visibility">
						<option value="公開" selected="selected">公開</option>
						<option value="好友">好友</option>
						<option value="私人">私人</option>
				</select></td>
			</tr>
			<tr>
				<td>會員ID：</td>
				<td><input type="text" name="mId" value="${param.mId}" /></td>
				<td>${XXX.mId}</td>
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