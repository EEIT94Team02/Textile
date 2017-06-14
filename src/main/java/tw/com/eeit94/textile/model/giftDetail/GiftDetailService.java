package tw.com.eeit94.textile.model.giftDetail;

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
public class GiftDetailService {
	@Autowired
	private GiftDetailDAO giftDetailDAO;

	public GiftDetailService(GiftDetailDAO giftDetailDAO) {
		this.giftDetailDAO = giftDetailDAO;
	}

	public GiftDetailDAO getGiftDetailDAO() {
		return giftDetailDAO;
	}

	@Transactional(readOnly = true)
	public List<GiftDetailBean> select(GiftDetailBean bean) {
		List<GiftDetailBean> result = null;
		if (bean != null && bean.getGiftDetailPK() != null) {
			bean = getGiftDetailDAO().select(bean.getGiftDetailPK());
			if (bean != null) {
				result = new ArrayList<GiftDetailBean>();
				result.add(bean);
			} else {
				result = getGiftDetailDAO().select();
			}
		} else {
			result = getGiftDetailDAO().select();
		}
		return result;
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