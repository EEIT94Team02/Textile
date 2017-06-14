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
 * @version 2017/06/09
 */
public enum ConstMemberKey {
	Id("mId", false, false),
	CreateTime("mCreateTime", false, false),
	EmailValid("mEmailValid", false, false),
	PhoneValid("mPhoneValid", false, false),
	Email("mEmail", true, true),
	Password("mPassword", true, true),
	Name("mName", true, true),
	Birthday("mBirthday", true, true),
	IdentityCardNumber("mIdentityCardNumber", true,	true),
	Gender("mGender", false, true),
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
	SelfIntroduction("mSelfIntroduction", true, true);

	/**
	 * key: MemberBean的屬性名稱。 
	 * 
	 * isGoingToCheck: 是否為準備要驗證的資料。 
	 * 
	 * isFromUserInput: 是否從使用者輸入的。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	private final String key;
	private final boolean isGoingToCheck;
	private final boolean isFromUserInput;

	ConstMemberKey(String key, boolean isGoingToCheck, boolean isFromUserInput) {
		this.key = key;
		this.isGoingToCheck = isGoingToCheck;
		this.isFromUserInput = isFromUserInput;
	}

	public String key() {
		return this.key;
	}

	public boolean isGoingToCheck() {
		return this.isGoingToCheck;
	}

	public boolean isFromUserInput() {
		return this.isFromUserInput;
	}
}