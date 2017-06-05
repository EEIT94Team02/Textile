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
	
	public Photo_albumBean createPhotoAlbum(Photo_albumBean bean) {
		return photo_albumDAO.insert(bean);
	}
	
	public Photo_albumBean ChangePhotoAlbumName(Photo_albumBean bean) {
		Photo_albumBean search = this.searchByAlbumName(bean);
		if(search == null){
			return photo_albumDAO.update(bean);
		}
		return null;
	}
	
	public Photo_albumBean searchByAlbumName(Photo_albumBean bean) {
		return photo_albumDAO.selectByAlbumName(bean);		
	}
	
	public List<Photo_albumBean> searchByPhotoAlbumMemberId(Photo_albumBean bean) {
		return photo_albumDAO.selectByMemberId(bean);		
	}
	
	
}