<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link type="text/css" href="<c:url value='/css/jquery-1.2.1.css'/>">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript"></script>
<style type="text/css">
.friendList {
	position: absolute;
	top: 0px;
	left: 0px;
	bottom: 0px;
	min-height: 250px;
	background: #DDDDDD;
	width: 100%;
	overflow: auto;
	opacity: 0.8;
	font-weight: bold;
	border-style: solid;
	border-color: #888888;
}
</style>



</head>
<body>
	<div class="friendList">
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
							<td><a href="${row.profileURL}">${row.mName}</a></td>
							<td><input onclick="doAccept()" type="button" value="加朋友"></td>
							<td><input onclick="doRefuse()" type="button" id="test" value="拒絕"></td>
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
	</div>
	<c:url value="/social/insert.do" var="accept" />
	<c:url value="/social/delete.do" var="refuse" />
	<script type="text/javascript">
// 		$(function() {
// 			$('#test').on('click', function() {
// 				var url = $(this).parents('tr').find('a').attr('href');
// 				console.log(url);
// 			});
// 		});
		function doAccept(event) {
			var jOb = $(this);
			var url = $(this).parents('tr').find('td:eq(0)').find('a').attr('href');
			console.log(url);
			$.get('${accept}', {
				'q' : url
			}, function(data) {
				jOb.parents('tr').remove();
			});
		}

		function doRefuse(event) {
			var jOb = event.currentTarget;
			var url = jOb.parents('tr').find('a').attr('href');
			console.log(url);
// 			$.get('${refuse}', {
// 				'q' : url
// 			}, function(data) {
// 				jOb.parents('tr').remove();
// 			});
		}
	</script>
</body>
</html>