package tw.com.eeit94.textile.model.member;

import java.lang.reflect.Method;
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
 * @version 2017/06/10
 */
@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private SecureService secureService;
	@Autowired
	private ExecutableValidator executableValidator;

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
		return memberDAO.selectByEmail(mkwbean).get(0);
	}

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
	 * 驗證表單所有的資料，從HttpRequest封裝表單與會員有關的所有資料至Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	public Map<String, String> encapsulateAndCheckAllData(Map<String, String> dataAndErrorsMap,
			HttpServletRequest request) {
		ConstMemberKey[] memberKeys = ConstMemberKey.values();
		for (int i = 0; i < memberKeys.length; i++) {
			if (memberKeys[i].isFromUserInput()) {
				dataAndErrorsMap.put(memberKeys[i].key(), request.getParameter(memberKeys[i].key()));
			}
		}

		return this.checkAllData(dataAndErrorsMap);
	}

	/**
	 * 驗證表單所有的資料，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	public Map<String, String> checkAllData(Map<String, String> dataAndErrorsMap) {
		ConstMemberKey[] memberKeys = ConstMemberKey.values();
		for (int i = 0; i < memberKeys.length; i++) {
			if (memberKeys[i].isGoingToCheck()) {
				dataAndErrorsMap.put(ConstHelperKey.KEY.key(), memberKeys[i].key());
				checkFormData(dataAndErrorsMap);
			}
		}

		dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
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