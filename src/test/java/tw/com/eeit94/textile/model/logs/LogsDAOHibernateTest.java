package tw.com.eeit94.textile.model.logs;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試LogsDAOHibernateTest的程式。
 * 
 * @author 賴
 * @version 2017/06/12
 */
public class LogsDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		LogsDAO logsDAO = (LogsDAO) context.getBean("LogsDAO");

		TestUtils.printResult("列出所有logs的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(logsDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		LogsBean lbean = new LogsBean();
		lbean.setlCreateTime(new Timestamp(System.currentTimeMillis()));
		lbean.setlLog("系統新增記錄訊息測試");

		TestUtils.printResult("新增一筆logs的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		logsDAO.insert(lbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("列出一筆logs的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(logsDAO.select(lbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("修改一筆logs的資料");
		lbean.setlLog("系統修改記錄訊息測試");
		sessionFactory.getCurrentSession().beginTransaction();
		logsDAO.update(lbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("列出一筆logs的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(logsDAO.select(lbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("刪除一筆logs的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		logsDAO.delete(lbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("重新列出所有logs的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(logsDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}