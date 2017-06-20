package tw.com.eeit94.textile.model.gift;

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
public class GiftService {
	@Autowired
	private GiftDAO giftDAO;

	public GiftService(GiftDAO giftDAO) {
		this.giftDAO = giftDAO;
	}

	public GiftDAO getGiftDAO() {
		return giftDAO;
	}

	@Transactional(readOnly = true)
	public List<GiftBean> select(GiftBean bean) {
		List<GiftBean> result = null;
		if (bean != null && bean.getGiftId() != null && !Integer.valueOf(0).equals(bean.getGiftId())) {
			bean = getGiftDAO().select(bean.getGiftId());
			if (bean != null) {
				result = new ArrayList<GiftBean>();
				result.add(bean);
			} else {
				result = getGiftDAO().select();
			}
		} else {
			result = getGiftDAO().select();
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