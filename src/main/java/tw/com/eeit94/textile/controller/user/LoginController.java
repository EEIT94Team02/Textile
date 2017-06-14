package tw.com.eeit94.textile.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstCookieParameter;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstLoginFilterKey;

/**
 * 登入系統驗證的Controller：要檢查登入是否成功，只要檢查Map物件有無「login_error」的Key或有無增加一筆鍵值。
 * 登入成功會在Session Scope放入該使用者的MemberBean資料，Key為「user」。
 * 
 * path為/check/login.do，而不使用/user/login.do，否則Filter映射到造成無窮迴圈，因為登入的控制元件是不需要登入的。
 * 
 * @author 賴
 * @version 2017/06/13
 * @throws Exception
 * @see {@link MemberService}
 */
@Controller
@RequestMapping(path = { "/check/login.do" })
public class LoginController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;

	/**
	 * 登入系統驗證的過程：
	 * 
	 * 1. 如果登入失敗，則轉回登入畫面；如果登入成功，若原本就是點選登入畫面進來的，則轉至首頁，
	 * 若是原本點選其它頁面而因為沒登入被迫轉至登入畫面者，則轉至原本欲導向的頁面。
	 * 
	 * 2. 如果有勾選保持登入，則在Cookie內加入「kl」的資訊，其值就是加密過的帳號，並且修改資料庫會員
	 * 的保持登入欄位為「是」；如果沒有勾選，但Cookie內有「kl」的資訊，則在回應時立刻讓該Cookie消失，
	 * 並且修改資料庫會員的保持登入欄位為「否」。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 * @throws Exception
	 */
	@RequestMapping(method = { RequestMethod.POST })
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(ConstMemberKey.Email.key(), request.getParameter(ConstMemberKey.Email.key()));
		dataAndErrorsMap.put(ConstMemberKey.Password.key(), request.getParameter(ConstMemberKey.Password.key()));

		int mapSize = dataAndErrorsMap.size();
		dataAndErrorsMap = this.memberService.checkLogin(dataAndErrorsMap);
		if (mapSize < dataAndErrorsMap.size()) {
			request.setAttribute(ConstLoginKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
			return ConstMapping.LOGIN_ERROR.path();
		} else {
			// 在Session Scope放入該使用者的MemberBean資料。
			MemberBean mbean = this.memberService.selectByEmail(request.getParameter(ConstMemberKey.Email.key()));
			HttpSession session = request.getSession();
			session.setAttribute(ConstLoginFilterKey.USER.key(), mbean);

			this.checkKeepLogin(request, response, dataAndErrorsMap, mbean);

			String target = (String) session.getAttribute(ConstLoginFilterKey.TARGET.key());
			if (target == null) {
				// 轉向首頁
				return ConstMapping.LOGIN_SUCCESS.path();
			} else {
				// 轉向原本欲導向的頁面，Session Scope的target已無用需移除。
				session.removeAttribute(target);
				return target;
			}
		}
	}

	/**
	 * 檢驗是否有保持登入，並做與登入有關的Cookie和Session Scope的相關設定。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 * @throws Exception
	 */
	private void checkKeepLogin(HttpServletRequest request, HttpServletResponse response,
			Map<String, String> dataAndErrorsMap, MemberBean mbean) throws Exception {
		HttpSession session = request.getSession();

		// 依照是否有勾選保持登入來設定Cookie和修改資料庫中會員與保持登入相關的欄位。
		if ((ConstLoginParameter.KEEPLOGIN.param()).equals(request.getParameter(ConstLoginKey.KEEPLOGIN.key()))) {
			// 設定新的Cookie kl和其內容。
			Cookie cookie = new Cookie(ConstLoginFilterKey.COOKIE_KL.key(), this.secureService.getEncryptedText(
					dataAndErrorsMap.get(ConstMemberKey.Email.key()), ConstSecureParameter.KEEPLOGIN.param()));
			cookie.setDomain(ConstCookieParameter.DOMAIN.param());
			cookie.setPath(ConstCookieParameter.PATH.param());
			cookie.setMaxAge(Integer.parseInt(ConstCookieParameter.VALUEOFWHENONEWEEKLATER.param()));
			cookie.setHttpOnly(true);
			response.addCookie(cookie);

			// 將資料庫中的會員資料保持登入的欄位設為「是」，並重新加入Session Scope。
			mbean.setmKeepLogin(ConstLoginParameter.KEEPLOGIN_YES.param());
			this.memberService.update(mbean);
			session.setAttribute(ConstLoginFilterKey.USER.key(), mbean);
		} else {
			// 將資料庫中的會員資料保持登入的欄位設為「否」，並重新加入Session Scope。
			mbean.setmKeepLogin(ConstLoginParameter.KEEPLOGIN_NO.param());
			this.memberService.update(mbean);
			session.setAttribute(ConstLoginFilterKey.USER.key(), mbean);
		}
	}
}