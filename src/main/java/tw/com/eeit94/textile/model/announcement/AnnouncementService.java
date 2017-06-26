package tw.com.eeit94.textile.model.announcement;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 周
 * @version 2017/06/12
 */
@Service
public class AnnouncementService {
	@Autowired
	private AnnouncementDAO announcementDao;

	public AnnouncementService(AnnouncementDAO announcementDAO) {
		this.announcementDao = announcementDAO;
	}

	public List<AnnouncementBean> select(AnnouncementBean bean) {
		List<AnnouncementBean> selects = announcementDao.select();
		return selects;
	}

	public List<AnnouncementBean> selectEndTime(AnnouncementBean bean) {

		List<AnnouncementBean> selectsEndTime = null;
		if (bean != null) {
			try {
				selectsEndTime = announcementDao.selectByEndAnnouncement(bean);
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println(e.getMessage() + "error by selectEndTime");
			}
		}
		return selectsEndTime;
	}

	public AnnouncementBean insert(AnnouncementBean bean) {
		AnnouncementBean result = null;
		if (bean != null) {
			result = announcementDao.insert(bean);
		}

		return result;
	}
	
	public List<AnnouncementBean> selectByBeginTime(AnnouncementBean bean){
		List<AnnouncementBean> result =null;
		if(bean!=null){
			try {
				result = announcementDao.selectByBeginTime(bean);
			} catch (Exception e) {
				System.out.println(e.getMessage() + "error by selectByBeginTime");
			}
		}
		return result ;
	}
	public AnnouncementBean update (AnnouncementBean bean){
		AnnouncementBean result =null;
		if(bean!=null){
			result =announcementDao.update(bean);
		}
		
		return result;
	}
	

}