package tw.com.eeit94.textile.model.reportupdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/20
 */
@Entity
@Table(name = "reportUpdate")
public class ReportUpdateBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 有設計流水號才用，沒有設計流水號用了大爆死。
	@Id // Primary Key
	private Integer reptUpNo;
	private java.sql.Timestamp reptUpDate;
	private String reptUpDetail;
	private String replyUpDetail;
	private Integer reptNo;
	
	public Integer getReptUpNo() {
		return reptUpNo;
	}
	public void setReptUpNo(Integer reptUpNo) {
		this.reptUpNo = reptUpNo;
	}
	public java.sql.Timestamp getReptUpDate() {
		return reptUpDate;
	}
	public void setReptUpDate(java.sql.Timestamp reptUpDate) {
		this.reptUpDate = reptUpDate;
	}
	public String getReptUpDetail() {
		return reptUpDetail;
	}
	public void setReptUpDetail(String reptUpDetail) {
		this.reptUpDetail = reptUpDetail;
	}
	public String getReplyUpDetail() {
		return replyUpDetail;
	}
	public void setReplyUpDetail(String replyUpDetail) {
		this.replyUpDetail = replyUpDetail;
	}
	public Integer getReptNo() {
		return reptNo;
	}
	public void setReptNo(Integer reptNo) {
		this.reptNo = reptNo;
	}
	@Override
	public String toString() {
		return "{"+getReptUpNo()+","+getReptUpDetail()+","+getReplyUpDetail()+","+getReptNo()+","+getReptUpDate()
		+"}";
	}

}