package tw.com.eeit94.textile.model.deposit;

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
@Table(name = "deposit")
@Component
public class DepositBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer depositId;
	private Integer memberId;
	private Timestamp depositDate;
	private Integer depositAmount;
	private Integer virtualPoints;

	@Override
	public String toString() {
		return "DepositBean=[" + depositId + ", " + memberId + ", " + depositDate + ", " + depositAmount + ", "
				+ virtualPoints + "]";
	}

	// depositId getter setter
	public void setDepositId(int depositId) {
		this.depositId = depositId;
	}

	public Integer getDepositId() {
		return depositId;
	}

	// memberId getter setter
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	// depositDate getter setter
	public void setDepositDate(Timestamp depositDate) {
		this.depositDate = depositDate;
	}

	public Timestamp getDespositDate() {
		return depositDate;
	}

	// depositAmount getter setter
	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Integer getDepositAmount() {
		return depositAmount;
	}

	// virtualPoints getter setter
	public void setVirtualPoints(int virtualPoints) {
		this.virtualPoints = virtualPoints;
	}

	public Integer getVirtualPoints() {
		return virtualPoints;
	}
}