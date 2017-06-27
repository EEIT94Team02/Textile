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
			<c:if test="${not empty user}">
				<div>
					<p style="color: black; font-size: 20px"><c:out value="${user.mName} 準備發起一個活動:" /></p>
				</div>
				<form action='<c:url value="/activity/create.do"/>' method="post">
					<table style="font-size: 14px; text-align: left;">
						<tr>
							<td style="">活動名稱：</td>
							<td><input type="text" name="activityname" value="${param.activityname}" /></td>
							<td>${activityCRDErrors.activityname}</td>
						</tr>
						<tr>
							<td>開始時間：</td>
							<td><input type="text" id="begin" name="begintime" class="ui-datetimepicker" placeholder="請點擊選擇開始時間"></td>
							<td>${activityCRDErrors.begintime}</td>
						</tr>
						<tr>
							<td>結束時間：</td>
							<td><input type="text" id="end" name="endtime" class="ui-datetimepicker" placeholder="請點擊選擇結束時間"></td>
							<td>${activityCRDErrors.endtime}</td>
						</tr>
						<tr>
							<td>活動地點：</td>
							<td><input type="text" name="place" value="${param.place}" /></td>
							<td>${activityCRDErrors.place}</td>
						</tr>
						<tr>
							<td>活動內容：</td>
							<td><textarea name="interpretation" cols="40" rows="10">${param.interpretation}</textarea></td>
							<td>${activityCRDErrors.interpretation}</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="建立"></td>
							<td>${activityCRDErrors.create}</td>
						</tr>
					</table>
				</form>
			</c:if>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
</html>