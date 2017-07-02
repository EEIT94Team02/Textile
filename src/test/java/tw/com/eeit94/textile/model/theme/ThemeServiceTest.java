package tw.com.eeit94.textile.model.theme;

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
public class ThemeServiceTest {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ThemeService service = (ThemeService) context.getBean("themeService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		// 更改主題測試
		ThemeBean changeTheme = new ThemeBean();
		changeTheme.setThemeNo(2);
		changeTheme.setmId(24);
		service.changeTheme(changeTheme);
		// 新增主題 如果有給予狀態參數會切換
		ThemeBean createTheme = new ThemeBean();
		createTheme.setThemeStyle("qwerty/sdfgjuki/vbgh.css");
		createTheme.setmId(12);
		createTheme.setThemeStatus(true);
		service.createNewTheme(createTheme);
		// 刪除主題
		ThemeBean deleteTheme = new ThemeBean();
		deleteTheme.setmId(24);
		deleteTheme.setThemeNo(2);
		service.deleteTheme(deleteTheme);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}