package tw.com.eeit94.textile.controller.logs;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，
 * 
 * 方便封裝或得到對應的Bean屬性成員或值。
 * 
 * @author 賴
 * @version 2017/06/12
 */
public enum ConstLogsKey {
	LOGS("logs");

	private final String key;

	private ConstLogsKey(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}
}