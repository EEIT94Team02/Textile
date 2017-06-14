package tw.com.eeit94.textile.model.deposit;

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
public class DepositService {
	@Autowired
	private DepositDAO depositDAO;

	public DepositService(DepositDAO depositDAO) {
		this.depositDAO = depositDAO;
	}

	public DepositDAO getDepositDAO() {
		return depositDAO;
	}

	@Transactional(readOnly = true)
	public List<DepositBean> select(DepositBean bean) {
		List<DepositBean> result = null;
		if (bean != null && bean.getDepositId() != null && !Integer.valueOf(0).equals(bean.getDepositId())) {
			bean = getDepositDAO().select(bean.getDepositId());
			if (bean != null) {
				result = new ArrayList<DepositBean>();
				result.add(bean);
			} else {
				result = getDepositDAO().select();
			}
		} else {
			result = getDepositDAO().select();
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