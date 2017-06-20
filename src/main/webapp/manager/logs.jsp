<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logs, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
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