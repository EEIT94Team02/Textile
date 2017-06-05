/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.example;

import java.util.List;

/*
 * DAO產生步驟：
 * 1. DAO名稱為'"Table名稱" + "DAO"'。
 * 2. Service需要任何DAO時，先在介面定義方法。
 */
public interface ExampleDAO {

	public List<ExampleBean> select();

	public ExampleBean select(ExampleBean bean);

	public ExampleBean insert();

	public ExampleBean update();

	public boolean delete();

	// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
	public List<ExampleBean> selectByPriceLessThan(double price);
}