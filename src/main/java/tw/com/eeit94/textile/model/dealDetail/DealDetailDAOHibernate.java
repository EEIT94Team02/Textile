package tw.com.eeit94.textile.model.dealDetail;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * deal_detail表格的CRUD，以hibernate實作。
 * 
 * @author 李
 * @version 2017/06/13
 */
@Repository(value = "dealDetailDAO")
public class DealDetailDAOHibernate implements DealDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	// 取得dealId的情況下，方能查詢明細，故select方法只有一個。
	@Override
	public List<DealDetailBean> select(int dealId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<DealDetailBean> cq = cb.createQuery(DealDetailBean.class);
		Root<DealDetailBean> dealDetailBean = cq.from(DealDetailBean.class);
		Predicate p = cb.equal(dealDetailBean.<DealDetailPK>get("dealDetailPK").<Integer>get("dealId"), dealId);
		return getSession().createQuery(cq.where(p)).getResultList();
	}

	@Override
	public DealDetailBean insert(DealDetailBean bean) {
		DealDetailBean result = null;
		if (bean != null) {
			result = getSession().get(DealDetailBean.class, bean.getDealDetailPK());
			if (result == null) {
				if (bean.getDealBean() == null) {
					bean.setDealBean(getSession().get(DealBean.class, bean.getDealDetailPK().getDealId()));
				}
				if (bean.getProductBean() == null) {
					bean.setProductBean(getSession().get(ProductBean.class, bean.getDealDetailPK().getProductId()));
				}
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public DealDetailBean update(DealDetailBean bean) {
		DealDetailBean result = null;
		if (bean != null) {
			result = getSession().get(DealDetailBean.class, bean.getDealDetailPK());
			if (result != null) {
				result.setAmount(bean.getAmount());
				getSession().update(result);
			}
		}
		return result;
	}

	@Override
	public boolean delete(DealDetailBean bean) {
		DealDetailBean result = null;
		if (bean != null) {
			result = getSession().get(DealDetailBean.class, bean.getDealDetailPK());
			if (result != null) {
				DealDetailBean temp = new DealDetailBean();
				temp.setDealDetailPK(bean.getDealDetailPK());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}