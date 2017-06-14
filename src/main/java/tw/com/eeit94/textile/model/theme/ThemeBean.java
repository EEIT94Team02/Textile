package tw.com.eeit94.textile.model.theme;

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
@Table(name = "theme")
public class ThemeBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY) // 有設計流水號才用，沒有設計流水號用了大爆死。
	@Id // Primary Key 如果沒@Id會發生 No identifier specified for entity
	private Integer themeNo;
	private String themeStyle;
	private Boolean themeStatus;
	private Integer mId;

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public Integer getThemeNo() {
		return themeNo;
	}

	public void setThemeNo(Integer themeNo) {
		this.themeNo = themeNo;
	}

	public String getThemeStyle() {
		return themeStyle;
	}

	public void setThemeStyle(String themeStyle) {
		this.themeStyle = themeStyle;
	}

	public Boolean getThemeStatus() {
		return themeStatus;
	}

	public void setThemeStatus(Boolean themeStatus) {
		this.themeStatus = themeStatus;
	}
}