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
	Id("mId", false, false, false),
	CreateTime("mCreateTime", false, false, false),
	ValidEmail("mValidEmail", false, false, false),
	ValidPhone("mValidPhone", false, false, false),
	ValidManager("mValidManager", false, false, false),
	KeepLogin("mKeepLogin", false, false, false),
	Email("mEmail", true, true, false),
	Password("mPassword", true, true, true),
	Name("mName", true, true, true),
	Birthday("mBirthday", true, true, true),
	IdentityCardNumber("mIdentityCardNumber", true, true, true),
	Gender("mGender", true, false, false),
	Address("mAddress", true, true, true),
	PhoneNumber("mPhoneNumber", true, true, true),
	HintPassword("mHintPassword", true, true, true),
	HintAnswer("mHintAnswer", true, true, true),
	Scores("mScores", false, false, false),
	Points("mPoints", false, false, false),
	Career("mCareer", false, false, false),
	Education("mEducation", false, false, false),
	Economy("mEconomy", false, false, false), 
	Marriage("mMarriage", false, false, false),
	Family("mFamily", false, false, false),
	BloodType("mBloodType", false, false, false),
	Constellation("mConstellation", false, false, false),
	Religion("mReligion", false, false, false),
	SelfIntroduction("mSelfIntroduction", false, false, true),
	
	// 特例：需用其它方式驗證
	EmailExist("mEmailExist", true, false, false),
	Password_Again("mPassword_again", true, false, false),
	Adrress_County("mAddress_county", true, false, false),
	Adrress_Region("mAddress_region", true, false, false);
	

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
	private final boolean NeedToCheckAfterRegistering;

	ConstMemberKey(String key, boolean needToBackInMapWhenRegistering, boolean needToCheckWhenRegistering, boolean NeedToCheckAfterRegistering) {
		this.key = key;
		this.needToBackInMapWhenRegistering = needToBackInMapWhenRegistering;
		this.needToCheckWhenRegistering = needToCheckWhenRegistering;
		this.NeedToCheckAfterRegistering = NeedToCheckAfterRegistering;
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

	public boolean NeedToCheckAfterRegistering() {
		return this.NeedToCheckAfterRegistering;
	}
}