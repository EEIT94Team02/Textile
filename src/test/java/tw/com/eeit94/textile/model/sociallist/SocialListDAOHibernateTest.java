package tw.com.eeit94.textile.model.sociallist;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.social.SocialListBean;
import tw.com.eeit94.textile.model.social.SocialListDAO;
import tw.com.eeit94.textile.model.social.SocialListDAOHibernate;
import tw.com.eeit94.textile.model.social.SocialListPK;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
public class SocialListDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		SocialListDAO dao = (SocialListDAOHibernate) context.getBean("socialListDAOHibernate");

		// List<SocialListBean> selects = dao.select();
		// System.out.println("selects=" + selects);

		// SocialListBean bean=new SocialListBean();
		// SocialListPK pk= new SocialListPK(11,22);
		// bean.setSocialListPK(pk);
		//
		// SocialListBean select=dao.select(pk);
		//
		// System.out.println("select="+select);

		 SocialListBean bean =new SocialListBean();
		 SocialListPK pk=new SocialListPK(99, 97);
		 bean.setSocialListPK(pk);
		 bean.setS_type("女友");
		 bean.setLog_in(new Timestamp(System.currentTimeMillis()));
//		 bean.setS_group("DEFAULT");
		
		 SocialListBean insert =dao.insert(bean);
		 System.out.println("insert="+insert);

//		 SocialListBean bean=new SocialListBean();
//		 SocialListPK pk=new SocialListPK(11,22);
//		 bean.setSocialListPK(pk);
//		 bean.setS_type("網友");
//		 bean.setLog_in(new Timestamp(System.currentTimeMillis()));
//		 bean.setS_group("eeit95");
//		
//		 SocialListBean update =dao.update(bean);
//		
//		 System.out.println("update"+update);

//		 SocialListBean del =new SocialListBean();
//		 SocialListPK pk =new SocialListPK(77,87);
//		 del.setSocialListPK(pk);
//		
//		 boolean result =dao.delete(del);
//		
//		 System.out.println("delete"+result);

//		SocialListBean select = new SocialListBean();
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// java.util.Date now =new java.util.Date();
//		int year = 2015;
//		String group = null;
//		String type = null;
//		SocialListPK pk = new SocialListPK(null, null);
//		try {
//			// Timestamp begin = new Timestamp(sdf.parse((year +
//			// "-12-31")).getTime());
//			Timestamp begin = null;
//			// group = "五五";
//			// type ="追蹤";
//			select.setS_group(group);
//			select.setS_type(type);
//			select.setSocialListPK(pk);
//			List<SocialListBean> beans = dao.selectByFriend(select, begin);
//			System.out.println("beans=" + beans);
//		} catch (Exception e) {
//
//		}

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}