package tw.com.eeit94.textile.model.deal;

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
@Repository(value = "dealDAO")
public class DealDAOHibernate implements DealDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public DealDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public DealBean select(int dealId) {
		return getSession().get(DealBean.class, dealId);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.deal.DealBean";

	@Override
	public List<DealBean> select() {
		return getSession().createQuery(SELECT_ALL, DealBean.class).getResultList();
	}

	@Override
	public DealBean insert(DealBean bean) {
		DealBean result = null;
		if (bean != null) {
			if (bean.getDealId() == null) {
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public DealBean update(DealBean bean) {
		DealBean result = null;
		if (bean != null) {
			result = getSession().get(DealBean.class, bean.getDealId());
			if (result != null) {
				getSession().evict(result);
				getSession().update(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public boolean delete(DealBean bean) {
		DealBean result = null;
		if (bean != null) {
			result = getSession().get(DealBean.class, bean.getDealId());
			if (result != null) {
				DealBean temp = new DealBean();
				temp.setDealId(bean.getDealId());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}