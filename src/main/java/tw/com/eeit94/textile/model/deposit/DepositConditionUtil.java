package tw.com.eeit94.textile.model.deposit;

/**
 * 將deposit表格的特殊查詢條件封裝為物件使用。
 * 
 * @author 李
 * @Version 2017/06/13
 */
public class DepositConditionUtil {
	private Integer memberId;
	private java.util.Date depositDateAfter;
	private java.util.Date depositDateBefore;

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setDepositDateAfter(java.util.Date depositDateAfter) {
		this.depositDateAfter = depositDateAfter;
	}
	public java.util.Date getDepositDateAfter() {
		return depositDateAfter;
	}
	
	public void setDepositDateBefore(java.util.Date depositDateBefore) {
		this.depositDateBefore = depositDateBefore;
	}
	public java.util.Date getDepositDateBefore() {
		return depositDateBefore;
	}
}
