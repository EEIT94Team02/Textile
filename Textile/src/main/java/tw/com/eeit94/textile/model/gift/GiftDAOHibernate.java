package tw.com.eeit94.textile.model.gift;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Repository(value = "giftDAO")
public class GiftDAOHibernate implements GiftDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public GiftBean select(int giftId) {
		return getSession().get(GiftBean.class, giftId);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.gift.GiftBean";

	@Override
	public List<GiftBean> select() {
		return getSession().createQuery(SELECT_ALL, GiftBean.class).getResultList();
	}

	@Override
	public GiftBean insert(GiftBean bean) {
		GiftBean result = null;
		if (bean != null) {
			if (bean.getGiftId() == null) {
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public GiftBean update(GiftBean bean) {
		GiftBean result = null;
		if (bean != null) {
			result = getSession().get(GiftBean.class, bean.getGiftId());
			if (result != null) {
				getSession().evict(result);
				getSession().update(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public boolean delete(GiftBean bean) {
		GiftBean result = null;
		if (bean != null) {
			result = getSession().get(GiftBean.class, bean.getGiftId());
			if (result != null) {
				GiftBean temp = new GiftBean();
				temp.setGiftId(bean.getGiftId());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}