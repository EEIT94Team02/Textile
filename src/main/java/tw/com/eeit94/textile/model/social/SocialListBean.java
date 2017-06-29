package tw.com.eeit94.textile.model.social;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
@Entity
@Table(name = "sociallist")
@DynamicInsert
public class SocialListBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SocialListPK socialListPK;
	private String s_type;
	private String s_group;
	private Timestamp log_in;
	@MapsId(value = "socialListPK")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acquaintenceId")
	private MemberBean mbean;

	@MapsId(value = "socialListPK")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private MemberBean ibean;

	public MemberBean getIbean() {
		return ibean;
	}

	public void setIbean(MemberBean ibean) {
		this.ibean = ibean;
	}

	public String toString() {
		return "SocialListBean[" + socialListPK + "," + s_type + "," + s_group + "," + log_in + "," + mbean + ","
				+ ibean + "]" + "\n";
	}

	public String getS_type() {
		return s_type;
	}

	public void setS_type(String s_type) {
		this.s_type = s_type;
	}

	public String getS_group() {
		return s_group;
	}

	public void setS_group(String s_group) {
		this.s_group = s_group;
	}

	public Timestamp getLog_in() {
		return log_in;
	}

	public void setLog_in(Timestamp log_in) {
		this.log_in = log_in;
	}

	public void setSocialListPK(SocialListPK socialListPK) {
		this.socialListPK = socialListPK;
	}

	public SocialListPK getSocialListPK() {
		return socialListPK;
	}

	public MemberBean getMbean() {
		return mbean;
	}

	public void setMbean(MemberBean mbean) {
		this.mbean = mbean;
	}
}