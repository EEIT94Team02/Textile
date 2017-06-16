<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/socail/socail.do"/>"  method="post">

	<h3>SocailList Table</h3>

<table>
	<tr>
		<td>UserID : </td>
		<td><input type="text" name="id" value="${param.id}"></td>
		<td><span class="error">${errors.id}</span></td>
	</tr>
	
	<tr>
		<td>
			<input type="submit" name="socailList" value="Insert">
			<input type="submit" name="socailList" value="Update">
		</td>
		<td>
			<input type="submit" name="socailList" value="Delete">
			<input type="submit" name="socailList" value="Select">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>
</form>

</body>
</html>
