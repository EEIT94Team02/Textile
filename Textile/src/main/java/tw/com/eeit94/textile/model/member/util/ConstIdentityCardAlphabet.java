package tw.com.eeit94.textile.model.member.util;

/**
 * 定義一對一的身分證字號第一個英文字母及其轉換的數字。詳細資料請參考：<a href=
 * "https://zh.wikipedia.org/wiki/%E4%B8%AD%E8%8F%AF%E6%B0%91%E5%9C%8B%E5%9C%8B%E6%B0%91%E8%BA%AB%E5%88%86%E8%AD%89">中華民國國民身分證</a>
 * 
 * @author 賴
 * @version 2017/06/09
 */
public enum ConstIdentityCardAlphabet {
	A("A", 10), B("B", 11), C("C", 12), D("D", 13), E("E", 14), F("F", 15), G("G", 16), H("H", 17), I("I", 34), J("J",
			18), K("K", 19), L("L", 20), M("M", 21), N("N", 22), O("O", 35), P("P", 23), Q("Q", 24), R("R",
					25), S("S", 26), T("T", 27), U("U", 28), V("V", 29), W("W", 32), X("X", 30), Y("Y", 31), Z("Z", 33);

	private final String alphabet;
	private final int number;

	ConstIdentityCardAlphabet(String alphabet, int number) {
		this.alphabet = alphabet;
		this.number = number;
	}

	public String alphabet() {
		return this.alphabet;
	}

	public int number() {
		return this.number;
	}
}