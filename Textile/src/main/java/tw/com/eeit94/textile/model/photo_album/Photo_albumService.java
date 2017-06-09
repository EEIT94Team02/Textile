/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo_album;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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