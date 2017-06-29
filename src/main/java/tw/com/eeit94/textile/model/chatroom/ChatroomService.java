package tw.com.eeit94.textile.model.chatroom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
 * 控制聊天室資料的Service元件，這個Service必須考慮到執行緒安全。
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
	private ReadWriteLock lock;
	private volatile Map<Long, String> chatroomIdentityGetter;
	private volatile Map<String, Long> chatroomPrimaryKeyGetter;
	private static final int RANDOM_CODE_AMOUNT = 16;

	public ChatroomService() {
		this.lock = new ReentrantReadWriteLock();
		this.chatroomIdentityGetter = new HashMap<>();
		this.chatroomPrimaryKeyGetter = new HashMap<>();
	}

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
	 * 判斷是否該聊天室主鍵需要產生隨機辨識碼的配對，如果要則產生配對並存入Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	public String produceChatroomIdentity(Long cId) {
		String identity = null;
		if (!chatroomIdentityGetter.containsKey(cId)) {
			this.lock.writeLock().lock();
			// 鎖住後再判斷一次
			try {
				if (!chatroomIdentityGetter.containsKey(cId)) {
					identity = this.secureService.getRandomCode(RANDOM_CODE_AMOUNT);
					chatroomIdentityGetter.put(cId, identity);
					chatroomPrimaryKeyGetter.put(identity, cId);
				}
			} finally {
				this.lock.writeLock().unlock();
			}
		} else {
			identity = chatroomIdentityGetter.get(cId);
		}

		return identity;
	}

	/**
	 * 得到聊天室主鍵為Key、隨機辨識碼為Value的Map物件，
	 * 
	 * 這個物件主要用來讓伺服器傳送給使用者訊息時辨別應在哪一個被訂閱的聊天室。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	public Map<Long, String> getChatroomIdentityGetter() {
		return this.chatroomIdentityGetter;
	}

	/**
	 * 得到隨機辨識碼為Key、聊天室主鍵為Value的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	public Map<String, Long> getChatroomPrimaryKeyGetter() {
		return this.chatroomPrimaryKeyGetter;
	}

	/**
	 * 得到聊天室的所有主鍵。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	@Transactional
	public List<Long> getAllChatroomPrimaryKeys() {
		List<Long> primaryKeys = new ArrayList<>();
		List<ChatroomBean> list = this.chatroomDAO.selectAll();
		for (int i = 0; i < list.size(); i++) {
			primaryKeys.add(list.get(i).getcId());
		}

		return primaryKeys;
	}

	/**
	 * 製作開啟聊天室傳送的Websocket URI。
	 * 
	 * @author 賴
	 * @version 2017/06/27
	 */
	public String getWebsocketURI(HttpServletRequest request) {
		String websocketURI = request.getRequestURL().toString();
		websocketURI = websocketURI.substring(0, websocketURI.lastIndexOf('/'));
		websocketURI = websocketURI.substring(0, websocketURI.lastIndexOf('/'));
		websocketURI = websocketURI.substring(websocketURI.indexOf(':'), websocketURI.length());
		websocketURI = new StringBuffer().append(ConstHelperKey.WEBSOCKET_PROTOCOL.key()).append(websocketURI)
				.append(ConstMapping.MESSAGE_SHOW.path()).toString();
		return websocketURI;
	}

	/**
	 * 製作聊天室傳送訊息的路徑。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	public String getSendURI(String identity) {
		String sendURI = ConstMapping.MESSAGE_IN.path();
		sendURI = new StringBuffer().append(sendURI).append("/").append(identity).toString();
		return sendURI;
	}

	/**
	 * 製作聊天室接收訊息的路徑。
	 * 
	 * @author 賴
	 * @version 2017/06/28
	 */
	public String getSubscribeURI(String identity) {
		String subscribeURI = ConstMapping.MESSAGE_OUT.path();
		subscribeURI = new StringBuffer().append(subscribeURI).append("/").append(identity).toString();
		return subscribeURI;
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