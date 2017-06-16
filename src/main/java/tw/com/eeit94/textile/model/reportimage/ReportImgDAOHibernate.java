package tw.com.eeit94.textile.model.reportimage;

import java.util.List;

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
@Repository(value = "reportImgDAO")
public class ReportImgDAOHibernate implements ReportImgDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ReportImgDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ReportImgBean select(Integer reptNo) {
		return getSession().get(ReportImgBean.class, reptNo);
	}

	@Override
	public List<ReportImgBean> selectAll(Integer reptNo) {
		Query<ReportImgBean> query = getSession().createQuery("FROM ReportImgBean where reptNo=:reportNo ",
				ReportImgBean.class);
		query.setParameter("reportNo", reptNo);
		return query.getResultList();
	}

	@Override // 新增圖片
	public ReportImgBean insertImg(ReportImgBean imgBean) {
		if (imgBean != null) {
			//驗證是否有開啟交易true是有false是沒
			//System.out.println(getSession().isJoinedToTransaction());
			this.getSession().save(imgBean);
			return imgBean;
		}
		return null;
	}

	@Override // 刪除圖片 接收圖片編號
	public boolean deleteImg(Integer reptImgNo) {
		ReportImgBean imgBean = this.select(reptImgNo);
		if (imgBean != null) {
			this.getSession().delete(imgBean);
			return true;
		}
		return false;
	}

	// @Override//更新圖片 接收圖片路徑 編號
	// public ReportImgBean updateImg(String imgPath, Integer reptNo) {
	// ReportImgBean imgBean = this.select(reptNo);
	// if(imgBean!=null){
	// imgBean.setImgPath(imgPath);
	// }
	// return imgBean;
	// }

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