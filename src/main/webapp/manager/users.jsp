<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logs, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<style type="text/css">
a {
	color: blue;
	text-decoration: none;
}

a:hover {
	color: red;
}

a:visit {
	color: blue;
}

table {
	border-collapse: collapse;
}

th {
	border: 1px solid black;
	padding: 5px 0px 5px 5px;
	text-align: center;
}

td {
	border: 1px solid black;
	padding: 5px 0px 5px 5px;
	text-align: center;
}

.t1 {
	min-width: 300px;
}

.t2 {
	min-width: 120px;
}

.t3 {
	min-width: 120px;
}

.t4 {
	min-width: 120px;
}

.t5 {
	min-width: 120px;
}
</style>
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id=left>
		<div class="actions">
			<ul>
				<li class="list"><a href="<c:url value='logs.do'/>">系統記錄</a></li>
				<li class="list"><a href="<c:url value='users.do'/>">會員列表</a></li>
				<li class="list"><a href="<c:url value='tests.do'/>">在線測試</a></li>
			</ul>
		</div>
	</div>
	<div id="center">
		<table>
			<thead>
				<tr>
					<th class="t1">信箱</th>
					<th class="t2">姓名</th>
					<th class="t3">生日</th>
					<th class="t4">居住地</th>
					<th class="t5">主頁</th>
				</tr>
			</thead>
			<c:forEach var="mbean" items="${users}">
				<tr>
					<td class="t1">${mbean.mEmail}</td>
					<td>${mbean.mName}</td>
					<td>${fn:substring(mbean.mBirthday.toString(),0,10)}</td>
					<td>${mbean.mAddress_County}${mbean.mAddress_Region}</td>
					<td><a href="${mbean.mOtherProfileUrl}">連結</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>