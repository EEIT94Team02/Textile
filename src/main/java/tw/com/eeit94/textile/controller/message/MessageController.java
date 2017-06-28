package tw.com.eeit94.textile.controller.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

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

	/**
	 * 接收請求與回應資料和聊天室頁面。
	 * 
	 * @author 賴
	 * @version 2017/06/27
	 */
	@MessageMapping("/in")
	public void messageHandler(Message<byte[]> message) {
		String text = new String(message.getPayload());
		System.out.println(message.getHeaders());
		simpMessagingTemplate.convertAndSend("/passage/out", text);
	}
}