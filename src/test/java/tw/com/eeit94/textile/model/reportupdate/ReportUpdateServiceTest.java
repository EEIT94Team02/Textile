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
public class ReportUpdateServiceTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ReportUpdateService service = (ReportUpdateService) context.getBean("reportUpdateService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		
		ReportUpdateBean selectTest = new ReportUpdateBean();
		selectTest.setReptUpNo(1);
		ReportUpdateBean select = service.select(selectTest); 
		System.out.println(
				select.getReptUpNo()+
				select.getReplyUpDetail()+
				select.getReptUpDetail()+
				select.getReptNo()
				);
		
		ReportUpdateBean bean = new ReportUpdateBean();
		bean.setReptNo(1);
		List<ReportUpdateBean> list = service.selectUpdateList(bean);
		for(ReportUpdateBean uBean:list){
			System.out.println(
					uBean.getReptUpNo()+
					uBean.getReplyUpDetail()+
					uBean.getReptUpDetail()+
					uBean.getReptNo()
					);
		}
		
		ReportUpdateBean ins = new ReportUpdateBean();
		ins.setReptUpDate(new java.sql.Timestamp(new Date().getTime()));
		ins.setReptUpDetail("service會員新增測試");
		ins.setReptNo(1);
		service.insert(ins);
		
		ReportUpdateBean mgr = new ReportUpdateBean();
		mgr.setReplyUpDetail("service管理員回覆測試");
		mgr.setReptUpNo(4);
		service.reply(mgr);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}