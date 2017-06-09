package tw.com.eeit94.textile.testmodel.photo;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoDAO;
import tw.com.eeit94.textile.model.photo.PhotoDAOHibernate;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

public class TestPhotoDAOHibernate {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		PhotoDAO dao = (PhotoDAOHibernate) context.getBean("photoDAO");

		PhotoBean bean;
		List<PhotoBean> beans;

		beans = dao.select();
		System.out.println(beans);
		
		PhotoBean max = new PhotoBean();
		max.setPhotono("20170527000000040005");
		String aaa = dao.selectMax(max);
		System.out.println(aaa);

		PhotoBean select = new PhotoBean();
		select.setPhotono("20170527000000010001");
		bean = dao.selectByPrimarykey(select);
		System.out.println(bean);		
		System.out.println(select.getPhoto_albumBean());
		
		PhotoBean selectalbumno = new PhotoBean();
		selectalbumno.setAlbumno(2);
		beans = dao.selectByAlbumno(selectalbumno);
		System.out.println(beans);
		
		PhotoBean selectPK = new PhotoBean();
		selectPK.setPhotono("20170527000000020001");
		bean = dao.selectByPrimarykey(selectPK);
		System.out.println(bean);
		
		PhotoBean selectOther = new PhotoBean();
		selectOther.setPhotoname("Roger");
		selectOther.setInterpretation("tennis");
		selectOther.setAlbumno(1);
		selectOther.setPosition("大頭貼");
		selectOther.setVisibility("公開");
		beans = dao.selectByOthers(selectOther);
		System.out.println(beans);

		PhotoBean insert = new PhotoBean();
		insert.setPhotono("20170527000000010002");
		insert.setRespath("xxx");
		insert.setPhotoname("Roger");
		insert.setInterpretation("tennis");
		insert.setAlbumno(1);
		insert.setPosition("大頭貼");
		insert.setVisibility("公開");
		bean = dao.insert(insert);
		System.out.println(bean);

		PhotoBean update = new PhotoBean();
		update.setPhotono("20170527000000010002");
		update.setRespath("xxx");
		update.setPhotoname("Roger");
		update.setInterpretation("tennis");
		update.setAlbumno(1);
		update.setPosition("大頭貼");
		update.setVisibility("私人");
		bean = dao.update(update);
		System.out.println(bean);

		PhotoBean del = new PhotoBean();
		del.setPhotono("20170527000000010002");
		boolean result = dao.delete(del);
		System.out.println(result);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}

}
