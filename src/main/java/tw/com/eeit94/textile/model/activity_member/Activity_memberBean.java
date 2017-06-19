package tw.com.eeit94.textile.model.activity_member;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Entity
@Table(name = "activity_member")
@Component
public class Activity_memberBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "{" + activity_memberPK.getActivityno() + "," + activity_memberPK.getmId() + "," + getPosition() + ","
				+ activityBean + memberBean+"}";
	}

	@EmbeddedId
	private Activity_memberPK activity_memberPK;
	public Activity_memberPK getActivity_memberPK() {
		return activity_memberPK;	}
	public void setActivity_memberPK(Activity_memberPK activity_memberPK) {
		this.activity_memberPK = activity_memberPK;
	}
	

	@MapsId(value = "activityno")
	@JoinColumn(name = "activityno")
	@OneToOne(fetch = FetchType.EAGER)
	private ActivityBean activityBean;
	public ActivityBean getActivityBean() {
		return activityBean;
	}
	public void setActivityBean(ActivityBean activityBean) {
		this.activityBean = activityBean;
	}
	
	@MapsId(value = "mId")
	@JoinColumn(name = "mId")
	@OneToOne(fetch = FetchType.EAGER)
	private MemberBean memberBean;
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}	

	private String position;



	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}