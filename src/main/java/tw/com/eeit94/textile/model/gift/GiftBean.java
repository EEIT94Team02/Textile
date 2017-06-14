package tw.com.eeit94.textile.model.gift;

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

import tw.com.eeit94.textile.model.giftDetail.GiftDetailBean;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 封裝gift表格資料的bean元件。
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

	@JoinColumn(name = "giverId", insertable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private MemberBean giverBean;
	
	@JoinColumn(name = "recipientId", insertable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private MemberBean recipientBean;
	
	private Timestamp giveDate;
	
	@JoinColumn(name = "giftId")
	@OneToMany(fetch = FetchType.LAZY)
	private Set<GiftDetailBean> giftDetailBeans;

	@Override
	public String toString() {
		return "GiftBean=[" + giftId + ", " + giverBean.getmName() + ", " + recipientBean.getmName() + ", " + giveDate + "]";
	}

	// giftId getter setter
	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}
	public Integer getGiftId() {
		return this.giftId;
	}

	// giverId getter setter
	public void setGiverBean(MemberBean giverBean) {
		this.giverBean = giverBean;
	}
	public MemberBean getGiverBean() {
		return giverBean;
	}

	// recipientId getter setter
	public void setRecipientBean(MemberBean recipientBean) {
		this.recipientBean = recipientBean;
	}
	public MemberBean getRecipientBean() {
		return this.recipientBean;
	}

	// giveDate getter setter
	public void setGiveDate(Timestamp giveDate) {
		this.giveDate = giveDate;
	}
	public Timestamp getGiveDate() {
		return this.giveDate;
	}

	public void setGiftDetailBeans(Set<GiftDetailBean> giftDetailBean) {
		this.giftDetailBeans = giftDetailBean;
	}
	public Set<GiftDetailBean> getGiftDetailBeans() {
		return giftDetailBeans;
	}
	
}