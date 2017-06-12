/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.example;

/*
 * Java Bean產生步驟：
 * 1. Java Bean名稱為'"Table名稱" + "Bean"'。
 * 2. 將屬性打好，屬性名稱即Table的欄位名稱，全宣告為private，全使用包裹類別，用Eclipse產生getter和setter。
 * 3. 使用JPA只需在類別、屬性上方標Annotation，不需要表格映射檔。
 * 4. 使用JPA要實作介面Serializable。
 * 5. 加上javadoc的註解，在Spring或Hibernate的Annotation上方，@author和@version必寫，@version為日期。
 */
/**
 * [空行] 
 * 本欄未必須由Shift+Alt+J產生，複製貼上也可(要改內容)，這裡簡單敘述這個元件的功能或地位，上面的註解不用留著，example的套件留著就好。
 * [空行]
 * @author 賴
 * @version 2017/06/10
 */
// @Entity
// @Table(name = "example")
public class ExampleBean implements java.io.Serializable {
	/**
	 * 使用Eclipse產生，先將下面的屬性刪掉，找到黃虛虛的底線，
	 * 利用「+Add generated serial version ID」即可產生。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	private static final long serialVersionUID = 4043115819346976937L;

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