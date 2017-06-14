<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logs, Textile.</title>
</head>
<body>
	<p>補充：網址結尾改成/logs.do可以列出記錄，改成/delogs.do可以刪除所有記錄。</p>
	<table>
		<thead>
			<tr>
				<th>紀錄編號</th>
				<th>紀錄時間</th>
				<th>紀錄內容</th>
			</tr>
		</thead>
		<c:forEach var="lBean" items="${logs}">
			<tr>
				<td>${lBean.lId}</td>
				<td>${lBean.lCreateTime}</td>
				<td>${lBean.lLog}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>