package tw.com.eeit94.textile.model.interest_detail;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberDAO;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試Interest_DetailDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class Interest_DetailDAOHibernateTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		MemberDAO memberDAO = (MemberDAO) context.getBean("memberDAO");
		Interest_DetailDAO interest_DetailDAO = (Interest_DetailDAO) context.getBean("interest_DetailDAO");
		
		// 必須先在會員主表新增一筆資料，再取得此主鍵。
		MemberBean mbean = TestUtils.getNewMemberBean();
		sessionFactory.getCurrentSession().beginTransaction();
		memberDAO.insert(mbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interest_DetailDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		Interest_DetailBean i_dbean = new Interest_DetailBean();
		i_dbean.setmId(mbean.getmId());
		i_dbean.setI_dMain(25);
		i_dbean.setI_dOther("{'key',[57,63]}");
		i_dbean.setI_dRecreation(233);
		i_dbean.setI_dOtherRecreation("{'key',[45]}");
		i_dbean.setI_dExercises(35);
		i_dbean.setI_dOtherExercises("{'key',[18]}");
		i_dbean.setI_dDiet(58);
		i_dbean.setI_dOtherDiet("{'key',[27,314,456]}");
		i_dbean.setI_dArt(85);
		i_dbean.setI_dOtherArt("{'key',[]}");
		i_dbean.setI_dDesign(22);
		i_dbean.setI_dOtherDesign("{'key',[]}");
		i_dbean.setI_dMusic(48);
		i_dbean.setI_dOtherMusic("{'key',[3287]}");
		i_dbean.setI_dHobbies(17);
		i_dbean.setI_dOtherHobbies("{'key',[69,71,8324]}");
		i_dbean.setI_dActivities(132);
		i_dbean.setI_dOtherActivities("{'key',[2145,98783]}");
		interest_DetailDAO.insert(i_dbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interest_DetailDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		i_dbean.setI_dMain(149);
		i_dbean.setI_dMusic(57);
		i_dbean.setI_dOther("{'key',[2,5,87,296,3842]}");
		sessionFactory.getCurrentSession().beginTransaction();
		interest_DetailDAO.update(i_dbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interest_DetailDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		interest_DetailDAO.delete(i_dbean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(interest_DetailDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}
}