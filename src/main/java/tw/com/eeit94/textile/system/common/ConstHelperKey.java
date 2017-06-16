package tw.com.eeit94.textile.system.common;

import tw.com.eeit94.textile.model.member.MemberService;

/**
 * 定義輔助搜尋request.getParameter()和Map<String, String>參數的Key常數，
 * 
 * 方便封裝或得到對應的Bean屬性成員或值。
 * 
 * 這些常數專門用在AJAX技術所對應的Controller居多。
 * 
 * @author 賴
 * @version 2017/06/10
 * @see {@link MemberService}
 * @see {@link InspectController}
 */
public enum ConstHelperKey {
	KEY("KEY"), SPACE(""), QUERY("q"), METHOD("m");

	private final String key;

	private ConstHelperKey(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}
}