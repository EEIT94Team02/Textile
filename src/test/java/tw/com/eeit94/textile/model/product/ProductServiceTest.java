package tw.com.eeit94.textile.model.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.model.product.ProductService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class ProductServiceTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		ProductService ps = (ProductService) ctx.getBean("productService");
		Session session = ((SessionFactory) ctx.getBean("sessionFactory")).getCurrentSession();
		session.beginTransaction();

		// Select by id & all
		// ProductBean bean = null;
		ProductBean bean = new ProductBean();
		bean.setProductId(1);
		System.out.println(ps.select(bean));
		// List<ProductBean> productBeans = ps.select(bean);
		// BufferedOutputStream bos = null;
		// try {
		// bos = new BufferedOutputStream(new FileOutputStream(new
		// File("E:/Project/img/table/heartForLoner2.jpg")));
		// bos.write(productBeans.get(0).getImg());
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (bos != null) {
		// try {
		// bos.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }

		// Insert
		// BufferedInputStream bis = null;
		// ByteArrayOutputStream baos = null;
		// BufferedOutputStream bos = null;
		// try {
		// File file = new File("E:/Project/img/table/heartForLoner.jpg");
		// bis = new BufferedInputStream(new FileInputStream(file));
		// byte[] temp = new byte[128];
		// baos = new ByteArrayOutputStream();
		// bos = new BufferedOutputStream(baos);
		// int data = 0;
		// while ((data = bis.read(temp)) != -1) {
		// bos.write(temp);
		// }
		// ProductBean bean = new ProductBean();
		// bean.setProductName("Heartsss");
		// bean.setUnitPrice(13);
		// bean.setCategory("自用");
		// bean.setIntro("邊緣人自給自足");
		// bean.setStatus("下架");
		// bean.setImg(baos.toByteArray());
		// bean.setRewardPoints((int) (13 * 1.4));
		// ps.insert(bean);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (bos != null) {
		// try {
		// bos.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// if (baos != null) {
		// try {
		// baos.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// if (bis != null) {
		// try {
		// bis.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }

		// Update
		// ProductBean temp = new ProductBean();
		// temp.setProductId(5);
		// ProductBean bean = ps.select(temp).get(0);
		// bean.setProductName("Heartless");
		// ps.update(bean);

		// Delete
		// ProductBean bean = new ProductBean();
		// bean.setProductId(5);
		// ps.delete(bean);

		session.getTransaction().commit();
		session.close();
		((ConfigurableApplicationContext) ctx).close();
	}
}