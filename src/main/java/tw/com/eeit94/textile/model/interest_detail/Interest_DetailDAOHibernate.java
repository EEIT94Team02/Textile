package tw.com.eeit94.textile.model.interest_detail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 控制興趣明細資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "interest_DetailDAO")
public class Interest_DetailDAOHibernate implements Interest_DetailDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<Interest_DetailBean> result;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	private final String SELECT_ALL = "from Interest_DetailBean";
	
	@Override
	public List<Interest_DetailBean> selectAll() {
		Query<Interest_DetailBean> query = this.getSession().createQuery(this.SELECT_ALL, Interest_DetailBean.class);
		return query.list();
	}

	@Override
	public List<Interest_DetailBean> select(Interest_DetailBean i_dbean) {
		this.result = new ArrayList<>();
		this.result.add(this.getSession().get(Interest_DetailBean.class, i_dbean.getmId()));
		return this.result;
	}

	@Override
	public List<Interest_DetailBean> insert(Interest_DetailBean i_dbean) {
		this.result = new ArrayList<>();
		this.getSession().save(i_dbean);
		return this.select(i_dbean);
	}

	@Override
	public List<Interest_DetailBean> update(Interest_DetailBean i_dbean) {
		this.result = new ArrayList<>();
		this.getSession().update(i_dbean);
		return this.select(i_dbean);
	}

	@Override
	public List<Interest_DetailBean> delete(Interest_DetailBean i_dbean) {
		this.result = new ArrayList<>();
		this.getSession().delete(i_dbean);
		return this.select(i_dbean);
	}
}