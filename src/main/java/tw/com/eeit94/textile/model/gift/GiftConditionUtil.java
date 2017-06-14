package tw.com.eeit94.textile.model.gift;

/**
 * 封裝Gift表格查詢的條件。
 * 
 * @author 李
 * @Version 2017/06/13
 */
public class GiftConditionUtil {
	private Integer giftId;
	private Integer giverId;
	private Integer recipientId;
	private String giverName;
	private String recipientName;
	private java.util.Date giveDateAfter;
	private java.util.Date giveDateBefore;

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}
	public Integer getGiftId() {
		return giftId;
	}
	
	public void setGiverId(Integer giverId) {
		this.giverId = giverId;
	}
	public Integer getGiverId() {
		return giverId;
	}

	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}
	public Integer getRecipientId() {
		return recipientId;
	}
	
	public void setGiverName(String giverName) {
		this.giverName = giverName;
	}
	public String getGiverName() {
		return giverName;
	}
	
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientName() {
		return recipientName;
	}
	
	public void setGiveDateAfter(java.util.Date giveDateAfter) {
		this.giveDateAfter = giveDateAfter;
	}
	public java.util.Date getGiveDateAfter() {
		return giveDateAfter;
	}

	public void setGiveDateBefore(java.util.Date giveDateBefore) {
		this.giveDateBefore = giveDateBefore;
	}
	public java.util.Date getGiveDateBefore() {
		return giveDateBefore;
	}
	
}
