package tw.com.eeit94.textile.model.theme;

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
@Repository(value = "themeDAO")
public class ThemeDAOHibernate implements ThemeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ThemeDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override // 查詢單筆主題
	public ThemeBean select(Integer themeNo) {
		return getSession().get(ThemeBean.class, themeNo);
	}

	@Override // 新增主題
	public ThemeBean insertTheme(ThemeBean themeBean) {
		if (themeBean != null) {
			this.getSession().save(themeBean);
			return themeBean;
		}
		return null;
	}

	@Override // 更新主題
	public ThemeBean updateTheme(Integer themeNo, String themeStyle, Boolean themeStatus) {
		ThemeBean themeBean = this.select(themeNo);
		if (themeBean != null) {
			themeBean.setThemeStyle(themeStyle);
			themeBean.setThemeStatus(themeStatus);
		}
		return themeBean;
	}

	@Override // 搜尋會員主題狀態true fales
	public List<ThemeBean> selectBoolan(Integer mId) {

		Query query = null;
		if (mId != null) {
			query = getSession().createQuery("FROM ThemeBean where mId=:memberNo and themeStatus=:themeStatus",
					ThemeBean.class);
			query.setParameter("memberNo", mId);
			query.setParameter("themeStatus", true);
		}
		return query.getResultList();
	}

	@Override // 搜尋會員主題列表
	public List<ThemeBean> selectMemberTheme(Integer mId) {
		Query<ThemeBean> query = getSession().createQuery("FROM ThemeBean where mId=:memberNo", ThemeBean.class);
		query.setParameter("memberNo", mId);
		return query.getResultList();
	}

	@Override // 刪除主題
	public boolean deleteTheme(Integer themeNo) {
		ThemeBean themeBean = this.select(themeNo);
		if (themeBean != null) {
			this.getSession().delete(themeBean);
			return true;
		}
		return false;
	}

	@Override
	public List<ThemeBean> selectByOthers(ThemeBean themeBean) {
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