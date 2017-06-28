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
@EnableWebMvc
@ComponentScan(basePackages = { "tw.com.eeit94.textile.controller" })
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
		registry.addViewController("/manager/logs.v").setViewName("/manager/logs.v");
		registry.addViewController("/check/register.v").setViewName("/check/register.v");
		registry.addViewController("/check/login.v").setViewName("/check/login.v");
		registry.addViewController("/check/logout.v").setViewName("/check/logout.v");
		registry.addViewController("/check/findPassword.v").setViewName("/check/findPassword.v");
		registry.addViewController("/user/queryName.v").setViewName("/user/queryName.v");
		/*
		 * 陳
		 */
		registry.addViewController("/photo/index.v").setViewName("/photo/index.v");
		registry.addViewController("/photo/albuminsert.v").setViewName("/photo/albuminsert.v");
		registry.addViewController("/activity/index.v").setViewName("/activity/index.v");
		registry.addViewController("/activity/createAct.v").setViewName("/activity/createAct.v");
		registry.addViewController("/activity/update.v").setViewName("/activity/update.v");
		registry.addViewController("/activity/historyActivity.v").setViewName("/activity/historyActivity.v");
		registry.addViewController("/activity/select.v").setViewName("/activity/select.v");
		registry.addViewController("/photo/upload.v").setViewName("/photo/upload.v");
		registry.addViewController("/photo/myalbum.v").setViewName("/photo/myalbum.v");
		registry.addViewController("/photo/showalbum.v").setViewName("/photo/showalbum.v");
		registry.addViewController("/photo/showphoto.v").setViewName("/photo/showphoto.v");
		registry.addViewController("/activity/secede.v").setViewName("/activity/secede.v");
		registry.addViewController("/activity/allAct.v").setViewName("/activity/allAct.v");
		registry.addViewController("/activity/calendar.v").setViewName("/activity/calendar.v");
		registry.addViewController("/photo/myActivity.v").setViewName("/photo/myActivity.v");
		registry.addViewController("/photo/friendalbum.v").setViewName("/photo/friendalbum.v");
		/*
		 * 李
		 */
		registry.addViewController("/store/index.v").setViewName("/store/index.v");
		registry.addViewController("/store/pMaintenance.v").setViewName("/store/pMaintenance.v");
		registry.addViewController("/store/cart.v").setViewName("/store/cart.v");
		registry.addViewController("/item/index.v").setViewName("/item/index.v");
		registry.addViewController("/deposit/index.v").setViewName("/deposit/index.v");
		registry.addViewController("/deposit/depositList.v").setViewName("/deposit/depositList.v");
		registry.addViewController("/deposit/depositProceed.v").setViewName("/deposit/depositProceed.v");
		registry.addViewController("/deal/index.v").setViewName("/deal/index.v");
		registry.addViewController("/gift/index.v").setViewName("/gift/index.v");
		registry.addViewController("/gift/giftSendingProceed.v").setViewName("/gift/giftSendingProceed.v");
		/*
		 * 黃
		 */
		registry.addViewController("/report/index.v").setViewName("/report/index.v");
		registry.addViewController("/report/createreport.v").setViewName("/report/createreport.v");
		registry.addViewController("/report/reportsuccess.v").setViewName("/report/reportsuccess.v");
		registry.addViewController("/manager/index.v").setViewName("/manager/index.v");
		registry.addViewController("/report/reportList.v").setViewName("/report/reportList.v");
		registry.addViewController("/manager/situationList.v").setViewName("/manager/situationList.v");
		registry.addViewController("/manager/reportReply.v").setViewName("/manager/reportReply.v");
		registry.addViewController("/manager/replysuccess.v").setViewName("/manager/replysuccess.v");
		registry.addViewController("/report/reportUpdate.v").setViewName("/report/reportUpdate.v");
		registry.addViewController("/report/reportUpDatesuccess.v").setViewName("/report/reportUpDatesuccess.v");
		registry.addViewController("/report/reportDetail.v").setViewName("/report/reportDetail.v");
		registry.addViewController("/manager/reply.v").setViewName("/manager/reply.v");
		registry.addViewController("/manager/replyfollowup.v").setViewName("/manager/replyfollowup.v");
		registry.addViewController("/manager/replyfollowupsuccess.v").setViewName("/manager/replyfollowupsuccess.v");
		/*
		 * 周
		 */
		registry.addViewController("/social/index.v").setViewName("/social/index.v");
		registry.addViewController("/social/invite.v").setViewName("/social/invite.v");
		registry.addViewController("/social/select.v").setViewName("/social/select.v");
		registry.addViewController("/social/search.v").setViewName("/social/search.v");
		registry.addViewController("/announcement/index.v").setViewName("/announcement/index.v");
		registry.addViewController("/announcement/list.v").setViewName("/announcement/list.v");
		registry.addViewController("/manager/insert.v").setViewName("/manager/insert.v");
		registry.addViewController("/manager/update.v").setViewName("/manager/update.v");

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
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/").resourceChain(true);
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

	// 錯誤網頁，萬用的404。(重新導向)
	@Bean(name = { "error.404.show" })
	public org.springframework.web.servlet.view.RedirectView error_page_r() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/error/404.v");
		redirectView.setContextRelative(true);
		return redirectView;
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
	// 系統記錄的畫面(後臺)。
	@Bean(name = { "logs.show", "/manager/logs.v" })
	public org.springframework.web.servlet.view.InternalResourceView manager_logs_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/logs.jsp");
		return internalResourceView;
	}

	// 會員列表的畫面(後臺)。
	@Bean(name = { "users.show", "/manager/users.v" })
	public org.springframework.web.servlet.view.InternalResourceView manager_users_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/manager/users.jsp");
		return internalResourceView;
	}

	// 登入成功，導向首頁。(重新導向)
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

	// 找回密碼的畫面。
	@Bean(name = { "/check/findPassword.v", "findPassword.show", "findPassword.error" })
	public org.springframework.web.servlet.view.InternalResourceView find_password_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/findPassword.jsp");
		return internalResourceView;
	}

	// 找回密碼成功的畫面。
	@Bean(name = { "findPassword.success" })
	public org.springframework.web.servlet.view.InternalResourceView find_password_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/findPasswordSuccess.jsp");
		return internalResourceView;
	}

	// 註冊會員。
	@Bean(name = { "/check/register.v", "register.error" })
	public org.springframework.web.servlet.view.InternalResourceView register_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/register.jsp");
		return internalResourceView;
	}

	// 註冊成功，不要addViewController。
	@Bean(name = { "register.success" })
	public org.springframework.web.servlet.view.InternalResourceView register_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/registerSuccess.jsp");
		return internalResourceView;
	}

	// 尚未驗證信箱，不要addViewController。
	@Bean(name = { "login.invalidEmail" })
	public org.springframework.web.servlet.view.InternalResourceView login_invalidEmail() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/emailCheckRe_mind.jsp");
		return internalResourceView;
	}

	// 重寄驗證郵件，不要addViewController。
	@Bean(name = { "login.emailCheckRe_send" })
	public org.springframework.web.servlet.view.InternalResourceView login_re_sendCheckingEmail() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/emailCheckRe_send.jsp");
		return internalResourceView;
	}

	// 驗證郵件成功，不要addViewController。
	@Bean(name = { "login.emailCheckSuccess" })
	public org.springframework.web.servlet.view.InternalResourceView login_checkEmailSuccess() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/check/emailCheckSuccess.jsp");
		return internalResourceView;
	}

	// 個人資訊頁面。
	@Bean(name = { "profile.show" })
	public org.springframework.web.servlet.view.InternalResourceView profile_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/profile.jsp");
		return internalResourceView;
	}

	// 其它人的個人資訊頁面。
	@Bean(name = { "otherProfile.show" })
	public org.springframework.web.servlet.view.InternalResourceView otherProfile_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/otherProfile.jsp");
		return internalResourceView;
	}

	// 修改密碼的頁面。
	@Bean(name = { "modifySecure.show", "modifySecure.error" })
	public org.springframework.web.servlet.view.InternalResourceView modifySecure_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/modifySecure.jsp");
		return internalResourceView;
	}

	// 修改個人資料的頁面。
	@Bean(name = { "modifyProfile.show", "modifyProfile.error" })
	public org.springframework.web.servlet.view.InternalResourceView modifyProfile_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/modifyProfile.jsp");
		return internalResourceView;
	}

	// 修改個人狀況的頁面。
	@Bean(name = { "modifySituation.show", "modifySituation.error" })
	public org.springframework.web.servlet.view.InternalResourceView modifySituation_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/modifySituation.jsp");
		return internalResourceView;
	}

	// 修改興趣喜好的頁面。
	@Bean(name = { "modifyInterest.show" })
	public org.springframework.web.servlet.view.InternalResourceView modifyInterest_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/modifyInterest.jsp");
		return internalResourceView;
	}

	// 修改資料成功的頁面。
	@Bean(name = { "modify.success" })
	public org.springframework.web.servlet.view.InternalResourceView modify_success() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/modifySuccess.jsp");
		return internalResourceView;
	}

	// 驗證手機的畫面。
	@Bean(name = { "phoneCheck.show" })
	public org.springframework.web.servlet.view.InternalResourceView phoneCheck_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/phoneCheck.jsp");
		return internalResourceView;
	}

	// 驗證手機錯誤的畫面。
	@Bean(name = { "phoneCheck.error" })
	public org.springframework.web.servlet.view.InternalResourceView phoneCheck_error() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/phoneCheckError.jsp");
		return internalResourceView;
	}

	// 會員姓名查詢的畫面。
	@Bean(name = { "/user/queryName.v", "queryName.error" })
	public org.springframework.web.servlet.view.InternalResourceView queryName_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/queryName.jsp");
		return internalResourceView;
	}

	// 會員條件查詢的畫面。
	@Bean(name = { "queryCondition.show", "queryCondition.error" })
	public org.springframework.web.servlet.view.InternalResourceView queryCondition_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/queryCondition.jsp");
		return internalResourceView;
	}

	// 開啟聊天室頁面。
	@Bean(name = { "chat.show" })
	public org.springframework.web.servlet.view.InternalResourceView chat_page() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/user/chat.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 陳
	 * @version 2017/06/14
	 */
	// 相簿首頁,相簿顯示頁面。
	@Bean(name = { "album.my" })
	public org.springframework.web.servlet.view.RedirectView myalbumRefresh() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/photo/myalbum.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	@Bean(name = { "album.list" })
	public org.springframework.web.servlet.view.RedirectView albumRefresh() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/photo/showalbum.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	@Bean(name = { "/photo/index.v", "/photo/myalbum.v" })
	public org.springframework.web.servlet.view.InternalResourceView Myalbum() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/myalbum.jsp");
		return internalResourceView;
	}

	@Bean(name = { "album.friendlist" })
	public org.springframework.web.servlet.view.RedirectView friendalbumRefresh() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/photo/friendalbum.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	@Bean(name = { "/photo/friendalbum.v" })
	public org.springframework.web.servlet.view.InternalResourceView friendalbum() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/friendalbum.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/photo/showalbum.v" })
	public org.springframework.web.servlet.view.InternalResourceView showalbum() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/showalbum.jsp");
		return internalResourceView;
	}

	// 相簿搜尋頁面。
	@Bean(name = { "/photo/albumselect.v", "select.album" })
	public org.springframework.web.servlet.view.InternalResourceView albumSelect() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/albumselect.jsp");
		return internalResourceView;
	}

	// 創建相簿頁面。
	@Bean(name = { "/photo/albuminsert.v", "insert.album" })
	public org.springframework.web.servlet.view.InternalResourceView albumInsert() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/albuminsert.jsp");
		return internalResourceView;
	}

	// 上傳照片頁面。
	@Bean(name = { "/photo/upload.v", "upload.photo" })
	public org.springframework.web.servlet.view.InternalResourceView photoupload() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/upload.jsp");
		return internalResourceView;
	}

	// 更新照片頁面。
	@Bean(name = { "/photo/update.v", "update.photo" })
	public org.springframework.web.servlet.view.InternalResourceView photoupdate() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/update.jsp");
		return internalResourceView;
	}

	// 尋找照片頁面。
	@Bean(name = { "/photo/select.v", "search.photo" })
	public org.springframework.web.servlet.view.InternalResourceView photosearch() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/select.jsp");
		return internalResourceView;
	}

	// 顯示照片頁面
	@Bean(name = { "/photo/showphoto.v" })
	public org.springframework.web.servlet.view.InternalResourceView photoshow() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/photo/showphoto.jsp");
		return internalResourceView;
	}

	// 顯示照片頁面。
	@Bean(name = { "photo.list" })
	public org.springframework.web.servlet.view.RedirectView photoshow_R() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/photo/showphoto.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 顯示照片頁面。
	@Bean(name = { "Activity.default" })
	public org.springframework.web.servlet.view.RedirectView myActivityIndex() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/photo/myActivity.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 活動首頁。
	@Bean(name = { "/activity/index.v", "/photo/myActivity.v", "/activity/secede.v" })
	public org.springframework.web.servlet.view.InternalResourceView activityIndex() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/myActivity.jsp");
		return internalResourceView;
	}

	// 創建活動頁面。
	@Bean(name = { "/activity/createAct.v", "actInsert.error" })
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
	@Bean(name = { "/activity/historyActivity.v" })
	public org.springframework.web.servlet.view.InternalResourceView ActDelete() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/historyActivity.jsp");
		return internalResourceView;
	}

	// 退出活動
	@Bean(name = { "Activity.delete", "Activity.join" })
	public org.springframework.web.servlet.view.RedirectView ActSecede() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/activity/secede.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 查詢活動列表
	@Bean(name = { "Activity.AllActivitise", "Activity.select" })
	public org.springframework.web.servlet.view.RedirectView AllActS() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setUrl("/activity/allAct.v");
		redirectView.setContextRelative(true);
		return redirectView;
	}

	// 查詢活動頁面。
	@Bean(name = { "/activity/allAct.v", "actSelect.error" })
	public org.springframework.web.servlet.view.InternalResourceView ActSelect() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/showActivity.jsp");
		return internalResourceView;
	}

	// 顯示成員明細
	@Bean(name = { "Activity.partner" })
	public org.springframework.web.servlet.view.InternalResourceView ActDetail() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/ActivityDetail.jsp");
		return internalResourceView;
	}

	// 顯示行事曆
	@Bean(name = { "/activity/calendar.v" })
	public org.springframework.web.servlet.view.InternalResourceView calendar() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/activity/activityCalendar.jsp");
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
	public InternalResourceView productIndex() {
		InternalResourceView view = new InternalResourceView();
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

	// 資料錯誤訊息返回商品維護頁面
	@Bean(name = { "pMaintenance.error" })
	public InternalResourceView productMaintainError() {
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

	// 購物車頁面
	@Bean(name = { "/store/cart.v" })
	public InternalResourceView cartPage() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/cart.jsp");
		return view;
	}

	// 成功新增商品至購物車
	@Bean(name = { "addCart.success" })
	public RedirectView addCart() {
		RedirectView view = new RedirectView();
		view.setUrl("/store/index.v");
		view.setContextRelative(true);
		return view;
	}

	// 購物車中的商品資料更新成功
	@Bean(name = { "adjust.success" })
	public RedirectView adjustSuccess() {
		RedirectView view = new RedirectView();
		view.setUrl("/store/cart.v");
		view.setContextRelative(true);
		return view;
	}

	// 購物車中的商品資料更新失敗
	@Bean(name = { "adjust.error" })
	public InternalResourceView adjustError() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/cart.jsp");
		return view;
	}

	// 物品欄首頁
	@Bean(name = { "/item/index.v" })
	public InternalResourceView itemIndex() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/item/index.jsp");
		return view;
	}

	// 物品欄總覽頁面
	@Bean(name = { "iList.show" })
	public InternalResourceView itemList() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/item/index.jsp");
		return view;
	}

	// 儲值首頁
	@Bean(name = { "/deposit/index.v" })
	public InternalResourceView depositIndex() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deposit/index.jsp");
		return view;
	}

	// 儲值動作頁面
	@Bean(name = { "/deposit/depositProceed.v" })
	public InternalResourceView depositProceed() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deposit/depositProceed.jsp");
		return view;
	}

	// 個人儲值紀錄頁面
	@Bean(name = { "dList.show" })
	public InternalResourceView depositList() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deposit/depositList.jsp");
		return view;
	}

	// 儲值成功，轉向儲值首頁
	@Bean(name = { "deposit.success" })
	public InternalResourceView depositSuccess() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deposit/depositSuccess.jsp");
		return view;
	}

	// 儲值失敗，回到儲值動作頁面
	@Bean(name = { "deposit.fail" })
	public InternalResourceView depositFail() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deposit/depositProceed.jsp");
		return view;
	}

	// 交易首頁
	@Bean(name = { "/deal/index.v" })
	public InternalResourceView dealIndex() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deal/index.jsp");
		return view;
	}

	// 個人交易紀錄頁面
	@Bean(name = { "dealList.show" })
	public RedirectView dealList() {
		RedirectView view = new RedirectView();
		view.setUrl("/deal/index.v");
		view.setContextRelative(true);
		return view;
	}

	// 交易明細紀錄頁面
	@Bean(name = { "dealDetail.show" })
	public InternalResourceView dealDetailList() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deal/dealDetailList.jsp");
		return view;
	}

	// 交易成功
	@Bean(name = { "deal.success" })
	public InternalResourceView dealSuccess() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/deal/purchaseSuccess.jsp");
		return view;
	}

	// 交易失敗
	@Bean(name = { "deal.error" })
	public InternalResourceView dealError() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/store/cart.jsp");
		return view;
	}

	// 禮物首頁
	@Bean(name = { "/gift/index.v" })
	public InternalResourceView giftIndex() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/gift/index.jsp");
		return view;
	}

	// 個人收送禮物紀錄頁面
	@Bean(name = { "gListAll.show" })
	public InternalResourceView giftListAll() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/gift/index.jsp");
		return view;
	}

	// 送禮明細頁面
	@Bean(name = { "giftDetail.show" })
	public InternalResourceView giftDetail() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/gift/giftDetailList.jsp");
		return view;
	}

	// 送禮頁面
	@Bean(name = { "/gift/giftSendingProceed.v" })
	public InternalResourceView giftSending() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/gift/giftSendingProceed.jsp");
		return view;
	}

	// 取得物品資料後重新導向送禮頁面
	@Bean(name = { "giftSendingPage.show" })
	public RedirectView giftSendPage() {
		RedirectView view = new RedirectView();
		view.setUrl("/gift/giftSendingProceed.v");
		view.setContextRelative(true);
		return view;
	}

	// 送禮成功
	@Bean(name = { "giftSending.success" })
	public InternalResourceView giftSendingSuccess() {
		InternalResourceView view = new InternalResourceView();
		view.setUrl("/gift/giftSendingSuccess.jsp");
		return view;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 黃
	 * @version 2017/06/14
	 */
	// 回報首頁
	// @Bean(name = { "/report/index.v" })
	// public org.springframework.web.servlet.view.InternalResourceView
	// reportIndex() {
	// org.springframework.web.servlet.view.InternalResourceView
	// internalResourceView = new
	// org.springframework.web.servlet.view.InternalResourceView();
	// internalResourceView.setUrl("/report/index.jsp");
	// return internalResourceView;
	// }

	// 回報失敗，轉向回報頁面。
	@Bean(name = { "report.error", "/report/createreport.v", "/report/index.v" })
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

	// 會員查詢自己回報紀錄，轉向到回報列表頁面。
	@Bean(name = { "reportList.show", "/report/reportList.v" })
	public InternalResourceView reportList() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportlist.jsp");
		return internalResourceView;
	}

	// 管理員查詢所有未回覆的回報
	@Bean(name = { "situationList.show", "/manager/situationList.v" })
	public InternalResourceView situationList() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/situationlist.jsp");
		return internalResourceView;
	}

	// 管理員回覆頁面
	@Bean(name = { "/manager/reportReply.v" })
	public InternalResourceView reportReply() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportreply.jsp");
		return internalResourceView;
	}

	// 轉向管理員回覆頁面/manager/reportReply.v
	@Bean(name = { "replydetail.show" })
	public RedirectView reportReply_R() {
		// org.springframework.web.servlet.view.InternalResourceView
		// internalResourceView = new
		// org.springframework.web.servlet.view.InternalResourceView();
		// internalResourceView.setUrl("/manager/reportReply.v");
		// return internalResourceView;
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/manager/reportReply.v");
		return redirectView;
	}

	// 管理員回覆成功頁面
	@Bean(name = { "replysuccess.show", "/manager/replysuccess.v" })
	public InternalResourceView replysuccess() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/replysuccess.jsp");
		return internalResourceView;
	}

	// 管理員回覆後續內容成功頁面
	@Bean(name = { "replyfollowupsuccess.show", "/manager/replyfollowupsuccess.v" })
	public InternalResourceView replyfollowupsuccess() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/replyfollowupsuccess.jsp");
		return internalResourceView;
	} // 新增回報內容頁面

	@Bean(name = { "/report/reportUpdate.v" })
	public InternalResourceView reportUpdate() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportupdate.jsp");
		return internalResourceView;
	}

	// 轉跳到新增回報內容頁面
	@Bean(name = { "reportUpdate.show" })
	public RedirectView reportUpdate_R() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/report/reportUpdate.v");
		return redirectView;
	}

	// 新增回報內容頁面
	@Bean(name = { "/report/reportDetail.v" })
	public InternalResourceView reportdetail() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportdetail.jsp");
		return internalResourceView;
	}

	// 轉跳到新增回報內容頁面
	@Bean(name = { "reportdetail.show" })
	public RedirectView reportdetail_R() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/report/reportDetail.v");
		return redirectView;
	}

	// 回報處理頁面
	@Bean(name = { "/manager/reply.v" })
	public InternalResourceView reply() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportreplymain.jsp");
		return internalResourceView;
	}

	// 轉跳到回報處理頁面
	@Bean(name = { "reply.show" })
	public RedirectView reply_R() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/manager/reply.v");
		return redirectView;
	}

	// 回報處理後續頁面
	@Bean(name = { "/manager/replyfollowup.v" })
	public InternalResourceView replyfollowup() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportreplyfollowup.jsp");
		return internalResourceView;
	}

	// 轉跳到回報處理後續頁面
	@Bean(name = { "replyfollowup.show" })
	public RedirectView replyfollowup_R() {
		org.springframework.web.servlet.view.RedirectView redirectView = new org.springframework.web.servlet.view.RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/manager/replyfollowup.v");
		return redirectView;
	}

	// 新增回報成功，轉向到回報結果頁面。
	@Bean(name = { "reportUpDate.success", "/report/reportUpDatesuccess.v" })
	public InternalResourceView reportUpDatesuccess() {
		org.springframework.web.servlet.view.InternalResourceView internalResourceView = new org.springframework.web.servlet.view.InternalResourceView();
		internalResourceView.setUrl("/report/reportupdatesuccess.jsp");
		return internalResourceView;
	}

	/**
	 * ****** View ******
	 * 
	 * @author 周
	 * @version 2017/06/14
	 */
	// 公告首頁
	@Bean(name = { "/announcement/index.v", "alist.show" })
	public InternalResourceView announcementIndex() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/index.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/announcement/list.v", "alist.error" })
	public InternalResourceView announcementList() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/list.jsp");
		return internalResourceView;
	}

	// 成功頁面
	@Bean(name = { "/announcement/success.v", "announcement.success", "social.success" })
	public InternalResourceView announcementsuccess() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/success.jsp");
		return internalResourceView;
	}

	// 失敗頁面
	@Bean(name = { "/announcement/error.v", "announcement.error", "social.error" })
	public InternalResourceView announcementerror() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/error.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/manager/insert.v" })
	public InternalResourceView announcementinsert() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/insert.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/manager/update.v" })
	public InternalResourceView announcementupdate() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/announcement/update.jsp");
		return internalResourceView;
	}

	// 社交名單首頁
	@Bean(name = { "/social/index.v" })
	public InternalResourceView socialList() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/social/index.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/social/invite.v", "s_invite" })
	public InternalResourceView socialinvite() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/social/invite.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/social/insert.v", "s_insert" })
	public InternalResourceView socialinsert() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/social/insert.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/social/select.v", "s_select.v" })
	public InternalResourceView socialListselect() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/social/select.jsp");
		return internalResourceView;
	}

	@Bean(name = { "/social/search.v", "s_search.v" })
	public InternalResourceView socialListsearch() {
		InternalResourceView internalResourceView = new InternalResourceView();
		internalResourceView.setUrl("/social/search.jsp");
		return internalResourceView;
	}
}