/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo_album;

import java.util.List;
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
public class Photo_albumService {
	@Autowired
	private Photo_albumDAO photo_albumDAO;
	public Photo_albumService(Photo_albumDAO photo_albumDAO) {
		this.photo_albumDAO = photo_albumDAO;
	}

	// 測試程式
	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		Photo_albumService service = (Photo_albumService) context.getBean("photo_albumService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();				
		Photo_albumBean bean ;
		List<Photo_albumBean> beans ;;	
		
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


	/*
	 * 實作企業邏輯
	 */
	@Transactional
	public List<Photo_albumBean> select() {
		return photo_albumDAO.select();
	}
	
	@Transactional
	public Photo_albumBean findPhotoAlbumByAlbumNo(Photo_albumBean bean) {
		return photo_albumDAO.selectByAlbumNo(bean);		
	}
	
	@Transactional
	public List<Photo_albumBean> findPhotoAlbumByOthers(Photo_albumBean bean) {
		return photo_albumDAO.selectByOthers(bean);		
	}
	
	
	@Transactional
	public Photo_albumBean createPhotoAlbum(Photo_albumBean bean) {
		return photo_albumDAO.insert(bean);
	}
	
	@Transactional
	public Photo_albumBean ChangePhotoAlbumColumn(Photo_albumBean bean) {
		Photo_albumBean search = this.findPhotoAlbumByAlbumNo(bean);
		if(search != null){
			search.setAlbumname(bean.getAlbumname() ==null ? search.getAlbumname():bean.getAlbumname());
			search.setIntroduction(bean.getIntroduction() ==null ? search.getIntroduction():bean.getIntroduction());
			search.setVisibility(bean.getVisibility() ==null ? search.getVisibility():bean.getVisibility());
			return photo_albumDAO.update(search);
		}
		return null;
	}
	
	@Transactional
	public boolean deletePhotoAlbum(Photo_albumBean bean) {
		Photo_albumBean search = this.findPhotoAlbumByAlbumNo(bean);
		if(search != null){
			return photo_albumDAO.delete(bean);
		}
		return false;
	}
	

	

	
	
}