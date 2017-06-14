package tw.com.eeit94.textile.model.deal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * deal表格CRUD的service元件。
 * 
 * @author 李
 * @version 2017/06/13
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class DealService {
	@Autowired
	private DealDAO dealDAO;

	public DealDAO getDealDAO() {
		return dealDAO;
	}

	@Transactional(readOnly = true)
	public List<DealBean> select(DealConditionUtil queryCondition) {
		List<DealBean> result = null;
		if (queryCondition != null) {
			if (queryCondition.getDealId() != null && !Integer.valueOf(0).equals(queryCondition.getDealId())) {
				result = new ArrayList<DealBean>();
				result.add(getDealDAO().select(queryCondition.getDealId()));
			} else if (queryCondition.getMemberId() != null || queryCondition.getDealDateAfter() != null
					|| queryCondition.getDealDateBefore() != null) {
				result = getDealDAO().selectConditional(queryCondition);
			}
		}
		return result;
	}

	public DealBean insert(DealBean bean) {
		DealBean result = null;
		if (bean != null) {
			result = getDealDAO().insert(bean);
		}
		return result;
	}

	public DealBean update(DealBean bean) {
		DealBean result = null;
		if (bean != null) {
			result = getDealDAO().update(bean);
		}
		return result;
	}

	public boolean delete(DealBean bean) {
		if (bean != null) {
			return getDealDAO().delete(bean);
		}
		return false;
	}
}