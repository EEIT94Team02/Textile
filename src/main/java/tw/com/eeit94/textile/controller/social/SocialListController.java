package tw.com.eeit94.textile.controller.social;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.chatroom.ChatroomService;
import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberService;
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
@SessionAttributes(names = { "userId", "select", "Bselect", "Tselect", "unconfirmedList" })
public class SocialListController {
	@Autowired
	public SocialListService socialListService;
	@Autowired
	public MemberService memberService;
	@Autowired
	public SecureService secureService;
	@Autowired
	public MemberRollbackProviderService memberRollbackProviderService;

	@RequestMapping(method = { RequestMethod.GET }, path = { "/invite.do" }, params = { "q" })
	public void inviteprocess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());

		String socialfre = request.getParameter("q");
		socialfre = this.secureService.getRebuiltEncryptedText(socialfre);
		socialfre = this.secureService.getDecryptedText(socialfre, "mId");

		Integer userId = mbean.getmId();
		Integer acquaintenceId = Integer.parseInt(socialfre);

		SocialListPK socialListPK = new SocialListPK(userId, acquaintenceId);
		SocialListBean sbean = new SocialListBean();
		sbean.setSocialListPK(socialListPK);
		sbean.setS_type("未確認");
		sbean.setS_group("未分類");
		sbean.setLog_in(new java.sql.Timestamp(System.currentTimeMillis()));
		this.socialListService.insert(sbean);

		Writer out = response.getWriter();
		out.write("success");
		out.close();
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

	@RequestMapping(method = { RequestMethod.GET }, path = { "/delete.do" }, params = { "q" })
	public void deleteprocess(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, Exception {
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean myBean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Integer myId = myBean.getmId();

		String socialfre = request.getParameter("q");
		System.out.println(socialfre);
		socialfre = this.secureService.getRebuiltEncryptedText(socialfre);
		Integer otherId = Integer
				.parseInt(this.secureService.getDecryptedText(socialfre, ConstSecureParameter.MEMBERID.param()));

		SocialListBean sbean = new SocialListBean();
		SocialListPK socialListPK = new SocialListPK(otherId, myId);
		sbean.setSocialListPK(socialListPK);

		String s_type = this.socialListService.checkRelationshipSituation(otherId, myId, "未確認");
		if (s_type != null) {
			this.socialListService.delete(sbean);
		}

		Writer out = response.getWriter();
		out.write("success");
		out.close();
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/insert.do" }, params = { "q" })
	public void insertprocess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean myBean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Integer myId = myBean.getmId();
		String socialfre = request.getParameter("q");
		socialfre = this.secureService.getRebuiltEncryptedText(socialfre);
		Integer otherId = Integer
				.parseInt(this.secureService.getDecryptedText(socialfre, ConstSecureParameter.MEMBERID.param()));

		SocialListBean sbean1 = new SocialListBean();
		SocialListPK socialListPK = new SocialListPK(otherId, myId);
		sbean1.setSocialListPK(socialListPK);
		sbean1.setS_type("好友");
		sbean1.setS_group("未分類");
		sbean1.setLog_in(new java.sql.Timestamp(System.currentTimeMillis()));
		sbean1 = this.socialListService.insert(sbean1);

		SocialListBean sbean2 = new SocialListBean();
		SocialListPK socialListPK2 = new SocialListPK(myId, otherId);
		sbean2.setSocialListPK(socialListPK2);
		sbean2.setS_type("好友");
		sbean2.setS_group("未分類");
		sbean2.setLog_in(new java.sql.Timestamp(System.currentTimeMillis()));
		sbean2 = this.socialListService.insert(sbean2);

		this.memberRollbackProviderService.addFriendWithRollbackProvider(sbean1);

		Writer out = response.getWriter();
		out.write("success");
		out.close();
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