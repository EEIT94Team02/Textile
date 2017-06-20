package tw.com.eeit94.textile.model.photo_album;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public class TestPhoto_albumService {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		Photo_albumService service = (Photo_albumService) context.getBean("photo_albumService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		Photo_albumBean bean;
		List<Photo_albumBean> beans;

		beans = service.select();
		System.out.println(beans);

		Photo_albumBean select = new Photo_albumBean();
		select.setAlbumno(1);
		bean = service.findPhotoAlbumByAlbumNo(select);
		System.out.println(bean);

		Photo_albumBean selects = new Photo_albumBean();
		selects.setAlbumname("大頭貼");
		selects.setIntroduction("大頭貼");
		selects.setVisibility("好友");
		selects.setmId(5);
		beans = service.findPhotoAlbumByOthers(selects);
		System.out.println(beans);

		Photo_albumBean createAlbum = new Photo_albumBean();
		createAlbum.setAlbumname("0531出遊");
		createAlbum.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		createAlbum.setIntroduction("好好玩");
		createAlbum.setVisibility("公開");
		createAlbum.setmId(8);
		bean = service.createPhotoAlbum(createAlbum);
		System.out.println(bean);

		Photo_albumBean change = new Photo_albumBean();
		change.setAlbumno(6);
		change.setAlbumname("0730出遊");
		change.setIntroduction("好好玩玩玩玩玩玩玩玩玩");
		change.setVisibility("公開");
		bean = service.ChangePhotoAlbumColumn(change);
		System.out.println(bean);

		Photo_albumBean del = new Photo_albumBean();
		del.setAlbumno(6);
		boolean result = service.deletePhotoAlbum(del);
		System.out.println(result);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}