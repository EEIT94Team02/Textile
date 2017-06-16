package tw.com.eeit94.textile.model.deposit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * deposit表格CRUD的service元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class DepositService {
	@Autowired
	private DepositDAO depositDAO;

	public DepositDAO getDepositDAO() {
		return depositDAO;
	}

	@Transactional(readOnly = true)
	public List<DepositBean> select(DepositConditionUtil queryCondition) {
		List<DepositBean> result = null;
		if (queryCondition.getMemberId() != null) {
			result = getDepositDAO().selectConditional(queryCondition);
		}
		return result;
	}

	public DepositBean insert(DepositBean bean) {
		DepositBean result = null;
		if (bean != null) {
			result = getDepositDAO().insert(bean);
		}
		return result;
	}

	public DepositBean update(DepositBean bean) {
		DepositBean result = null;
		if (bean != null) {
			result = getDepositDAO().update(bean);
		}
		return result;
	}

	public boolean delete(DepositBean bean) {
		if (bean != null) {
			return getDepositDAO().delete(bean);
		}
		return false;
	}
}