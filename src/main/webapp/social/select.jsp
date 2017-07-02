<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Welcome, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
</head>
<script type="text/javascript"></script>
<body>
	<h1>Textile</h1>

	<fieldset>
		<c:out value="您的好友列表" />
		<div>
			<table>
				<c:forEach var="row" items="${LinksBean}">
					<c:url value="/user/index.v" var="member">
						<c:param name="q" value="${row.profileURL}"></c:param>
					</c:url>
					<c:url value="/user/chat.v" var="chatroom">
						<c:param name="q" value="${row.chatroomURL}"></c:param>
					</c:url>
					<br />
					<figure style="display: inline-block">
						<a href="${member}">${row.linkname}</a>
					</figure>
					<figure style="display: inline-block">
						<a href="${chatroom}">聊天</a>
					</figure>
				</c:forEach>
			</table>
			<table>
				<thead>
					<tr>
						<th>好友的名字</th>
						<th>群組</th>
						<th>結交時間</th>
						<th>類別</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${select}">

						<tr>
							<td>${row.mbean.mName}</td>
							<td>${row.s_group}</td>
							<td>${row.log_in}</td>
							<td>${row.s_type}</td>
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
				<c:forEach var="row" items="${Tselect}">

					<tr>
						<td>${row.mbean.mName}</td>
						<td>${row.s_group}</td>
						<td>${row.log_in}</td>
						<td>${row.s_type}</td>
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
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${unconfirmed}">
					<tr>
						<td><a href="${row.profileURL}">${row.mName}</a></td>
						<td><input onclick="doAccept()" type="button" value="確認"></td>
						<td><input onclick="doRefuse()" type="button" value="拒絕"></td>
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
						<td>${row.mbean.mName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	<p>
		<a href='<c:url value="/social/"/>'>返回</a>
	</p>
	<c:url value="/social/insert.do" var="accept" />
	<c:url value="/social/refuse.do" var="refuse" />
	<script type="text/javascript">
		function doAccept(event) {
			var jOb = $(event.currentTarget);
			$.get('${accept}', {
				'q' : '${row.encryptedMId}'
			}, function(data) {
				jOb.parents('tr').remove();
			});
		}

		function doRefuse(event) {
			var jOb = $(event.currentTarget);
			$.get('${refuse}', {
				'q' : '${row.encryptedMId}'
			}, function(data) {
				jOb.parents('tr').remove();
			});
		}
	</script>
</body>
</html>