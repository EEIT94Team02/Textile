<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Textile</h1>
	<c:if test="${not empty Albumresult}">
			${Albumresult}
		</c:if>
	<c:if test="${not empty AlbumList}">
		<c:out value="${user.mName} 的相簿" />
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
					<c:url value="/photo/list.do" var="album">
						<c:param name="albumno" value="${row.albumno}"></c:param>
						<c:param name="createtime" value="${row.createtime}"></c:param>
						<c:param name="albumname" value="${row.albumname}"></c:param>
						<c:param name="introduction" value="${row.introduction}"></c:param>
						<c:param name="visibility" value="${row.visibility}"></c:param>
						<c:param name="mId" value="${row.mId}"></c:param>
					</c:url>
					<tr>
						<td><a href="${album}">${row.albumno}</a></td>
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
	</c:if>

	<p>
		<a href='<c:url value="/photo/albumselect.v"/>'>查詢相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/albuminsert.v"/>'>創建相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/albumupdate.v"/>'>更新相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/albumdelete.v"/>'>刪除相簿(要登入)</a>
	</p>
	<p>
		<a href='<c:url value="/photo/upload.v"/>'>上傳照片(要登入)</a>
	</p>


</body>
</html>