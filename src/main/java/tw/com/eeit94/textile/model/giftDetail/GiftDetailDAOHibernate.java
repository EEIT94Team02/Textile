package tw.com.eeit94.textile.model.giftDetail;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.gift.GiftBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Repository(value = "giftDetailDAO")
public class GiftDetailDAOHibernate implements GiftDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public GiftDetailBean select(GiftDetailPK giftDetailPK) {
		return getSession().get(GiftDetailBean.class, giftDetailPK);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.giftDetail.GiftDetailBean";

	@Override
	public List<GiftDetailBean> select() {
		return getSession().createQuery(SELECT_ALL, GiftDetailBean.class).getResultList();
	}

	@Override
	public GiftDetailBean insert(GiftDetailBean bean) {
		GiftDetailBean result = null;
		if (bean != null) {
			result = getSession().get(GiftDetailBean.class, bean.getGiftDetailPK());
			if (result == null) {
				bean.setGiftBean(getSession().get(GiftBean.class, bean.getGiftDetailPK().getGiftId()));
				bean.setProductBean(getSession().get(ProductBean.class, bean.getGiftDetailPK().getProductId()));
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public GiftDetailBean update(GiftDetailBean bean) {
		GiftDetailBean result = null;
		if (bean != null) {
			result = getSession().get(GiftDetailBean.class, bean.getGiftDetailPK());
			if (result != null) {
				getSession().evict(result);
				getSession().update(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public boolean delete(GiftDetailBean bean) {
		GiftDetailBean result = null;
		if (bean != null) {
			result = getSession().get(GiftDetailBean.class, bean.getGiftDetailPK());
			if (result != null) {
				GiftDetailBean temp = new GiftDetailBean();
				temp.setGiftDetailPK(bean.getGiftDetailPK());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}