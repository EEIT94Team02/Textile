package tw.com.eeit94.textile.model.chatroom_log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制聊天室紀錄資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Service
public class Chatroom_LogService {
	@Autowired
	private Chatroom_LogDAO chatroom_LogDAO;
	
	@Transactional
	public List<Chatroom_LogBean> selectAll() {
		return chatroom_LogDAO.selectAll();
	}
}