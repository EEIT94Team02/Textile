<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<h1>Textile</h1>
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
	<div id=center></div>

</body>
</html>