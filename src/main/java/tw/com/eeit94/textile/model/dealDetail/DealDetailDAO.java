package tw.com.eeit94.textile.model.dealDetail;

import java.util.List;

/**
 * deal_detail表格DAO的interface。
 * 
 * @author 李
 * @version 2017/06/13
 */
public interface DealDetailDAO {

	List<DealDetailBean> select(int dealId);

	DealDetailBean insert(DealDetailBean bean);

	DealDetailBean update(DealDetailBean bean);

	boolean delete(DealDetailBean bean);
}