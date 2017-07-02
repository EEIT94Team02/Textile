package tw.com.eeit94.textile.model.dealDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.deal.DealConditionUtil;

/**
 * deal_detail表格CRUD的service元件。
 * 
 * @author 李
 * @version 2017/06/13
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class DealDetailService {
	@Autowired
	private DealDetailDAO dealDetailDAO;

	public DealDetailDAO getDealDetailDAO() {
		return dealDetailDAO;
	}

	@Transactional(readOnly = true)
	public List<DealDetailBean> select(DealConditionUtil dealCondition) {
		List<DealDetailBean> result = null;
		if (dealCondition != null && dealCondition.getDealId() != null && !Integer.valueOf(0).equals(dealCondition.getDealId())) {
			result = getDealDetailDAO().select(dealCondition.getDealId().intValue());
		}
		return result;
	}

	public DealDetailBean insert(DealDetailBean bean) {
		DealDetailBean result = null;
		if (bean != null) {
			result = getDealDetailDAO().insert(bean);
		}
		return result;
	}

	public DealDetailBean update(DealDetailBean bean) {
		DealDetailBean result = null;
		if (bean != null) {
			result = getDealDetailDAO().update(bean);
		}
		return result;
	}

	public boolean delete(DealDetailBean bean) {
		if (bean != null) {
			return getDealDetailDAO().delete(bean);
		}
		return false;
	}
}