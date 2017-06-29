package tw.com.eeit94.textile.model.chatroom;

import java.util.LinkedHashMap;
import java.util.List;

import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogBean;

/**
 * 呈現聊天室頁面資料的VO。
 * 
 * @author 賴
 * @version 2017/06/26
 */
public class ChatViewBean {
	private String chatroomIdentity;
	private String acquaintenceName;
	private String websocketURI;
	private String sendURI;
	private String subscribeURI;
	private String encryptedMId;
	private Integer mId;
	private Integer acquaintenceId;
	private List<Chatroom_LogBean> messageLogs;

	public String getChatroomIdentity() {
		return chatroomIdentity;
	}

	public void setChatroomIdentity(String chatroomIdentity) {
		this.chatroomIdentity = chatroomIdentity;
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

	public String getEncryptedMId() {
		return encryptedMId;
	}

	public void setEncryptedMId(String encryptedMId) {
		this.encryptedMId = encryptedMId;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public Integer getAcquaintenceId() {
		return acquaintenceId;
	}

	public void setAcquaintenceId(Integer acquaintenceId) {
		this.acquaintenceId = acquaintenceId;
	}

	public List<Chatroom_LogBean> getMessageLogs() {
		return messageLogs;
	}

	public void setMessageLogs(List<Chatroom_LogBean> messageLogs) {
		this.messageLogs = messageLogs;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("chatroomIdentity", this.getChatroomIdentity().toString());
		linkedHashMap.put("acquaintenceName", this.getAcquaintenceName().toString());
		linkedHashMap.put("websocketURI", this.getWebsocketURI().toString());
		linkedHashMap.put("sendURI", this.getSendURI().toString());
		linkedHashMap.put("subscribeURI", this.getSubscribeURI().toString());
		linkedHashMap.put("encryptedMId", this.getEncryptedMId());
		linkedHashMap.put("mId", this.getmId().toString());
		linkedHashMap.put("acquaintenceId", this.getAcquaintenceId().toString());
		linkedHashMap.put("messageLogs", this.getMessageLogs().toString());
		return linkedHashMap.toString();
	}
}