package tw.com.eeit94.textile.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.UserCentralService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.model.social.SocialListService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 讀取使用者資料的Controller，主要加入表單直接讀取的興趣明細資料於MemberBean中。
 * 
 * 如果請求路徑後方為index，並且帶參數「q」，則為讀取其它使用者的資料。
 * 
 * @author 賴
 * @version 2017/06/20
 * @see {@link MemberService}
 * @see {@link Interest_DetailService}
 */
@Controller
@RequestMapping(path = { "/user" })
public class ProfileController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UserCentralService userCentralService;
	@Autowired
	private SocialListService socialListService;
	private static final String UNCONFIRMED = "未確認";
	private static final String FRIEND = "好友";

	/**
	 * 讀取個人使用者的資料。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/index.v" }, method = { RequestMethod.GET })
	public String userViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		// 將好友列表加入session scope
		this.socialListService.setLinksListInSession(request);
		return ConstMapping.PROFILE_USER_SHOW.path();
	}

	/**
	 * 讀取其它使用者的資料：
	 * 
	 * 注意：外來加密的字串可能含有「 」，必須轉為原先的「+」，還要判斷該使用者是否已邀請或為好朋友。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/index.v" }, method = { RequestMethod.GET }, params = { "q" })
	public String otheruserViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Integer userId = mbean.getmId();

		String query = request.getParameter(ConstHelperKey.QUERY.key());
		query = this.secureService.getRebuiltEncryptedText(query);
		query = this.secureService.getDecryptedText(query.replace(' ', '+'), ConstSecureParameter.MEMBERID.param());
		Integer acquaintenceId = Integer.parseInt(query);
		MemberBean acquaintenceBean = this.memberService.selectByPrimaryKey(acquaintenceId);

		String s_type = null;
		s_type = this.socialListService.checkRelationshipSituation(userId, acquaintenceId, UNCONFIRMED);
		if (s_type != null) {
			request.setAttribute("s_type", s_type);
		}
		s_type = this.socialListService.checkRelationshipSituation(userId, acquaintenceId, FRIEND);
		if (s_type != null) {
			request.setAttribute("s_type", s_type);
		}

		if (acquaintenceBean != null) {
			acquaintenceBean = this.userCentralService.selectUserAllData(acquaintenceBean);
			request.setAttribute(ConstFilterKey.OTHERUSER.key(), acquaintenceBean);
			request.setAttribute(ConstHelperKey.QUERY.key(), query);
			return ConstMapping.PROFILE_OTHERUSER_SUCCESS.path();
		} else {
			return ConstMapping.ERROR_PAGE_REDIRECT.path();
		}
	}
}