/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity_member;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
 */
@Entity
@Table(name = "activity_member")
	public class Activity_memberBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;	
	
	
	@Override
	public String toString() {
		return "{" + getActivityno() + "," + getMemberid()+ "," + getPosition() + "}";
	}
	
	@Id
	private Integer activityno;
	@Id
	private Integer memberid;
	private String position;

	public Integer getActivityno() {
		return activityno;
	}
	public void setActivityno(Integer activityno) {
		this.activityno = activityno;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}




}