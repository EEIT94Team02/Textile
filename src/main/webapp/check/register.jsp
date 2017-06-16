<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register, Textile</title>
<script type="text/javascript" src="../js/jquery-3.2.1.js"></script>
</head>
<body>
	<form action="register.do" method="post">
		<fieldset>
			<legend>
				個人資料一<em>(必要)</em>
			</legend>
			<div>
				<label for="mEmail">帳號：</label> <input id="mEmail" name="mEmail" value="${dataAndErrorsMap.mEmail}" type="text"
					size="55" maxlength="50" placeholder="請輸入帳號" autofocus required /> <span><img id="mEmailImage" src="" />${dataAndErrorsMap.mEmail_error}</span>
				<br />
				<p>帳號為Email信箱。</p>
			</div>
			<div>
				<label for="mPassword">密碼：</label> <input id="mPassword" name="mPassword" value="${dataAndErrorsMap.mPassword}"
					type="password" size="21" maxlength="16" placeholder="請輸入密碼" required /> <span><img id="mPasswordImage"
					src="" />${dataAndErrorsMap.mPassword_error}</span> <br />
				<p>密碼長度介於8~16個字元，包含英文大寫、小寫、數字和規定的特殊符號各至少一個。</p>
			</div>
			<div>
				<!-- 這裡用JavaScript驗證密碼是否相同，且啟動時必須複製上面的值在這邊覆蓋。 -->
				<label>請再次輸入密碼：</label> <input id="mPassword_again" type="password" size="21" maxlength="16" placeholder="請輸入密碼"
					autocomplete="off" required /> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mName">姓名：</label> <input id="mName" name="mName" value="${dataAndErrorsMap.mName}" type="text"
					size="21" maxlength="20" placeholder="王小明" required /> <span><img id="mNameImage" src="" />${dataAndErrorsMap.mName_error}</span>
				<br />
				<p>姓名為兩個字以上的中文字或六個字以上的英文字。</p>
			</div>
			<div>
				<label for="mIdentityCardNumber">身分證字號：</label> <input id="mIdentityCardNumber" name="mIdentityCardNumber"
					value="${dataAndErrorsMap.mIdentityCardNumber}" type="text" size="21" maxlength="10" placeholder="" required /> <span><img
					id="mIdentityCardNumberImage" src="" />${dataAndErrorsMap.mIdentityCardNumber_error}</span> <br />
				<p>身分證字號由開頭一個大寫英文字和末九位的數字所組成。</p>
			</div>
			<div>
				<label for="mBirthday">生日：</label> <input id="mBirthday" name="mBirthday" value="${dataAndErrorsMap.mBirthday}"
					type="text" size="21" maxlength="10" placeholder="2000-2-29" required /> <span><img id="mBirthdayImage"
					src="" />${dataAndErrorsMap.mBirthday_error}</span> <br />
				<p>生日為yyyy-mm-dd(西元年-月-日)的格式。</p>
			</div>
			<div>
				<label for="mPhoneNumber">手機：</label> <input id="mPhoneNumber" name="mPhoneNumber"
					value="${dataAndErrorsMap.mPhoneNumber}" type="text" size="21" maxlength="10" placeholder="" required /> <span><img
					id="mPhoneNumberImage" src="" />${dataAndErrorsMap.mPhoneNumber_error}</span> <br />
				<p></p>
			</div>
			<div>
				<label for="mAddress">性別：</label>
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
			<!-- 這裡用JavaScript產生縣市鄉鎮區，且選單必須跟著變動，mAddress必須合併。 -->
			<div>
				<label for="mAddress">地址：</label> <select id="mAddress_county" name="mAddress_county">
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
				<label for="mHintPassword">密碼提示：</label> <input id="mHintPassword" name="mHintPassword"
					value="${dataAndErrorsMap.mHintPassword}" type="text" size="45" maxlength="40" placeholder="" required /> <span><img
					id="mHintPasswordImage" src="" />${dataAndErrorsMap.mHintPassword_error}</span> <br />
				<p></p>
			</div>
			<div>
				<label for="mHintAnswer">提示答案：</label> <input id="mHintAnswer" name="mHintAnswer"
					value="${dataAndErrorsMap.mHintAnswer}" type="text" size="45" maxlength="40" placeholder="" required /> <span><img
					id="mHintAnswerImage" src="" />${dataAndErrorsMap.mHintAnswer_error}</span> <br />
				<p></p>
			</div>
		</fieldset>
		<fieldset>
			<legend> 個人資料二 </legend>
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

		</fieldset>
	</form>
	<script type="text/javascript">
	<!-- 縣市鄉鎮區的陣列 -->
		var address_TaiwanArray = [ '臺北市', '基隆市', '新北市', '桃園市', '新竹市', '新竹縣',
				'苗栗縣', '臺中市', '彰化縣', '南投縣', '雲林縣', '嘉義市', '嘉義縣', '臺南市', '高雄市',
				'屏東縣', '宜蘭縣', '花蓮縣', '臺東縣', '澎湖縣', '金門縣', '連江縣' ];
		var address_TaipeiCityArray = [ '中正區', '大同區', '中山區', '松山區', '大安區',
				'萬華區', '信義區', '士林區', '北投區', '內湖區', '南港區', '文山區' ];
		var address_KeelungCityArray = [ '仁愛區', '中正區', '信義區', '中山區', '安樂區',
				'暖暖區', '七堵區' ];
		var address_NewTaipeiCityArray = [ '板橋區', '新莊區', '中和區', '永和區', '土城區',
				'樹林區', '三峽區', '鶯歌區', '三重區', '蘆洲區', '五股區', '泰山區', '林口區', '八里區',
				'淡水區', '三芝區', '石門區', '金山區', '萬里區', '汐止區', '瑞芳區', '貢寮區', '平溪區',
				'雙溪區', '新店區', '深坑區', '石碇區', '坪林區', '烏來區' ];
		var address_TaoyuanCityArray = [ '桃園區', '中壢區', '平鎮區', '八德區', '楊梅區',
				'蘆竹區', '大溪區', '龍潭區', '龜山區', '大園區', '觀音區', '新屋區', '復興區' ];
		var address_HsinchuCityArray = [ '東區', '北區', '香山區' ];
		var address_HsinchuCountyArray = [ '竹北市', '竹東鎮', '新埔鎮', '關西鎮', '湖口鄉',
				'新豐鄉', '峨眉鄉', '寶山鄉', '北埔鄉', '芎林鄉', '橫山鄉', '尖石鄉', '五峰鄉' ];
		var address_MiaoliCountyArray = [ '苗栗市', '頭份市', '竹南鎮', '後龍鎮', '通霄鎮',
				'苑裡鎮', '卓蘭鎮', '造橋鄉', '西湖鄉', '頭屋鄉', '公館鄉', '銅鑼鄉', '三義鄉', '大湖鄉',
				'獅潭鄉', '三灣鄉', '南庄鄉', '泰安鄉' ];
		var address_TaichungCityArray = [ '中區', '東區', '南區', '西區', '北區', '北屯區',
				'西屯區', '南屯區', '太平區', '大里區', '霧峰區', '烏日區', '豐原區', '后里區', '石岡區',
				'東勢區', '新社區', '潭子區', '大雅區', '神岡區', '大肚區', '沙鹿區', '龍井區', '梧棲區',
				'清水區', '大甲區', '外埔區', '大安區', '和平區' ];
		var address_ChanghuaCountyArray = [ '彰化市', '員林市', '和美鎮', '鹿港鎮', '溪湖鎮',
				'二林鎮', '田中鎮', '北斗鎮', '花壇鄉', '芬園鄉', '大村鄉', '永靖鄉', '伸港鄉', '線西鄉',
				'福興鄉', '秀水鄉', '埔心鄉', '埔鹽鄉', '大城鄉', '芳苑鄉', '竹塘鄉', '社頭鄉', '二水鄉',
				'田尾鄉', '埤頭鄉', '溪州鄉' ];
		var address_NantouCountyArray = [ '南投市', '埔里鎮', '草屯鎮', '竹山鎮', '集集鎮',
				'名間鄉', '鹿谷鄉', '中寮鄉', '魚池鄉', '國姓鄉', '水里鄉', '信義鄉', '仁愛鄉' ];
		var address_YunlinCountyArray = [ '斗六市', '斗南鎮', '虎尾鎮', '西螺鎮', '土庫鎮',
				'北港鎮', '林內鄉', '古坑鄉', '大埤鄉', '莿桐鄉', '褒忠鄉', '二崙鄉', '崙背鄉', '麥寮鄉',
				'臺西鄉', '東勢鄉', '元長鄉', '四湖鄉', '口湖鄉', '水林鄉' ];
		var address_ChiayiCityArray = [ '東區', '西區' ];
		var address_ChiayiCountyArray = [ '太保市', '朴子市', '布袋鎮', '大林鎮', '民雄鄉',
				'溪口鄉', '新港鄉', '六腳鄉', '東石鄉', '義竹鄉', '鹿草鄉', '水上鄉', '中埔鄉', '竹崎鄉',
				'梅山鄉', '番路鄉', '大埔鄉', '阿里山鄉' ];
		var address_TainanCityArray = [ '中西區', '東區', '南區', '北區', '安平區', '安南區',
				'永康區', '歸仁區', '新化區', '左鎮區', '玉井區', '楠西區', '南化區', '仁德區', '關廟區',
				'龍崎區', '官田區', '麻豆區', '佳里區', '西港區', '七股區', '將軍區', '學甲區', '北門區',
				'新營區', '後壁區', '白河區', '東山區', '六甲區', '下營區', '柳營區', '鹽水區', '善化區',
				'大內區', '山上區', '新市區', '安定區' ];
		var address_KaohsiungCityArray = [ '楠梓區', '左營區', '鼓山區', '三民區', '鹽埕區',
				'前金區', '新興區', '苓雅區', '前鎮區', '旗津區', '小港區', '鳳山區', '大寮區', '鳥松區',
				'林園區', '仁武區', '大樹區', '大社區', '岡山區', '路竹區', '橋頭區', '梓官區', '彌陀區',
				'永安區', '燕巢區', '田寮區', '阿蓮區', '茄萣區', '湖內區', '旗山區', '美濃區', '內門區',
				'杉林區', '甲仙區', '六龜區', '茂林區', '桃源區', '那瑪夏區' ];
		var address_PingtungCountyArray = [ '屏東市', '潮州鎮', '東港鎮', '恆春鎮', '萬丹鄉',
				'長治鄉', '麟洛鄉', '九如鄉', '里港鄉', '鹽埔鄉', '高樹鄉', '萬巒鄉', '內埔鄉', '竹田鄉',
				'新埤鄉', '枋寮鄉', '新園鄉', '崁頂鄉', '林邊鄉', '南州鄉', '佳冬鄉', '琉球鄉', '車城鄉',
				'滿州鄉', '枋山鄉', '霧臺鄉', '瑪家鄉', '泰武鄉', '來義鄉', '春日鄉', '獅子鄉', '牡丹鄉',
				'三地門鄉' ];
		var address_YilanCountyArray = [ '宜蘭市', '頭城鎮', '羅東鎮', '蘇澳鎮', '礁溪鄉',
				'壯圍鄉', '員山鄉', '冬山鄉', '五結鄉', '三星鄉', '大同鄉', '南澳鄉' ];
		var address_HualienCountyArray = [ '花蓮市', '鳳林鎮', '玉里鎮', '新城鄉', '吉安鄉',
				'壽豐鄉', '光復鄉', '豐濱鄉', '瑞穗鄉', '富里鄉', '秀林鄉', '萬榮鄉', '卓溪鄉' ];
		var address_TaitungCountyArray = [ '臺東市', '成功鎮', '關山鎮', '長濱鄉', '池上鄉',
				'東河鄉', '鹿野鄉', '卑南鄉', '大武鄉', '綠島鄉', '太麻里鄉', '海端鄉', '延平鄉', '金峰鄉',
				'達仁鄉', '蘭嶼鄉' ];
		var address_PenghuCountyArray = [ '馬公市', '湖西鄉', '白沙鄉', '西嶼鄉', '望安鄉',
				'七美鄉' ];
		var address_KinmenCountyArray = [ '金城鎮', '金湖鎮', '金沙鎮', '金寧鄉', '烈嶼鄉',
				'烏坵鄉' ];
		var address_LianjiangCountyArray = [ '南竿鄉', '北竿鄉', '莒光鄉', '東引鄉' ];
		var address_AllArray = [ address_TaipeiCityArray,
				address_KeelungCityArray, address_NewTaipeiCityArray,
				address_TaoyuanCityArray, address_HsinchuCityArray,
				address_HsinchuCountyArray, address_MiaoliCountyArray,
				address_TaichungCityArray, address_ChanghuaCountyArray,
				address_NantouCountyArray, address_YunlinCountyArray,
				address_ChiayiCityArray, address_ChiayiCountyArray,
				address_TainanCityArray, address_KaohsiungCityArray,
				address_PingtungCountyArray, address_YilanCountyArray,
				address_HualienCountyArray, address_TaitungCountyArray,
				address_PenghuCountyArray, address_KinmenCountyArray,
				address_LianjiangCountyArray ];

		// 當縣市更動時，鄉鎮區亦要更動
		$('#mAddress_county').on('change', function() {
			var county = $('#mAddress_county').val();
			var regionArray;
			for (var i = 0; i < address_TaiwanArray.length; i++) {
				if (county === address_TaiwanArray[i]) {
					regionArray = address_AllArray[i];
				}
			}
			var selectJOb = $('#mAddress_region');
			selectJOb.empty();
			for (var i = 0; i < regionArray.length; i++) {
				var optionJOb = $('<option></option>').text(regionArray[i]);
				optionJOb.val(regionArray[i]);
				selectJOb.append(optionJOb);
			}
		});

		// 設定表單個人資料二的預設值
		$(document).ready(function() {
			$('#mEconomy option:eq(2)').prop('selected', true);
		});
	</script>
</body>
</html>