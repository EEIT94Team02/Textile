/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Service產生步驟：
 * 1. Service名稱為'"Table名稱" + "Service"'。
 * 2. 標記@Service。
 * 3. 加入至少一個Bean元件並標記@Autowired。
 */
@Service
public class ActivityService {
	@Autowired
	private ActivityDAO activityDao;
	
	public ActivityService(ActivityDAO activityDao) {
		this.activityDao = activityDao;
	}

	/*
	 * 實作企業邏輯
	 */
	@Transactional
	public List<ActivityBean> selectAll() {
		return activityDao.select();
	}	
	
	@Transactional
	public ActivityBean selectByActivityNO(ActivityBean bean) {
		return activityDao.select(bean);
	}
	@Transactional
	public List<ActivityBean> customeSelect(ActivityBean bean , String string) {
		List<ActivityBean> result = null;		
		try {
			result = activityDao.selectByOthers(bean,string);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	@Transactional
	public ActivityBean createNewActivity(ActivityBean bean) {
		return activityDao.insert(bean);
	}
	@Transactional
	public ActivityBean updateActivity(ActivityBean bean) {
		ActivityBean result = this.selectByActivityNO(bean);
		if(result != null){
			result.setActivityname(bean.getActivityname() ==null ? result.getActivityname(): bean.getActivityname());
			result.setBegintime(bean.getBegintime() ==null ? result.getBegintime(): bean.getBegintime());
			result.setEndtime(bean.getEndtime() ==null ? result.getEndtime(): bean.getEndtime());
			result.setInterpretation(bean.getInterpretation() ==null ? result.getInterpretation(): bean.getInterpretation());
			result.setPlace(bean.getPlace() ==null ? result.getPlace(): bean.getPlace());
			result.setVisibility(bean.getVisibility() ==null ? result.getVisibility(): bean.getVisibility());
			return activityDao.update(result);
		}
		return null;
	}
	
	@Transactional
	public Boolean deleteActivity(ActivityBean bean) {
		return activityDao.delete(bean);
	}
	


}
