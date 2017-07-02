package tw.com.eeit94.textile.system.common;

import tw.com.eeit94.textile.controller.manager.ManagerController;
import tw.com.eeit94.textile.controller.user.LoginController;
import tw.com.eeit94.textile.controller.user.LogoutController;
import tw.com.eeit94.textile.controller.user.ModifyController;
import tw.com.eeit94.textile.controller.user.ProfileController;
import tw.com.eeit94.textile.controller.user.QueryUserController;
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
 * @see {@link ManagerController}
 * @see {@link LoginController}
 * @see {@link LogoutController}
 * @see {@link RegisterController}
 * @see {@link ProfileController}
 * @see {@link ModifyController}
 * @see {@link QueryUserController}
 */
public enum ConstMapping {
	ERROR_PAGE("/error/404.v"),
	ERROR_PAGE_REDIRECT("error.404.show"),
	FALSE_MAIN_PAGE("/index.jsp"),
	TRUE_MAIN_PAGE("/index.v"),
	REDIRECT_MAIN_PAGE("/index.r"),
	MANAGER_LOGS_SHOW("logs.show"),
	MANAGER_USERS_SHOW("users.show"),
	LOGIN_PAGE("/check/login.v"),
	LOGIN_ERROR("login.error"),
	LOGIN_SUCCESS("login.success"),
	LOGIN_INVALIDEMAIL("login.invalidEmail"),
	LOGIN_EMAILCHECK("/check/emailCheck.do"),
	LOGIN_RE_SENDEMAILCHECK("re_sendEmailCheck.do"),
	LOGIN_EMAILCHECKRE_SEND("login.emailCheckRe_send"),
	LOGIN_EMAILCHECKSUCCESS("login.emailCheckSuccess"),
	LOGIN_FINDPASSWORD_SHOW("findPassword.show"),
	LOGIN_FINDPASSWORD_ERROR("findPassword.error"),
	LOGIN_FINDPASSWORD_SUCCESS("findPassword.success"),
	LOGOUT_SUCCESS("logout.success"),
	REGISTER_ERROR("register.error"),
	REGISTER_SUCCESS("register.success"),
	PROFILE_USER_SHOW("profile.show"),
	PROFILE_OTHERUSER_PAGE("/user/index.v"),
	PROFILE_OTHERUSER_SUCCESS("otherProfile.show"),
	MODIFY_SECURE_SHOW("modifySecure.show"),
	MODIFY_SECURE_ERROR("modifySecure.error"),
	MODIFY_PHONECHECK_SHOW("phoneCheck.show"),
	MODIFY_PHONECHECK_ERROR("phoneCheck.error"),
	MODIFY_PROFILE_SHOW("modifyProfile.show"),
	MODIFY_PROFILE_ERROR("modifyProfile.error"),
	MODIFY_SITUATION_SHOW("modifySituation.show"),
	MODIFY_SITUATION_ERROR("modifySituation.error"),
	MODIFY_INTEREST_SHOW("modifyInterest.show"),
	MODIFY_SUCCESS("modify.success"),
	QUERYNAME_SHOW("/user/queryName.v"),
	QUERYNAME_ERROR("queryName.error"),
	QUERYCONDITION_SHOW("queryCondition.show"),
	QUERYCONDITION_ERROR("queryCondition.error"),
	CHAT("/user/chat.do"),
	CHAT_SHOW("chat.show"),
	MESSAGE_SHOW("/endpoint.do"),
	MESSAGE_IN("/message/in"),
	MESSAGE_OUT("/passage/out");

	private final String path;

	private ConstMapping(String path) {
		this.path = path;
	}

	public String path() {
		return this.path;
	}
}