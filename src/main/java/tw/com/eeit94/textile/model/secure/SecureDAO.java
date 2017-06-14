package tw.com.eeit94.textile.model.secure;

import java.util.List;

/**
 * 控制金鑰資料的DAO，資料儲存後不可修改或刪除，往後只能新增或讀取。
 * 
 * @author 賴
 * @version 2017/06/11
 */
public interface SecureDAO {

	public List<SecureBean> selectAll();

	public List<SecureBean> select(SecureBean sbean);

	public List<SecureBean> insert(SecureBean sbean);

	public List<SecureBean> update(SecureBean sbean);

	public List<SecureBean> delete(SecureBean sbean);
	
	public List<SecureBean> selectByTarget(SecureBean sbean);
}