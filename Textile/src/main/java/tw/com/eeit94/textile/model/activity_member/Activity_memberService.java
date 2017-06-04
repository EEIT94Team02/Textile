/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.activity_member;


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
public class Activity_memberService {
	@Autowired
	private Activity_memberDAO activityMemberDAO;

	// 測試程式
	public static void main(String args[]) {
//		Activity_memberService xxx = new Activity_memberService();		

		
	}

	/*
	 * 實作企業邏輯
	 */
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<Activity_memberBean> selectActivityMemberByActivity(Activity_memberBean bean) {
		return activityMemberDAO.select(bean);
	}
	
	public Activity_memberBean changePosition(Activity_memberBean bean){
		if(bean.getActivityno() != null && bean.getMemberid() != null){
			return activityMemberDAO.update(bean);
		}
		return null;
	}
	
	public Activity_memberBean insertNewActivityMember(Activity_memberBean bean){
		if(bean.getActivityno() != null && bean.getMemberid() != null){
			return activityMemberDAO.insert(bean);
		}
		return null;
	}
	
	public Activity_memberBean inviteNewActivityMember(Activity_memberBean bean){
		if(bean.getActivityno() != null && bean.getMemberid() != null){
			return activityMemberDAO.insert(bean);
		} else{
			this.changePosition(bean);
		}
		return null;
	}
	
	
	
}