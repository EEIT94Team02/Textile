package tw.com.eeit94.textile.model.dealDetail;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.deal.DealDAO;
import tw.com.eeit94.textile.model.deal.DealDAOHibernate;
import tw.com.eeit94.textile.model.dealDetail.DealDetailBean;
import tw.com.eeit94.textile.model.dealDetail.DealDetailDAO;
import tw.com.eeit94.textile.model.dealDetail.DealDetailDAOHibernate;
import tw.com.eeit94.textile.model.dealDetail.DealDetailPK;
import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DealDetailDAOHibernateTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		DealDAO dealDao = (DealDAOHibernate) ctx.getBean("dealDAO");
		DealDetailDAO dealDetailDao = (DealDetailDAOHibernate) ctx.getBean("dealDetailDAO");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by id
		// System.out.println(dealDetailDao.select(new DealDetailPK(1, 2)));

		// Select all
		// System.out.println(dealDetailDao.select());

		// Insert
		// DealBean dealBean = new DealBean();
		// dealBean.setMemberId(2);
		// dealBean.setDealDate(new Timestamp(System.currentTimeMillis()));
		// dealBean.setTotalCost(300);
		// dealDao.insert(dealBean);
		// DealDetailBean dealDetailBean = new DealDetailBean();
		// dealDetailBean.setDealDetailPK(new DealDetailPK(dealBean.getDealId(),
		// 1));
		// dealDetailBean.setAmount(5);
		// dealDetailDao.insert(dealDetailBean);
		// DealDetailBean dealDetailBean2 = new DealDetailBean();
		// dealDetailBean2.setDealDetailPK(new
		// DealDetailPK(dealBean.getDealId(), 2));
		// dealDetailBean2.setAmount(10);
		// dealDetailDao.insert(dealDetailBean2);
		// DealDetailBean dealDetailBean3 = new DealDetailBean();
		// dealDetailBean3.setDealDetailPK(new
		// DealDetailPK(dealBean.getDealId(), 3));
		// dealDetailBean3.setAmount(20);
		// dealDetailDao.insert(dealDetailBean3);
		// System.out.println(dealDetailDao.select());

		// Update
		// DealDetailBean bean = dealDetailDao.select(new DealDetailPK(4, 2));
		// int origin = bean.getAmount();
		// bean.setAmount(5);
		// DealDetailBean temp = dealDetailDao.update(bean);
		// DealBean dealBean = temp.getDealBean();
		// dealBean.setTotalCost(dealBean.getTotalCost().intValue() + ((5 -
		// origin)*temp.getProductBean().getUnitPrice().intValue()));
		// dealDao.update(dealBean);

		// Delete
		// DealDetailBean bean = dealDetailDao.select(new DealDetailPK(4, 2));
		// DealBean dealBean = bean.getDealBean();
		// dealBean.setTotalCost(dealBean.getTotalCost() -
		// bean.getAmount()*bean.getProductBean().getUnitPrice());
		// dealDao.update(dealBean);
		// dealDetailDao.delete(bean);

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}