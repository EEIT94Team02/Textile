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
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
</head>
<body>
	<c:url value="/photo/album/list.do" var="album">
		<c:param name="mId" value="${mysecuremId}"></c:param>
	</c:url>
	<c:url value="/activity/myAct.do" var="myAct">
	</c:url>
	<div id="header">
		<div class="section">
			<ul>
				<li><c:if test="${not empty user}">
						<c:if test='${sessionScope.user.mValidManager == "Y"}'>
							<a href="manager/">後臺</a>
						</c:if>
					</c:if></li>
				<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
				<li><a href="<c:url value ='/user/' />">會員</a></li>
				<li><a href="${album}">相簿</a></li>
				<li><a href="${myAct}">活動</a></li>
				<li><a href="<c:url value ='/store/' />">商店</a></li>
				<li><a href="<c:url value ='/report/' />">回報</a></li>
				<li><c:if test="${not empty user}">
						<c:out escapeXml="false" value="<a href='../check/logout.do'>${user.mName}</a>" />
					</c:if></li>
			</ul>
		</div>
	</div>
	<div id="center">
		<div id="left">
			<div class="actions">
				<ul>
					<li class="list"><a href="${album}">我的活動列表</a></li>
					<li class="list"><a href="${album}">募集中的活動</a></li>
					<li class="list"><a href="<c:url value='/photo/albuminsert.v'/>">舉辦一個活動</a></li>
					<li class="list"><a href="${selectalbum}">OOO</a></li>
					<li class="list"><a href="<c:url value='/photo/upload.v'/>">XXX</a></li>
				</ul>
			</div>
		</div>

		<!--預留給聊天室的區塊-->
		<div id="right">預留給聊天室的區塊</div>
		<div id="body">
			<c:if test="${not empty myActivityList}">
				<table>
					<thead>
						<tr>
							<th>活動編號</th>
							<th>開始時間</th>
							<th>結束時間</th>
							<th>活動名稱</th>
							<th>活動地點</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="row" items="${myActivityList}">
							<tr>
								<td>${row.activityno}</td>
								<td>${row.begintime}</td>
								<td>${row.endtime}</td>
								<td>${row.activityname}</td>
								<td>${row.place}</td>
							</tr>
						</c:forEach>

						<c:if test="${not empty Activity}">
							<tr>
								<td>${Activity.activityno}</td>
								<td>${Activity.begintime}</td>
								<td>${Activity.endtime}</td>
								<td>${Activity.activityname}</td>
								<td>${Activity.place}</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</c:if>
			<c:if test="${not empty Activity}">
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
						<tr>
							<td>${Activity.activityno}</td>
							<td>${Activity.begintime}</td>
							<td>${Activity.endtime}</td>
							<td>${Activity.activityname}</td>
							<td>${Activity.place}</td>
							<td>${Activity.interpretation}</td>

						</tr>
					</tbody>
				</table>
			</c:if>

			<c:if test="${not empty partner}">
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
					</tbody>
				</table>
			</c:if>
			<c:if test="${not empty partake}">
				<table>
					<thead>
						<tr>
							<th>活動編號</th>
							<th>活動成員</th>
							<th>參與狀態</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="row" items="${partake}">
							<tr>
								<td>${row.activity_memberPK.mId}</td>
								<td>${row.activityBean}</td>
								<td>${row.position}</td>
							</tr>
						</c:forEach>
					</tbody>
					</tbody>
				</table>
			</c:if>

			<c:if test="${empty ActivityList}">
			</c:if>
			<p><a href='<c:url value="/activity/select.v"/>'>查詢活動(要登入)</a></p>
			<p><a href='<c:url value="/activity/insert.v"/>'>創建活動(要登入)</a></p>
			<p><a href='<c:url value="/activity/update.v"/>'>更新活動(要登入)</a></p>
			<p><a href='<c:url value="/activity/delete.v"/>'>刪除活動(要登入)</a></p>
		</div>
	</div>
	<div id="footer">this is footer</div>
</body>
</html>