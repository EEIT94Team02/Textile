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

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("cId", this.getcId().toString());
		linkedHashMap.put("acquaintenceName", this.getAcquaintenceName().toString());
		return linkedHashMap.toString();
	}
}