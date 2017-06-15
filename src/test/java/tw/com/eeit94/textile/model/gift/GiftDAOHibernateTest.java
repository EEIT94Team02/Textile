package tw.com.eeit94.textile.model.gift;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * gift dao的測試程式。
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

		// Select by giftId getGiftDetailBeans
		// GiftBean bean = giftDao.select(1);
		// System.out.println(bean.getGiftDetailBeans());

		// Select all by memberId
		// System.out.println(giftDao.selectAll(5));

		// Select by recipient name
		// GiftQueryConditionUtil queryCondition = new GiftQueryConditionUtil();
		// queryCondition.setRecipientName("eva");
		// queryCondition.setGiveDateBefore(new java.util.Date());
		// System.out.println(giftDao.selectConditional(queryCondition));

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
		// GiftBean bean = giftDao.select(4);
		// System.out.println(giftDao.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}