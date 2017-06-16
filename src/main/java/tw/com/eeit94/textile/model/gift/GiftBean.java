package tw.com.eeit94.textile.model.gift;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Entity
@Table(name = "gift")
@Component
public class GiftBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer giftId;
	private Integer giverId;
	private Integer recipientId;
	private Timestamp giveDate;

	@Override
	public String toString() {
		return "GiftBean=[" + giftId + ", " + giverId + ", " + recipientId + ", " + giveDate + "]";
	}

	// giftId getter setter
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public Integer getGiftId() {
		return this.giftId;
	}

	// giverId getter setter
	public void setGiverId(int giverId) {
		this.giverId = giverId;
	}

	public Integer getGiverId() {
		return this.giverId;
	}

	// recipientId getter setter
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public Integer getRecipientId() {
		return this.recipientId;
	}

	// giveDate getter setter
	public void setGiveDate(Timestamp giveDate) {
		this.giveDate = giveDate;
	}

	public Timestamp getGiveDate() {
		return this.giveDate;
	}
}