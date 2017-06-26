package tw.com.eeit94.textile.controller.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.social.SocialListBean;
import tw.com.eeit94.textile.model.social.SocialListService;

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
	private MemberService memberService;

	public MemberService getMemberService() {
		return memberService;
	}

	@Autowired
	private Activity_memberService activity_memberService;

	public Activity_memberService getActivity_memberService() {
		return activity_memberService;
	}

	@Autowired
	private SocialListService socialListService;

	public SocialListService getSocialListService() {
		return socialListService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/select.do" })
	public String selectprocess(HttpServletRequest request, Model model) throws ParseException {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activitySelectErrors", errors);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		HttpSession session = request.getSession();
		String activityname = request.getParameter("activityname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String place = request.getParameter("place");
		String interpretation = request.getParameter("interpretation");

		// 驗證資料
		if (activityname == "" && begintime == "" && endtime == "" && place == "" && interpretation == "") {
			errors.put("select", "請至少輸入一個條件");
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
		bean.setActivityno(0);
		bean.setActivityname(activityname);
		bean.setBegintime(begin);
		bean.setEndtime(end);
		bean.setPlace(place);
		bean.setInterpretation(interpretation);
		List<ActivityBean> result = getActivityService().customeSelect(bean);

		System.out.println(result);

		// 根據Model執行結果呼叫View
		if (result != null && !result.isEmpty()) {
			session.setAttribute("AllActivitise", result);
			return "Activity.select";
		} else {
			errors.put("select", "查無資料，請重新查詢");
			return "actSelect.error";
		}
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/partner.do" })
	public String partnerprocess(HttpServletRequest request, Model model) throws ParseException {

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activitySelectErrors", errors);
		String activityno = request.getParameter("activityno");
		int actNo = 0;
		if (activityno != null && activityno != "") {
			actNo = Integer.parseInt(activityno);
		}

		Activity_memberBean man = new Activity_memberBean();
		Activity_memberPK memberBeanPK = new Activity_memberPK();
		memberBeanPK.setActivityno(actNo);
		man.setActivity_memberPK(memberBeanPK);

		List<Activity_memberBean> results = getActivity_memberService().findActivityMemberByActivityNo(man);

		if (results != null && !results.isEmpty()) {
			model.addAttribute("FindPartner", "搜尋成功");
			ActivityBean bean = new ActivityBean();
			bean.setActivityno(actNo);
			ActivityBean actbean = getActivityService().selectByActivityNO(bean);
			model.addAttribute("Activity", actbean);
			model.addAttribute("partner", results);
			return "Activity.partner";
		} else {
			errors.put("partner", "查無資料，請重新搜尋");
			return "actSelect.error";
		}
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/join.do" })
	public String partakeprocess(HttpServletRequest request, Model model) throws ParseException {
		String activitynostring = request.getParameter("activityno");
		String mIdstring = request.getParameter("mId");
		HttpSession session = request.getSession();
		int mId = Integer.parseInt(mIdstring);
		int activityno = Integer.parseInt(activitynostring);
		Activity_memberBean joinbean = new Activity_memberBean();
		joinbean.setActivity_memberPK(new Activity_memberPK(activityno, mId));		
		if(getActivity_memberService().findByPK(joinbean) != null){
			joinbean.setPosition("參與者");
			getActivity_memberService().changePosition(joinbean);
		} else{
			joinbean.setPosition("參與者");
			ActivityBean insertbean = new ActivityBean();
			insertbean.setActivityno(activityno);			
			joinbean.setActivityBean(getActivityService().selectByActivityNO(insertbean));
			joinbean.setMemberBean(getMemberService().selectByPrimaryKey(mId));
			getActivity_memberService().addNewActivityMember(joinbean);
		}
		Activity_memberBean bean = new Activity_memberBean();
		Activity_memberPK pk = new Activity_memberPK();
		pk.setmId(mId);
		bean.setActivity_memberPK(pk);
		List<Activity_memberBean> activities = getActivity_memberService().findActivityNoByMemberId(bean);
		Map<String, List<Activity_memberBean>> myAct = new HashMap<String, List<Activity_memberBean>>();
		List<Activity_memberBean> ready = new ArrayList<Activity_memberBean>();
		List<Activity_memberBean> notcommit = new ArrayList<Activity_memberBean>();
		List<Activity_memberBean> owner = new ArrayList<Activity_memberBean>();
		List<Activity_memberBean> old = new ArrayList<Activity_memberBean>();
		myAct.put("owner", owner);
		myAct.put("ready", ready);
		myAct.put("notcommit", notcommit);
		myAct.put("old", old);
		for (Activity_memberBean aaa : activities) {
			if (aaa.getActivityBean().getBegintime().getTime() < System.currentTimeMillis()) {
				old.add(aaa);
			} else {
				if ("待確認".equals(aaa.getPosition())) {
					notcommit.add(aaa);
				} else if ("發起人".equals(aaa.getPosition())) {
					owner.add(aaa);
				} else {
					ready.add(aaa);
				}
			}
		}
		session.setAttribute("myActivityList", myAct);
		return "Activity.join";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/myAct.do" })
	public String myActivity(HttpServletRequest request, Model model) throws ParseException {

		// 接收資料
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");

		Map<String, String> errors = new HashMap<String, String>();
		session.setAttribute("activitySelectErrors", errors);

		Activity_memberBean bean = new Activity_memberBean();
		Activity_memberPK pk = new Activity_memberPK();
		pk.setmId(user.getmId());
		bean.setActivity_memberPK(pk);
		List<Activity_memberBean> activities = getActivity_memberService().findActivityNoByMemberId(bean);
		
		System.out.println(activities);
		
		List<String> s_type = new ArrayList<>();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		Integer userId = memberBean.getmId();
		s_type.add("好友");
		List<SocialListBean> friendsBean = getSocialListService().selectAllFriend(userId, s_type);

		Map<String, List<Activity_memberBean>> myAct = new HashMap<String, List<Activity_memberBean>>();
		List<Activity_memberBean> ready = new ArrayList<Activity_memberBean>();
		List<Activity_memberBean> notcommit = new ArrayList<Activity_memberBean>();
		List<Activity_memberBean> owner = new ArrayList<Activity_memberBean>();
		List<Activity_memberBean> old = new ArrayList<Activity_memberBean>();
		myAct.put("owner", owner);
		myAct.put("ready", ready);
		myAct.put("notcommit", notcommit);
		myAct.put("old", old);
		for (Activity_memberBean aaa : activities) {
			if (aaa.getActivityBean().getBegintime().getTime() < System.currentTimeMillis()) {
				old.add(aaa);
			} else {
				if ("待確認".equals(aaa.getPosition())) {
					notcommit.add(aaa);
				} else if ("發起人".equals(aaa.getPosition())) {
					owner.add(aaa);
				} else {
					ready.add(aaa);
				}
			}
		}
		session.setAttribute("FriendList", friendsBean);
		session.setAttribute("myActivityList", myAct);
		return "Activity.default";
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/myAct.do" })
	public String doGetmyActivity(HttpServletRequest request, Model model) throws ParseException {
		return myActivity(request, model);
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/allAct.do" })
	public String selectAll(HttpServletRequest request, Model model) throws ParseException {
		HttpSession session = request.getSession();
		List<ActivityBean> results = getActivityService().nowActivitySelect();
		session.setAttribute("AllActivitise", results);
		return "Activity.AllActivitise";
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/allAct.do" })
	public String doGetselectAll(HttpServletRequest request, Model model) throws ParseException {
		return selectAll(request, model);
	}

}