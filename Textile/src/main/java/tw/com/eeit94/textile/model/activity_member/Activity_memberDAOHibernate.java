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

@Repository
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
		Activity_memberDAO dao = (Activity_memberDAOHibernate) context.getBean("activity_memberDAOHibernate");
		
//		List<Activity_memberBean> beans = dao.select();
//		System.out.println(beans);
		
//		Activity_memberBean test = new Activity_memberBean();
//		test.setActivityno(1);
//		test.setmId(1);
//		Activity_memberBean bean = dao.selectByPrimaryKey(test);
//		System.out.println(bean);
		
		Activity_memberBean test = new Activity_memberBean();
		test.setActivityno(4);
		test.setmId(1);
		test.setPosition("參與者");
		List<Activity_memberBean> beans= dao.selectByOthers(test);		
		System.out.println(beans);
		
//		Activity_memberBean insert = new Activity_memberBean();
//	    insert.setActivityno(3);
//		insert.setmId(5);
//		insert.setPosition("參與者");	
//		Activity_memberBean bean = dao.insert(insert);
//		System.out.println(bean);
		
//		Activity_memberBean update = new Activity_memberBean();
//		update.setActivityno(3);
//		update.setmId(5);
//		update.setPosition("發起人");	
//		Activity_memberBean bean = dao.update(update);
//		System.out.println(bean);
		
//		Activity_memberBean test = new Activity_memberBean();
//		test.setActivityno(1);
//		List<Activity_memberBean> beans= dao.selectByOthers(test);
//		for(int i = 0; i< beans.size();i++){
//			beans.get(i).setPosition("取消");
//		}
//		dao.updatePosition(beans);
//		System.out.println(beans);
		
//		Activity_memberBean del = new Activity_memberBean();
//	    del.setActivityno(3);
//		del.setmId(5);
//		boolean result = dao.delete(del);
//		System.out.println(result);		
		
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
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Activity_memberBean> query = cb.createQuery(Activity_memberBean.class);
		Root<Activity_memberBean> root = query.from(Activity_memberBean.class);
		query.select(root);
		Predicate p1 = cb.equal(root.<Integer>get("activityno"), bean.getActivityno());
		Predicate p2 = cb.equal(root.<Integer>get("mId"), bean.getmId());		
		
		return getSession().createQuery(query.where(p1,p2)).getSingleResult();
	}
	
	@Override
	public List<Activity_memberBean> selectByOthers(Activity_memberBean bean) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Activity_memberBean> query = cb.createQuery(Activity_memberBean.class);
		Root<Activity_memberBean> root = query.from(Activity_memberBean.class);
		query.select(root);
		Predicate p1 ;
		Predicate p2 ;
		if(bean.getActivityno() != null){
			p1 = cb.equal(root.<Integer>get("activityno"), bean.getActivityno());
		} else{
			p1 = cb.ge(root.<Integer>get("activityno"), 0);
		}
		if(bean.getmId() != null){
			p2 = cb.equal(root.<Integer>get("mId"), bean.getmId());
		} else{
			p2 = cb.ge(root.<Integer>get("mId"), 0);
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