package tw.com.eeit94.textile.model.gift;

import java.util.List;

/**
 * gift表格dao的interface。
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface GiftDAO {
	
	GiftBean select(int giftId);

	List<GiftBean> selectAll(int userId);

	List<GiftBean> selectConditional(GiftConditionUtil queryCondition);

	GiftBean insert(GiftBean bean);

	GiftBean update(GiftBean bean);

	boolean delete(GiftBean bean);
}