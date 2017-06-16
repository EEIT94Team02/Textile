package tw.com.eeit94.textile.model.item;

import java.util.List;

/**
 * item表格DAO的interface。
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface ItemDAO {

	ItemBean select(ItemPK itemPK);

	List<ItemBean> select(ItemConditionUtil queryCondition);

	ItemBean insert(ItemBean bean);

	ItemBean update(ItemBean bean);

	boolean delete(ItemBean bean);
}