package tw.com.eeit94.textile.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;

/**
 * 登入系統驗證的Controller：要檢查登入是否成功，只要檢查Map物件有無「login_error」的Key或有無增加一筆鍵值。
 * 登入成功會在Session Scope放入該使用者的MemberBean資料，Key為「user」。
 * 
 * @author 賴
 * @version 2017/06/12
 * @throws Exception
 * @see {@link MemberService}
 */
@Controller
@RequestMapping(path = { "/user/login.do" })
public class LoginController {
	@Autowired
	private MemberService memberService;

	/**
	 * 登入系統驗證的過程：如果登入失敗，則轉回登入畫面；如果登入成功，若原本就是點選登入畫面進來的，則轉至首頁，
	 * 若是原本點選其它頁面而因為沒登入被迫轉至登入畫面者，則轉至原本欲導向的頁面。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 * @throws Exception
	 */
	@RequestMapping(method = { RequestMethod.POST })
	public String process(HttpServletRequest request) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(ConstMemberKey.Email.key(), request.getParameter(ConstMemberKey.Email.key()));
		dataAndErrorsMap.put(ConstMemberKey.Password.key(), request.getParameter(ConstMemberKey.Password.key()));

		int mapSize = dataAndErrorsMap.size();
		dataAndErrorsMap = memberService.checkLogin(dataAndErrorsMap);
		if (mapSize < dataAndErrorsMap.size()) {
			return "login.error";
		} else {
			// 在Session Scope放入該使用者的MemberBean資料。
			HttpSession session = request.getSession();
			session.setAttribute("user", memberService.selectByEmail(request.getParameter(ConstMemberKey.Email.key())));
			String target = (String) session.getAttribute("target");

			if (target == null) {
				// 轉向首頁
				return "login.success";
			} else {
				// 轉向原本欲導向的頁面，Session Scope的target已無用需移除。
				session.removeAttribute(target);
				return target;
			}
		}
	}
}