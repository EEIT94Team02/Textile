/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity_member;


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
public class Activity_memberService {

	@Autowired
	private Activity_memberDAO activityMemberDAO;
	
	public Activity_memberService(Activity_memberDAO activityMemberDAO) {
		this.activityMemberDAO = activityMemberDAO;
	}

	// 測試程式
	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		Activity_memberService service = (Activity_memberService) context.getBean("activity_memberService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		
//		List<Activity_memberBean> beans = service.findAll();
//		System.out.println(beans);
		
//		Activity_memberBean select = new Activity_memberBean();
//		select.setActivityno(1);
//		List<Activity_memberBean> beans = service.findActivityMemberByActivityNo(select);
//		System.out.println(beans);
		
//		Activity_memberBean select = new Activity_memberBean();
//		select.setmId(1);
//		List<Activity_memberBean> beans = service.findActivityNoByMemberId(select);
//		System.out.println(beans);
		
//		Activity_memberBean add = new Activity_memberBean();
//		add.setActivityno(2);
//		add.setmId(5);
//		add.setPosition("待回覆");
//		Activity_memberBean bean = service.addNewActivityMember(add);
//		System.out.println(bean);
		
//		Activity_memberBean change = new Activity_memberBean();
//		change.setActivityno(2);
//		change.setmId(5);
//		change.setPosition("參與者");
//		Activity_memberBean bean = service.changePosition(change);
//		System.out.println(bean);
		
//		Activity_memberBean com = new Activity_memberBean();
//		com.setmId(1);
//		List<Activity_memberBean> result = service.commitAllActivity(com);
//		System.out.println(result);
		
//		Activity_memberBean del = new Activity_memberBean();
//		del.setActivityno(1);
//		boolean result = service.deleteByActivityNo(del);
//		System.out.println(result);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
		
	}

	/*
	 * 實作企業邏輯
	 */
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<Activity_memberBean> findAll() {
		return activityMemberDAO.select();
	}	
	
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<Activity_memberBean> findActivityMemberByActivityNo(Activity_memberBean bean) {
		return activityMemberDAO.selectByOthers(bean);
	}
	
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<Activity_memberBean> findActivityNoByMemberId(Activity_memberBean bean) {
		return activityMemberDAO.selectByOthers(bean);
	}
	
	public Activity_memberBean addNewActivityMember(Activity_memberBean bean){
			return activityMemberDAO.insert(bean);
	}	
	
	public Activity_memberBean changePosition(Activity_memberBean bean){
//		if(bean.getActivityno() != null && bean.getmId() != null){
//			return activityMemberDAO.update(bean);
//		}
		return null;
	}
	
	public List<Activity_memberBean> commitAllActivity(Activity_memberBean bean){
		List<Activity_memberBean> begin= activityMemberDAO.selectByOthers(bean);
		for(int i = 0; i< begin.size();i++){
			begin.get(i).setPosition("參與者");
		}
		bean.setPosition("參與者");
		activityMemberDAO.updatePosition(begin);
		List<Activity_memberBean> after = activityMemberDAO.selectByOthers(bean);		
		if(begin.size() == after.size()){
			return after;
		}
		return null;
	}
	
	public boolean deleteByActivityNo(Activity_memberBean bean){
		List<Activity_memberBean> del= activityMemberDAO.selectByOthers(bean);
		for(int i = 0; i< del.size();i++){
			activityMemberDAO.delete(del.get(i));
		}	
		if(activityMemberDAO.selectByOthers(bean).isEmpty() && activityMemberDAO.selectByOthers(bean).size() ==0){
			return true;
		}
		return false;                                                                                                                                                                                                                                                                                                                                                                               
	}
	
}