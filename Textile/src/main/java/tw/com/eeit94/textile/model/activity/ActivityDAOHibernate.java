/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * Hibernate DAO產生步驟：
 * 1. Hibernate DAO名稱為'"Table名稱" + "DAOHibernate"'。
 * 2. 實作'"Table名稱" + "DAO"'介面，並覆寫方法。
 * 3. 標記@Repository(value = '"Table名稱(第一個字母小寫)" + "DAO"')。
 * 4. 加入Bean元件並標記@Autowired。
 */
@Repository(value="activityDAO")
public class ActivityDAOHibernate implements ActivityDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ActivityDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<ActivityBean> select() {
		return this.getSession().createQuery("FROM ActivityBean", ActivityBean.class).getResultList();
	}	
	
	@Override
	public ActivityBean select(ActivityBean bean) {
		return getSession().get(ActivityBean.class, bean.getActivityno());
	}

	@Override
	public ActivityBean insert(ActivityBean bean) {		
			getSession().save(bean);
			return bean;
	}

	@Override
	public ActivityBean update(ActivityBean bean) {
		ActivityBean select = this.select(bean);
		if(select != null) {
			select.setActivityname(bean.getActivityname());
			select.setBegintime(bean.getBegintime());
			select.setEndtime(bean.getEndtime());
			select.setInterpretation(bean.getInterpretation());
			select.setPlace(bean.getPlace());
			select.setVisibility(bean.getVisibility());		
			return select;
		}
		return null;
	}

	@Override
	public boolean delete(ActivityBean bean) {
		ActivityBean select = this.select(bean);
		if(select != null){
		getSession().delete(select);
			return true;
		}
		return false;
	}
	
	@Override
	public List<ActivityBean> selectByOthers(ActivityBean bean, String string){
		CriteriaBuilder cb = getSession().getCriteriaBuilder();		
		CriteriaQuery<ActivityBean> qry = cb.createQuery(ActivityBean.class);
		Root<ActivityBean> root = qry.from(ActivityBean.class);
		qry.select(root);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Predicate p1 = cb.like(root.<String>get("activityname"),bean.getActivityname() ==null? "%":"%"+bean.getActivityname()+"%");
		Predicate p2 = null;
		try {
			p2 = cb.between(root.<Timestamp>get("begintime"), (bean.getBegintime()!=null? bean.getBegintime():new java.sql.Timestamp(0)),
					(bean.getEndtime ()!=null?bean.getEndtime():new Timestamp((sdf.parse("2100-12-31 23:59:59")).getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Predicate p4 = cb.like(root.<String>get("place"),bean.getPlace()==null? "%":"%"+bean.getPlace()+"%");
		Predicate p5 = cb.like(root.<String>get("visibility"),bean.getVisibility()==null? "%":"%"+bean.getVisibility()+"%");		
		Order order = cb.asc(root.get("activityno"));
		
		if(string != null && string.length() != 0){
			order = cb.asc(root.get(string));
		}
		
		List<ActivityBean> results = getSession().createQuery(qry.where(p1,p2,p4,p5).orderBy(order)).getResultList();	
		return results;
	}
	
}