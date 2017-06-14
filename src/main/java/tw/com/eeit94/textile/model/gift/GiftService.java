package tw.com.eeit94.textile.model.gift;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * gift表格CRUD的Service元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class GiftService {
	@Autowired
	private GiftDAO giftDAO;

	public GiftDAO getGiftDAO() {
		return giftDAO;
	}

	@Transactional(readOnly = true)
	public List<GiftBean> select(GiftConditionUtil queryCondition) {
		List<GiftBean> result = null;
		if (queryCondition != null) {
			if (queryCondition.getGiftId() != null && !Integer.valueOf(0).equals(queryCondition.getGiftId())) {
				result = new ArrayList<GiftBean>();
				result.add(getGiftDAO().select(queryCondition.getGiftId()));
			} else if (queryCondition.getGiverId() != null || queryCondition.getRecipientId() != null) {
				if (queryCondition.getGiverName() != null || queryCondition.getRecipientName() != null
						|| queryCondition.getGiveDateAfter() != null || queryCondition.getGiveDateBefore() != null) {
					result = getGiftDAO().selectConditional(queryCondition);
				} else {
					result = getGiftDAO().selectAll(queryCondition.getGiverId() == null
							? queryCondition.getRecipientId() : queryCondition.getGiverId());
				}
			}
		}
		return result;
	}

	public GiftBean insert(GiftBean bean) {
		GiftBean result = null;
		if (bean != null) {
			result = getGiftDAO().insert(bean);
		}
		return result;
	}

	public GiftBean update(GiftBean bean) {
		GiftBean result = null;
		if (bean != null) {
			result = getGiftDAO().update(bean);
		}
		return result;
	}

	public boolean delete(GiftBean bean) {
		if (bean != null) {
			return getGiftDAO().delete(bean);
		}
		return false;
	}
}