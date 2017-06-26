<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat, Textile</title>
<style type="text/css">
#input {
	width: 300px;
}
</style>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<h3>${chat.acquaintenceName}</h3>
	<hr />
	<div id="response"></div>
	<div>
		<p>
			<textarea id="input" rows="5" placeholder="請在此輸入訊息..."></textarea>
		</p>
		<input type="button" id="button" name="button" value="送出" />
	</div>
	${chat.cId}
</body>
</html>