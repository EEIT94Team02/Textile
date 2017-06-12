package tw.com.eeit94.textile.model.deal;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.deal.DealDAO;
import tw.com.eeit94.textile.model.deal.DealDAOHibernate;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DealDAOHibernateTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		DealDAO dealDao = (DealDAOHibernate) ctx.getBean("dealDAO");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by id
		// System.out.println(dealDao.select(1));

		// Select all
		// System.out.println(dealDao.select());

		// Insert
		DealBean bean = new DealBean();
		bean.setMemberId(2);
		bean.setDealDate(new Timestamp(System.currentTimeMillis()));
		bean.setTotalCost(300);
		System.out.println(dealDao.insert(bean));

		// Update
		// DealBean bean = new DealBean();
		// bean.setDealId(5);
		// bean.setMemberId(2);
		// bean.setDealDate(new Timestamp(System.currentTimeMillis()));
		// bean.setTotalCost(400);
		// System.out.println(dealDao.update(bean));

		// Delete
		// DealBean bean = new DealBean();
		// bean.setDealId(4);
		// System.out.println(dealDao.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}