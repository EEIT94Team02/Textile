<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<c:if test="${user.mValidManager!='Y'}"> --%>
		<p>
			<a href="createreport.v">回報頁面</a>
		</p>
		<p>
			<a href="reportlist.do">查詢回報紀錄</a>
		</p>
<%-- 	</c:if> --%>
	<c:if test="${user.mValidManager=='Y'}">
		<p>
			<a href="../report/situationlist.do">查詢所有未回覆的回報記錄</a>
		</p>
	</c:if>
</body>
</html>