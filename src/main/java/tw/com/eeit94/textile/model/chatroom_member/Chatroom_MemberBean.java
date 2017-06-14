package tw.com.eeit94.textile.model.chatroom_member;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 封裝聊天室明細資料的VO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Entity
@Table(name = "chatroom_member")
public class Chatroom_MemberBean implements Serializable {
	private static final long serialVersionUID = -1844689536601195786L;
	
	@EmbeddedId
	private Chatroom_MemberPK chatroom_MemberPK;

	public Chatroom_MemberPK getChatroom_MemberPK() {
		return chatroom_MemberPK;
	}

	public void setChatroom_MemberPK(Chatroom_MemberPK chatroom_MemberPK) {
		this.chatroom_MemberPK = chatroom_MemberPK;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("cId", this.chatroom_MemberPK.getcId().toString());
		linkedHashMap.put("mId", this.chatroom_MemberPK.getmId().toString());
		return linkedHashMap.toString();
	}
}