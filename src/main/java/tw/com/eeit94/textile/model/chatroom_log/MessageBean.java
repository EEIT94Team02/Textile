package tw.com.eeit94.textile.model.chatroom_log;

import java.util.LinkedHashMap;

/**
 * 定義接收聊天室訊息的JavaBean，Spring會依照JSON物件的Key、Value塞入這個JavaBean。
 * 
 * @author 賴
 * @version 2017/06/28
 */
public class MessageBean {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("message", this.getMessage());
		return linkedHashMap.toString();
	}
}