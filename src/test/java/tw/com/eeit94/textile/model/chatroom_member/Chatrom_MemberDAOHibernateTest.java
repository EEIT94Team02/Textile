package tw.com.eeit94.textile.model.chatroom_member;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.chatroom.ChatroomDAO;
import tw.com.eeit94.textile.model.chatroom.ChatroomDAOHibernate;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberDAO;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試Chatrom_MemberDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class Chatrom_MemberDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		MemberDAO memberDAO = (MemberDAO) context.getBean("memberDAO");
		Chatroom_MemberDAO chatroom_MemberDAO = (Chatroom_MemberDAO) context.getBean("chatroom_MemberDAO");

		// 必須先在會員主表新增一筆資料，再取得此主鍵。
		TestUtils.printResult("新增一筆member和chatroom的資料");
		MemberBean mbean = TestUtils.getNewMemberBean();
		sessionFactory.getCurrentSession().beginTransaction();
		memberDAO.insert(mbean);
		sessionFactory.getCurrentSession().getTransaction().commit();
		// 必須先在聊天室主表新增一筆資料，再取得此主鍵。
		ChatroomDAO chatroomDAO = new ChatroomDAOHibernate(sessionFactory);
		ChatroomBean cbean = new ChatroomBean();
		cbean.setcCreateTime(new Timestamp(System.currentTimeMillis()));
		cbean.setcClass("個人");
		sessionFactory.getCurrentSession().beginTransaction();
		chatroomDAO.insert(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("列出所有member和chatroom的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.selectAll());
		TestUtils.printResult(chatroom_MemberDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		// 模擬同一聊天室中有兩個人，所以各新增一次。
		TestUtils.printResult("新增兩筆chatroom_member資料");
		Chatroom_MemberBean c_mbean1 = new Chatroom_MemberBean();
		Chatroom_MemberPK chatroom_MemberPK1 = new Chatroom_MemberPK();
		chatroom_MemberPK1.setcId(cbean.getcId());
		chatroom_MemberPK1.setmId(mbean.getmId());
		c_mbean1.setChatroom_MemberPK(chatroom_MemberPK1);
		Chatroom_MemberBean c_mbean2 = new Chatroom_MemberBean();
		Chatroom_MemberPK chatroom_MemberPK2 = new Chatroom_MemberPK();
		chatroom_MemberPK2.setcId(cbean.getcId());
		chatroom_MemberPK2.setmId(1);
		c_mbean2.setChatroom_MemberPK(chatroom_MemberPK2);
		sessionFactory.getCurrentSession().beginTransaction();
		chatroom_MemberDAO.insert(c_mbean1);
		chatroom_MemberDAO.insert(c_mbean2);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("列出兩筆chatroom_member資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroom_MemberDAO.select(c_mbean1));
		TestUtils.printResult(chatroom_MemberDAO.select(c_mbean2));
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("藉由刪除一筆刪除chatroom的資料來刪除兩筆chatroom_member的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		cbean = chatroomDAO.select(cbean).get(0);
		chatroomDAO.delete(cbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		TestUtils.printResult("重新列出所有chatroom和chatroom_member的資料");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(chatroomDAO.selectAll());
		TestUtils.printResult(chatroom_MemberDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}