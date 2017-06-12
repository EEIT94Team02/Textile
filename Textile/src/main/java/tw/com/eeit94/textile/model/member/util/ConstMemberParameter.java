package tw.com.eeit94.textile.model.member.util;

/**
 * 定義MemberService需要使用到的常數。
 * 
 * @author 賴
 * @version 2017/06/11
 */
public enum ConstMemberParameter {
	CHECK("check"), _ERROR("_error"), LOGIN("login");

	private final String param;

	private ConstMemberParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}