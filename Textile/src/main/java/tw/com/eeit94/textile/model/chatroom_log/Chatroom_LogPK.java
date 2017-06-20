package tw.com.eeit94.textile.model.chatroom_log;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 定義聊天室紀錄資料的複合主鍵。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Embeddable
public class Chatroom_LogPK  implements Serializable{
	private static final long serialVersionUID = -1554180262793004491L;
	
	private Long cId;
	private Integer mId;
	private java.sql.Timestamp cSendTime;

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public java.sql.Timestamp getcSendTime() {
		return cSendTime;
	}

	public void setcSendTime(java.sql.Timestamp cSendTime) {
		this.cSendTime = cSendTime;
	}
}