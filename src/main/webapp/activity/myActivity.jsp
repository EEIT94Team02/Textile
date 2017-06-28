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
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
<script src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<link type="text/css" rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id="center">
		<div id="left">
			<div class="actions">
			<c:url value="/photo/album/list.do" var="myalbum">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<c:url value="/photo/album/select.do" var="selectalbum">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<c:url value="/photo/album/friend.do" var="friendalbum">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<c:url value="/photo/album/list.do" var="album">
				<c:param name="mId" value="${mysecuremId}"></c:param>
			</c:url>
			<c:url value="/activity/myAct.do" var="myAct">
			</c:url>
			<c:url value="/activity/allAct.do" var="allAct">
			</c:url>
				<ul>
					<li class="list"><a href="${myalbum}">我的相簿列表</a></li>
					<li class="list"><a href="<c:url value='/photo/albuminsert.v'/>">創建相簿</a></li>
					<li class="list"><a href="${friendalbum}">好友相簿</a></li>
					<li class="list"><a href="${selectalbum}">瀏覽相簿</a></li>
					<li class="list"><a href="<c:url value='/photo/upload.v'/>">上傳照片</a></li>
					<li class="list"><a href="${myAct}">我的活動列表</a></li>
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
			<c:if test="${not empty myActivityList}">
				<c:if test="${not empty myActivityList.owner}">
					<h3>${user.mName}主辦的活動</h3>
					<table border="1" style="text-align: center; width: 100%">
						<thead>
							<tr>
								<th style="text-align: center; width: 5%">活動</th>
								<th style="text-align: center; width: 15%">開始時間</th>
								<th style="text-align: center; width: 15%">結束時間</th>
								<th style="text-align: center; width: 20%">活動名稱</th>
								<th style="text-align: center; width: 30%">地點</th>
								<th style="text-align: center; width: 5%">成員名單</th>
								<th style="text-align: center; width: 5%">邀請</th>
								<th style="text-align: center; width: 5%">修改</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="owner" items="${myActivityList.owner}">
								<c:url value="/activity/partner.do" var="findActMember">
									<c:param name="activityno" value="${owner.activity_memberPK.activityno}"></c:param>
								</c:url>
								<c:url value="/activity/delete.do" var="cancelBean">
									<c:param name="activityno" value="${owner.activity_memberPK.activityno}"></c:param>
								</c:url>
								<tr>
									<td>${owner.activityBean.activityno}</td>
									<td><fmt:formatDate value="${owner.activityBean.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td><fmt:formatDate value="${owner.activityBean.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td>${owner.activityBean.activityname}</td>
									<td>${owner.activityBean.place}</td>
									<td><input onclick="findActMember('${findActMember}')" type="button" class="btn" value="查看明細"></td>
									<td><img src='<c:url value ="/image/join.png"/>' width="25" onclick="findActMember('${findActMember}')"></td>
									<td><img src='<c:url value ="/image/cancel.png"/>' width="25" onclick="cancelMyAct('${cancelBean}')"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<br>
				<br>
				<c:if test="${not empty myActivityList.ready}">
					<h3>${user.mName}已參加的活動</h3>
					<table border="1" style="text-align: center; width: 100%">
						<thead>
							<tr>
								<th style="text-align: center; width: 5%">活動</th>
								<th style="text-align: center; width: 15%">開始時間</th>
								<th style="text-align: center; width: 15%">結束時間</th>
								<th style="text-align: center; width: 20%">活動名稱</th>
								<th style="text-align: center; width: 30%">地點</th>
								<th style="text-align: center; width: 5%">成員名單</th>
								<th style="text-align: center; width: 5%">邀請</th>
								<th style="text-align: center; width: 5%">退出</th>
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
									<td><input onclick="findActMember('${findActMember}')"class="btn" type="button" value="查看明細"></td>
									<td><img src='<c:url value ="/image/join.png"/>' width="25" onclick="findActMember('${findActMember}')"></td>
									<td><img src='<c:url value ="/image/cancel.png"/>' width="25" onclick="deleteMyAct('${secedebean}')"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<br>
				<br>
				<c:if test="${not empty myActivityList.notcommit}">
					<h3>${user.mName}受邀請的活動</h3>
					<table border="1" style="text-align: center; width: 100%">
						<thead>
							<tr>
								<th style="text-align: center; width: 5%">活動</th>
								<th style="text-align: center; width: 15%">開始時間</th>
								<th style="text-align: center; width: 15%">結束時間</th>
								<th style="text-align: center; width: 20%">活動名稱</th>
								<th style="text-align: center; width: 30%">地點</th>
								<th style="text-align: center; width: 5%">成員名單</th>
								<th style="text-align: center; width: 5%">參加</th>
								<th style="text-align: center; width: 5%">拒絕</th>
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
									<td><input onclick="findActMember('${findActMember}')" class="btn" type="button" value="查看明細"></td>
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
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>

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
		function cancelMyAct(obj) {
			swal({
				title : "你確定要取消嗎？",
				text : "是否要取消此活動?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "btn-danger",
				confirmButtonText : "確認取消",
				closeOnConfirm : false
			}, function() {
				swal("成功執行!", "你已取消此活動", "success");
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