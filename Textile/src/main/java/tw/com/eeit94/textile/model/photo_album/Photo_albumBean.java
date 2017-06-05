/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo_album;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
 */
@Entity
@Table(name = "photo_album")
	public class Photo_albumBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "{"+getAlbumno() + "," + getCreatetime()
				+ "," + getAlbumname() + "," + getIntroduction()
				+ "," + getVisibility() + "," + getMemberid()+"}";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer albumno;
	private java.util.Date createtime;
	private String albumname;
	private String introduction;
	private String visibility;
	private Integer memberid;	
	
	public Integer getAlbumno() {
		return albumno;
	}
	public void setAlbumno(Integer albumno) {
		this.albumno = albumno;
	}
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
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
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	





}