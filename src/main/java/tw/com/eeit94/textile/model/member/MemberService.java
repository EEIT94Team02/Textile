package tw.com.eeit94.textile.model.member;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.controller.user.ConstUserKey;
import tw.com.eeit94.textile.controller.user.ConstUserParameter;
import tw.com.eeit94.textile.controller.user.RegisterController;
import tw.com.eeit94.textile.model.member.service.MemberKeyWordsBean;
import tw.com.eeit94.textile.model.member.util.CheckAddress;
import tw.com.eeit94.textile.model.member.util.CheckBirthday;
import tw.com.eeit94.textile.model.member.util.CheckEmail;
import tw.com.eeit94.textile.model.member.util.CheckEmailExist;
import tw.com.eeit94.textile.model.member.util.CheckEmailExistValidator;
import tw.com.eeit94.textile.model.member.util.CheckHintAnswer;
import tw.com.eeit94.textile.model.member.util.CheckHintPassword;
import tw.com.eeit94.textile.model.member.util.CheckIdentityCardNumber;
import tw.com.eeit94.textile.model.member.util.CheckName;
import tw.com.eeit94.textile.model.member.util.CheckPassword;
import tw.com.eeit94.textile.model.member.util.CheckPhoneNumber;
import tw.com.eeit94.textile.model.member.util.CheckSelfIntroduction;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 控制會員基本資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
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
	private static final String OLDPASSWORD_ERROR_MESSAGE = "舊密碼必須與原密碼相同";
	private static final String NEWPASSWORD_ERROR_MESSAGE = "新密碼必須與原密碼不同";
	private static final String NewPASSWORD_AGAIN_ERROR_MESSAGE = "新密碼不一致";
	private static final Integer System_mId = new Integer(1);

	/**
	 * 特殊查詢：搜索全部會員。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	@Transactional(readOnly = true)
	public List<MemberBean> selectAll() {
		return this.memberDAO.selectAll();
	}

	/**
	 * 特殊查詢：利用primary key搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 */
	@Transactional(readOnly = true)
	public MemberBean selectByPrimaryKey(Integer mId) {
		MemberBean mbean = new MemberBean();
		mbean.setmId(mId);
		List<MemberBean> list = this.memberDAO.select(mbean);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
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
		List<MemberBean> list = this.memberDAO.selectByEmail(mkwbean);
		if (list.size() > 0) {
			mbean = list.get(0);
		}
		return mbean;
	}

	/**
	 * 特殊查詢：利用完全相同的姓名來搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 */
	@Transactional(readOnly = true)
	public List<MemberBean> selectByName(String mName) {
		MemberKeyWordsBean mkwbean = new MemberKeyWordsBean();
		mkwbean.setmName(mName);
		List<MemberBean> list = this.memberDAO.selectByName(mkwbean);
		return list;
	}

	/**
	 * 特殊查詢：利用相似的姓名來搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 */
	@Transactional(readOnly = true)
	public List<MemberBean> selectBySimilarName(String mName) {
		MemberKeyWordsBean mkwbean = new MemberKeyWordsBean();
		mkwbean.setmName(mName);
		List<MemberBean> list = this.memberDAO.selectBySimilarName(mkwbean);
		return list;
	}

	/**
	 * 特殊查詢：利用條件來搜尋，由於結果可能很多，以及興趣另外比對，所以交易必須統一管理。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 */
	public List<MemberBean> selectBySimilarName(MemberKeyWordsBean mkwbean) {
		List<MemberBean> list = this.memberDAO.selectByKeyWords(mkwbean);
		return list;
	}

	/**
	 * 特殊查詢：這裡只查詢基本資料、論壇經歷、個人狀況，有關個人喜好的查詢會在回傳後List&lt;MemberBean&gt;，
	 * 逐一利用位元比對。因為要特製化查詢，MemberBean的屬性成員不敷使用，因此使用新創的MemberKeyWordsBean。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	@Transactional(readOnly = true)
	public List<MemberBean> selectByKeyWords(MemberKeyWordsBean mkwbean) {
		List<MemberBean> list = this.memberDAO.selectByKeyWords(mkwbean);
		return list;
	}

	/**
	 * 特殊查詢：搜尋會員總數。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	@Transactional(readOnly = true)
	public Long selectMemberCount() {
		return this.memberDAO.selectMemberCount();
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
	 * 修改會員的密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@Transactional
	public void updateSecurity(Map<String, String> dataAndErrorsMap, HttpServletRequest request) throws Exception {
		MemberBean mbean = (MemberBean) request.getSession().getAttribute(ConstFilterKey.USER.key());
		String newPassword = dataAndErrorsMap.get(ConstMemberKey.NewPassword.key());
		String encryptedNewPassword = this.secureService.getEncryptedText(newPassword,
				ConstSecureParameter.PASSWORD.param());
		mbean.setmPassword(encryptedNewPassword);
		this.memberDAO.update(mbean);
	}

	/**
	 * 修改會員的個人資料，修改手機時必須要將手機已驗證的參數調回未驗證。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@Transactional
	public void updateProfile(Map<String, String> dataAndErrorsMap, HttpServletRequest request) throws Exception {
		java.util.Date mBirthday;
		try {
			mBirthday = this.simpleDateFormat.parse(dataAndErrorsMap.get(ConstMemberKey.Birthday.key()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		MemberBean mbean = (MemberBean) request.getSession().getAttribute(ConstFilterKey.USER.key());

		// 如果修改手機，則亦改變手機已驗證的參數。
		if (!mbean.getmPhoneNumber().equals(dataAndErrorsMap.get(ConstMemberKey.PhoneNumber.key()))) {
			mbean.setmValidPhone(ConstUserParameter.VALIDPHONE_NO.param());
		}

		mbean.setmName(dataAndErrorsMap.get(ConstMemberKey.Name.key()));
		mbean.setmIdentityCardNumber(dataAndErrorsMap.get(ConstMemberKey.IdentityCardNumber.key()));
		mbean.setmBirthday(mBirthday);
		mbean.setmPhoneNumber(dataAndErrorsMap.get(ConstMemberKey.PhoneNumber.key()));
		mbean.setmGender(dataAndErrorsMap.get(ConstMemberKey.Gender.key()));
		mbean.setmAddress_County(dataAndErrorsMap.get(ConstMemberKey.Adrress_County.key()));
		mbean.setmAddress_Region(dataAndErrorsMap.get(ConstMemberKey.Adrress_Region.key()));
		mbean.setmAddress(dataAndErrorsMap.get(ConstMemberKey.Address.key()));
		mbean.setmHintPassword(dataAndErrorsMap.get(ConstMemberKey.HintPassword.key()));
		mbean.setmHintAnswer(dataAndErrorsMap.get(ConstMemberKey.HintAnswer.key()));

		// 生日改變，也應該改變星座。
		mbean.setmConstellation(this.getConstellationByBirthday(mbean.getmBirthday()));
		this.memberDAO.update(mbean);
	}

	/**
	 * 修改會員的個人狀況，星座在表單被disable不會有值。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@Transactional
	public void updateSituation(Map<String, String> dataAndErrorsMap, HttpServletRequest request) throws Exception {
		MemberBean mbean = (MemberBean) request.getSession().getAttribute(ConstFilterKey.USER.key());
		mbean.setmCareer(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.Career.key())));
		mbean.setmEducation(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.Education.key())));
		mbean.setmEconomy(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.Economy.key())));
		mbean.setmMarriage(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.Marriage.key())));
		mbean.setmFamily(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.Family.key())));
		mbean.setmBloodType(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.BloodType.key())));
		mbean.setmReligion(Integer.parseInt(dataAndErrorsMap.get(ConstMemberKey.Religion.key())));
		mbean.setmSelfIntroduction(dataAndErrorsMap.get(ConstMemberKey.SelfIntroduction.key()));
		this.memberDAO.update(mbean);
	}

	/**
	 * 得到會員主頁的連結。
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 * @throws Exception
	 */
	public String getOtherProfileUrl(MemberBean mbean, HttpServletRequest request) throws Exception {
		String encryptedMId = this.secureService.getEncryptedText(mbean.getmId().toString(),
				ConstSecureParameter.MEMBERID.param());
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(request.getContextPath()).append(ConstMapping.PROFILE_OTHERUSER_PAGE.path())
				.append(ConstHelperKey.QUESTION.key()).append(ConstHelperKey.QUERY_EQUAL.key()).append(encryptedMId);
		return sBuffer.toString();
	}

	/**
	 * 查詢會員「隨機搜尋」的最後步驟之一：去除名為「系統」的會員。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public MemberBean getRidOfSystemMBean(MemberBean mbean) {
		List<MemberBean> memberList = new ArrayList<>();
		memberList.add(mbean);
		memberList = this.getRidOfSystemMBean(memberList);
		if (memberList.size() > 0) {
			return memberList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查詢會員「條件搜尋」的最後步驟之一：去除名為「系統」的會員。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public List<MemberBean> getRidOfSystemMBean(List<MemberBean> memberList) {
		List<MemberBean> list = new ArrayList<>();
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getmId().intValue() != System_mId.intValue()) {
				list.add(memberList.get(i));
			}
		}

		return list;
	}

	/**
	 * 查詢會員「隨機搜尋」：完全隨機篩選出一名會員。
	 * 
	 * 注意：要篩選掉自己。
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 */
	public MemberBean getQueryRandomResult(MemberBean usermBean) {
		Long memberCount = this.selectMemberCount();
		MemberBean mbean = null;
		boolean isResultUserSelf = true;

		while (isResultUserSelf) {
			Integer randomIndex = (int) (Math.random() * memberCount.intValue());
			mbean = this.selectByPrimaryKey(randomIndex);
			if (mbean != null) {
				if (usermBean.getmId().intValue() != mbean.getmId().intValue()) {
					isResultUserSelf = false;
				}
			}
		}

		return mbean;
	}

	/**
	 * 查詢會員「條件搜尋」的最後步驟之一：隨機篩選出一名會員。
	 * 
	 * 注意：要篩選掉自己。
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 */
	public MemberBean getQueryConditionResult(List<MemberBean> memberList, MemberBean usermBean) {
		MemberBean mbean = null;
		boolean isResultUserSelf = true;
		while (isResultUserSelf) {
			int randomIndex = (int) (Math.random() * memberList.size());
			if (memberList.size() > 0) {
				mbean = memberList.get(randomIndex);
				if (usermBean.getmId().intValue() != mbean.getmId().intValue()) {
					isResultUserSelf = false;
				}
				if (memberList.size() == 1) {
					mbean = null;
					isResultUserSelf = false;
				}
			} else {
				isResultUserSelf = false;
			}
		}

		return mbean;
	}

	/**
	 * 查詢會員「條件搜尋」時，將興趣除外的個人資料封裝在MemberKeyWordsBean。
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 * @throws ParseException
	 * @see {@link MemberKeyWordsBean}
	 */
	@SuppressWarnings("unchecked")
	public MemberKeyWordsBean setMemberKeyWordsBean(MemberKeyWordsBean mkwbean, HttpServletRequest request)
			throws ParseException {
		String mGender = request.getParameter(ConstMemberKey.Gender.key());
		mkwbean.setmGender((mGender == "" ? null : mGender));
		java.util.Date mBirthdayBegin = this.simpleDateFormat
				.parse(request.getParameter(ConstUserKey.mBirthdayBegin.key()));
		mkwbean.setmBirthdayBegin(mBirthdayBegin);
		java.util.Date mBirthdayEnd = this.simpleDateFormat
				.parse(request.getParameter(ConstUserKey.mBirthdayEnd.key()));
		mkwbean.setmBirthdayEnd(mBirthdayEnd);

		// 如果縣市不選擇「皆可」的話才封裝地址區域清單。
		String mAddress_County = request.getParameter(ConstMemberKey.Adrress_County.key());
		if (mAddress_County.equals(ConstUserParameter.mAddress_County_Default.param())) {
			mAddress_County = null;
		}
		mkwbean.setmAddress_County(mAddress_County);

		List<String> mAddress_Region = (List<String>) request.getAttribute(ConstUserKey.mAddress_RegionList.key());
		if (mAddress_County == null || mAddress_Region.size() == 0) {
			mAddress_Region = null;
		}
		mkwbean.setmAddress_Region(mAddress_Region);

		Integer mScores = Integer.parseInt(request.getParameter(ConstMemberKey.Scores.key()));
		mkwbean.setmScores(mScores);
		java.util.Date mCreateTimeBegin = this.simpleDateFormat
				.parse(request.getParameter(ConstUserKey.mCreateTimeBegin.key()));
		mkwbean.setmCreateTimeBegin(new Timestamp(mCreateTimeBegin.getTime()));
		java.util.Date mCreateTimeEnd = this.simpleDateFormat
				.parse(request.getParameter(ConstUserKey.mCreateTimeEnd.key()));
		mkwbean.setmCreateTimeEnd(new Timestamp(mCreateTimeEnd.getTime()));

		Integer mCareer = Integer.parseInt(request.getParameter(ConstMemberKey.Career.key()));
		mkwbean.setmCareer((mCareer == -1 ? null : mCareer));
		Integer mEducation = Integer.parseInt(request.getParameter(ConstMemberKey.Education.key()));
		mkwbean.setmEducation((mEducation == -1 ? null : mEducation));
		Integer mEconomy = Integer.parseInt(request.getParameter(ConstMemberKey.Economy.key()));
		mkwbean.setmEconomy((mEconomy == -1 ? null : mEconomy));
		Integer mMarriage = Integer.parseInt(request.getParameter(ConstMemberKey.Marriage.key()));
		mkwbean.setmMarriage((mMarriage == -1 ? null : mMarriage));
		Integer mFamily = Integer.parseInt(request.getParameter(ConstMemberKey.Family.key()));
		mkwbean.setmFamily((mFamily == -1 ? null : mFamily));
		Integer mBloodType = Integer.parseInt(request.getParameter(ConstMemberKey.BloodType.key()));
		mkwbean.setmBloodType((mBloodType == -1 ? null : mBloodType));
		Integer mConstellation = Integer.parseInt(request.getParameter(ConstMemberKey.Constellation.key()));
		mkwbean.setmConstellation((mConstellation == -1 ? null : mConstellation));
		Integer mReligion = Integer.parseInt(request.getParameter(ConstMemberKey.Religion.key()));
		mkwbean.setmReligion((mReligion == -1 ? null : mReligion));
		return mkwbean;
	}

	/**
	 * 驗證成功時，封裝所有表單資料至MemberBean並儲存在資料庫會員表格中，其它相關會員表格者另外處理，
	 * 交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 * @see {@link RegisterController}
	 */
	public MemberBean getNewMemberBean(Map<String, String> dataAndErrorsMap, HttpServletRequest request) {
		// 密碼加密
		String mPassword;
		try {
			mPassword = this.secureService.getEncryptedText(dataAndErrorsMap.get(ConstMemberKey.Password.key()),
					ConstSecureParameter.PASSWORD.param());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 轉換日期字串為物件
		java.util.Date mBirthday;
		try {
			mBirthday = this.simpleDateFormat.parse(dataAndErrorsMap.get(ConstMemberKey.Birthday.key()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
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
		mbean.setmAddress_County(dataAndErrorsMap.get(ConstMemberKey.Adrress_County.key()));
		mbean.setmAddress_Region(dataAndErrorsMap.get(ConstMemberKey.Adrress_Region.key()));
		mbean.setmAddress(dataAndErrorsMap.get(ConstMemberKey.Address.key()));
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
		return this.memberDAO.insert(mbean).get(0);
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
		if (!dataAndErrorsMap.get(ConstMemberKey.Password.key()).equals(
				this.secureService.getDecryptedText(mbean.getmPassword(), ConstSecureParameter.PASSWORD.param()))) {
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
	 * 從HttpRequest封裝表單與會員「條件查詢」有關的所有資料至Map物件，其中地址、興趣、其它興趣需分開處理。
	 * 
	 * (這裡有很多寫死的地方)
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 */
	public void encapsulateAllWhenQueryingCondition(Map<String, String> dataAndErrorsMap, HttpServletRequest request) {
		List<String> mAddress_RegionList = new ArrayList<>();
		List<String> mInterest_List = new ArrayList<>();
		List<String> mOtherInterest_List = new ArrayList<>();
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		request.setAttribute(ConstUserKey.mAddress_RegionList.key(), mAddress_RegionList);
		request.setAttribute(ConstUserKey.mInterest_List.key(), mInterest_List);
		request.setAttribute(ConstUserKey.mOtherInterest_List.key(), mOtherInterest_List);

		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			if (key.startsWith(ConstUserKey.mA_RL.key())) {
				mAddress_RegionList.add(request.getParameter(key));
			} else if (key.startsWith(ConstUserKey.mI_L.key())) {
				mInterest_List.add(request.getParameter(key));
			} else if (key.startsWith(ConstUserKey.mOI_L.key())) {
				mOtherInterest_List.add(request.getParameter(key));
			} else {
				dataAndErrorsMap.put(key, request.getParameter(key));
			}
		}
	}

	/**
	 * 驗證表單所有的資料，從HttpRequest封裝表單與會員有關的所有資料至Map物件，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public Map<String, String> encapsulateAndCheckAllDataWhenModifying(Map<String, String> dataAndErrorsMap,
			HttpServletRequest request) {
		ConstMemberKey[] memberKeys = ConstMemberKey.values();
		for (int i = 0; i < memberKeys.length; i++) {
			if (memberKeys[i].needToBackInMapWhenModifying() && request.getParameter(memberKeys[i].key()) != null) {
				dataAndErrorsMap.put(memberKeys[i].key(), request.getParameter(memberKeys[i].key()));
				if (memberKeys[i].needToCheckWhenModifying()) {
					dataAndErrorsMap.put(ConstHelperKey.KEY.key(), memberKeys[i].key());
					dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
					dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
				}
			}
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證表單所有的資料，從HttpRequest封裝表單與會員有關的所有資料至Map物件，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public Map<String, String> encapsulateAndCheckAllDataWhenRegistering(Map<String, String> dataAndErrorsMap,
			HttpServletRequest request) {
		ConstMemberKey[] memberKeys = ConstMemberKey.values();
		for (int i = 0; i < memberKeys.length; i++) {
			if (memberKeys[i].needToBackInMapWhenRegistering()) {
				dataAndErrorsMap.put(memberKeys[i].key(), request.getParameter(memberKeys[i].key()));
				if (memberKeys[i].needToCheckWhenRegistering()) {
					dataAndErrorsMap.put(ConstHelperKey.KEY.key(), memberKeys[i].key());
					dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
					dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
				}
			}
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證表單單一的資料，從HttpRequest封裝表單與會員有關的所有資料至Map物件，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * request參數中的「m」(METHOD)就是屬性名稱，「q」(QUERY)就是該屬性的值。(AJAX專用)
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public Map<String, String> encapsulateAndCheckOneData(Map<String, String> dataAndErrorsMap,
			HttpServletRequest request) {
		String mField = request.getParameter(ConstHelperKey.METHOD.key());
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), mField);
		dataAndErrorsMap.put(mField, request.getParameter(ConstHelperKey.QUERY.key()));
		dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
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
	 * 這樣的寫法對於國際化的方面有些寫死，若要國際化要在實作Validator的initialize()方法中想辦法取得MessageSource，
	 * 
	 * SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this)
	 * do this magic.
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 * @see {@link CheckEmailExistValidator}
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
		String mPassword_Again = dataAndErrorsMap.get(ConstMemberKey.Password_Again.key());

		if (mPassword == null || !mPassword.equals(mPassword_Again)) {
			dataAndErrorsMap.put(ConstMemberKey.Password_Again.key() + ConstMemberParameter._ERROR.param(),
					PASSWORD_AGAIN_ERROR_MESSAGE);
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證舊密碼是否和現在的密碼相同。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws Exception
	 */
	@Transactional
	public Map<String, String> checkOldPassword(Map<String, String> dataAndErrorsMap, HttpServletRequest request)
			throws Exception {
		String mOldPassword = dataAndErrorsMap.get(ConstMemberKey.OldPassword.key());
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		String mPassword = this.secureService.getDecryptedText(mbean.getmPassword(),
				ConstSecureParameter.PASSWORD.param());
		if (!mOldPassword.equals(mPassword)) {
			dataAndErrorsMap.put(ConstMemberKey.OldPassword.key() + ConstMemberParameter._ERROR.param(),
					OLDPASSWORD_ERROR_MESSAGE);
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證新密碼是否符合規定，且不可和原密碼一樣。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws Exception
	 */
	@Transactional
	public Map<String, String> checkNewPassword(Map<String, String> dataAndErrorsMap, HttpServletRequest request)
			throws Exception {
		String mNewPassword = dataAndErrorsMap.get(ConstMemberKey.NewPassword.key());
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		String mPassword = this.secureService.getDecryptedText(mbean.getmPassword(),
				ConstSecureParameter.PASSWORD.param());
		// 先驗證是否和原密碼一樣，如果一樣則傳回錯誤。
		if (mPassword.equals(mNewPassword)) {
			dataAndErrorsMap.put(ConstMemberKey.NewPassword.key() + ConstMemberParameter._ERROR.param(),
					NEWPASSWORD_ERROR_MESSAGE);
			return dataAndErrorsMap;
		}

		// 再用原本的驗證方式驗證密碼是否符合規定，因為用原本的驗證器(@CheckPassword)，所以方法得用「mPassword」。
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Password.key());
		dataAndErrorsMap.put(ConstMemberKey.Password.key(), mNewPassword);
		dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
		if (dataAndErrorsMap.containsKey(ConstMemberKey.Password.key() + ConstMemberParameter._ERROR.param())) {
			String output = dataAndErrorsMap.get(ConstMemberKey.Password.key() + ConstMemberParameter._ERROR.param());
			dataAndErrorsMap.remove(ConstMemberKey.Password.key() + ConstMemberParameter._ERROR.param());
			dataAndErrorsMap.put(ConstMemberKey.NewPassword.key() + ConstMemberParameter._ERROR.param(), output);
			return dataAndErrorsMap;
		}

		return dataAndErrorsMap;
	}

	/**
	 * 驗證新密碼是否符合規定，且不可和原密碼一樣。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws Exception
	 */
	public Map<String, String> checkNewPassword_Again(Map<String, String> dataAndErrorsMap, HttpServletRequest request)
			throws Exception {
		String mNewPassword = dataAndErrorsMap.get(ConstMemberKey.NewPassword.key());
		String mNewPassword_Again = dataAndErrorsMap.get(ConstMemberKey.NewPassword_Again.key());

		if (mNewPassword == null || !mNewPassword.equals(mNewPassword_Again)) {
			dataAndErrorsMap.put(ConstMemberKey.NewPassword_Again.key() + ConstMemberParameter._ERROR.param(),
					NewPASSWORD_AGAIN_ERROR_MESSAGE);
		}

		return dataAndErrorsMap;
	}
}