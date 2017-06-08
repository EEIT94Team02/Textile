<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="upload.controller" enctype="multipart/form-data" method="post"> 
<table>

	<tr>			
		<td><label>照片名稱 :</label></td>
		<td><input type="text" name="photoname" value="${param.photoname}"></td>
		<td><span>${errors.xxx}</span></td>
	</tr>
	<tr>	
		<td><label>照片敘述 :</label></td>
		<td><input type="text" name="interpretation" value="${param.interpretation}"></td>
		<td>${errors.ooo}</td>
	</tr>
	<tr>
		<td><label>類別 :</label></td>
		<td><input type="radio" name="position"/>大頭貼
		<input type="radio" name="position">封面
		<input type="radio" name="position">一般
		<input type="radio" name="position">背景</td>
		<td><span>${errors.ooo}</span></td>
	</tr>
	<tr>
		<td><label>隱私設定 :</label></td>
		<td><input type="radio" name="visibility">公開
		<input type="radio" name="visibility">好友
		<input type="radio" name="visibility">隱藏</td>
		<td>${errors.ooo}</td>
	</tr>
	<tr>
		<td><label>上傳檔案 :</label></td>
		<td><input type="file" name="file"></td>
		<td><input type="submit" value="upload" align="top"></td>
	</tr> 
</table>  
</form>
</body>
</html>