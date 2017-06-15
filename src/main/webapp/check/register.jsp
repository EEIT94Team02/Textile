<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register, Textile</title>
</head>
<body>
	<form action="register.do" method="post">
		<fieldset>
			<legend>
				Form Check<em>(required)</em>
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
				<label for="mAddress">地址：</label> <select id="mAddress_county" name="mAddress_county"></select><select
					id="mAddress_region" name="mAddress_region"></select> <input id="mAddress" name="mAddress"
					value="${dataAndErrorsMap.mAddress}" type="text" size="65" maxlength="60" placeholder="" required /> <span><img
					id="mAddressImage" src="" />${dataAndErrorsMap.mAddress_error}</span> <br />
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
		<fieldset></fieldset>
	</form>
</body>
</html>