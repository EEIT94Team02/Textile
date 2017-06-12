package tw.com.eeit94.textile.model.item;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemDAO;
import tw.com.eeit94.textile.model.item.ItemDAOHibernate;
import tw.com.eeit94.textile.model.item.ItemPK;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class ItemDAOHibernateTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		ItemDAO itemDao = (ItemDAOHibernate) ctx.getBean("itemDAO");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by primary key
		ItemPK itemPK = new ItemPK(0, 0);
		ItemBean resultById = itemDao.select(itemPK);
		System.out.println(resultById);

		// Select all
		// List<ItemBean> resultAll = itemDao.select();
		// System.out.println(resultAll);

		// insert
		// ItemPK itemPK = new ItemPK(1,2);
		// ItemBean resultInsert = new ItemBean();
		// resultInsert.setItemPK(itemPK);
		// resultInsert.setAmount(55);
		// itemDao.insert(resultInsert);

		// Update
		// ItemPK itemPK = new ItemPK(1, 2);
		// ItemBean resultUpdate = new ItemBean();
		// resultUpdate.setItemPK(itemPK);
		// resultUpdate.setAmount(777);
		// System.out.println(itemDao.update(resultUpdate));

		// Delete
		// ItemPK itemPK = new ItemPK(1, 2);
		// ItemBean bean = new ItemBean();
		// bean.setItemPK(itemPK);
		// System.out.println(itemDao.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}