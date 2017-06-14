package tw.com.eeit94.textile.system.supervisor;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，方便尋找對應的Bean屬性成員。
 * 
 * @author 賴
 * @version 2017/06/10
 * @see {@link LoginFilter}
 */
public enum ConstManagerList {
	SUPERMANAGER("textile@gmail.com");

	private final String email;

	private ConstManagerList(String email) {
		this.email = email;
	}

	public String key() {
		return this.email;
	}
}