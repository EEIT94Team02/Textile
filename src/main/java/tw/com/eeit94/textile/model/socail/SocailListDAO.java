package tw.com.eeit94.textile.model.socail;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
public interface SocailListDAO {

	public List<SocailListBean> select();

	public SocailListBean select(SocailListPK pk);

	public SocailListBean insert(SocailListBean bean);

	public SocailListBean update(SocailListBean bean);

	public boolean delete(SocailListBean bean);

	List<SocailListBean> selectByFriend(SocailListBean bean, Timestamp date) throws ParseException;

	

	
	
}

	