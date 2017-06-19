<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>管理員回覆成功</h3>
<table border="1">
	<thead>
	<tr>
		<th>回報編號</th>
		<th>會員編號</th>
		<th>回報日期</th>
		<th>回報種類</th>		
		<th>回報內容</th>
		<th>回覆內容</th>
		<th>回報圖片</th>
	</tr>
	</thead>
	<tbody>
		<tr>
			<th>${report.reptNo}</th>
			<th>${report.mId}</th>
			<th>${report.reptDate}</th>
			<th>${report.reptType}</th>		
			<th>${report.reptDetail}</th>
			<th>${report.replyDetail}</th>
			<th>
			<c:forEach var="rImg" items="${reportImg}">
				<img src="..${rImg.imgPath}">
			</c:forEach>
			</th>
		</tr>
	</tbody>
</table>
</center>
</body>
</html>