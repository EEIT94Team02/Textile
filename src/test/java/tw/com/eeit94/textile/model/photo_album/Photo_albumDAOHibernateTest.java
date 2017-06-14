package tw.com.eeit94.textile.model.photo_album;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumDAO;
import tw.com.eeit94.textile.model.photo_album.Photo_albumDAOHibernate;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public class Photo_albumDAOHibernateTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFacotry = (SessionFactory) context.getBean("sessionFactory");
		sessionFacotry.getCurrentSession().beginTransaction();

		Photo_albumDAO dao = (Photo_albumDAOHibernate) context.getBean("photo_albumDAO");
		List<Photo_albumBean> beans = null;
		Photo_albumBean bean = null;

		beans = dao.select();
		System.out.println(beans);

		Photo_albumBean test = new Photo_albumBean();
		test.setAlbumno(1);
		bean = dao.selectByAlbumNo(test);
		System.out.println(bean);

		Photo_albumBean insert = new Photo_albumBean();
		insert.setAlbumname("大頭貼");
		insert.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		insert.setIntroduction("大頭貼");
		insert.setmId(00000005);
		insert.setVisibility("公開");
		bean = dao.insert(insert);
		System.out.println(bean);

		Photo_albumBean update = new Photo_albumBean();
		update.setAlbumno(6);
		update.setAlbumname("大頭貼");
		update.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		update.setIntroduction("大頭貼");
		update.setmId(00000005);
		update.setVisibility("私人");
		bean = dao.update(update);
		System.out.println(bean);

		Photo_albumBean delete = new Photo_albumBean();
		delete.setAlbumno(6);
		boolean result = dao.delete(delete);
		System.out.println(result);

		sessionFacotry.getCurrentSession().getTransaction().commit();
		sessionFacotry.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}