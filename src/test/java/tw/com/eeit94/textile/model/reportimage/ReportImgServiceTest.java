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
public class ReportImgServiceTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ReportImgService service = (ReportImgService) context.getBean("reportImgService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		// 新增圖片
		ReportImgBean InsertNewImg = new ReportImgBean();
		InsertNewImg.setImgPath("/123/456/789.jpg");
		InsertNewImg.setReptNo(1);
		service.InsertNewImg(InsertNewImg);
		// 刪除圖片
		ReportImgBean deleteImg = new ReportImgBean();
		deleteImg.setReptImgNo(3);
		service.deleteImg(deleteImg);
		// 查詢圖片
		ReportImgBean selectRrptImg = new ReportImgBean();
		selectRrptImg.setReptNo(1);
		List<ReportImgBean> list = service.selectRrptImg(selectRrptImg);
		for (ReportImgBean bean : list) {
			System.out.println("圖片編號" + bean.getReptImgNo() + "圖片路徑" + bean.getImgPath() + "回報編號" + bean.getReptNo());
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}