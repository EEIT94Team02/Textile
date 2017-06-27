<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel=stylesheet type="text/css" href="<c:url value = '/css/jacky.css'/>">
<style type="text/css">
body {
	background-image:
		url("../image/background/Alien_Ink_2560X1600_Abstract_Background_1.jpg");
}

.formDiv {
	margin: auto;
	margin-top: 15%;
	margin-left: 40%;
	margin-right: 38%;
	font-family: Microsoft JhengHei;
	width: 350px;
	height:250px;
	border: 2px ;
	background-color:#34c2bb;
	text-align:center;
	line-height:50px;
}
</style>
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<div class="formDiv">
		<form action="login.do" method="post">
			<table>
				<tr>
					<td>帳號：</td>
					<td><input type="text" name="mEmail" value="${dataAndErrorsMap.mEmail}" /></td>
					<td>${dataAndErrorsMap.login_error}</td>
				</tr>
				<tr>
					<td>密碼：</td>
					<td><input type="password" name="mPassword" value="${dataAndErrorsMap.mPassword}" /></td>
					<td></td>
				</tr>
				<tr>
					<c:set var="x" value="&nbsp;&nbsp;&nbsp;" />
					<td colspan="2" align="right"><a href="findPassword.v">忘記密碼？</a>${x}<input type="checkbox" name="keepLogin"
						value="1" />保持登入</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input class="btn" type="submit" value="登入" /></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>