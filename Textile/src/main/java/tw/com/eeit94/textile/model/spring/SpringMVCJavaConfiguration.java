package tw.com.eeit94.textile.model.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * DispatcherServlet的Bean Container，Controller或View元件宣告在此。
 */
//@Configuration
//@ComponentScan(basePackages = { "*.controller" })
//@EnableWebMvc
public class SpringMVCJavaConfiguration extends WebMvcConfigurerAdapter {

	/*
	 * View元件產生步驟：
	 * 1. name設定和Controller回傳的字串相同，name也可以設定Url Pattern如"/pages/product.jsp"。
	 * 2. 方法名稱就是Bean的真實ID(變數名稱)，謹慎取名。
	 * 範例如下：
	 */
	/*
	// ******View Resolver******
	@Bean(name = { "login.success" })
	public org.springframework.web.servlet.view.RedirectView login_success() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/index.jsp");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	@Bean(name = { "login.error" })
	public org.springframework.web.servlet.view.InternalResourceView login_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/secure/login.jsp");
		return internalResourceView;
	}
	*/

	// ******View Resolver******
	// View的ID和name打好，這個View Resolver就很夠用了。
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		org.springframework.web.servlet.view.BeanNameViewResolver beanNameViewResolver = new org.springframework.web.servlet.view.BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
		registry.viewResolver(beanNameViewResolver);
	}
}