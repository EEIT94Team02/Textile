package tw.com.eeit94.textile.model.socaillist;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
public class SocailListDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		SocailListDAO dao = (SocailListDAOHibernate) context.getBean("socailListDAOHibernate");

		// List<SocailListBean> selects = dao.select();

		// System.out.println("selects=" + selects);

		// SocailListBean bean=new SocailListBean();
		// SocailListPK pk= new SocailListPK(11,22);
		// bean.setSocailListPK(pk);
		//
		// SocailListBean select=dao.select(pk);
		//
		// System.out.println("select="+select);

		// SocailListBean bean =new SocailListBean();
		// SocailListPK pk=new SocailListPK(99, 97);
		// bean.setSocailListPK(pk);
		// bean.setS_type("女友");
		// bean.setLog_in(new java.util.Date());
		// bean.setS_group("eeit94");
		//
		// SocailListBean insert =dao.insert(bean);
		// System.out.println("insert="+insert);

		// SocailListBean bean=new SocailListBean();
		// SocailListPK pk=new SocailListPK(11,22);
		// bean.setSocailListPK(pk);
		// bean.setS_type("網友");
		// bean.setLog_in(new java.util.Date());
		// bean.setS_group("eeit95");
		//
		// SocailListBean update =dao.update(bean);
		//
		// System.out.println("update"+update);

		// SocailListBean del =new SocailListBean();
		// SocailListPK pk =new SocailListPK(77,87);
		// del.setSocailListPK(pk);
		//
		// boolean result =dao.delete(del);
		//
		// System.out.println("delete"+result);

		SocailListBean select = new SocailListBean();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// java.util.Date now =new java.util.Date();
		int year = 2015;
		String group = null;
		String type = null;
		SocailListPK pk = new SocailListPK(null, null);
		try {
			// Timestamp begin = new Timestamp(sdf.parse((year +
			// "-12-31")).getTime());
			Timestamp begin = null;
			// group = "五五";
			// type ="追蹤";
			select.setS_group(group);
			select.setS_type(type);
			select.setSocailListPK(pk);
			List<SocailListBean> beans = dao.selectByFriend(select, begin);
			System.out.println("beans=" + beans);
		} catch (Exception e) {

		}

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}