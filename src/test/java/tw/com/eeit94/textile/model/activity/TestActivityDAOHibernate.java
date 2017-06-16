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
import tw.com.eeit94.textile.model.activity.ActivityDAO;
import tw.com.eeit94.textile.model.activity.ActivityDAOHibernate;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public class TestActivityDAOHibernate {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		ActivityDAO dao = (ActivityDAOHibernate) context.getBean("activityDAO");
		sessionFactory.getCurrentSession().beginTransaction();

		ActivityBean bean;
		List<ActivityBean> beans;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		beans = dao.select();
		System.out.println(beans);

		ActivityBean select = new ActivityBean();
		select.setActivityno(1);
		;
		bean = dao.select(select);
		System.out.println(bean);

		ActivityBean insert = new ActivityBean();
		insert.setActivityno(3);
		try {
			insert.setBegintime(new java.sql.Timestamp(sdf.parse("2017-10-20 00:00:00").getTime()));
			insert.setEndtime(new java.sql.Timestamp(sdf.parse("2017-10-20 00:00:00").getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		insert.setActivityname("新莊桌遊團");
		insert.setInterpretation(
				"星蝕,馬尼拉,符文戰爭,blood bowl....等等" + "\n" + "由於本人自己也想玩沒玩過的桌遊" + "\n" + "所以歡迎參加者帶自己有的桌遊來交流");
		insert.setPlace("輔大fun桌遊 桌遊店");
		insert.setVisibility("私人");
		bean = dao.insert(insert);
		System.out.println(bean);

		ActivityBean update = new ActivityBean();
		update.setActivityno(2);
		update.setVisibility("公開");
		bean = dao.update(update);
		System.out.println(bean);

		ActivityBean del = new ActivityBean();
		del.setActivityno(6);
		boolean result = dao.delete(del);
		System.out.println(result);

		select = new ActivityBean();
		select.setActivityname("林口");
		select.setPlace("林口");
		select.setVisibility("公開");
		Timestamp begin = null;
		Timestamp end = null;
		try {
			begin = new Timestamp(sdf.parse("2017-05-20 00:00:00").getTime());
			end = new Timestamp(sdf.parse("2017-06-01 00:00:00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		select.setBegintime(begin);
		select.setEndtime(end);
		try {
			beans = dao.selectByOthers(select, "begintime");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(beans);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}