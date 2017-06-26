package tw.com.eeit94.textile.model.deposit;

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

import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * deposit表格資料的CRUD，以hibernate實作。
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
	
	// deposit表格的查詢
	// 以使用者會員Id為主，時間區間為輔做條件查詢。
	@Override
	public List<DepositBean> selectConditional(DepositConditionUtil queryCondition) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<DepositBean> cq = cb.createQuery(DepositBean.class);
		Root<DepositBean> depositBean = cq.from(DepositBean.class);
		List<Predicate> pList = new ArrayList<Predicate>();
		Predicate byId = null;
		Predicate byDate = null;
		if (queryCondition.getMemberId() != null) {
			byId = cb.equal(depositBean.<MemberBean>get("memberBean").<Integer>get("mId"), 
					queryCondition.getMemberId());
			pList.add(byId);
			if (queryCondition.getDepositDateAfter() != null || queryCondition.getDepositDateBefore() != null) {
				if (queryCondition.getDepositDateAfter() == null) {
					byDate = cb.between(depositBean.<Timestamp>get("depositDate"), 
							new java.util.Date(0), queryCondition.getDepositDateBefore());
				} else if (queryCondition.getDepositDateBefore() == null) {
					byDate = cb.between(depositBean.<Timestamp>get("depositDate"), 
							queryCondition.getDepositDateAfter(), new java.util.Date(System.currentTimeMillis()));
				} else {
					byDate = cb.between(depositBean.<Timestamp>get("depositDate"), 
							queryCondition.getDepositDateAfter(), queryCondition.getDepositDateBefore());
				}
				pList.add(byDate);
			}
		}
		Predicate[] pArray = pList.toArray(new Predicate[pList.size()]);
		return getSession().createQuery(cq.where(pArray)).getResultList();
	}
	
	@Override
	public DepositBean insert(DepositBean bean) {
		DepositBean result = null;
		if (bean != null) {
			if (bean.getDepositId() == null) {
//				bean.setMemberBean(getSession().get(MemberBean.class, bean.get));
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