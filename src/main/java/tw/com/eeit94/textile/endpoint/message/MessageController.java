package tw.com.eeit94.textile.endpoint.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * @version 2017/06/26
	 */
	@RequestMapping("/handle")
	public void messageHandler(String message) {
		this.simpMessagingTemplate.convertAndSend("/passage/receive", message + "123");
	}
}