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
import org.springframework.stereotype.Repository;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Repository(value = "activity_memberDAO")
public class Activity_memberDAOHibernate implements Activity_memberDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Activity_memberDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
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
		Predicate p1;
		Predicate p2;
		if (bean.getActivity_memberPK().getActivityno() != null) {
			p1 = cb.equal(root.<Integer>get("activity_memberPK").get("activityno"),
					bean.getActivity_memberPK().getActivityno());
		} else {
			p1 = cb.ge(root.<Integer>get("activity_memberPK").get("activityno"), 0);
		}
		if (bean.getActivity_memberPK().getmId() != null) {
			p2 = cb.equal(root.<Integer>get("activity_memberPK").get("mId"), bean.getActivity_memberPK().getmId());
		} else {
			p2 = cb.ge(root.<Integer>get("activity_memberPK").get("mId"), 0);
		}
		Predicate p3 = cb.like(root.<String>get("position"),
				bean.getPosition() == null ? "%" : "%" + bean.getPosition() + "%");

		List<Activity_memberBean> results = getSession().createQuery(query.where(p1, p2, p3)).getResultList();
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
		if (select != null) {
			select.setPosition(bean.getPosition());
		}
		return select;
	}

	public List<Activity_memberBean> updatePosition(List<Activity_memberBean> beans) {
		List<Activity_memberBean> result = new ArrayList<Activity_memberBean>();
		for (Activity_memberBean bean : beans) {
			result.add(this.update(bean));
		}
		return result;
	}

	@Override
	public boolean delete(Activity_memberBean bean) {
		Activity_memberBean select = this.selectByPrimaryKey(bean);
		if (select != null) {
			getSession().delete(select);
			return true;
		}
		return false;
	}
}