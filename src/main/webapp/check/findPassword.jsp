<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find password, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
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
</body>
</html>