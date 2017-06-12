package tw.com.eeit94.textile.model.gift;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.gift.GiftBean;
import tw.com.eeit94.textile.model.gift.GiftService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class GiftServiceTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		GiftService gs = (GiftService) ctx.getBean("giftService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// GiftBean bean = new GiftBean();
		// bean.setGiftId(1);
		// System.out.println(gs.select(bean));

		// Insert
		// GiftBean bean = new GiftBean();
		// bean.setGiverId(5);
		// bean.setRecipientId(1);
		// bean.setGiveDate(new Timestamp(System.currentTimeMillis()));
		// System.out.println(gs.insert(bean));

		// Update
		// GiftBean temp = new GiftBean();
		// temp.setGiftId(4);
		// GiftBean bean = gs.select(temp).get(0);
		// bean.setGiveDate(new Timestamp(System.currentTimeMillis()));
		// System.out.println(gs.update(bean));

		// Delete
		// GiftBean bean = new GiftBean();
		// bean.setGiftId(4);
		// System.out.println(gs.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}