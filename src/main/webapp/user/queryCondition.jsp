<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<style>
div.otherInterestDiv div {
	background: yellow;
}
</style>
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '../js/jquery-ui-1.12.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '../js/json2.js'/>"></script>
</head>
<body>
	<div id="header">
		<div class="section">
			<c:url value="/photo/album/list.do" var="album">
				<c:param name="mId" value="${user.mId}"></c:param>
			</c:url>
			<ul>
				<li><c:if test="${not empty user}">
						<c:if test='${sessionScope.user.mValidManager == "Y"}'>
							<a href="manager/">後臺</a>
						</c:if>
					</c:if></li>
				<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
				<li><a href="<c:url value ='/user/' />">會員</a></li>
				<li><a href="${album}">相簿</a></li>
				<li><a href="<c:url value ='/activity/' />">活動</a></li>
				<li><a href="<c:url value ='/store/' />">商店</a></li>
				<li><a href="<c:url value ='/report/' />">回報</a></li>
				<li><a href="<c:url value ='/announcement/' />">公告</a></li>
				<li><c:if test="${empty user}">
						<a href="check/register.v">註冊</a>
						<c:out escapeXml="false" value="<a href='check/login.r'>(登入)</a>" />
					</c:if> <c:if test="${not empty user}">
						<c:url var="x" value="/check/logout.do" />
						<c:out escapeXml="false" value="<a href='${x}'>${user.mName}</a>" />
					</c:if></li>
			</ul>
		</div>
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
				<form id="queryCondition" action="queryCondition.do" method="post">
					<input type="hidden" name="m" value="queryCondition" />
					<p>個人資料</p>
					<div>
						<label for="mGender">性別：</label>
						<c:set var="genders" value=",M,F" />
						<c:set var="gendersArray" value="${genders.split(',')}" />
						<c:set var="genderWords" value="皆可,男,女" />
						<select id="mGender" name="mGender">
							<c:forEach items="${genderWords.split(',')}" var="genderWordsArray" varStatus="status">
								<option value="${gendersArray[status.index]}"
									${dataAndErrorsMap.mGender == gendersArray[status.index] ? 'selected' : ''}>${genderWordsArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mBirthdayBegin}">
								<c:set var="x" value="${dataAndErrorsMap.mBirthdayBegin}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="1980-01-01" />
							</c:otherwise>
						</c:choose>
						<label for="mBirthdayBegin">出生日期從：</label> <input id="mBirthdayBegin" name="mBirthdayBegin"
							value="${fn:substring(x.toString(),0,10)}" type="date" /> <img src="" /><span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mBirthdayEnd}">
								<c:set var="x" value="${dataAndErrorsMap.mBirthdayEnd}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="2017-06-25" />
							</c:otherwise>
						</c:choose>
						<label for="mBirthdayEnd">到：</label> <input id="mBirthdayEnd" name="mBirthdayEnd"
							value="${fn:substring(x.toString(),0,10)}" type="date" /> <img src="" /><span></span> <br />
						<p></p>
					</div>
					<div>
						<label for="mAddress_County">地區：</label> <select id="mAddress_County" name="mAddress_County">
							<c:set var="address_Taiwan"
								value="皆可,臺北市,基隆市,新北市,桃園市,新竹市,新竹縣,苗栗縣,臺中市,彰化縣,南投縣,雲林縣,嘉義市,嘉義縣,臺南市,高雄市,屏東縣,宜蘭縣,花蓮縣,臺東縣,澎湖縣,金門縣,連江縣" />
							<c:forEach items="${address_Taiwan.split(',')}" var="address_TaiwanArray">
								<option value="${address_TaiwanArray}"
									${dataAndErrorsMap.mAddress_County == address_TaiwanArray ? 'selected' : ''}>${address_TaiwanArray}</option>
							</c:forEach>
						</select>
						<p></p>
						<div id="mAddress_Region"></div>
						<p></p>
					</div>
					<p>論壇經歷</p>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mScores}">
								<c:set var="x" value="${dataAndErrorsMap.mScores}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="0" />
							</c:otherwise>
						</c:choose>
						<label for="mScores">積分：</label> <input type="number" id="mScores" name="mScores" min="0" max="10000000"
							value="${x}" /><br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mCreateTimeBegin}">
								<c:set var="x" value="${dataAndErrorsMap.mCreateTimeBegin}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="2016-01-01" />
							</c:otherwise>
						</c:choose>
						<label for="mCreateTimeBegin">加入會員從：</label> <input id="mCreateTimeBegin" name="mCreateTimeBegin"
							value="${fn:substring(x.toString(),0,10)}" type="date" /> <img src="" /><span></span> <br />
						<p></p>
					</div>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mCreateTimeEnd}">
								<c:set var="x" value="${dataAndErrorsMap.mCreateTimeEnd}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="2017-06-25" />
							</c:otherwise>
						</c:choose>
						<label for="mCreateTimeEnd">到：</label> <input id="mCreateTimeEnd" name="mCreateTimeEnd"
							value="${fn:substring(x.toString(),0,10)}" type="date" /> <img src="" /><span></span> <br />
						<p></p>
					</div>
					<p>個人狀況</p>
					<div>
						<c:choose>
							<c:when test="${not empty dataAndErrorsMap.mCareer}">
								<c:set var="x" value="${dataAndErrorsMap.mCareer}" />
							</c:when>
							<c:otherwise>
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mCareer">目前職業：</label>
						<c:set var="careers"
							value="皆可,其它職業,農牧業,漁業,木材森林業,礦業採石業,交通運輸業,餐旅業,建築工程業,製造業,新聞廣告業,衛生保健業,娛樂業,文教機關,宗教團體,公共事業,一般商業,服務業,家庭管理,治安人員,軍人,資訊業,職業運動人員" />
						<select id="mCareer" name="mCareer">
							<c:forEach items="${careers.split(',')}" var="careersArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${careersArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mEducation">最高學歷：</label>
						<c:set var="educations" value="皆可,無,國小,國中,高中職,學士,專科,碩士,博士" />
						<select id="mEducation" name="mEducation">
							<c:forEach items="${educations.split(',')}" var="educationsArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${educationsArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mEconomy">經濟狀況：</label>
						<c:set var="economys" value="皆可,貧窮,清寒,普通,小康,富裕" />
						<select id="mEconomy" name="mEconomy">
							<c:forEach items="${economys.split(',')}" var="economysArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${economysArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mMarriage">婚姻狀況：</label>
						<c:set var="marriages" value="皆可,未婚,已婚,離婚,喪偶,分居" />
						<select id="mMarriage" name="mMarriage">
							<c:forEach items="${marriages.split(',')}" var="marriagesArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${marriagesArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mFamily">家庭型態：</label>
						<c:set var="familys" value="皆可,核心家庭,三代同堂,數代同堂,雙薪家庭,頂客家庭,單親家庭,繼親家庭,隔代家庭,新移民家庭,同性戀家族" />
						<select id="mFamily" name="mFamily">
							<c:forEach items="${familys.split(',')}" var="familysArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${familysArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mBloodType">血型：</label>
						<c:set var="bloodTypes" value="皆可,A,B,AB,O" />
						<select id="mBloodType" name="mBloodType">
							<c:forEach items="${bloodTypes.split(',')}" var="bloodTypesArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${bloodTypesArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mConstellation">星座：</label>
						<c:set var="constellations"
							value="皆可,牡羊座 (3/21~4/20),金牛座 (4/21~5/21),雙子座 (5/22~6/21),巨蟹座 (6/22~7/23),獅子座 (7/24~8/23),處女座 (8/24~9/23),天秤座 (9/24~10/23),天蠍座 (10/24~11/22),射手座 (11/23~12/22),魔羯座 (12/23~1/20),水瓶座 (1/21~2/19),雙魚座 (2/20~3/20)" />
						<select id="mConstellation" name="mConstellation">
							<c:forEach items="${constellations.split(',')}" var="constellationsArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${constellationsArray}</option>
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
								<c:set var="x" value="-1" />
							</c:otherwise>
						</c:choose>
						<label for="mReligion">宗教信仰：</label>
						<c:set var="religions" value="皆可,無,佛教,道教,基督教,天主教,回教,民間信仰,其它宗教" />
						<select id="mReligion" name="mReligion">
							<c:forEach items="${religions.split(',')}" var="religionsArray" varStatus="status">
								<option value="${status.index - 1}" ${x == (status.index - 1) ? 'selected' : ''}>${religionsArray}</option>
							</c:forEach>
						</select> <span></span> <br />
						<p></p>
					</div>
					<p>興趣喜好</p>
					<div>
						<table id="interestTable">
							<tbody>
								<tr>
									<td>戶外休閒：</td>
									<td><input type="checkbox" name="mI_L1" value="${user.i_d.items.i1.key}" />${user.i_d.items.i1.value}<input
										type="checkbox" name="mI_L2" value="${user.i_d.items.i2.key}" />${user.i_d.items.i2.value}<input
										type="checkbox" name="mI_L3" value="${user.i_d.items.i3.key}" />${user.i_d.items.i3.value}<input
										type="checkbox" name="mI_L4" value="${user.i_d.items.i4.key}" />${user.i_d.items.i4.value}<input
										type="checkbox" name="mI_L5" value="${user.i_d.items.i5.key}" />${user.i_d.items.i5.value}<input
										type="checkbox" name="mI_L6" value="${user.i_d.items.i6.key}" />${user.i_d.items.i6.value}<input
										type="checkbox" name="mI_L7" value="${user.i_d.items.i7.key}" />${user.i_d.items.i7.value}<input
										type="checkbox" name="mI_L8" value="${user.i_d.items.i8.key}" />${user.i_d.items.i8.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>運動健身：</td>
									<td><input type="checkbox" name="mI_L9" value="${user.i_d.items.i9.key}" />${user.i_d.items.i9.value}<input
										type="checkbox" name="mI_L10" value="${user.i_d.items.i10.key}" />${user.i_d.items.i10.value}<input
										type="checkbox" name="mI_L11" value="${user.i_d.items.i11.key}" />${user.i_d.items.i11.value}<input
										type="checkbox" name="mI_L12" value="${user.i_d.items.i12.key}" />${user.i_d.items.i12.value}<input
										type="checkbox" name="mI_L13" value="${user.i_d.items.i13.key}" />${user.i_d.items.i13.value}<input
										type="checkbox" name="mI_L14" value="${user.i_d.items.i14.key}" />${user.i_d.items.i14.value}<input
										type="checkbox" name="mI_L15" value="${user.i_d.items.i15.key}" />${user.i_d.items.i15.value}<input
										type="checkbox" name="mI_L16" value="${user.i_d.items.i16.key}" />${user.i_d.items.i16.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>飲食料理：</td>
									<td><input type="checkbox" name="mI_L17" value="${user.i_d.items.i17.key}" />${user.i_d.items.i17.value}<input
										type="checkbox" name="mI_L18" value="${user.i_d.items.i18.key}" />${user.i_d.items.i18.value}<input
										type="checkbox" name="mI_L19" value="${user.i_d.items.i19.key}" />${user.i_d.items.i19.value}<input
										type="checkbox" name="mI_L20" value="${user.i_d.items.i20.key}" />${user.i_d.items.i20.value}<input
										type="checkbox" name="mI_L21" value="${user.i_d.items.i21.key}" />${user.i_d.items.i21.value}<input
										type="checkbox" name="mI_L22" value="${user.i_d.items.i22.key}" />${user.i_d.items.i22.value}<input
										type="checkbox" name="mI_L23" value="${user.i_d.items.i23.key}" />${user.i_d.items.i23.value}<input
										type="checkbox" name="mI_L24" value="${user.i_d.items.i24.key}" />${user.i_d.items.i24.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>文學藝術：</td>
									<td><input type="checkbox" name="mI_L25" value="${user.i_d.items.i25.key}" />${user.i_d.items.i25.value}<input
										type="checkbox" name="mI_L26" value="${user.i_d.items.i26.key}" />${user.i_d.items.i26.value}<input
										type="checkbox" name="mI_L27" value="${user.i_d.items.i27.key}" />${user.i_d.items.i27.value}<input
										type="checkbox" name="mI_L28" value="${user.i_d.items.i28.key}" />${user.i_d.items.i28.value}<input
										type="checkbox" name="mI_L29" value="${user.i_d.items.i29.key}" />${user.i_d.items.i29.value}<input
										type="checkbox" name="mI_L30" value="${user.i_d.items.i30.key}" />${user.i_d.items.i30.value}<input
										type="checkbox" name="mI_L31" value="${user.i_d.items.i31.key}" />${user.i_d.items.i31.value}<input
										type="checkbox" name="mI_L32" value="${user.i_d.items.i32.key}" />${user.i_d.items.i32.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>設計藝術：</td>
									<td><input type="checkbox" name="mI_L33" value="${user.i_d.items.i33.key}" />${user.i_d.items.i33.value}<input
										type="checkbox" name="mI_L34" value="${user.i_d.items.i34.key}" />${user.i_d.items.i34.value}<input
										type="checkbox" name="mI_L35" value="${user.i_d.items.i35.key}" />${user.i_d.items.i35.value}<input
										type="checkbox" name="mI_L36" value="${user.i_d.items.i36.key}" />${user.i_d.items.i36.value}<input
										type="checkbox" name="mI_L37" value="${user.i_d.items.i37.key}" />${user.i_d.items.i37.value}<input
										type="checkbox" name="mI_L38" value="${user.i_d.items.i38.key}" />${user.i_d.items.i38.value}<input
										type="checkbox" name="mI_L39" value="${user.i_d.items.i39.key}" />${user.i_d.items.i39.value}<input
										type="checkbox" name="mI_L40" value="${user.i_d.items.i40.key}" />${user.i_d.items.i40.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>音樂藝術：</td>
									<td><input type="checkbox" name="mI_L41" value="${user.i_d.items.i41.key}" />${user.i_d.items.i41.value}<input
										type="checkbox" name="mI_L42" value="${user.i_d.items.i42.key}" />${user.i_d.items.i42.value}<input
										type="checkbox" name="mI_L43" value="${user.i_d.items.i43.key}" />${user.i_d.items.i43.value}<input
										type="checkbox" name="mI_L44" value="${user.i_d.items.i44.key}" />${user.i_d.items.i44.value}<input
										type="checkbox" name="mI_L45" value="${user.i_d.items.i45.key}" />${user.i_d.items.i45.value}<input
										type="checkbox" name="mI_L46" value="${user.i_d.items.i46.key}" />${user.i_d.items.i46.value}<input
										type="checkbox" name="mI_L47" value="${user.i_d.items.i47.key}" />${user.i_d.items.i47.value}<input
										type="checkbox" name="mI_L48" value="${user.i_d.items.i48.key}" />${user.i_d.items.i48.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>興趣嗜好：</td>
									<td><input type="checkbox" name="mI_L49" value="${user.i_d.items.i49.key}" />${user.i_d.items.i49.value}<input
										type="checkbox" name="mI_L50" value="${user.i_d.items.i50.key}" />${user.i_d.items.i50.value}<input
										type="checkbox" name="mI_L51" value="${user.i_d.items.i51.key}" />${user.i_d.items.i51.value}<input
										type="checkbox" name="mI_L52" value="${user.i_d.items.i52.key}" />${user.i_d.items.i52.value}<input
										type="checkbox" name="mI_L53" value="${user.i_d.items.i53.key}" />${user.i_d.items.i53.value}<input
										type="checkbox" name="mI_L54" value="${user.i_d.items.i54.key}" />${user.i_d.items.i54.value}<input
										type="checkbox" name="mI_L55" value="${user.i_d.items.i55.key}" />${user.i_d.items.i55.value}<input
										type="checkbox" name="mI_L56" value="${user.i_d.items.i56.key}" />${user.i_d.items.i56.value}</td>
									<td></td>
								</tr>
								<tr>
									<td>其它活動：</td>
									<td><input type="checkbox" name="mI_L57" value="${user.i_d.items.i57.key}" />${user.i_d.items.i57.value}<input
										type="checkbox" name="mI_L58" value="${user.i_d.items.i58.key}" />${user.i_d.items.i58.value}<input
										type="checkbox" name="mI_L59" value="${user.i_d.items.i59.key}" />${user.i_d.items.i59.value}<input
										type="checkbox" name="mI_L60" value="${user.i_d.items.i60.key}" />${user.i_d.items.i60.value}<input
										type="checkbox" name="mI_L61" value="${user.i_d.items.i61.key}" />${user.i_d.items.i61.value}<input
										type="checkbox" name="mI_L62" value="${user.i_d.items.i62.key}" />${user.i_d.items.i62.value}<input
										type="checkbox" name="mI_L63" value="${user.i_d.items.i63.key}" />${user.i_d.items.i63.value}<input
										type="checkbox" name="mI_L64" value="${user.i_d.items.i64.key}" />${user.i_d.items.i64.value}</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td><input id="mOtherInterest" name="mOtherInterest" value="" type="text" size="21" maxlength="20"
										placeholder="這裡可以輸入其它興趣哦..." /><img src="" /></td>
									<td><div class="otherInterestDiv"></div></td>
								</tr>
							</tbody>
						</table>

					</div>
					<div>
						<input id="submit" name="submit" value="搜尋" type="submit" /><img src="" /><span>${dataAndErrorsMap.queryCondition_error}</span>
						<br />
						<p></p>
					</div>
				</form>
			</fieldset>
			<c:if test="${not empty mAddress_RegionList}">
				<c:set var="start" value="${false}" />
				<c:forEach var="mAddress_RegionArray" items="${mAddress_RegionList}" varStatus="status">
					<c:if test="${mAddress_RegionArray != ''}">
						<c:choose>
							<c:when test="${start == false}">
								<c:set var="start" value="${true}" />
								<c:set var="mAddress_RegionCheckedArray" value="${mAddress_RegionArray}" />
							</c:when>
							<c:otherwise>
								<c:set var="mAddress_RegionCheckedArray" value="${mAddress_RegionCheckedArray},${mAddress_RegionArray}" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${not empty mInterest_List}">
				<c:set var="start" value="${false}" />
				<c:forEach var="mInterestArray" items="${mInterest_List}" varStatus="status">
					<c:if test="${mInterestArray != ''}">
						<c:choose>
							<c:when test="${start == false}">
								<c:set var="start" value="${true}" />
								<c:set var="mInterestExistArray" value="${mInterestArray}" />
							</c:when>
							<c:otherwise>
								<c:set var="mInterestExistArray" value="${mInterestExistArray},${mInterestArray}" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${not empty mOtherInterest_List}">
				<c:set var="start" value="${false}" />
				<c:forEach var="mOtherInterestArray" items="${mOtherInterest_List}" varStatus="status">
					<c:if test="${mOtherInterestArray != ''}">
						<c:choose>
							<c:when test="${start == false}">
								<c:set var="start" value="${true}" />
								<c:set var="mOtherInterestExistArray" value="${mOtherInterestArray}" />
							</c:when>
							<c:otherwise>
								<c:set var="mOtherInterestExistArray" value="${mOtherInterestExistArray},${mOtherInterestArray}" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">
    var imgsrc_close16 = '../image/check/check_close16.png';
    var imgsrc_loading16 = '../image/check/check_loading16.gif';
    var interestList = [];

    // 設定日期物件的結束時間不能比開始時間前面
    function changemBirthdayEndMin() {
      $('#mBirthdayEnd').attr('min', $('#mBirthdayBegin').val());
    }

    function changemCreateTimeEndMin() {
      $('#mCreateTimeEnd').attr('min', $('#mCreateTimeBegin').val());
    }

    $('#mBirthdayBegin').on('change', changemBirthdayEndMin);
    $('#mCreateTimeBegin').on('change', changemCreateTimeEndMin);

    // 縣市鄉鎮區的陣列，因為可以不選，故新增「皆可」。
    var address_TaiwanArray = ['皆可', '臺北市', '基隆市', '新北市', '桃園市', '新竹市', '新竹縣', '苗栗縣', '臺中市', '彰化縣', '南投縣', '雲林縣', '嘉義市', '嘉義縣', '臺南市', '高雄市', '屏東縣', '宜蘭縣', '花蓮縣', '臺東縣', '澎湖縣', '金門縣', '連江縣'];
    var address_EmptyArray = [];
    var address_TaipeiCityArray = ['中正區', '大同區', '中山區', '松山區', '大安區', '萬華區', '信義區', '士林區', '北投區', '內湖區', '南港區', '文山區'];
    var address_KeelungCityArray = ['仁愛區', '中正區', '信義區', '中山區', '安樂區', '暖暖區', '七堵區'];
    var address_NewTaipeiCityArray = ['板橋區', '新莊區', '中和區', '永和區', '土城區', '樹林區', '三峽區', '鶯歌區', '三重區', '蘆洲區', '五股區', '泰山區', '林口區', '八里區', '淡水區', '三芝區', '石門區', '金山區', '萬里區', '汐止區', '瑞芳區', '貢寮區', '平溪區', '雙溪區', '新店區', '深坑區', '石碇區', '坪林區', '烏來區'];
    var address_TaoyuanCityArray = ['桃園區', '中壢區', '平鎮區', '八德區', '楊梅區', '蘆竹區', '大溪區', '龍潭區', '龜山區', '大園區', '觀音區', '新屋區', '復興區'];
    var address_HsinchuCityArray = ['東區', '北區', '香山區'];
    var address_HsinchuCountyArray = ['竹北市', '竹東鎮', '新埔鎮', '關西鎮', '湖口鄉', '新豐鄉', '峨眉鄉', '寶山鄉', '北埔鄉', '芎林鄉', '橫山鄉', '尖石鄉', '五峰鄉'];
    var address_MiaoliCountyArray = ['苗栗市', '頭份市', '竹南鎮', '後龍鎮', '通霄鎮', '苑裡鎮', '卓蘭鎮', '造橋鄉', '西湖鄉', '頭屋鄉', '公館鄉', '銅鑼鄉', '三義鄉', '大湖鄉', '獅潭鄉', '三灣鄉', '南庄鄉', '泰安鄉'];
    var address_TaichungCityArray = ['中區', '東區', '南區', '西區', '北區', '北屯區', '西屯區', '南屯區', '太平區', '大里區', '霧峰區', '烏日區', '豐原區', '后里區', '石岡區', '東勢區', '新社區', '潭子區', '大雅區', '神岡區', '大肚區', '沙鹿區', '龍井區', '梧棲區', '清水區', '大甲區', '外埔區', '大安區', '和平區'];
    var address_ChanghuaCountyArray = ['彰化市', '員林市', '和美鎮', '鹿港鎮', '溪湖鎮', '二林鎮', '田中鎮', '北斗鎮', '花壇鄉', '芬園鄉', '大村鄉', '永靖鄉', '伸港鄉', '線西鄉', '福興鄉', '秀水鄉', '埔心鄉', '埔鹽鄉', '大城鄉', '芳苑鄉', '竹塘鄉', '社頭鄉', '二水鄉', '田尾鄉', '埤頭鄉', '溪州鄉'];
    var address_NantouCountyArray = ['南投市', '埔里鎮', '草屯鎮', '竹山鎮', '集集鎮', '名間鄉', '鹿谷鄉', '中寮鄉', '魚池鄉', '國姓鄉', '水里鄉', '信義鄉', '仁愛鄉'];
    var address_YunlinCountyArray = ['斗六市', '斗南鎮', '虎尾鎮', '西螺鎮', '土庫鎮', '北港鎮', '林內鄉', '古坑鄉', '大埤鄉', '莿桐鄉', '褒忠鄉', '二崙鄉', '崙背鄉', '麥寮鄉', '臺西鄉', '東勢鄉', '元長鄉', '四湖鄉', '口湖鄉', '水林鄉'];
    var address_ChiayiCityArray = ['東區', '西區'];
    var address_ChiayiCountyArray = ['太保市', '朴子市', '布袋鎮', '大林鎮', '民雄鄉', '溪口鄉', '新港鄉', '六腳鄉', '東石鄉', '義竹鄉', '鹿草鄉', '水上鄉', '中埔鄉', '竹崎鄉', '梅山鄉', '番路鄉', '大埔鄉', '阿里山鄉'];
    var address_TainanCityArray = ['中西區', '東區', '南區', '北區', '安平區', '安南區', '永康區', '歸仁區', '新化區', '左鎮區', '玉井區', '楠西區', '南化區', '仁德區', '關廟區', '龍崎區', '官田區', '麻豆區', '佳里區', '西港區', '七股區', '將軍區', '學甲區', '北門區', '新營區', '後壁區', '白河區', '東山區', '六甲區', '下營區', '柳營區', '鹽水區', '善化區', '大內區', '山上區', '新市區', '安定區'];
    var address_KaohsiungCityArray = ['楠梓區', '左營區', '鼓山區', '三民區', '鹽埕區', '前金區', '新興區', '苓雅區', '前鎮區', '旗津區', '小港區', '鳳山區', '大寮區', '鳥松區', '林園區', '仁武區', '大樹區', '大社區', '岡山區', '路竹區', '橋頭區', '梓官區', '彌陀區', '永安區', '燕巢區', '田寮區', '阿蓮區', '茄萣區', '湖內區', '旗山區', '美濃區', '內門區', '杉林區', '甲仙區', '六龜區', '茂林區', '桃源區', '那瑪夏區'];
    var address_PingtungCountyArray = ['屏東市', '潮州鎮', '東港鎮', '恆春鎮', '萬丹鄉', '長治鄉', '麟洛鄉', '九如鄉', '里港鄉', '鹽埔鄉', '高樹鄉', '萬巒鄉', '內埔鄉', '竹田鄉', '新埤鄉', '枋寮鄉', '新園鄉', '崁頂鄉', '林邊鄉', '南州鄉', '佳冬鄉', '琉球鄉', '車城鄉', '滿州鄉', '枋山鄉', '霧臺鄉', '瑪家鄉', '泰武鄉', '來義鄉', '春日鄉', '獅子鄉', '牡丹鄉', '三地門鄉'];
    var address_YilanCountyArray = ['宜蘭市', '頭城鎮', '羅東鎮', '蘇澳鎮', '礁溪鄉', '壯圍鄉', '員山鄉', '冬山鄉', '五結鄉', '三星鄉', '大同鄉', '南澳鄉'];
    var address_HualienCountyArray = ['花蓮市', '鳳林鎮', '玉里鎮', '新城鄉', '吉安鄉', '壽豐鄉', '光復鄉', '豐濱鄉', '瑞穗鄉', '富里鄉', '秀林鄉', '萬榮鄉', '卓溪鄉'];
    var address_TaitungCountyArray = ['臺東市', '成功鎮', '關山鎮', '長濱鄉', '池上鄉', '東河鄉', '鹿野鄉', '卑南鄉', '大武鄉', '綠島鄉', '太麻里鄉', '海端鄉', '延平鄉', '金峰鄉', '達仁鄉', '蘭嶼鄉'];
    var address_PenghuCountyArray = ['馬公市', '湖西鄉', '白沙鄉', '西嶼鄉', '望安鄉', '七美鄉'];
    var address_KinmenCountyArray = ['金城鎮', '金湖鎮', '金沙鎮', '金寧鄉', '烈嶼鄉', '烏坵鄉'];
    var address_LianjiangCountyArray = ['南竿鄉', '北竿鄉', '莒光鄉', '東引鄉'];
    var address_AllArray = [address_EmptyArray, address_TaipeiCityArray, address_KeelungCityArray, address_NewTaipeiCityArray, address_TaoyuanCityArray, address_HsinchuCityArray, address_HsinchuCountyArray, address_MiaoliCountyArray, address_TaichungCityArray, address_ChanghuaCountyArray, address_NantouCountyArray, address_YunlinCountyArray, address_ChiayiCityArray, address_ChiayiCountyArray, address_TainanCityArray, address_KaohsiungCityArray, address_PingtungCountyArray, address_YilanCountyArray, address_HualienCountyArray, address_TaitungCountyArray, address_PenghuCountyArray, address_KinmenCountyArray, address_LianjiangCountyArray];

    // 當縣市更動時，鄉鎮區亦要更動。
    function changeOptionOfmAddress_Region() {
      var county = $('#mAddress_County').val();
      var regionArray;
      for (var i = 0; i < address_TaiwanArray.length; i++) {
        if (county === address_TaiwanArray[i]) {
          regionArray = address_AllArray[i];
        }
      }
      var divJOb = $('#mAddress_Region');
      divJOb.empty();
      divJOb.slideUp();
      if (county === '皆可') { return; }
      for (var i = 0; i < regionArray.length; i++) {
        var checkboxJOb = $('<input type="checkbox" id="mA_RL' + i + '" name="mA_RL' + i + '" value="' + regionArray[i] + '">' + regionArray[i] + '</input>');
        var spanJOb;
        if (i % 10 == 9) {
          spanJOb = $('<br />');
        } else {
          spanJOb = $('<span>&nbsp;&nbsp;</span>');
        }
        divJOb.append([checkboxJOb, spanJOb]);
      }
      divJOb.slideDown(500);
    }

    $('#mAddress_County').on('change', changeOptionOfmAddress_Region);

    // 設定提交表單後，要將按鈕功能取消。
    function disableSubmit() {
      var submitJOb = $(document).find('input[type="submit"]');
      submitJOb.prop('disabled', true);
      submitJOb.next().attr('src', imgsrc_loading16);
    }

    $('#queryCondition').on('submit', disableSubmit);

    // 設定新增其它的興趣。m=q,q=input
    function checkInterest(event) {
      var id = $(event.currentTarget).attr('id');
      $('#' + id + ' + img').attr('src', imgsrc_loading16);
      $.get('queryCondition.do', {
        'm': id,
        'q': $('#' + id).val()
      }, function(output) {
        interestList = JSON.parse(output);
        $('#' + id).autocomplete({
          source: interestList
        });
      });
    }

    function checkInterestOnblur(event) {
      var id = $(event.currentTarget).attr('id');
      $('#' + id + ' + img').attr('src', '');
      createOtherInterestDiv(event);
    }

    $('#mOtherInterest').on('keydown', checkInterest).on('blur', checkInterestOnblur);

    function removeDivAndRearrangeAllInput(event) {
      // 先找到父元素，再馬上移除該div，否則影響到div的剩餘數目。
      var inputJOb = $(event.target).parents('tr').find('input[type="text"]');
      $(event.target).parent('div').remove();
      var otherInterestDivJOb = inputJOb.parent('tr').find('div[class="otherInterestDiv"]');
      var innerDivs = otherInterestDivJOb.children();
      for (var j = 0; j < innerDivs.length; j++) {
        var hiddenInputJOb = $(innerDivs[j]).find('input[type="hidden"]');
        hiddenInputJOb.attr('name', 'mOI_L' + j);
      }
    }

    function checkInterestNameExist(innerDivs, newInterestName) {
      for (var i = 0; i < innerDivs.length; i++) {
        var interestName = $(innerDivs[i]).find('span:eq(1)').text();
        if (interestName == newInterestName) { return true; }
      }
      return false;
    }

    function createOtherInterestDiv(event) {
      var inputJOb = $(event.target);
      var otherInterestDivJOb = inputJOb.parents('tr').find('div[class="otherInterestDiv"]');
      var innerDivs = otherInterestDivJOb.children();
      if (inputJOb.val() == '') { return; }
      if (checkInterestNameExist(innerDivs, inputJOb.val())) { return; }
      if (innerDivs.length < 5) {
        var cell1 = $('<img></img>').attr('src', imgsrc_close16);
        $(cell1).on('click', removeDivAndRearrangeAllInput);
        var cell2 = $('<span></span>').text(' ');
        var cell3 = $('<span></span>').text(inputJOb.val());
        var cell4 = $('<input type="hidden" name="mOI_L' + (innerDivs.length + 1) + '" />').val(inputJOb.val());
        var newDivJOb = $('<div></div>').append([cell1, cell2, cell3, cell4]);
        otherInterestDivJOb.append(newDivJOb);
      } else {
        alert('其它興趣最多只能輸入五個。');
      }
    }

    // 設定註冊錯誤回傳後，要預先設定日期的結束時間最小值，和設定所有開始日期的最大值為今天。
    $(document).ready(function() {
      changemBirthdayEndMin();
      changemCreateTimeEndMin();
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth() + 1; // January is 0!
      var yyyy = today.getFullYear();
      if (dd < 10) {
        dd = '0' + dd
      }
      if (mm < 10) {
        mm = '0' + mm
      }
      today = yyyy + '-' + mm + '-' + dd;
      $('#mBirthdayBegin').attr('max', today);
      $('#mBirthdayEnd').attr('max', today);
      $('#mCreateTimeBegin').attr('max', today);
      $('#mCreateTimeEnd').attr('max', today);
    });

    // 設定註冊錯誤回傳後，表單地址鄉鎮區必須跟原來所選的值一樣。
    $(document).ready(function() {
      changeOptionOfmAddress_Region();
      var mAddress_RegionCheckedArray = ("${mAddress_RegionCheckedArray}").split(',');
      var checkboxJObs = $('#mAddress_Region').children('input[type="checkbox"]');
      for (var i = 0; i < mAddress_RegionCheckedArray.length; i++) {
        for (var j = 0; j < checkboxJObs.length; j++) {
          if ($(checkboxJObs[j]).val() == mAddress_RegionCheckedArray[i]) {
            $(checkboxJObs[j]).prop('checked', true);
          }
        }
      }
    });

    // 設定興趣的初始化。
    var mInterestExistArray = ("${mInterestExistArray}").split(',');

    $(document).ready(function() {
      for (var i = 0; i < mInterestExistArray.length; i++) {
        var checkboxJOb;
        for (var j = 1; j < 64; j++) {
          checkboxJOb = $('#interestTable').find('input[name="mI_L' + j + '"]');
          if (mInterestExistArray[i] == checkboxJOb.val()) {
            checkboxJOb.prop('checked', true);
          }
        }
      }
    });

    // 設定其它興趣的初始化，方法為模擬手動新增興趣，每個興趣類別至多5個，共有8個類別。
    var mOtherInterestExistArray = ("${mOtherInterestExistArray}").split(',');

    $(document).ready(function() {
      for (var i = 0; i < mOtherInterestExistArray.length; i++) {
        var inputJOb = $('#mOtherInterest');
        inputJOb.val(mOtherInterestExistArray[i]);
        var event = jQuery.Event('blur');
        inputJOb.trigger(event);
        inputJOb.val('');
      }
    });
  </script>
</body>
</html>