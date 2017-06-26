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

</body>
<h1>Textile SocialList insertcheck</h1>	
<form action='<c:url value="insert.do"/>' method="post">
			<table>
				
				<tr>
					<td>${param.acquaintenceId}(看的畫面)xxx邀請你做朋友</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="submit" value="確認"></td>
					<td><input type="submit" name="submit" value="拒絕"></td>
					<td>${SocialListInsertErrors.insert}</td>
				</tr>
			</table>
		</form>



</html>