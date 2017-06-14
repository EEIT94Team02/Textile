package tw.com.eeit94.textile.system.supervisor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.spring.SpringMVCJavaConfiguration;

/**
 * 輔助Spring MVC架構中的攔截器，記錄責任鏈的關鍵點，並再發生任何錯誤時導向錯誤網頁。
 * 
 * @author 賴
 * @version 2017/06/15
 * @see {@link SpringMVCJavaConfiguration}
 */
@Component
public class PathInterceptor implements HandlerInterceptor {
	/**
	 * 紀錄資訊的Service。
	 * 
	 * @author 賴
	 * @version 2017/06/15
	 */
	@Autowired
	private LogsService logsService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("攔截器接收「").append(request.getRequestURI()).append("」的請求，");
		String controller = "Controller";
		String temp = handler.toString();
		if (temp.indexOf('@') != -1) {
			// 去除"@"後方的所有字元。
			controller = temp.substring(0, temp.indexOf('@'));
		} else {
			// 去除開頭的"public java.lang.String "及"Controller"後方的所有字元。
			controller = temp.substring(temp.lastIndexOf("tw"), temp.lastIndexOf(controller) + 10);
		}
		sBuffer.append("並即將被「").append(controller).append("」處理。");
		this.logsService.insertNewLog(sBuffer.toString());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			HttpSession session = request.getSession();
			session.setAttribute(ConstFilterKey.ExceptionFromControllerOrView.key(), ex.getMessage());
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + ConstMapping.ERROR_PAGE.path());

			/*
			 * debug用，上線要刪除。
			 */
			ex.printStackTrace();
		}
	}
}