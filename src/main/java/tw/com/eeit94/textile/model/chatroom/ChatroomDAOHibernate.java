package tw.com.eeit94.textile.model.chatroom;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 控制聊天室資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "chatroomDAO")
public class ChatroomDAOHibernate implements ChatroomDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<ChatroomBean> results;
	
	public ChatroomDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	private final String SELECT_ALL = "from ChatroomBean";
	
	@Override
	public List<ChatroomBean> selectAll() {
		Query<ChatroomBean> query = this.getSession().createQuery(this.SELECT_ALL, ChatroomBean.class);
		return query.list();
	}

	@Override
	public List<ChatroomBean> select(ChatroomBean cbean) {
		this.results = new ArrayList<>();
		this.results.add(this.getSession().get(ChatroomBean.class, cbean.getcId()));
		return this.results;
	}

	@Override
	public List<ChatroomBean> insert(ChatroomBean cbean) {
		this.getSession().save(cbean);
		return this.select(cbean);
	}

	@Override
	public List<ChatroomBean> update(ChatroomBean cbean) {
		this.getSession().update(cbean);
		return this.select(cbean);
	}

	@Override
	public List<ChatroomBean> delete(ChatroomBean cbean) {
		this.getSession().delete(cbean);
		return this.select(cbean);
	}
}
