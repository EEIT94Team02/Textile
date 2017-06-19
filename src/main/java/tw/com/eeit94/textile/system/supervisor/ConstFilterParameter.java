package tw.com.eeit94.textile.system.supervisor;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，
 * 
 * 方便封裝或得到對應的Bean屬性成員或值。
 * 
 * @author 賴
 * @version 2017/06/10
 * @see {@link LoginFilter}
 */
public enum ConstFilterParameter {
	UTF_8("UTF-8"), IS_MANAGER("Y");

	private final String param;

	private ConstFilterParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}