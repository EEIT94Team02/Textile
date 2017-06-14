package tw.com.eeit94.textile.model.logs;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 封裝記錄資料的VO。
 * 
 * 這個表格純粹記錄專用，以下為使用的時機：
 * 
 * 1. 部署完成後，想查程式運行的狀態、訊息，但因無法使用System.out.println()等系統內建方法。
 * 
 * 2. Demo手機認證、信箱認證、信用卡付款、銀行匯款等等，其資訊不可見。
 * 
 * 3. 任何其它重要的資訊。
 * 
 * @author 賴
 * @version 2017/06/12
 */
@Entity
@Table(name = "log")
public class LogBean implements Serializable {
	private static final long serialVersionUID = 2348883515741876412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lId;
	private java.sql.Timestamp lCreateTime;
	private String lLog;

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public java.sql.Timestamp getlCreateTime() {
		return lCreateTime;
	}

	public void setlCreateTime(java.sql.Timestamp lCreateTime) {
		this.lCreateTime = lCreateTime;
	}

	public String getlLog() {
		return lLog;
	}

	public void setlLog(String lLog) {
		this.lLog = lLog;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("lId", this.getlId().toString());
		linkedHashMap.put("lCreateTime", this.getlCreateTime().toString());
		linkedHashMap.put("lLog", this.getlLog());
		return linkedHashMap.toString();
	}
}