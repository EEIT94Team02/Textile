package tw.com.eeit94.textile.model.dealDetail;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Repository(value = "dealDetailDAO")
public class DealDetailDAOHibernate implements DealDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public DealDetailDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public DealDetailBean select(DealDetailPK dealDetailPK) {
		return getSession().get(DealDetailBean.class, dealDetailPK);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.dealDetail.DealDetailBean";

	@Override
	public List<DealDetailBean> select() {
		return getSession().createQuery(SELECT_ALL, DealDetailBean.class).getResultList();
	}

	@Override
	public DealDetailBean insert(DealDetailBean bean) {
		DealDetailBean result = null;
		if (bean != null) {
			result = getSession().get(DealDetailBean.class, bean.getDealDetailPK());
			if (result == null) {
				bean.setDealBean(getSession().get(DealBean.class, bean.getDealDetailPK().getDealId()));
				bean.setProductBean(getSession().get(ProductBean.class, bean.getDealDetailPK().getProductId()));
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