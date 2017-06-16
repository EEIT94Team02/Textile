package tw.com.eeit94.textile.model.deposit;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.deposit.DepositBean;
import tw.com.eeit94.textile.model.deposit.DepositService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DepositServiceTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		DepositService ds = (DepositService) ctx.getBean("depositService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// DepositBean bean = new DepositBean();
		// bean.setDepositId(1);
		// System.out.println(ds.select(bean));

		// Insert
		// DepositBean bean = new DepositBean();
		// bean.setMemberId(8);
		// bean.setDepositDate(new Timestamp(System.currentTimeMillis()));
		// bean.setDepositAmount(100);
		// bean.setVirtualPoints((int)(100*1.4));
		// System.out.println(ds.insert(bean));

		// Update
		// DepositBean temp = new DepositBean();
		// temp.setDepositId(4);
		// DepositBean bean = ds.select(temp).get(0);
		// bean.setDepositAmount(300);
		// bean.setVirtualPoints((int) (300*1.4));
		// System.out.println(ds.update(bean));

		// Delete
		// DepositBean bean = new DepositBean();
		// bean.setDepositId(4);
		// System.out.println(ds.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}