package tw.com.eeit94.textile.model.announcement;

import java.text.ParseException;
import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
public interface AnnouncementDAO {

	List<AnnouncementBean> select();

	AnnouncementBean select(AnnouncementBean bean);

	AnnouncementBean insert(AnnouncementBean bean);

	AnnouncementBean update(AnnouncementBean bean);

	boolean delete(AnnouncementBean bean);

	List<AnnouncementBean> selectByBginTime(AnnouncementBean bean) throws ParseException;
}