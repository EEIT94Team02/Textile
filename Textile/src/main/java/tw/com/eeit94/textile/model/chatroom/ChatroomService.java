package tw.com.eeit94.textile.model.chatroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制聊天室資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Service
public class ChatroomService {
	@Autowired
	private ChatroomDAO chatroomDAO;
	
	@Transactional
	public List<ChatroomBean> selectAll() {
		return chatroomDAO.selectAll();
	}
}