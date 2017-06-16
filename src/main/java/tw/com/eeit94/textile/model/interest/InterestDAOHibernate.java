package tw.com.eeit94.textile.model.interest;

import java.util.ArrayList;
import java.util.List;

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
	private List<InterestBean> result;
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	private final String SELECT_ALL = "from InterestBean";
	
	@Override
	public List<InterestBean> selectAll() {
		Query<InterestBean> query = this.getSession().createQuery(this.SELECT_ALL, InterestBean.class);
		return query.list();
	}

	@Override
	public List<InterestBean> select(InterestBean ibean) {
		this.result = new ArrayList<>();
		this.result.add(this.getSession().get(InterestBean.class, ibean.getiId()));
		return this.result;
	}

	@Override
	public List<InterestBean> insert(InterestBean ibean) {
		this.result = new ArrayList<>();
		this.getSession().save(ibean);
		return this.select(ibean);
	}

	@Override
	public List<InterestBean> update(InterestBean ibean) {
		this.result = new ArrayList<>();
		this.getSession().update(ibean);
		return this.select(ibean);
	}

	@Override
	public List<InterestBean> delete(InterestBean ibean) {
		this.result = new ArrayList<>();
		this.getSession().delete(ibean);
		return this.select(ibean);
	}
}