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
public class AnnouncementServiceTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		AnnouncementDAO dao = (AnnouncementDAO) context.getBean("announcementDAOHibernate");
		AnnouncementService sv = (AnnouncementService) context.getBean("announcementService");

		// allselect
		// List<AnnouncementBean> selects = dao.select();
		// System.out.println("selects=" + selects);

		// select
		// AnnouncementBean bean=new AnnouncementBean();
		// bean.setA_id(10);
		// AnnouncementBean select=dao.select(bean);
		// System.out.println("selects="+select);

		// update
		// AnnouncementBean bean = new AnnouncementBean();
		// bean.setA_id(10);
		// bean.setMsg("明日6/5 11:00~23:59 北歐神 英雄神抽蛋機率*3up 伏金*5up
		// 想把石頭丟到馬桶裡的請把握機會!!");
		// AnnouncementBean update = dao.update(bean);
		// System.out.println("update=" + update);

		// insert
		// AnnouncementBean bean = new AnnouncementBean();
		// DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		// try {
		// bean.setA_id(88);
		// bean.setMsg("明日6/5 11:00~23:59 北歐神 英雄神抽蛋機率*3up 伏金*5up
		// 想把石頭丟到馬桶裡的請把握機會!!");
		// bean.setRelTime(new java.util.Date());
		// bean.setStartTime(new
		// java.util.Date(sdf.parse("2018-03-02").getTime()));
		// bean.setEndTime(new
		// java.util.Date(sdf.parse("2018-05-02").getTime()));
		// bean.setNextTime(new
		// java.util.Date(sdf.parse("2018-04-02").getTime()));
		// bean.setA_type("新聞");
		// bean.setFre("月");
		// bean.setGist("抽蛋時間到囉!");
		// AnnouncementBean insert = dao.insert(bean);
		// System.out.println("insert=" + insert);
		// } catch (Exception e) {
		// }
			
		// 下次公告時間
//		AnnouncementBean bean = new AnnouncementBean();
//		bean.setA_id(12);
//		int fre = 2; // 公告頻率
//		try {
//			AnnouncementBean updateByNextTime = sv.updateByNextTime(bean, fre);
//			System.out.println("updateByNextTime=" + updateByNextTime);
//		} catch (Exception e) {
//		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}