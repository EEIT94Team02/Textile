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
		 * 共同
		 */
		registry.addViewController("/index.v").setViewName("/index.v");
		registry.addViewController("/error/404.v").setViewName("/error/404.v");
		registry.addViewController("/manager/index.v").setViewName("/manager/index.v");
		/*
		 * 賴
		 */
		registry.addViewController("/check/register.v").setViewName("/check/register.v");
		registry.addViewController("/check/login.v").setViewName("/check/login.v");
		registry.addViewController("/check/logout.v").setViewName("/check/logout.v");
		registry.addViewController("/user/index.v").setViewName("/user/index.v");
		registry.addViewController("/user/profile.v").setViewName("/user/profile.v");
		registry.addViewController("/user/modify.v").setViewName("/user/modify.v");
		registry.addViewController("/manager/logs.v").setViewName("/manager/logs.v");
		/*
		 * 陳
		 */
		registry.addViewController("/photo/index.v").setViewName("/photo/index.v");
		registry.addViewController("/photo/select.v").setViewName("/photo/select.v");
		registry.addViewController("/photo/insert.v").setViewName("/photo/insert.v");
		registry.addViewController("/photo/update.v").setViewName("/photo/update.v");
		registry.addViewController("/photo/delete.v").setViewName("/photo/delete.v");
		registry.addViewController("/activity/index.v").setViewName("/activity/index.v");
		registry.addViewController("/activity/insert.v").setViewName("/activity/insert.v");
		registry.addViewController("/activity/update.v").setViewName("/activity/update.v");
		registry.addViewController("/activity/delete.v").setViewName("/activity/delete.v");
		registry.addViewController("/activity/select.v").setViewName("/activity/select.v");

		/*
		 * 李
		 */
		registry.addViewController("/store/index.v").setViewName("/store/index.v");
		registry.addViewController("/store/pList.v").setViewName("/store/pList.v");
		registry.addViewController("/store/pSingle.v").setViewName("/store/pSingle.v");
		registry.addViewController("/store/pMaintenance.v").setViewName("/store/pMaintenance.v");
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
		registry.addViewController("/announcement/announcement.v").setViewName("/announcement/announcement.v");

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
	 * ****** View (主要為後臺) ******
	 * 
	 * @author 共同
	 * @version 2017/06/16
	 */
	// 首頁
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

	// 管理員後臺首頁。
	@Bean(name = { "/manager/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView manager_index_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/index.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	// 註冊會員頁面。
	@Bean(name = { "/check/register.v", "register.error" })
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
	@Bean(name = { "/check/index.v", "/check/login.v", "login.error" })
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

	// 個人資訊的頁面。
	@Bean(name = { "/user/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView user_index_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/index.jsp");
		return internalResourceView;
	}

	// 個人介紹的頁面。
	@Bean(name = { "/user/profile.v" })
	public org.springframework.web.servlet.view.InternalResourceView user_profile_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/profile.jsp");
		return internalResourceView;
	}

	// 修改資料的頁面。
	@Bean(name = { "/user/modify.v" })
	public org.springframework.web.servlet.view.InternalResourceView user_modify_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/modify.jsp");
		return internalResourceView;
	}

	// 系統記錄的畫面(後臺)。
	@Bean(name = { "logs.success", "/manager/logs.v" })
	public org.springframework.web.servlet.view.InternalResourceView manager_logs_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/logs.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 陳
	 * @version 2017/06/14
	 */
	// 相簿首頁,相簿顯示頁面。
	@Bean(name = { "/photo/index.v", "album.default" })
	public org.springframework.web.servlet.view.InternalResourceView albumIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/showalbum.jsp");
		return internalResourceView;
	}

	// 相簿搜尋頁面。
	@Bean(name = { "/photo/select.v" })
	public org.springframework.web.servlet.view.InternalResourceView albumSelect() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/select.jsp");
		return internalResourceView;
	}

	// 更新相簿頁面。
	@Bean(name = { "/photo/update.v", "update.error" })
	public org.springframework.web.servlet.view.InternalResourceView albumUpdate() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/update.jsp");
		return internalResourceView;
	}

	// 創建相簿頁面。
	@Bean(name = { "/photo/insert.v", "insert.error" })
	public org.springframework.web.servlet.view.InternalResourceView albumInsert() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/insert.jsp");
		return internalResourceView;
	}

	// 刪除相簿頁面。
	@Bean(name = { "/photo/delete.v", "delete.error" })
	public org.springframework.web.servlet.view.InternalResourceView albumDelete() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/delete.jsp");
		return internalResourceView;
	}

	// 活動首頁。
	@Bean(name = { "/activity/index.v" , "Activity.default"})
	public org.springframework.web.servlet.view.InternalResourceView activityIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/showActivity.jsp");
		return internalResourceView;
	}
	
	// 創建活動頁面。
	@Bean(name = { "/activity/insert.v", "actInsert.error" })
	public org.springframework.web.servlet.view.InternalResourceView ActInsert() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/createAct.jsp");
		return internalResourceView;
	}
	
	// 更新活動頁面。
	@Bean(name = { "/activity/update.v", "actUpdate.error" })
	public org.springframework.web.servlet.view.InternalResourceView ActUpdate() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/updateAct.jsp");
		return internalResourceView;
	}
	
	// 刪除活動頁面。
	@Bean(name = { "/activity/delete.v", "actDelete.error" })
	public org.springframework.web.servlet.view.InternalResourceView ActDelete() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/deleteAct.jsp");
		return internalResourceView;
	}
	
	// 查詢活動頁面。
	@Bean(name = { "/activity/select.v", "actSelect.error" })
	public org.springframework.web.servlet.view.InternalResourceView ActSelect() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/selectAct.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 李
	 * @version 2017/06/14
	 */

	// 商店首頁
	@Bean(name = { "/store/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView productIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/store/index.jsp");
		return internalResourceView;
	}

	// 商品總表頁面
	@Bean(name = { "pList.show" })
	public InternalResourceView productList() {
		InternalResourceView rView = new InternalResourceView();
		rView.setUrl("/store/pList.jsp");
		return rView;
	}

	// 個別商品頁面
	@Bean(name = { "/store/pSingle.v" })
	public InternalResourceView productSingle() {
		InternalResourceView rView = new InternalResourceView();
		rView.setUrl("/store/pSingle.jsp");
		return rView;
	}

	// 使用RedirectView導向個別商品頁面
	@Bean(name = { "pSingle.show" })
	public RedirectView productSingleR() {
		RedirectView rView = new RedirectView();
		rView.setUrl("/store/pSingle.v");
		rView.setContextRelative(true);
		return rView;
	}

	// 商品維護頁面
	@Bean(name = { "pMaintenance.show" })
	public InternalResourceView productMaintain() {
		InternalResourceView rView = new InternalResourceView();
		rView.setUrl("/store/pMaintenance.jsp");
		return rView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 黃
	 * @version 2017/06/14
	 */
	// 回報首頁
	@Bean(name = { "/report/index.v" })
	public org.springframework.web.servlet.view.InternalResourceView reportIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/index.jsp");
		return internalResourceView;
	}

	// 回報失敗，轉向回報頁面。
	@Bean(name = { "report.error", "/report/createreport.v" })
	public org.springframework.web.servlet.view.InternalResourceView report_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/createreport.jsp");
		return internalResourceView;
	}

	// 新增回報成功，轉向到回報結果頁面。
	@Bean(name = { "report.success", "/report/reportsuccess.v" })
	public org.springframework.web.servlet.view.InternalResourceView report_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportsuccess.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 周
	 * @version 2017/06/14
	 */
	// 公告首頁
	@Bean(name = { "/announcement/announcement.v" })
	public InternalResourceView announcement() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/announcement.jsp");
		return internalResourceView;
	}

	// 社交名單首頁
	@Bean(name = { "/social/index.v" })
	public InternalResourceView socialList() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/social/index.jsp");
		return internalResourceView;
	}
}