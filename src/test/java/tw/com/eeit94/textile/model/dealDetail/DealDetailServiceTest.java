package tw.com.eeit94.textile.model.dealDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.dealDetail.DealDetailBean;
import tw.com.eeit94.textile.model.dealDetail.DealDetailPK;
import tw.com.eeit94.textile.model.dealDetail.DealDetailService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DealDetailServiceTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		DealDetailService dds = (DealDetailService) ctx.getBean("dealDetailService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// DealDetailBean bean = new DealDetailBean();
		// bean.setDealDetailPK(new DealDetailPK(1, 1));
		// System.out.println(dds.select(bean));

		// Insert
		// DealDetailBean bean = new DealDetailBean();
		// bean.setDealDetailPK(new DealDetailPK(2, 2));
		// bean.setAmount(12);
		// System.out.println(dds.insert(bean));

		// Update
		// DealDetailBean temp = new DealDetailBean();
		// temp.setDealDetailPK(new DealDetailPK(2, 2));
		// DealDetailBean bean = dds.select(temp).get(0);
		// bean.setAmount(324);
		// System.out.println(dds.update(bean));

		// Delete
		// DealDetailBean bean = new DealDetailBean();
		// bean.setDealDetailPK(new DealDetailPK(2, 2));
		// System.out.println(dds.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}