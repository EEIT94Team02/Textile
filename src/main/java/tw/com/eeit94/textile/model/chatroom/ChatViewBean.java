package tw.com.eeit94.textile.model.chatroom;

import java.util.LinkedHashMap;

/**
 * 呈現聊天室頁面資料的VO。
 * 
 * @author 賴
 * @version 2017/06/26
 */
public class ChatViewBean {
	private String cId;
	private String acquaintenceName;
	private String websocketURI;
	private String sendURI;
	private String subscribeURI;

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getAcquaintenceName() {
		return acquaintenceName;
	}

	public void setAcquaintenceName(String acquaintenceName) {
		this.acquaintenceName = acquaintenceName;
	}

	public String getWebsocketURI() {
		return websocketURI;
	}

	public void setWebsocketURI(String websocketURI) {
		this.websocketURI = websocketURI;
	}

	public String getSendURI() {
		return sendURI;
	}

	public void setSendURI(String sendURI) {
		this.sendURI = sendURI;
	}

	public String getSubscribeURI() {
		return subscribeURI;
	}

	public void setSubscribeURI(String subscribeURI) {
		this.subscribeURI = subscribeURI;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("cId", this.getcId().toString());
		linkedHashMap.put("acquaintenceName", this.getAcquaintenceName().toString());
		linkedHashMap.put("websocketURI", this.getWebsocketURI().toString());
		linkedHashMap.put("sendURI", this.getSendURI().toString());
		linkedHashMap.put("subscribeURI", this.getSubscribeURI().toString());
		return linkedHashMap.toString();
	}
}