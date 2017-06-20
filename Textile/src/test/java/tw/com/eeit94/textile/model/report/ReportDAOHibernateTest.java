package tw.com.eeit94.textile.model.report;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ReportDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		// ReportDAOHibernate daoHibernate = new
		// ReportDAOHibernate(sessionFactory);
		ReportDAOHibernate daoHibernate = (ReportDAOHibernate) context.getBean("reportDAOHibernate");
		// 查詢單筆測
		ReportBean select = daoHibernate.select(1);
		System.out.println("select" + select);

		// 查詢會員回報列表
		List<ReportBean> list = daoHibernate.selectReptByMId(24);
		for (ReportBean reportBean : list) {
			System.out.print(reportBean.getReptNo() + "," + reportBean.getmId() + "," + reportBean.getReptDetail() + ","
					+ reportBean.getReplyDetail() + "," + reportBean.getReptType() + "," + reportBean.getSituation());
			System.out.println();
		}

		// 新增回報
		ReportBean reportBean = new ReportBean();
		reportBean.setmId(12);
		reportBean.setReptDate(new java.sql.Timestamp(new Date().getTime()));// 將Date轉成Timestamp傳入
		reportBean.setReptType("回報種類");
		reportBean.setReptDetail("問題內容測試123");
		reportBean.setSituation(false);
		daoHibernate.insert(reportBean);

		// 更新會員回報測試
		ReportBean UpdateCus = new ReportBean();
		UpdateCus.setReptDetail("會員問題內容更新5");
		UpdateCus.setReptNo(6);
		daoHibernate.custUpdate(UpdateCus);
		// 更新管理員回報測試
		daoHibernate.mgrUpdate("管理員回覆", 5);
		daoHibernate.delete(9);

		// 查詢時間測試
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ReportBean SBOBeam = new ReportBean();
		List<ReportBean> beans;
		try {
			// ((sdf.parse("2100-12-31 23:59:59")).getTime() 轉成 long
			SBOBeam.setReptDate(new Timestamp((sdf.parse("2100-12-31 23:59:59")).getTime()));
			beans = daoHibernate.selectByOthers(SBOBeam);
			for (ReportBean reportBean1 : beans) {
				System.out.print(reportBean1.getReptNo() + "," + reportBean1.getmId() + ","
						+ reportBean1.getReptDetail() + "," + reportBean1.getReplyDetail() + ","
						+ reportBean1.getReptType() + "," + reportBean1.getSituation());
				System.out.println();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<ReportBean> beans2 = daoHibernate.selectReptBySituation(true);
		System.out.println("beans2=" + beans2);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}