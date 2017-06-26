package tw.com.eeit94.textile.model.reportupdate;

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
public class ReportUpdateDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		ReportUpdateAOHibernate daohibernate = (ReportUpdateAOHibernate) context.getBean("reportUpdateDAO");
		
		ReportUpdateBean select = daohibernate.select(1);
		System.out.println("select" + select);
		
		List<ReportUpdateBean> selectlist = daohibernate.selectUpDateList(1);
		for(ReportUpdateBean bean:selectlist){
			System.out.println(
					bean.getReptUpNo()+
					bean.getReptUpDetail()+
					bean.getReplyUpDetail()+
					bean.getReptNo()
					);			
		}
		
		ReportUpdateBean bean = new ReportUpdateBean();
		bean.setReptUpDate(new java.sql.Timestamp(new Date().getTime()));
		bean.setReptUpDetail("DAO會員更新測試");
		bean.setReptNo(1);
		daohibernate.insertReptUpDate(bean);
		
		ReportUpdateBean mgr = new ReportUpdateBean();
		mgr.setReplyUpDetail("DAO管理員更新測試");
		mgr.setReptUpNo(4);
		daohibernate.mgrUpdate(mgr.getReplyUpDetail(), mgr.getReptUpNo());
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}