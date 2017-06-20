/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * Hibernate DAO產生步驟：
 * 1. Hibernate DAO名稱為'"Table名稱" + "DAOHibernate"'。
 * 2. 實作'"Table名稱" + "DAO"'介面，並覆寫方法。
 * 3. 標記@Repository(value = '"Table名稱(第一個字母小寫)" + "DAO"')。
 * 4. 加入Bean元件並標記@Autowired。
 * 5. 加上javadoc的註解，在Spring或Hibernate的Annotation上方，@author和@version必寫，@version為日期。
 * 6. 不要寫建構子，要測試請參考tw.com.eeit94.textile.model.exapmle.ExampleDAOHibernateTest.java這支測試程式。
 * 7. 要寫測試程式，但不要寫在這裡！
 */
/**
 * [空行] 
 * 本欄未必須由Shift+Alt+J產生，複製貼上也可(要改內容)，這裡簡單敘述這個元件的功能或地位，上面的註解不用留著，example的套件留著就好。
 * [空行]
 * @author 賴
 * @version 2017/06/10
 */
@Repository(value = "exampleDAO")
public class ExampleDAOHibernate implements ExampleDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * 不要寫測試程式，建構子不要寫。
	 */
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<ExampleBean> selectAll() {
		// 用Hibernate實作
		return null;
	}

	@Override
	public ExampleBean select(ExampleBean bean) {
		// 用Hibernate實作
		return null;
	}

	@Override
	public ExampleBean insert() {
		// 用Hibernate實作
		return null;
	}

	@Override
	public ExampleBean update() {
		// 用Hibernate實作
		return null;
	}

	@Override
	public boolean delete() {
		// 用Hibernate實作
		return false;
	}

	private final String SELECT_BY_PRICE_LESS_THAN = "select name from ExampleBean where price<";

	@Override
	public List<ExampleBean> selectByPriceLessThan(double price) {
		@SuppressWarnings("unused")
		Query<ExampleBean> query = this.getSession().createQuery(SELECT_BY_PRICE_LESS_THAN + price, ExampleBean.class);
		/*
		 * 用Hibernate實作
		 */
		return null;
	}
}