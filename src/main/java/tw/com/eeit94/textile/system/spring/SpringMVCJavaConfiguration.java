package tw.com.eeit94.textile.system.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Spring MVC Java 組態設定檔。 DispatcherServlet的Bean
 * Container，Interceptor、Controller或View元件宣告在此。
 * 
 * View元件產生步驟：
 * 
 * 1. 每個View(就是jsp)都要設置Bean，即使是各目錄下的index.jsp。
 * 
 * 2. name設定和Controller回傳的字串相同，name也可以設定Url Pattern如"/pages/product.v"，
 * 
 * 3. Controller回傳的頁面如果是相同的頁面必用InternalResourceView，
 * 
 * 如果是Controller回傳新的jsp，只有該目錄下的index.jsp可以使用RedirectView。
 * 
 * (注意request scope的問題，有些東西可能要放在session scope)
 * 
 * 4. 每個目錄都會有各自的首頁叫index.jsp，
 * 
 * 例如：真實路徑是/Textile/report/index.jsp，
 * 
 * 如果現在在/Textile/的位置， 那麼超連結只要「report/」，
 * 
 * Bean name為「/report/index.v」，SetUrl為「/report/index.jsp」。
 * 
 * 例如：真實路徑是/Textile/report/reportlog.jsp，
 * 
 * 如果現在在/Textile/report/的位置，那麼超連結只要「reportlog.v」，
 * 
 * Bean name為「/report/reportlog.v」，SetUrl為「/report/reportlog.jsp」。
 * 
 * 5. Bean的方法名稱就是Bean的真實ID(變數名稱)，謹慎取名。
 * 
 * @author 賴
 * @version 2017/06/14
 */
@Configuration
@ComponentScan(basePackages = { "tw.com.eeit94.textile.controller" })
@EnableWebMvc
public class SpringMVCJavaConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * ****** View Controller ******
	 * 
	 * 每個View元件(請求路徑為「.v」結尾者)，都必須由ViewController經手，才能從JSP之間直接來往(View元件)。
	 * 
	 * @author 共同
	 * @version 2017/06/14
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/*
		 * 賴
		 */
		registry.addViewController("/index.v").setViewName("/index.v");
		registry.addViewController("/error/404.v").setViewName("/error/404.v");
		registry.addViewController("/check/register.v").setViewName("/check/register.v");
		registry.addViewController("/check/login.v").setViewName("/check/login.v");
		registry.addViewController("/check/index.v").setViewName("/check/index.v");
		registry.addViewController("/manager/index.v").setViewName("/manager/index.v");
		registry.addViewController("/user/index.v").setViewName("/user/index.v");
		/*
		 * 陳
		 */
		/*
		 * 李
		 */
		registry.addViewController("/store/index.v").setViewName("/store/index.v");
		registry.addViewController("/store/pMaintenance.v").setViewName("/store/pMaintenance.v");
		registry.addViewController("/store/pSingle.v").setViewName("/store/pSingle.v");
		registry.addViewController("/store/pTestTrigger.v").setViewName("/store/pTestTrigger.v");
		registry.addViewController("/item/iTestTrigger.v").setViewName("/item/iTestTrigger.v");
		/*
		 * 黃
		 */
		registry.addViewController("/report/index.v").setViewName("/report/index.v");
		registry.addViewController("/report/createreport.v").setViewName("/report/createreport.v");
		registry.addViewController("/report/reportsuccess.v").setViewName("/report/reportsuccess.v");		
		registry.addViewController("/manager/index.v").setViewName("/manager/index.v");		
		/*
		 * 周
		 */
		registry.addViewController("/social/index.v").setViewName("/social/index.v");
		
		super.addViewControllers(registry);
	}

	/**
	 * ****** Interceptor ******
	 * 
	 * @author 共同
	 * @version 2017/06/15
	 */
	@Bean
	public tw.com.eeit94.textile.system.supervisor.PathInterceptor pathInterceptor() {
		return new tw.com.eeit94.textile.system.supervisor.PathInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.pathInterceptor()).addPathPatterns("/**/*.do", "/**/*.v")
				.pathMatcher(new AntPathMatcher());
	}

	/**
	 * ****** Static Resource ******
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").resourceChain(true);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").resourceChain(true);
		registry.addResourceHandler("/image/**").addResourceLocations("/image/").resourceChain(true);
		registry.addResourceHandler("/album/**").addResourceLocations("/album/").resourceChain(true);
		super.addResourceHandlers(registry);
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

	/**
	 * ****** View ******
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	// 首頁。
	@Bean(name = { "/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView main_index_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/index.jsp");
		return internalResourceView;
	}

	// 錯誤網頁，萬用的404。
	@Bean(name = { "/error/404.v" })
	public org.springframework.web.servlet.view.InternalResourceView error_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/error/404.jsp");
		return internalResourceView;
	}

	// 註冊會員頁面。
	@Bean(name = { "/check/register.v" })
	public org.springframework.web.servlet.view.InternalResourceView register_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/register.jsp");
		return internalResourceView;
	}

	// 登入成功，導向首頁。
	@Bean(name = { "login.success" })
	public org.springframework.web.servlet.view.RedirectView login_success() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/index.jsp");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 登入畫面，登入失敗時轉回同一登入畫面。
	@Bean(name = { "/check/login.v", "/check/index.v", "login.error" })
	public org.springframework.web.servlet.view.InternalResourceView login_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/login.jsp");
		return internalResourceView;
	}

	// 登出畫面。
	@Bean(name = { "logout.success" })
	public org.springframework.web.servlet.view.InternalResourceView logout_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/logout.jsp");
		return internalResourceView;
	}

	// 系統記錄畫面，只有管理員可以使用。
	@Bean(name = { "/manager/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView logs_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/index.jsp");
		return internalResourceView;
	}

	// 列出或刪除系統紀錄成功，轉向同一系統記錄畫面，只有管理員可以使用。
	@Bean(name = { "logs.success", })
	public org.springframework.web.servlet.view.InternalResourceView logs_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/index.jsp");
		return internalResourceView;
	}

	// 個人資訊的頁面。
	@Bean(name = { "/user/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView profile_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/index.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 陳
	 * @version 2017/06/14
	 */
	// 執行失敗，轉回同一畫面。
	@Bean(name = { "insert.error" })
	public org.springframework.web.servlet.view.InternalResourceView albumcreate_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/insert.jsp");
		return internalResourceView;
	}
	
	@Bean(name = { "update.error" })
	public org.springframework.web.servlet.view.InternalResourceView albumupdate_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/update.jsp");
		return internalResourceView;
	}

	// 創建相簿成功，轉向相簿首頁。
	@Bean(name = { "album.default" })
	public org.springframework.web.servlet.view.InternalResourceView showAlbum() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/allalbum.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 李
	 * @version 2017/06/14
	 */
	
	// 測試用，為商品頁面呼叫controller
		@Bean(name = { "/store/pTestTrigger.v" })
		public org.springframework.web.servlet.view.InternalResourceView productTrigger() {
			org.springframework.web.servlet.view.InternalResourceView view = new org.springframework.web.servlet.view.InternalResourceView();
			view.setUrl("/store/pTestTrigger.jsp");
			return view;
		}
	// 測試用，為物品欄頁面呼叫controller
	@Bean(name = { "/item/iTestTrigger.v" })
	public org.springframework.web.servlet.view.InternalResourceView itemTrigger() {
		org.springframework.web.servlet.view.InternalResourceView view = new org.springframework.web.servlet.view.InternalResourceView();
		view.setUrl("/item/iTestTrigger.jsp");
		return view;
	}
	
	// 商店首頁
	@Bean(name = { "/store/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView productIndex() {
		org.springframework.web.servlet.view.InternalResourceView view = new org.springframework.web.servlet.view.InternalResourceView();
		view.setUrl("/store/index.jsp");
		return view;
	}

	// 商品總表頁面
	@Bean(name = { "pList.show" })
	public InternalResourceView productList() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/index.jsp");
		return view;
	}

	// 個別商品頁面
	@Bean(name = { "/store/pSingle.v" })
	public InternalResourceView productSingle() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/pSingle.jsp");
		return view;
	}
	
	// 使用RedirectView導向個別商品頁面
	@Bean(name = { "pSingle.show" })
	public RedirectView productSingleR() {
		RedirectView view = new RedirectView();
		view.setUrl("/store/pSingle.v");
		view.setContextRelative(true);
		return view;
	}

	// 商品維護頁面
	@Bean(name = { "/store/pMaintenance.v" })
	public InternalResourceView productMaintainV() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/pMaintenance.jsp");
		return view;
	}
	
	// 使用InternalResourceView導向商品維護頁面
	@Bean(name = { "pMaintenance.show" })
	public InternalResourceView productMaintain() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/pMaintenance.jsp");
		return view;
	}
	
	// 使用RedirectView導向商品維護頁面
	@Bean(name = { "pMaintenance.show.r" })
	public RedirectView productMaintainR() {
		RedirectView view = new RedirectView();
		view.setUrl("/store/pMaintenance.v");
		view.setContextRelative(true);
		return view;
	}
	
	@Bean(name = { "iList.show" })
	public InternalResourceView itemList() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/item/index.jsp");
		return view;
	}
	
	/**
	 * ****** View ******
	 * 
	 * @author 黃
	 * @version 2017/06/14
	 */
	//回報首頁
	@Bean(name = { "/report/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView reportIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/index.jsp");
		return internalResourceView;
	}
	
	//回報失敗，轉向回報頁面。
	@Bean(name = { "report.error","/report/createreport.v" })
	public org.springframework.web.servlet.view.InternalResourceView report_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/createreport.jsp");
		return internalResourceView;
	}
	
	//新增回報成功，轉向到回報結果頁面。
	@Bean(name = { "report.success","/report/reportsuccess.v" })
	public org.springframework.web.servlet.view.InternalResourceView report_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportsuccess.jsp");
		return internalResourceView;
	}
	
	//管理員view
	@Bean(name = { "/manager/index.v" })
	public InternalResourceView managerIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/index.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 周
	 * @version 2017/06/14
	 */
	 //公告首頁
	 @Bean(name={"/announcement.v"})
	 public InternalResourceView announcement(){
		 InternalResourceView internalResourceView = new InternalResourceView();
		 internalResourceView.setUrl("/announcement/announcement.jsp");
			return internalResourceView; 
	 }
	 //社交名單首頁
	 @Bean(name={"/social/index.v"})
	 public InternalResourceView socialList(){
		 InternalResourceView internalResourceView = new InternalResourceView();
		 internalResourceView.setUrl("/social/index.jsp");
			return internalResourceView; 
	 }
}