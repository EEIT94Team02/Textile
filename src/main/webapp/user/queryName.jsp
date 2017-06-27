<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Query user, Textile</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<style type="text/css">
.dataBasic td {
	width: 100px;
}

.dataSituation td:nth-child(1) {
	width: 100px;
}

.dataSituation td:nth-child(2) {
	width: 200px;
}
</style>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="<c:url value = '../css/jquery-ui-1.12.1.css'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '../js/jquery-ui-1.12.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '../js/json2.js'/>"></script>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>

	<div id="left">
		<div class="actions">
			<ul>
				<li class="list"><a href="modifySecure.v">修改密碼</a></li>
				<li class="list"><a href="modifyProfile.v">修改基本資料</a></li>
				<li class="list"><a href="modifySituation.v">修改個人狀況</a></li>
				<li class="list"><a href="modifyInterest.v">修改興趣喜好</a></li>
				<li class="list"><a href="queryName.v">會員姓名查詢</a></li>
				<li class="list"><a href="queryCondition.v">會員條件查詢</a></li>
				<li class="list"><a href="queryRandom.do">會員隨機查詢</a></li>
			</ul>
		</div>
	</div>

	<div id="center">
		<div id="body">
			<fieldset>
				<form id="queryName" class="ui-widget" action="queryName.do" method="post">
					<input type="hidden" name="m" value="queryName" />請輸入會員名稱：<input id="q" name="q" value="${dataAndErrorsMap.mName}"
						type="text" size="21" maxlength="20" placeholder="王小明" /><img src="" /><span>${dataAndErrorsMap.mName_error}</span><br />
					<p></p>
					<input id="submit" name="submit" value="搜尋" type="submit" /> <br />
					<p></p>
				</form>
			</fieldset>
		</div>
	</div>
	<script type="text/javascript">
		var imgsrc_loading16 = '../image/check/check_loading16.gif';
		var userList = [];

		// m=q,q=input
		function checkField(event) {
			var id = $(event.currentTarget).attr('id');
			$('#' + id + ' + img').attr('src', imgsrc_loading16);
			$('#' + id + ' + img + span').text('');
			$.get('queryName.do', {
				'm' : id,
				'q' : $('#' + id).val()
			}, function(output) {
				userList = JSON.parse(output);
				$("#q").autocomplete({
					source : userList
				});
			});
		}

		function checkFieldOnblur(event) {
			var id = $(event.currentTarget).attr('id');
			$('#' + id + ' + img').attr('src', '');
		}

		$('#q').on('keydown', checkField).on('blur', checkFieldOnblur);
	</script>
</body>
</html>