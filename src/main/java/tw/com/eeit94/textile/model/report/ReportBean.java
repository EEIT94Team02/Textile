package tw.com.eeit94.textile.model.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
@Entity
@Table(name = "report")
public class ReportBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 有設計流水號才用，沒有設計流水號用了大爆死。
	@Id // Primary Key
	private Integer reptNo;
	private Integer mId;
	private java.sql.Timestamp reptDate;
	private String reptType;
	private String reptDetail;
	private String replyDetail;
	private Boolean situation;

	public Integer getReptNo() {
		return reptNo;
	}

	public void setReptNo(Integer reptNo) {
		this.reptNo = reptNo;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public java.sql.Timestamp getReptDate() {
		return reptDate;
	}

	public void setReptDate(java.sql.Timestamp reptDate) {
		this.reptDate = reptDate;
	}

	public String getReptType() {
		return reptType;
	}

	public void setReptType(String reptType) {
		this.reptType = reptType;
	}

	public String getReptDetail() {
		return reptDetail;
	}

	public void setReptDetail(String reptDetail) {
		this.reptDetail = reptDetail;
	}

	public String getReplyDetail() {
		return replyDetail;
	}

	public void setReplyDetail(String replyDetail) {
		this.replyDetail = replyDetail;
	}

	public Boolean getSituation() {
		return situation;
	}

	public void setSituation(Boolean situation) {
		this.situation = situation;
	}
}