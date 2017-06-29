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
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<c:url value = '/css/jquery-ui-timepicker-addon.css'/>">
<script type="text/javascript" src="<c:url value = '/js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-1.12.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/event.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/sweetalert.min.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery-ui-timepicker-addon.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$('#begin').datetimepicker({
			dateFormat : "yy-mm-dd",
			timeFormat : "HH:mm",
			minDate : "+0d",

		});
		$('#end').datetimepicker({
			dateFormat : "yy-mm-dd",
			timeFormat : "HH:mm",
			minDate : "+0d",
		});
	});
</script>
<style type="text/css">
.ui-datetimepicker {
	background: #FFFFFF;
	border: 1px solid #AAAAAA;
	color: #0000AA;
}
</style>
<title>Welcome, Textile.</title>
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
			<c:if test="${not empty user}">
				<div align="center">
					<h3>
						<c:out value="發起人:${user.mName}" />
					</h3>
					<form action='<c:url value="/activity/create.do"/>' method="post">
						<table style="font-size: 16px; text-align: left; font-weight: bold;">
							<tr style="margin: 5px; padding: 5px; height: 2em;">
								<td>活動名稱：</td>
								<td><input type="text" name="activityname" value="${param.activityname}" height="18px" /></td>
								<td style="color: red">${activityCRDErrors.activityname}</td>
							</tr>
							<tr style="margin: 5px; padding: 5px; height: 2em;">
								<td>開始時間：</td>
								<td><input type="text" id="begin" name="begintime" height="18px" class="ui-datetimepicker"
									placeholder="請點擊選擇開始時間"></td>
								<td style="color: red">${activityCRDErrors.begintime}</td>
							</tr>
							<tr style="margin: 5px; padding: 5px; height: 2em;">
								<td>結束時間：</td>
								<td><input type="text" id="end" name="endtime" height="18px" class="ui-datetimepicker" onblur="checkdate()"
									placeholder="請點擊選擇結束時間"></td>
								<td style="color: red">${activityCRDErrors.endtime}</td>
							</tr>
							<tr style="margin: 5px; padding: 5px; height: 2em;">
								<td>活動地點：</td>
								<td><input type="text" name="place" height="18px" value="${param.place}" /></td>
								<td style="color: red">${activityCRDErrors.place}</td>
							</tr>
							<tr style="margin: 5px; padding: 5px;">
								<td style="float: left">活動內容：</td>
								<td><textarea name="interpretation" cols="30" rows="8">${param.interpretation}</textarea></td>
								<td style="color: red">${activityCRDErrors.interpretation}</td>
							</tr>
							<tr style="margin: 5px; padding: 5px; height: 3em;">
								<td></td>
								<td><input type="submit" class="btn" value="建立"></td>
								<td style="color: red">${activityCRDErrors.create}</td>
							</tr>
						</table>
					</form>
				</div>
			</c:if>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>

	<script>
		function checkdate() {
			var enddate = $('#end').value();
			var begindate = $('#begin').value();
			$.get('/checkdate.do', {
				"begintime" : begindate,
				"endtime" : enddate
			}, function(data) {

			});
		}
	</script>
</body>
</html>