package tw.com.eeit94.textile.model.deposit;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.deposit.DepositBean;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DepositBeanTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by id
		// DepositBean result = session.get(DepositBean.class, 1);
		// System.out.println(result);

		// Select all
		// List<DepositBean> resultAll = session.createQuery("from
		// tw.com.eeit94.textile.model.deposit.DepositBean").getResultList();
		// System.out.println(resultAll);

		// Insert
		// DepositBean resultInsert = new DepositBean();
		// resultInsert.setMemberId(3);
		// resultInsert.setDepositDate(new
		// Timestamp(System.currentTimeMillis()));
		// resultInsert.setDepositAmount(150);
		// resultInsert.setVirtualPoints((int) (150 * 1.4));
		// session.save(resultInsert);

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}