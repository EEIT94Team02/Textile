package tw.com.eeit94.textile.model.social;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "sociallist")
@DynamicInsert
public class LinksBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private String Linkname;
	private String profileURL;
	private String chatroomURL;
	private Integer LinkmId;
	
	
	
	public Integer getLinkmId() {
		return LinkmId;
	}
	public void setLinkmId(Integer linkmId) {
		LinkmId = linkmId;
	}
	public String getLinkname() {
		return Linkname;
	}
	public void setLinkname(String linkname) {
		Linkname = linkname;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public String getChatroomURL() {
		return chatroomURL;
	}
	public void setChatroomURL(String chatroomURL) {
		this.chatroomURL = chatroomURL;
	}

	
	
	
}
