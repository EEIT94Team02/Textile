package tw.com.eeit94.textile.model.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * item表格CRUD的service元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class ItemService {
	@Autowired
	private ItemDAO itemDAO;

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	@Transactional(readOnly = true)
	public List<ItemBean> select(ItemConditionUtil queryCondition) {
		List<ItemBean> result = null;
		if (queryCondition != null && queryCondition.getMemberId() != null) {
			if (queryCondition.getItemId() != null) {
				result = new ArrayList<ItemBean>();
				result.add(getItemDAO().select(new ItemPK(queryCondition.getItemId(), queryCondition.getMemberId())));
			} else {
				result = getItemDAO().select(queryCondition);
			}
		}
		return result;
	}

	public ItemBean insert(ItemBean bean) {
		ItemBean result = null;
		if (bean != null) {
			result = getItemDAO().insert(bean);
		}
		return result;
	}

	public ItemBean update(ItemBean bean) {
		ItemBean result = null;
		if (bean != null) {
			result = getItemDAO().update(bean);
		}
		return result;
	}

	public boolean delete(ItemBean bean) {
		if (bean != null) {
			return getItemDAO().delete(bean);
		}
		return false;
	}
}