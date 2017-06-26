package tw.com.eeit94.textile.controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.chatroom.ChatViewBean;
import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.chatroom.ChatroomService;
import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 開啟聊天室頁面以及傳遞聊天室所需要的初始參數的控制元件。
 * 
 * @author 賴
 * @version 2017/06/26
 */
@Controller
@RequestMapping(path = { "/user" })
public class ChatController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private Chatroom_MemberService chatroom_MemberService;

	/**
	 * 接收請求與回應資料和聊天室頁面。
	 * 
	 * @author 賴
	 * @version 2017/06/26
	 * @throws Exception
	 */
	@RequestMapping(path = { "/chat.do" }, method = { RequestMethod.GET }, params = { "q" })
	public String chatViewProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		session.removeAttribute(ConstChatKey.CHAT.key());

		String encryptedCId = request.getParameter(ConstHelperKey.QUERY.key());
		String decryptedCId = this.secureService.getDecryptedText(encryptedCId,
				ConstSecureParameter.CHATROOMID.param());
		Long cId = Long.parseLong(decryptedCId);
		ChatroomBean cbean = this.chatroomService.selectByPrimaryKey(cId);

		Integer acquaintenceId = this.chatroom_MemberService.getAcquaintenceId(cbean, mbean);
		if (acquaintenceId == null) {
			return ConstMapping.ERROR_PAGE.path();
		}
		MemberBean acquaintenceBean = this.memberService.selectByPrimaryKey(acquaintenceId);
		String acquaintenceName = acquaintenceBean.getmName();

		ChatViewBean cvbean = new ChatViewBean();
		cvbean.setcId(encryptedCId);
		cvbean.setAcquaintenceName(acquaintenceName);
		session.setAttribute(ConstChatKey.CHAT.key(), cvbean);
		return ConstMapping.CHAT_SHOW.path();
	}
}