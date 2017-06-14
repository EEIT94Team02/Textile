package tw.com.eeit94.textile.model.chatroom_member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制聊天室明細資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Service
public class Chatroom_MemberService {
	@Autowired
	private Chatroom_MemberDAO chatroom_MemberDAO;
	
	@Transactional
	public List<Chatroom_MemberBean> selectAll() {
		return chatroom_MemberDAO.selectAll();
	}
}