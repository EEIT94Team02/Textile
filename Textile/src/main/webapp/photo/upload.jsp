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

<form action='<c:url value="upload.do"/>' enctype="multipart/form-data" method="post"> 
<div>
<div>
<label>照片名稱 :</label>
</div>
<div>			
		<input type="text" name="photoname" value="${param.photoname}">
		<span>${errors.photoname}</span>
</div>
<div>
		<label>類別:</label>
</div>
<div>
		<input type="radio" name="position" value="a">大頭貼
		<input type="radio" name="position" value="b">封面
		<input type="radio" name="position" value="c">一般
		<input type="radio" name="position" value="d">背景
		<span></span>
</div>
<div>
<label>隱私設定 :</label>
</div>
<div>
		<input type="radio" name="visibility" value="A">公開
		<input type="radio" name="visibility" value="B">好友
		<input type="radio" name="visibility" value="C">隱藏
		<span></span>
</div>
<div>
		<label>上傳檔案 :</label>
</div>
<div>
		<input type="file" name="file"  multiple accept="image/*">
		<span>${errors.file}</span>
</div>
<div>
		<label>照片敘述 :</label>
		<textarea name="interpretation"></textarea>
</div>
<div>		
		<input type="submit" value="upload" >
</div>
</div>
</form>
</body>
</html>