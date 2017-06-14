package tw.com.eeit94.textile.model.activity_member;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberPK;
import tw.com.eeit94.textile.model.activity_member.Activity_memberService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public class Activity_memberServiceTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		Activity_memberService service = (Activity_memberService) context.getBean("activity_memberService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		List<Activity_memberBean> beans;
		Activity_memberBean bean;
		Activity_memberPK activity_memberPK;

		beans = service.findAll();
		System.out.println(beans);

		Activity_memberBean selectAct = new Activity_memberBean();
		activity_memberPK = new Activity_memberPK();
		activity_memberPK.setActivityno(1);
		selectAct.setActivity_memberPK(activity_memberPK);
		beans = service.findActivityMemberByActivityNo(selectAct);
		System.out.println(beans);

		Activity_memberBean selectMId = new Activity_memberBean();
		activity_memberPK = new Activity_memberPK();
		activity_memberPK.setmId(1);
		selectMId.setActivity_memberPK(activity_memberPK);
		beans = service.findActivityNoByMemberId(selectMId);
		System.out.println(beans);

		Activity_memberBean add = new Activity_memberBean();
		activity_memberPK = new Activity_memberPK(2, 5);
		add.setActivity_memberPK(activity_memberPK);
		add.setActivityBean(
				sessionFactory.getCurrentSession().get(ActivityBean.class, activity_memberPK.getActivityno()));
		add.setPosition("待回覆");
		bean = service.addNewActivityMember(add);
		System.out.println(bean);

		Activity_memberBean change = new Activity_memberBean();
		activity_memberPK = new Activity_memberPK(2, 5);
		change.setActivity_memberPK(activity_memberPK);
		change.setPosition("參與者");
		bean = service.changePosition(change);
		System.out.println(bean);

		Activity_memberBean com = new Activity_memberBean();
		activity_memberPK = new Activity_memberPK();
		activity_memberPK.setmId(1);
		com.setActivity_memberPK(activity_memberPK);
		List<Activity_memberBean> commit = service.commitAllActivity(com);
		System.out.println(commit);

		Activity_memberBean del = new Activity_memberBean();
		activity_memberPK = new Activity_memberPK();
		activity_memberPK.setActivityno(1);
		del.setActivity_memberPK(activity_memberPK);
		boolean result = service.deleteByActivityNo(del);
		System.out.println(result);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}