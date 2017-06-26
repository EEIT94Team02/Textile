package tw.com.eeit94.textile.model.chatroom_member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.service.MemberRollbackProviderService;
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
	 * 搜索自己所在的聊天室，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @see {@link MemberRollbackProviderService}
	 */
	public List<Chatroom_MemberBean> selectByMId(Integer mId) {
		return this.chatroom_MemberDAO.selectByMId(mId);
	}

	/**
	 * 檢查會員是否在該聊天室實體(聊天室會員明細名單)內。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public boolean checkInTheRoom(ChatroomBean cbean, MemberBean mbean) {
		List<Integer> mIds = this.getChatroomMembers(cbean);
		Integer mId = mbean.getmId();
		for (Integer acquaintenceId : mIds) {
			if (acquaintenceId.intValue() == mId.intValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 藉由聊天室實體得到聊天室各會員的主鍵。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public List<Integer> getChatroomMembers(ChatroomBean cbean) {
		List<Integer> mIds = new ArrayList<>();
		List<Chatroom_MemberBean> list = cbean.getChatroom_MemberBean();
		for (Chatroom_MemberBean c_mbean : list) {
			mIds.add(c_mbean.getChatroom_MemberPK().getmId());
		}
		return mIds;
	}

	/**
	 * 藉由聊天室實體獲取朋友的主鍵。
	 * 
	 * 注意：聊天室分類必須是「個人」。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public Integer getAcquaintenceId(ChatroomBean cbean, MemberBean mbean) {
		if (this.checkInTheRoom(cbean, mbean)) {
			List<Integer> mIds = this.getChatroomMembers(cbean);
			Integer mId = mbean.getmId();
			for (Integer acquaintenceId : mIds) {
				if (acquaintenceId.intValue() != mId.intValue()) {
					return acquaintenceId;
				}
			}
		}
		return null;
	}

	/**
	 * 藉由聊天室會員明細實體清單取得聊天室主鍵清單。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public List<Long> getChatroomPrimaryKeys(List<Chatroom_MemberBean> chatroom_MemberList) {
		List<Long> primaryKeys = new ArrayList<>();
		Chatroom_MemberPK chatroom_MemberPK;
		for (int i = 0; i < chatroom_MemberList.size(); i++) {
			chatroom_MemberPK = chatroom_MemberList.get(i).getChatroom_MemberPK();
			primaryKeys.add(chatroom_MemberPK.getcId());
		}
		return primaryKeys;
	}

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