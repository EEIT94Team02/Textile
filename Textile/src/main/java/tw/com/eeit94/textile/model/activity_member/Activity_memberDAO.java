package tw.com.eeit94.textile.model.activity_member;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public interface Activity_memberDAO {

	public List<Activity_memberBean> select();

	public Activity_memberBean selectByPrimaryKey(Activity_memberBean bean);

	public List<Activity_memberBean> selectByOthers(Activity_memberBean bean);

	public Activity_memberBean insert(Activity_memberBean bean);

	public Activity_memberBean update(Activity_memberBean bean);

	public boolean delete(Activity_memberBean bean);

	public List<Activity_memberBean> updatePosition(List<Activity_memberBean> beans);
}