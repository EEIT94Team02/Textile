<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Textile</h1>
	<table>
		<thead>
			<tr>
				<th>活動編號</th>
				<th>開始時間</th>
				<th>結束時間</th>
				<th>活動名稱</th>
				<th>活動地點</th>
				<th>活動內容</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty ActivityList}">
				<c:forEach var="row" items="${ActivityList}">
					<tr>
						<td>${row.activityno}</td>
						<td>${row.begintime}</td>
						<td>${row.endtime}</td>
						<td>${row.activityname}</td>
						<td>${row.place}</td>
						<td>${row.interpretation}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${not empty Activity}">
				<tr>
					<td>${Activity.activityno}</td>
					<td>${Activity.begintime}</td>
					<td>${Activity.endtime}</td>
					<td>${Activity.activityname}</td>
					<td>${Activity.place}</td>
					<td>${Activity.interpretation}</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<table>
		<thead>
			<tr>
				<th>活動編號</th>
				<th>活動成員</th>
				<th>參與狀態</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${partner}">
				<tr>
					<td>${row.activity_memberPK.activityno}</td>
					<td>${row.memberBean}</td>
					<td>${row.position}</td>
				</tr>
			</c:forEach>
			<c:forEach var="row" items="${partake}">
				<tr>
					<td>${row.activity_memberPK.mId}</td>
					<td>${row.activityBean}</td>
					<td>${row.position}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty ActivityList}">
	</c:if>

	<p>
		<a href='<c:url value="/activity/select.v"/>'>查詢活動(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/activity/insert.v"/>'>創建活動(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/activity/update.v"/>'>更新活動(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/activity/delete.v"/>'>刪除活動(要登入)</a>
	</p>



</body>
</html>