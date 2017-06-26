package tw.com.eeit94.textile.model.dealDetail;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 封裝deal_detail表格資料的bean元件。
 * 
 * @author 李
 * @version 2017/06/13
 */
@Entity
@Table(name = "deal_detail")
@Component
public class DealDetailBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DealDetailPK dealDetailPK;

	@MapsId(value = "dealId")
	@JoinColumn(name = "dealId", insertable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private DealBean dealBean;

	@MapsId(value = "productId")
	@JoinColumn(name = "productId", insertable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private ProductBean productBean;

	private Integer amount;

	@Override
	public String toString() {
		return "DealDetailBean=[" + dealDetailPK.getDealId() + ", " + dealBean.getMemberBean().getmName() + ", "
				+ dealDetailPK.getProductId() + ", " + productBean.getProductName() + ", " + productBean.getUnitPrice()
				+ ", " + amount + "]";
	}

	// dealDetailPK getter setter
	public void setDealDetailPK(DealDetailPK dealDetailPK) {
		this.dealDetailPK = dealDetailPK;
	}
	public DealDetailPK getDealDetailPK() {
		return this.dealDetailPK;
	}

	// dealBean getter setter
	public void setDealBean(DealBean dealBean) {
		this.dealBean = dealBean;
	}
	public DealBean getDealBean() {
		return this.dealBean;
	}

	// productBean getter setter
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	public ProductBean getProductBean() {
		return this.productBean;
	}

	// amount getter setter
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmount() {
		return this.amount;
	}
}