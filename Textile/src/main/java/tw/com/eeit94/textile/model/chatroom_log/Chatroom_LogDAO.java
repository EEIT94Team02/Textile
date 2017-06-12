package tw.com.eeit94.textile.model.chatroom_log;

import java.util.List;

/**
 * 控制聊天室紀錄資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public interface Chatroom_LogDAO {

	public List<Chatroom_LogBean> selectAll();

	public List<Chatroom_LogBean> select(Chatroom_LogBean c_lbean);

	public List<Chatroom_LogBean> insert(Chatroom_LogBean c_lbean);

	public List<Chatroom_LogBean> update(Chatroom_LogBean c_lbean);

	public List<Chatroom_LogBean> delete(Chatroom_LogBean c_lbean);
}