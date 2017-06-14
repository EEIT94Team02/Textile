package tw.com.eeit94.textile.model.giftDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.giftDetail.GiftDetailBean;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailPK;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class GiftDetailServiceTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		GiftDetailService gds = (GiftDetailService) ctx.getBean("giftDetailService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// GiftDetailBean bean = new GiftDetailBean();
		// bean.setGiftDetailPK(new GiftDetailPK(1, 1));
		// System.out.println(gds.select(bean));

		// Insert
		// GiftDetailBean bean = new GiftDetailBean();
		// bean.setGiftDetailPK(new GiftDetailPK(1, 1));
		// bean.setAmount(21);
		// gds.insert(bean);
		// GiftDetailBean bean2 = new GiftDetailBean();
		// bean2.setGiftDetailPK(new GiftDetailPK(2, 3));
		// bean2.setAmount(100);
		// gds.insert(bean2);
		// GiftDetailBean bean3 = new GiftDetailBean();
		// bean3.setGiftDetailPK(new GiftDetailPK(3, 2));
		// bean3.setAmount(11);
		// gds.insert(bean3);
		// System.out.println(gds.select(null));

		// Update
		// GiftDetailBean temp = new GiftDetailBean();
		// temp.setGiftDetailPK(new GiftDetailPK(1, 1));
		// GiftDetailBean bean = gds.select(temp).get(0);
		// bean.setAmount(213);
		// gds.update(bean);
		// System.out.println(gds.select(bean));

		// Delete
		// GiftDetailBean bean = new GiftDetailBean();
		// bean.setGiftDetailPK(new GiftDetailPK(1, 1));
		// System.out.println(gds.delete(bean));
		// bean.setGiftDetailPK(new GiftDetailPK(2, 3));
		// System.out.println(gds.delete(bean));
		// bean.setGiftDetailPK(new GiftDetailPK(3, 2));
		// System.out.println(gds.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}