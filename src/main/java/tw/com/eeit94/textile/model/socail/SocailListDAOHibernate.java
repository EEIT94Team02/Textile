package tw.com.eeit94.textile.model.socail;

import java.sql.Timestamp;
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
public class SocailListDAOHibernate implements SocailListDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<SocailListBean> select() {
		return this.getSession().createQuery("From SocailListBean", SocailListBean.class).getResultList();
	}

	@Override
	public SocailListBean select(SocailListPK pk) {
		return this.getSession().get(SocailListBean.class, pk);
	}

	@Override
	public SocailListBean insert(SocailListBean bean) {
		if (bean != null) {
			SocailListBean select = this.select(bean.getSocailListPK());
			if (select == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public SocailListBean update(SocailListBean bean) {
		SocailListBean kappa = this.select(bean.getSocailListPK());
		if (kappa != null) {
			kappa.setS_type(bean.getS_type());
			kappa.setS_group(bean.getS_group());
		}
		return kappa;
	}

	@Override
	public boolean delete(SocailListBean del) {
		SocailListBean bean = this.select(del.getSocailListPK());
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	

	@Override
	public List<SocailListBean> selectByFriend(SocailListBean bean, Timestamp date) throws ParseException {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocailListBean> qry = cb.createQuery(SocailListBean.class);
		// DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Root<SocailListBean> root = qry.from(SocailListBean.class);
		qry.select(root);
		Predicate p1;
		Predicate p3;
		if (date != null) {
			p1 = cb.lessThan(root.<Timestamp>get("log_in"), date);
		} else {
			p1 = cb.greaterThan(root.<Timestamp>get("log_in"), new Timestamp(0));
		}
		if (bean.getSocailListPK().getUserId() != null && bean.getSocailListPK() != null) {
			p3 = cb.equal(root.<Integer>get("socailListPK").get("userId"), bean.getSocailListPK().getUserId());
		} else {
			p3 = cb.ge(root.<Integer>get("socailListPK").get("userId"), 0);
		}
		Predicate p2 = cb.like(root.<String>get("s_group"),
				bean.getS_group() == null ? "%" : "%" + bean.getS_group() + "%");
		Predicate p4 = cb.like(root.<String>get("s_type"),
				bean.getS_type() == null ? "%" : "%" + bean.getS_type() + "%");

		List<SocailListBean> result = getSession().createQuery(qry.where(p1, p2, p3, p4)).getResultList();
		return result;
	}

	

	

	
}