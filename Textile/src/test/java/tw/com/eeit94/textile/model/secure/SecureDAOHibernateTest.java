package tw.com.eeit94.textile.model.secure;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試SecureDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/11
 */
public class SecureDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		SecureDAO secureDAO = (SecureDAO) context.getBean("secureDAO");

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(secureDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();
		
		SecureBean sbean = new SecureBean();
		sbean.setsTarget(ConstSecureParameter.PASSWORD.param());
		
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(secureDAO.selectByTarget(sbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}