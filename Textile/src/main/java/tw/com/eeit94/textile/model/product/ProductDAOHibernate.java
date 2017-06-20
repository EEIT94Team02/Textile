package tw.com.eeit94.textile.model.product;

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
@Repository(value = "productDAO")
public class ProductDAOHibernate implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory = null;

	public ProductDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ProductBean select(int productId) {
		return getSession().get(ProductBean.class, productId);
	}

	private static final String SELECT_ALL = "from tw.com.eeit94.textile.model.product.ProductBean";

	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = getSession().createQuery(SELECT_ALL, ProductBean.class).getResultList();
		return result;
	}

	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			if (bean.getProductId() == null) {
				getSession().save(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = getSession().get(ProductBean.class, bean.getProductId());
			if (result != null) {
				getSession().evict(result);
				getSession().update(bean);
				result = bean;
			}
		}
		return result;
	}

	@Override
	public boolean delete(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = getSession().get(ProductBean.class, bean.getProductId());
			if (result != null) {
				ProductBean temp = new ProductBean();
				temp.setProductId(bean.getProductId());
				getSession().evict(result);
				getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
}