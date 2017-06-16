package tw.com.eeit94.textile.model.item;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 封裝item表格的複合主鍵，並嵌入至item的bean元件中。
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
	public ItemPK(Integer memberId, Integer productId) {
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