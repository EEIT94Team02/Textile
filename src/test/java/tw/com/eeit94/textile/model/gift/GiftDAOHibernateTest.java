package tw.com.eeit94.textile.model.gift;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.gift.GiftBean;
import tw.com.eeit94.textile.model.gift.GiftDAO;
import tw.com.eeit94.textile.model.gift.GiftDAOHibernate;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class GiftDAOHibernateTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		GiftDAO giftDao = (GiftDAOHibernate) ctx.getBean("giftDAO");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by id
		// System.out.println(giftDao.select(1));

		// Select all
		// System.out.println(giftDao.select());

		// Insert
		// GiftBean bean = new GiftBean();
		// bean.setGiverId(5);
		// bean.setRecipientId(1);
		// bean.setGiveDate(new Timestamp(System.currentTimeMillis()));
		// System.out.println(giftDao.insert(bean));

		// Update
		// GiftBean bean = giftDao.select(4);
		// bean.setGiveDate(new Timestamp(System.currentTimeMillis()));
		// System.out.println(giftDao.update(bean));

		// Delete
		GiftBean bean = giftDao.select(4);
		System.out.println(giftDao.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}