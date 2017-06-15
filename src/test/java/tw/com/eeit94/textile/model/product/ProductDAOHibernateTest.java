package tw.com.eeit94.textile.model.product;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.model.product.ProductDAO;
import tw.com.eeit94.textile.model.product.ProductDAOHibernate;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * product dao的測試程式。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class ProductDAOHibernateTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		ProductDAO productDao = (ProductDAOHibernate) ctx.getBean("productDAO");
		session.beginTransaction();

		// Select by id
		// ProductBean resultById = productDao.select(1);
		// System.out.println(resultById);

		// Select all
		// List<ProductBean> resultAll = productDao.select();
		// System.out.println(resultAll);

		// Insert
		// ProductBean beanInsert = new ProductBean();
		// beanInsert.setProductName("Hearts");
		// beanInsert.setUnitPrice(22);
		// beanInsert.setCategory("送禮用");
		// beanInsert.setIntro("美美的");
		// beanInsert.setStatus("上架");
		// ProductBean resultInsert = productDao.insert(beanInsert);

		// Update
		// ProductBean beanUpdate = new ProductBean();
		// beanUpdate.setProductId(1);
		// beanUpdate.setProductName("Hearts");
		// beanUpdate.setUnitPrice(23);
		// beanUpdate.setCategory("送禮用");
		// beanUpdate.setIntro("美的");
		// beanUpdate.setStatus("下架");
		// beanUpdate.setRewardPoints(38);
		// System.out.println(productDao.update(beanUpdate));

		// Delete
		// ProductBean beanDelete = new ProductBean();
		// beanDelete.setProductId(4);
		// System.out.println(productDao.delete(beanDelete));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}