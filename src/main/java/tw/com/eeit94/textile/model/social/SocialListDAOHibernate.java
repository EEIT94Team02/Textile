package tw.com.eeit94.textile.model.social;

import java.sql.Timestamp;
import java.text.ParseException;
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

import tw.com.eeit94.textile.model.member.MemberBean;

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
		this.getSession().saveOrUpdate(bean);
		return this.select(bean.getSocialListPK());
	}

	@Override
	public SocialListBean update(SocialListBean bean) {
		SocialListBean kappa = this.select(bean.getSocialListPK());
		if (kappa != null) {
			kappa.setS_type(bean.getS_type());
			kappa.setS_group(bean.getS_group());
			kappa.setLog_in(bean.getLog_in());
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
	public List<SocialListBean> searchFriend(Integer userId, String s_type, String s_group, Timestamp s_login) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		Root<SocialListBean> root = qry.from(SocialListBean.class);
		Predicate p4;
		Predicate p1 = cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("userId"), userId);
		Predicate p2 = cb.equal(root.<String>get("s_type"), s_type);
		Predicate p3 = cb.like(root.<String>get("s_group"), s_group == "" ? "%" : "%" + s_group + "%");
		if (s_login != null) {
			p4 = cb.lessThan(root.<Timestamp>get("log_in"), s_login);
		} else {
			p4 = cb.greaterThan(root.<Timestamp>get("log_in"), new Timestamp(0));
		}

		qry = qry.select(root).where(p1, p2, p3, p4);
		List<SocialListBean> users = getSession().createQuery(qry).getResultList();
		return users;
	}

	@Override
	public List<SocialListBean> selectAllFriend(Integer userId, String s_type) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		Root<SocialListBean> root = qry.from(SocialListBean.class);

		Predicate p1 = cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("userId"), userId);
		Predicate p2 = cb.equal(root.<String>get("s_type"), s_type);
		qry = qry.select(root).where(p1, p2);
		List<SocialListBean> users = getSession().createQuery(qry).getResultList();
		return users;
	}

	@Override
	public List<SocialListBean> selectbyRelationship(Integer userId, Integer acquaintenceId, String s_type) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		Root<SocialListBean> root = qry.from(SocialListBean.class);
		Predicate p1 = cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("userId"), userId);
		Predicate p2 = cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("acquaintenceId"), acquaintenceId);
		Predicate p3 = cb.equal(root.<String>get("s_type"), s_type);
		qry = qry.select(root).where(p1, p2, p3);
		List<SocialListBean> users = getSession().createQuery(qry).getResultList();
		return users;
	}

	@Override
	public List<SocialListBean> getRelativeToMeList(Integer acquaintenceId, String s_type) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		Root<SocialListBean> root = qry.from(SocialListBean.class);
		Predicate p1 = cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("acquaintenceId"), acquaintenceId);
		// Predicate p1 =
		// cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("userId"),
		// acquaintenceId);
		Predicate p2 = cb.equal(root.<String>get("s_type"), s_type);
		qry = qry.select(root).where(p1, p2);
		List<SocialListBean> users = getSession().createQuery(qry).getResultList();
		return users;
	}

	@Override
	public List<SocialListBean> selectByFriend(SocialListBean bean, Timestamp date) throws ParseException {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		// DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Root<SocialListBean> root = qry.from(SocialListBean.class);
		qry.select(root);
		Predicate p1;

		if (date != null) {
			p1 = cb.lessThan(root.<Timestamp>get("log_in"), date);
		} else {
			p1 = cb.greaterThan(root.<Timestamp>get("log_in"), new Timestamp(0));
		}
		Predicate p3 = cb.like(root.<MemberBean>get("mbean").<String>get("mName"),
				bean.getMbean().getmName() == null ? "%" : "%" + bean.getMbean().getmName() + "%");

		Predicate p2 = cb.like(root.<String>get("s_group"),
				bean.getS_group() == null ? "%" : "%" + bean.getS_group() + "%");
		Predicate p4 = cb.like(root.<String>get("s_type"),
				bean.getS_type() == null ? "%" : "%" + bean.getS_type() + "%");

		List<SocialListBean> result = getSession().createQuery(qry.where(p1, p2, p3, p4)).getResultList();
		return result;
	}

	@Override
	public List<SocialListBean> selectAll(int userId, List<String> s_type) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<SocialListBean> qry = cb.createQuery(SocialListBean.class);
		Root<SocialListBean> root = qry.from(SocialListBean.class);
		List<Predicate> pList = new ArrayList<>();
		Predicate pUserId = cb.equal(root.<SocialListPK>get("socialListPK").<Integer>get("userId"), userId);
		if (s_type != null) {
			for (String type : s_type) {
				Predicate pType = cb.equal(root.<String>get("s_type"), type);
				pList.add(pType);
			}
		}
		Predicate[] pArray = pList.toArray(new Predicate[pList.size()]);
		Predicate pOr = cb.or(pArray);
		List<SocialListBean> users = getSession().createQuery(qry.where(cb.and(pUserId, pOr))).getResultList();
		return users;
	}
}