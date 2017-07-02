package tw.com.eeit94.textile.model.secure;

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
 * 控制金鑰資料的DAO，利用Hibernate來實作，資料儲存後不可修改或刪除，往後只能新增或讀取。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "secureDAO")
public class SecureDAOHibernate implements SecureDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<SecureBean> result;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	private static final String SELECT_ALL = "from SecureBean";

	@Override
	public List<SecureBean> selectAll() {
		Query<SecureBean> query = this.getSession().createQuery(SELECT_ALL, SecureBean.class);
		return query.list();
	}

	@Override
	public List<SecureBean> select(SecureBean sbean) {
		this.result = new ArrayList<>();
		this.result.add(this.getSession().get(SecureBean.class, sbean.getsId()));
		return this.result;
	}

	@Override
	public List<SecureBean> insert(SecureBean sbean) {
		this.result = new ArrayList<>();
		this.getSession().save(sbean);
		return this.select(sbean);
	}

	@Override
	public List<SecureBean> update(SecureBean sbean) {
		this.result = new ArrayList<>();
		this.getSession().update(sbean);
		return this.select(sbean);
	}

	@Override
	public List<SecureBean> delete(SecureBean sbean) {
		this.result = new ArrayList<>();
		this.getSession().delete(sbean);
		return this.select(sbean);
	}

	/**
	 * 輸入欄位sTarget的參數，以搜尋需要的金鑰。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	@Override
	public List<SecureBean> selectByTarget(SecureBean sbean) {
		CriteriaBuilder cBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<SecureBean> query = cBuilder.createQuery(SecureBean.class);
		Root<SecureBean> root = query.from(SecureBean.class);
		Predicate pTarget = cBuilder.equal(root.<String>get("sTarget"), sbean.getsTarget());
		query = query.select(root).where(pTarget);
		return this.getSession().createQuery(query).getResultList();
	}
}