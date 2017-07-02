package tw.com.eeit94.textile.model.photo;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoService;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public class PhotoServiceTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		PhotoService service = (PhotoService) context.getBean("photoService");
		sessionFactory.getCurrentSession().beginTransaction();

		PhotoBean bean = null;
		List<PhotoBean> beans = null;

//		 beans = service.select();
//		 System.out.println(beans);
//		
		 PhotoBean select = new PhotoBean();
		 select.setPhotono("20170527000000020001");
		 bean = service.selectByphotono(select);
		 System.out.println(bean);
		
//		 PhotoBean selectbyAlbum = new PhotoBean();
//		 selectbyAlbum.setAlbumno(1);
//		 beans = service.selectByAlbumno(selectbyAlbum);
//		 System.out.println(beans);

		// test getTimeString , getMemberIdString , countphoto
		PhotoBean insert = new PhotoBean();
		String timeString = service.getTimeString();
		System.out.println(timeString);
		String memberIdString = service.getMemberIdString(1);
		System.out.println(memberIdString);
		int beanPhotoNo = service.countphoto(timeString + memberIdString);
		System.out.println(beanPhotoNo);
		StringBuilder sb = new StringBuilder();
		String tempno = sb.append("0000").append(beanPhotoNo).substring(sb.length() - 4, sb.length());
		String newphotono = timeString + memberIdString + tempno;
		bean.setPhotono(newphotono);
		
		// test insertDataToTable
		insert.setPhotono(newphotono);
		insert.setAlbumno(1);
		insert.setPhotoname("Nadal");
		insert.setPosition("大頭貼");
		insert.setInterpretation("US OPEN Champion");
		insert.setPhoto_albumBean(bean.getPhoto_albumBean());
		bean = service.insertDataToPhoto(insert);
		System.out.println(bean);

		// PhotoBean update = new PhotoBean();
		// update.setAlbumno(2);
		// update.setPhotono(beanPhotoNo);
		// update.setInterpretation("XXX");
		// update.setPosition("OOO");
		// update.setPhotoname("Nole");
		// bean = service.updatePhotoinfo(update);
		// System.out.println(bean);
		//
		// PhotoBean removePhoto = new PhotoBean();
		// removePhoto.setPhotono("20170608000000010003");
		// boolean remove = service.removePhoto(removePhoto);
		// System.out.println(remove);

		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}
}