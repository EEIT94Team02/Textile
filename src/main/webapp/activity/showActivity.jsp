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
	<c:if test="${not empty ActivityList}">
		<table>
			<thead>
				<tr>
					<th>活動編號</th>
					<th>開始時間</th>
					<th>結束時間</th>
					<th>活動名稱</th>
					<th>活動地點</th>
					<th>活動說明</th>
					<th>公開</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${ActivityList}">
					<tr>
						<td>${row.activityno}</td>
						<td>${row.begintime}</td>
						<td>${row.endtime}</td>
						<td>${row.activityname}</td>
						<td>${row.place}</td>
						<td>${row.interpretation}</td>
						<td>${row.visibility}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty ActivityList}">
		${selectActivityErrors.selecterror}
		${ActivityCRDErrors.insert}
		${ActivityCRDErrors.update}
		${ActivityCRDErrors.delete}
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