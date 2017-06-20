package tw.com.eeit94.textile.model.item;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Embeddable
public class ItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer memberId;
	private Integer productId;

	// Default constructor for select all method
	public ItemPK() {
	}

	// Constructor to inject composite primary key
	public ItemPK(int memberId, int productId) {
		this.memberId = memberId;
		this.productId = productId;
	}

	// Getter for composite primary key
	public Integer getMemberId() {
		return this.memberId;
	}

	public Integer getProductId() {
		return this.productId;
	}
}