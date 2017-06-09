<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/photo/load.controller" enctype="multipart/form-data" method="post"> 
<div>
<div>
<label>照片名稱 :</label>
</div>
<div>			
		<input type="text" name="photoname" value="${param.photoname}">
		<span>${errors.xxx}</span>
</div>
<div>
		<label>類別:</label>
</div>
<div>
		<input type="radio" name="position"/>大頭貼
		<input type="radio" name="position">封面
		<input type="radio" name="position">一般
		<input type="radio" name="position">背景
		<span>${errors.ooo}</span>
</div>
<div>
<label>隱私設定 :</label>
</div>
<div>
		<input type="radio" name="visibility">公開
		<input type="radio" name="visibility">好友
		<input type="radio" name="visibility">隱藏
		<span>${errors.ooo}</span>
</div>
<div>
		<label>上傳檔案 :</label>
</div>
<div>
		<input type="file" name="file">
</div>
<div>
		<label>照片敘述 :</label>
</div>
<div>
		<textarea></textarea>
		<input type="submit" value="upload" >
</div>
</div>
</form>
</body>
</html>