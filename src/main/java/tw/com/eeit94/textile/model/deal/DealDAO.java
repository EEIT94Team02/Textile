package tw.com.eeit94.textile.model.deal;

import java.util.List;

/**
 * deal表格dao的interface。
 * 
 * @author 李
 * @version 2017/06/13
 */
public interface DealDAO {
	
	DealBean select(int dealId);
	
	List<DealBean> selectConditional(DealConditionUtil queryCondition);

	DealBean insert(DealBean bean);

	DealBean update(DealBean bean);

	boolean delete(DealBean bean);
}