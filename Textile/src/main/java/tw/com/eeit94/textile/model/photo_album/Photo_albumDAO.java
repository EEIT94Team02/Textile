/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo_album;

import java.util.List;

/*
 * DAO產生步驟：
 * 1. DAO名稱為'"Table名稱" + "DAO"'。
 * 2. Service需要任何DAO時，先在介面定義方法。
 */
public interface Photo_albumDAO {

	public List<Photo_albumBean> select();

	public Photo_albumBean select(Photo_albumBean bean);
	
	public Photo_albumBean selectByAlbumName(Photo_albumBean bean);
	
	public List<Photo_albumBean> selectByMemberId(Photo_albumBean bean);

	public Photo_albumBean insert(Photo_albumBean bean);

	public Photo_albumBean update(Photo_albumBean bean);

	public boolean delete(Photo_albumBean bean);

// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
//	public List<PhotoBean> selectByPriceLessThan(double price);
}