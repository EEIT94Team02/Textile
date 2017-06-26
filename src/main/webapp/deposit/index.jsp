<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deposit Index</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
	<form id="depositForm">
		<button type="button" class="btn btn-info btn-lg" id="deposit">我要儲值</button>
		<button type="button" class="btn btn-info btn-lg" id="showRecord">儲值紀錄</button>
		<input type="hidden" name="memberId" value="${sessionScope.user.mId}" />
	</form>
</body>
<script>
$('button').on('click', function() {
	var action = $(this).attr('id')
	if (action == 'showRecord') {
		$('#depositForm').attr('action', 'dList.do').attr('method', 'POST').submit();
	} else {
		location.href='depositProceed.v';
	}
	
});
</script>
</html>