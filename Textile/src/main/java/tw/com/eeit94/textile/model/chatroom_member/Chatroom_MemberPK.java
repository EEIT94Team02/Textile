package tw.com.eeit94.textile.model.chatroom_member;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 定義聊天室明細資料的複合主鍵。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Embeddable
public class Chatroom_MemberPK  implements Serializable{
	private static final long serialVersionUID = -524343907024480231L;
	
	private Long cId;
	private Integer mId;

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
}