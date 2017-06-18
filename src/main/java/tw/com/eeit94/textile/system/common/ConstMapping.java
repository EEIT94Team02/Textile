package tw.com.eeit94.textile.system.common;

import tw.com.eeit94.textile.controller.logs.LogsController;
import tw.com.eeit94.textile.controller.user.LoginController;
import tw.com.eeit94.textile.controller.user.RegisterController;
import tw.com.eeit94.textile.system.spring.SpringMVCJavaConfiguration;

/**
 * 定義請求路徑和控制元件的對應，需以各個控制元件和MVC組態設定檔做對照。
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * @author 賴
 * @version 2017/06/10
 * @see {@link SpringMVCJavaConfiguration}
 * @see {@link LogsController}
 * @see {@link LoginController}
 * @see {@link RegisterController}
 */
public enum ConstMapping {
	ERROR_PAGE("/error/404.v"),
	FALSE_MAIN_PAGE("/index.jsp"),
	TRUE_MAIN_PAGE("/index.v"),
	LOGS_SUCCESS("logs.success"),
	LOGIN_PAGE("/check/login.v"),
	LOGIN_ERROR("login.error"),
	LOGIN_SUCCESS("login.success"),
	LOGOUT_SUCCESS("logout.success"),
	REGISTER_ERROR("register.error"),
	REGISTER_SUCCESS("register.success");

	private final String path;

	private ConstMapping(String path) {
		this.path = path;
	}

	public String path() {
		return this.path;
	}
}