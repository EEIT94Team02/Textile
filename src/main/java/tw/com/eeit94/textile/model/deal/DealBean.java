package tw.com.eeit94.textile.model.deal;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.dealDetail.DealDetailBean;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 封裝deal表格資料的bean元件。
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
	
	@JoinColumn(name="memberId")
	@ManyToOne(fetch=FetchType.EAGER)
	private MemberBean memberBean;
	
	private Timestamp dealDate;
	private Integer totalCost;
	
	@JoinColumn(name = "dealId")
	@OneToMany(fetch = FetchType.LAZY)
	private Set<DealDetailBean> dealDetailBeans;

	@Override
	public String toString() {
		return "DealBean=[" + dealId + ", " + memberBean.getmName() + ", " + dealDate + ", " + totalCost + "]";
	}

	// dealId getter setter
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	public Integer getDealId() {
		return this.dealId;
	}

	// memberBean getter setter
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	public MemberBean getMemberBean() {
		return memberBean;
	}

	// dealDate getter setter
	public void setDealDate(Timestamp dealDate) {
		this.dealDate = dealDate;
	}
	public Timestamp getDealDate() {
		return dealDate;
	}

	// totalCost getter setter
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	public Integer getTotalCost() {
		return totalCost;
	}

	public void setDealDetailBeans(Set<DealDetailBean> dealDetailBeans) {
		this.dealDetailBeans = dealDetailBeans;
	}
	public Set<DealDetailBean> getDealDetailBeans() {
		return dealDetailBeans;
	}
}