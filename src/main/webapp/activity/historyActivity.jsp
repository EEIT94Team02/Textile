<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<body>
	<c:url value="/photo/album/list.do" var="album">
		<c:param name="mId" value="${mysecuremId}"></c:param>
	</c:url>
	<c:url value="/activity/myAct.do" var="myAct">
	</c:url>
	<c:url value="/activity/allAct.do" var="allAct">
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
					<li class="list"><a href="${myAct}">我的活動</a></li>
					<li class="list"><a href="${allAct}">活動列表</a></li>
					<li class="list"><a href="<c:url value='/activity/createAct.v'/>">開團招募</a></li>
					<li class="list"><a href="<c:url value='/activity/historyActivity.v'/>">歷史活動</a></li>
				</ul>
			</div>
		</div>

		<!--預留給聊天室的區塊-->
		<div id="right">預留給聊天室的區塊</div>
		<div id="body">
			<c:if test="${not empty myActivityList.old}">
				<h3>歷史紀錄</h3>
				<table border="1" style="text-align: center; width: 100%">
					<thead>
						<tr>
							<th>活動</th>
							<th>開始時間</th>
							<th>結束時間</th>
							<th>活動名稱</th>
							<th>地點</th>
							<th>成員名單</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="old" items="${myActivityList.old}">
							<c:url value="/activity/partner.do" var="findActMember">
								<c:param name="activityno" value="${old.activityBean.activityno}"></c:param>
							</c:url>
							<tr>
								<td>${old.activityBean.activityno}</td>
								<td><fmt:formatDate value="${old.activityBean.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
								<td><fmt:formatDate value="${old.activityBean.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
								<td>${old.activityBean.activityname}</td>
								<td>${old.activityBean.place}</td>
								<td><input onclick="findActMember('${findActMember}')" type="button" value="查看明細"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
	<div id="footer">this is footer</div>
	<script>
		function findActMember(obj) {
			location.href = obj;
		};
	</script>
</body>
</html>