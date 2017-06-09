package tw.com.eeit94.textile.testmodel.activity_member;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberDAO;
import tw.com.eeit94.textile.model.activity_member.Activity_memberDAOHibernate;
import tw.com.eeit94.textile.model.activity_member.Activity_memberPK;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

public class TestActivity_memberDAOHibernate {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		sessionFactory.getCurrentSession().beginTransaction();		
		Activity_memberDAO dao = (Activity_memberDAOHibernate) context.getBean("activity_memberDAO");
		
		List<Activity_memberBean> beans;
		Activity_memberBean bean;
		Activity_memberPK activity_memberPK;
		
//		beans = dao.select();
//		System.out.println(beans);
//		
//		Activity_memberBean select = new Activity_memberBean();
//		activity_memberPK = new Activity_memberPK();
//		activity_memberPK.setActivityno(1);
//		activity_memberPK.setmId(1);
//		select.setActivity_memberPK(activity_memberPK);
//		bean = dao.selectByPrimaryKey(select);
//		System.out.println(bean);
//		
//		Activity_memberBean selectother = new Activity_memberBean();
//		activity_memberPK =  new Activity_memberPK();
//		activity_memberPK.setActivityno(2);
//		activity_memberPK.setmId(3);
//		selectother.setActivity_memberPK(activity_memberPK);		
//		selectother.setPosition("");
//		beans= dao.selectByOthers(selectother);		
//		System.out.println(beans);
		
		Activity_memberBean insert = new Activity_memberBean();
		activity_memberPK =  new Activity_memberPK();
		activity_memberPK.setActivityno(3);
		activity_memberPK.setmId(5);		
		insert.setActivity_memberPK(activity_memberPK);
		insert.setActivityBean(sessionFactory.getCurrentSession().get(ActivityBean.class, activity_memberPK.getActivityno()));
		insert.setPosition("參與者");	
		bean = dao.insert(insert);
		System.out.println(bean);
		
		Activity_memberBean update = new Activity_memberBean();
		activity_memberPK =  new Activity_memberPK(3,5);
		update.setActivity_memberPK(activity_memberPK);
		update.setPosition("發起人");	
		bean = dao.update(update);
		System.out.println(bean);
		
		Activity_memberBean test = new Activity_memberBean();
		activity_memberPK =  new Activity_memberPK();
		activity_memberPK.setActivityno(1);
		test.setActivity_memberPK(activity_memberPK);
//		test.setActivityno(1);
		beans= dao.selectByOthers(test);
		for(int i = 0; i< beans.size();i++){
			beans.get(i).setPosition("取消");
		}
		dao.updatePosition(beans);
		System.out.println(beans);
		
		Activity_memberBean del = new Activity_memberBean();
		activity_memberPK =  new Activity_memberPK(3,5);
		del.setActivity_memberPK(activity_memberPK);
		boolean result = dao.delete(del);
		System.out.println(result);		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();			
	
	}

}
