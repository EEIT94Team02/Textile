package tw.com.eeit94.textile.model.photo_album;

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
@Table(name = "photo_album")
@Component
public class Photo_albumBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "{" + getAlbumno() + "," + getCreatetime() + "," + getAlbumname() + "," + getIntroduction() + ","
				+ getVisibility() + "," + getmId() + "}";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer albumno;
	private java.sql.Timestamp createtime;
	private String albumname;
	private String introduction;
	private String visibility;
	private Integer mId;

	public Integer getAlbumno() {
		return albumno;
	}

	public void setAlbumno(Integer albumno) {
		this.albumno = albumno;
	}

	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}
}