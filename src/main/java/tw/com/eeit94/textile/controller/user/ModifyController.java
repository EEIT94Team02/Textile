package tw.com.eeit94.textile.controller.user;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.UserCentralService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 修改使用者資料的Controller，分為修改密碼、驗證手機、修改基本資料、修改個人狀況、修改興趣喜好。
 * 
 * @author 賴
 * @version 2017/06/20
 * @see {@link MemberService}
 * @see {@link Interest_DetailService}
 */
@Controller
@RequestMapping(path = { "/user" })
public class ModifyController {
	@Autowired
	private LogsService logsService;
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private Interest_DetailService interest_DetailService;
	@Autowired
	private UserCentralService userCentralService;
	private static final int PHONECHECK_CODE_LENGTH = 8;

	/**
	 * 讀取個人使用者的密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modifySecure.v" }, method = { RequestMethod.GET })
	public String secureViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		return ConstMapping.MODIFY_SECURE_SHOW.path();
	}

	/**
	 * 讀取個人使用者的密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modifyProfile.v" }, method = { RequestMethod.GET })
	public String profileViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		return ConstMapping.MODIFY_PROFILE_SHOW.path();
	}

	/**
	 * 讀取個人使用者的密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modifySituation.v" }, method = { RequestMethod.GET })
	public String situationViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		return ConstMapping.MODIFY_SITUATION_SHOW.path();
	}

	/**
	 * 讀取個人使用者的密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modifyInterest.v" }, method = { RequestMethod.GET })
	public String interestViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		return ConstMapping.MODIFY_INTEREST_SHOW.path();
	}

	/**
	 * 發送手機驗證碼，並傳回輸入驗證碼的畫面。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	@RequestMapping(path = { "/phoneCheck.v" })
	public String phoneCheckViewProcess(HttpServletRequest request, HttpServletResponse response) {
		String randomNumbers = this.secureService.getRandomNumber(PHONECHECK_CODE_LENGTH);
		HttpSession session = request.getSession();
		session.setAttribute(ConstUserKey.PHONECHECKCODE.key(), randomNumbers);

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("系統自動產生的手機驗證碼為：").append(randomNumbers);
		this.logsService.insertNewLog(sBuffer.toString());
		return ConstMapping.MODIFY_PHONECHECK_SHOW.path();
	}

	/**
	 * 驗證使用者單一的資料並回傳訊息。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws IOException
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.GET }, produces = { "text/plain; charset=UTF-8" })
	@ResponseBody
	public void checkProcess(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws IOException {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap = this.memberService.encapsulateAndCheckOneData(dataAndErrorsMap, request);
		String output = this.userCentralService.getAJAXCheckResult(dataAndErrorsMap);
		out.write(output.getBytes());
		out.close();
	}

	/**
	 * 驗證使用者的舊密碼並回傳訊息。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.GET }, params = { "m=mOldPassword" }, produces = {
			"text/plain; charset=UTF-8" })
	@ResponseBody
	public void checkOldPasswordProcess(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(request.getParameter(ConstHelperKey.METHOD.key()),
				request.getParameter(ConstHelperKey.QUERY.key()));
		dataAndErrorsMap = this.memberService.checkOldPassword(dataAndErrorsMap, request);
		String output = this.userCentralService.getAJAXCheckResult(dataAndErrorsMap);
		out.write(output.getBytes());
		out.close();
	}

	/**
	 * 驗證使用者的新密碼並回傳訊息。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.GET }, params = { "m=mNewPassword" }, produces = {
			"text/plain; charset=UTF-8" })
	@ResponseBody
	public void checkNewPasswordProcess(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(request.getParameter(ConstHelperKey.METHOD.key()),
				request.getParameter(ConstHelperKey.QUERY.key()));
		dataAndErrorsMap = this.memberService.checkNewPassword(dataAndErrorsMap, request);
		String output = this.userCentralService.getAJAXCheckResult(dataAndErrorsMap);
		out.write(output.getBytes());
		out.close();
	}

	/**
	 * 驗證使用者再次輸入的新密碼並回傳訊息。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.GET }, params = { "mNewPassword",
			"mNewPassword_Again" }, produces = { "text/plain; charset=UTF-8" })
	@ResponseBody
	public void checkNewPassword_AgainProcess(HttpServletRequest request, HttpServletResponse response,
			OutputStream out) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(ConstMemberKey.NewPassword.key(), request.getParameter(ConstMemberKey.NewPassword.key()));
		dataAndErrorsMap.put(ConstMemberKey.NewPassword_Again.key(),
				request.getParameter(ConstMemberKey.NewPassword_Again.key()));
		dataAndErrorsMap = this.memberService.checkNewPassword_Again(dataAndErrorsMap, request);
		String output = this.userCentralService.getAJAXCheckResult(dataAndErrorsMap);
		out.write(output.getBytes());
		out.close();
	}

	/**
	 * 驗證使用者的興趣並回傳訊息。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.GET }, params = { "n=interest" }, produces = {
			"text/plain; charset=UTF-8" })
	@ResponseBody
	public void checkInterestProcess(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap = this.interest_DetailService.encapsulateAndCheckOneData(dataAndErrorsMap, request);
		String output = this.userCentralService.getAJAXCheckResult(dataAndErrorsMap);
		out.write(output.getBytes());
		out.close();
	}

	/**
	 * 驗證手機的驗證碼，並傳回驗證結果，如果驗證失敗，必須重新請求驗證碼，原本的驗證碼只要驗證一次馬上消失。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	@RequestMapping(path = { "/phoneCheck.do" }, method = { RequestMethod.POST }, params = { "m=phoneCheck" })
	public String phoneCheckProcess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute(ConstUserKey.PHONECHECKCODE.key()) == null) {
			return ConstMapping.MODIFY_PHONECHECK_ERROR.path();
		}

		String codeFromSystem = (String) session.getAttribute(ConstUserKey.PHONECHECKCODE.key());
		String codeFromInput = request.getParameter(ConstHelperKey.QUERY.key());
		session.removeAttribute(ConstUserKey.PHONECHECKCODE.key());
		if (!codeFromSystem.equals(codeFromInput)) {
			return ConstMapping.MODIFY_PHONECHECK_ERROR.path();
		}

		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean.setmValidPhone(ConstUserParameter.VALIDPHONE_YES.param());
		this.memberService.update(mbean);
		return ConstMapping.MODIFY_SUCCESS.path();
	}

	/**
	 * 修改使用者密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.POST }, params = { "m=security" })
	public String modifySecureProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		dataAndErrorsMap.put(ConstMemberKey.OldPassword.key(), request.getParameter(ConstMemberKey.OldPassword.key()));
		dataAndErrorsMap.put(ConstMemberKey.NewPassword.key(), request.getParameter(ConstMemberKey.NewPassword.key()));
		dataAndErrorsMap.put(ConstMemberKey.NewPassword_Again.key(),
				request.getParameter(ConstMemberKey.NewPassword_Again.key()));
		dataAndErrorsMap = this.memberService.checkOldPassword(dataAndErrorsMap, request);
		dataAndErrorsMap = this.memberService.checkNewPassword(dataAndErrorsMap, request);
		dataAndErrorsMap = this.memberService.checkNewPassword_Again(dataAndErrorsMap, request);
		if (this.userCentralService.getSubmitCheckFailure(dataAndErrorsMap)) {
			return ConstMapping.MODIFY_SECURE_ERROR.path();
		}

		this.memberService.updateSecurity(dataAndErrorsMap, request);
		return ConstMapping.MODIFY_SUCCESS.path();
	}

	/**
	 * 修改使用者個人資料。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.POST }, params = { "m=profile" })
	public String modifyProfileProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		dataAndErrorsMap = this.memberService.encapsulateAndCheckAllDataWhenModifying(dataAndErrorsMap, request);
		if (this.userCentralService.getSubmitCheckFailure(dataAndErrorsMap)) {
			return ConstMapping.MODIFY_PROFILE_ERROR.path();
		}

		this.memberService.updateProfile(dataAndErrorsMap, request);
		return ConstMapping.MODIFY_SUCCESS.path();
	}

	/**
	 * 修改使用者個人狀況。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.POST }, params = { "m=situation" })
	public String modifySituationProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		dataAndErrorsMap = this.memberService.encapsulateAndCheckAllDataWhenModifying(dataAndErrorsMap, request);
		if (this.userCentralService.getSubmitCheckFailure(dataAndErrorsMap)) {
			return ConstMapping.MODIFY_SITUATION_ERROR.path();
		}

		this.memberService.updateSituation(dataAndErrorsMap, request);
		return ConstMapping.MODIFY_SUCCESS.path();
	}

	/**
	 * 修改使用者興趣喜好。
	 * 
	 * (只許成功，失敗一樣回傳更新成功，即使失敗資料都會被重置)
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	@RequestMapping(path = { "/modify.do" }, method = { RequestMethod.POST }, params = { "m=interest" })
	public String modifyInterestProcess(HttpServletRequest request, HttpServletResponse response) {
		this.interest_DetailService.updateInterest_DetailBean(request);
		return ConstMapping.MODIFY_SUCCESS.path();
	}
}