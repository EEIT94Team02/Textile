/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity_member;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		
		List<Activity_memberBean> beans = dao.select();
		System.out.println(beans);		
		
//		Activity_memberBean test = new Activity_memberBean();
//		test.setActivityno(1);
//		List<Activity_memberBean> beans= dao.selectByActivityno(test);
//		System.out.println(beans);
		
//		Activity_memberBean test = new Activity_memberBean();
//		test.setMemberid(1);
//		List<Activity_memberBean> beans= dao.selectByMemberid(test);
//		System.out.println(beans);
		
//		Activity_memberBean test = new Activity_memberBean();
//		test.setMemberid(1);
////	test.setActivityno(1);
//		List<Activity_memberBean> beans= dao.select(test);
//		System.out.println(beans);
		
//		Activity_memberBean insert = new Activity_memberBean();
//	    insert.setActivityno(3);
//		insert.setMemberid(5);
//		insert.setPosition("參與者");	
//		Activity_memberBean bean = dao.insert(insert);
//		System.out.println(bean);
		
//		Activity_memberBean update = new Activity_memberBean();
//		update.setActivityno(3);
//		update.setMemberid(5);
//		update.setPosition("發起人");	
//		Activity_memberBean bean = dao.update(update);
//		System.out.println(bean);
		
//		Activity_memberBean del = new Activity_memberBean();
//	    del.setActivityno(3);
//		del.setMemberid(5);
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

	
	
//	@Override
//	public List<Activity_memberBean> selectByActivityno(Activity_memberBean bean) {
//		Query<Activity_memberBean> query = getSession().createQuery("FROM Activity_memberBean where activityno=:activityno", Activity_memberBean.class);
//		query.setParameter("activityno", bean.getActivityno());
//		return query.list();		
//	}
//	
//	@Override
//	public List<Activity_memberBean> selectByMemberid(Activity_memberBean bean) {
//		Query<Activity_memberBean> query = getSession().createQuery("FROM Activity_memberBean where memberid=:memberid", Activity_memberBean.class);
//		query.setParameter("memberid", bean.getMemberid());		
//		return query.list();		
//	}
	
	@Override
	public List<Activity_memberBean> select(Activity_memberBean bean) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Activity_memberBean> query = cb.createQuery(Activity_memberBean.class);
		Root<Activity_memberBean> root = query.from(Activity_memberBean.class);
		query.select(root);
		
		Predicate p1 = cb.equal(root.<Integer>get("activityno"), bean.getActivityno() == null ? "%" : bean.getActivityno());
		
		return null;
	}

	@Override
	public Activity_memberBean insert(Activity_memberBean bean) {
//		List<Activity_memberBean> select = this.select(bean);
//		if(select.isEmpty() && select.size()==0) {
			getSession().save(bean);
			return bean;
//		}
//		return null;
	}

	@Override
	public Activity_memberBean update(Activity_memberBean bean) {
		List<Activity_memberBean> select = this.select(bean);
		if(!select.isEmpty() && select.size()!=0) {
			select.get(0).setPosition(bean.getPosition());		
		}
		return select.get(0);
	}

	@Override
	public boolean delete(Activity_memberBean bean) {
		List<Activity_memberBean> select = this.select(bean);
		if(!select.isEmpty() && select.size()!=0){
		getSession().delete(this.select(bean).get(0));
			return true;
		}
		return false;
	}

//	private final String SELECT_BY_PRICE_LESS_THAN = "select name from ExampleBean where price<";	
//	@Override
//	public List<PhotoBean> selectByPriceLessThan(double price) {
//		@SuppressWarnings("unused")
//		Query<PhotoBean> query = this.getSession().createQuery(SELECT_BY_PRICE_LESS_THAN + price, PhotoBean.class);
//		/*
//		 * 用Hibernate實作
//		 */
//		return null;
//	}

}