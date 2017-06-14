package tw.com.eeit94.textile.system.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Spring MVC Java 組態設定檔。 DispatcherServlet的Bean
 * Container，Interceptor、Controller或View元件宣告在此。
 * 
 * @author 賴
 * @version 2017/06/10
 */
@Configuration
@ComponentScan(basePackages = { "tw.com.eeit94.textile.controller" })
@EnableWebMvc
public class SpringMVCJavaConfiguration extends WebMvcConfigurerAdapter {

	/*
	 * View元件產生步驟：
	 * 
	 * 1. name設定和Controller回傳的字串相同，name也可以設定Url Pattern如"/pages/product.jsp"。
	 * 
	 * 2. 方法名稱就是Bean的真實ID(變數名稱)，謹慎取名。 範例如下：
	 */
	/*
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
		internalResourceView.setUrl("/user/login.jsp");
		return internalResourceView;
	}
	*/
	
	@Bean(name = { "pList.show" })
	public RedirectView productList() {
		RedirectView rView = new RedirectView();
		rView.setUrl("/store/pList.jsp");
		rView.setContextRelative(true);
		return rView;
	}
	
	@Bean(name = { "pSingle.show" })
	public RedirectView productSingle() {
		RedirectView rView = new RedirectView();
		rView.setUrl("/store/pSingle.jsp");
		rView.setContextRelative(true);
		return rView;
	}

	/**
	 * ****** View Resolver ****** View的ID和name如果打好，這個View Resolver就很夠用了。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		org.springframework.web.servlet.view.BeanNameViewResolver beanNameViewResolver = new org.springframework.web.servlet.view.BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
		registry.viewResolver(beanNameViewResolver);
	}
}