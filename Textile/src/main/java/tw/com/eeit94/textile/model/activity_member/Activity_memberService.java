package tw.com.eeit94.textile.model.activity_member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class Activity_memberService {

	@Autowired
	private Activity_memberDAO activityMemberDAO;

	public Activity_memberService(Activity_memberDAO activityMemberDAO) {
		this.activityMemberDAO = activityMemberDAO;
	}

	@Transactional(readOnly = true)
	public List<Activity_memberBean> findAll() {
		return activityMemberDAO.select();
	}

	@Transactional(readOnly = true)
	public List<Activity_memberBean> findActivityMemberByActivityNo(Activity_memberBean bean) {
		return activityMemberDAO.selectByOthers(bean);
	}

	@Transactional(readOnly = true)
	public List<Activity_memberBean> findActivityNoByMemberId(Activity_memberBean bean) {
		return activityMemberDAO.selectByOthers(bean);
	}

	public Activity_memberBean addNewActivityMember(Activity_memberBean bean) {
		return activityMemberDAO.insert(bean);
	}

	public Activity_memberBean changePosition(Activity_memberBean bean) {
		Activity_memberBean result = activityMemberDAO.selectByPrimaryKey(bean);
		if (result != null) {
			result.setPosition(bean.getPosition());
		}
		return result;
	}

	public List<Activity_memberBean> commitAllActivity(Activity_memberBean bean) {
		List<Activity_memberBean> begin = activityMemberDAO.selectByOthers(bean);
		for (int i = 0; i < begin.size(); i++) {
			begin.get(i).setPosition("參與者");
		}
		bean.setPosition("參與者");
		activityMemberDAO.updatePosition(begin);
		List<Activity_memberBean> after = activityMemberDAO.selectByOthers(bean);
		if (begin.size() == after.size()) {
			return after;
		}
		return null;
	}

	public boolean deleteByActivityNo(Activity_memberBean bean) {
		List<Activity_memberBean> del = activityMemberDAO.selectByOthers(bean);
		for (int i = 0; i < del.size(); i++) {
			activityMemberDAO.delete(del.get(i));
		}
		if (activityMemberDAO.selectByOthers(bean).isEmpty() && activityMemberDAO.selectByOthers(bean).size() == 0) {
			return true;
		}
		return false;
	}
}