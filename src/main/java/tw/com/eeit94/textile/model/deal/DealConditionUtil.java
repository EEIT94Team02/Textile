package tw.com.eeit94.textile.model.deal;

/**
 * 封裝deal表格特殊查詢的條件。
 * 
 * @author 李
 * @Version 2017/06/12
 */
public class DealConditionUtil {
	private Integer dealId;
	private Integer memberId;
	private java.util.Date dealDateAfter;
	private java.util.Date dealDateBefore;

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	public Integer getDealId() {
		return dealId;
	}
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setDealDateAfter(java.util.Date dealDateAfter) {
		this.dealDateAfter = dealDateAfter;
	}
	public java.util.Date getDealDateAfter() {
		return dealDateAfter;
	}
	
	public void setDealDateBefore(java.util.Date dealDateBefore) {
		this.dealDateBefore = dealDateBefore;
	}
	public java.util.Date getDealDateBefore() {
		return dealDateBefore;
	}
	
}
