package tw.com.eeit94.textile.model.member;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.member.util.CheckAddress;
import tw.com.eeit94.textile.model.member.util.CheckBirthday;
import tw.com.eeit94.textile.model.member.util.CheckEmail;
import tw.com.eeit94.textile.model.member.util.CheckEmailExist;
import tw.com.eeit94.textile.model.member.util.CheckHintAnswer;
import tw.com.eeit94.textile.model.member.util.CheckHintPassword;
import tw.com.eeit94.textile.model.member.util.CheckIdentityCardNumber;
import tw.com.eeit94.textile.model.member.util.CheckName;
import tw.com.eeit94.textile.model.member.util.CheckPassword;
import tw.com.eeit94.textile.model.member.util.CheckPhoneNumber;
import tw.com.eeit94.textile.model.member.util.CheckSelfIntroduction;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.model.member.util.MemberKeyWordsBean;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

/**
 * 控制會員基本資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/16
 */
@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private SecureService secureService;
	@Autowired
	private ExecutableValidator executableValidator;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	private static final String PASSWORD_AGAIN_ERROR_MESSAGE = "密碼不一致";

	/**
	 * 修改會員的基本資料。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	@Transactional
	public MemberBean update(MemberBean mbean) {
		return this.memberDAO.update(mbean).get(0);
	}

	/**
	 * 特殊查詢：利用帳號搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	@Transactional(readOnly = true)
	public MemberBean selectByEmail(String mEmail) {
		MemberKeyWordsBean mkwbean = new MemberKeyWordsBean();
		mkwbean.setmEmail(mEmail);
		MemberBean mbean = null;
		List<MemberBean> list = memberDAO.selectByEmail(mkwbean);
		if (list.size() > 0) {
			mbean = list.get(0);
		}
		return mbean;
	}

	/**
	 * 驗證信箱是否在資料庫會員表格中已有。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	public Map<String, String> checkNonexistentEmail(Map<String, String> dataAndErrorsMap, HttpServletRequest request) {
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.EmailExist.key());
		dataAndErrorsMap.put(ConstMemberKey.EmailExist.key(), request.getParameter(ConstMemberKey.Email.key()));
		dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
		return dataAndErrorsMap;
	}

	/**
	 * 驗證密碼和再輸入密碼是否一致，Map<String, String>只放分別兩組密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	public Map<String, String> checkTheSamePassword(Map<String, String> dataAndErrorsMap, HttpServletRequest request) {
		dataAndErrorsMap.put(ConstMemberKey.Password.key(), request.getParameter(ConstMemberKey.Password.key()));
		dataAndErrorsMap.put(ConstMemberKey.Password_Again.key(),
				request.getParameter(ConstMemberKey.Password_Again.key()));
		String mPassword = dataAndErrorsMap.get(ConstMemberKey.Password.key());
		String mPassword_again = dataAndErrorsMap.get(ConstMemberKey.Password_Again.key());

		if (mPassword == null || !mPassword.equals(mPassword_again)) {
			dataAndErrorsMap.put(ConstMemberKey.Password_Again.key() + ConstMemberParameter._ERROR.param(),
					PASSWORD_AGAIN_ERROR_MESSAGE);
		}
		return dataAndErrorsMap;
	}

	/**
	 * 驗證成功時，封裝所有表單資料至MemberBean並儲存在資料庫會員表格中，其它相關會員表格者另外處理。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	public MemberBean getNewMemberBean(Map<String, String> dataAndErrorsMap, HttpServletRequest request) {
		// 密碼加密
		String mPassword;
		try {
			mPassword = this.secureService.getEncryptedText(dataAndErrorsMap.get(ConstMemberKey.Password.key()),
					ConstSecureParameter.PASSWORD.param());
		} catch (Exception e) {
			throw new RuntimeException();
		}

		// 縣市鄉鎮區路等地址合併
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(request.getParameter(ConstMemberKey.Adrress_County.key()))
				.append(request.getParameter(ConstMemberKey.Adrress_Region.key()))
				.append(request.getParameter(ConstMemberKey.Address.key()));
		String fullAddress = sBuffer.toString();

		// 轉換日期字串為物件
		java.util.Date mBirthday;
		try {
			mBirthday = this.simpleDateFormat.parse(dataAndErrorsMap.get(ConstMemberKey.Birthday.key()));
		} catch (ParseException e) {
			throw new RuntimeException();
		}

		MemberBean mbean = new MemberBean();
		mbean.setmCreateTime(new Timestamp(System.currentTimeMillis()));
		mbean.setmValidEmail("N");
		mbean.setmValidPhone("N");
		mbean.setmValidManager("N");
		mbean.setmKeepLogin("N");
		mbean.setmEmail(dataAndErrorsMap.get(ConstMemberKey.Email.key()));
		mbean.setmPassword(mPassword);
		mbean.setmName(dataAndErrorsMap.get(ConstMemberKey.Name.key()));
		mbean.setmBirthday(mBirthday);
		mbean.setmIdentityCardNumber(dataAndErrorsMap.get(ConstMemberKey.IdentityCardNumber.key()));
		mbean.setmGender(dataAndErrorsMap.get(ConstMemberKey.Gender.key()));
		mbean.setmAddress(fullAddress);
		mbean.setmPhoneNumber(dataAndErrorsMap.get(ConstMemberKey.PhoneNumber.key()));
		mbean.setmHintPassword(dataAndErrorsMap.get(ConstMemberKey.HintAnswer.key()));
		mbean.setmHintAnswer(dataAndErrorsMap.get(ConstMemberKey.HintPassword.key()));
		mbean.setmScores(0);
		mbean.setmPoints(0);
		mbean.setmCareer(0);
		mbean.setmEducation(0);
		mbean.setmEconomy(3);
		mbean.setmMarriage(0);
		mbean.setmFamily(0);
		mbean.setmBloodType(0);
		mbean.setmConstellation(this.getConstellationByBirthday(mbean.getmBirthday()));
		mbean.setmReligion(0);
		mbean.setmSelfIntroduction("");
		return mbean;
	}

	/**
	 * 自動轉換生日對應的星座。
	 * 
	 * 編號0：牡羊座 (3/21~4/20)，編號1：金牛座 (4/21~5/21)，編號2：雙子座 (5/22~6/21)，
	 * 
	 * 編號3：巨蟹座 (6/22~7/23)，編號4：獅子座 (7/24~8/23)，編號5：處女座 (8/24~9/23)，
	 * 
	 * 編號6：天秤座 (9/24~10/23)，編號7：天蠍座 (10/24~11/22)，編號8：射手座 (11/23~12/22)，
	 * 
	 * 編號9：魔羯座 (12/23~1/20)，編號10：水瓶座 (1/21~2/19)，編號11：雙魚座 (2/20~3/20)
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	public Integer getConstellationByBirthday(java.util.Date mBirthday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mBirthday);
		long[] beginConstellationRange = new long[12];
		beginConstellationRange[0] = this.getLongTime(calendar, 3, 21);
		beginConstellationRange[1] = this.getLongTime(calendar, 4, 21);
		beginConstellationRange[2] = this.getLongTime(calendar, 5, 22);
		beginConstellationRange[3] = this.getLongTime(calendar, 6, 22);
		beginConstellationRange[4] = this.getLongTime(calendar, 7, 24);
		beginConstellationRange[5] = this.getLongTime(calendar, 8, 24);
		beginConstellationRange[6] = this.getLongTime(calendar, 9, 24);
		beginConstellationRange[7] = this.getLongTime(calendar, 10, 24);
		beginConstellationRange[8] = this.getLongTime(calendar, 11, 23);
		beginConstellationRange[9] = this.getLongTime(calendar, 12, 23);
		beginConstellationRange[10] = this.getLongTime(calendar, 1, 21);
		beginConstellationRange[11] = this.getLongTime(calendar, 2, 20);

		int count = 0;
		for (int i = 0; i < beginConstellationRange.length; i++) {
			count = i;
			if (i >= 0 && i < 9) {
				if (mBirthday.getTime() >= beginConstellationRange[i]
						&& mBirthday.getTime() < beginConstellationRange[i + 1]) {
					break;
				}
			} else if (i == 9) {
				if (mBirthday.getTime() >= beginConstellationRange[i]
						|| mBirthday.getTime() < beginConstellationRange[i + 1]) {
					break;
				}
			} else if (i == 10) {
				if (mBirthday.getTime() >= beginConstellationRange[i]
						&& mBirthday.getTime() < beginConstellationRange[i + 1]) {
					break;
				}
			} else {
				break;
			}
		}
		return new Integer(count);
	}

	/**
	 * 取得特定年月日的毫秒數。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	public long getLongTime(Calendar calendar, int month, int day) {
		calendar.set(Calendar.MONDAY, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime().getTime();
	}

	/**
	 * 登入系統的驗證：
	 * 
	 * 需先傳入裝有帳號和密碼鍵值的Map物件。驗證的順序依序為帳號是否有誤、存在或密碼一致，
	 * 如果其中有任何錯誤，則在Map物件放入同樣的錯誤資訊(使用者無法得知是帳號還是密碼錯誤)，
	 * 要檢查登入是否成功，只要檢查Map物件有無「login_error」的Key或有無增加一筆鍵值。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Map<String, String> checkLogin(Map<String, String> dataAndErrorsMap) throws Exception {
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Email.key());

		// 檢查帳號是否有誤
		int mapSize = dataAndErrorsMap.size();
		dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
		if (mapSize < dataAndErrorsMap.size()) { // 因為帳號若錯誤會多一筆
			return this.loginError(dataAndErrorsMap);
		}

		// 檢查帳號是否存在
		MemberKeyWordsBean mkwbean = new MemberKeyWordsBean();
		mkwbean.setmEmail(dataAndErrorsMap.get(ConstMemberKey.Email.key()));
		List<MemberBean> list = memberDAO.selectByEmail(mkwbean);
		if (list.size() == 0) {
			return this.loginError(dataAndErrorsMap);
		}

		// 檢查密碼是否與之一致，從資料庫讀出的密碼必須先解密。
		MemberBean mbean = list.get(0);
		if (!dataAndErrorsMap.get(ConstMemberKey.Password.key())
				.equals(secureService.getDecryptedText(mbean.getmPassword(), ConstSecureParameter.PASSWORD.param()))) {
			return this.loginError(dataAndErrorsMap);
		}

		return this.loginSuccess(dataAndErrorsMap);
	}

	/**
	 * 登入系統的驗證：回傳登入失敗的資訊。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	public Map<String, String> loginError(Map<String, String> dataAndErrorsMap) {
		if (dataAndErrorsMap.containsKey(ConstHelperKey.KEY.key())) {
			dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
		}
		if (dataAndErrorsMap.containsKey(ConstMemberKey.Email.key() + ConstMemberParameter._ERROR.param())) {
			dataAndErrorsMap.remove(ConstMemberKey.Email.key() + ConstMemberParameter._ERROR.param());
		}

		// 添加一筆登入失敗的資訊於Map物件。
		dataAndErrorsMap.put(ConstMemberParameter.LOGIN.param() + ConstMemberParameter._ERROR.param(), "帳號或密碼錯誤");
		return dataAndErrorsMap;
	}

	/**
	 * 登入系統的驗證：回傳登入成功的資訊。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	public Map<String, String> loginSuccess(Map<String, String> dataAndErrorsMap) {
		if (dataAndErrorsMap.containsKey(ConstHelperKey.KEY.key())) {
			dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證表單所有的資料，從HttpRequest封裝表單與會員有關的所有資料至Map物件，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	public Map<String, String> encapsulateAndCheckAllData(Map<String, String> dataAndErrorsMap,
			HttpServletRequest request) {
		ConstMemberKey[] memberKeys = ConstMemberKey.values();
		for (int i = 0; i < memberKeys.length; i++) {
			if (memberKeys[i].needToCheckWhenRegistering()) {
				dataAndErrorsMap.put(ConstHelperKey.KEY.key(), memberKeys[i].key());
				dataAndErrorsMap.put(memberKeys[i].key(), request.getParameter(memberKeys[i].key()));
				dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
				dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
			}
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證表單單一的資料，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	public Map<String, String> checkFormData(Map<String, String> dataAndErrorsMap) {
		// 自動找尋要驗證的資料，該Map的鍵「預設」從Map的「"KEY"」對應的Value來得到。
		String currentKey = dataAndErrorsMap.get(ConstHelperKey.KEY.key());
		String currentValue = dataAndErrorsMap.get(currentKey);

		// 自動找尋對應該資料的方法，參數的形態必須依序輸入。
		String firstCharRemovedKey = currentKey.substring(1, currentKey.length());
		Method method;
		try {
			method = MemberService.class.getMethod(new StringBuffer().append(ConstMemberParameter.CHECK.param())
					.append(firstCharRemovedKey).toString(), String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 參數的值必須依序輸入。
		Object returnValue = currentValue;

		// 執行後回傳裝有是否違反限制條件的訊息，限制條件為方法的回傳值必須符合自己規定的條件。
		Set<ConstraintViolation<MemberService>> constraintViolations = this.executableValidator
				.validateReturnValue(this, method, returnValue);

		// 如果有錯誤訊息，自動封裝錯誤到Map，鍵值為原本該資料的Key加上「_error」。
		Iterator<ConstraintViolation<MemberService>> iterator = constraintViolations.iterator();
		while (iterator.hasNext()) {
			dataAndErrorsMap.put(
					new StringBuffer().append(currentKey).append(ConstMemberParameter._ERROR.param()).toString(),
					iterator.next().getMessage());
		}

		return dataAndErrorsMap;
	}

	/**
	 * 協助驗證表單的資料，傳回值會受到對應的Annotation檢驗。
	 * 
	 * 這樣的寫法對於國際化的方面有些寫死，若要國際化可能要在實作Validator的initialize()方法中想辦法取得MessageSource。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	@CheckEmail(column = "信箱")
	public String checkEmail(String email) {
		return email;
	}

	@CheckEmailExist(column = "信箱")
	public String checkEmailExist(String nonexistentEmail) {
		return nonexistentEmail;
	}

	@CheckPassword(column = "密碼")
	public String checkPassword(String password) {
		return password;
	}

	@CheckName(column = "姓名")
	public String checkName(String name) {
		return name;
	}

	@CheckBirthday(column = "生日")
	public String checkBirthday(String birthday) {
		return birthday;
	}

	@CheckIdentityCardNumber(column = "身分證字號")
	public String checkIdentityCardNumber(String identityCardNumber) {
		return identityCardNumber;
	}

	@CheckAddress(column = "地址")
	public String checkAddress(String address) {
		return address;
	}

	@CheckPhoneNumber(column = "手機")
	public String checkPhoneNumber(String phoneNumber) {
		return phoneNumber;
	}

	@CheckHintPassword(column = "密碼提示問題")
	public String checkHintPassword(String hintPassword) {
		return hintPassword;
	}

	@CheckHintAnswer(column = "密碼提示答案")
	public String checkHintAnswer(String hintAnswer) {
		return hintAnswer;
	}

	@CheckSelfIntroduction(column = "自我介紹")
	public String checkSelfIntroduction(String selfIntroduction) {
		return selfIntroduction;
	}
}