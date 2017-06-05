/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity_member;

import java.util.List;

/*
 * DAO產生步驟：
 * 1. DAO名稱為'"Table名稱" + "DAO"'。
 * 2. Service需要任何DAO時，先在介面定義方法。
 */
public interface Activity_memberDAO {

	public List<Activity_memberBean> select();
	
	public Activity_memberBean selectByPrimaryKey(Activity_memberBean bean);
	
	public List<Activity_memberBean> selectByOthers(Activity_memberBean bean);

	public Activity_memberBean insert(Activity_memberBean bean);

	public Activity_memberBean update(Activity_memberBean bean);

	public boolean delete(Activity_memberBean bean);
	
	public List<Activity_memberBean> updatePosition(List<Activity_memberBean> beans);

// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
//	public List<PhotoBean> selectByPriceLessThan(double price);
}