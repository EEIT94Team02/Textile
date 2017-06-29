<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>${otheruser.mName},Textile</title>
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
			<fieldset>
				<table class="dataBasic">
					<thead>
						<tr>
							<th colspan="2">基本資料</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>姓名：</td>
							<td>${otheruser.mName}</td>
						</tr>
						<tr>
							<td>性別：</td>
							<td><c:set var="genders" value="M,F" /> <c:set var="gendersArray" value="${genders.split(',')}" /> <c:set
									var="genderWords" value="男,女" /> <c:forEach items="${genderWords.split(',')}" var="genderWordsArray"
									varStatus="status">
					${otheruser.mGender == gendersArray[status.index] ? genderWordsArray : ''}
				</c:forEach></td>
						</tr>
						<tr>
							<!-- 地址只出現前三個字，如縣、市。 -->
							<td>居住：</td>
							<td>${otheruser.mAddress_County}</td>
						</tr>
						<tr>
							<td>出生：</td>
							<td>${fn:substring(otheruser.mBirthday.toString(),0,4)}年</td>
						</tr>
						<tr>
							<td>積分：</td>
							<td>${otheruser.mScores}</td>
						</tr>
					</tbody>
				</table>
				<form action='<c:url value="/social/invite.do?q=${param.q}"/>' method="post">
					<table>
						<tr>
							<td></td>
							<td><input type="submit" name="submit" value="邀請"></td>
							<td>${SocialListInviteErrors.insert}</td>
						</tr>
					</table>
				</form>
			</fieldset>
			<fieldset>
				<table class="dataSituation">
					<thead>
						<tr>
							<th colspan="2">個人簡介</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>目前職業：</td>
							<c:set var="careers"
								value="其它職業,農牧業,漁業,木材森林業,礦業採石業,交通運輸業,餐旅業,建築工程業,製造業,新聞廣告業,衛生保健業,娛樂業,文教機關,宗教團體,公共事業,一般商業,服務業,家庭管理,治安人員,軍人,資訊業,職業運動人員" />
							<c:forEach items="${careers.split(',')}" var="careersArray" varStatus="status">
								<c:if test="${otheruser.mCareer == status.index}">
									<td>${careersArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>最高學歷：</td>
							<c:set var="educations" value="無,國小,國中,高中職,學士,專科,碩士,博士" />
							<c:forEach items="${educations.split(',')}" var="educationsArray" varStatus="status">
								<c:if test="${otheruser.mEducation == status.index}">
									<td>${educationsArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>經濟狀況：</td>
							<c:set var="economys" value="貧窮,清寒,普通,小康,富裕" />
							<c:forEach items="${economys.split(',')}" var="economysArray" varStatus="status">
								<c:if test="${otheruser.mEconomy == status.index}">
									<td>${economysArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>婚姻狀況：</td>
							<c:set var="marriages" value="未婚,已婚,離婚,喪偶,分居" />
							<c:forEach items="${marriages.split(',')}" var="marriagesArray" varStatus="status">
								<c:if test="${otheruser.mMarriage == status.index}">
									<td>${marriagesArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>家庭型態：</td>
							<c:set var="familys" value="核心家庭,三代同堂,數代同堂,雙薪家庭,頂客家庭,單親家庭,繼親家庭,隔代家庭,新移民家庭,同性戀家族" />
							<c:forEach items="${familys.split(',')}" var="familysArray" varStatus="status">
								<c:if test="${otheruser.mFamily == status.index}">
									<td>${familysArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>血型：</td>
							<c:set var="bloodTypes" value="A,B,AB,O" />
							<c:forEach items="${bloodTypes.split(',')}" var="bloodTypesArray" varStatus="status">
								<c:if test="${otheruser.mBloodType == status.index}">
									<td>${bloodTypesArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>星座：</td>
							<c:set var="constellations"
								value="牡羊座 (3/21~4/20),金牛座 (4/21~5/21),雙子座 (5/22~6/21),巨蟹座 (6/22~7/23),獅子座 (7/24~8/23),處女座 (8/24~9/23),天秤座 (9/24~10/23),天蠍座 (10/24~11/22),射手座 (11/23~12/22),魔羯座 (12/23~1/20),水瓶座 (1/21~2/19),雙魚座 (2/20~3/20)" />
							<c:forEach items="${constellations.split(',')}" var="constellationsArray" varStatus="status">
								<c:if test="${otheruser.mConstellation == status.index}">
									<td>${constellationsArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>宗教信仰：</td>
							<c:set var="religions" value="無,佛教,道教,基督教,天主教,回教,民間信仰,其它宗教" />
							<c:forEach items="${religions.split(',')}" var="religionsArray" varStatus="status">
								<c:if test="${otheruser.mReligion == status.index}">
									<td>${religionsArray}</td>
								</c:if>
							</c:forEach>
						</tr>
						<tr>
							<td>自我介紹：</td>
							<td>${otheruser.mSelfIntroduction}</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset>
				<table>
					<thead>
						<tr>
							<th colspan="2">興趣喜好</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>戶外休閒：</td>
							<td><c:set var="recreation"
									value="${otheruser.i_d.items.i1.value},${otheruser.i_d.items.i2.value},${otheruser.i_d.items.i3.value},${otheruser.i_d.items.i4.value},${otheruser.i_d.items.i5.value},${otheruser.i_d.items.i6.value},${otheruser.i_d.items.i7.value},${otheruser.i_d.items.i8.value},${otheruser.i_d.items.o1.value},${otheruser.i_d.items.o2.value},${otheruser.i_d.items.o3.value},${otheruser.i_d.items.o4.value},${otheruser.i_d.items.o5.value}" />
								<c:set var="recreationSelected"
									value="${otheruser.i_d.items.i1.selected},${otheruser.i_d.items.i2.selected},${otheruser.i_d.items.i3.selected},${otheruser.i_d.items.i4.selected},${otheruser.i_d.items.i5.selected},${otheruser.i_d.items.i6.selected},${otheruser.i_d.items.i7.selected},${otheruser.i_d.items.i8.selected},${otheruser.i_d.items.o1.selected},${otheruser.i_d.items.o2.selected},${otheruser.i_d.items.o3.selected},${otheruser.i_d.items.o4.selected},${otheruser.i_d.items.o5.selected}" />
								<c:set var="recreationSelectedArray" value="${recreationSelected.split(',')}" /> <c:set var="start"
									value="${false}" /> <c:forEach items="${recreation.split(',')}" var="recreationArray" varStatus="status">
									<c:if test="${recreationSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${recreationArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${recreationArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>運動健身：</td>
							<td><c:set var="exercises"
									value="${otheruser.i_d.items.i9.value},${otheruser.i_d.items.i10.value},${otheruser.i_d.items.i11.value},${otheruser.i_d.items.i12.value},${otheruser.i_d.items.i13.value},${otheruser.i_d.items.i14.value},${otheruser.i_d.items.i15.value},${otheruser.i_d.items.i16.value},${otheruser.i_d.items.o6.value},${otheruser.i_d.items.o7.value},${otheruser.i_d.items.o8.value},${otheruser.i_d.items.o9.value},${otheruser.i_d.items.o10.value}" />
								<c:set var="exercisesSelected"
									value="${otheruser.i_d.items.i9.selected},${otheruser.i_d.items.i10.selected},${otheruser.i_d.items.i11.selected},${otheruser.i_d.items.i12.selected},${otheruser.i_d.items.i13.selected},${otheruser.i_d.items.i14.selected},${otheruser.i_d.items.i15.selected},${otheruser.i_d.items.i16.selected},${otheruser.i_d.items.o6.selected},${otheruser.i_d.items.o7.selected},${otheruser.i_d.items.o8.selected},${otheruser.i_d.items.o9.selected},${otheruser.i_d.items.o10.selected}" />
								<c:set var="exercisesSelectedArray" value="${exercisesSelected.split(',')}" /> <c:set var="start"
									value="${false}" /> <c:forEach items="${exercises.split(',')}" var="exercisesArray" varStatus="status">
									<c:if test="${exercisesSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${exercisesArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${exercisesArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>飲食料理：</td>
							<td><c:set var="diet"
									value="${otheruser.i_d.items.i17.value},${otheruser.i_d.items.i18.value},${otheruser.i_d.items.i19.value},${otheruser.i_d.items.i20.value},${otheruser.i_d.items.i21.value},${otheruser.i_d.items.i22.value},${otheruser.i_d.items.i23.value},${otheruser.i_d.items.i24.value},${otheruser.i_d.items.o11.value},${otheruser.i_d.items.o12.value},${otheruser.i_d.items.o13.value},${otheruser.i_d.items.o14.value},${otheruser.i_d.items.o15.value}" />
								<c:set var="dietSelected"
									value="${otheruser.i_d.items.i17.selected},${otheruser.i_d.items.i18.selected},${otheruser.i_d.items.i19.selected},${otheruser.i_d.items.i20.selected},${otheruser.i_d.items.i21.selected},${otheruser.i_d.items.i22.selected},${otheruser.i_d.items.i23.selected},${otheruser.i_d.items.i24.selected},${otheruser.i_d.items.o11.selected},${otheruser.i_d.items.o12.selected},${otheruser.i_d.items.o13.selected},${otheruser.i_d.items.o14.selected},${otheruser.i_d.items.o15.selected}" />
								<c:set var="dietSelectedArray" value="${dietSelected.split(',')}" /> <c:set var="start" value="${false}" /> <c:forEach
									items="${diet.split(',')}" var="dietArray" varStatus="status">
									<c:if test="${dietSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${dietArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${dietArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>文學藝術：</td>
							<td><c:set var="art"
									value="${otheruser.i_d.items.i25.value},${otheruser.i_d.items.i26.value},${otheruser.i_d.items.i27.value},${otheruser.i_d.items.i28.value},${otheruser.i_d.items.i29.value},${otheruser.i_d.items.i30.value},${otheruser.i_d.items.i31.value},${otheruser.i_d.items.i32.value},${otheruser.i_d.items.o16.value},${otheruser.i_d.items.o17.value},${otheruser.i_d.items.o18.value},${otheruser.i_d.items.o19.value},${otheruser.i_d.items.o20.value}" />
								<c:set var="artSelected"
									value="${otheruser.i_d.items.i25.selected},${otheruser.i_d.items.i26.selected},${otheruser.i_d.items.i27.selected},${otheruser.i_d.items.i28.selected},${otheruser.i_d.items.i29.selected},${otheruser.i_d.items.i30.selected},${otheruser.i_d.items.i31.selected},${otheruser.i_d.items.i32.selected},${otheruser.i_d.items.o16.selected},${otheruser.i_d.items.o17.selected},${otheruser.i_d.items.o18.selected},${otheruser.i_d.items.o19.selected},${otheruser.i_d.items.o20.selected}" />
								<c:set var="artSelectedArray" value="${artSelected.split(',')}" /> <c:set var="start" value="${false}" /> <c:forEach
									items="${art.split(',')}" var="artArray" varStatus="status">
									<c:if test="${artSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${artArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${artArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>設計藝術：</td>
							<td><c:set var="design"
									value="${otheruser.i_d.items.i33.value},${otheruser.i_d.items.i34.value},${otheruser.i_d.items.i35.value},${otheruser.i_d.items.i36.value},${otheruser.i_d.items.i37.value},${otheruser.i_d.items.i38.value},${otheruser.i_d.items.i39.value},${otheruser.i_d.items.i40.value},${otheruser.i_d.items.o21.value},${otheruser.i_d.items.o22.value},${otheruser.i_d.items.o23.value},${otheruser.i_d.items.o24.value},${otheruser.i_d.items.o25.value}" />
								<c:set var="designSelected"
									value="${otheruser.i_d.items.i33.selected},${otheruser.i_d.items.i34.selected},${otheruser.i_d.items.i35.selected},${otheruser.i_d.items.i36.selected},${otheruser.i_d.items.i37.selected},${otheruser.i_d.items.i38.selected},${otheruser.i_d.items.i39.selected},${otheruser.i_d.items.i40.selected},${otheruser.i_d.items.o21.selected},${otheruser.i_d.items.o22.selected},${otheruser.i_d.items.o23.selected},${otheruser.i_d.items.o24.selected},${otheruser.i_d.items.o25.selected}" />
								<c:set var="designSelectedArray" value="${designSelected.split(',')}" /> <c:set var="start" value="${false}" />
								<c:forEach items="${design.split(',')}" var="designArray" varStatus="status">
									<c:if test="${designSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${designArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${designArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>音樂藝術：</td>
							<td><c:set var="music"
									value="${otheruser.i_d.items.i41.value},${otheruser.i_d.items.i42.value},${otheruser.i_d.items.i43.value},${otheruser.i_d.items.i44.value},${otheruser.i_d.items.i45.value},${otheruser.i_d.items.i46.value},${otheruser.i_d.items.i47.value},${otheruser.i_d.items.i48.value},${otheruser.i_d.items.o26.value},${otheruser.i_d.items.o27.value},${otheruser.i_d.items.o28.value},${otheruser.i_d.items.o29.value},${otheruser.i_d.items.o30.value}" />
								<c:set var="musicSelected"
									value="${otheruser.i_d.items.i41.selected},${otheruser.i_d.items.i42.selected},${otheruser.i_d.items.i43.selected},${otheruser.i_d.items.i44.selected},${otheruser.i_d.items.i45.selected},${otheruser.i_d.items.i46.selected},${otheruser.i_d.items.i47.selected},${otheruser.i_d.items.i48.selected},${otheruser.i_d.items.o26.selected},${otheruser.i_d.items.o27.selected},${otheruser.i_d.items.o28.selected},${otheruser.i_d.items.o29.selected},${otheruser.i_d.items.o30.selected}" />
								<c:set var="musicSelectedArray" value="${musicSelected.split(',')}" /> <c:set var="start" value="${false}" />
								<c:forEach items="${music.split(',')}" var="musicArray" varStatus="status">
									<c:if test="${musicSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${musicArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${musicArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>興趣嗜好：</td>
							<td><c:set var="hobbies"
									value="${otheruser.i_d.items.i49.value},${otheruser.i_d.items.i50.value},${otheruser.i_d.items.i51.value},${otheruser.i_d.items.i52.value},${otheruser.i_d.items.i53.value},${otheruser.i_d.items.i54.value},${otheruser.i_d.items.i55.value},${otheruser.i_d.items.i56.value},${otheruser.i_d.items.o31.value},${otheruser.i_d.items.o32.value},${otheruser.i_d.items.o33.value},${otheruser.i_d.items.o34.value},${otheruser.i_d.items.o35.value}" />
								<c:set var="hobbiesSelected"
									value="${otheruser.i_d.items.i49.selected},${otheruser.i_d.items.i50.selected},${otheruser.i_d.items.i51.selected},${otheruser.i_d.items.i52.selected},${otheruser.i_d.items.i53.selected},${otheruser.i_d.items.i54.selected},${otheruser.i_d.items.i55.selected},${otheruser.i_d.items.i56.selected},${otheruser.i_d.items.o31.selected},${otheruser.i_d.items.o32.selected},${otheruser.i_d.items.o33.selected},${otheruser.i_d.items.o34.selected},${otheruser.i_d.items.o35.selected}" />
								<c:set var="hobbiesSelectedArray" value="${hobbiesSelected.split(',')}" /> <c:set var="start" value="${false}" />
								<c:forEach items="${hobbies.split(',')}" var="hobbiesArray" varStatus="status">
									<c:if test="${hobbiesSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${hobbiesArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${hobbiesArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td>其它活動：</td>
							<td><c:set var="activities"
									value="${otheruser.i_d.items.i57.value},${otheruser.i_d.items.i58.value},${otheruser.i_d.items.i59.value},${otheruser.i_d.items.i60.value},${otheruser.i_d.items.i61.value},${otheruser.i_d.items.i62.value},${otheruser.i_d.items.i63.value},${otheruser.i_d.items.i64.value},${otheruser.i_d.items.o36.value},${otheruser.i_d.items.o37.value},${otheruser.i_d.items.o38.value},${otheruser.i_d.items.o39.value},${otheruser.i_d.items.o40.value}" />
								<c:set var="activitiesSelected"
									value="${otheruser.i_d.items.i57.selected},${otheruser.i_d.items.i58.selected},${otheruser.i_d.items.i59.selected},${otheruser.i_d.items.i60.selected},${otheruser.i_d.items.i61.selected},${otheruser.i_d.items.i62.selected},${otheruser.i_d.items.i63.selected},${otheruser.i_d.items.i64.selected},${otheruser.i_d.items.o36.selected},${otheruser.i_d.items.o37.selected},${otheruser.i_d.items.o38.selected},${otheruser.i_d.items.o39.selected},${otheruser.i_d.items.o40.selected}" />
								<c:set var="activitiesSelectedArray" value="${activitiesSelected.split(',')}" /> <c:set var="start"
									value="${false}" /> <c:forEach items="${activities.split(',')}" var="activitiesArray" varStatus="status">
									<c:if test="${activitiesSelectedArray[status.index] == '1'}">
										<c:choose>
											<c:when test="${start == false}">
												<c:set var="start" value="${true}" />
												<c:out value="${activitiesArray}" />
											</c:when>
											<c:otherwise>
												<c:out value="、${activitiesArray}" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</div>
	</div>
	<script type="text/javascript">
    
  </script>
</body>
</html>