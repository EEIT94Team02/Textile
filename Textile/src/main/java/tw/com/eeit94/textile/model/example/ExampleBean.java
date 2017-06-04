/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.example;

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
 */
// @Entity
// @Table(name = "example")
public class ExampleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// @Id ← Primary Key
	// @GeneratedValue(strategy=GenerationType.IDENTITY) ← 有設計流水號才用，沒有設計流水號用了大爆死。
	private Integer id;
	private String name;
	private Double price;
	private Boolean onSale;
	private java.util.Date makeDate;
	private Integer expire;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getOnSale() {
		return onSale;
	}

	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	public java.util.Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(java.util.Date makeDate) {
		this.makeDate = makeDate;
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}
}