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

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Repository(value = "activityDAO")
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
		if (select != null) {
			select.setActivityname(bean.getActivityname());
			select.setBegintime(bean.getBegintime());
			select.setEndtime(bean.getEndtime());
			select.setInterpretation(bean.getInterpretation());
			select.setPlace(bean.getPlace());
			return select;
		}
		return null;
	}

	@Override
	public boolean delete(ActivityBean bean) {
		ActivityBean select = this.select(bean);
		if (select != null) {
			getSession().delete(select);
			return true;
		}
		return false;
	}

	@Override
	public List<ActivityBean> selectByOthers(ActivityBean bean) throws ParseException {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<ActivityBean> qry = cb.createQuery(ActivityBean.class);
		Root<ActivityBean> root = qry.from(ActivityBean.class);
		qry.select(root);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Predicate p1 = cb.like(root.<String>get("activityname"),
				bean.getActivityname() == "" ? "%" : "%" + bean.getActivityname() + "%");
		Predicate p2 = null;
		if(bean.getEndtime().getTime() != 0){
			p2 = cb.between(root.<Timestamp>get("begintime"),bean.getBegintime(),bean.getEndtime());
		} else{
			p2 = cb.between(root.<Timestamp>get("begintime"),bean.getBegintime(),new Timestamp((sdf.parse("2100-12-31 23:59:59")).getTime()));
		}
		Predicate p3 = null;
		if(bean.getActivityno() != 0){
			p3 = cb.equal(root.<Integer>get("activityno"), bean.getActivityno());
		} else{
			p3 = cb.ge(root.<Integer>get("activityno"), 0);
		}
		Predicate p4 = cb.like(root.<String>get("place"), bean.getPlace() == "" ? "%" : "%" + bean.getPlace() + "%");
		
		List<ActivityBean> results = getSession().createQuery(qry.where(p1, p2, p3, p4)).getResultList();
		return results;
	}
}