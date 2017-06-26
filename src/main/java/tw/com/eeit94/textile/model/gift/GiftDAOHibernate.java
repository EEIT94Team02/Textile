package tw.com.eeit94.textile.model.gift;

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
 * gift表格的CRUD，以Hibernate實作。
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
	public GiftBean select(Integer giftId) {
		return getSession().get(GiftBean.class, giftId);
	}
	
	//  以使用者會員Id查詢其所有收送禮記錄。
	@Override
	public List<GiftBean> selectAll(Integer userId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<GiftBean> cq = cb.createQuery(GiftBean.class);
		Root<GiftBean> giftBean = cq.from(GiftBean.class);
		Predicate pGiver = cb.equal(giftBean.<MemberBean>get("giverBean").<Integer>get("mId"), userId);
		Predicate pRecipient = cb.equal(giftBean.<MemberBean>get("recipientBean").<Integer>get("mId"), userId);
		Predicate pOr = cb.or(pGiver, pRecipient);
		return getSession().createQuery(cq.where(pOr)).getResultList();
	}

	// gift表格的條件查詢
	// 將使用者會員Id以送禮人或收禮人的身分查詢，輔以姓名模糊查詢， 或時間區間查詢送禮時間。
	@Override
	public List<GiftBean> selectConditional(GiftConditionUtil queryCondition) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<GiftBean> cq = cb.createQuery(GiftBean.class);
		Root<GiftBean> giftBean = cq.from(GiftBean.class);
		List<Predicate> pList = new ArrayList<Predicate>();
		Predicate byId = null;
		Predicate byName = null;
		Predicate byDate = null;
		if (queryCondition.getGiverId() != null || queryCondition.getRecipientId() != null) {
			if (queryCondition.getGiverId() == null) {
				byId = cb.equal(giftBean.<MemberBean>get("recipientBean").<Integer>get("mId"), queryCondition.getRecipientId());
				if (queryCondition.getGiverName() != null) {
					byName = cb.like(giftBean.<MemberBean>get("giverBean").<String>get("mName"),
							"%" + queryCondition.getGiverName() + "%");
					pList.add(byName);
				}
			} else {
				byId = cb.equal(giftBean.<MemberBean>get("giverBean").<Integer>get("mId"), queryCondition.getGiverId());
				if (queryCondition.getRecipientName() != null) {
					byName = cb.like(giftBean.<MemberBean>get("recipientBean").<String>get("mName"),
							"%" + queryCondition.getRecipientName() + "%");
					pList.add(byName);
				}
			}
			pList.add(byId);
			if (queryCondition.getGiveDateAfter() != null || queryCondition.getGiveDateBefore() != null) {
				if (queryCondition.getGiveDateAfter() == null) {
					byDate = cb.between(giftBean.<Timestamp>get("giveDate"), new java.util.Date(0),
							queryCondition.getGiveDateBefore());
				} else if (queryCondition.getGiveDateBefore() == null) {
					byDate = cb.between(giftBean.<Timestamp>get("giveDate"), queryCondition.getGiveDateAfter(),
							new java.util.Date(System.currentTimeMillis()));
				} else {
					byDate = cb.between(giftBean.<Timestamp>get("giveDate"), queryCondition.getGiveDateAfter(),
							queryCondition.getGiveDateBefore());
				}
				pList.add(byDate);
			}
		}
		Predicate[] pArray = pList.toArray(new Predicate[pList.size()]);
		return getSession().createQuery(cq.where(pArray)).getResultList();
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