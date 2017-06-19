<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tbody>
			<thead>
				<tr>
					<th>回報編號</th>
					<th>會員編號</th>
					<th>回報日期</th>
					<th>回報類別</th>
					<th>回報內容</th>
					<th>管理員回覆</th>
					<th>回覆狀況</th>
				</tr>
			</thead>
			<c:forEach var="sList" items="${situationList}">
			<c:url value="reportreply.do" var="link" scope="page">
				<c:param name="reptNo" value="${sList.reptNo}"></c:param>
				<c:param name="mId" value="${sList.mId}"></c:param>
				<c:param name="reptDate" value="${sList.reptDate}"></c:param>
				<c:param name="reptType" value="${sList.reptType}"></c:param>
				<c:param name="reptDetail" value="${sList.reptDetail}"></c:param>
				<c:param name="replyDetail" value="${sList.reptDetail}"></c:param>
				<c:param name="situation" value="${sList.situation}"></c:param>
			</c:url>
			<tr>
			<td>${sList.reptNo}</td>			
			<td>${sList.mId}</td>
			<td>${sList.reptDate}</td>
			<td>${sList.reptType}</td>
			<td>${sList.reptDetail}</td>
			<td><a href="${link}">回覆</a></td>
			<td>${sList.situation?'已回覆':'未回覆'}</td>
			<td>
			<c:forEach var="rImg" items="${reportimg}">
				<c:if test="${rImg.reptNo==sList.reptNo}">
				<img src="..${rImg.imgPath}">
				</c:if>
			</c:forEach>
			</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>

</body>
</html>