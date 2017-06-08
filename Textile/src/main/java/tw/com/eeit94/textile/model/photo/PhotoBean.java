/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
 */
@Entity
@Table(name = "photo")
	public class PhotoBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public String toString() {
		return "{" + getPhotono() + "," + getRespath() + "," + getPhotoname() + "," + getInterpretation()
				+ "," + getAlbumno() + "," + getPosition() + ","+ getVisibility() + "}";
	}
	@Id
	private String photono;
	private String respath;
	private String photoname;
	private String interpretation;
	private Integer albumno;
	private String position;
	private String visibility;	
	
	@MapsId(value="albumno")
	@JoinColumn(name="albumno")
	@OneToOne(fetch=FetchType.EAGER)
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