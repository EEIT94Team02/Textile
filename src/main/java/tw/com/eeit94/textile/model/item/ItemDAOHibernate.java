package tw.com.eeit94.textile.model.item;

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
import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * item表格資料的CRUD，以hibernate實作。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Repository(value = "itemDAO")
public class ItemDAOHibernate implements ItemDAO {
	@Autowired
	private SessionFactory sessionFactory = null;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ItemBean select(ItemPK itemPK) {
		return getSession().get(ItemBean.class, itemPK);
	}

	@Override
	public List<ItemBean> select(ItemConditionUtil queryCondition) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<ItemBean> cq = cb.createQuery(ItemBean.class);
		Root<ItemBean> itemBean = cq.from(ItemBean.class);
		Predicate byId = cb.equal(itemBean.<MemberBean>get("memberBean"), queryCondition.getMemberId());
		return getSession().createQuery(cq.where(byId)).getResultList();
	}

	@Override
	public ItemBean insert(ItemBean bean) {
		ItemBean result = null;
		if (bean != null) {
			result = getSession().get(ItemBean.class, bean.getItemPK());
			if (result == null) {
				bean.setProductBean(getSession().get(ProductBean.class, bean.getItemPK().getProductId()));
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public ItemBean update(ItemBean bean) {
		ItemBean result = null;
		if (bean != null) {
			result = getSession().get(ItemBean.class, bean.getItemPK());
			if (result != null) {
				getSession().evict(result);
				getSession().update(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public boolean delete(ItemBean bean) {
		ItemBean result = null;
		if (bean != null) {
			result = getSession().get(ItemBean.class, bean.getItemPK());
			if (result != null) {
				ItemBean temp = new ItemBean();
				temp.setItemPK(bean.getItemPK());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}