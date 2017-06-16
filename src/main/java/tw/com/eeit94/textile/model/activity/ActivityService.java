package tw.com.eeit94.textile.model.activity;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class ActivityService {
	@Autowired
	private ActivityDAO activityDao;

	public ActivityService(ActivityDAO activityDao) {
		this.activityDao = activityDao;
	}

	@Transactional(readOnly = true)
	public List<ActivityBean> selectAll() {
		return activityDao.select();
	}

	@Transactional(readOnly = true)
	public ActivityBean selectByActivityNO(ActivityBean bean) {
		return activityDao.select(bean);
	}

	@Transactional(readOnly = true)
	public List<ActivityBean> customeSelect(ActivityBean bean, String string) {
		List<ActivityBean> result = null;
		try {
			result = activityDao.selectByOthers(bean, string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ActivityBean createNewActivity(ActivityBean bean) {
		return activityDao.insert(bean);
	}

	public ActivityBean updateActivity(ActivityBean bean) {
		ActivityBean result = this.selectByActivityNO(bean);
		if (result != null) {
			result.setActivityname(bean.getActivityname() == null ? result.getActivityname() : bean.getActivityname());
			result.setBegintime(bean.getBegintime() == null ? result.getBegintime() : bean.getBegintime());
			result.setEndtime(bean.getEndtime() == null ? result.getEndtime() : bean.getEndtime());
			result.setInterpretation(
					bean.getInterpretation() == null ? result.getInterpretation() : bean.getInterpretation());
			result.setPlace(bean.getPlace() == null ? result.getPlace() : bean.getPlace());
			result.setVisibility(bean.getVisibility() == null ? result.getVisibility() : bean.getVisibility());
			return activityDao.update(result);
		}
		return null;
	}

	public Boolean deleteActivity(ActivityBean bean) {
		return activityDao.delete(bean);
	}
}