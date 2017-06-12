package tw.com.eeit94.textile.model.activity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.activity.ActivityService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public class TestActivityService {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ActivityService service = (ActivityService) context.getBean("activityService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ActivityBean bean;
		List<ActivityBean> beans;

		beans = service.selectAll();
		System.out.println(beans);

		ActivityBean createNew = new ActivityBean();
		try {
			createNew.setBegintime(new java.sql.Timestamp(sdf.parse("2017-09-20 00:00:00").getTime()));
			createNew.setEndtime(new java.sql.Timestamp(sdf.parse("2017-09-20 00:00:00").getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		createNew.setActivityname("新莊桌遊團");
		createNew.setInterpretation(
				"星蝕,馬尼拉,符文戰爭,blood bowl....等等" + "\n" + "由於本人自己也想玩沒玩過的桌遊" + "\n" + "所以歡迎參加者帶自己有的桌遊來交流");
		createNew.setPlace("輔大fun桌遊 桌遊店");
		createNew.setVisibility("私人");
		bean = service.createNewActivity(createNew);
		System.out.println(bean);

		ActivityBean update = new ActivityBean();
		update.setActivityno(6);
		update.setVisibility("好友");
		update.setActivityname("");
		update.setPlace("");
		try {
			update.setBegintime(new java.sql.Timestamp(sdf.parse("2017-09-20 00:00:00").getTime()));
			update.setEndtime(new java.sql.Timestamp(sdf.parse("2017-09-20 00:00:00").getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		bean = service.updateActivity(update);
		System.out.println(bean);

		ActivityBean del = new ActivityBean();
		del.setActivityno(6);
		boolean result = service.deleteActivity(del);
		System.out.println(result);

		ActivityBean anySelect = new ActivityBean();
		anySelect.setActivityname("林口");
		anySelect.setPlace("大安");
		anySelect.setVisibility("取消");
		Timestamp begin = null;
		Timestamp end = null;
		try {
			begin = new Timestamp(sdf.parse("2017-05-20 00:00:00").getTime());
			end = new Timestamp(sdf.parse("2017-06-01 00:00:00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		anySelect.setBegintime(begin);
		anySelect.setEndtime(end);
		beans = service.customeSelect(anySelect, null);
		System.out.println(beans);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}