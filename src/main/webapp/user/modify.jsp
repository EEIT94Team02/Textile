<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<form action="register.do" method="post">
		<fieldset>
			<legend>
				個人資料<em>(*為必填項目)</em>
			</legend>
			<div>
				<label for="mEmail">*帳號：</label> <input id="mEmail" name="mEmail" value="${dataAndErrorsMap.mEmail}" type="text"
					size="55" maxlength="50" placeholder="請輸入帳號" autofocus required /> <img src="" /><span>${dataAndErrorsMap.mEmail_error}</span>
				<br />
				<p>帳號為Email信箱。</p>
			</div>
			<div>
				<label for="mPassword">*密碼：</label> <input id="mPassword" name="mPassword" value="${dataAndErrorsMap.mPassword}"
					type="password" size="21" maxlength="16" placeholder="請輸入密碼" required /> <span><img id="mPasswordImage"
					src="" />${dataAndErrorsMap.mPassword_error}</span> <br />
				<p>密碼長度介於8~16個字元，包含英文大寫、小寫、數字和規定的特殊符號各至少一個。</p>
			</div>
			<div>
				<label>*請再次輸入密碼：</label> <input id="mPassword_again" type="password" size="21" maxlength="16" placeholder="請輸入密碼"
					autocomplete="off" required /> <span><img id="mPassword_againImage" src="" /></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mName">*姓名：</label> <input id="mName" name="mName" value="${dataAndErrorsMap.mName}" type="text"
					size="21" maxlength="20" placeholder="王小明" required /> <span><img id="mNameImage" src="" />${dataAndErrorsMap.mName_error}</span>
				<br />
				<p>姓名為兩個字以上的中文字或六個字以上的英文字。</p>
			</div>
			<div>
				<label for="mIdentityCardNumber">*身分證字號：</label> <input id="mIdentityCardNumber" name="mIdentityCardNumber"
					value="${dataAndErrorsMap.mIdentityCardNumber}" type="text" size="21" maxlength="10" placeholder="" required /> <span><img
					id="mIdentityCardNumberImage" src="" />${dataAndErrorsMap.mIdentityCardNumber_error}</span> <br />
				<p>身分證字號由開頭一個大寫英文字和末九位的數字所組成。</p>
			</div>
			<div>
				<label for="mBirthday">*生日：</label> <input id="mBirthday" name="mBirthday" value="${dataAndErrorsMap.mBirthday}"
					type="text" size="21" maxlength="10" placeholder="2000-2-29" required /> <span><img id="mBirthdayImage"
					src="" />${dataAndErrorsMap.mBirthday_error}</span> <br />
				<p>生日為yyyy-mm-dd(西元年-月-日)的格式。</p>
			</div>
			<div>
				<label for="mPhoneNumber">*手機：</label> <input id="mPhoneNumber" name="mPhoneNumber"
					value="${dataAndErrorsMap.mPhoneNumber}" type="text" size="21" maxlength="10" placeholder="" required /> <span><img
					id="mPhoneNumberImage" src="" />${dataAndErrorsMap.mPhoneNumber_error}</span> <br />
				<p></p>
			</div>
			<div>
				<label for="mAddress">*性別：</label>
				<c:set var="genders" value="M,F" />
				<c:set var="gendersArray" value="${genders.split(',')}" />
				<c:set var="genderWords" value="男,女" />
				<select id="mGender" name="mGender">
					<c:forEach items="${genderWords.split(',')}" var="genderWordsArray" varStatus="status">
						<option value="${gendersArray[status.index]}"
							${dataAndErrorsMap.mGender == gendersArray[status.index] ? 'selected' : ''}>${genderWordsArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mAddress">*地址：</label> <select id="mAddress_county" name="mAddress_county">
					<c:set var="address_Taiwan"
						value="臺北市,基隆市,新北市,桃園市,新竹市,新竹縣,苗栗縣,臺中市,彰化縣,南投縣,雲林縣,嘉義市,嘉義縣,臺南市,高雄市,屏東縣,宜蘭縣,花蓮縣,臺東縣,澎湖縣,金門縣,連江縣" />
					<c:forEach items="${address_Taiwan.split(',')}" var="address_TaiwanArray">
						<option value="${address_TaiwanArray}"
							${dataAndErrorsMap.mAddress_county == address_TaiwanArray ? 'selected' : ''}>${address_TaiwanArray}</option>
					</c:forEach>
				</select> <select id="mAddress_region" name="mAddress_region">
					<c:set var="address_Taipei" value="中正區,大同區,中山區,松山區,大安區,萬華區,信義區,士林區,北投區,內湖區,南港區,文山區" />
					<c:forEach items="${address_Taipei.split(',')}" var="address_TaipeiArray">
						<option value="${address_TaipeiArray}"
							${dataAndErrorsMap.mAddress_region == address_TaipeiArray ? 'selected' : ''}>${address_TaipeiArray}</option>
					</c:forEach>
				</select> <input id="mAddress" name="mAddress" value="${dataAndErrorsMap.mAddress}" type="text" size="65" maxlength="60"
					placeholder="" required /> <span><img id="mAddressImage" src="" />${dataAndErrorsMap.mAddress_error}</span> <br />
				<p></p>
			</div>
			<div>
				<label for="mHintPassword">*密碼提示：</label> <input id="mHintPassword" name="mHintPassword"
					value="${dataAndErrorsMap.mHintPassword}" type="text" size="45" maxlength="40" placeholder="" required /> <span><img
					id="mHintPasswordImage" src="" />${dataAndErrorsMap.mHintPassword_error}</span> <br />
				<p></p>
			</div>
			<div>
				<label for="mHintAnswer">*提示答案：</label> <input id="mHintAnswer" name="mHintAnswer"
					value="${dataAndErrorsMap.mHintAnswer}" type="text" size="45" maxlength="40" placeholder="" required /> <span><img
					id="mHintAnswerImage" src="" />${dataAndErrorsMap.mHintAnswer_error}</span> <br />
				<p></p>
			</div>
		</fieldset>
		<fieldset>
			<legend> 個人狀況 </legend>
			<div>
				<label for="mCareer">目前職業：</label>
				<c:set var="careers"
					value="其它職業,農牧業,漁業,木材森林業,礦業採石業,交通運輸業,餐旅業,建築工程業,製造業,新聞廣告業,衛生保健業,娛樂業,文教機關,宗教團體,公共事業,一般商業,服務業,家庭管理,治安人員,軍人,資訊業,職業運動人員" />
				<select id="mCareer" name="mCareer">
					<c:forEach items="${careers.split(',')}" var="careersArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mCareer == status.index ? 'selected' : ''}>${careersArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mEducation">最高學歷：</label>
				<c:set var="educations" value="無,國小,國中,高中職,學士,專科,碩士,博士" />
				<select id="mEducation" name="mEducation">
					<c:forEach items="${educations.split(',')}" var="educationsArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mEducation == status.index ? 'selected' : ''}>${educationsArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mEconomy">經濟狀況：</label>
				<c:set var="economys" value="貧窮,清寒,普通,小康,富裕" />
				<select id="mEconomy" name="mEconomy">
					<c:forEach items="${economys.split(',')}" var="economysArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mEconomy == status.index ? 'selected' : ''}>${economysArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mMarriage">婚姻狀況：</label>
				<c:set var="marriages" value="未婚,已婚,離婚,喪偶,分居" />
				<select id="mMarriage" name="mMarriage">
					<c:forEach items="${marriages.split(',')}" var="marriagesArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mMarriage == status.index ? 'selected' : ''}>${marriagesArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mFamily">家庭型態：</label>
				<c:set var="familys" value="核心家庭,三代同堂,數代同堂,雙薪家庭,頂客家庭,單親家庭,繼親家庭,隔代家庭,新移民家庭,同性戀家族" />
				<select id="mFamily" name="mFamily">
					<c:forEach items="${familys.split(',')}" var="familysArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mFamily == status.index ? 'selected' : ''}>${familysArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mBloodType">血型：</label>
				<c:set var="bloodTypes" value="A,B,AB,O" />
				<select id="mBloodType" name="mBloodType">
					<c:forEach items="${bloodTypes.split(',')}" var="bloodTypesArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mBloodType == status.index ? 'selected' : ''}>${bloodTypesArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<!-- 星座欄要配合生日 -->
			<div>
				<label for="mConstellation">星座：</label>
				<c:set var="constellations"
					value="牡羊座 (3/21~4/20),金牛座 (4/21~5/21),雙子座 (5/22~6/21),巨蟹座 (6/22~7/23),獅子座 (7/24~8/23),處女座 (8/24~9/23),天秤座 (9/24~10/23),天蠍座 (10/24~11/22),射手座 (11/23~12/22),魔羯座 (12/23~1/20),水瓶座 (1/21~2/19),雙魚座 (2/20~3/20)" />
				<select id="mConstellation" name="mConstellation">
					<c:forEach items="${constellations.split(',')}" var="constellationsArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mConstellation == status.index ? 'selected' : ''}>${constellationsArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mReligion">宗教信仰：</label>
				<c:set var="religions" value="無,佛教,道教,基督教,天主教,回教,民間信仰,其它宗教" />
				<select id="mReligion" name="mReligion">
					<c:forEach items="${religions.split(',')}" var="religionsArray" varStatus="status">
						<option value="${status.index}" ${dataAndErrorsMap.mReligion == status.index ? 'selected' : ''}>${religionsArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mSelfIntroduction">自我介紹：</label>
				<textarea id="mSelfIntroduction" name="mSelfIntroduction" placeholder="在這裡介紹你自己......">${dataAndErrorsMap.mSelfIntroduction}</textarea>
				<span><img id="mSelfIntroductionImage" src="" />${dataAndErrorsMap.mSelfIntroduction_error}</span> <br />
				<p></p>
			</div>
		</fieldset>
	</form>
	<script type="text/javascript">
    // 當生日更動時，星座也要更動。
    $('#mBirthday').on('change', function() {
      function changeToSelected(index, trueOrFalse) {
        $('#mConstellation option:eq(' + index + ')').prop('selected', trueOrFalse);
      }
      for (var i = 0; i < 12; i++) {
        changeToSelected(i, false);
      }
      if ($('#mBirthday + span img:first-child').attr('src') != '') {
        var numbers = birthday_str.split('-');
        var date = new Date(numbers[0], numbers[1], numbers[2]);
        var dateArray = [new Date(numbers[0], 3, 21), new Date(numbers[0], 4, 21), new Date(numbers[0], 5, 22), new Date(numbers[0], 6, 22), new Date(numbers[0], 7, 24), new Date(numbers[0], 8, 24), new Date(numbers[0], 9, 24), new Date(numbers[0], 10, 24), new Date(numbers[0], 11, 23), new Date(numbers[0], 12, 23), new Date(numbers[0], 1, 21), new Date(numbers[0], 2, 20)];
        for (var i = 0; i < 12; i++) {
          if (i >= 0 && i < 9) {
            if (date >= dateArray[i] && date < dateArray[i + 1]) {
              changeToSelected(i, true);
            }
          } else if (i == 9) {
            if (date >= dateArray[i] || date < dateArray[i + 1]) {
              changeToSelected(i, true);
            }
          } else if (i == 10) {
            if (date >= dateArray[i] && date < dateArray[i + 1]) {
              changeToSelected(i, true);
            }
          } else {
            if (date >= dateArray[i] && date < dateArray[(i + 1) % 11]) {
              changeToSelected(i, true);
            }
          }
        }
      }
    });
    // 設定表單個人資料二的預設值
    $(document).ready(function() {
      $('#mEconomy option:eq(2)').prop('selected', true);
    });
  </script>
</body>
</html>