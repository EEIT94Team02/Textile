package tw.com.eeit94.textile.model.photo_album;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class Photo_albumService {
	@Autowired
	private Photo_albumDAO photo_albumDAO;

	public Photo_albumService(Photo_albumDAO photo_albumDAO) {
		this.photo_albumDAO = photo_albumDAO;
	}

	@Transactional(readOnly = true)
	public List<Photo_albumBean> select() {
		return photo_albumDAO.select();
	}

	@Transactional(readOnly = true)
	public Photo_albumBean findPhotoAlbumByAlbumNo(Photo_albumBean bean) {
		return photo_albumDAO.selectByAlbumNo(bean);
	}

	@Transactional(readOnly = true)
	public List<Photo_albumBean> findPhotoAlbumByOthers(Photo_albumBean bean) {
		return photo_albumDAO.selectByOthers(bean);
	}

	public Photo_albumBean createPhotoAlbum(Photo_albumBean bean) {
		return photo_albumDAO.insert(bean);
	}

	public Photo_albumBean ChangePhotoAlbumColumn(Photo_albumBean bean) {
		Photo_albumBean search = this.findPhotoAlbumByAlbumNo(bean);
		if (search != null) {
			search.setAlbumname(bean.getAlbumname() == null ? search.getAlbumname() : bean.getAlbumname());
			search.setIntroduction(bean.getIntroduction() == null ? search.getIntroduction() : bean.getIntroduction());
			search.setVisibility(bean.getVisibility() == null ? search.getVisibility() : bean.getVisibility());
			return photo_albumDAO.update(search);
		}
		return null;
	}

	public boolean deletePhotoAlbum(Photo_albumBean bean) {
		Photo_albumBean search = this.findPhotoAlbumByAlbumNo(bean);
		if (search != null) {
			return photo_albumDAO.delete(bean);
		}
		return false;
	}
}