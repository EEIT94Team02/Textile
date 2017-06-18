package tw.com.eeit94.textile.model.chatroom_log;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 控制聊天室紀錄資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "chatroom_LogDAO")
public class Chatroom_LogDAOHibernate implements Chatroom_LogDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<Chatroom_LogBean> results;
	
	public Chatroom_LogDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	private final String SELECT_ALL = "from Chatroom_LogBean";
	
	@Override
	public List<Chatroom_LogBean> selectAll() {
		Query<Chatroom_LogBean> query = this.getSession().createQuery(this.SELECT_ALL, Chatroom_LogBean.class);
		return query.list();
	}

	@Override
	public List<Chatroom_LogBean> select(Chatroom_LogBean c_lbean) {
		this.results = new ArrayList<>();
		this.results.add(this.getSession().load(Chatroom_LogBean.class, c_lbean.getChatroom_LogPK()));
		return this.results;
	}

	@Override
	public List<Chatroom_LogBean> insert(Chatroom_LogBean c_lbean) {
		this.getSession().save(c_lbean);
		return this.select(c_lbean);
	}

	@Override
	public List<Chatroom_LogBean> update(Chatroom_LogBean c_lbean) {
		this.getSession().update(c_lbean);
		return this.select(c_lbean);
	}

	@Override
	public List<Chatroom_LogBean> delete(Chatroom_LogBean c_lbean) {
		this.getSession().delete(c_lbean);;
		return this.select(c_lbean);
	}
}
