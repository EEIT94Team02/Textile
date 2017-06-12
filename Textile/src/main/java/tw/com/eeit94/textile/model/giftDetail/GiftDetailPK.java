package tw.com.eeit94.textile.model.giftDetail;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
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
	public GiftDetailPK(int giftId, int productId) {
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