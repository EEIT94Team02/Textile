package tw.com.eeit94.textile.model.deposit;

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
@Repository(value = "depositDAO")
public class DepositDAOHibernate implements DepositDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public DepositDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public DepositBean select(int depositId) {
		return getSession().get(DepositBean.class, depositId);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.deposit.DepositBean";

	@Override
	public List<DepositBean> select() {
		return getSession().createQuery(SELECT_ALL, DepositBean.class).getResultList();
	}

	@Override
	public DepositBean insert(DepositBean bean) {
		DepositBean result = null;
		if (bean != null) {
			if (bean.getDepositId() == null) {
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public DepositBean update(DepositBean bean) {
		DepositBean result = null;
		if (bean != null) {
			result = getSession().get(DepositBean.class, bean.getDepositId());
			if (result != null) {
				getSession().evict(result);
				getSession().update(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public boolean delete(DepositBean bean) {
		DepositBean result = null;
		if (bean != null) {
			result = getSession().get(DepositBean.class, bean.getDepositId());
			if (result != null) {
				DepositBean temp = new DepositBean();
				temp.setDepositId(bean.getDepositId());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}