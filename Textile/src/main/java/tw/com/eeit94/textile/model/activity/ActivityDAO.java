package tw.com.eeit94.textile.model.activity;

import java.text.ParseException;
import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
public interface ActivityDAO {

	public List<ActivityBean> select();

	public ActivityBean select(ActivityBean bean);

	public ActivityBean insert(ActivityBean bean);

	public ActivityBean update(ActivityBean bean);

	public boolean delete(ActivityBean bean);

	public List<ActivityBean> selectByOthers(ActivityBean bean, String string) throws ParseException;
}