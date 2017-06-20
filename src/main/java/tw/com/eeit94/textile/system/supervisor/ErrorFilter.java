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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import tw.com.eeit94.textile.model.logs.LogsService;

/**
 * 攔截例外的過濾器。
 * 
 * @author 賴
 * @version 2017/06/15
 */
@Component
@WebFilter(urlPatterns = { "/*" })
public class ErrorFilter implements Filter {
	/**
	 * 紀錄資訊的Service。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@Autowired
	private LogsService logsService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/*
		 * SpringBeanAutowiringSupport能夠幫你把在ServletContext的元件與Spring的 Root
		 * Container做連通。
		 */
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	/**
	 * 這個過濾器盡可能設在順序的第一位。
	 * 
	 * @author 賴
	 * @version 2017/06/15
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		StringBuffer sBuffer = new StringBuffer();

		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;

			// 設定所有的請求內容編碼為UTF-8。
			request.setCharacterEncoding(ConstFilterParameter.UTF_8.param());

			// 移除之前放在Session Scope的例外訊息，以免發生看到不對稱的例外訊息的Bug。
			HttpSession session = request.getSession();
			session.removeAttribute(ConstFilterKey.ExceptionFromServer.key());

			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				session.setAttribute(ConstFilterKey.ExceptionFromServer.key(), e.getMessage());

				/*
				 * 這裡不能呼叫forward()和sendRedirect()，否則會發生類似以下錯誤。
				 * 
				 * Cannot call sendRedirect() after the response has been
				 * committed.
				 */
				sBuffer.append("過濾器接收後端傳回的例外：").append(e.getMessage());
				this.logsService.insertNewLog(sBuffer.toString());

				/*
				 * debug用，部署後要註解掉。
				 */
				e.printStackTrace();
			}
		} else {
			sBuffer.append("過濾器接收未知的請求而發生錯誤。");
			this.logsService.insertNewLog(sBuffer.toString());
		}
	}

	@Override
	public void destroy() {
	}
}