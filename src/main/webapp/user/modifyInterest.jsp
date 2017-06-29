<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Modify interest, Textile</title>
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
<style>
div.otherInterestDiv div {
	background: yellow;
}
</style>
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>


	<div id="center">
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
		<div id="right">
			<jsp:include page="/rightInclude.jsp" />
		</div>
		<div id="middle">
			<div class="background">
				<form id="interest" action="modify.do" method="post">
					<input type="hidden" name="m" value="interest" />
					<fieldset>
						<legend>興趣喜好</legend>
						<div>
							<table>
								<tbody>
									<tr>
										<td>戶外休閒：</td>
										<td><input type="checkbox" name="i1" value="${user.i_d.items.i1.selected}"
											${user.i_d.items.i1.selected == 1 ? 'checked' : ''} />${user.i_d.items.i1.value}<input type="checkbox"
											name="i2" value="${user.i_d.items.i2.selected}" ${user.i_d.items.i2.selected == 1 ? 'checked' : ''} />${user.i_d.items.i2.value}<input
											type="checkbox" name="i3" value="${user.i_d.items.i3.selected}"
											${user.i_d.items.i3.selected == 1 ? 'checked' : ''} />${user.i_d.items.i3.value}<input type="checkbox"
											name="i4" value="${user.i_d.items.i4.selected}" ${user.i_d.items.i4.selected == 1 ? 'checked' : ''} />${user.i_d.items.i4.value}<br />
											<input type="checkbox" name="i5" value="${user.i_d.items.i5.selected}"
											${user.i_d.items.i5.selected == 1 ? 'checked' : ''} />${user.i_d.items.i5.value}<input type="checkbox"
											name="i6" value="${user.i_d.items.i6.selected}" ${user.i_d.items.i6.selected == 1 ? 'checked' : ''} />${user.i_d.items.i6.value}<input
											type="checkbox" name="i7" value="${user.i_d.items.i7.selected}"
											${user.i_d.items.i7.selected == 1 ? 'checked' : ''} />${user.i_d.items.i7.value}<input type="checkbox"
											name="i8" value="${user.i_d.items.i8.selected}" ${user.i_d.items.i8.selected == 1 ? 'checked' : ''} />${user.i_d.items.i8.value}</td>
										<td></td>
										<td><input id="mOtherRecreation" name="mOtherRecreation" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherRecreation_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>運動健身：</td>
										<td><input type="checkbox" name="i9" value="${user.i_d.items.i9.selected}"
											${user.i_d.items.i9.selected == 1 ? 'checked' : ''} />${user.i_d.items.i9.value}<input type="checkbox"
											name="i10" value="${user.i_d.items.i10.selected}" ${user.i_d.items.i10.selected == 1 ? 'checked' : ''} />${user.i_d.items.i10.value}<input
											type="checkbox" name="i11" value="${user.i_d.items.i11.selected}"
											${user.i_d.items.i11.selected == 1 ? 'checked' : ''} />${user.i_d.items.i11.value}<input type="checkbox"
											name="i12" value="${user.i_d.items.i12.selected}" ${user.i_d.items.i12.selected == 1 ? 'checked' : ''} />${user.i_d.items.i12.value}<br />
											<input type="checkbox" name="i13" value="${user.i_d.items.i13.selected}"
											${user.i_d.items.i13.selected == 1 ? 'checked' : ''} />${user.i_d.items.i13.value}<input type="checkbox"
											name="i14" value="${user.i_d.items.i14.selected}" ${user.i_d.items.i14.selected == 1 ? 'checked' : ''} />${user.i_d.items.i14.value}<input
											type="checkbox" name="i15" value="${user.i_d.items.i15.selected}"
											${user.i_d.items.i15.selected == 1 ? 'checked' : ''} />${user.i_d.items.i15.value}<input type="checkbox"
											name="i16" value="${user.i_d.items.i16.selected}" ${user.i_d.items.i16.selected == 1 ? 'checked' : ''} />${user.i_d.items.i16.value}</td>
										<td></td>
										<td><input id="mOtherExercises" name="mOtherExercises" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherExercises_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>飲食料理：</td>
										<td><input type="checkbox" name="i17" value="${user.i_d.items.i17.selected}"
											${user.i_d.items.i17.selected == 1 ? 'checked' : ''} />${user.i_d.items.i17.value}<input type="checkbox"
											name="i18" value="${user.i_d.items.i18.selected}" ${user.i_d.items.i18.selected == 1 ? 'checked' : ''} />${user.i_d.items.i18.value}<input
											type="checkbox" name="i19" value="${user.i_d.items.i19.selected}"
											${user.i_d.items.i19.selected == 1 ? 'checked' : ''} />${user.i_d.items.i19.value}<input type="checkbox"
											name="i20" value="${user.i_d.items.i20.selected}" ${user.i_d.items.i20.selected == 1 ? 'checked' : ''} />${user.i_d.items.i20.value}<br />
											<input type="checkbox" name="i21" value="${user.i_d.items.i21.selected}"
											${user.i_d.items.i21.selected == 1 ? 'checked' : ''} />${user.i_d.items.i21.value}<input type="checkbox"
											name="i22" value="${user.i_d.items.i22.selected}" ${user.i_d.items.i22.selected == 1 ? 'checked' : ''} />${user.i_d.items.i22.value}<input
											type="checkbox" name="i23" value="${user.i_d.items.i23.selected}"
											${user.i_d.items.i23.selected == 1 ? 'checked' : ''} />${user.i_d.items.i23.value}<input type="checkbox"
											name="i24" value="${user.i_d.items.i24.selected}" ${user.i_d.items.i24.selected == 1 ? 'checked' : ''} />${user.i_d.items.i24.value}</td>
										<td></td>
										<td><input id="mOtherDiet" name="mOtherDiet" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherDiet_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>文學藝術：</td>
										<td><input type="checkbox" name="i25" value="${user.i_d.items.i25.selected}"
											${user.i_d.items.i25.selected == 1 ? 'checked' : ''} />${user.i_d.items.i25.value}<input type="checkbox"
											name="i26" value="${user.i_d.items.i26.selected}" ${user.i_d.items.i26.selected == 1 ? 'checked' : ''} />${user.i_d.items.i26.value}<input
											type="checkbox" name="i27" value="${user.i_d.items.i27.selected}"
											${user.i_d.items.i27.selected == 1 ? 'checked' : ''} />${user.i_d.items.i27.value}<input type="checkbox"
											name="i28" value="${user.i_d.items.i28.selected}" ${user.i_d.items.i28.selected == 1 ? 'checked' : ''} />${user.i_d.items.i28.value}<br />
											<input type="checkbox" name="i29" value="${user.i_d.items.i29.selected}"
											${user.i_d.items.i29.selected == 1 ? 'checked' : ''} />${user.i_d.items.i29.value}<input type="checkbox"
											name="i30" value="${user.i_d.items.i30.selected}" ${user.i_d.items.i30.selected == 1 ? 'checked' : ''} />${user.i_d.items.i30.value}<input
											type="checkbox" name="i31" value="${user.i_d.items.i31.selected}"
											${user.i_d.items.i31.selected == 1 ? 'checked' : ''} />${user.i_d.items.i31.value}<input type="checkbox"
											name="i32" value="${user.i_d.items.i32.selected}" ${user.i_d.items.i32.selected == 1 ? 'checked' : ''} />${user.i_d.items.i32.value}</td>
										<td></td>
										<td><input id="mOtherArt" name="mOtherArt" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherArt_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>設計藝術：</td>
										<td><input type="checkbox" name="i33" value="${user.i_d.items.i33.selected}"
											${user.i_d.items.i33.selected == 1 ? 'checked' : ''} />${user.i_d.items.i33.value}<input type="checkbox"
											name="i34" value="${user.i_d.items.i34.selected}" ${user.i_d.items.i34.selected == 1 ? 'checked' : ''} />${user.i_d.items.i34.value}<input
											type="checkbox" name="i35" value="${user.i_d.items.i35.selected}"
											${user.i_d.items.i35.selected == 1 ? 'checked' : ''} />${user.i_d.items.i35.value}<input type="checkbox"
											name="i36" value="${user.i_d.items.i36.selected}" ${user.i_d.items.i36.selected == 1 ? 'checked' : ''} />${user.i_d.items.i36.value}<br />
											<input type="checkbox" name="i37" value="${user.i_d.items.i37.selected}"
											${user.i_d.items.i37.selected == 1 ? 'checked' : ''} />${user.i_d.items.i37.value}<input type="checkbox"
											name="i38" value="${user.i_d.items.i38.selected}" ${user.i_d.items.i38.selected == 1 ? 'checked' : ''} />${user.i_d.items.i38.value}<input
											type="checkbox" name="i39" value="${user.i_d.items.i39.selected}"
											${user.i_d.items.i39.selected == 1 ? 'checked' : ''} />${user.i_d.items.i39.value}<input type="checkbox"
											name="i40" value="${user.i_d.items.i40.selected}" ${user.i_d.items.i40.selected == 1 ? 'checked' : ''} />${user.i_d.items.i40.value}</td>
										<td></td>
										<td><input id="mOtherDesign" name="mOtherDesign" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherDesign_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>音樂藝術：</td>
										<td><input type="checkbox" name="i41" value="${user.i_d.items.i41.selected}"
											${user.i_d.items.i41.selected == 1 ? 'checked' : ''} />${user.i_d.items.i41.value}<input type="checkbox"
											name="i42" value="${user.i_d.items.i42.selected}" ${user.i_d.items.i42.selected == 1 ? 'checked' : ''} />${user.i_d.items.i42.value}<input
											type="checkbox" name="i43" value="${user.i_d.items.i43.selected}"
											${user.i_d.items.i43.selected == 1 ? 'checked' : ''} />${user.i_d.items.i43.value}<input type="checkbox"
											name="i44" value="${user.i_d.items.i44.selected}" ${user.i_d.items.i44.selected == 1 ? 'checked' : ''} />${user.i_d.items.i44.value}<br />
											<input type="checkbox" name="i45" value="${user.i_d.items.i45.selected}"
											${user.i_d.items.i45.selected == 1 ? 'checked' : ''} />${user.i_d.items.i45.value}<input type="checkbox"
											name="i46" value="${user.i_d.items.i46.selected}" ${user.i_d.items.i46.selected == 1 ? 'checked' : ''} />${user.i_d.items.i46.value}<input
											type="checkbox" name="i47" value="${user.i_d.items.i47.selected}"
											${user.i_d.items.i47.selected == 1 ? 'checked' : ''} />${user.i_d.items.i47.value}<input type="checkbox"
											name="i48" value="${user.i_d.items.i48.selected}" ${user.i_d.items.i48.selected == 1 ? 'checked' : ''} />${user.i_d.items.i48.value}</td>
										<td></td>
										<td><input id="mOtherMusic" name="mOtherMusic" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherMusic_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>興趣嗜好：</td>
										<td><input type="checkbox" name="i49" value="${user.i_d.items.i49.selected}"
											${user.i_d.items.i49.selected == 1 ? 'checked' : ''} />${user.i_d.items.i49.value}<input type="checkbox"
											name="i50" value="${user.i_d.items.i50.selected}" ${user.i_d.items.i50.selected == 1 ? 'checked' : ''} />${user.i_d.items.i50.value}<input
											type="checkbox" name="i51" value="${user.i_d.items.i51.selected}"
											${user.i_d.items.i51.selected == 1 ? 'checked' : ''} />${user.i_d.items.i51.value}<input type="checkbox"
											name="i52" value="${user.i_d.items.i52.selected}" ${user.i_d.items.i52.selected == 1 ? 'checked' : ''} />${user.i_d.items.i52.value}<br />
											<input type="checkbox" name="i53" value="${user.i_d.items.i53.selected}"
											${user.i_d.items.i53.selected == 1 ? 'checked' : ''} />${user.i_d.items.i53.value}<input type="checkbox"
											name="i54" value="${user.i_d.items.i54.selected}" ${user.i_d.items.i54.selected == 1 ? 'checked' : ''} />${user.i_d.items.i54.value}<input
											type="checkbox" name="i55" value="${user.i_d.items.i55.selected}"
											${user.i_d.items.i55.selected == 1 ? 'checked' : ''} />${user.i_d.items.i55.value}<input type="checkbox"
											name="i56" value="${user.i_d.items.i56.selected}" ${user.i_d.items.i56.selected == 1 ? 'checked' : ''} />${user.i_d.items.i56.value}</td>
										<td></td>
										<td><input id="mOtherHobbies" name="mOtherHobbies" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherHobbies_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
									<tr>
										<td>其它活動：</td>
										<td><input type="checkbox" name="i57" value="${user.i_d.items.i57.selected}"
											${user.i_d.items.i57.selected == 1 ? 'checked' : ''} />${user.i_d.items.i57.value}<input type="checkbox"
											name="i58" value="${user.i_d.items.i58.selected}" ${user.i_d.items.i58.selected == 1 ? 'checked' : ''} />${user.i_d.items.i58.value}<input
											type="checkbox" name="i59" value="${user.i_d.items.i59.selected}"
											${user.i_d.items.i59.selected == 1 ? 'checked' : ''} />${user.i_d.items.i59.value}<input type="checkbox"
											name="i60" value="${user.i_d.items.i60.selected}" ${user.i_d.items.i60.selected == 1 ? 'checked' : ''} />${user.i_d.items.i60.value}<br />
											<input type="checkbox" name="i61" value="${user.i_d.items.i61.selected}"
											${user.i_d.items.i61.selected == 1 ? 'checked' : ''} />${user.i_d.items.i61.value}<input type="checkbox"
											name="i62" value="${user.i_d.items.i62.selected}" ${user.i_d.items.i62.selected == 1 ? 'checked' : ''} />${user.i_d.items.i62.value}<input
											type="checkbox" name="i63" value="${user.i_d.items.i63.selected}"
											${user.i_d.items.i63.selected == 1 ? 'checked' : ''} />${user.i_d.items.i63.value}<input type="checkbox"
											name="i64" value="${user.i_d.items.i64.selected}" ${user.i_d.items.i64.selected == 1 ? 'checked' : ''} />${user.i_d.items.i64.value}</td>
										<td></td>
										<td><input id="mOtherActivities" name="mOtherActivities" value="" type="text" size="21" maxlength="20"
											placeholder="這裡可以輸入其它興趣哦..." /> <img src="" /><span>${dataAndErrorsMap.mOtherActivities_error}</span> <br />
											<div class="otherInterestDiv"></div></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div>
							<input id="submit" name="submit" value="修改" type="submit" /> <br />
							<p></p>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var imgsrc_correct16 = '../image/check/check_correct16.png';
		var imgsrc_error16 = '../image/check/check_error16.png';
		var imgsrc_close16 = '../image/check/check_close16.png';
		var imgsrc_loading16 = '../image/check/check_loading16.gif';

		// 自動驗證的AJAX
		function checkInterest(event) {
			var id = $(event.currentTarget).attr('id');
			$('#' + id + ' + img').attr('src', imgsrc_loading16);
			$.get('modify.do', {
				'm' : id,
				'n' : 'interest',
				'q' : $('#' + id).val()
			}, function(output) {
				$('#' + id).next().next().text(output);
			});
		}

		function checkInterestOnblur(event) {
			var id = $(event.currentTarget).attr('id');
			$('#' + id + ' + img').attr('src', imgsrc_loading16);
			$.get('modify.do', {
				'm' : id,
				'n' : 'interest',
				'q' : $('#' + id).val()
			}, function(output) {
				$('#' + id).next().next().text(output);
			}).done(function() {
				changeInterestImageFor(id);
			});
		}

		function changeInterestImageFor(id) {
			if ($('#' + id).next().next().text() == ''
					&& $('#' + id).val() == '') {
				$('#' + id + ' + img').attr('src', '');
			} else if ($('#' + id).next().next().text() == ''
					&& $('#' + id).val() != '') {
				$('#' + id + ' + img').attr('src', imgsrc_correct16);
			} else {
				$('#' + id + ' + img').attr('src', imgsrc_error16);
			}
		}

		// 自動驗證各種興趣
		$('#mOtherRecreation').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherExercises').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherDiet').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherArt').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherDesign').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherMusic').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherHobbies').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);
		$('#mOtherActivities').on('keyup', checkInterest).on('blur',
				checkInterestOnblur);

		// 當興趣明細的checkbox被勾選或取消勾選時，要改變value的值。
		function changeValueOfInterest(event) {
			var checkboxJOb = $(event.target);
			if (checkboxJOb.prop('checked')) {
				checkboxJOb.val('1');
			} else {
				checkboxJOb.val('0');
			}
		}

		$('#interest input[type="checkbox"]').on('change',
				changeValueOfInterest);

		// 當其它興趣輸入完成時，要在下方新增一個區塊。
		function getBasicIndexOfHiddenInputName(inputName) {
			var i = 0;
			switch (inputName) {
			case 'mOtherRecreation':
				i = 5 * 0;
				break;
			case 'mOtherExercises':
				i = 5 * 1;
				break;
			case 'mOtherDiet':
				i = 5 * 2;
				break;
			case 'mOtherArt':
				i = 5 * 3;
				break;
			case 'mOtherDesign':
				i = 5 * 4;
				break;
			case 'mOtherMusic':
				i = 5 * 5;
				break;
			case 'mOtherHobbies':
				i = 5 * 6;
				break;
			case 'mOtherActivities':
				i = 5 * 7;
				break;
			}
			return i;
		}

		function getInputNameByIndex(i) {
			switch (parseInt(i / 5)) {
			case 0:
				return 'mOtherRecreation';
				break;
			case 1:
				return 'mOtherExercises';
				break;
			case 2:
				return 'mOtherDiet';
				break;
			case 3:
				return 'mOtherArt';
				break;
			case 4:
				return 'mOtherDesign';
				break;
			case 5:
				return 'mOtherMusic';
				break;
			case 6:
				return 'mOtherHobbies';
				break;
			case 7:
				return 'mOtherActivities';
				break;
			}
		}

		function removeDivAndRearrangeAllInput(event) {
			// 先找到父元素，再馬上移除該div，否則影響到div的剩餘數目。
			var inputJOb = $(event.target).parents('td').find(
					'input[type="text"]');
			$(event.target).parent('div').remove();
			var i = getBasicIndexOfHiddenInputName(inputJOb.attr('name'));
			var otherInterestDivJOb = inputJOb.parent('td').find(
					'div[class="otherInterestDiv"]');
			var innerDivs = otherInterestDivJOb.children();
			for (var j = 0; j < innerDivs.length; j++) {
				var hiddenInputJOb = $(innerDivs[j]).find(
						'input[type="hidden"]');
				hiddenInputJOb.attr('name', 'o' + (i + j));
			}
		}

		function checkInterestNameExist(innerDivs, newInterestName) {
			for (var i = 0; i < innerDivs.length; i++) {
				var interestName = $(innerDivs[i]).find('span:eq(1)').text();
				if (interestName == newInterestName) {
					return true;
				}
			}
			return false;
		}

		function createOtherInterestDiv(event) {
			var inputJOb = $(event.target);
			// 有輸入且沒錯誤才能加入興趣
			if (inputJOb.val() == '' || inputJOb.next().next().text() != '') {
				return;
			}
			var i = getBasicIndexOfHiddenInputName(inputJOb.attr('name'));
			var otherInterestDivJOb = inputJOb.parent('td').find(
					'div[class="otherInterestDiv"]');
			var innerDivs = otherInterestDivJOb.children();
			if (checkInterestNameExist(innerDivs, inputJOb.val())) {
				return;
			}
			if (innerDivs.length < 5) {
				var cell1 = $('_$tag_$tag_').attr('src', imgsrc_close16);
				$(cell1).on('click', removeDivAndRearrangeAllInput);
				var cell2 = $('_$tag__$tag__').text(' ');
				var cell3 = $('_$tag__$tag__').text(inputJOb.val());
				var cell4 = $(
						'<input type="hidden" name="o'
								+ (i + innerDivs.length + 1) + '" />').val(
						inputJOb.val());
				var newDivJOb = $('_$tag_$tag_').append(
						[ cell1, cell2, cell3, cell4 ]);
				otherInterestDivJOb.append(newDivJOb);
			} else {
				alert('其它興趣最多只能輸入五個。');
			}
		}

		$('#interest input[type="text"]').on('blur', createOtherInterestDiv);

		// 設定提交表單後，要將按鈕功能取消。
		function disableSubmit() {
			$(document).find('input[type="submit"]').prop('disabled', true);
		}

		$('#interest').on('submit', disableSubmit);

		// 設定其它興趣的初始化，方法為模擬手動新增興趣，每個興趣類別至多5個，共有8個類別。
		var otherInterestTexts = [ '${user.i_d.items.o1.value}',
				'${user.i_d.items.o2.value}', '${user.i_d.items.o3.value}',
				'${user.i_d.items.o4.value}', '${user.i_d.items.o5.value}',
				'${user.i_d.items.o6.value}', '${user.i_d.items.o7.value}',
				'${user.i_d.items.o8.value}', '${user.i_d.items.o9.value}',
				'${user.i_d.items.o10.value}', '${user.i_d.items.o11.value}',
				'${user.i_d.items.o12.value}', '${user.i_d.items.o13.value}',
				'${user.i_d.items.o14.value}', '${user.i_d.items.o15.value}',
				'${user.i_d.items.o16.value}', '${user.i_d.items.o17.value}',
				'${user.i_d.items.o18.value}', '${user.i_d.items.o19.value}',
				'${user.i_d.items.o20.value}', '${user.i_d.items.o21.value}',
				'${user.i_d.items.o22.value}', '${user.i_d.items.o23.value}',
				'${user.i_d.items.o24.value}', '${user.i_d.items.o25.value}',
				'${user.i_d.items.o26.value}', '${user.i_d.items.o27.value}',
				'${user.i_d.items.o28.value}', '${user.i_d.items.o29.value}',
				'${user.i_d.items.o30.value}', '${user.i_d.items.o31.value}',
				'${user.i_d.items.o32.value}', '${user.i_d.items.o33.value}',
				'${user.i_d.items.o34.value}', '${user.i_d.items.o35.value}',
				'${user.i_d.items.o36.value}', '${user.i_d.items.o37.value}',
				'${user.i_d.items.o38.value}', '${user.i_d.items.o39.value}',
				'${user.i_d.items.o40.value}' ];

		$(document).ready(function() {
			for (var i = 0; i < otherInterestTexts.length; i++) {
				var inputJOb = $('#' + getInputNameByIndex(i));
				inputJOb.val(otherInterestTexts[i]);
				var event = jQuery.Event('blur');
				inputJOb.trigger(event);
				inputJOb.val('');
			}
		});
	</script>
</body>
</html>