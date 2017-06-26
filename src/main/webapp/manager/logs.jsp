<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logs, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<style type="text/css">
table {
	border-collapse: collapse;
}

th {
	border: 1px solid black;
	padding: 5px 0px 5px 5px;
}

td {
	border: 1px solid black;
	padding: 5px 0px 5px 5px;
}

.t1 {
	min-width: 80px;
	text-align: center;
}

.t2 {
	min-width: 170px;
	text-align: center;
}
</style>
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<p>補充：網址結尾改成/logs.do可以列出記錄，改成/delogs.do可以刪除所有記錄。</p>
	<table>
		<thead>
			<tr>
				<th class="t1">紀錄編號</th>
				<th class="t2">紀錄時間</th>
				<th>紀錄內容</th>
			</tr>
		</thead>
		<c:forEach var="lbean" items="${logs}">
			<tr>
				<td class="t1">${lbean.lId}</td>
				<td>${lbean.lCreateTime}</td>
				<td>${lbean.lLog}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>