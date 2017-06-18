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

import tw.com.eeit94.textile.controller.user.ConstUserParameter;
import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstMapping;

/**
 * 驗證是否登入的過濾器，並記錄每個使用者的請求與回應路徑，如果使用者關閉Cookie，則會因找不到存在Cookie的JSESSIONID而導向登入網頁。
 * 
 * 映射：/activity/*、/album/*、/manager/*、/photo/*、/report/*、/social/*、/store/*、/theme/*、/user/*；
 * 
 * 其中，/album/*內的資料夾還要驗證是否與會員主鍵相符才能看到；
 * 
 * 其中，/log/*等後臺管理頁面需要管理員帳號才能瀏覽。
 * 
 * 這個過濾器藉由檢驗Cookie和資料庫會員資料的保持登入欄位來決定是否要「自動登入」！
 * 
 * @author 賴
 * @version 2017/06/14
 */
@Component
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "activity", value = "/activity/*"),
		@WebInitParam(name = "u_album", value = "/album/*"), @WebInitParam(name = "m_manager", value = "/manager/*"),
		@WebInitParam(name = "photo", value = "/photo/*"), @WebInitParam(name = "report", value = "/report/*"),
		@WebInitParam(name = "socail", value = "/socail/*"), @WebInitParam(name = "store", value = "/store/*"),
		@WebInitParam(name = "theme", value = "/theme/*"), @WebInitParam(name = "user", value = "/user/*") })
public class LoginFilter implements Filter {
	/**
	 * 存放多個映射的網址。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	private List<String> loginUrlList = new ArrayList<>();

	/**
	 * 存放多個映射只有會員主鍵相符才能夠查看的網址，其參數名稱為「u_」開頭。
	 * 
	 * @author 賴
	 * @version 2017/06/15
	 */
	private List<String> mIdComparedUrlList = new ArrayList<>();

	/**
	 * 存放多個映射只有管理員能夠查看的網址，其參數名稱為「m_」開頭。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private List<String> managedUrlList = new ArrayList<>();

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
			this.loginUrlList.add(filterConfig.getInitParameter(urlName));

			if (urlName.startsWith("u_")) {
				this.mIdComparedUrlList.add(filterConfig.getInitParameter(urlName));
			}
			if (urlName.startsWith("m_")) {
				this.managedUrlList.add(filterConfig.getInitParameter(urlName));
			}
		}

		/*
		 * SpringBeanAutowiringSupport能夠幫你把在ServletContext的元件與Spring的 Root
		 * Container做連通。
		 */
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	/**
	 * 檢驗是否登入、是否為管理員、是否在請求需會員主鍵相符的路徑時能合格。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			String contextPath = request.getContextPath();
			String servletPath = request.getServletPath();

			StringBuffer sBuffer = new StringBuffer().append("過濾器接收");

			/*
			 * 讀取使用者的姓名，得知使用者的身份，會先嘗試從Session或Cookie
			 * kl值(且之前必須勾選保持登入)找到MemberBean。
			 */
			HttpSession session = request.getSession();
			if (this.checkSessionToKeepLogin(request) || this.checkCookieToKeepLogin(request)) {
				sBuffer.append("「").append(((MemberBean) session.getAttribute(ConstFilterKey.USER.key())).getmName())
						.append("」的請求，");
			} else {
				sBuffer.append("「訪客」的請求，");
			}

			sBuffer.append("路徑為：「").append(servletPath).append("」").append("，是否需要登入：");
			if (this.checkNeedTo("Login", servletPath)) {
				sBuffer.append("「是」，是否已登入：");

				// 判斷是否有無登入，是否為管理員等。
				if (this.isLogin(request)) {
					sBuffer.append("「是」，<br />是否需要會員主鍵相符才能查看：");

					if (this.checkNeedTo("CompareMId", servletPath)) {
						sBuffer.append("「是」，是否主鍵相符：");

						// 若servletPath為「/album/30/20170615.jpg」，則去掉第一個斜線。
						String tempPath = servletPath.substring(1, servletPath.length());
						// 此時tempPath為「album/30/20170615.jpg」，則取中間兩個斜線內側的序數。
						int frontSlashIndex = tempPath.indexOf('/') + 1;
						int behindSlashIndex = tempPath.lastIndexOf('/');
						String tempMId = tempPath.substring(frontSlashIndex, behindSlashIndex);
						request.setAttribute("tempMId", tempMId);

						// 用mId比較會員的mId是否相同
						if (this.isTheSameAsPrimaryKey(request)) {
							sBuffer.append("「是」，狀態為：轉向目標路徑。");
							this.logsService.insertNewLog(sBuffer.toString());
							chain.doFilter(request, response);
						} else {
							sBuffer.append("「否」，狀態為：轉向錯誤網頁。");
							this.logsService.insertNewLog(sBuffer.toString());
							response.sendRedirect(contextPath + ConstMapping.ERROR_PAGE.path());
						}
					} else {
						sBuffer.append("「否」，是否需要管理員才能查看：");

						if (this.checkNeedTo("Manage", servletPath)) {
							sBuffer.append("「是」，是否為管理員：");
							if (this.isManager(request)) {
								sBuffer.append("「是」，狀態為：轉向目標路徑。");
								this.logsService.insertNewLog(sBuffer.toString());
								chain.doFilter(request, response);
							} else {
								sBuffer.append("「否」，狀態為：轉向錯誤網頁。");
								this.logsService.insertNewLog(sBuffer.toString());
								response.sendRedirect(contextPath + ConstMapping.ERROR_PAGE.path());
							}
						} else {
							sBuffer.append("「否」，狀態為：轉向目標路徑。");
							this.logsService.insertNewLog(sBuffer.toString());
							chain.doFilter(request, response);
						}
					}
				} else {
					session.setAttribute(ConstFilterKey.TARGET.key(), servletPath);
					sBuffer.append("「否」，狀態為：轉向登入網頁。");
					this.logsService.insertNewLog(sBuffer.toString());
					response.sendRedirect(contextPath + ConstMapping.LOGIN_PAGE.path());
				}
			} else {
				sBuffer.append("「否」，狀態為：轉向目標路徑。");
				this.logsService.insertNewLog(sBuffer.toString());
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}

	/**
	 * 利用servletPath來判斷以下三件事情：
	 * 
	 * 判斷目前的請求路徑是否需要登入。
	 * 
	 * 判斷目前的請求路徑是否為必須相符會員主鍵的。
	 * 
	 * 判斷目前的請求路徑是否為管理員才能查看的網址。
	 * 
	 * @author 賴
	 * @version 2017/06/15
	 */
	private boolean checkNeedTo(String doSomething, String servletPath) {
		List<String> list = new ArrayList<>();
		switch (doSomething) {
		case "Login":
			list = this.loginUrlList;
			break;
		case "CompareMId":
			list = this.mIdComparedUrlList;
			break;
		case "Manage":
			list = this.managedUrlList;
			break;
		}

		boolean isTheNeedNecessary = false;
		for (String url : list) {
			if (url.endsWith("*")) {
				url = url.substring(0, url.length() - 1);
				if (servletPath.startsWith(url)) {
					isTheNeedNecessary = true;
					break;
				}
			} else {
				if (servletPath.equals(url)) {
					isTheNeedNecessary = true;
					break;
				}
			}
		}
		return isTheNeedNecessary;
	}

	/**
	 * 檢驗是否登入，方法為查看Session Scope有無「user」的Key。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	private boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		if (mbean != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 檢驗資料夾內的數字是否與會員的主鍵相同。
	 * 
	 * @author 賴
	 * @version 2017/06/15
	 */
	private boolean isTheSameAsPrimaryKey(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		String tempMId = (String) request.getAttribute("tempMId");
		int mId = Integer.parseInt(tempMId);

		if (mId == mbean.getmId()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 檢驗是否為管理員，方法為驗證Session Scope找到的Memberbean內的帳號是否為管理員清單之一。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private boolean isManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		if (mbean.getmValidManager().equals(ConstFilterParameter.IS_MANAGER.param())) {
			return true;
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
	private boolean checkSessionToKeepLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ConstFilterKey.USER.key()) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 檢驗從客戶端來的Cookie kl的值，其實就是加密過的帳號，解密這個值並用它來搜尋該會員，
	 * 如果會員存在，則將MemberBean直接加入Session Scope即可略過登入。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 */
	private boolean checkCookieToKeepLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(ConstFilterKey.COOKIE_KL.key())) {
					try {
						String mEmail = this.secureService.getDecryptedText(cookie.getValue(),
								ConstSecureParameter.KEEPLOGIN.param());
						MemberBean mbean = this.memberService.selectByEmail(mEmail);
						if (mbean.getmKeepLogin().equals(ConstUserParameter.KEEPLOGIN_YES.param())) {
							session.setAttribute(ConstFilterKey.USER.key(), mbean);
							return true;
						}
					} catch (Exception e) {
						this.logsService.insertNewLog("該會員帳號不存在或讀取並解密客戶端Cookie kl的值失敗而拋出例外：<br />" + e.getMessage());
					}
				}
			}
		}
		return false;
	}
}