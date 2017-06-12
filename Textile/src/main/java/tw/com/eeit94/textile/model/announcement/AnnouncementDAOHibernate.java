package tw.com.eeit94.textile.model.announcement;

import java.text.ParseException;
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
 * @author 周
 * @version 2017/06/12
 */
@Repository
public class AnnouncementDAOHibernate implements AnnouncementDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public AnnouncementDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<AnnouncementBean> select() {

		return this.getSession().createQuery("From AnnouncementBean", AnnouncementBean.class).getResultList();
	}

	@Override
	public AnnouncementBean select(AnnouncementBean bean) {

		return getSession().get(AnnouncementBean.class, bean.getA_id());
	}

	@Override
	public AnnouncementBean insert(AnnouncementBean bean) {
		if (bean != null) {
			AnnouncementBean select = this.select(bean);
			if (select == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public AnnouncementBean update(AnnouncementBean bean) {
		AnnouncementBean kappa = this.select(bean);
		if (kappa != null) {
			kappa.setA_id(bean.getA_id());
			kappa.setA_type(bean.getA_type());
			kappa.setFre(bean.getFre());
			kappa.setMsg(bean.getMsg());
			kappa.setGist(bean.getGist());
			kappa.setNextTime(bean.getNextTime());
			kappa.setStartTime(bean.getStartTime());
			kappa.setEndTime(bean.getEndTime());
			kappa.setRelTime(bean.getRelTime());
		}
		return kappa;
	}

	@Override
	public boolean delete(AnnouncementBean del) {
		AnnouncementBean bean = this.select(del);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	@Override
	public List<AnnouncementBean> selectByBginTime(AnnouncementBean bean) throws ParseException {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<AnnouncementBean> qry = cb.createQuery(AnnouncementBean.class);
		Root<AnnouncementBean> root = qry.from(AnnouncementBean.class);
		qry.select(root);
		// DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Predicate p2;
		Predicate p5;
		if (bean.getStartTime() != null) {
			p5 = cb.lessThan(root.<java.util.Date>get("startTime"), bean.getStartTime());
			p2 = cb.greaterThan(root.<java.util.Date>get("endTime"), bean.getStartTime());
		} else {
			p5 = cb.greaterThan(root.<java.util.Date>get("startTime"), new java.util.Date(0));
			p2 = cb.greaterThan(root.<java.util.Date>get("endTime"), new java.util.Date(0));
		}
		Predicate p1 = cb.like(root.<String>get("a_type"),
				bean.getA_type() == null ? "%" : "%" + bean.getA_type() + "%");
		Predicate p3 = cb.like(root.<String>get("gist"), bean.getGist() == null ? "%" : "%" + bean.getGist() + "%");
		Predicate p4 = cb.like(root.<String>get("fre"), bean.getFre() == null ? "%" : "%" + bean.getFre() + "%");
		List<AnnouncementBean> result = getSession().createQuery(qry.where(p1, p2, p3, p4, p5)).getResultList();

		return result;
	}
}