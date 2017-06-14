package tw.com.eeit94.textile.system.common;

import tw.com.eeit94.textile.controller.logs.LogsController;
import tw.com.eeit94.textile.controller.user.LoginController;
import tw.com.eeit94.textile.system.spring.SpringMVCJavaConfiguration;

/**
 * 定義請求路徑和控制元件的對應，需以各個控制元件和MVC組態設定檔做對照。
 * 
 * @author 賴
 * @version 2017/06/10
 * @see {@link SpringMVCJavaConfiguration}
 * @see {@link LoginController}
 * @see {@link LogsController}
 */
public enum ConstMapping {
	LOGIN_ERROR("login.error"), LOGIN_SUCCESS("login.success"), LOGOUT_SUCCESS("logout.success"), LOGS_SUCCESS(
			"logs.success");

	private final String path;

	private ConstMapping(String path) {
		this.path = path;
	}

	public String path() {
		return this.path;
	}
}