package tw.com.eeit94.textile.model.logs;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 控制記錄資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/12
 */
@Repository(value = "logsDAO")
public class LogsDAOHibernate implements LogsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<LogsBean> results;
	private static final String CLEARED_PREFIX = "已清空共";
	private static final String CLEARED_SUFFIX = "筆紀錄，並重新開始對系統記錄。";

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<LogsBean> selectAll() {
		this.results = new ArrayList<>();
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<LogsBean> query = cBuilder.createQuery(LogsBean.class);
		Root<LogsBean> root = query.from(LogsBean.class);
		Order order = cBuilder.desc(root.<Integer>get("lId"));
		query = query.select(root).orderBy(order);
		this.results = this.getSession().createQuery(query).getResultList();
		return this.results;
	}

	private final Integer PRIMARY_KEY_FIRST = 1;

	@Override
	public List<LogsBean> deleteAll() {
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaDelete<LogsBean> delete = cBuilder.createCriteriaDelete(LogsBean.class);
		Root<LogsBean> root = delete.from(LogsBean.class);
		Predicate predicate = cBuilder.ge(root.<Integer>get("lId"), this.PRIMARY_KEY_FIRST);
		delete.where(predicate);
		int rows = this.getSession().createQuery(delete).executeUpdate();

		LogsBean lbean = new LogsBean();
		lbean.setlCreateTime(new Timestamp(System.currentTimeMillis()));
		lbean.setlLog(
				new StringBuffer().append(CLEARED_PREFIX).append(rows).append(CLEARED_SUFFIX).toString());
		this.insert(lbean);
		return this.selectAll();
	}

	@Override
	public List<LogsBean> select(LogsBean lbean) {
		this.results = new ArrayList<>();
		this.results.add(this.getSession().get(LogsBean.class, lbean.getlId()));
		return this.results;
	}

	@Override
	public List<LogsBean> insert(LogsBean lbean) {
		this.getSession().save(lbean);
		return this.select(lbean);
	}

	@Override
	public List<LogsBean> update(LogsBean lbean) {
		this.getSession().update(lbean);
		return this.select(lbean);
	}

	@Override
	public List<LogsBean> delete(LogsBean lbean) {
		this.getSession().delete(lbean);
		return this.select(lbean);
	}
}