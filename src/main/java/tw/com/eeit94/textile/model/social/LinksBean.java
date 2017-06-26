package tw.com.eeit94.textile.model.social;

import java.io.Serializable;

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
	private String mName;
	private String profileURL;
	private String chatroomURL;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
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
