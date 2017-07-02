package tw.com.eeit94.textile.model.photo_album;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public interface Photo_albumDAO {

	public List<Photo_albumBean> select();

	public Photo_albumBean selectByAlbumNo(Photo_albumBean bean);

	public List<Photo_albumBean> selectByOthers(Photo_albumBean bean);

	public Photo_albumBean insert(Photo_albumBean bean);

	public Photo_albumBean update(Photo_albumBean bean);

	public boolean delete(Photo_albumBean bean);

	public List<Photo_albumBean> selectBymId(Photo_albumBean bean);
}