package tw.com.eeit94.textile.model.interest_detail;

import java.util.List;

/**
 * 控制興趣明細資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public interface Interest_DetailDAO {

	public List<Interest_DetailBean> selectAll();

	public List<Interest_DetailBean> select(Interest_DetailBean i_dbean);

	public List<Interest_DetailBean> insert(Interest_DetailBean i_dbean);

	public List<Interest_DetailBean> update(Interest_DetailBean i_dbean);

	public List<Interest_DetailBean> delete(Interest_DetailBean i_dbean);
}