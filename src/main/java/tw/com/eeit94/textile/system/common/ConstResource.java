package tw.com.eeit94.textile.system.common;

/**
 * 定義資源與檔名或路徑的對應，需以各個控制元件或MVC組態設定檔做對照。
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * @author 賴
 * @version 2017/06/19
 * @see {@link MailRegisterService}
 */
public enum ConstResource {
	REGISTER_SUCCESS_EMAIL_TEMPLATE("register_success.html");

	private final String path;

	private ConstResource(String path) {
		this.path = path;
	}

	public String path() {
		return this.path;
	}
}