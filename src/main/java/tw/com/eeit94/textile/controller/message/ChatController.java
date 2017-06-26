package tw.com.eeit94.textile.controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;

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
		session.removeAttribute(ConstChatKey.CHAT.key());

		String encryptedCId = request.getParameter(ConstHelperKey.QUERY.key());
		String decryptedCId = this.secureService.getDecryptedText(encryptedCId,
				ConstSecureParameter.CHATROOMID.param());
		Long cId = Long.parseLong(decryptedCId);

		return ConstMapping.CHAT_SHOW.path();
	}
}