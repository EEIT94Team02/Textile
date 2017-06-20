package tw.com.eeit94.textile.model.member.service;

import java.util.LinkedHashMap;

/**
 * 註冊成功時，需要封裝一些會員資料的JavaBean供JavaMailSender使用。
 * 
 * @author 賴
 * @version 2017/06/19
 */
public class MailRegisterModelBean {
	private String mEmail;
	private String mName;
	private String checkUrl;

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

	public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("mEmail", this.getmEmail().toString());
		linkedHashMap.put("mName", this.getmName().toString());
		linkedHashMap.put("checkUrl", this.getCheckUrl().toString());
		return linkedHashMap.toString();
	}
}