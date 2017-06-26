package tw.com.eeit94.textile.model.member.service;

import java.util.LinkedHashMap;

/**
 * 找回密碼驗證成功時，需要封裝一些會員資料的JavaBean供JavaMailSender使用。
 * 
 * @author 賴
 * @version 2017/06/26
 */
public class MailFindPasswordModelBean {
	private String mEmail;
	private String mName;
	private String mPassword;

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("mEmail", this.getmEmail().toString());
		linkedHashMap.put("mName", this.getmName().toString());
		linkedHashMap.put("mPassword", this.getmPassword().toString());
		return linkedHashMap.toString();
	}
}