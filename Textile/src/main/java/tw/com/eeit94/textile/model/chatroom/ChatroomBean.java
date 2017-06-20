package tw.com.eeit94.textile.model.chatroom;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogBean;
import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberBean;

/**
 * 封裝聊天室資料的VO，子表為聊天室明細資料、聊天室紀錄資料。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Entity
@Table(name = "chatroom")
public class ChatroomBean implements Serializable {
	private static final long serialVersionUID = 418458345348339768L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cId;
	private java.sql.Timestamp cCreateTime;
	private String cClass;
	@OneToMany(cascade = {
			CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "chatroom_MemberPK.cId", targetEntity = Chatroom_MemberBean.class)
	private List<Chatroom_MemberBean> chatroom_MemberBean;
	@OneToMany(cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "chatroom_LogPK.cId", targetEntity = Chatroom_LogBean.class)
	private List<Chatroom_LogBean> chatroom_LogBean;

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public java.sql.Timestamp getcCreateTime() {
		return cCreateTime;
	}

	public void setcCreateTime(java.sql.Timestamp cCreateTime) {
		this.cCreateTime = cCreateTime;
	}

	public String getcClass() {
		return cClass;
	}

	public void setcClass(String cClass) {
		this.cClass = cClass;
	}

	public List<Chatroom_MemberBean> getChatroom_MemberBean() {
		return chatroom_MemberBean;
	}

	public void setChatroom_MemberBean(List<Chatroom_MemberBean> chatroom_MemberBean) {
		this.chatroom_MemberBean = chatroom_MemberBean;
	}

	public List<Chatroom_LogBean> getChatroom_LogBean() {
		return chatroom_LogBean;
	}

	public void setChatroom_LogBean(List<Chatroom_LogBean> chatroom_LogBean) {
		this.chatroom_LogBean = chatroom_LogBean;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("cId", this.getcId().toString());
		linkedHashMap.put("cCreateTime", this.getcCreateTime().toString());
		linkedHashMap.put("cClass", this.getcClass());
		linkedHashMap.put("chatroom_MemberBean",
				this.chatroom_MemberBean != null ? this.chatroom_MemberBean.toString() : null);
		linkedHashMap.put("chatroom_LogBean", this.chatroom_LogBean != null ? this.chatroom_LogBean.toString() : null);
		return linkedHashMap.toString();
	}
}