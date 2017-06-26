package tw.com.eeit94.textile.model.chatroom;

import java.util.List;

/**
 * 控制聊天室資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public interface ChatroomDAO {

	public List<ChatroomBean> selectAll();

	public List<ChatroomBean> select(ChatroomBean cbean);

	public List<ChatroomBean> insert(ChatroomBean cbean);

	public List<ChatroomBean> update(ChatroomBean cbean);

	public List<ChatroomBean> delete(ChatroomBean cbean);

	public List<ChatroomBean> selectByPrimaryKeys(List<Long> primaryKeys);
}