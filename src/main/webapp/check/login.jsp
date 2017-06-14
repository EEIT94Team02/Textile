<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login, Textile</title>
</head>
<body>
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
				<td colspan="2" align="right"><input type="checkbox" name="keepLogin" value="1" />保持登入</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="登入" /></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>