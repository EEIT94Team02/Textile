package tw.com.eeit94.textile.model.announcement;

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
public class AnnouncementDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		AnnouncementDAO dao = (AnnouncementDAOHibernate) context.getBean("announcementDAOHibernate");

		// List<AnnouncementBean> selects= dao.select();
		// System.out.println("selects="+selects);

		// AnnouncementBean bean=new AnnouncementBean();
		// AnnouncementBean nt=new java.util.Date();
		// bean.setNextTime();
		// AnnouncementBean select =dao.select(bean);
		// System.out.println("selects="+select);

		// DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// AnnouncementBean bean=new AnnouncementBean();
		// Calendar cal = Calendar.getInstance();
		// int day= 37;
		// cal.add(Calendar.DAY_OF_MONTH, day);
		// System.out.println(sdf.format(cal.getTime()));

		// date.setTime();
		// bean.setNextTime(date);
		// bean.setA_id(10);
		// AnnouncementBean update =dao.update(bean);
		// System.out.println("update="+update);
		// System.out.println("目前日曆時戳: "+sdf.format(date));

		// AnnouncementBean bean=new AnnouncementBean();
		// bean.setA_id(12);
		// bean.setFre("四天一次");
		// AnnouncementBean insert =dao.insert(bean);
		// System.out.println("insert="+insert);

		// AnnouncementBean bean=new AnnouncementBean();
		// bean.setA_id(10);
		// bean.setFre("三天一次");
		// bean.setRelTime(new java.util.Date());
		//
		// AnnouncementBean update =dao.update(bean);
		// System.out.println("update="+update);

		// AnnouncementBean del =new AnnouncementBean();
		// del.setA_id(10);
		// del.setFre("一天一次");
		// boolean result =dao.delete(del);
		// System.out.println(result);

		// 查詢公告開始區間
		// AnnouncementBean select=new AnnouncementBean();
		// DateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		// java.util.Date begin=null;
		// try {
		// begin = sdf.parse("2017-05-30");
		// select.setStartTime(begin);
		// List<AnnouncementBean> beans = dao.selectByBginTime(select);
		// System.out.println(beans);
		// } catch (Exception e) {
		//
		// }

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}