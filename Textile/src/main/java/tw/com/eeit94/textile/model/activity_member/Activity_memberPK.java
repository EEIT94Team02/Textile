package tw.com.eeit94.textile.model.activity_member;

import java.io.Serializable;

import javax.persistence.Embeddable;

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
