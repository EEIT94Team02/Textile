package tw.com.eeit94.textile.model.activity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Entity
@Table(name = "activity")
@Component
public class ActivityBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "{" + getActivityno() + "," + getBegintime() + "," + getEndtime() + "," + getActivityname() + ","
				+ getPlace() + "," + getInterpretation() + "," + getVisibility() + "}";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityno;
	private java.sql.Timestamp begintime;
	private java.sql.Timestamp endtime;
	private String activityname;
	private String place;
	private String interpretation;
	private String visibility;

	public Integer getActivityno() {
		return activityno;
	}

	public void setActivityno(Integer activityno) {
		this.activityno = activityno;
	}

	public java.sql.Timestamp getBegintime() {
		return begintime;
	}

	public void setBegintime(java.sql.Timestamp begintime) {
		this.begintime = begintime;
	}

	public java.sql.Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(java.sql.Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}