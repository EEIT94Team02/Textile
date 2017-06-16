package tw.com.eeit94.textile.model.socaillist;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.socail.SocailListBean;
import tw.com.eeit94.textile.model.socail.SocailListDAO;
import tw.com.eeit94.textile.model.socail.SocailListPK;
import tw.com.eeit94.textile.model.socail.SocailListService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
public class SocailListServiceTest {

	public static void main(String arg[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		SocailListService sv = (SocailListService) context.getBean("socailListService");
		SocailListDAO dao = (SocailListDAO) context.getBean("socailListDAOHibernate");

		// allselect

	//		 List<SocailListBean> selects = dao.select();
	//		 System.out.println("selects=" + selects);

		// select

//		 SocailListPK pk =new SocailListPK(11, 22);
//		 SocailListBean bean =new SocailListBean();
//		 bean.setSocailListPK(pk);
//		 SocailListBean select =dao.select(pk);
//		 System.out.println("selects=" + select);

		// insert

//		 SocailListBean bean =new SocailListBean();
//		 SocailListPK pk =new SocailListPK(19, 23);
//		 bean.setSocailListPK(pk);
//		 bean.setLog_in(new Timestamp(System.currentTimeMillis()));
//		 bean.setS_group("eeit9411");
//		 bean.setS_type("黑單");
//		 SocailListBean insert =dao.insert(bean);
//		 System.out.println("insert=" + insert);

		// delete

//		 SocailListBean bean =new SocailListBean();
//		 SocailListPK pk =new SocailListPK(11, 22);
//		 bean.setSocailListPK(pk);
//		 boolean delete =dao.delete(bean);
//		 System.out.println("delete=" + delete );

		// 特殊查詢
		SocailListBean select = new SocailListBean();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// java.util.Date now =new java.util.Date();
		int year = 2017;
		String group = null;
		String type = null;
		SocailListPK pk = new SocailListPK(33, 44);
		try {
			Timestamp begin = new Timestamp(sdf.parse((year + "-12-31")).getTime());
			// Timestamp begin = null;
			// group = "五五";
			// type ="追蹤";
			select.setS_group(group);
			select.setS_type(type);
			select.setSocailListPK(pk);
			List<SocailListBean> beans = dao.selectByFriend(select, begin);
			System.out.println("beans=" + beans);
		} catch (Exception e) {
				System.out.println(e.getMessage()+"error by selectByFriend");
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}