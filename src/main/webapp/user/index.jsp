<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User, Textile</title>
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
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
</head>
<body>
	<table class="dataBasic">
		<tbody>
			<tr>
				<td>姓名：</td>
				<td>${user.mName}</td>
			</tr>
			<tr>
				<td>性別：</td>
				<c:set var="genders" value="M,F" />
				<c:set var="gendersArray" value="${genders.split(',')}" />
				<c:set var="genderWords" value="男,女" />
				<c:forEach items="${genderWords.split(',')}" var="genderWordsArray" varStatus="status">
					<td>${user.mGender == gendersArray[status.index] ? genderWordsArray : ''}</td>
				</c:forEach>
			</tr>
			<tr>
				<!-- 地址只出現前三個字，如縣、市。 -->
				<td>地址：</td>
				<td>${fn:substring(user.mAddress, 0, 3)}</td>
			</tr>
			<tr>
				<td>積分：</td>
				<td>${user.mScores}</td>
			</tr>
		</tbody>
	</table>
	<table class="dataSituation">
		<tbody>
			<tr>
				<td>目前職業：</td>
				<c:set var="careers"
					value="其它職業,農牧業,漁業,木材森林業,礦業採石業,交通運輸業,餐旅業,建築工程業,製造業,新聞廣告業,衛生保健業,娛樂業,文教機關,宗教團體,公共事業,一般商業,服務業,家庭管理,治安人員,軍人,資訊業,職業運動人員" />
				<c:forEach items="${careers.split(',')}" var="careersArray" varStatus="status">
					<c:if test="${user.mCareer == status.index}">
						<td>${careersArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>最高學歷：</td>
				<c:set var="educations" value="無,國小,國中,高中職,學士,專科,碩士,博士" />
				<c:forEach items="${educations.split(',')}" var="educationsArray" varStatus="status">
					<c:if test="${user.mEducation == status.index}">
						<td>${educationsArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>經濟狀況：</td>
				<c:set var="economys" value="貧窮,清寒,普通,小康,富裕" />
				<c:forEach items="${economys.split(',')}" var="economysArray" varStatus="status">
					<c:if test="${user.mEconomy == status.index}">
						<td>${economysArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>婚姻狀況：</td>
				<c:set var="marriages" value="未婚,已婚,離婚,喪偶,分居" />
				<c:forEach items="${marriages.split(',')}" var="marriagesArray" varStatus="status">
					<c:if test="${user.mMarriage == status.index}">
						<td>${marriagesArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>家庭型態：</td>
				<c:set var="familys" value="核心家庭,三代同堂,數代同堂,雙薪家庭,頂客家庭,單親家庭,繼親家庭,隔代家庭,新移民家庭,同性戀家族" />
				<c:forEach items="${familys.split(',')}" var="familysArray" varStatus="status">
					<c:if test="${user.mFamily == status.index}">
						<td>${familysArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>血型：</td>
				<c:set var="bloodTypes" value="A,B,AB,O" />
				<c:forEach items="${bloodTypes.split(',')}" var="bloodTypesArray" varStatus="status">
					<c:if test="${user.mBloodType == status.index}">
						<td>${bloodTypesArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>星座：</td>
				<c:set var="constellations"
					value="牡羊座 (3/21~4/20),金牛座 (4/21~5/21),雙子座 (5/22~6/21),巨蟹座 (6/22~7/23),獅子座 (7/24~8/23),處女座 (8/24~9/23),天秤座 (9/24~10/23),天蠍座 (10/24~11/22),射手座 (11/23~12/22),魔羯座 (12/23~1/20),水瓶座 (1/21~2/19),雙魚座 (2/20~3/20)" />
				<c:forEach items="${constellations.split(',')}" var="constellationsArray" varStatus="status">
					<c:if test="${user.mConstellation == status.index}">
						<td>${constellationsArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>宗教信仰：</td>
				<c:set var="religions" value="無,佛教,道教,基督教,天主教,回教,民間信仰,其它宗教" />
				<c:forEach items="${religions.split(',')}" var="religionsArray" varStatus="status">
					<c:if test="${user.mReligion == status.index}">
						<td>${religionsArray}</td>
					</c:if>
				</c:forEach>
			</tr>
			<tr>
				<td>自我介紹：</td>
				<td>${user.mSelfIntroduction}</td>
			</tr>
		</tbody>
	</table>
	<table>
		<tbody>
			<tr>
				<td>戶外休閒：</td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript">
	
	
	
	</script>
</body>
</html>