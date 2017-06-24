<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify profile, Textile</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
</head>
<body>
	<form id="profile" action="modify.do" method="post">
		<input type="hidden" name="m" value="profile" />
		<fieldset>
			<legend>個人資料</legend>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mName}">
						<c:set var="x" value="${dataAndErrorsMap.mName}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mName}" />
					</c:otherwise>
				</c:choose>
				<label for="mName">姓名：</label> <input id="mName" name="mName" value="${x}" type="text" size="21" maxlength="20"
					placeholder="王小明" required /> <img src="" /><span>${dataAndErrorsMap.mPassword_error}</span> <br />
				<p>姓名為兩個字以上的中文字或六個字以上的英文字。</p>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mIdentityCardNumber}">
						<c:set var="x" value="${dataAndErrorsMap.mIdentityCardNumber}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mIdentityCardNumber}" />
					</c:otherwise>
				</c:choose>
				<label for="mIdentityCardNumber">身分證字號：</label> <input id="mIdentityCardNumber" name="mIdentityCardNumber"
					value="${x}" type="text" size="21" maxlength="10" placeholder="" required /> <img src="" /><span>${dataAndErrorsMap.mIdentityCardNumber_error}</span>
				<br />
				<p>身分證字號由開頭一個大寫英文字和末九位的數字所組成。</p>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mBirthday}">
						<c:set var="x" value="${dataAndErrorsMap.mBirthday}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mBirthday}" />
					</c:otherwise>
				</c:choose>
				<label for="mBirthday">生日：</label> <input id="mBirthday" name="mBirthday" value="${fn:substring(x.toString(),0,10)}"
					type="text" size="21" maxlength="10" placeholder="2000-2-29" required /> <img src="" /><span>${dataAndErrorsMap.mBirthday_error}</span>
				<br />
				<p>生日為yyyy-mm-dd(西元年-月-日)的格式。</p>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mPhoneNumber}">
						<c:set var="x" value="${dataAndErrorsMap.mPhoneNumber}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mPhoneNumber}" />
					</c:otherwise>
				</c:choose>
				<label for="mPhoneNumber">手機：</label> <input id="mPhoneNumber" name="mPhoneNumber" value="${x}" type="text"
					size="21" maxlength="10" placeholder="" required /> <img src="" /><span>${dataAndErrorsMap.mPhoneNumber_error}</span>
				<br />
				<p></p>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mGender}">
						<c:set var="x" value="${dataAndErrorsMap.mGender}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mGender}" />
					</c:otherwise>
				</c:choose>
				<label for="mGender">性別：</label>
				<c:set var="genders" value="M,F" />
				<c:set var="gendersArray" value="${genders.split(',')}" />
				<c:set var="genderWords" value="男,女" />
				<select id="mGender" name="mGender">
					<c:forEach items="${genderWords.split(',')}" var="genderWordsArray" varStatus="status">
						<option value="${gendersArray[status.index]}" ${x == gendersArray[status.index] ? 'selected' : ''}>${genderWordsArray}</option>
					</c:forEach>
				</select> <span></span> <br />
				<p></p>
			</div>
			<div>
				<label for="mAddress">地址：</label>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mAddress_County}">
						<c:set var="x" value="${dataAndErrorsMap.mAddress_County}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mAddress_County}" />
					</c:otherwise>
				</c:choose>
				<select id="mAddress_County" name="mAddress_County">
					<c:set var="address_Taiwan"
						value="臺北市,基隆市,新北市,桃園市,新竹市,新竹縣,苗栗縣,臺中市,彰化縣,南投縣,雲林縣,嘉義市,嘉義縣,臺南市,高雄市,屏東縣,宜蘭縣,花蓮縣,臺東縣,澎湖縣,金門縣,連江縣" />
					<c:forEach items="${address_Taiwan.split(',')}" var="address_TaiwanArray">
						<option value="${address_TaiwanArray}" ${x == address_TaiwanArray ? 'selected' : ''}>${address_TaiwanArray}</option>
					</c:forEach>
				</select>
				<!-- 地址的區鄉鎮市要用JavaScript再重新刷一次。 -->
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mAddress_Region}">
						<c:set var="x" value="${dataAndErrorsMap.mAddress_Region}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mAddress_Region}" />
					</c:otherwise>
				</c:choose>
				<select id="mAddress_Region" name="mAddress_Region">
					<c:set var="address_Taipei" value="中正區,大同區,中山區,松山區,大安區,萬華區,信義區,士林區,北投區,內湖區,南港區,文山區" />
					<c:forEach items="${address_Taipei.split(',')}" var="address_TaipeiArray">
						<option value="${address_TaipeiArray}" ${x == address_TaipeiArray ? 'selected' : ''}>${address_TaipeiArray}</option>
					</c:forEach>
				</select>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mAddress}">
						<c:set var="x" value="${dataAndErrorsMap.mAddress}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mAddress}" />
					</c:otherwise>
				</c:choose>
				<input id="mAddress" name="mAddress" value="${x}" type="text" size="65" maxlength="60" placeholder="" required /> <img
					src="" /><span>${dataAndErrorsMap.mAddress_error}</span> <br />
				<p></p>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mHintPassword}">
						<c:set var="x" value="${dataAndErrorsMap.mHintPassword}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mHintPassword}" />
					</c:otherwise>
				</c:choose>
				<label for="mHintPassword">密碼提示：</label> <input id="mHintPassword" name="mHintPassword" value="${x}" type="text"
					size="45" maxlength="40" placeholder="" required /> <img src="" /><span>${dataAndErrorsMap.mHintPassword_error}</span>
				<br />
				<p></p>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty dataAndErrorsMap.mHintAnswer}">
						<c:set var="x" value="${dataAndErrorsMap.mHintAnswer}" />
					</c:when>
					<c:otherwise>
						<c:set var="x" value="${user.mHintAnswer}" />
					</c:otherwise>
				</c:choose>
				<label for="mHintAnswer">提示答案：</label> <input id="mHintAnswer" name="mHintAnswer" value="${x}" type="text" size="45"
					maxlength="40" placeholder="" required /> <img src="" /><span>${dataAndErrorsMap.mHintAnswer_error}</span> <br />
				<p></p>
			</div>
			<div>
				<input id="submit" name="submit" value="修改" type="submit" /> <br />
				<p></p>
			</div>
		</fieldset>
	</form>
	<c:choose>
		<c:when test="${not empty dataAndErrorsMap.mAddress_Region}">
			<c:set var="init_mAddress_Region" value="${dataAndErrorsMap.mAddress_Region}" />
		</c:when>
		<c:otherwise>
			<c:set var="init_mAddress_Region" value="${user.mAddress_Region}" />
		</c:otherwise>
	</c:choose>
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
        'm': id,
        'q': $('#' + id).val()
      }, function(output) {
        $('#' + id).parent().find('span').text(output);
      });
    }

    function checkFieldOnblur(event) {
      var id = $(event.currentTarget).attr('id');
      $('#' + id + ' + img').attr('src', imgsrc_loading16);
      $.get('modify.do', {
        'm': id,
        'q': $('#' + id).val()
      }, function(output) {
        $('#' + id).parent().find('span').text(output);
      }).done(function() {
        changeImageFor(id);
      });
    }

    function changeImageFor(id) {
      if ($('#' + id).parent().find('span').text() == '' && $('#' + id).val() == '') {
        $('#' + id + ' + img').attr('src', '');
      } else if ($('#' + id).parent().find('span').text() == '' && $('#' + id).val() != '') {
        $('#' + id + ' + img').attr('src', imgsrc_correct16);
      } else {
        $('#' + id + ' + img').attr('src', imgsrc_error16);
      }
    }

    // 自動驗證姓名
    $('#mName').on('keyup', checkField).on('blur', checkFieldOnblur);
    // 自動驗證身分證字號
    $('#mIdentityCardNumber').on('keyup', checkField).on('blur', checkFieldOnblur);
    // 自動驗證生日
    $('#mBirthday').on('keyup', checkField).on('blur', checkFieldOnblur);
    // 自動驗證手機
    $('#mPhoneNumber').on('keyup', checkField).on('blur', checkFieldOnblur);
    // 自動驗證地址
    $('#mAddress').on('keyup', checkField).on('blur', checkFieldOnblur);
    // 自動驗證密碼提示
    $('#mHintPassword').on('keyup', checkField).on('blur', checkFieldOnblur);
    // 自動驗證提示答案
    $('#mHintAnswer').on('keyup', checkField).on('blur', checkFieldOnblur);

    // 縣市鄉鎮區的陣列
    var address_TaiwanArray = ['臺北市', '基隆市', '新北市', '桃園市', '新竹市', '新竹縣', '苗栗縣', '臺中市', '彰化縣', '南投縣', '雲林縣', '嘉義市', '嘉義縣', '臺南市', '高雄市', '屏東縣', '宜蘭縣', '花蓮縣', '臺東縣', '澎湖縣', '金門縣', '連江縣'];
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
    var address_AllArray = [address_TaipeiCityArray, address_KeelungCityArray, address_NewTaipeiCityArray, address_TaoyuanCityArray, address_HsinchuCityArray, address_HsinchuCountyArray, address_MiaoliCountyArray, address_TaichungCityArray, address_ChanghuaCountyArray, address_NantouCountyArray, address_YunlinCountyArray, address_ChiayiCityArray, address_ChiayiCountyArray, address_TainanCityArray, address_KaohsiungCityArray, address_PingtungCountyArray, address_YilanCountyArray, address_HualienCountyArray, address_TaitungCountyArray, address_PenghuCountyArray, address_KinmenCountyArray, address_LianjiangCountyArray];

    // 當縣市更動時，鄉鎮區亦要更動。
    function changeOptionOfmAddress_Region() {
      var county = $('#mAddress_County').val();
      var regionArray;
      for (var i = 0; i < address_TaiwanArray.length; i++) {
        if (county === address_TaiwanArray[i]) {
          regionArray = address_AllArray[i];
        }
      }
      var selectJOb = $('#mAddress_Region');
      selectJOb.empty();
      for (var i = 0; i < regionArray.length; i++) {
        var optionJOb = $('<option></option>').text(regionArray[i]);
        optionJOb.val(regionArray[i]);
        selectJOb.append(optionJOb);
      }
    }

    $('#mAddress_County').on('change', changeOptionOfmAddress_Region);

    // 設定提交表單後，要將按鈕功能取消。
    function disableSubmit() {
      $(document).find('input[type="submit"]').prop('disabled', true);
    }

    $('#profile').on('submit', disableSubmit);

    // 設定提交表單後如果還有錯誤要做圖示覆蓋
    $(document).ready(function() {
      var idCheckArray = ['mName', 'mIdentityCardNumber', 'mBirthday', 'mPhoneNumber', 'mAddress', 'mHintPassword', 'mHintAnswer'];
      for (var i = 0; i < idCheckArray.length; i++) {
        var id = idCheckArray[i];
        changeImageFor(id);
      }
    });

    // 設定註冊錯誤回傳後，表單地址鄉鎮區必須跟原來所選的值一樣。
    $(document).ready(function() {
      changeOptionOfmAddress_Region();
      var mAddress_Region = '${init_mAddress_Region}';
      $('#mAddress_Region option[value="' + mAddress_Region + '"]').prop('selected', true);
    });
  </script>
</body>
</html>