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
			<c:if test="${not empty myActivityList}">
				<c:if test="${not empty myActivityList.owner}">
					<h3>${user.mName}主辦的活動</h3>
					<table border="1" style="text-align: center; width: 100%">
						<thead>
							<tr>
								<th>活動</th>
								<th>開始時間</th>
								<th>結束時間</th>
								<th>活動名稱</th>
								<th>地點</th>
								<th>成員名單</th>
								<th>邀請</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="owner" items="${myActivityList.owner}">
								<c:url value="/activity/partner.do" var="findActMember">
									<c:param name="activityno" value="${owner.activity_memberPK.activityno}"></c:param>
								</c:url>
								<tr>
									<td>${owner.activityBean.activityno}</td>
									<td><fmt:formatDate value="${owner.activityBean.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td><fmt:formatDate value="${owner.activityBean.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td>${owner.activityBean.activityname}</td>
									<td>${owner.activityBean.place}</td>
									<td><input onclick="findActMember('${findActMember}')" type="button" value="查看明細"></td>
									<td><img src='<c:url value ="/image/join.png"/>' width="25" onclick="findActMember('${findActMember}')"></td>
									<td><img src='<c:url value ="/image/cancel.png"/>' width="25" onclick="deleteMyAct('${secedebean}')"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${not empty myActivityList.ready}">
					<h3>已參加的活動</h3>
					<table border="1" style="text-align: center; width: 100%">
						<thead>
							<tr>
								<th>活動</th>
								<th>開始時間</th>
								<th>結束時間</th>
								<th>活動名稱</th>
								<th>地點</th>
								<th>成員名單</th>
								<th>邀請</th>
								<th>退出</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="row" items="${myActivityList.ready}">
								<c:url value="/activity/secede.do" var="secedebean">
									<c:param name="activityno" value="${row.activity_memberPK.activityno}"></c:param>
									<c:param name="mId" value="${row.activity_memberPK.mId}"></c:param>
								</c:url>
								<c:url value="/activity/partner.do" var="findActMember">
									<c:param name="activityno" value="${row.activity_memberPK.activityno}"></c:param>
								</c:url>
								<c:url value="/activity/invite.do" var="inviteMember">
									<c:param name="activityno" value="${row.activity_memberPK.activityno}"></c:param>
									<c:param name="friendList" value="${FriendList}"></c:param>
								</c:url>

								<tr>
									<td>${row.activityBean.activityno}</td>
									<td><fmt:formatDate value="${row.activityBean.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td><fmt:formatDate value="${row.activityBean.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td>${row.activityBean.activityname}</td>
									<td>${row.activityBean.place}</td>
									<td><input onclick="findActMember('${findActMember}')" type="button" value="查看明細"></td>
									<td><img src='<c:url value ="/image/join.png"/>' width="25" onclick="inviteFriend('${inviteMember}')"></td>
									<td><img src='<c:url value ="/image/cancel.png"/>' width="25" onclick="deleteMyAct('${secedebean}')"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${not empty myActivityList.notcommit}">
					<h3>受邀請的活動</h3>
					<table border="1" style="text-align: center; width: 100%">
						<thead>
							<tr>
								<th>活動</th>
								<th>開始時間</th>
								<th>結束時間</th>
								<th>活動名稱</th>
								<th>地點</th>
								<th>成員名單</th>
								<th>參加</th>
								<th>拒絕</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="notcommit" items="${myActivityList.notcommit}">
								<c:url value="/activity/secede.do" var="notcommitsecedebean">
									<c:param name="activityno" value="${notcommit.activity_memberPK.activityno}"></c:param>
									<c:param name="mId" value="${notcommit.activity_memberPK.mId}"></c:param>
								</c:url>
								<c:url value="/activity/join.do" var="joinbean">
									<c:param name="activityno" value="${notcommit.activity_memberPK.activityno}"></c:param>
									<c:param name="mId" value="${notcommit.activity_memberPK.mId}"></c:param>
								</c:url>
								<tr>
									<td>${notcommit.activityBean.activityno}</td>
									<td><fmt:formatDate value="${notcommit.activityBean.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td><fmt:formatDate value="${notcommit.activityBean.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td>${notcommit.activityBean.activityname}</td>
									<td>${notcommit.activityBean.place}</td>
									<td><input onclick="findActMember('${findActMember}')" type="button" value="查看明細"></td>
									<td><img src='<c:url value ="/image/invite.png"/>' width="25" onclick="joinMyAct('${joinbean}')"></td>
									<td><img src='<c:url value ="/image/cancel.png"/>' width="25" onclick="refuseMyAct('${notcommitsecedebean}')"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</c:if>
		</div>
	</div>
	<div id="footer">this is footer</div>

	<script>
		function deleteMyAct(obj) {
			swal({
				title : "你確定要刪除嗎？",
				text : "是否要退出此活動?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "btn-danger",
				confirmButtonText : "確認刪除",
				closeOnConfirm : false
			}, function() {
				swal("成功執行!", "你已退出此活動", "success");
				location.href = obj;
			});

		};
		function joinMyAct(obj) {
			swal({
				title : "你確定要參加嗎？？",
				text : "是否要參加此活動?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "",
				confirmButtonText : "確認參加",
				closeOnConfirm : false
			}, function() {
				swal("成功執行!", "你已加入此活動", "success");
				location.href = obj;
			});
		};
		function refuseMyAct(obj) {
			swal({
				title : "你確定拒絕邀請嗎？",
				text : "是否要回絕此活動邀請?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "btn-danger",
				confirmButtonText : "確認退出",
				closeOnConfirm : false,
			}, function() {
				swal("成功執行!", "你已退出此活動", "success");
				location.href = obj;
			});
		};
		function inviteFriend(obj) {
			swal({
				title : "你確定拒絕邀請嗎？",
				text : "是否要回絕此活動邀請?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "btn-danger",
				confirmButtonText : "確認退出",
				closeOnConfirm : false,
			}, function() {
				swal("成功執行!", "你已退出此活動", "success");
				location.href = obj;
			});
		};		


		function findActMember(obj) {
			location.href = obj;
		};
	</script>
</body>
</html>