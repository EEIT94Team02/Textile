<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Textile</h1>

	<c:if test="${not empty insert}">
		<table>
			<thead>
				<tr>
					<th>photono</th>
					<th>img</th>
					<th>photoname</th>
					<th>interpretation</th>
					<th>albumno</th>
					<th>position</th>
					<th>visibility</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${insert}">
					<c:url value="/showphoto.v" var="photo">
						<c:param name="photono" value="${row.photono}"></c:param>
					</c:url>
					<tr>
						<td>${row.photono}</td>
						<td><img src="${photo}" width="200" /></td>
						<td>${row.photoname}</td>
						<td>${row.interpretation}</td>
						<td>${row.albumno}</td>
						<td>${row.position}</td>
						<td>${row.visibility}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<p>
		<a href="list.do">首頁</a>
	</p>

	<p>
		<a href='<c:url value="/photo/select.v"/>'>搜尋照片(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/upload.v"/>'>上傳照片(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/update.v"/>'>更新照片(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/delete.v"/>'>刪除照片(要登入)</a>
	</p>

</body>
</html>