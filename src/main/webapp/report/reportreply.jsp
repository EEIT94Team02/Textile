<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome, Textile</title>
</head>
<body>
<center>
<h3>管理員回覆</h3>
<form action='<c:url value="replysuccess.do" />' method="post" >
<table border="1">
	<tbody>
		<thead>
		<tr>
			<th>回報編號</th>
			<th>會員編號</th>
			<th>回報日期</th>
			<th>回報種類</th>		
			<th>回報內容</th>
			<th>回報圖片</th>
		</tr>
	</thead>
		<tr>
			<th>${report.reptNo}<input type="hidden" name="reptNo" value="${report.reptNo}"></th>
			<th>${report.mId}<input type="hidden" name="mId" value="${report.mId}"></th>
			<th>${report.reptDate}<input type="hidden" name="reptDate" value="${report.reptDate}"></th>
			<th>${report.reptType}<input type="hidden" name="reptType" value="${report.reptType}"></th>		
			<th>${report.reptDetail}<input type="hidden" name="reptDetail" value="${report.reptDetail}"></th>
			<th>
				<c:forEach var="rImg" items="${reportImg}">
					<img src="..${rImg.imgPath}">
				</c:forEach>
			</th>
	   </tr>
	</tbody>
</table>
	<textarea name="replyDetail" rows="10" cols="50">test</textarea>
	<br>
	<input type="Submit" id="ButtonSubmit" value="Save" />
</form>
</center>
</body>
</html>