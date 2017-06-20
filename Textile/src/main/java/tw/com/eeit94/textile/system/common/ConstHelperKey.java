package tw.com.eeit94.textile.system.common;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，方便尋找對應的Bean屬性成員。
 * 
 * @author 賴
 * @version 2017/06/10
 */
public enum ConstHelperKey {
	KEY("KEY");

	private final String key;

	private ConstHelperKey(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}
}