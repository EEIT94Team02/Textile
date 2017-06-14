package tw.com.eeit94.textile.system.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
	/**
	 * ****** View ******
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	// 登入成功，導向首頁。
	@Bean(name = { "login.success" })
	public org.springframework.web.servlet.view.RedirectView login_success() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/index.jsp");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 登入失敗，轉回同一登入畫面。
	@Bean(name = { "login.error" })
	public org.springframework.web.servlet.view.InternalResourceView login_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/login.jsp");
		return internalResourceView;
	}

	// 登出畫面。
	@Bean(name = { "logout.success" })
	public org.springframework.web.servlet.view.RedirectView logout_success() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/check/logout.jsp");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 系統記錄畫面，只有管理員可以使用。
	@Bean(name = { "/manager/logs.jsp" })
	public org.springframework.web.servlet.view.RedirectView logs_page() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/manager/logs.jsp");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 列出或刪除系統紀錄成功，轉向同一系統記錄畫面，只有管理員可以使用。
	@Bean(name = { "logs.success", })
	public org.springframework.web.servlet.view.InternalResourceView logs_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/logs.jsp");
		return internalResourceView;
	}

	// 個人資訊的頁面。
	@Bean(name = { "/user/profile.jsp" })
	public org.springframework.web.servlet.view.RedirectView profile_page() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/user/profile.jsp");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 陳
	 * @version 2017/06/14
	 */
	// 創建相簿失敗，轉回同一畫面。
	@Bean(name = { "album.error" })
	public org.springframework.web.servlet.view.InternalResourceView albumcreate_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/album.jsp");
		return internalResourceView;
	}

	// 創建相簿成功，轉向相簿首頁。
	@Bean(name = { "album.create" , "album.default"})
	public org.springframework.web.servlet.view.InternalResourceView albumcreate_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/allalbum.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View Resolver ******
	 *
	 * View的ID和name如果打好，這個View Resolver就很夠用了。
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