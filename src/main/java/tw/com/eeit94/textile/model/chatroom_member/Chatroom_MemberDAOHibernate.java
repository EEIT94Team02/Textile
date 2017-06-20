package tw.com.eeit94.textile.model.chatroom_member;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 控制聊天室明細資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "chatroom_MemberDAO")
public class Chatroom_MemberDAOHibernate implements Chatroom_MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<Chatroom_MemberBean> results;
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	private static final String SELECT_ALL = "from Chatroom_MemberBean";
	
	@Override
	public List<Chatroom_MemberBean> selectAll() {
		Query<Chatroom_MemberBean> query = this.getSession().createQuery(SELECT_ALL, Chatroom_MemberBean.class);
		return query.list();
	}

	@Override
	public List<Chatroom_MemberBean> select(Chatroom_MemberBean c_mbean) {
		this.results = new ArrayList<>();
		this.results.add(this.getSession().load(Chatroom_MemberBean.class, c_mbean.getChatroom_MemberPK()));
		return this.results;
	}

	@Override
	public List<Chatroom_MemberBean> insert(Chatroom_MemberBean c_mbean) {
		this.getSession().save(c_mbean);
		return this.select(c_mbean);
	}

	/**
	 * Chatroom_MemberBean對應到的表格內容只有複合主鍵的兩欄，因無法修改，此方法並不需要。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	@Override
	public List<Chatroom_MemberBean> update(Chatroom_MemberBean c_mbean) {
		return null;
	}
	
	@Override
	public List<Chatroom_MemberBean> delete(Chatroom_MemberBean c_mbean) {
		this.getSession().delete(c_mbean);
		return this.select(c_mbean);
	}
}