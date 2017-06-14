package tw.com.eeit94.textile.model.dealDetail;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 封裝deal_detail表格的複合主鍵，並嵌入deal_detail表格的bean元件中。
 * 
 * @author 李
 * @version 2017/06/13
 */
@Embeddable
public class DealDetailPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer dealId;
	private Integer productId;

	// Default constructor for select all method
	public DealDetailPK() {
	}

	// Constructor to inject composite primary key
	public DealDetailPK(int dealId, int productId) {
		this.dealId = dealId;
		this.productId = productId;
	}

	// Getter for composite primary key
	public Integer getDealId() {
		return dealId;
	}

	public Integer getProductId() {
		return productId;
	}
}