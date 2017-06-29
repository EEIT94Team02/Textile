<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>Modify situation, Textile</title>
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
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id="right">
		<jsp:include page="/rightInclude.jsp" />
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
		<div id="middle">
			<form id="situation" action="modify.do" method="post">
				<input type="hidden" name="m" value="situation" />
				<fieldset>
					<legend>個人狀況</legend>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mCareer}">
								<c:set var="x" value="${dataAndErrorsMap.mCareer}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mCareer}" />
							</c:otherwise>
						</c:choose>
						<label for="mCareer">目前職業：</label>
						<c:set var="careers"
							value="其它職業,農牧業,漁業,木材森林業,礦業採石業,交通運輸業,餐旅業,建築工程業,製造業,新聞廣告業,衛生保健業,娛樂業,文教機關,宗教團體,公共事業,一般商業,服務業,家庭管理,治安人員,軍人,資訊業,職業運動人員" />
						<select id="mCareer" name="mCareer">
							<c:forEach items="${careers.split(',')}" var="careersArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${careersArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mEducation}">
								<c:set var="x" value="${dataAndErrorsMap.mEducation}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mEducation}" />
							</c:otherwise>
						</c:choose>
						<label for="mEducation">最高學歷：</label>
						<c:set var="educations" value="無,國小,國中,高中職,學士,專科,碩士,博士" />
						<select id="mEducation" name="mEducation">
							<c:forEach items="${educations.split(',')}" var="educationsArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${educationsArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mEconomy}">
								<c:set var="x" value="${dataAndErrorsMap.mEconomy}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mEconomy}" />
							</c:otherwise>
						</c:choose>
						<label for="mEconomy">經濟狀況：</label>
						<c:set var="economys" value="貧窮,清寒,普通,小康,富裕" />
						<select id="mEconomy" name="mEconomy">
							<c:forEach items="${economys.split(',')}" var="economysArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${economysArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mMarriage}">
								<c:set var="x" value="${dataAndErrorsMap.mMarriage}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mMarriage}" />
							</c:otherwise>
						</c:choose>
						<label for="mMarriage">婚姻狀況：</label>
						<c:set var="marriages" value="未婚,已婚,離婚,喪偶,分居" />
						<select id="mMarriage" name="mMarriage">
							<c:forEach items="${marriages.split(',')}" var="marriagesArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${marriagesArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mFamily}">
								<c:set var="x" value="${dataAndErrorsMap.mFamily}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mFamily}" />
							</c:otherwise>
						</c:choose>
						<label for="mFamily">家庭型態：</label>
						<c:set var="familys" value="核心家庭,三代同堂,數代同堂,雙薪家庭,頂客家庭,單親家庭,繼親家庭,隔代家庭,新移民家庭,同性戀家族" />
						<select id="mFamily" name="mFamily">
							<c:forEach items="${familys.split(',')}" var="familysArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${familysArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mBloodType}">
								<c:set var="x" value="${dataAndErrorsMap.mBloodType}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mBloodType}" />
							</c:otherwise>
						</c:choose>
						<label for="mBloodType">血型：</label>
						<c:set var="bloodTypes" value="A,B,AB,O" />
						<select id="mBloodType" name="mBloodType">
							<c:forEach items="${bloodTypes.split(',')}" var="bloodTypesArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${bloodTypesArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mConstellation}">
								<c:set var="x" value="${dataAndErrorsMap.mConstellation}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mConstellation}" />
							</c:otherwise>
						</c:choose>
						<label for="mConstellation">星座：</label>
						<c:set var="constellations"
							value="牡羊座 (3/21~4/20),金牛座 (4/21~5/21),雙子座 (5/22~6/21),巨蟹座 (6/22~7/23),獅子座 (7/24~8/23),處女座 (8/24~9/23),天秤座 (9/24~10/23),天蠍座 (10/24~11/22),射手座 (11/23~12/22),魔羯座 (12/23~1/20),水瓶座 (1/21~2/19),雙魚座 (2/20~3/20)" />
						<select id="mConstellation" name="mConstellation" disabled>
							<c:forEach items="${constellations.split(',')}" var="constellationsArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${constellationsArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mReligion}">
								<c:set var="x" value="${dataAndErrorsMap.mReligion}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mReligion}" />
							</c:otherwise>
						</c:choose>
						<label for="mReligion">宗教信仰：</label>
						<c:set var="religions" value="無,佛教,道教,基督教,天主教,回教,民間信仰,其它宗教" />
						<select id="mReligion" name="mReligion">
							<c:forEach items="${religions.split(',')}" var="religionsArray" varStatus="status">
								<option value="${status.index}" ${x == status.index ? 'selected' : ''}>${religionsArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mSelfIntroduction}">
								<c:set var="x" value="${dataAndErrorsMap.mSelfIntroduction}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="${user.mSelfIntroduction}" />
							</c:otherwise>
						</c:choose>
						<label for="mSelfIntroduction" style="float: left;">自我介紹：</label>
						<textarea rows="10" cols="120" id="mSelfIntroduction" name="mSelfIntroduction" placeholder="在這裡介紹你自己......">${x}</textarea>
						<img src="" /><span>${dataAndErrorsMap.mSelfIntroduction_error}</span> <br />
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

		// 自動驗證自我介紹
		$('#mSelfIntroduction').on('keyup', checkField).on('blur',
				checkFieldOnblur);

		// 設定提交表單後，要將按鈕功能取消。
		function disableSubmit() {
			$(document).find('input[type="submit"]').prop('disabled', true);
		}

		$('#situation').on('submit', disableSubmit);

		// 設定提交表單後如果還有錯誤要做圖示覆蓋
		$(document).ready(function() {
			var idCheckArray = [ 'mSelfIntroduction' ];
			for (var i = 0; i < idCheckArray.length; i++) {
				var id = idCheckArray[i];
				changeImageFor(id);
			}
		});
	</script>
</body>
</html>