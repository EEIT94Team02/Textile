package tw.com.eeit94.textile.model.chatroom_member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

/**
 * 控制聊天室明細資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
 */
@Service
public class Chatroom_MemberService {
	@Autowired
	private Chatroom_MemberDAO chatroom_MemberDAO;

	/**
	 * 註冊成功時，新增一提供系統公告和使用者的聊天室，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public List<Chatroom_MemberBean> getNewChatroom_MemberBean(ChatroomBean cbean, MemberBean mbean) {
		List<Chatroom_MemberBean> list = new ArrayList<>();
		Chatroom_MemberBean c_mbean1 = new Chatroom_MemberBean();
		Chatroom_MemberPK chatroom_MemberPK1 = new Chatroom_MemberPK();
		chatroom_MemberPK1.setcId(cbean.getcId());
		chatroom_MemberPK1.setmId(Integer.parseInt(ConstHelperKey.SYSTEM_ID.key()));
		c_mbean1.setChatroom_MemberPK(chatroom_MemberPK1);
		Chatroom_MemberBean c_mbean2 = new Chatroom_MemberBean();
		Chatroom_MemberPK chatroom_MemberPK2 = new Chatroom_MemberPK();
		chatroom_MemberPK2.setcId(cbean.getcId());
		chatroom_MemberPK2.setmId(mbean.getmId());
		c_mbean2.setChatroom_MemberPK(chatroom_MemberPK2);
		list.add(this.chatroom_MemberDAO.insert(c_mbean1).get(0));
		list.add(this.chatroom_MemberDAO.insert(c_mbean2).get(0));
		return list;
	}
}