package tw.com.eeit94.textile.system.supervisor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.spring.SpringMVCJavaConfiguration;

/**
 * 驗證網址的過濾器，回應請求路徑的方式如下：
 * 
 * 請求路徑若為「/」，則添加為「/index.v」，直接導向由Spring處理，並轉送RedirectController處理。
 * 
 * 請求路徑若為「*.jsp」，直接導向錯誤網頁。
 * 
 * 請求路徑若為「*.do」，直接導向由Spring處理，並轉送對應的Controller處理。
 * 
 * 請求路徑若為「*.v」，直接導向由Spring處理，並轉送RedirectController處理。
 * 
 * 請求路徑若為「*.r」，直接導向成「*.v」的網頁。(附加redirect的功能！)
 * 
 * 請求路徑若為「*.css」、「*.js」、「*.jpg」等靜態資源，則直接導向由Spring處理，並轉送ResourceHandler處理。
 * 
 * @author 賴
 * @version 2017/06/14
 * @see {@link SpringMVCJavaConfiguration}
 */
@WebFilter(urlPatterns = { "/*" })
public class PathFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 檢驗請求路徑並轉換、轉送請求路徑。
	 * 
	 * @author 賴
	 * @version 2017/06/14
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			String contextPath = request.getContextPath();
			String servletPath = request.getServletPath();

			// 檢驗請求路徑最後是否為*.*的結尾，例如*.jsp、*.do、*.v等。
			String prefixPath = null;
			String suffixPath = null;
			if (servletPath.lastIndexOf('.') != -1) {
				// 如果路徑為「/user/modify.v」，則suffixPath為「.」。
				suffixPath = servletPath.substring(servletPath.lastIndexOf('.'), servletPath.length());
			} else if (servletPath.lastIndexOf('/') + 1 == servletPath.length()) {
				// 如果路徑為「/user/」，則suffixPath為「.jsp」，servletPath改為「/user/index.jsp」。
				suffixPath = ".jsp";
				servletPath += ConstMapping.FALSE_MAIN_PAGE.path().substring(1);
			} else {
				// 如果都不是，直接到404.v慢走不送。
				response.sendRedirect(contextPath + ConstMapping.ERROR_PAGE.path());
			}

			switch (suffixPath) {
			case ".jsp":
				/*
				 * ConstMapping.FALSE_MAIN_PAGE.path()就是/index.jsp，這個設定是必要的，
				 * 因應Tomcat的Welcome File設定，會對每個目錄(待確認?)的根路徑請求補上「index.jsp」。
				 */
				if (servletPath.endsWith(ConstMapping.FALSE_MAIN_PAGE.path())) {
					prefixPath = servletPath.substring(0, servletPath.lastIndexOf('/'));
					response.sendRedirect(contextPath + prefixPath + ConstMapping.TRUE_MAIN_PAGE.path());
				} else {
					// 非「/index.jsp」結尾者，直接到404.v慢走不送。
					response.sendRedirect(contextPath + ConstMapping.ERROR_PAGE.path());
				}
				break;
			case ".do":
				// 到Controller。
				chain.doFilter(request, response);
				break;
			case ".v":
				// 到Spring的ViewController或自訂的Controller。
				chain.doFilter(request, response);
				break;
			case ".r":
				// 將其redirect到目標路徑，但「.r」改為「.v」，也就是附加重新導向的功能。
				prefixPath = servletPath.substring(0, servletPath.lastIndexOf('.'));
				response.sendRedirect(contextPath + prefixPath + ".v");
				break;
			// ！！！！！！所有靜態資源如下，如果有增加要補上，並且別忘了在Spring MVC設定檔補上靜態資源的路徑：！！！！！！
			case ".css":
				chain.doFilter(request, response);
				break;
			case ".js":
				chain.doFilter(request, response);
				break;
			// 圖片專用
			case ".jpg":
				chain.doFilter(request, response);
				break;
			case ".jpeg":
				chain.doFilter(request, response);
				break;
			case ".png":
				chain.doFilter(request, response);
				break;
			case ".gif":
				chain.doFilter(request, response);
				break;
			// Bootstrap專用
			case ".ttf":
				chain.doFilter(request, response);
				break;
			case ".woff":
				chain.doFilter(request, response);
				break;
			case ".woff2":
				chain.doFilter(request, response);
				break;
			case ".map":
				chain.doFilter(request, response);
				break;
			default:
				response.sendRedirect(contextPath + ConstMapping.ERROR_PAGE.path());
			}
		}
	}

	@Override
	public void destroy() {
	}
}