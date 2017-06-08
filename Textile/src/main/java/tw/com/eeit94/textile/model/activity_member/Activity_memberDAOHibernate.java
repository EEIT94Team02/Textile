/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity_member;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

/*
 * Hibernate DAO產生步驟：
 * 1. Hibernate DAO名稱為'"Table名稱" + "DAOHibernate"'。
 * 2. 實作'"Table名稱" + "DAO"'介面，並覆寫方法。
 * 3. 標記@Repository(value = '"Table名稱(第一個字母小寫)" + "DAO"')。
 * 4. 加入Bean元件並標記@Autowired。
 */
@Repository(value="activity_memberDAO")
public class Activity_memberDAOHibernate implements Activity_memberDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Activity_memberDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// 測試程式
	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		sessionFactory.getCurrentSession().beginTransaction();		
		Activity_memberDAO dao = (Activity_memberDAOHibernate) context.getBean("activity_memberDAO");
		
		List<Activity_memberBean> beans;
		Activity_memberBean bean;
		Activity_memberPK activity_memberPK;
		
		beans = dao.select();
		System.out.println(beans);
		
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
//		activity_memberPK.setActivityno(3);
//		activity_memberPK.setmId(5);
//		selectother.setActivity_memberPK(activity_memberPK);		
//		selectother.setPosition("");
//		beans= dao.selectByOthers(selectother);		
//		System.out.println(beans);
		
//		Activity_memberBean insert = new Activity_memberBean();
//		activity_memberPK =  new Activity_memberPK();
//		activity_memberPK.setActivityno(3);
//		activity_memberPK.setmId(5);		
//		insert.setActivity_memberPK(activity_memberPK);
//		insert.setActivityBean(sessionFactory.getCurrentSession().get(ActivityBean.class, activity_memberPK.getActivityno()));
//		insert.setPosition("參與者");	
//		bean = dao.insert(insert);
//		System.out.println(bean);
		
//		Activity_memberBean update = new Activity_memberBean();
//		activity_memberPK =  new Activity_memberPK(3,5);
//		update.setActivity_memberPK(activity_memberPK);
//		update.setPosition("發起人");	
//		bean = dao.update(update);
//		System.out.println(bean);
		
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

	@Override
	public List<Activity_memberBean> select() {
		return this.getSession().createQuery("FROM Activity_memberBean", Activity_memberBean.class).getResultList();
	}
	
	@Override
	public Activity_memberBean selectByPrimaryKey(Activity_memberBean bean) {
		return getSession().get(Activity_memberBean.class, bean.getActivity_memberPK());
	}
	
	@Override
	public List<Activity_memberBean> selectByOthers(Activity_memberBean bean) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Activity_memberBean> query = cb.createQuery(Activity_memberBean.class);
		Root<Activity_memberBean> root = query.from(Activity_memberBean.class);
		query.select(root);
		Predicate p1 ;
		Predicate p2 ;
		if(bean.getActivity_memberPK().getActivityno() != null){
			p1 = cb.equal(root.<Integer>get("activity_memberPK").get("activityno"), bean.getActivity_memberPK().getActivityno());
		} else{
			p1 = cb.ge(root.<Integer>get("activity_memberPK").get("activityno"), 0);
		}
		if(bean.getActivity_memberPK().getmId() != null){
			p2 = cb.equal(root.<Integer>get("activity_memberPK").get("mId"), bean.getActivity_memberPK().getmId());
		} else{
			p2 = cb.ge(root.<Integer>get("activity_memberPK").get("mId"), 0);
		}		
		Predicate p3 = cb.like(root.<String>get("position"), bean.getPosition()== null ? "%" : "%"+bean.getPosition()+"%");
		
		List<Activity_memberBean> results = getSession().createQuery(query.where(p1,p2,p3)).getResultList();
		return results;
	}

	@Override
	public Activity_memberBean insert(Activity_memberBean bean) {
			getSession().save(bean);
			return bean;
	}

	@Override
	public Activity_memberBean update(Activity_memberBean bean) {
		Activity_memberBean select = this.selectByPrimaryKey(bean);
		if(select != null) {
			select.setPosition(bean.getPosition());		
		}
		return select;
	}
	
	public List<Activity_memberBean> updatePosition(List<Activity_memberBean> beans){
		List<Activity_memberBean> result = new ArrayList<Activity_memberBean>();
			for(Activity_memberBean bean : beans){
				result.add(this.update(bean));
			}	
			return result;
	}

	@Override
	public boolean delete(Activity_memberBean bean) {
		Activity_memberBean select = this.selectByPrimaryKey(bean);
		if(select != null) {
		getSession().delete(select);
			return true;
		}
		return false;
	}
}