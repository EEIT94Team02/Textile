package tw.com.eeit94.textile.model.item;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.product.ProductBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Repository(value = "itemDAO")
public class ItemDAOHibernate implements ItemDAO {
	@Autowired
	private SessionFactory sessionFactory = null;

	public ItemDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ItemBean select(ItemPK itemPK) {
		return getSession().get(ItemBean.class, itemPK);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.item.ItemBean";

	@Override
	public List<ItemBean> select() {
		return getSession().createQuery(SELECT_ALL, ItemBean.class).getResultList();
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