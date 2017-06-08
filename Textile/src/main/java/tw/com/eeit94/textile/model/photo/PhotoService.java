/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

/*
 * Service產生步驟：
 * 1. Service名稱為'"Table名稱" + "Service"'。
 * 2. 標記@Service。
 * 3. 加入至少一個Bean元件並標記@Autowired。
 */
@Service
public class PhotoService {
	@Autowired
	private PhotoDAO photoDAO;

	public PhotoService(PhotoDAO photoDAO) {
		this.photoDAO = photoDAO;
	}

	// 測試程式
	public static void main(String args[]) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		PhotoService service = (PhotoService)context.getBean("photoService");
		sessionFactory.getCurrentSession().beginTransaction();
		
		PhotoBean bean = null;
		List<PhotoBean>  beans = null;
		
		beans = service.select();
		System.out.println(beans);
		
		PhotoBean select = new PhotoBean();
		select.setPhotono("20170527000000020001");
		bean = service.selectByphotono(select);
		System.out.println(bean);
		
		PhotoBean selectbyAlbum = new PhotoBean();
		selectbyAlbum.setAlbumno(1);
		beans = service.selectByAlbumno(selectbyAlbum);
		System.out.println(beans);
		
		//test uploadPhot
		File file = new File("C:/Users/Student/Desktop/Textile-etc/photo/nadal.jpg");
		File target = new File("C:/Textile/repository/Textile/src/main/webapp/image/");
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
		
		PhotoBean update = new PhotoBean();
		update.setAlbumno(2);
		update.setPhotono(beanPhotoNo);
		update.setInterpretation("XXX");
		update.setVisibility("私人");
		update.setPosition("OOO");
		update.setPhotoname("Nole");
		bean = service.updatePhotoinfo(update);
		System.out.println(bean);
		
		PhotoBean removePhoto = new PhotoBean();
		removePhoto.setPhotono("20170608000000010002");
		boolean remove = service.removePhoto(removePhoto);
		System.out.println(remove);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();

	}

	/*
	 * 實作企業邏輯
	 */
	@Transactional // import
					// org.springframework.transaction.annotation.Transactional;
	public List<PhotoBean> select() {
		return photoDAO.select();
	}

	@Transactional // import
					// org.springframework.transaction.annotation.Transactional;
	public List<PhotoBean> selectByAlbumno(PhotoBean bean) {
		return photoDAO.selectByAlbumno(bean);
	}

	@Transactional // import
					// org.springframework.transaction.annotation.Transactional;
	public PhotoBean selectByphotono(PhotoBean bean) {
		return photoDAO.selectByPrimarykey(bean);
	}

	@Transactional // import
	// org.springframework.transaction.annotation.Transactional;
	public String getTimeString() {
		Calendar today = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();		
		Integer yy = today.get(Calendar.YEAR);
		Integer mm = today.get(Calendar.MONTH)+1;
		Integer dd = today.get(Calendar.DATE);
		String month = "";
		String date = "";
		if(mm.toString().length() == 1 ){
			month = "0"+mm;
		}
		if(dd.toString().length() == 1 ){
			date = "0"+dd;
		}	
		return sb.append(yy).append(month).append(date).toString();
	}
	
	@Transactional // import
	// org.springframework.transaction.annotation.Transactional;
	public String getMemberIdString(int id) {
		StringBuilder sb = new StringBuilder();	
		sb.append("00000000").append(id);
		String memberIdString = sb.substring(sb.length()-8,sb.length());
		return memberIdString;
	}
	
	@Transactional // import
	// org.springframework.transaction.annotation.Transactional;
	public String countphoto(PhotoBean bean) {
		String temp = photoDAO.selectMax(bean);
		temp = temp.substring(temp.length()-4,temp.length());
		String max = String.valueOf(Integer.parseInt(temp)+1);
		StringBuilder sb = new StringBuilder();
		sb.append("0000").append(max).substring(sb.length()-4, sb.length());
		String result = bean.getPhotono()+sb;
		return result;
	}
	
	@Transactional // import
	// org.springframework.transaction.annotation.Transactional;
	public PhotoBean insertDataToTable(PhotoBean bean) {	
		return photoDAO.insert(bean);
	}

	@Transactional // import
					// org.springframework.transaction.annotation.Transactional;
	public File uploadPhoto(File file, File rootfolder) {
		File result = null;
//		"C:/Textile/repository/Textile/src/main/webapp/image/Makarova.jpg"
//		rootfolder+file.getName()+".jpg"
		UID photo = new UID();		
		File file2 = new File(rootfolder+"/XXXX/"+photo.hashCode()+".jpg");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			file2.getParentFile().mkdir();
			fis = new FileInputStream(file);
			fos = new FileOutputStream(file2, true);
			int data;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			result = file2;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Transactional // import
					// org.springframework.transaction.annotation.Transactional;
	public PhotoBean updatePhotoinfo(PhotoBean bean) {
		PhotoBean result = null;
		PhotoBean phonebean = this.selectByphotono(bean);
		if (phonebean != null) {
			phonebean.setPhotoname(bean.getPhotoname() == null ? phonebean.getPhotoname() : bean.getPhotoname());
			phonebean.setPosition(bean.getPosition() == null ? phonebean.getPosition() : bean.getPosition());
			phonebean.setVisibility(bean.getVisibility() == null ? phonebean.getVisibility() : bean.getVisibility());
			phonebean.setAlbumno(bean.getAlbumno() == null ? phonebean.getAlbumno() : bean.getAlbumno());
			phonebean.setInterpretation(
					bean.getInterpretation() == null ? phonebean.getInterpretation() : bean.getInterpretation());
			result = photoDAO.update(phonebean);
		}
		return result;
	}

	@Transactional // import
					// org.springframework.transaction.annotation.Transactional;
	public boolean removePhoto(PhotoBean bean) {
		boolean result = false;
		PhotoBean phonebean = photoDAO.selectByPrimarykey(bean);
		if (phonebean != null) {
			result = photoDAO.delete(phonebean);
			if (result) {
				File file = new File(phonebean.getRespath());
				result = file.delete();
			}
		}
		return result;
	}

}