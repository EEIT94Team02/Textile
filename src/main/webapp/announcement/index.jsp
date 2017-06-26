<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/announcement.do"/>" method="post">
		<p>
			<a href='<c:url value="insert.v"/>'>新增公告</a>
		</p>
		<p>
			<a href='<c:url value="list.v"/>'>查詢公告</a>
		</p>
		<p>
			<a href='<c:url value="update.v"/>'>修改公告</a>
		</p>
		<div>
			<label>查詢公告</label>
		</div>
		<div>
			<label>公告類別</label>
		</div>

		<div>
			<input type="radio" name="a_type" />系統 <input type="radio" name="a_type" />新聞 <input type="radio" name="a_type" />活動
			<input type="radio" name="a_type" />商城
			<c:out value=""></c:out>
		</div>
		<div>
			<label>公告時間</label> <input type="text" name="startTime" value="">
			
			<span>${errors.ooo}</span> <input type="submit" name="announcement" value="開始查詢">

		</div>
		<br>
		<div>
			<label>列出活動中的公告</label> <input type="submit" name="announcement" value="Select">

		</div>

		<br>
		<div>
			<label>列出已結束公告活動</label> <input type="submit" name="endTime" value="Select">
		</div>
		<br>
		<div>
			<label>新增修改公告</label>

		</div>

		<table>

			<tr>
				<td>id :</td>
				<td><input type="text" name="a_id" value="${param.a_id}"></td>
				<td>${errors.id}</td>
			</tr>
			<tr>
				<td>startTime :</td>
				<td><input type="text" name="startTime" value="${param.startTime}"></td>
				<td>${errors.startTime}</td>
			</tr>

			<tr>
				<td>endTime :</td>
				<td><input type="text" name="endTime" value="${param.endTime}"></td>
				<td>${errors.endTime}</td>
			</tr>
			<tr>
				<td>gist :</td>
				<td><input type="text" name="gist" value="${param.gist}"></td>
				<td>${errors.gist}</td>
			</tr>
			<tr>
				<td>msg :</td>
				<td><input type="text" name="msg" value="${param.msg}"></td>
				<td>${errors.msg}</td>
			</tr>
			<tr>
				<td>type :</td>
				<td><input type="text" name="a_type" value="${param.a_type}"></td>
				<td>${errors.a_type}</td>
			</tr>
			<tr>
				<td>relTime :</td>
				<td><input type="text" name="relTime" value="${param.relTime}"></td>
				<td>${errors.relTime}</td>
			</tr>
			<tr>
				<td><input type="submit" name="announcement" value="Insert"> 
				<input type="submit" name="announcement" value="Update"></td>
			</tr>

		</table>
	</form>

</body>
</html>



