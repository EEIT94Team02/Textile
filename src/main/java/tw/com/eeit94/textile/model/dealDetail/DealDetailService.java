package tw.com.eeit94.textile.model.dealDetail;

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
public class DealDetailService {
	@Autowired
	private DealDetailDAO dealDetailDAO;

	public DealDetailService(DealDetailDAO dealDetailDAO) {
		this.dealDetailDAO = dealDetailDAO;
	}

	public DealDetailDAO getDealDetailDAO() {
		return dealDetailDAO;
	}

	@Transactional(readOnly = true)
	public List<DealDetailBean> select(DealDetailBean bean) {
		List<DealDetailBean> result = null;
		if (bean != null && bean.getDealDetailPK() != null) {
			bean = getDealDetailDAO().select(bean.getDealDetailPK());
			if (bean != null) {
				result = new ArrayList<DealDetailBean>();
				result.add(bean);
			} else {
				result = getDealDetailDAO().select();
			}
		} else {
			result = getDealDetailDAO().select();
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