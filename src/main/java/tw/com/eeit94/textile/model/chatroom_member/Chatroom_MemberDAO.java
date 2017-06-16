package tw.com.eeit94.textile.model.chatroom_member;

import java.util.List;

/**
 * 控制聊天室明細資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public interface Chatroom_MemberDAO {

	public List<Chatroom_MemberBean> selectAll();

	public List<Chatroom_MemberBean> select(Chatroom_MemberBean c_mbean);

	public List<Chatroom_MemberBean> insert(Chatroom_MemberBean c_mbean);
	
	public List<Chatroom_MemberBean> update(Chatroom_MemberBean c_mbean);

	public List<Chatroom_MemberBean> delete(Chatroom_MemberBean c_mbean);
}