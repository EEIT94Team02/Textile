package tw.com.eeit94.textile.model.reporupdatetimage;

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
@Repository(value = "reportUpdateImgDAO")
public class ReportUpdateImgDAOHibernate implements ReportUpdateImgDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ReportUpdateImgDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ReportUpdateImgBean select(Integer reptUpNo) {
		return getSession().get(ReportUpdateImgBean.class, reptUpNo);

	}

	@Override
	public List<ReportUpdateImgBean> selectAll(Integer reptUpNo) {
		Query<ReportUpdateImgBean> query = getSession().createQuery("FROM ReportUpdateImgBean where reptUpNo=:reportUpNo ",
				ReportUpdateImgBean.class);
		query.setParameter("reportUpNo", reptUpNo);
		return query.getResultList();
	}

	@Override
	public ReportUpdateImgBean insertImg(ReportUpdateImgBean imgBean) {
		if (imgBean != null) {
			System.out.println(imgBean.getImgUpPath()+imgBean.getReptUpImgNo()+imgBean.getReptUpNo());
			this.getSession().save(imgBean);
			return imgBean;
		}
		return null;
	}

	@Override
	public boolean deleteImg(Integer reptUpImgNo) {
		ReportUpdateImgBean imgBean = this.select(reptUpImgNo);
		if (imgBean != null) {
			this.getSession().delete(imgBean);
			return true;
		}
		return false;
	}
}