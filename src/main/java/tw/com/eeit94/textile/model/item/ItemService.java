package tw.com.eeit94.textile.model.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class ItemService {
	@Autowired
	private ItemDAO itemDAO;

	public ItemService(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	@Transactional(readOnly = true)
	public List<ItemBean> select(ItemBean bean) {
		List<ItemBean> result = null;
		if (bean != null && bean.getItemPK() != null) {
			bean = getItemDAO().select(bean.getItemPK());
			if (bean != null) {
				result = new ArrayList<ItemBean>();
				result.add(bean);
			} else {
				result = getItemDAO().select();
			}
		} else {
			result = getItemDAO().select();
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