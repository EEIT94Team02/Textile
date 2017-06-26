<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deal Test Trigger</title>
</head>
<body>
	<c:url var="showList" value="dealList.do" >
		<c:param name="memberId" value="${sessionScope.user.mId}"/>
	</c:url>
	<h3><a href="${showList}">deal</a></h3><hr/>
	<h3><a href="../">home</a></h3>
</body>
</html>