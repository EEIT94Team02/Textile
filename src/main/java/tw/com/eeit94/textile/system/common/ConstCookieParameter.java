package tw.com.eeit94.textile.system.common;

import tw.com.eeit94.textile.controller.user.LoginController;

/**
 * 定義LoginController需要使用到的常數。
 * 
 * @author 賴
 * @version 2017/06/13
 * @see {@link LoginController}
 * @see {@link LogoutController}
 */
public enum ConstCookieParameter {
	/*
	 * 604800 = 86400 * 7，單位為秒，即一星期。
	 */
	DOMAIN("localhost"), PATH("/"), VALUEOFRIGHTAWAY("0"), VALUEOFWHENBROWSERCLOSE("-1"), VALUEOFWHENONEWEEKLATER(
			"604800");

	private final String param;

	private ConstCookieParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}