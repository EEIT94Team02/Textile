package tw.com.eeit94.textile.model.item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemPK;
import tw.com.eeit94.textile.model.item.ItemService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class ItemServiceTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ItemService is = (ItemService) ctx.getBean("itemService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// ItemBean bean = new ItemBean();
		// bean.setItemPK(new ItemPK(2, 2));
		// System.out.println(is.select(bean));

		// Insert
		// ItemBean bean = new ItemBean();
		// bean.setItemPK(new ItemPK(2, 1));
		// bean.setAmount(33);
		// System.out.println(is.insert(bean));

		// Update
		// ItemBean temp = new ItemBean();
		// temp.setItemPK(new ItemPK(2, 1));
		// ItemBean bean = is.select(temp).get(0);
		// bean.setAmount(7);
		// System.out.println(is.update(bean));

		// Delete
		ItemBean bean = new ItemBean();
		bean.setItemPK(new ItemPK(2, 1));
		System.out.println(is.delete(bean));

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}