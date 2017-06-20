package tw.com.eeit94.textile.model.item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemPK;
import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class ItemBeanTest {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		// Select by composite primary key
		// ItemBean result = (ItemBean)session.get(ItemBean.class, new ItemPK(2,
		// 2));
		// System.out.println(result);

		// Insert
		// ItemBean resultInsert = new ItemBean();
		// ItemPK itemPK = new ItemPK(1, 2);
		// resultInsert.setItemPK(itemPK);
		// resultInsert.setProductBean(session.get(ProductBean.class, 2));
		// resultInsert.setAmount(770);
		// session.save(resultInsert);

		// Update
		// ItemBean resultUpdate = new ItemBean();
		// ItemPK itemPK = new ItemPK(1, 2);
		// resultUpdate.setItemPK(itemPK);
		// resultUpdate.setAmount(777);
		// session.update(resultUpdate);

		// Delete
		// ItemBean resultDelete = new ItemBean();
		// ItemPK itemPK = new ItemPK(1, 2);
		// resultDelete.setItemPK(itemPK);
		// session.delete(resultDelete);

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}