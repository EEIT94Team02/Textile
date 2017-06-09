/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
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

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
 */
@Entity
@Table(name = "activity_member")
@Component
public class Activity_memberBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "{" + activity_memberPK.getmId() + "," + activity_memberPK.getActivityno() + "," + getPosition() + ","
				+ activityBean + "}";
	}

	@EmbeddedId
	private Activity_memberPK activity_memberPK;

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

	private String position;

	public Activity_memberPK getActivity_memberPK() {
		return activity_memberPK;
	}

	public void setActivity_memberPK(Activity_memberPK activity_memberPK) {
		this.activity_memberPK = activity_memberPK;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}