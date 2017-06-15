<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action='<c:url value="upload.do"/>' enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td><label>照片名稱 :</label></td>
				<td>
					<input type="text" name="photoname" value="${param.photoname}">
				</td> 
				<td>${errors.photoname}</td>
			</tr>
			<tr>
				<td><label>照片類別:</label></td>
				<td>
					<input type="radio" name="position" value="大頭貼">大頭貼
					<input type="radio" name="position" value="封面">封面
					<input type="radio" name="position" value="一般">一般
					<input type="radio" name="position" value="背景">背景
				</td>
				<td></td>
			</tr>
			<tr>
				<td><label>隱私設定 :</label></td>
				<td>
					<input type="radio" name="visibility" value="公開">公開
					<input type="radio" name="visibility" value="好友">好友
					<input type="radio" name="visibility" value="隱藏">隱藏
				</td>
				<td></td>
			</tr>
			<tr>
				<td><label>上傳檔案 :</label></td>
				<td><input type="file" name="file" multiple accept="image/*"></td>
				<td>${errors.file}</td>
			</tr>
			<tr>
				<td><label>照片敘述 :</label></td>
				<td><textarea name="interpretation"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="upload"></td>
			</tr>
		</table>
	</form>
</body>
</html>