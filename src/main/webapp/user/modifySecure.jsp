<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Modify security, Textile</title>
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
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
<script src="<c:url value = '/js/social.list.js'/>"></script>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id="right">
		<jsp:include page="/rightInclude.jsp" />
	</div>
	<div id="center">
		<div id="middle">
			<form id="security" action="modify.do" method="post">
				<input type="hidden" name="m" value="security" />
				<fieldset>
					<legend>
						安全性<em>(*為必填項目)</em>
					</legend>
					<div>
						<p>信箱已驗證：(是)</p>
						<p>
							<c:choose>
								<c:when test="${user.mValidPhone == 'Y'}">
									<c:set var="x" value="(是)" />
								</c:when>
								<c:otherwise>
									<c:set var="x" value="(否) <a href='phoneCheck.v'>按此驗證手機</a>" />
								</c:otherwise>
							</c:choose>
							手機已驗證：${x}
						</p>
					</div>
					<div>
						<label for="mOldPassword">*舊密碼：</label> <input id="mOldPassword" name="mOldPassword"
							value="${dataAndErrorsMap.mOldPassword}" type="password" size="21" maxlength="16" placeholder="請輸入密碼" required />
						<img src="" /><span>${dataAndErrorsMap.mOldPassword_error}</span> <br />
						<p></p>
					</div>
					<div>
						<label for="mNewPassword">*新密碼：</label> <input id="mNewPassword" name="mNewPassword"
							value="${dataAndErrorsMap.mNewPassword}" type="password" size="21" maxlength="16" placeholder="請輸入密碼" required />
						<img src="" /><span>${dataAndErrorsMap.mNewPassword_error}</span> <br />
						<p>密碼長度介於8~16個字元，包含英文大寫、小寫、數字和規定的特殊符號各至少一個。</p>
					</div>
					<div>
						<label for="mNewPassword_Again">*請再次輸入新密碼：</label> <input id="mNewPassword_Again" name="mNewPassword_Again"
							value="${dataAndErrorsMap.mNewPassword_Again}" type="password" size="21" maxlength="16" placeholder="請輸入密碼"
							required /> <img src="" /><span>${dataAndErrorsMap.mNewPassword_Again_error}</span> <br />
						<p></p>
					</div>
					<div>
						<input id="submit" name="submit" value="修改" type="submit" /> <br />
						<p></p>
					</div>
				</fieldset>
			</form>
		</div>
	</div>

	<div id="left">
		<div class="actions">
			<ul style="font-weight: bold;">
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
	<div id="right">
		<jsp:include page="/rightInclude.jsp" />
	</div>

	<script type="text/javascript">
		var imgsrc_correct16 = '../image/check/check_correct16.png';
		var imgsrc_error16 = '../image/check/check_error16.png';
		var imgsrc_close16 = '../image/check/check_close16.png';
		var imgsrc_loading16 = '../image/check/check_loading16.gif';

		// 自動驗證的AJAX
		function checkField(event) {
			var id = $(event.currentTarget).attr('id');
			$('#' + id + ' + img').attr('src', imgsrc_loading16);
			$.get('modify.do', {
				'm' : id,
				'q' : $('#' + id).val()
			}, function(output) {
				$('#' + id).parent().find('span').text(output);
			});
		}

		function checkFieldOnblur(event) {
			var id = $(event.currentTarget).attr('id');
			$('#' + id + ' + img').attr('src', imgsrc_loading16);
			$.get('modify.do', {
				'm' : id,
				'q' : $('#' + id).val()
			}, function(output) {
				$('#' + id).parent().find('span').text(output);
			}).done(function() {
				changeImageFor(id);
			});
		}

		function changeImageFor(id) {
			if ($('#' + id).parent().find('span').text() == ''
					&& $('#' + id).val() == '') {
				$('#' + id + ' + img').attr('src', '');
			} else if ($('#' + id).parent().find('span').text() == ''
					&& $('#' + id).val() != '') {
				$('#' + id + ' + img').attr('src', imgsrc_correct16);
			} else {
				$('#' + id + ' + img').attr('src', imgsrc_error16);
			}
		}

		function checkPassword_Again(event) {
			var mNewPassword = 'mNewPassword';
			$('#' + mNewPassword + '_Again + img')
					.attr('src', imgsrc_loading16);
			$.get('modify.do', {
				'mNewPassword' : $('#' + mNewPassword).val(),
				'mNewPassword_Again' : $('#' + mNewPassword + '_Again').val()
			}, function(output) {
				$('#' + mNewPassword + '_Again').parent().find('span').text(
						output);
			});
		}

		function checkPassword_AgainOnblur(event) {
			var mNewPassword = 'mNewPassword';
			$('#' + mNewPassword + '_Again + img')
					.attr('src', imgsrc_loading16);
			$.get(
					'modify.do',
					{
						'mNewPassword' : $('#' + mNewPassword).val(),
						'mNewPassword_Again' : $('#' + mNewPassword + '_Again')
								.val()
					},
					function(output) {
						$('#' + mNewPassword + '_Again').parent().find('span')
								.text(output);
					}).done(function() {
				changeImageFor(mNewPassword + '_Again');
			});
		}

		// 自動驗證舊密碼
		$('#mOldPassword').on('keyup', checkField).on('blur', checkFieldOnblur);
		// 自動驗證新密碼
		$('#mNewPassword').on('keyup', checkField).on('blur', checkFieldOnblur);
		// 自動驗證再輸入一次新密碼
		$('#mNewPassword_Again').on('keyup', checkPassword_Again).on('blur',
				checkPassword_AgainOnblur);

		// 設定提交表單後，要將按鈕功能取消。
		function disableSubmit() {
			$(document).find('input[type="submit"]').prop('disabled', true);
		}

		$('#security').on('submit', disableSubmit);

		// 設定提交表單後如果還有錯誤要做圖示覆蓋
		$(document).ready(
				function() {
					var idCheckArray = [ 'mOldPassword', 'mNewPassword',
							'mNewPassword_Again' ];
					for (var i = 0; i < idCheckArray.length; i++) {
						var id = idCheckArray[i];
						changeImageFor(id);
					}
				});
	</script>
</body>
</html>