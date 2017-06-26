package tw.com.eeit94.textile.model.announcement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
@Entity
@Table(name = "announcement")
public class AnnouncementBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer a_id;
	private String a_type;
	private String gist;
	private String msg;
	private java.util.Date relTime;
	private java.util.Date startTime;
	private java.util.Date endTime;

	public String toString() {
		return "AnnouncementBean[" + a_id + "," + a_type + ","  + gist + "," + msg + "," + relTime + "," + startTime + "," + endTime + "]" + "\n";
	}

	

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public String getA_type() {
		return a_type;
	}

	public void setA_type(String a_type) {
		this.a_type = a_type;
	}

	public String getGist() {
		return gist;
	}

	public void setGist(String gist) {
		this.gist = gist;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public java.util.Date getRelTime() {
		return relTime;
	}

	public void setRelTime(java.util.Date relTime) {
		this.relTime = relTime;
	}

	
}