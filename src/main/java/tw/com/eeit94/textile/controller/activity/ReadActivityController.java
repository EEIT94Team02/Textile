package tw.com.eeit94.textile.controller.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/activity" })
public class ReadActivityController {

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

	@RequestMapping(method = { RequestMethod.POST }, path = { "/select.do" })
	public String selectprocess(HttpServletRequest request, Model model) throws ParseException {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activitySelectErrors", errors);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String activityno = request.getParameter("activityno");
		String activityname = request.getParameter("activityname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String place = request.getParameter("place");
		String interpretation = request.getParameter("interpretation");		

		// 驗證資料
		if (activityno == "" && activityname == "" && begintime == "" && endtime == "" && place == "" && interpretation == "" ) {
			errors.put("select", "請至少輸入一個條件");
		}
		
		int actNo = 0;
		if (activityno != null && activityno != "") {	
			actNo = Integer.parseInt(activityno);
		}
		
		java.sql.Timestamp begin = new java.sql.Timestamp(0);
		if (begintime != null && begintime != "") {
			begin = new java.sql.Timestamp(sdf.parse(begintime).getTime());
		}
		java.sql.Timestamp end = new java.sql.Timestamp(0);
		if (endtime != null && endtime != "") {
			end = new java.sql.Timestamp(sdf.parse(endtime).getTime());
		}
		if (begintime != "" && endtime != "") {
			if (begin.getTime() > end.getTime()) {
				errors.put("begintime", "請確認活動時間");
				errors.put("endtime", "請確認活動時間");
			}
		}
		if (errors != null && !errors.isEmpty()) {
			return "actSelect.error";
		}

		// 轉換資料
		// 呼叫Model
		ActivityBean bean = new ActivityBean();
		bean.setActivityno(actNo);
		bean.setActivityname(activityname);
		bean.setBegintime(begin);
		bean.setEndtime(end);
		bean.setPlace(place);
		bean.setInterpretation(interpretation);
		List<ActivityBean> result = getActivityService().customeSelect(bean);

		// 根據Model執行結果呼叫View
		if (result != null && !result.isEmpty()) {
			model.addAttribute("actselectOK", "搜尋成功");
			model.addAttribute("ActivityList", result);
			return "Activity.default";
		} else {
			errors.put("select", "查無資料，請重新查詢");
			return "actSelect.error";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/partner.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String partnerprocess(HttpServletRequest request, Model model) throws ParseException {

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activitySelectErrors", errors);
		String activityno = request.getParameter("activityno");
		if (activityno == null || activityno == "") {
			errors.put("activityno", "請輸入活動編號");
		}
		if (errors != null && !errors.isEmpty()) {
			return "actSelect.error";
		}
		int actNo = 0;
		if (activityno != null && activityno != "") {
			actNo = Integer.parseInt(activityno);
		}

		Activity_memberBean man = new Activity_memberBean();
		Activity_memberPK memberBeanPK = new Activity_memberPK();
		memberBeanPK.setActivityno(actNo);
		man.setActivity_memberPK(memberBeanPK);	
		
		System.out.println(man);
		List<Activity_memberBean> results = getActivity_memberService().findActivityMemberByActivityNo(man);

		if (results != null && !results.isEmpty()) {
			model.addAttribute("FindPartner", "搜尋成功");
			ActivityBean bean = new ActivityBean();
			bean.setActivityno(actNo);			
			ActivityBean actbean = getActivityService().selectByActivityNO(bean);
			model.addAttribute("Activity", actbean);
			model.addAttribute("partner", results);
			return "Activity.default";
		} else {
			errors.put("partner", "查無資料，請重新搜尋");
			return "actSelect.error";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/partake.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String partakeprocess(HttpServletRequest request, Model model) throws ParseException {

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activitySelectErrors", errors);
		String IdString = request.getParameter("mId");
		if (IdString == null || IdString == "") {
			errors.put("mId", "請輸入正確資料");
		}
		if (errors != null && !errors.isEmpty()) {
			return "actSelect.error";
		}
		int memberId = 0;
		if (IdString != null && IdString != "") {
			memberId = Integer.parseInt(IdString);
		}

		Activity_memberBean man = new Activity_memberBean();
		Activity_memberPK memberBeanPK = new Activity_memberPK();
		memberBeanPK.setmId(memberId);
		man.setActivity_memberPK(memberBeanPK);
		List<Activity_memberBean> results = getActivity_memberService().findActivityNoByMemberId(man);

		if (results != null && !results.isEmpty()) {
			model.addAttribute("FindPartake", "搜尋成功");
			model.addAttribute("partake", results);
			return "Activity.default";
		} else {
			errors.put("partake", "查無資料，請重新搜尋");
			return "actSelect.error";
		}
	}
	
}