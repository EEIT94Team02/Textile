package tw.com.eeit94.textile.model.item;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Entity
@Table(name = "item")
@Component
public class ItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPK itemPK;

	@MapsId(value = "productId")
	@JoinColumn(name = "productId", insertable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private ProductBean productBean;

	private Integer amount;

	@Override
	public String toString() {
		return "ItemBean=[" + itemPK.getMemberId() + ", " + itemPK.getProductId() + ", " + amount + ", " + productBean
				+ "]";
	}

	// itemPK getter setter
	public void setItemPK(ItemPK itemPK) {
		this.itemPK = itemPK;
	}

	public ItemPK getItemPK() {
		return this.itemPK;
	}

	// productBean getter setter
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	// amount getter setter
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Integer getAmoumt() {
		return this.amount;
	}
}