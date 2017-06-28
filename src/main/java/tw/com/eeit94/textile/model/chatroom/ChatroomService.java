package tw.com.eeit94.textile.model.chatroom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberBean;
import tw.com.eeit94.textile.model.member.service.MemberRollbackProviderService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;

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
	@Autowired
	private SecureService secureService;

	/**
	 * 藉由聊天室的主鍵，搜尋對應的實體。
	 * 
	 * @author 賴
	 * @version 2017/06/26
	 */
	@Transactional(readOnly = true)
	public ChatroomBean selectByPrimaryKey(Long cId) {
		ChatroomBean cbean = new ChatroomBean();
		cbean.setcId(cId);
		List<ChatroomBean> list = this.chatroomDAO.select(cbean);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 藉由聊天室的各個主鍵，搜尋對應的實體，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @see {@link MemberRollbackProviderService}
	 */
	public List<ChatroomBean> selectByPrimaryKeys(List<Long> primaryKeys) {
		return this.chatroomDAO.selectByPrimaryKeys(primaryKeys);
	}

	/**
	 * 得到聊天室的所有主鍵。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	@Autowired
	public List<Long> getAllChatroomPrimaryKeys() {
		List<Long> primaryKeys = new ArrayList<>();
		List<ChatroomBean> list = this.chatroomDAO.selectAll();
		for (int i = 0; i < list.size(); i++) {
			primaryKeys.add(list.get(i).getcId());
		}
		
		return primaryKeys;
	}

	/**
	 * 製作聊天室傳送訊息的Websocket URI。
	 * 
	 * @author 賴
	 * @version 2017/06/27
	 */
	public String getWebsocketUri(HttpServletRequest request) {
		String websocketUri = request.getRequestURL().toString();
		websocketUri = websocketUri.substring(0, websocketUri.lastIndexOf('/'));
		websocketUri = websocketUri.substring(0, websocketUri.lastIndexOf('/'));
		websocketUri = websocketUri.substring(websocketUri.indexOf(':'), websocketUri.length());
		websocketUri = new StringBuffer().append(ConstHelperKey.WEBSOCKET_PROTOCOL.key()).append(websocketUri)
				.toString();
		return websocketUri;
	}

	/**
	 * 藉由聊天室實體來製作redirect的url。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @throws Exception
	 */
	public String getOtherUserChatUrl(ChatroomBean cbean, HttpServletRequest request) throws Exception {
		if (cbean == null) {
			return ConstHelperKey.SPACE.key();
		} else {
			String encryptedCId = this.secureService.getEncryptedText(cbean.getcId().toString(),
					ConstSecureParameter.CHATROOMID.param());
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(request.getContextPath()).append(ConstMapping.CHAT.path())
					.append(ConstHelperKey.QUESTION.key()).append(ConstHelperKey.QUERY_EQUAL.key())
					.append(encryptedCId);
			return sBuffer.toString();
		}
	}

	/**
	 * 藉由聊天室的子表聊天室會員實體，來篩選聊天室清單。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @param List&lt;ChatroomBean&gt;
	 *            chatroomList：要先確認聊天室清單的分類主要是「個人」還是「群組」，否則會發生問題。
	 */
	public ChatroomBean getUniqueChatroom_MemberBeanByAcquaintenceId(List<ChatroomBean> chatroomList,
			Integer acquaintenceId) {
		List<Chatroom_MemberBean> Chatroom_MemberList;
		Chatroom_MemberBean chatroom_MemberBean;
		for (int i = 0; i < chatroomList.size(); i++) {
			Chatroom_MemberList = chatroomList.get(i).getChatroom_MemberBean();
			for (int j = 0; j < Chatroom_MemberList.size(); j++) {
				chatroom_MemberBean = Chatroom_MemberList.get(j);
				if (chatroom_MemberBean.getChatroom_MemberPK().getmId().intValue() == acquaintenceId.intValue()) {
					return chatroomList.get(i);
				}
			}
		}
		return null;
	}

	/**
	 * 藉由聊天室的分類，來篩選聊天室清單。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public List<ChatroomBean> getChatroomBeanByClass(List<ChatroomBean> chatroomList, String cClass) {
		List<ChatroomBean> list = new ArrayList<>();
		ChatroomBean chatroomBean;
		for (int i = 0; i < chatroomList.size(); i++) {
			chatroomBean = chatroomList.get(i);
			if (chatroomBean.getcClass().equals(cClass)) {
				list.add(chatroomBean);
			}
		}
		return list;
	}

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