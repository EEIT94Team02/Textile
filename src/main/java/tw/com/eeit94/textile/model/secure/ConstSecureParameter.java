package tw.com.eeit94.textile.model.secure;

/**
 * 定義SecureService需要使用到的常數與對應需要加密、解密的欄位名稱。
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * ！！！！！！不要使用自動排版！！！！！！
 * 
 * @author 賴
 * @version 2017/06/25
 */
public enum ConstSecureParameter {
	TRANSFORMATION("AES/CBC/PKCS5Padding"),
	ALGORITHM("AES"),
	MEMBERID("mId"),
	EMAIL("mEmail"),
	PASSWORD("mPassword"),
	KEEPLOGIN("mKeepLogin"),
	CHATROOMID("cId"),
	ALBUMNO("albumno"),
	PHOTONO("photono");

	private final String param;

	private ConstSecureParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}