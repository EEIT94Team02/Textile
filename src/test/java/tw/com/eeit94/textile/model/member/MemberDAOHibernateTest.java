package tw.com.eeit94.textile.model.member;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.member.util.MemberKeyWordsBean;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試MemberDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class MemberDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		MemberDAO memberDAO = (MemberDAO) context.getBean("memberDAO");

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		MemberBean mbean = TestUtils.getNewMemberBean();
		sessionFactory.getCurrentSession().beginTransaction();
		memberDAO.insert(mbean);
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		mbean.setmCareer(5);
		mbean.setmEconomy(4);
		mbean.setmSelfIntroduction("https://www.facebook.com/");
		sessionFactory.getCurrentSession().beginTransaction();
		memberDAO.update(mbean);
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		memberDAO.delete(mbean);
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		// 特殊查詢的測試
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		/*
		 * 這裡可以靠註解切換，模擬輸入的查詢資料。
		 */
		MemberKeyWordsBean mkwbean = new MemberKeyWordsBean();
		// mkwbean.setmGender("F");
		// mkwbean.setmBirthdayBegin(sdf.parse("1990-10-23"));
		// mkwbean.setmBirthdayEnd(sdf.parse("2000-10-23"));
		java.util.List<String> mAddress = new ArrayList<>();
		mAddress.add("永和區");
		// mkwbean.setmAddress(mAddress);
		// mkwbean.setmScores(500);
		mkwbean.setmCreateTimeBegin(new java.sql.Timestamp(sdf.parse("2016-01-01").getTime()));
		mkwbean.setmCreateTimeEnd(new java.sql.Timestamp(sdf.parse("2016-06-30").getTime()));
		// mkwbean.setmCareer(12);
		// mkwbean.setmEducation(5);
		// mkwbean.setmEconomy(2);
		// mkwbean.setmMarriage(0);
		// mkwbean.setmFamily(3);
		// mkwbean.setmBloodType(1);
		// mkwbean.setmConstellation(8);
		// mkwbean.setmReligion(2);
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectByKeyWords(mkwbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		// 利用姓名查詢
		mkwbean.setmName("Stacy Evans");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectByName(mkwbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		// 利用相似的姓名查詢
		mkwbean.setmName("s");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectBySimilarName(mkwbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		// 利用帳號查詢
		mkwbean.setmEmail("juan336830@outlook.com");
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(memberDAO.selectByEmail(mkwbean));
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}