package tw.com.eeit94.textile.model.chatroom_log;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.chatroom.ChatroomDAO;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試Chatrom_LogDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class Chatrom_LogDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		ChatroomDAO chatroomDAO = (ChatroomDAO) context.getBean("chatroomDAO");
		Chatroom_LogDAO chatroom_LogDAO = (Chatroom_LogDAO) context.getBean("chatroom_LogDAO");

		// 必須先在聊天室主表新增一筆資料，再取得此主鍵。
		ChatroomBean cbean = new ChatroomBean();
		cbean.setcCreateTime(new Timestamp(System.currentTimeMillis()));
		cbean.setcClass("個人");
		sessionFactory.getCurrentSession().beginTransaction();
		chatroomDAO.insert(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();
		
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.selectAll());
		TestUtils.printResult(chatroom_LogDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		Chatroom_LogBean c_lbean1 = new Chatroom_LogBean();
		Chatroom_LogPK chatroom_LogPK1 = new Chatroom_LogPK();
		chatroom_LogPK1.setcId(cbean.getcId());
		chatroom_LogPK1.setmId(1);
		chatroom_LogPK1.setcSendTime(new Timestamp(System.currentTimeMillis()));
		c_lbean1.setChatroom_LogPK(chatroom_LogPK1);
		c_lbean1.setcContent("安安，你好嗎？");
		Chatroom_LogBean c_lbean2 = new Chatroom_LogBean();
		Chatroom_LogPK chatroom_LogPK2 = new Chatroom_LogPK();
		chatroom_LogPK2.setcId(cbean.getcId());
		chatroom_LogPK2.setmId(2);
		chatroom_LogPK2.setcSendTime(new Timestamp(System.currentTimeMillis()));
		c_lbean2.setChatroom_LogPK(chatroom_LogPK2);
		c_lbean2.setcContent("好啊！");

		sessionFactory.getCurrentSession().beginTransaction();
		chatroom_LogDAO.insert(c_lbean1);
		chatroom_LogDAO.insert(c_lbean2);
		// 因為使用LAZY，所以在commit之前必須先強迫程式使用get方法，才會去資料庫抓資料。
		TestUtils.printResult(c_lbean1);
		TestUtils.printResult(c_lbean2);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		cbean = chatroomDAO.select(cbean).get(0);
		chatroomDAO.delete(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.selectAll());
		TestUtils.printResult(chatroom_LogDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}