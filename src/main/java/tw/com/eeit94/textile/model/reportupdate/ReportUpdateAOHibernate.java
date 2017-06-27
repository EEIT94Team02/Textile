package tw.com.eeit94.textile.model.reportupdate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/20
 */
@Repository(value = "reportUpdateDAO") // 如果這裡改了測試程式那邊也要改context.getBean("reportDAOHibernate")改context.getBean("reportDAO")
public class ReportUpdateAOHibernate implements ReportUpdateDAO {
	@Autowired // 如果寫到Service要把static拿掉
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ReportUpdateAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override // 查詢單筆回報記錄 接收reptUpNo編號
	public ReportUpdateBean select(Integer reptUpNo) {
		return getSession().get(ReportUpdateBean.class, reptUpNo);
	}

	@Override // 查詢某一筆回報的 update 紀錄 舊往新
	public List<ReportUpdateBean> selectUpDateList(Integer reptNo) {
			Query<ReportUpdateBean> query = getSession().createQuery("FROM ReportUpdateBean where reptNo=:reptNo ", ReportUpdateBean.class);
			query.setParameter("reptNo", reptNo);
			return query.getResultList();
//		CriteriaBuilder builder = getSession().getCriteriaBuilder();
//		CriteriaQuery<ReportUpdateBean> query = builder.createQuery(ReportUpdateBean.class);
//		javax.persistence.criteria.Root<ReportUpdateBean> reportUpdateBean = query.from(ReportUpdateBean.class);
//		query.select(reportUpdateBean);
//		Predicate reportNo = builder.equal(reportUpdateBean.<Integer>get("reptNo"), reptNo);
//		query.orderBy(builder.asc(reportUpdateBean.<Integer>get("reptUpNo")));
//		return getSession().createQuery(query.where(reportNo)).getResultList();
	}
	
	@Override // 查詢某一筆回報的 update 紀錄 新往舊
	public List<ReportUpdateBean> selectListBydesc(Integer reptNo) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ReportUpdateBean> query = builder.createQuery(ReportUpdateBean.class);
		javax.persistence.criteria.Root<ReportUpdateBean> reportUpdateBean = query.from(ReportUpdateBean.class);
		query.select(reportUpdateBean);
		Predicate reportNo = builder.equal(reportUpdateBean.<Integer>get("reptNo"), reptNo);
		query.orderBy(builder.desc(reportUpdateBean.<Integer>get("reptUpNo")));
		return getSession().createQuery(query.where(reportNo)).getResultList();
	}
	
	@Override
	public List<ReportUpdateBean> selectReplyNull() {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ReportUpdateBean> query = builder.createQuery(ReportUpdateBean.class);
		javax.persistence.criteria.Root<ReportUpdateBean> reportUpdateBean = query.from(ReportUpdateBean.class);
		query.select(reportUpdateBean);
		//查詢null .isNull
		Predicate reportNo = builder.isNull(reportUpdateBean.<String>get("replyUpDetail"));
		return getSession().createQuery(query.where(reportNo)).getResultList();
	}

	@Override
	public ReportUpdateBean insertReptUpDate(ReportUpdateBean bean) {
			this.getSession().save(bean);
			return bean;
	}

	@Override
	public ReportUpdateBean mgrUpdate(String replyUpDetail, Integer reptUpNo) {
		ReportUpdateBean bean = this.select(reptUpNo);
			if (bean != null) {
				bean.setReplyUpDetail(replyUpDetail);
			}
		return bean;
	}
	
	
}