package tw.com.eeit94.textile.model.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 使用Spring的交易管理需要openSessionInViewFilter。
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		javax.servlet.FilterRegistration openSessionInViewFilterRegistration = servletContext.addFilter(
				"openSessionInViewFilter", org.springframework.orm.hibernate5.support.OpenSessionInViewFilter.class);
		openSessionInViewFilterRegistration.addMappingForUrlPatterns(null, true, "*.controller");
		super.onStartup(servletContext);
	}

	// DispatcherServlet找不到Bean時，會到最底層的Bean Container尋找。
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { tw.com.eeit94.textile.model.spring.SpringJavaConfiguration.class };
	}

	// DispatcherServlet的Bean Container。
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { tw.com.eeit94.textile.model.spring.SpringMVCJavaConfiguration.class };
	}

	// DispatcherServlet要接收的Url Pattern。
	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.controller" };
	}
}