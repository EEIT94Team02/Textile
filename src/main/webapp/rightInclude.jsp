<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<c:out value="您的好友列表" />
		<div>
			<table>
				<thead>
					<tr>
						<th>好友</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${friendList}">
						<tr>
							<td><a href="${row.profileURL}">${row.mName}</a></td>
							<td><a href="${row.chatroomURL}">聊天</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</fieldset>

	<fieldset>
		<c:out value="您的追蹤列表" />
		<table>
			<thead>
				<tr>
					<th>追蹤中</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${trackList}">
					<tr>
						<td>${row.mName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</fieldset>

	<fieldset>
		<c:out value="您的邀請名單" />
		<table>
			<thead>
				<tr>
					<th>邀請人</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${unconfirmedList}">
					<tr>
						<td>${row.mName}</td>
						<td><input onclick="getInviteId('${row.profileURL}')" type="button" value="加好友"></td>
						<td><input onclick="refuse('${row.profileURL}')" type="button" value="取消"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	<fieldset>
		<c:out value="您的黑名單列表" />
		<table>
			<thead>
				<tr>
					<th>黑單中</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${blackList}">
					<tr>
						<td>${row.mName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>


</body>
</html>