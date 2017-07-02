package tw.com.eeit94.textile.model.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 封裝product表格資料的bean元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Entity
@Table(name = "Product")
@Component
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private Integer unitPrice;
	private String category;
	private String intro;
	private boolean status;
	private byte[] img;
	private Integer rewardPoints;

	@Override
	public String toString() {
		return "ProductBean=[" + productId + ", " + productName + ", " + unitPrice + ", " + category + ", " + intro
				+ ", " + status + ", " + img + ", " + rewardPoints + "]";
	}

	// productId getter setter
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	// productName getter setter
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return this.productName;
	}

	// unitPrice getter setter
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getUnitPrice() {
		return this.unitPrice;
	}

	// category getter setter
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}

	// intro getter setter
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIntro() {
		return this.intro;
	}

	// status getter setter
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return this.status;
	}

	// img getter setter
	public void setImg(byte[] img) {
		this.img = img;
	}

	public byte[] getImg() {
		return this.img;
	}

	// rewardPoints getter setter
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public Integer getRewardPoints() {
		return this.rewardPoints;
	}
}