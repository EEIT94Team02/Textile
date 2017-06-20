package tw.com.eeit94.textile.model.deal;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.deal.DealService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DealServiceTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		DealService ds = (DealService) ctx.getBean("dealService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// DealBean bean = new DealBean();
		// bean.setDealId(2);
		// System.out.println(ds.select(bean));

		// Insert
		// DealBean bean = new DealBean();
		// bean.setMemberId(4);
		// bean.setDealDate(new Timestamp(System.currentTimeMillis()));
		// bean.setTotalCost(4000);
		// System.out.println(ds.insert(bean));

		// Update
		// DealBean temp = new DealBean();
		// temp.setDealId(4);
		// DealBean bean = ds.select(temp).get(0);
		// bean.setDealDate(new Timestamp(System.currentTimeMillis()));
		// bean.setTotalCost(400);
		// System.out.println(ds.update(bean));

		// Delete
		// DealBean bean = new DealBean();
		// bean.setDealId(4);
		// System.out.println(ds.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}