package tw.com.eeit94.textile.model.giftDetail;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 封裝gift_detail表格的複合主鍵，並嵌入至gift_detail的bean元件中。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Embeddable
public class GiftDetailPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer giftId;
	private Integer productId;

	// Default constructor for select all method
	public GiftDetailPK() {
	}

	// Constructor to inject composite primary key
	public GiftDetailPK(Integer giftId, Integer productId) {
		this.giftId = giftId;
		this.productId = productId;
	}

	// Getter for composite primary key
	public Integer getGiftId() {
		return giftId;
	}

	public Integer getProductId() {
		return productId;
	}
}