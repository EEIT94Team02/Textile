package tw.com.eeit94.textile.model.socaillist;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
@Entity
@Table(name = "sociallist")
public class SocailListBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SocailListPK socailListPK;
	private String s_type;
	private String s_group;
	private Timestamp log_in;

	public String toString() {
		return "SocailListBean[" + socailListPK + "," + s_type + "," + s_group + "," + log_in + "]" + "\n";
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

	public void setSocailListPK(SocailListPK socailListPK) {
		this.socailListPK = socailListPK;
	}

	public SocailListPK getSocailListPK() {
		return socailListPK;
	}
}