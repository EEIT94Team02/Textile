package tw.com.eeit94.textile.model.social;

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
public class SocialListDAOHibernate implements SocialListDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<SocialListBean> select() {
		return this.getSession().createQuery("From SocialListBean", SocialListBean.class).getResultList();
	}

	@Override
	public SocialListBean select(SocialListPK pk) {
		return this.getSession().get(SocialListBean.class, pk);
	}

	@Override
	public SocialListBean insert(SocialListBean bean) {
		if (bean != null) {
			SocialListBean select = this.select(bean.getSocialListPK());
			if (select == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public SocialListBean update(SocialListBean bean) {
		SocialListBean kappa = this.select(bean.getSocialListPK());
		if (kappa != null) {
			kappa.setS_type(bean.getS_type());
			kappa.setS_group(bean.getS_group());
		}
		return kappa;
	}

	@Override
	public boolean delete(SocialListBean del) {
		SocialListBean bean = this.select(del.getSocialListPK());
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	

	@Override
	public List<SocialListBean> selectByFriend(SocialListBean bean, Timestamp date) throws ParseException {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		// DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Root<SocialListBean> root = qry.from(SocialListBean.class);
		qry.select(root);
		Predicate p1;
		Predicate p3;
		if (date != null) {
			p1 = cb.lessThan(root.<Timestamp>get("log_in"), date);
		} else {
			p1 = cb.greaterThan(root.<Timestamp>get("log_in"), new Timestamp(0));
		}
		if (bean.getSocialListPK().getUserId() != null && bean.getSocialListPK() != null) {
			p3 = cb.equal(root.<Integer>get("socialListPK").get("userId"), bean.getSocialListPK().getUserId());
		} else {
			p3 = cb.ge(root.<Integer>get("socialListPK").get("userId"), 0);
		}
		Predicate p2 = cb.like(root.<String>get("s_group"),
				bean.getS_group() == null ? "%" : "%" + bean.getS_group() + "%");
		Predicate p4 = cb.like(root.<String>get("s_type"),
				bean.getS_type() == null ? "%" : "%" + bean.getS_type() + "%");

		List<SocialListBean> result = getSession().createQuery(qry.where(p1, p2, p3, p4)).getResultList();
		return result;
	}

	

	

	
}