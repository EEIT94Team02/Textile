package tw.com.eeit94.textile.model.member.util;

/**
 * 定義一對一的MemberBean屬性和Map的鍵值對應。
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * @author 賴
 * @version 2017/06/17
 */
public enum ConstMemberKey {
	Id("mId", false, false),
	CreateTime("mCreateTime", false, false),
	ValidEmail("mValidEmail", false, false),
	ValidPhone("mValidPhone", false, false),
	ValidManager("mValidManager", false, false),
	KeepLogin("mKeepLogin", false, false),
	Email("mEmail", true, false),
	Password("mPassword", true, true),
	Name("mName", true, true),
	Birthday("mBirthday", true, true),
	IdentityCardNumber("mIdentityCardNumber", true,	true),
	Gender("mGender", false, false),
	Address("mAddress", true, true),
	PhoneNumber("mPhoneNumber", true, true),
	HintPassword("mHintPassword", true, true),
	HintAnswer("mHintAnswer", true, true),
	Scores("mScores", false, false),
	Points("mPoints", false, false),
	Career("mCareer", false, false),
	Education("mEducation", false, false),
	Economy("mEconomy", false, false), 
	Marriage("mMarriage", false, false),
	Family("mFamily", false, false),
	BloodType("mBloodType", false, false),
	Constellation("mConstellation", false, false),
	Religion("mReligion", false, false),
	SelfIntroduction("mSelfIntroduction", false, true),
	
	// 特例：需用其它方式驗證
	EmailExist("mEmailExist", false, false),
	Password_Again("mPassword_again", false, false),
	Adrress_County("mAddress_county", false, false),
	Adrress_Region("mAddress_region", false, false);
	

	/**
	 * key: MemberBean的屬性名稱。 
	 * 
	 * isGoingToCheck: 是否為準備要驗證的資料。 
	 * 
	 * isFromUserInput: 是否從使用者輸入的。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	private final String key;
	private final boolean needToCheckWhenRegistering;
	private final boolean isAlwaysNeedToCheck;

	ConstMemberKey(String key, boolean needToCheckWhenRegistering, boolean isAlwaysNeedToCheck) {
		this.key = key;
		this.needToCheckWhenRegistering = needToCheckWhenRegistering;
		this.isAlwaysNeedToCheck = isAlwaysNeedToCheck;
	}

	public String key() {
		return this.key;
	}
	
	public boolean needToCheckWhenRegistering() {
		return this.needToCheckWhenRegistering;
	}

	public boolean isAlwaysNeedToCheck() {
		return this.isAlwaysNeedToCheck;
	}
}