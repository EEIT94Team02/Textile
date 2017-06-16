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
 * selectByBginTime 利用開始時間去尋找還在活動期間內的公告
 * selectByEndAnnouncement 列出所有已經結束的活動公告
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
			kappa.setMsg(bean.getMsg());
			kappa.setGist(bean.getGist());
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
		Predicate p4;
		if (bean.getStartTime() != null) {
			p4 = cb.lessThan(root.<java.util.Date>get("startTime"), bean.getStartTime());
			p2 = cb.greaterThan(root.<java.util.Date>get("endTime"), bean.getStartTime());
		} else {
			p4 = cb.greaterThan(root.<java.util.Date>get("startTime"), new java.util.Date(0));
			p2 = cb.greaterThan(root.<java.util.Date>get("endTime"), new java.util.Date(0));
		}
		Predicate p1 = cb.like(root.<String>get("a_type"),
				bean.getA_type() == null ? "%" : "%" + bean.getA_type() + "%");
		Predicate p3 = cb.like(root.<String>get("gist"), bean.getGist() == null ? "%" : "%" + bean.getGist() + "%");

		List<AnnouncementBean> result = getSession().createQuery(qry.where(p1, p2, p3, p4)).getResultList();

		return result;
	}

		@Override
		public List<AnnouncementBean> selectByEndAnnouncement(AnnouncementBean bean) throws ParseException {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<AnnouncementBean> qry = cb.createQuery(AnnouncementBean.class);
		Root<AnnouncementBean> root= qry.from(AnnouncementBean.class);
		qry.select(root);
		Predicate p1;
		if(bean.getEndTime()!=null){
//			p1=cb.lessThan(root.<java.util.Date>get("endTime"), bean.getEndTime()); 測試用
			p1=cb.lessThan(root.<java.util.Date>get("endTime"), new java.util.Date());//線上用
		}else{
			p1=cb.greaterThan(root.<java.util.Date>get("startTime"), new java.util.Date(0));
		}
		
		List<AnnouncementBean> result = getSession().createQuery(qry.where(p1)).getResultList();

		return result;
		
	}
}