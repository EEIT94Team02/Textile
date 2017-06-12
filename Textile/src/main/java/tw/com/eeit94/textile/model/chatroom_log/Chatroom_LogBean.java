package tw.com.eeit94.textile.model.chatroom_log;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 封裝聊天室紀錄資料的VO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Entity
@Table(name = "chatroom_log")
public class Chatroom_LogBean implements Serializable {
	private static final long serialVersionUID = -2532937870037606789L;

	@EmbeddedId
	private Chatroom_LogPK chatroom_LogPK;
	private String cContent;

	public Chatroom_LogPK getChatroom_LogPK() {
		return chatroom_LogPK;
	}

	public void setChatroom_LogPK(Chatroom_LogPK chatroom_LogPK) {
		this.chatroom_LogPK = chatroom_LogPK;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("cId", this.getChatroom_LogPK().getcId().toString());
		linkedHashMap.put("mId", this.getChatroom_LogPK().getmId().toString());
		linkedHashMap.put("cSendTime", this.getChatroom_LogPK().getcSendTime().toString());
		linkedHashMap.put("cContent", this.getcContent());
		return linkedHashMap.toString();
	}
}