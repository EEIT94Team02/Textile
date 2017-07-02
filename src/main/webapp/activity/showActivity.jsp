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
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/jquery-ui-1.12.1.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sweetalert.css'/>">
<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/jquery-ui-timepicker-addon.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-1.12.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-timepicker-addon.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$('#begin').datetimepicker({
			dateFormat : "yy-mm-dd",
			timeFormat : "HH:mm"
		});
		$('#end').datetimepicker({
			dateFormat : "yy-mm-dd",
			timeFormat : "HH:mm"
		});
	});
</script>
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
				<ul style="font-weight: bold;">
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
			<c:if test="${not empty AllActivitise}">
				<form action='<c:url value="/activity/select.do"/>' method="post">
					<table border="1" style="text-align: center; width: 100%; margin: auto">
						<thead style="font-size: 16px">
							<tr>
								<th>活動編號</th>
								<th>開始時間</th>
								<th>結束時間</th>
								<th>活動名稱</th>
								<th>活動地點</th>
								<th>查看說明</th>
								<th>我要參加</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="row" items="${AllActivitise}">
								<c:url value="/activity/partner.do" var="findActMember">
									<c:param name="activityno" value="${row.activityno}"></c:param>
								</c:url>
								<c:url value="/activity/join.do" var="joinbean">
									<c:param name="activityno" value="${row.activityno}"></c:param>
									<c:param name="mId" value="${user.mId}"></c:param>
								</c:url>
								<tr>
									<td>${row.activityno}</td>
									<td><fmt:formatDate value="${row.begintime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td><fmt:formatDate value="${row.endtime}" pattern="yyyy/MM/dd HH:mm" /></td>
									<td>${row.activityname}</td>
									<td>${row.place}</td>
									<td><input onclick="findActMember('${findActMember}')" class="btn" type="button" value="查看明細"></td>
									<td><img onclick="joinMyAct('${joinbean}')" src='<c:url value ="/image/invite.png"/>' width="25"></td>
								</tr>
							</c:forEach>
							<tr>
								<td style="text-align: center; font-size: 16px; color: black; font-weight: bold">查詢:</td>
								<td><input style="text-align: center" size="10" type="text" id="begin" name="begintime" class="ui-datetimepicker" value="${param.begintime}" placeholder="請輸入條件"></td>
								<td><input style="text-align: center" size="10" type="text" id="end" name="endtime" class="ui-datetimepicker" value="${param.endtime}" placeholder="請輸入條件"></td>
								<td><input style="text-align: center" size="10" type="text" name="activityname" value="${param.activityname}" placeholder="搜尋名稱"></td>
								<td><input style="text-align: center" size="10" type="text" name="place" value="${param.place}" placeholder="搜尋地點"></td>
								<td><input style="text-align: center" size="10" type="text" name="interpretation" value="${param.interpretation}" placeholder="活動明細查詢"></td>
								<td><input style="text-align: center" type="submit" value="開始查詢"></td>
							</tr>
							<c:if test="${not empty activitySelectErrors}">
								<tr>
									<td></td>
									<td>${activitySelectErrors.begintime}</td>
									<td>${activitySelectErrors.endtime}</td>
									<td>${activitySelectErrors.activityname}</td>
									<td>${activitySelectErrors.place}</td>
									<td>${activitySelectErrors.interpretation}</td>
									<td></td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</form>
			</c:if>
			<c:if test="${empty AllActivitise}">
				<h3>目前無舉行中的活動</h3>
			</c:if>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
	<script>
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
		function findActMember(obj) {
			location.href = obj;
		};
	</script>
</body>
</html>