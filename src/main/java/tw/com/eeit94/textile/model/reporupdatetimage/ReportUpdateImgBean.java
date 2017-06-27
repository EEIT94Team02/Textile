package tw.com.eeit94.textile.model.reporupdatetimage;

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
@Table(name = "reportUpdateImg")
public class ReportUpdateImgBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 有設計流水號才用，沒有設計流水號用了大爆死。
	private Integer reptUpImgNo;
	private String imgUpPath;
	private Integer reptUpNo;

	public Integer getReptUpImgNo() {
		return reptUpImgNo;
	}

	public void setReptUpImgNo(Integer reptUpImgNo) {
		this.reptUpImgNo = reptUpImgNo;
	}

	public String getImgUpPath() {
		return imgUpPath;
	}

	public void setImgUpPath(String imgUpPath) {
		this.imgUpPath = imgUpPath;
	}

	public Integer getReptUpNo() {
		return reptUpNo;
	}

	public void setReptUpNo(Integer reptUpNo) {
		this.reptUpNo = reptUpNo;
	}

}