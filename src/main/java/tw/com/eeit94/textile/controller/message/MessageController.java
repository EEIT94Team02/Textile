package tw.com.eeit94.textile.controller.message;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import tw.com.eeit94.textile.model.chatroom.ChatroomService;
import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogBean;
import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogPK;
import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

/**
 * 傳遞聊天訊息的控制元件。
 * 
 * @author 賴
 * @version 2017/06/26
 */
@Controller
public class MessageController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private SecureService secureService;
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private Chatroom_LogService chatroom_LogService;

	/**
	 * 接收請求與回應資料和聊天室頁面。
	 * 
	 * @author 賴
	 * @version 2017/06/27
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@MessageMapping("/in/{identity}")
	@SuppressWarnings("unchecked")
	public void messageHandler(Message<byte[]> message, @DestinationVariable String identity)
			throws NumberFormatException, Exception {
		Map<String, Object> nativeHeaders = (Map<String, Object>) message.getHeaders()
				.get(ConstHelperKey.WEBSOCKET_NATIVEHEADERS.key());
		String cContent = new String(message.getPayload());
		String encryptedMId = ((List<String>) nativeHeaders.get(ConstHelperKey.QUERY.key())).get(0);
		Long cId = this.chatroomService.getChatroomPrimaryKeyGetter().get(identity);
		Integer mId = Integer
				.parseInt(this.secureService.getDecryptedText(encryptedMId, ConstSecureParameter.MEMBERID.param()));

		Chatroom_LogBean c_lbean = new Chatroom_LogBean();
		Chatroom_LogPK chatroom_LogPK = new Chatroom_LogPK();
		chatroom_LogPK.setcId(cId);
		chatroom_LogPK.setmId(mId);
		chatroom_LogPK.setcSendTime(new Timestamp(System.currentTimeMillis()));
		c_lbean.setChatroom_LogPK(chatroom_LogPK);
		c_lbean.setcContent(cContent);
		this.chatroom_LogService.insertNewChatLog(c_lbean);

		simpMessagingTemplate.convertAndSend("/passage/out/" + identity, cContent, nativeHeaders);
	}
}