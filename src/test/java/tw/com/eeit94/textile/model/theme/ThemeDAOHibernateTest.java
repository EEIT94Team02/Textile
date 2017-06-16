package tw.com.eeit94.textile.model.theme;

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
public class ThemeDAOHibernateTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		// ThemeDAOHibernate themeDAOHibernate = new ThemeDAOHibernate();
		ThemeDAOHibernate themeDAOHibernate = (ThemeDAOHibernate) context.getBean("themeDAO");
		// 查詢單筆主題測試
		ThemeBean bean = themeDAOHibernate.select(1);
		System.out.println("主題編號" + bean.getThemeNo() + "," + "主題樣式" + bean.getThemeStyle() + "," + "主題狀態"
				+ bean.getThemeStatus() + "," + "會員編號" + bean.getmId());

		// 查詢會員主題列表測試
		List<ThemeBean> list = themeDAOHibernate.selectMemberTheme(12);
		for (ThemeBean themeBean : list) {
			System.out.print("主題編號" + themeBean.getThemeNo() + "," + "主題樣式" + themeBean.getThemeStyle() + "," + "主題狀態"
					+ themeBean.getThemeStatus() + "," + "會員編號" + themeBean.getmId());
			System.out.println();
		}

		// 新增主題測試
		ThemeBean themeBean = new ThemeBean();
		themeBean.setThemeStyle("123456789.css");
		themeBean.setThemeStatus(true);
		themeBean.setmId(24);
		themeDAOHibernate.insertTheme(themeBean);

		// 更新主題測試
		themeDAOHibernate.updateTheme(8, "123789.css", true);
		// 查詢會員含有true的表格測試 Type specified for TypedQuery錯誤
		List<ThemeBean> list2 = themeDAOHibernate.selectBoolan(24);
		for (ThemeBean themeBean2 : list2) {
			System.out.println("主題編號" + themeBean2.getThemeNo() + "," + "主題樣式" + themeBean2.getThemeStyle() + ","
					+ "主題狀態" + themeBean2.getThemeStatus() + "," + "會員編號" + themeBean2.getmId());
		}
		// 刪除測試
		themeDAOHibernate.deleteTheme(4);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}