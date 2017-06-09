package tw.com.eeit94.textile.testmodel.photo_album;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumDAO;
import tw.com.eeit94.textile.model.photo_album.Photo_albumDAOHibernate;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

public class TestPhoto_albumDAOHibernate {

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
