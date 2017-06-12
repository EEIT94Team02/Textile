package tw.com.eeit94.textile.model.photo;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public interface PhotoDAO {

	public List<PhotoBean> select();

	public PhotoBean selectByPrimarykey(PhotoBean bean);

	public List<PhotoBean> selectByAlbumno(PhotoBean bean);

	public List<PhotoBean> selectByOthers(PhotoBean bean);

	public PhotoBean insert(PhotoBean bean);

	public PhotoBean update(PhotoBean bean);

	public boolean delete(PhotoBean bean);

	public String selectMax(PhotoBean bean);
}