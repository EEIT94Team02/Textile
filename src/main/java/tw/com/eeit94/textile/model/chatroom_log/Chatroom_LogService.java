package tw.com.eeit94.textile.model.chatroom_log;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

/**
 * 控制聊天室紀錄資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
 */
@Service
public class Chatroom_LogService {
	@Autowired
	private Chatroom_LogDAO chatroom_LogDAO;
	private static final String Register_Success_Message = "歡迎您加入Textile交友網的新會員，未來有任何的系統公告與通知都會在這裡顯示哦！很高興能為你服務！";

	/**
	 * 註冊成功時，對新會員發送一筆祝賀訊息，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public List<Chatroom_LogBean> getNewChatroom_LogBean(ChatroomBean cbean) {
		Chatroom_LogBean c_lbean = new Chatroom_LogBean();
		Chatroom_LogPK chatroom_LogPK = new Chatroom_LogPK();
		chatroom_LogPK.setcId(cbean.getcId());
		chatroom_LogPK.setmId(Integer.parseInt(ConstHelperKey.SYSTEM_ID.key()));
		chatroom_LogPK.setcSendTime(new Timestamp(System.currentTimeMillis()));
		c_lbean.setChatroom_LogPK(chatroom_LogPK);
		c_lbean.setcContent(Register_Success_Message);
		return this.chatroom_LogDAO.insert(c_lbean);
	}
}