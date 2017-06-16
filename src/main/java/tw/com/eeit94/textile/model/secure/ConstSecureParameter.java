package tw.com.eeit94.textile.model.secure;

/**
 * 定義SecureService需要使用到的常數與對應需要加密、解密的欄位名稱。
 * 
 * @author 賴
 * @version 2017/06/11
 */
public enum ConstSecureParameter {
	TRANSFORMATION("AES/CBC/PKCS5Padding"), ALGORITHM("AES"), PASSWORD("mPassword"), EMAIL("mEmail"), CHATROOMID("cId");

	private final String param;

	private ConstSecureParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}