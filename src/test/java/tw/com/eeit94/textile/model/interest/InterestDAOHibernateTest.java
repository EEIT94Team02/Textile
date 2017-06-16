package tw.com.eeit94.textile.model.interest;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試InterestDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class InterestDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		InterestDAO interestDAO = (InterestDAO) context.getBean("interestDAO");

		TestUtils.printResult("列出所有interest的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interestDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		InterestBean ibean = new InterestBean();
		ibean.setiClass(3);
		ibean.setiName("義式");

		TestUtils.printResult("新增一筆interest的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		interestDAO.insert(ibean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("列出一筆interest的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interestDAO.select(ibean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("修改一筆interest的資料");
		ibean.setiName("法式");
		sessionFactory.getCurrentSession().beginTransaction();
		interestDAO.update(ibean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("列出一筆interest的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interestDAO.select(ibean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("刪除一筆interest的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		interestDAO.delete(ibean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("重新列出所有interest的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interestDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}