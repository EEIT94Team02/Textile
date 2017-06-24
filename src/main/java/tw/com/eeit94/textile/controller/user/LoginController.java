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
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 登入系統驗證的Controller，此控制元件附加接收驗證信箱的功能：
 * 
 * 要檢查登入是否成功，只要檢查Map物件有無「login_error」的Key或有無增加一筆鍵值。 登入成功會在Session
 * Scope放入該使用者的MemberBean資料，Key為「user」。
 * 
 * path為/check/login.do，而不使用/user/login.do，否則Filter映射到造成無窮迴圈，因為登入的控制元件是不需要登入的。
 * 
 * @author 賴
 * @version 2017/06/19
 * @throws Exception
 * @see {@link MemberService}
 */
@Controller
@RequestMapping(path = { "/check" })
public class LoginController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;

	/**
	 * 登入系統驗證的過程：
	 * 
	 * 1. 如果使用者沒有驗證信箱，直接轉往提醒驗證信箱的網頁。
	 * 
	 * 2. 如果登入失敗，則轉回登入畫面；如果登入成功，若原本就是點選登入畫面進來的，則轉至首頁，
	 * 若是原本點選其它頁面而因為沒登入被迫轉至登入畫面者，則轉至原本欲導向的頁面。
	 * 
	 * 3. 如果有勾選保持登入，則在Cookie內加入「kl」的資訊，其值就是加密過的帳號，並且修改資料庫會員
	 * 的保持登入欄位為「是」；如果沒有勾選，但Cookie內有「kl」的資訊，則在回應時立刻讓該Cookie消失，
	 * 並且修改資料庫會員的保持登入欄位為「否」。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 * @throws Exception
	 */
	@RequestMapping(path = { "/login.do" }, method = { RequestMethod.POST })
	public String loginProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 檢驗帳號密碼是否為同一組。
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(ConstMemberKey.Email.key(), request.getParameter(ConstMemberKey.Email.key()));
		dataAndErrorsMap.put(ConstMemberKey.Password.key(), request.getParameter(ConstMemberKey.Password.key()));
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);

		int mapSize = dataAndErrorsMap.size();
		dataAndErrorsMap = this.memberService.checkLogin(dataAndErrorsMap);
		if (mapSize < dataAndErrorsMap.size()) {
			return ConstMapping.LOGIN_ERROR.path();
		}

		// 檢驗該帳號是否有驗證信箱。
		MemberBean mbean = this.memberService.selectByEmail(request.getParameter(ConstMemberKey.Email.key()));
		if (mbean != null && mbean.getmValidEmail().equals(ConstUserParameter.VALIDEMAIL_NO.param())) {
			String encryptedMEmail = this.secureService.getEncryptedText(mbean.getmEmail(),
					ConstSecureParameter.EMAIL.param());
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(ConstMapping.LOGIN_RE_SENDEMAILCHECK.path()).append(ConstHelperKey.QUESTION.key())
					.append(ConstHelperKey.QUERY_EQUAL.key()).append(encryptedMEmail);
			String emailCheckRe_sendUrl = sBuffer.toString();
			request.setAttribute(ConstUserKey.EMAILCHECKRE_SENDURL.key(), emailCheckRe_sendUrl);
			return ConstMapping.LOGIN_INVALIDEMAIL.path();
		}

		// 在Session Scope放入該使用者的MemberBean資料。
		HttpSession session = request.getSession();
		session.setAttribute(ConstFilterKey.USER.key(), mbean);

		// 實作勾選保持登入與否對應的過程。
		this.checkKeepLogin(request, response, dataAndErrorsMap);

		String target = (String) session.getAttribute(ConstFilterKey.TARGET.key());
		if (target == null) {
			// 轉向首頁
			return ConstMapping.LOGIN_SUCCESS.path();
		} else {
			// 轉向原本欲導向的頁面，Session Scope的target已無用需移除。
			session.removeAttribute(target);
			// 因應本專案的設計，這裡不能直接回傳路徑。
			response.sendRedirect(request.getContextPath() + target);
			return null;
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
			Map<String, String> dataAndErrorsMap) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());

		// 依照是否有勾選保持登入來設定Cookie和修改資料庫中會員與保持登入相關的欄位。
		if ((ConstUserParameter.KEEPLOGIN.param()).equals(request.getParameter(ConstUserKey.KEEPLOGIN.key()))) {
			// 設定新的Cookie kl和其內容。
			Cookie cookie = new Cookie(ConstFilterKey.COOKIE_KL.key(), this.secureService.getEncryptedText(
					dataAndErrorsMap.get(ConstMemberKey.Email.key()), ConstSecureParameter.KEEPLOGIN.param()));
			cookie.setDomain(ConstCookieParameter.DOMAIN.param());
			cookie.setPath(ConstCookieParameter.PATH.param());
			cookie.setMaxAge(Integer.parseInt(ConstCookieParameter.VALUEOFWHENONEWEEKLATER.param()));
			cookie.setHttpOnly(true);
			response.addCookie(cookie);

			// 將資料庫中的會員資料保持登入的欄位設為「是」，並重新加入Session Scope。
			mbean.setmKeepLogin(ConstUserParameter.KEEPLOGIN_YES.param());
			this.memberService.update(mbean);
			session.setAttribute(ConstFilterKey.USER.key(), mbean);
		} else {
			// 將資料庫中的會員資料保持登入的欄位設為「否」，並重新加入Session Scope。
			mbean.setmKeepLogin(ConstUserParameter.KEEPLOGIN_NO.param());
			this.memberService.update(mbean);
			session.setAttribute(ConstFilterKey.USER.key(), mbean);
		}
	}

	/**
	 * 驗證信箱，將會員資料欄的mValidEmail改為「Y」，如果已經是「Y」，則回傳錯誤網頁。
	 * 
	 * 注意：外來加密的字串可能含有「 」，必須轉為原先的「+」。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 * @throws Exception
	 */
	@RequestMapping(path = { "/emailCheck.do" }, method = { RequestMethod.GET })
	public String checkValidEmailProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mEmail = request.getParameter(ConstHelperKey.QUERY.key()).replace(' ', '+');
		mEmail = this.secureService.getDecryptedText(mEmail, ConstSecureParameter.EMAIL.param());
		MemberBean mbean = this.memberService.selectByEmail(mEmail);
		if (mbean.getmValidEmail().equals(ConstUserParameter.VALIDEMAIL_YES.param())) {
			return ConstMapping.ERROR_PAGE.path();
		}
		mbean.setmValidEmail(ConstUserParameter.VALIDEMAIL_YES.param());
		this.memberService.update(mbean);
		return ConstMapping.LOGIN_EMAILCHECKSUCCESS.path();
	}
}