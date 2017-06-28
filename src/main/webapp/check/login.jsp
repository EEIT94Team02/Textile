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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
	padding: 12px;
	color:black;
}
</style>
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>

	<div class="formDiv">
		<fieldset>
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
						<td><a href="findPassword.v">忘記密碼？</a>${x}</td>
					</tr>
					<tr>
						<c:set var="x" value="&nbsp;&nbsp;&nbsp;" />
						<td colspan="3" align="center"><input type="checkbox" name="keepLogin" value="1" />保持登入</td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input id="btn" class="btn" type="submit" value="登入" /></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	<script type="text/javascript">
		$(".formDiv").on('submit', function() {
			var tdJOb = $('#btn').parent();
			$('#btn').remove();
			var iJOb = $('<i></i>').attr('class', 'fa fa-spinner fa-spin');
			var spanJOb = $('<span></span>').text('Loading');
			iJOb.append(spanJOb);
			tdJOb.append(iJOb);
		});
	</script>
</body>
</html>