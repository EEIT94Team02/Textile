/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
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