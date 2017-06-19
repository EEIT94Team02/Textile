package tw.com.eeit94.textile.controller.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.activity.ActivityService;
import tw.com.eeit94.textile.model.activity_member.Activity_memberBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberPK;
import tw.com.eeit94.textile.model.activity_member.Activity_memberService;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/activity" })
public class ActivityMemberController {

	@Autowired
	private ActivityService activityService;
	public ActivityService getActivityService() {
		return activityService;
	}

	@Autowired
	private Activity_memberService activity_memberService;
	public Activity_memberService getActivity_memberService() {
		return activity_memberService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/join.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String createprocess(HttpServletRequest request, Model model) throws ParseException {

		// 接收資料
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activityCRDErrors", errors);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String activityname = request.getParameter("activityname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String place = request.getParameter("place");
		String interpretation = request.getParameter("interpretation");

		// 轉換資料
		// 驗證資料
		if (activityname == null || activityname == "") {
			errors.put("activityname", "請輸入活動名稱");
		}
		if (place == null || place == "") {
			errors.put("place", "請輸入活動地點");
		}
		if (interpretation == null || interpretation == "") {
			errors.put("interpretation", "請輸入活動內容");
		}
		if (begintime == null || begintime == "") {
			errors.put("begintime", "請輸入活動開始時間");
		}
		if (endtime == null || endtime == "") {
			errors.put("endtime", "請輸入活動結束時間");
		}
		if (begintime != "" && endtime != "") {
			if (sdf.parse(begintime).getTime() > sdf.parse(endtime).getTime()) {
				errors.put("begintime", "請確認活動時間");
				errors.put("endtime", "請確認活動時間");
			}
		}
		if (errors != null && !errors.isEmpty()) {
			return "actInsert.error";
		}

		java.sql.Timestamp begin = new java.sql.Timestamp(sdf.parse(begintime).getTime());
		java.sql.Timestamp end = new java.sql.Timestamp(sdf.parse(endtime).getTime());

		// 呼叫Model
		ActivityBean bean = new ActivityBean();
		bean.setActivityname(activityname);
		bean.setBegintime(begin);
		bean.setEndtime(end);
		bean.setPlace(place);
		bean.setInterpretation(interpretation);
		ActivityBean result = getActivityService().createNewActivity(bean);

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("create", "活動創建失敗");
			return "actInsert.error";
		} else {
			Activity_memberBean man = new Activity_memberBean();
			man.setActivity_memberPK(new Activity_memberPK(result.getActivityno(), user.getmId()));
			man.setActivityBean(result);
			man.setMemberBean(user);
			man.setPosition("發起人");
			Activity_memberBean create = getActivity_memberService().addNewActivityMember(man);
			if (create != null) {
				model.addAttribute("actInsertOK", "新建活動成功");
				model.addAttribute("ActivityList", getActivityService().selectAll());
				return "Activity.default";
			}
			errors.put("create", "活動創建失敗");
			return "actInsert.error";
		}
	}
}