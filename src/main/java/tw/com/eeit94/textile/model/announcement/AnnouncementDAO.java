package tw.com.eeit94.textile.model.announcement;

import java.text.ParseException;
import java.util.List;

/**
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

	

	List<AnnouncementBean> selectByEndAnnouncement(AnnouncementBean bean) throws ParseException;

	List<AnnouncementBean> selectByBeginTime(AnnouncementBean bean) throws ParseException;
}