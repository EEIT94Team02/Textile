package tw.com.eeit94.textile.model.deposit;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 封裝deposit表格資料的bean元件。
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
	
	@JoinColumn(name="memberId")
	@OneToOne(fetch=FetchType.EAGER)
	private MemberBean memberBean;
	
	private Timestamp depositDate;
	private Integer depositAmount;
	private Integer virtualPoints;

	@Override
	public String toString() {
		return "DepositBean=[" + depositId + ", " + memberBean.getmName() + ", " + depositDate + ", " + depositAmount + ", "
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
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	// depositDate getter setter
	public void setDepositDate(Timestamp depositDate) {
		this.depositDate = depositDate;
	}

	public Timestamp getDespositDate() {
		return depositDate;
	}

	// depositAmount getter setter
	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Integer getDepositAmount() {
		return depositAmount;
	}

	// virtualPoints getter setter
	public void setVirtualPoints(Integer virtualPoints) {
		this.virtualPoints = virtualPoints;
	}

	public Integer getVirtualPoints() {
		return virtualPoints;
	}
}