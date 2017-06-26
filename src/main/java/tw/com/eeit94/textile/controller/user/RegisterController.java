package tw.com.eeit94.textile.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.MailRegisterService;
import tw.com.eeit94.textile.model.member.service.MemberRollbackProviderService;
import tw.com.eeit94.textile.model.member.service.UserCentralService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 驗證註冊表單的資料，並回傳註冊成功與否。
 * 
 * @author 賴
 * @version 2017/06/19
 * @see {@link MemberService}
 */
@Controller
@RequestMapping(path = { "/check" })
public class RegisterController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UserCentralService userCentralService;
	@Autowired
	private MemberRollbackProviderService memberRollbackService;
	@Autowired
	private MailRegisterService mailRegisterService;

	/**
	 * 註冊的過程：註冊完新帳號不會自動登入，所以如果用原帳號登入，Session Scope的資料仍在。
	 * 
	 * 1. 檢查表單資料是否有誤，利用回傳的Map<String, String>物件，檢查是否有任何的Key其結尾附帶「_error」。
	 * 
	 * 2. 驗證成功時利用rollbackProvider()初始化相關會員資料於資料庫，失敗時一次rollback。
	 * 
	 * 3. 寄送驗證信箱的郵件，沒有驗證信箱無法使用登入功能。即使發信失敗，仍要顯示註冊成功，因為會員資料已新增完成。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	@RequestMapping(path = { "/register.do" }, method = { RequestMethod.POST })
	public String process(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		dataAndErrorsMap = this.memberService.encapsulateAndCheckAllDataWhenRegistering(dataAndErrorsMap, request);
		dataAndErrorsMap = this.memberService.checkNonexistentEmail(dataAndErrorsMap, request);
		dataAndErrorsMap = this.memberService.checkTheSamePassword(dataAndErrorsMap, request);

		// 檢查是否含「_error」結尾的Key。
		if (this.userCentralService.getSubmitCheckFailure(dataAndErrorsMap)) {
			return ConstMapping.REGISTER_ERROR.path();
		}

		// 初始化所有相關新會員資料於資料庫。
		this.memberRollbackService.registerWithRollbackProvider(dataAndErrorsMap, request);
		MemberBean mbean = (MemberBean) request.getAttribute(ConstFilterKey.USER.key());
		ChatroomBean cbean = (ChatroomBean) request.getAttribute(ConstFilterKey.CHATROOM.key());
		if (mbean == null || cbean == null) {
			return ConstMapping.REGISTER_ERROR.path();
		}

		this.sendEmailCheckMail(request, response);
		return ConstMapping.REGISTER_SUCCESS.path();
	}

	/**
	 * 處理寄送驗證信箱郵件的方法，先製作驗證網址的前輟，再由MailRegisterService加工後面的參數。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 * @see {@link MailRegisterService}
	 */
	public void sendEmailCheckMail(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, String> dataAndErrorsMap = (Map<String, String>) request
				.getAttribute(ConstUserKey.DATAANDERRORSMAP.key());

		// 寄送驗證信箱的郵件，先得到網址如「http://localhost:8080/Textile」/check/register.do。
		String checkUrl = request.getRequestURL().toString();
		checkUrl = checkUrl.substring(0, checkUrl.lastIndexOf('/'));
		checkUrl = checkUrl.substring(0, checkUrl.lastIndexOf('/'));
		checkUrl = checkUrl + ConstMapping.LOGIN_EMAILCHECK.path();
		dataAndErrorsMap.put(ConstUserKey.CHECKURL.key(), checkUrl);
		try {
			this.mailRegisterService.doSendEmail(dataAndErrorsMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 重新寄送驗證信箱的郵件，接收進來參數為加密過的Email，必須先被解密。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 * @throws Exception
	 */
	@RequestMapping(path = { "/re_sendEmailCheck.do" }, method = { RequestMethod.GET })
	public String re_sendEmailCheckMail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mEmail = this.secureService.getDecryptedText(request.getParameter(ConstHelperKey.QUERY.key()),
				ConstSecureParameter.EMAIL.param());
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(ConstMemberKey.Email.key(), mEmail);
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		this.sendEmailCheckMail(request, response);
		return ConstMapping.LOGIN_EMAILCHECKRE_SEND.path();
	}
}