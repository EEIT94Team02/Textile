package tw.com.eeit94.textile.model.chatroom;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 控制聊天室資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
 */
@Service
public class ChatroomService {
	@Autowired
	private ChatroomDAO chatroomDAO;

	/**
	 * 註冊成功時，新增一提供系統公告和使用者的聊天室，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public ChatroomBean getNewChatroomBean() {
		ChatroomBean cbean = new ChatroomBean();
		cbean.setcCreateTime(new Timestamp(System.currentTimeMillis()));
		cbean.setcClass(ConstChatroomParameter.USER.param());
		return this.chatroomDAO.insert(cbean).get(0);
	}
}