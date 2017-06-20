package tw.com.eeit94.textile.model.chatroom;

/**
 * 定義ChatroomService需要使用到的常數。
 * 
 * @author 賴
 * @version 2017/06/18
 */
public enum ConstChatroomParameter {
	USER("個人"), GROUP("群組");

	private final String param;

	private ConstChatroomParameter(String param) {
		this.param = param;
	}

	public String param() {
		return this.param;
	}
}