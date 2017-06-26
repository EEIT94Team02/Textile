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
 * @version 2017/06/18
 */
public enum ConstMemberKey {
	Id("mId", false, false, false, false),
	CreateTime("mCreateTime", false, false, false, false),
	ValidEmail("mValidEmail", false, false, false, false),
	ValidPhone("mValidPhone", false, false, false, false),
	ValidManager("mValidManager", false, false, false, false),
	KeepLogin("mKeepLogin", false, false, false, false),
	Email("mEmail", true, true, false, false),
	Password("mPassword", true, true, false, false), // 改密碼用其它的Key
	Name("mName", true, true, true, true),
	Birthday("mBirthday", true, true, true, true),
	IdentityCardNumber("mIdentityCardNumber", true, true, true, true),
	Gender("mGender", true, false, true, false),
	Adrress_County("mAddress_County", true, false, true, false),
	Adrress_Region("mAddress_Region", true, false, true, false),
	Address("mAddress", true, true, true, true),
	PhoneNumber("mPhoneNumber", true, true, true, true),
	HintPassword("mHintPassword", true, true, true, true),
	HintAnswer("mHintAnswer", true, true, true, true),
	Scores("mScores", false, false, false, false),
	Points("mPoints", false, false, false, false),
	Career("mCareer", false, false, true, false),
	Education("mEducation", false, false, true, false),
	Economy("mEconomy", false, false, true, false), 
	Marriage("mMarriage", false, false, true, false),
	Family("mFamily", false, false, true, false),
	BloodType("mBloodType", false, false, true, false),
	Constellation("mConstellation", false, false, false, false), // 這格在表單被disable，不會有值。
	Religion("mReligion", false, false, true, false),
	SelfIntroduction("mSelfIntroduction", false, false, true, true),
	
	// 特例：註冊時，驗證障
	EmailExist("mEmailExist", true, false, false, false),
	Password_Again("mPassword_again", true, false, false, false),
	OldPassword("mOldPassword", false, false, true, true),
	NewPassword("mNewPassword", false, false, true, true),
	NewPassword_Again("mNewPassword_Again", false, false, true, true);

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
	private final boolean needToBackInMapWhenRegistering;
	private final boolean needToCheckWhenRegistering;
	private final boolean needToBackInMapWhenModifying;
	private final boolean needToCheckWhenModifying;

	ConstMemberKey(String key, boolean needToBackInMapWhenRegistering, boolean needToCheckWhenRegistering,boolean needToBackInMapWhenModifying, boolean needToCheckWhenModifying) {
		this.key = key;
		this.needToBackInMapWhenRegistering = needToBackInMapWhenRegistering;
		this.needToCheckWhenRegistering = needToCheckWhenRegistering;
		this.needToBackInMapWhenModifying = needToBackInMapWhenModifying;
		this.needToCheckWhenModifying = needToCheckWhenModifying;
	}

	public String key() {
		return this.key;
	}
	
	public boolean needToBackInMapWhenRegistering() {
		return this.needToBackInMapWhenRegistering;
	}
	
	public boolean needToCheckWhenRegistering() {
		return this.needToCheckWhenRegistering;
	}
	
	public boolean needToBackInMapWhenModifying() {
		return this.needToBackInMapWhenModifying;
	}

	public boolean needToCheckWhenModifying() {
		return this.needToCheckWhenModifying;
	}
}