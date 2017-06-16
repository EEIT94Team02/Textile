package tw.com.eeit94.textile.controller.user;

/**
 * 定義LoginController需要使用到的常數。
 * 
 * @author 賴
 * @version 2017/06/13
 */
public enum ConstLoginParameter {
	KEEPLOGIN("1"), KEEPLOGIN_YES("Y"), KEEPLOGIN_NO("N");

	private final String param;

	private ConstLoginParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}