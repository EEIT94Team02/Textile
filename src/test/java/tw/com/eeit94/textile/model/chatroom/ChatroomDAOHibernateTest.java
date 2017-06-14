package tw.com.eeit94.textile.model.chatroom;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試ChatroomDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class ChatroomDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		ChatroomDAO chatroomDAO = (ChatroomDAO) context.getBean("chatroomDAO");

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		ChatroomBean cbean = new ChatroomBean();
		cbean.setcCreateTime(new Timestamp(System.currentTimeMillis()));
		cbean.setcClass("個人");

		sessionFactory.getCurrentSession().beginTransaction();
		chatroomDAO.insert(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.select(cbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		cbean.setcClass("群組");
		sessionFactory.getCurrentSession().beginTransaction();
		chatroomDAO.update(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.select(cbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		chatroomDAO.delete(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}