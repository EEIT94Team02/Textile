<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find password, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
<style type="text/css">
body {
	background-image: url("../image/background/10.jpg");
}

.formDiv {
	margin: auto auto;
	margin-top: 15%;
	margin-left: 40%;
	margin-right: 38%;
	font-family: Microsoft JhengHei;
	width: 350px;
	height: 250px;
	border: 2px;
	background-color: #FFBB66;
	text-align: center;
	line-height: 50px;
	font-weight: bolder;
	border-radius: 8px;
	border: 1px solid #eeb44f;
	border-color: #000000;
	padding: 5px;
	border: 1px solid #eeb44f;
}

fieldset {
	padding: 10px;
	color:black;
}
</style>
</head>
<body>

<div class="formDiv">
<fieldset>
	<form action="findPassword.do" method="post">
		<table>
			<tr>
				<td>帳號輸入：</td>
				<td><input type="text" name="mEmail" value="${dataAndErrorsMap.mEmail}" /></td>
				<td>${dataAndErrorsMap.mEmail_error}</td>
			</tr>
			<c:if test="${not empty user}">
				<tr>
					<td>密碼提示：</td>
					<td >${user.mHintPassword}</td>
					<td></td>
				</tr>
				<tr>
					<td>密碼答案：</td>
					<td><input type="text" name="mHintAnswer" value="${dataAndErrorsMap.mHintAnswer}" /></td>
					<td>${dataAndErrorsMap.mHintAnswer_error}</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="提交" /></td>
				<td></td>
			</tr>
		</table>
	</form>
	</fieldset>
	</div>
</body>
</html>