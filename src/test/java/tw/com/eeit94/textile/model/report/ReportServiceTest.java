package tw.com.eeit94.textile.model.report;

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
public class ReportServiceTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ReportService service = (ReportService) context.getBean("reportService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		// 測試會員新增
		ReportBean NewRepor = new ReportBean();
		NewRepor.setmId(24);
		NewRepor.setReptDetail("新增測試");
		NewRepor.setReptType("回報種類四");
		service.createNewReport(NewRepor);
		// 測試會員更新
		ReportBean upRBC = new ReportBean();
		upRBC.setReptDetail("會員問題內容更新測試");
		upRBC.setmId(24);
		upRBC.setReptNo(3);
		service.updateReptByCus(upRBC);
		// 測試管理員新增
		ReportBean upMGR = new ReportBean();
		upMGR.setReplyDetail("管理員更新內容測試");
		upMGR.setReptNo(4);
		service.updateReptByMgr(upMGR);
		// 刪除測試
		ReportBean delete = new ReportBean();
		delete.setReptNo(5);
		service.deleteRept(delete);
		// 查詢回覆狀態
		List<ReportBean> list = service.selectReptBySituation(false);
		for (ReportBean reportBean : list) {
			System.out.print(reportBean.getReptNo() + "," + reportBean.getmId() + "," + reportBean.getReptDetail() + ","
					+ reportBean.getReplyDetail() + "," + reportBean.getReptType() + "," + reportBean.getSituation());
			System.out.println();
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}