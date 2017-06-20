package tw.com.eeit94.textile.model.item;

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

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 封裝item表格資料的bean元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Entity
@Table(name = "item")
@Component
public class ItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId		// 複合主鍵另外封裝並以物件形式嵌入。
	private ItemPK itemPK;
	
	@MapsId(value = "memberId")		// 對應封裝過後的複合主鍵裡的屬性memberId
	@JoinColumn(name = "memberId", insertable = false)	//  對應此表格於資料庫中已建立foreign key mapping規則的memberId欄位
	@ManyToOne(fetch = FetchType.EAGER)		// 一對一的對應關係，且於此表格建立之時一併取得對應表格的bean元件以使用。
	private MemberBean memberBean;

	@MapsId(value = "productId")	// 對應封裝過後的複合主鍵裡的屬性productId
	@JoinColumn(name = "productId", insertable = false)		//  對應此表格於資料庫中已建立foreign key mapping規則的productId欄位
	@OneToOne(fetch = FetchType.EAGER)	// 一對一的對應關係，且於此表格建立之時一併取得對應表格的bean元件以使用。
	private ProductBean productBean;

	private Integer amount;

	@Override
	public String toString() {
		return "ItemBean=[" + itemPK.getMemberId() + ", " + itemPK.getProductId() + ", " + amount + ", "
				+ productBean + "]";
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
	
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	
	public MemberBean getMemberBean() {
		return memberBean;
	}
	
	// amount getter setter
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}
}