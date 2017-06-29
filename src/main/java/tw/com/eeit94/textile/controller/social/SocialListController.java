package tw.com.eeit94.textile.controller.social;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.MemberRollbackProviderService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.model.social.SocialListBean;
import tw.com.eeit94.textile.model.social.SocialListPK;
import tw.com.eeit94.textile.model.social.SocialListService;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 
 * 
 * @author 周
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/social" })
@SessionAttributes(names = { "userId", "select", "Bselect", "Tselect", "unconfirmed" })
public class SocialListController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@Autowired
	private MemberRollbackProviderService memberRollbackProviderService;
	@Autowired
	public SocialListService socialListService;
	@Autowired
	public MemberService memberService;
	@Autowired
	public SecureService secureService;

	@RequestMapping(method = { RequestMethod.POST }, path = { "/invite.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String inviteprocess(HttpServletRequest request, Model model) throws Exception {
		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();

		HttpSession session = request.getSession();
		MemberBean ibean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		model.addAttribute("SocialListInviteErrors", errors);
		String socialfre = request.getParameter("q");
		socialfre = this.secureService.getRebuiltEncryptedText(socialfre);
		socialfre = this.secureService.getDecryptedText(socialfre, "mId");
		String sb = request.getParameter("submit");
		Integer userId = ibean.getmId();
		Integer acquaintenceId = 0;
		// 轉換資料
		if (socialfre == null || socialfre == "") {
			errors.put("socialfre", "請輸入朋友id");
		} else {
			acquaintenceId = Integer.parseInt(socialfre);
		}
		if (errors != null && !errors.isEmpty()) {
			return "social.error";
		}

		SocialListPK pk = new SocialListPK(userId, acquaintenceId);
		SocialListBean bean = new SocialListBean();
		MemberBean mbean = this.memberService.selectByPrimaryKey(acquaintenceId);
		bean.setSocialListPK(pk);
		bean.setS_group("未分類");
		bean.setMbean(mbean);
		bean.setIbean(ibean);
		bean.setLog_in(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setS_type("未確認");
		SocialListBean result = this.socialListService.insert(bean);
		System.out.println(sb);
		if (sb.equals("邀請")) {
			System.out.println(sb);
			model.addAttribute("insertOK", "邀請成功");
			model.addAttribute("invite", result);
			return "s_insert";

		} else {
			errors.put("socialinserterror", "邀請失敗");
			return "social.error";
		}

	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/search.do" })
	public String searchprocess(HttpServletRequest request, Model model) throws IOException {

		// 接收資料
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Integer userId = mbean.getmId();

		String socialfre = request.getParameter("mName");
		String socialtype = request.getParameter("s_type");
		String socialgroup = request.getParameter("s_group");
		String socaillog_in = request.getParameter("log_in");

		Map<String, String> errors = new HashMap<String, String>();

		model.addAttribute("SocialListSelectErrors", errors);
		Timestamp date = null;
		if (socaillog_in != null && socaillog_in != "") {
			try {
				date = new Timestamp(sdf.parse(socaillog_in).getTime());
			} catch (ParseException e) {
				errors.put("Timestamperror", "時間格式錯誤");
				e.printStackTrace();
			}
		}

		List<SocialListBean> search = this.socialListService.searchfriend(userId, socialtype, socialgroup, date);
		String sb = request.getParameter("submit");
		System.out.println(userId);
		System.out.println(sb);
		System.out.println("列出" + search);

		if (sb.equals("條件查詢")) {
			System.out.println(sb);
			model.addAttribute("search", search);
			return "s_search.v";

		} else if (search == null) {
			errors.put("searcherror", "查詢失敗");
			System.out.println("查詢失敗");
			return "social.error";
		} else {
			System.out.println("errorsss");
			return "/error/404.v";
		}

	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/delete.do" })
	public String deleteprocess(HttpServletRequest request, Model model) throws IOException {
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Integer userId = mbean.getmId();

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("SocialListDeleteErrors", errors);
		String socialfre = request.getParameter("acquaintenceId");
		String sb = request.getParameter("submit");
		System.out.println(sb);
		System.out.println(socialfre);
		SocialListBean bean = new SocialListBean();

		Integer acquaintenceId = 0;
		// 轉換資料
		if (socialfre == null || socialfre == "") {
			errors.put("socialfre", "請輸入朋友id");
		} else {
			acquaintenceId = Integer.parseInt(socialfre);
		}
		SocialListPK pk = new SocialListPK(userId, acquaintenceId);
		bean.setSocialListPK(pk);
		boolean deletefriend = this.socialListService.delete(bean);

		if (sb.equals("刪除") && deletefriend) {
			model.addAttribute("delete", deletefriend);
			System.out.println(sb);
			return "social.success";
		} else if (!deletefriend) {

			return "social.error";
		} else {
			errors.put("deletefail", "刪除失敗");
			return "/error/404.v";
		}

	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/insert.do" })
	public String insertprocess(HttpServletRequest request, Model model) throws IOException {
		// 接收資料

		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());// 被邀請者的Id(這個畫面使用者的id)
		Integer acquaintenceId = mbean.getmId();
		// Integer userId =ibean.getmId();

		List<SocialListBean> selectcheck = this.socialListService.selectcheck(acquaintenceId, "未確認");// 去表格找acquaintenceId找自己的Id
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("SocialListInsertErrors", errors);
		String getUnconfirmedId = request.getParameter("q");
		Integer UnconfirmedId = Integer.parseInt(getUnconfirmedId);// 邀請者的Id
		MemberBean ibean = this.memberService.selectByPrimaryKey(UnconfirmedId);// 邀請者的ibean
		if (errors != null && !errors.isEmpty()) {
			return "social.error";
		}
		SocialListBean bean = new SocialListBean();// insert一筆(被邀請者,邀請者)的複合主鍵
		SocialListBean bean2 = new SocialListBean();// update(邀請者,被邀請者)由"未確認"更改成"好友"
		System.out.println(acquaintenceId);
		System.out.println(UnconfirmedId);
		System.out.println("列出" + selectcheck);
		SocialListPK pk = new SocialListPK(acquaintenceId, UnconfirmedId);// insert用PK
		SocialListPK pk2 = new SocialListPK(UnconfirmedId, acquaintenceId);// update用PK
		bean.setS_group("未分類");
		bean.setSocialListPK(pk);
		bean.setIbean(mbean);// Ibean one to one userId的表格,所以輸入自己(使用這個頁面)的ID
		bean.setMbean(ibean);// Mbean on to one acquaintenceId的表格,所以輸入邀請者的ID
		bean.setLog_in(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setS_type("好友");
		SocialListBean insert = this.socialListService.insert(bean);
		System.out.println(UnconfirmedId);
		bean2.setSocialListPK(pk2);
		bean2.setS_type("好友");
		bean2.setLog_in(new java.sql.Timestamp(System.currentTimeMillis()));
		SocialListBean update = this.socialListService.update(bean2);
		System.out.println(UnconfirmedId);
		if (insert == null) {
			errors.put("socialinserterror", "新增失敗");
			return "social.error";
		} else {
			// model.addAttribute("insertOK", "新增成功");
			// model.addAttribute("insert", insert);
			// model.addAttribute("insert", update);
			return "social.success";
		}

		// } else if (sb.equals("拒絕")) {
		//
		// boolean result = socialListService.refuseDelete(bean);
		//
		// if (result) {
		// model.addAttribute("deleteOK", "刪除成功");
		// model.addAttribute("delete", result);
		// return "social.success";
		// } else {
		// errors.put("socialDeleteError", "刪除失敗");
		// return "social.error";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/refuse.do" })
	public String refuseprocess(HttpServletRequest request, Model model) throws Exception {
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Integer acquaintenceId = mbean.getmId();

		String userIdTemp = this.secureService.getDecryptedText(request.getParameter("q"),
				ConstSecureParameter.MEMBERID.param());
		Integer userId = Integer.parseInt(userIdTemp);
		SocialListBean sbean = new SocialListBean();
		SocialListPK pk = new SocialListPK(userId, acquaintenceId);
		sbean.setSocialListPK(pk);
		this.socialListService.delete(sbean);
		return null;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" })
	public String updateprocess(HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		String updateType = request.getParameter("s_type");

		Integer userId = mbean.getmId();

		List<SocialListBean> selectByFriend = this.socialListService.selectAllFriend(userId, "好友");

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("SocialListInsertErrors", errors);

		return "";
	}
}