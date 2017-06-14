package tw.com.eeit94.textile.system.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring啟動時要載入的組態設定檔和伺服器相關的元件。
 * 
 * 所有Filter所過濾的請求路徑都必導向Controller。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 使用Spring的交易管理需要openSessionInViewFilter。
	 * 
	 * addMappingForServletNames方法中的isMatchAfter如果設為false，則這個Filter是第一個被映射到的。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		javax.servlet.FilterRegistration errorFilterRegistration = servletContext.addFilter("errorFilter",
				tw.com.eeit94.textile.system.supervisor.ErrorFilter.class);
		errorFilterRegistration.addMappingForServletNames(null, false, "/*");
		javax.servlet.FilterRegistration loginFilterRegistration = servletContext.addFilter("loginFilter",
				tw.com.eeit94.textile.system.supervisor.LoginFilter.class);
		loginFilterRegistration.addMappingForServletNames(null, true, "/*");
		javax.servlet.FilterRegistration pathFilterRegistration = servletContext.addFilter("pathFilter",
				tw.com.eeit94.textile.system.supervisor.LoginFilter.class);
		pathFilterRegistration.addMappingForServletNames(null, true, "/*");
		javax.servlet.FilterRegistration openSessionInViewFilterRegistration = servletContext.addFilter(
				"openSessionInViewFilter", org.springframework.orm.hibernate5.support.OpenSessionInViewFilter.class);
		openSessionInViewFilterRegistration.addMappingForUrlPatterns(null, true, "*.do");
		super.onStartup(servletContext);
	}

	/**
	 * DispatcherServlet找不到Bean時，會到最底層的Bean Container尋找。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { tw.com.eeit94.textile.system.spring.SpringJavaConfiguration.class };
	}

	/**
	 * DispatcherServlet的Bean Container。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { tw.com.eeit94.textile.system.spring.SpringMVCJavaConfiguration.class };
	}

	/**
	 * DispatcherServlet要接收的Url Pattern。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do", "*.v" };
	}
}