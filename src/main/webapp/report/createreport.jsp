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
<center>
<form action='<c:url value="createNewReport.do" />' enctype="multipart/form-data" method="post"> 
  <select name="reptType">
    <option value="reportType1">reportType1</option>
    <option value="reportType2">reportType2</option>
    <option value="reportType3">reportType3</option>
  </select><span class="error">${errors.reportType}</span>
  <br>
    <textarea name="reptDetail" rows="10" cols="30">test</textarea><span class="error">${errors.message}</span>
  <br>
  <label class="fontSize" >上傳圖片：</label> 
      <Input Type="file" name="file" multiple accept="image/*"><BR>
      
  <input type="submit" value="upload" name="送出">
  
<!--   <input type="button" value="Clear" onclick="clearForm()"> -->
  <h3><span class="error">${errors.action}</span></h3>
</form>
</center> 
</body>
</html>