package tw.com.eeit94.textile.model.dealDetail;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
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
	@OneToOne(fetch = FetchType.EAGER)
	private DealBean dealBean;

	@MapsId(value = "productId")
	@JoinColumn(name = "productId", insertable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private ProductBean productBean;

	private Integer amount;

	@Override
	public String toString() {
		return "DealDetailBean=[" + dealDetailPK.getDealId() + ", " + dealBean.getMemberId() + ", "
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
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Integer getAmount() {
		return this.amount;
	}
}