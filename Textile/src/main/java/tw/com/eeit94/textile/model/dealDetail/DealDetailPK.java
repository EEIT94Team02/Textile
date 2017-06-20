package tw.com.eeit94.textile.model.dealDetail;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
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