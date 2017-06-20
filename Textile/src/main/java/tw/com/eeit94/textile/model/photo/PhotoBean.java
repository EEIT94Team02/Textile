package tw.com.eeit94.textile.model.photo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Entity
@Table(name = "photo")
@Component
public class PhotoBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "{" + getPhotono() + "," + getRespath() + "," + getPhotoname() + "," + getInterpretation() + ","
				+ getAlbumno() + "," + getPosition() + "," + getVisibility() + "," + getPhoto_albumBean() + "}";
	}

	@Id
	private String photono;
	private String respath;
	private String photoname;
	private String interpretation;
	private Integer albumno;
	private String position;
	private String visibility;

	@MapsId(value = "albumno")
	@JoinColumn(name = "albumno")
	@OneToOne(fetch = FetchType.EAGER)
	private Photo_albumBean photo_albumBean;

	public Photo_albumBean getPhoto_albumBean() {
		return photo_albumBean;
	}

	public void setPhoto_albumBean(Photo_albumBean photo_albumBean) {
		this.photo_albumBean = photo_albumBean;
	}

	public String getPhotono() {
		return photono;
	}

	public void setPhotono(String photono) {
		this.photono = photono;
	}

	public String getRespath() {
		return respath;
	}

	public void setRespath(String respath) {
		this.respath = respath;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}

	public Integer getAlbumno() {
		return albumno;
	}

	public void setAlbumno(Integer albumno) {
		this.albumno = albumno;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}