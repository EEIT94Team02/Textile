package tw.com.eeit94.textile.model.activity_member;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Embeddable
public class Activity_memberPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer activityno;
	private Integer mId;

	public Activity_memberPK() {
	}

	public Activity_memberPK(int activityno, int mId) {
		this.activityno = activityno;
		this.mId = mId;
	}

	public void setActivityno(Integer activityno) {
		this.activityno = activityno;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public Integer getActivityno() {
		return activityno;
	}

	public Integer getmId() {
		return mId;
	}
}