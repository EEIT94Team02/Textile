package tw.com.eeit94.textile.model.giftDetail;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.gift.GiftBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * gift_detail表格的CRUD，以hibernate實作。
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
	
	
	// 只有獲得giftId的情況下，方能查詢明細，故select方法只有一個。
	@Override
	public List<GiftDetailBean> select(Integer giftId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<GiftDetailBean> cq = cb.createQuery(GiftDetailBean.class);
		Root<GiftDetailBean> giftDetailBean = cq.from(GiftDetailBean.class);
		Predicate p = cb.equal(giftDetailBean.<GiftDetailPK>get("giftDetailPK").<Integer>get("giftId"),
				giftId);
		return getSession().createQuery(cq.where(p)).getResultList();
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