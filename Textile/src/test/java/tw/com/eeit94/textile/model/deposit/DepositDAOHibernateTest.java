package tw.com.eeit94.textile.model.deposit;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.deposit.DepositBean;
import tw.com.eeit94.textile.model.deposit.DepositDAO;
import tw.com.eeit94.textile.model.deposit.DepositDAOHibernate;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class DepositDAOHibernateTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		DepositDAO depositDao = (DepositDAOHibernate) ctx.getBean("depositDAO");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by id
		// System.out.println(depositDao.select(1));

		// Select all
		// System.out.println(depositDao.select());

		// Insert
		// DepositBean bean = new DepositBean();
		// bean.setMemberId(3);
		// bean.setDepositDate(new Timestamp(System.currentTimeMillis()));
		// bean.setDepositAmount(150);
		// bean.setVirtualPoints((int)(150*1.4));
		// depositDao.insert(bean);

		// Update
		// DepositBean bean = new DepositBean();
		// bean.setDepositId(4);
		// bean.setMemberId(3);
		// bean.setDepositDate(new Timestamp(System.currentTimeMillis()));
		// bean.setDepositAmount(1500);
		// bean.setVirtualPoints((int)(1500*1.4));
		// depositDao.update(bean);

		// Delete
		DepositBean bean = new DepositBean();
		bean.setDepositId(4);
		System.out.println(depositDao.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}