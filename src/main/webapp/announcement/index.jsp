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
<script src="<c:url value = '/js/social.list.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<body>
	<div id="header">
		<div class="section">
			<c:url value="/photo/album/list.do" var="album">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<ul>
				<li><c:if test="${not empty user}">
						<c:if test='${sessionScope.user.mValidManager == "Y"}'>
							<a href="manager/">後臺</a>
						</c:if>
					</c:if></li>
				<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
				<li><a href="<c:url value ='/user/' />">會員</a></li>
				<li><a href="${album}">相簿</a></li>
				<li><a href="<c:url value ='/activity/' />">活動</a></li>
				<li><a href="<c:url value ='/store/' />">商店</a></li>
				<li><a href="<c:url value ='/report/' />">回報</a></li>
				<li><a href="<c:url value ='/announcement/' />">公告</a></li>
				<li><c:if test="${empty user}">
						<a href="check/register.v">註冊</a>
						<c:out escapeXml="false" value="<a href='check/login.r'>(登入)</a>" />
					</c:if> <c:if test="${not empty user}">
						<c:out escapeXml="false" value="<a href='check/logout.do'>${user.mName}</a>" />
					</c:if></li>
			</ul>
		</div>
	</div>
	<div id="center">
		<div id="body">
		<fieldset>
			<div>
				<label>查詢公告</label>
			</div>
			<div>
				<label>公告類別</label>
			</div>

			<div>
				<input type="radio" name="a_type" />系統 <input type="radio" name="a_type" />新聞 <input type="radio" name="a_type" />活動
				<input type="radio" name="a_type" />商城
				<c:out value=""></c:out>
			</div>
			<div>
				<label>公告時間</label> <input type="text" name="startTime" value=""> <span>${errors.ooo}</span> <input
					type="submit" name="announcement" value="開始查詢">
			</div>
			<br>
			<div>
				<label>列出活動中的公告</label> <input type="submit" name="announcement" value="Select">

			</div>

			<br>
			<div>
				<label>列出已結束公告活動</label> <input type="submit" name="endTime" value="Select">
			</div>
			<br>
			<div>
				<label>新增修改公告</label>

			</div>
			</fieldset>
		</div>
	</div>
	
	<div id="left">
		<div class="actions">
		<ul>
				<li class="list"><a href='<c:url value="insert.v"/>'>新增公告</a></li>
				<li class="list"><a href='<c:url value="list.v"/>'>查詢公告</a></li>
				<li class="list"><a href='<c:url value="update.v"/>'>修改公告</a></li>
			</ul>
		</div>
	</div>
	<div id="right">
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
							<c:url  var="member">
								<c:param name="q" value="${row.profileURL}"></c:param>
							</c:url>
							<c:url var="chatroom">
								<c:param name="q" value="${row.chatroomURL}"></c:param>
							</c:url>
							<tr>
								<td><a href="${member}">${row.mName}</a></td>
								<td><a href="${chatroom}">聊天</a></td>
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
						<c:url var="insert">
							<c:param name="q" value="${row.profileURL}"></c:param>
						</c:url>
						<c:url  var="refuseDelete">
							<c:param name="q" value="${row.profileURL}"></c:param>
						</c:url>
						<tr>
							<td>${row.mName}</td>
							<td><input onclick="getInviteId('${insert}')" type="button" value="加好友"></td>
							<td><input onclick="refuse('${refuseDelete}')" type="button" value="取消"></td>
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
	<div id="footer">this is footer</div>



</body>
</html>



