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

	<c:if test="${not empty AlbumList}">
		<table>
			<thead>
				<tr>
					<th>Albumno</th>
					<th>Createtime</th>
					<th>Albumname</th>
					<th>introduction</th>
					<th>visibility</th>
					<th>mId</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${AlbumList}">
					<tr>
						<td>${row.albumno}</td>
						<td>${row.createtime}</td>
						<td>${row.albumname}</td>
						<td>${row.introduction}</td>
						<td>${row.visibility}</td>
						<td>${row.mId}</td>
					</tr>
				</c:forEach>						
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty AlbumList}">
		${selectAlbumErrors.selecterror}
		${albumCRDErrors.update}
	</c:if>

	<p>
		<a href='<c:url value="/photo/select.v"/>'>查詢相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/insert.v"/>'>創建相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/update.v"/>'>更新相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/delete.v"/>'>刪除相簿(要登入)</a>
	</p>


</body>
</html>