package tw.com.eeit94.textile.controller.user;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，
 * 
 * 方便封裝或得到對應的Bean屬性成員或值。
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * @author 賴
 * @version 2017/06/13
 * @see {@link ConstFilterKey}
 * @see {@link LoginController}
 * @See {@link RegisterController}
 * @see {@link ModifyController}
 * @see {@link QueryUserController}
 */
public enum ConstUserKey {
	KEEPLOGIN("keepLogin"),
	DATAANDERRORSMAP("dataAndErrorsMap"),
	// RegisterController使用
	CHECKURL("checkUrl"),
	EMAILCHECKRE_SENDURL("emailCheckRe_sendUrl"),
	// ModifyController使用
	PHONECHECKCODE("phoneCheckCode"),
	RESULTLIST("resultList"),
	// QueryUserController使用
	mAddress_RegionList("mAddress_RegionList"),
	mInterest_List("mInterest_List"),
	mOtherInterest_List("mOtherInterest_List"),
	mA_RL("mA_RL"),
	mI_L("mI_L"),
	mOI_L("mOI_L"),
	mBirthdayBegin("mBirthdayBegin"),
	mBirthdayEnd("mBirthdayEnd"),
	mCreateTimeBegin("mCreateTimeBegin"),
	mCreateTimeEnd("mCreateTimeEnd"),
	queryCondition("queryCondition");

	private final String key;

	private ConstUserKey(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}
}