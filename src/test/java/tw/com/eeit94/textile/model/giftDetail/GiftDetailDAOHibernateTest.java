package tw.com.eeit94.textile.model.giftDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.gift.GiftDAO;
import tw.com.eeit94.textile.model.gift.GiftDAOHibernate;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailBean;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailDAO;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailDAOHibernate;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailPK;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class GiftDetailDAOHibernateTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		GiftDetailDAO giftDetailDao = (GiftDetailDAOHibernate) ctx.getBean("giftDetailDAO");
		GiftDAO giftDao = (GiftDAOHibernate) ctx.getBean("giftDAO");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by id
		// System.out.println(giftDetailDao.select(new GiftDetailPK(2, 1)));

		// Select all
		// System.out.println(giftDetailDao.select());

		// Insert
		// GiftBean giftBean = new GiftBean();
		// giftBean.setGiverId(5);
		// giftBean.setRecipientId(1);
		// giftBean.setGiveDate(new Timestamp(System.currentTimeMillis()));
		// GiftBean temp = giftDao.insert(giftBean);
		// GiftDetailBean bean1 = new GiftDetailBean();
		// bean1.setGiftDetailPK(new GiftDetailPK(temp.getGiftId(), 1));
		// bean1.setAmount(99);
		// giftDetailDao.insert(bean1);
		// GiftDetailBean bean2 = new GiftDetailBean();
		// bean2.setGiftDetailPK(new GiftDetailPK(temp.getGiftId(), 3));
		// bean2.setAmount(999);
		// System.out.println(giftDetailDao.insert(bean2));

		// Update
		// GiftDetailBean bean = giftDetailDao.select(new GiftDetailPK(4, 3));
		// bean.setAmount(77);
		// System.out.println(giftDetailDao.update(bean));

		// Delete
		GiftDetailBean bean = giftDetailDao.select(new GiftDetailPK(4, 3));
		System.out.println(giftDetailDao.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}