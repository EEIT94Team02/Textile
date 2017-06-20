package tw.com.eeit94.textile.model.deal;

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
@Table(name = "deal")
@Component
public class DealBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dealId;
	private Integer memberId;
	private Timestamp dealDate;
	private Integer totalCost;

	@Override
	public String toString() {
		return "DealBean=[" + dealId + ", " + memberId + ", " + dealDate + ", " + totalCost + "]";
	}

	// dealId getter setter
	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public Integer getDealId() {
		return this.dealId;
	}

	// memberId getter setter
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	// dealDate getter setter
	public void setDealDate(Timestamp dealDate) {
		this.dealDate = dealDate;
	}

	public Timestamp getDealDate() {
		return dealDate;
	}

	// totalCost getter setter
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getTotalCost() {
		return totalCost;
	}
}