package tw.com.eeit94.textile.model.interest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 控制興趣資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "interestDAO")
public class InterestDAOHibernate implements InterestDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<InterestBean> results;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	private static final String SELECT_ALL = "from InterestBean";

	@Override
	public List<InterestBean> selectAll() {
		Query<InterestBean> query = this.getSession().createQuery(SELECT_ALL, InterestBean.class);
		return query.list();
	}

	@Override
	public List<InterestBean> select(InterestBean ibean) {
		this.results = new ArrayList<>();
		this.results.add(this.getSession().load(InterestBean.class, ibean.getiId()));
		return this.results;
	}

	@Override
	public List<InterestBean> insert(InterestBean ibean) {
		this.getSession().save(ibean);
		return this.select(ibean);
	}

	@Override
	public List<InterestBean> update(InterestBean ibean) {
		this.getSession().update(ibean);
		return this.select(ibean);
	}

	@Override
	public List<InterestBean> delete(InterestBean ibean) {
		this.getSession().delete(ibean);
		return this.select(ibean);
	}

	@Override
	public List<InterestBean> selectByPrimaryKeys(List<Integer> primaryKeys) {
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<InterestBean> query = cBuilder.createQuery(InterestBean.class);
		Root<InterestBean> root = query.from(InterestBean.class);
		List<Predicate> pList = new ArrayList<>();
		for (int i = 0; i < primaryKeys.size(); i++) {
			Predicate p = cBuilder.equal(root.<Integer>get("iId"), primaryKeys.get(i));
			pList.add(p);
		}
		Predicate[] pArray = new Predicate[pList.size()];
		for (int i = 0; i < pList.size(); i++) {
			pArray[i] = pList.get(i);
		}
		Predicate pOr = cBuilder.or(pArray);
		query = query.select(root).where(pOr);
		return this.getSession().createQuery(query).getResultList();
	}
}