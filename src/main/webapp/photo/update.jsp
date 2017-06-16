<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Textile</h1>
	<c:out value="${user.mName} 的相簿" />
	<form action='<c:url value="/photo/album/update.do"/>' method="post">
		<table>
			<tr>
				<td>相簿編號：</td>
				<td><input type="text" name="albumno" value="${param.albumno}" /></td>
			</tr>
			<tr>
				<td>相簿名稱：</td>
				<td><input type="text" name="albumname" value="${param.albumname}" /></td>
				<td>${albumCRDErrors.albumname}</td>
			</tr>
			<tr>
				<td>相簿簡介：</td>
				<td><input type="text" name="introduction" value="${param.introduction}" /></td>
				<td>${albumCRDErrors.introduction}</td>
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
				<td></td>
				<td><input type="submit" value="更新"></td>
				<td>${albumCRDErrors.update}</td>
			</tr>
		</table>
	</form>



</body>
</html>