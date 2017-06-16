/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.example;

import java.util.List;

/*
 * DAO產生步驟：
 * 1. DAO名稱為'"Table名稱" + "DAO"'。
 * 2. Service需要任何DAO時，先在介面定義方法。
 * 3. 加上javadoc的註解，在Spring或Hibernate的Annotation上方，@author和@version必寫，@version為日期。
 */
/**
 * [空行] 
 * 本欄未必須由Shift+Alt+J產生，複製貼上也可(要改內容)，這裡簡單敘述這個元件的功能或地位，上面的註解不用留著，example的套件留著就好。
 * [空行]
 * @author 賴
 * @version 2017/06/10
 */
public interface ExampleDAO {

	public List<ExampleBean> selectAll();

	public ExampleBean select(ExampleBean bean);

	public ExampleBean insert();

	public ExampleBean update();

	public boolean delete();

	// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
	public List<ExampleBean> selectByPriceLessThan(double price);
}