package tw.com.eeit94.textile.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.system.common.ConstCookieParameter;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstLoginFilterKey;

/**
 * 登出的控制元件，目標是將MemberBean從Session Scope刪除，以及將與保持登入有關的Cookie清除。
 * 
 * @author 賴
 * @version 2017/06/13
 */
@Controller
@RequestMapping(path = { "/check/logout.do" })
public class LogoutController {

	@RequestMapping(method = { RequestMethod.GET })
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute(ConstLoginFilterKey.USER.key());

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			// 清除Cookie kl的資料，所有設定要和加入Cookie kl的設定一模一樣。
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(ConstLoginFilterKey.COOKIE_KL.key())) {
					cookie.setDomain(ConstCookieParameter.DOMAIN.param());
					cookie.setPath(ConstCookieParameter.PATH.param());
					cookie.setMaxAge(Integer.parseInt(ConstCookieParameter.VALUEOFRIGHTAWAY.param()));
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
				}
			}
		}

		return ConstMapping.LOGOUT_SUCCESS.path();
	}
}