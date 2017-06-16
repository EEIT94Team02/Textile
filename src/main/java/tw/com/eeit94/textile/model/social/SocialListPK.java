package tw.com.eeit94.textile.model.social;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
@Embeddable
public class SocialListPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer acquaintenceId;

	public SocialListPK() {

	}

	public SocialListPK(Integer userId, Integer acquaintenceId) {
		this.userId = userId;
		this.acquaintenceId = acquaintenceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getAcquaintenceId() {
		return acquaintenceId;
	}
}