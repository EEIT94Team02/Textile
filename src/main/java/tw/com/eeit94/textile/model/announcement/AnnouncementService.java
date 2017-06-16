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
	public List<AnnouncementBean> selectEndTime (AnnouncementBean bean){
	
		List<AnnouncementBean> selectsEndTime = null;
		try {
			selectsEndTime = announcementDao.selectByEndAnnouncement(bean);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+"error by selectEndTime");
		}
		
		return selectsEndTime;
	}
	
//	@Transactional
//	public AnnouncementBean updateByNextTime(AnnouncementBean bean, int fre) {
//		AnnouncementBean result = null;
//		result = announcementDao.select(bean);
//		Calendar cal = Calendar.getInstance();
//		// DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		cal.setTime(result.getNextTime());
//
//		if (result != null) {
//			if ("月".equals(result.getFre())) {
//				cal.add(Calendar.MONTH, fre);
//			} else if ("天".equals(result.getFre())) {
//				cal.add(Calendar.DAY_OF_MONTH, fre);
//			} else if ("時".equals(result.getFre())) {
//				cal.add(Calendar.HOUR, fre);
//			} else {
//				cal.add(Calendar.MINUTE, fre);
//			}
//			result.setNextTime(cal.getTime());
//		}
//		return result;
//	}

}