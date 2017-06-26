package tw.com.eeit94.textile.controller.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.activity.ActivityService;
import tw.com.eeit94.textile.model.activity_member.Activity_memberBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberPK;
import tw.com.eeit94.textile.model.activity_member.Activity_memberService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;

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
	private MemberService memberService;

	public MemberService getMemberService() {
		return memberService;
	}
	
	@Autowired
	private Activity_memberService activity_memberService;

	public Activity_memberService getActivity_memberService() {
		return activity_memberService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/invite.do" })
	public String createprocess(HttpServletRequest request, Model model) throws ParseException {

		String activitynostring = request.getParameter("activityno");
		String mIdstring = request.getParameter("mId");
		int mId = Integer.parseInt(mIdstring);
		int activityno = Integer.parseInt(activitynostring);
		Activity_memberBean joinbean = new Activity_memberBean();
		ActivityBean aaa =new ActivityBean();
		aaa.setActivityno(activityno);		
		System.out.println(aaa);
		joinbean.setActivity_memberPK(new Activity_memberPK(activityno, mId));
		if(getActivity_memberService().findByPK(joinbean) != null){
			
		}
		
		
		joinbean.setPosition("待確認");
		joinbean.setActivityBean(getActivityService().selectByActivityNO(aaa));
		joinbean.setMemberBean(getMemberService().selectByPrimaryKey(mId));	
		
		System.out.println((getActivity_memberService().findByPK(joinbean)));
		
		getActivity_memberService().addNewActivityMember(joinbean);
		if(getActivity_memberService().addNewActivityMember(joinbean) == null){
			System.out.println("aaa");
		}
		
		Activity_memberBean man = new Activity_memberBean();
		Activity_memberPK memberBeanPK = new Activity_memberPK();
		memberBeanPK.setActivityno(activityno);
		man.setActivity_memberPK(memberBeanPK);
		List<Activity_memberBean> results = getActivity_memberService().findActivityMemberByActivityNo(man);
		if (results != null && !results.isEmpty()) {
			model.addAttribute("FindPartner", "搜尋成功");
			ActivityBean bean = new ActivityBean();
			bean.setActivityno(activityno);
			ActivityBean actbean = getActivityService().selectByActivityNO(bean);
			model.addAttribute("Activity", actbean);
			model.addAttribute("partner", results);
			return "Activity.partner";
		} else {
			return "actSelect.error";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/secede.do" })
	public String dosecede(HttpServletRequest request, Model model) throws ParseException {
		String activitynostring = request.getParameter("activityno");
		String mIdstring = request.getParameter("mId");
		HttpSession session = request.getSession();
		int mId = Integer.parseInt(mIdstring);
		int activityno = Integer.parseInt(activitynostring);
		Activity_memberBean deletebean = new Activity_memberBean();
		deletebean.setActivity_memberPK(new Activity_memberPK(activityno, mId));
		getActivity_memberService().deleteByActivityNo(deletebean);
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
		return "Activity.delete";
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/secede.do" })
	public String doGetsecede(HttpServletRequest request, Model model) throws ParseException {
		return dosecede(request, model);
	}

}