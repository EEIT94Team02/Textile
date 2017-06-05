/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

/*
 * Service產生步驟：
 * 1. Service名稱為'"Table名稱" + "Service"'。
 * 2. 標記@Service。
 * 3. 加入至少一個Bean元件並標記@Autowired。
 */
@Service
public class ActivityService {
	@Autowired
	private ActivityDAO activityDao;
	
	public ActivityService(ActivityDAO activityDao) {
		this.activityDao = activityDao;
	}

	// 測試程式
	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ActivityService service = (ActivityService) context.getBean("activityService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ActivityBean bean ;
		List<ActivityBean> beans ;
		
		ActivityBean createNew = new ActivityBean();		
		try {
			createNew.setBegintime(new java.sql.Timestamp(sdf.parse("2017-09-20 00:00:00").getTime()));
			createNew.setEndtime(new java.sql.Timestamp(sdf.parse("2017-09-20 00:00:00").getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		createNew.setActivityname("新莊桌遊團");
		createNew.setInterpretation("星蝕,馬尼拉,符文戰爭,blood bowl....等等"+"\n"+"由於本人自己也想玩沒玩過的桌遊"+"\n"+"所以歡迎參加者帶自己有的桌遊來交流");
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
		
//		ActivityBean del = new ActivityBean();
//		del.setActivityno(6);
//		boolean result = service.deleteActivity(del);
//		System.out.println(result);	
		
//		ActivityBean anySelect = new ActivityBean();
////		anySelect.setActivityname("林口");
////		anySelect.setPlace("大安");
////		anySelect.setVisibility("取消");
//		Timestamp begin = null;
//		Timestamp end = null;
//		try {
//			begin = new Timestamp(sdf.parse("2017-05-20 00:00:00").getTime());
//			end = new Timestamp(sdf.parse("2017-06-01 00:00:00").getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}		
//		anySelect.setBegintime(begin);
//		anySelect.setEndtime(end);			
//		beans = service.customeSelect(anySelect,null);
//		System.out.println(beans);		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();		
	}



	/*
	 * 實作企業邏輯
	 */
	@Transactional
	public ActivityBean selectByActivityNO(ActivityBean bean) {
		return activityDao.select(bean);
	}
	@Transactional
	public List<ActivityBean> customeSelect(ActivityBean bean , String string) {
		List<ActivityBean> result = null;		
		try {
			result = activityDao.selectByOthers(bean,string);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	@Transactional
	public ActivityBean createNewActivity(ActivityBean bean) {
		return activityDao.insert(bean);
	}
	@Transactional
	public ActivityBean updateActivity(ActivityBean bean) {
		ActivityBean result = this.selectByActivityNO(bean);
		if(result != null){
			result.setActivityname(bean.getActivityname() ==null ? result.getActivityname(): bean.getActivityname());
			result.setBegintime(bean.getBegintime() ==null ? result.getBegintime(): bean.getBegintime());
			result.setEndtime(bean.getEndtime() ==null ? result.getEndtime(): bean.getEndtime());
			result.setInterpretation(bean.getInterpretation() ==null ? result.getInterpretation(): bean.getInterpretation());
			result.setPlace(bean.getPlace() ==null ? result.getPlace(): bean.getPlace());
			result.setVisibility(bean.getVisibility() ==null ? result.getVisibility(): bean.getVisibility());
			return activityDao.update(result);
		}
		return null;
	}
	
	@Transactional
	public Boolean deleteActivity(ActivityBean bean) {
		return activityDao.delete(bean);
	}
	


}
