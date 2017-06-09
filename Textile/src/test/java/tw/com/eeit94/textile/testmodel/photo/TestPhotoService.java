package tw.com.eeit94.textile.testmodel.photo;

import java.io.File;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoService;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

public class TestPhotoService {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		PhotoService service = (PhotoService)context.getBean("photoService");
		sessionFactory.getCurrentSession().beginTransaction();
		
		PhotoBean bean = null;
		List<PhotoBean>  beans = null;
		
//		beans = service.select();
//		System.out.println(beans);
//		
//		PhotoBean select = new PhotoBean();
//		select.setPhotono("20170527000000020001");
//		bean = service.selectByphotono(select);
//		System.out.println(bean);
//		
//		PhotoBean selectbyAlbum = new PhotoBean();
//		selectbyAlbum.setAlbumno(1);
//		beans = service.selectByAlbumno(selectbyAlbum);
//		System.out.println(beans);
		
		//test uploadPhot
		File file = new File("C:/Users/Student/Desktop/Textile-etc/photo/nadal.jpg");
		File target = new File("C:/Textile/repository/Textile/src/main/webapp/photos/");
//		"C:/Textile/repository/Textile/src/main/webapp/image/Makarova.jpg"
		File result = service.uploadPhoto(file, target);
		System.out.println(result.getName());		
	
		//test getTimeString , getMemberIdString , countphoto
		PhotoBean insert = new PhotoBean();
		String timeString = service.getTimeString();
		System.out.println(timeString);
		String memberIdString = service.getMemberIdString(1);
		System.out.println(memberIdString);
		insert.setPhotono(timeString+memberIdString);
		String beanPhotoNo = service.countphoto(insert);
		System.out.println(beanPhotoNo);
		
		//test insertDataToTable
		insert.setPhotono(beanPhotoNo);
		insert.setRespath(result.getPath());
		insert.setAlbumno(1);
		insert.setPhotoname("Nadal");
		insert.setPosition("大頭貼");
		insert.setVisibility("公開");
		insert.setInterpretation("US OPEN Champion");
		bean = service.insertDataToTable(insert);
		System.out.println(bean);
		
//		PhotoBean update = new PhotoBean();
//		update.setAlbumno(2);
//		update.setPhotono(beanPhotoNo);
//		update.setInterpretation("XXX");
//		update.setVisibility("私人");
//		update.setPosition("OOO");
//		update.setPhotoname("Nole");
//		bean = service.updatePhotoinfo(update);
//		System.out.println(bean);
//		
//		PhotoBean removePhoto = new PhotoBean();
//		removePhoto.setPhotono("20170608000000010003");
//		boolean remove = service.removePhoto(removePhoto);
//		System.out.println(remove);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();


	}

}
