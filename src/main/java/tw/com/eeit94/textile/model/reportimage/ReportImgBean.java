package tw.com.eeit94.textile.model.reportimage;

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
@Table(name = "reportImg")
public class ReportImgBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 有設計流水號才用，沒有設計流水號用了大爆死。
	private Integer reptImgNo;
	private String imgPath;
	private Integer reptNo;

	public Integer getReptImgNo() {
		return reptImgNo;
	}

	public void setReptImgNo(Integer reptImgNo) {
		this.reptImgNo = reptImgNo;
	}

	public String getImgPath() {
		return imgPath;
	}

	public Integer getReptNo() {
		return reptNo;
	}

	public void setReptNo(Integer reptNo) {
		this.reptNo = reptNo;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}