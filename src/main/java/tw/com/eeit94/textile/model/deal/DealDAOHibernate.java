package tw.com.eeit94.textile.model.deal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * deal表格的CRUD及條件查詢，以Hibernate實作。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Repository(value = "dealDAO")
public class DealDAOHibernate implements DealDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public DealBean select(int dealId) {
		return getSession().get(DealBean.class, dealId);
	}
	
	// deal表格的條件查詢
	// 以使用者的會員Id查詢其所有交易紀錄，輔以時間區間查詢交易時間。
	public List<DealBean> selectConditional(DealConditionUtil queryCondition) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<DealBean> cq = cb.createQuery(DealBean.class);
		Root<DealBean> dealBean = cq.from(DealBean.class);
		List<Predicate> pList = new ArrayList<Predicate>();
		Predicate byId = null;
		Predicate byDate = null;
		if (queryCondition.getMemberId() != null) {
			byId = cb.equal(dealBean.<Integer>get("memberId"), queryCondition.getMemberId());
			if (queryCondition.getDealDateAfter() != null || queryCondition.getDealDateBefore() != null) {
				if (queryCondition.getDealDateBefore() == null) {
					byDate = cb.between(dealBean.<Timestamp>get("dealDate"), 
							queryCondition.getDealDateAfter(), new java.util.Date());
				} else if (queryCondition.getDealDateAfter() == null) {
					byDate = cb.between(dealBean.<Timestamp>get("dealDate"), 
							new java.util.Date(0), queryCondition.getDealDateBefore());
				} else {
					byDate = cb.between(dealBean.<Timestamp>get("dealDate"), 
							queryCondition.getDealDateAfter(), queryCondition.getDealDateBefore());
				}
			}
			pList.add(byId);
			pList.add(byDate);
		}
		Predicate[] pArray = pList.toArray(new Predicate[pList.size()]);
		return getSession().createQuery(cq.where(pArray)).getResultList();
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