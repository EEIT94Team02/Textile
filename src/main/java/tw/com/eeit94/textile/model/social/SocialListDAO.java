package tw.com.eeit94.textile.model.social;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import tw.com.eeit94.textile.model.member.MemberBean;

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

	public List<SocialListBean> getRelativeToMeList(Integer acquaintenceId, String s_type);

	public List<SocialListBean> selectAllFriend(Integer userId, String s_type);

	

	List<SocialListBean> selectByFriend(SocialListBean bean, Timestamp date) throws ParseException;



	List<SocialListBean> searchFriend(Integer userId, String s_type, String s_group, Timestamp s_login);

	List<SocialListBean> selectAllFriend(Integer userId, List<String> s_type);

	


	

	
	
}

	