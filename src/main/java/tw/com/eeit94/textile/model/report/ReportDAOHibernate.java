package tw.com.eeit94.textile.model.report;

import java.sql.Timestamp;
import java.util.Date;
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
 * @version 2017/06/12
 */
@Repository(value = "reportDAO")//如果這裡改了測試程式那邊也要改context.getBean("reportDAOHibernate")改context.getBean("reportDAO")
public class ReportDAOHibernate implements ReportDAO {
	@Autowired // 如果寫到Service要把static拿掉
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ReportDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override // 查詢單筆回報記錄 接收report編號
	public ReportBean select(Integer reptNo) {
		return getSession().get(ReportBean.class, reptNo);
	}

	@Override // 查詢會員的回報記錄 接收會員編號
	public List<ReportBean> selectReptByMId(Integer mId) {
		// 使用Java組態設定檔,所以是寫ReportBean而不是report
		Query<ReportBean> query = getSession().createQuery("FROM ReportBean where mId=:memberNo ", ReportBean.class);
		query.setParameter("memberNo", mId);
		return query.getResultList();

	}

	@Override // 查回報狀態
	public List<ReportBean> selectReptBySituation(Boolean situation) {
		Query<ReportBean> query = getSession().createQuery("FROM ReportBean where situation=:situation ",
				ReportBean.class);
		query.setParameter("situation", situation);
		return query.getResultList();
	}
	
	@Override //查詢會員最新回報
	public List<ReportBean> selectReptByMidTop(Integer mId){
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ReportBean> query = builder.createQuery(ReportBean.class);
		javax.persistence.criteria.Root<ReportBean> reportBean = query.from(ReportBean.class);
		query.select(reportBean);
		Predicate meberId = builder.equal(reportBean.<Integer>get("mId"), mId);
		//orderBy設定
		query.orderBy(builder.desc(reportBean.<Integer>get("reptNo")));
		return getSession().createQuery(query.where(meberId)).getResultList();
		
//		Query<ReportBean> query = getSession().createQuery("SELECT TOP 1 * FROM report WHERE mId=:meberID ORDER BY reptNo DESC",
//				ReportBean.class);
//		query.setParameter("meberID", mId);
//		return query.getResultList();
	}

	@Override
	public List<ReportBean> selectByOthers(ReportBean bean) {
		// Criteria query = getSession().createCriteria(ReportBean.class);
		// //已經過時

		// 創建標準工廠 解說網頁 http://java.300364.net/show/22957240577591972063.html
		// CriteriaBuilder：字段之間是什麼關係，如何生成一個查詢條件，每一個查詢條件都是什麼方式
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// CriteriaQuery：查詢哪些字段，排序是什麼
		CriteriaQuery<ReportBean> query = builder.createQuery(ReportBean.class);

		// Root：查詢哪個表 //這裡的from相對於sql的from哪個table
		javax.persistence.criteria.Root<ReportBean> reportBean = query.from(ReportBean.class);
		query.select(reportBean);
		// 查回報編號
		Predicate reptNo;
		if (bean.getReptNo() != null) {
			// Predicate（Expression）：單獨每一條查詢條件的詳細描述,等於在設定SQL的where條件
			reptNo = builder.equal(reportBean.<Integer>get("reptNo"), bean.getReptNo());
		} else {
			reptNo = builder.greaterThan(reportBean.<Integer>get("reptNo"), 0);
		}
		// 查會員編號
		Predicate mId;
		if (bean.getmId() != null) {
			mId = builder.equal(reportBean.<Integer>get("mId"), bean.getmId());
		} else {
			mId = builder.greaterThan(reportBean.<Integer>get("mId"), 0);
		}
		// 查時間
		Predicate reptDate;
		if (bean.getReptDate() != null) {
			reptDate = builder.lessThan(reportBean.<Timestamp>get("reptDate"), bean.getReptDate());
		} else {
			reptDate = builder.lessThan(reportBean.<Timestamp>get("reptDate"),
					new java.sql.Timestamp(new Date().getTime()));

		}
		// 相當於使用SQL where
		return getSession().createQuery(query.where(reptNo, mId, reptDate)).getResultList();
	}

	@Override // 新增回報資料 會員編號 回報類別 回報內容
	public ReportBean insert(ReportBean bean) {
		if (bean != null) {
				this.getSession().save(bean);
				return bean;
		}
		return null;
	}

	@Override // 會員回報內容修改 接收回報內容 編號
	public ReportBean custUpdate(ReportBean reportBean) {
		ReportBean bean = this.select(reportBean.getReptNo());
		if (bean != null) {
			bean.setReptDetail(reportBean.getReptDetail());
			bean.setSituation(false);
		}
		return bean;
	}

	@Override // 管理員回報內容修改 接收回覆內容 狀態 編號
	public ReportBean mgrUpdate(String replyDetail, Integer reptNo) {
		ReportBean bean = this.select(reptNo);
		if (bean != null) {
			bean.setReplyDetail(replyDetail);
			bean.setSituation(true);
		}
		return bean;
	}

	@Override // 刪除回報紀錄 接收回報編號
	public boolean delete(Integer reptNo) {
		ReportBean bean = this.select(reptNo);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	@Override //修改回覆狀態
	public ReportBean situationChange(Integer reptNo, Boolean situation) {
		ReportBean bean = this.select(reptNo);
		if (bean != null) {
			bean.setSituation(situation);
			return bean;
		}
		return null;
	}

	// private final String SELECT_BY_PRICE_LESS_THAN = "select name from
	// ExampleBean where price<";
	//
	// @Override
	// public List<ReportBean> selectByPriceLessThan(double price) {
	// @SuppressWarnings("unused")
	// Query<ReportBean> query =
	// this.getSession().createQuery(SELECT_BY_PRICE_LESS_THAN + price,
	// ReportBean.class);
	// /*
	// * 用Hibernate實作
	// */
	// return null;
	// }
}