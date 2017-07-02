package tw.com.eeit94.textile.system.supervisor;

import tw.com.eeit94.textile.controller.user.ConstUserKey;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，方便尋找對應的Bean屬性成員。
 * 
 * @author 賴
 * @version 2017/06/10
 * @see {@link LoginFilter}
 * @see {@link ConstUserKey}
 */
public enum ConstFilterKey {
	USER("user"), OTHERUSER("otheruser"), CHATROOM("chatroom"), TARGET("target"), COOKIE_KL("kl"), ExceptionFromServer(
			"exceptionFromServer");

	private final String key;

	private ConstFilterKey(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}
}