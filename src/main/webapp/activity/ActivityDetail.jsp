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
		<jsp:include page="/headerInclude.jsp" />
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
		<div id="right">
			<jsp:include page="/rightInclude.jsp" />
		</div>
		<div id="body">
			<c:if test="${not empty Activity}">
				<h2 style="margin-left: 10px">活動:</h2>
				<div style="font-size: 18px; width: 100%">
					<table>
						<tr style="padding: 15px; width: 80px">
							<td style="padding: 15px; width: 80px"><label style="font-weight: bold">活動名稱:</label></td>
							<td style="font-size: 16px">${Activity.activityname}</td>
						</tr>
						<tr style="padding: 15px; width: 80px">
							<td style="padding: 15px; width: 80px"><label style="font-weight: bold">開始時間:</label></td>
							<td style="font-size: 16px"><fmt:formatDate value="${Activity.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
						<tr style="padding: 15px; width: 80px">
							<td style="padding: 15px; width: 80px"><label style="font-weight: bold">結束時間:</label></td>
							<td style="font-size: 16px"><fmt:formatDate value="${Activity.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
						<tr style="padding: 15px; width: 80px">
							<td style="padding: 15px; width: 80px"><label style="font-weight: bold">活動地點:</label></td>
							<td style="font-size: 16px">${Activity.place}</td>
						</tr>
						<tr style="padding: 15px; width: 80px">
							<td style="padding: 15px; width: 80px"><label style="font-weight: bold">活動敘述:</label></td>
							<td style="font-size: 16px"></td>
						</tr>
						<tr style="padding: 15px; width: 80px">
						</tr>
						<tr style="padding: 15px; width: 80px">
							<td colspan="2" style="padding: 15px">${Activity.interpretation}</td>
						</tr>
					</table>
				</div>
			</c:if>
			<br> <br> <br> <br>
			<c:if test="${not empty partner}">
				<h2 style="margin-left: 10px">活動成員:</h2>
				<div style="font-size: 18px">
					<table>
						<thead style="font-size: 16px">
							<tr>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody style="font-size: 16px; font-family: inherit; text-align: center; margin-right: 10px">
							<c:forEach var="row" items="${partner}">
								<tr>
									<td style="width: 120px; padding: 5px">${row.memberBean.mName}</td>
									<td style="width: 120px; padding: 5px">${row.position}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br>
				<br>
				<form action='<c:url value="/activity/invite.do"/>' method="post">
					<input type="text" hidden="hidden" name="activityno" value="${Activity.activityno}" />
					<label style="padding: 15px; font-weight: bold; font-size: 16px">邀請好友一起加入:</label>
					<select name="mId">
						<c:forEach var="Friend" items="${FriendList}">
							<option value="${Friend.mbean.mId}">${Friend.mbean.mName}</option>
						</c:forEach>
					</select>
					<input type="submit" value="邀請" />
				</form>
			</c:if>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
</html>