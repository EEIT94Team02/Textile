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
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-1.12.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/jquery-ui-1.12.1.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
<script src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<script type="text/javascript"></script>
<body>
	<h1>Textile</h1>

	<fieldset>
		<c:out value="您的好友列表" />
		<div>
		<table >
			<c:forEach var="row" items="${LinksBean}">
				<c:url value="/user/index.v" var="member">
					<c:param name="q" value="${row.profileURL}"></c:param>
				</c:url>
				<c:url value="/user/chat.v" var="chatroom">
					<c:param name="q" value="${row.chatroomURL}"></c:param>
				</c:url><br/>
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
				<c:url value="/social/insert.do" var="insert">
					<c:param name="q" value="${row.ibean.mId}"></c:param>
				</c:url>
				<c:url value="/social/refuse.do" var="refuseDelete">
					<c:param name="q" value="${row.ibean.mId}"></c:param>
				</c:url>
					<tr>
						<td>${row.ibean.mName}</td>
						<td><input onclick="getInviteId('${insert}')" type="button" value="確認邀請"></td>
						<td><input onclick="refuse('${refuseDelete}')" type="button" value="拒絕邀請"></td>
						<td>
							<input type="hidden"  value="${row.mbean.mId}" />
						</td>
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


</body>

</html>