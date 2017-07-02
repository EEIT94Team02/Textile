package tw.com.eeit94.textile.model.giftDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.gift.GiftBean;

/**
 * gift_detail表格CRUD的service元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class GiftDetailService {
	@Autowired
	private GiftDetailDAO giftDetailDAO;

	public GiftDetailDAO getGiftDetailDAO() {
		return giftDetailDAO;
	}

	@Transactional(readOnly = true)
	public List<GiftDetailBean> select(GiftBean bean) {
		if (bean != null && bean.getGiftId() != null) {
			return getGiftDetailDAO().select(bean.getGiftId());
		}
		return null;
	}

	public GiftDetailBean insert(GiftDetailBean bean) {
		GiftDetailBean result = null;
		if (bean != null) {
			result = getGiftDetailDAO().insert(bean);
		}
		return result;
	}

	public GiftDetailBean update(GiftDetailBean bean) {
		GiftDetailBean result = null;
		if (bean != null) {
			result = getGiftDetailDAO().update(bean);
		}
		return result;
	}

	public boolean delete(GiftDetailBean bean) {
		if (bean != null) {
			return getGiftDetailDAO().delete(bean);
		}
		return false;
	}
}