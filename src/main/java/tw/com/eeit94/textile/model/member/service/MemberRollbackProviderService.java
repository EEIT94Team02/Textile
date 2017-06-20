package tw.com.eeit94.textile.model.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.chatroom.ChatroomBean;
import tw.com.eeit94.textile.model.chatroom.ChatroomService;
import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogBean;
import tw.com.eeit94.textile.model.chatroom_log.Chatroom_LogService;
import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberBean;
import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberService;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailBean;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * Service實作有關會員操作時一次用到多個Service時，需要用到統一交易的方法。
 * 
 * @author 賴
 * @version 2017/06/18
 */
@Service
public class MemberRollbackProviderService {
	@Autowired
	private MemberService memberService;
	@Autowired
	private Interest_DetailService interest_DetailService;
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private Chatroom_MemberService chatroom_MemberService;
	@Autowired
	private Chatroom_LogService chatroom_LogService;

	/**
	 * 驗證成功時，新增所有會員的相關資料。
	 * 
	 * 1. 新增會員資料：MemberService。
	 * 
	 * 2. 初始化興趣明細資料：Interest_DetailService。
	 * 
	 * 3. 新增一個人聊天室：ChatroomService。
	 * 
	 * 4. 將新會員與系統公告加入個人聊天室：Chatroom_MemberService。
	 * 
	 * 5. 對新會員發送一筆祝賀訊息：Chatroom_LogService。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	@Transactional
	public void rollbackProviderWhenRegistering(Map<String, String> dataAndErrorsMap, HttpServletRequest request) {
		MemberBean mbean = this.memberService.getNewMemberBean(dataAndErrorsMap, request);
		Interest_DetailBean i_dbean = this.interest_DetailService.getNewInterest_DetailBean(mbean);
		mbean.setInterest_DetailBean(i_dbean);
		ChatroomBean cbean = this.chatroomService.getNewChatroomBean();
		List<Chatroom_MemberBean> c_mbeans = this.chatroom_MemberService.getNewChatroom_MemberBean(cbean, mbean);
		List<Chatroom_LogBean> c_lbeans = this.chatroom_LogService.getNewChatroom_LogBean(cbean);
		cbean.setChatroom_MemberBean(c_mbeans);
		cbean.setChatroom_LogBean(c_lbeans);
		request.setAttribute(ConstFilterKey.USER.key(), mbean);
		request.setAttribute(ConstFilterKey.CHATROOM.key(), cbean);
	}
}