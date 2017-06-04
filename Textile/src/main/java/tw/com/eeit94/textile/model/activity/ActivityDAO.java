/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity;

import java.text.ParseException;
import java.util.List;

/*
 * DAO產生步驟：
 * 1. DAO名稱為'"Table名稱" + "DAO"'。
 * 2. Service需要任何DAO時，先在介面定義方法。
 */
public interface ActivityDAO {

	public List<ActivityBean> select();
	
	public ActivityBean select(ActivityBean bean);

	public ActivityBean insert(ActivityBean bean);

	public ActivityBean update(ActivityBean bean);

	public boolean delete(ActivityBean bean);
	
	public List<ActivityBean> selectByOthers(ActivityBean bean) throws ParseException;

// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
//	public List<PhotoBean> selectByPriceLessThan(double price);
}