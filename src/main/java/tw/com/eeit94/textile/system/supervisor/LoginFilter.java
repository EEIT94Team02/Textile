package tw.com.eeit94.textile.system.supervisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import tw.com.eeit94.textile.controller.user.ConstLoginParameter;
import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;

/**
 * 驗證是否登入的過濾器，並記錄每個使用者的請求與回應路徑，如果使用者關閉Cookie，則會因找不到存在Cookie的JSESSIONID而導向登入網頁。
 * 
 * 映射：/activity/*、/album/*、/log/*、/photo/*、/report/*、/theme/*、/user/*。
 * 
 * 其中，/log/*需要管理員帳號才能瀏覽。
 * 
 * @author 賴
 * @version 2017/06/13
 */
@Component
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "u_activity", value = "/activity/*"),
		@WebInitParam(name = "u_album", value = "/album/*"), @WebInitParam(name = "u_photo", value = "/photo/*"),
		@WebInitParam(name = "m_manager", value = "/manager/*"), @WebInitParam(name = "u_report", value = "/report/*"),
		@WebInitParam(name = "u_theme", value = "/theme/*"),
		@WebInitParam(name = "u_user", value = "/user/*") }, asyncSupported = true)
public class LoginFilter implements Filter {
	/**
	 * 存放多個映射的網址。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	private List<String> urlList = new ArrayList<>();

	/**
	 * 存放多個映射只有管理員能夠查看的網址。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private List<String> managedUrlList = new ArrayList<>();

	/**
	 * 存放多個管理員帳號的清單。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	private List<String> managerList = new ArrayList<>();

	/**
	 * 紀錄資訊的Service。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@Autowired
	private LogsService logsService;

	/**
	 * 控制金鑰的Service，這裡用來解密從客戶端來的Cookie kl的值。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	@Autowired
	private SecureService secureService;

	/**
	 * 控制會員基本資料的Service元件。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	@Autowired
	private MemberService memberService;
	private final String LOGIN_PAGE = "/check/login.jsp";
	private final String ERROR_PAGE = "/error/404.jsp";

	/**
	 * 過濾器啟動時，將要映射的網址、管理員才能查看的網址和所有管理員分別加入過濾器的各個屬性成員變數，同時注入LogsService。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Enumeration<String> e = filterConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String urlName = e.nextElement();
			this.urlList.add(filterConfig.getInitParameter(urlName));

			if (urlName.startsWith("m_")) {
				this.managedUrlList.add(filterConfig.getInitParameter(urlName));
			}
		}

		ConstManagerList[] c = ConstManagerList.values();
		for (int i = 0; i < c.length; i++) {
			this.managerList.add(c[i].key());
		}

		/*
		 * SpringBeanAutowiringSupport能夠幫你把在ServletContext的元件與Spring的 Root
		 * Container做連通。
		 */
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	/**
	 * 檢驗是否登入，方法為查看Session Scope有無「user」的Key。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		StringBuffer sBuffer = new StringBuffer().append("過濾器接收");

		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			String contextPath = request.getContextPath();
			String servletPath = request.getServletPath();

			// 設定所有的請求內容編碼為UTF-8。
			request.setCharacterEncoding(ConstLoginFilterParameter.UTF_8.param());

			// 讀取使用者的姓名，得知使用者的身份。
			HttpSession session = request.getSession();
			if (session.getAttribute(ConstLoginFilterKey.USER.key()) != null) {
				sBuffer.append("「")
						.append(((MemberBean) session.getAttribute(ConstLoginFilterKey.USER.key())).getmName())
						.append("」的請求，");
			} else {
				sBuffer.append("「訪客」的請求，");
			}

			sBuffer.append("路徑為：「").append(servletPath).append("」").append("，<br />是否需要登入：");
			if (this.needToLogin(servletPath)) {
				sBuffer.append("「是」，是否已登入：");

				// 讀取客戶端的Cookie並檢驗是否有必要登入。
				this.checkCookieToKeepLogin(request);

				// 判斷是否有無登入，是否為管理員等。
				if (this.isLogin(request)) {
					sBuffer.append("「是」，是否需要管理員才能查看：");
					if (this.needToManage(servletPath)) {
						sBuffer.append("「是」，是否為管理員：");
						if (this.isManager(request)) {
							sBuffer.append("「是」，狀態為：轉向目標路徑。");
							this.logsService.insertNewLog(sBuffer.toString());
							chain.doFilter(request, response);
						} else {
							sBuffer.append("「否」，狀態為：轉向錯誤網頁。");
							this.logsService.insertNewLog(sBuffer.toString());
							response.sendRedirect(contextPath + this.ERROR_PAGE);
						}
					} else {
						sBuffer.append("「否」，狀態為：轉向目標路徑。");
						this.logsService.insertNewLog(sBuffer.toString());
						chain.doFilter(request, response);
					}
				} else {
					session.setAttribute(ConstLoginFilterKey.TARGET.key(), servletPath);
					sBuffer.append("「否」，狀態為：轉向登入網頁。");
					this.logsService.insertNewLog(sBuffer.toString());
					response.sendRedirect(contextPath + this.LOGIN_PAGE);
				}
			} else {
				sBuffer.append("「否」，狀態為：轉向目標路徑。");
				this.logsService.insertNewLog(sBuffer.toString());
				chain.doFilter(request, response);
			}
		} else {
			sBuffer.append("未知的請求而發生錯誤。");
			this.logsService.insertNewLog(sBuffer.toString());
		}
	}

	@Override
	public void destroy() {
	}

	/**
	 * 判斷目前的請求路徑是否需要登入。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	private boolean needToLogin(String servletPath) {
		boolean needToLogin = false;
		for (String url : this.urlList) {
			if (url.endsWith("*")) {
				url = url.substring(0, url.length() - 1);
				if (servletPath.startsWith(url)) {
					needToLogin = true;
					break;
				}
			} else {
				if (servletPath.equals(url)) {
					needToLogin = true;
					break;
				}
			}
		}
		return needToLogin;
	}

	/**
	 * 判斷目前的請求路徑是否為管理員才能查看的網址。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private boolean needToManage(String servletPath) {
		boolean needToManage = false;
		for (String url : this.managedUrlList) {
			if (url.endsWith("*")) {
				url = url.substring(0, url.length() - 1);
				if (servletPath.startsWith(url)) {
					needToManage = true;
					break;
				}
			} else {
				if (servletPath.equals(url)) {
					needToManage = true;
					break;
				}
			}
		}
		return needToManage;
	}

	/**
	 * 檢驗是否登入，方法為查看Session Scope有無「user」的Key。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	private boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstLoginFilterKey.USER.key());
		if (mbean == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 檢驗是否為超級管理員，方法為驗證Session Scope找到的Memberbean內的帳號是否為超級管理員清單之一。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private boolean isManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstLoginFilterKey.USER.key());
		for (int i = 0; i < this.managerList.size(); i++) {
			if (mbean.getmEmail().equals(this.managerList.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 檢驗從客戶端來的Cookie kl的值，其實就是加密過的帳號，解密這個值並用它來搜尋該會員，
	 * 如果會員存在，則將MemberBean直接加入Session Scope即可略過登入。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private void checkCookieToKeepLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(ConstLoginFilterKey.COOKIE_KL.key())) {
					try {
						String mEmail = this.secureService.getDecryptedText(cookie.getValue(),
								ConstSecureParameter.KEEPLOGIN.param());
						MemberBean mbean = this.memberService.selectByEmail(mEmail);
						if (mbean.getmKeepLogin().equals(ConstLoginParameter.KEEPLOGIN_YES.param())) {
							session.setAttribute(ConstLoginFilterKey.USER.key(), mbean);
						}
					} catch (Exception e) {
						this.logsService.insertNewLog("該會員帳號不存在或讀取並解密客戶端Cookie kl的值失敗而拋出例外。<br />" + e.getMessage());
					}
				}
			}
		}
	}
}