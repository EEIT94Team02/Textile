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
import tw.com.eeit94.textile.model.secure.SecureService;
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
	private SecureService secureService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private Interest_DetailService interest_DetailService;
	@Autowired
	private UserCentralService userCentralService;

	/**
	 * 讀取個人使用者的資料。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @throws Exception
	 */
	@RequestMapping(path = { "/modify.v" }, method = { RequestMethod.GET })
	public String userProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		return ConstMapping.MODIFY_SHOW.path();
	}
}