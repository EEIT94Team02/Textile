package tw.com.eeit94.textile.model.reportimage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
public class ReportImgDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		// 新增測試
		// ReportImgDAOHibernate imgDAOHibernate = new
		// ReportImgDAOHibernate(sessionFactory);
		ReportImgDAOHibernate imgDAOHibernate = (ReportImgDAOHibernate) context.getBean("reportImgDAOHibernate");
		ReportImgBean imgBean = new ReportImgBean();
		imgBean.setImgPath("/images/snoopy.jpg");
		imgBean.setReptNo(2);
		// 如果出現 Exception in thread "main" org.hibernate.MappingException:
		// Unknown entity
		// 表示bean的mapping沒有設定要去SpringJavaConfiguration.class設定
		imgDAOHibernate.insertImg(imgBean);
		// //更新測試
		// imgDAOHibernate.updateImg("/images/oooooo.jpg", 1);
		// 刪除測試
		imgDAOHibernate.deleteImg(2);
		// 查詢測試
		List<ReportImgBean> list = imgDAOHibernate.selectAll(1);
		for (ReportImgBean reportImgBean : list) {
			System.out.print(
					reportImgBean.getReptImgNo() + "," + reportImgBean.getImgPath() + "," + reportImgBean.getReptNo());
			System.out.println();
		}

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}