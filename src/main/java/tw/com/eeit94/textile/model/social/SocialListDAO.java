package tw.com.eeit94.textile.model.social;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
public interface SocialListDAO {

	public List<SocialListBean> select();

	public SocialListBean select(SocialListPK pk);

	public SocialListBean insert(SocialListBean bean);

	public SocialListBean update(SocialListBean bean);

	public boolean delete(SocialListBean bean);

	List<SocialListBean> selectByFriend(SocialListBean bean, Timestamp date) throws ParseException;

	

	
	
}

	