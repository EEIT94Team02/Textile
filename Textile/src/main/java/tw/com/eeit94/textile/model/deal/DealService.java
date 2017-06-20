package tw.com.eeit94.textile.model.deal;

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
public class DealService {
	@Autowired
	private DealDAO dealDAO;

	public DealService(DealDAO dealDAO) {
		this.dealDAO = dealDAO;
	}

	public DealDAO getDealDAO() {
		return dealDAO;
	}

	@Transactional(readOnly = true)
	public List<DealBean> select(DealBean bean) {
		List<DealBean> result = null;
		if (bean != null && bean.getDealId() != null && !Integer.valueOf(0).equals(bean.getDealId())) {
			bean = getDealDAO().select(bean.getDealId());
			if (bean != null) {
				result = new ArrayList<DealBean>();
				result.add(bean);
			} else {
				result = getDealDAO().select();
			}
		} else {
			result = getDealDAO().select();
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