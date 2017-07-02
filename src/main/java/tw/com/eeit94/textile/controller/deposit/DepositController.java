package tw.com.eeit94.textile.controller.deposit;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.deposit.DepositBean;
import tw.com.eeit94.textile.model.deposit.DepositConditionUtil;
import tw.com.eeit94.textile.model.deposit.DepositService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;

@Controller
@RequestMapping( path = { "/deposit" } )
@SessionAttributes( names= { "dList" } )
public class DepositController {
	@Autowired
	private DepositService depositService;
	@Autowired
	private MemberService memberService;
	
	@InitBinder
	public void initMethod(WebDataBinder binder) {
		CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(java.util.Date.class, customDateEditor);
	}
	
	public DepositService getDepositService() {
		return depositService;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}
	
	@RequestMapping( path = { "/dList.do" } )
	public String depositShow(HttpServletRequest request, DepositConditionUtil depositCondition, Model model) {
		HttpSession session = request.getSession();
		MemberBean userBean = (MemberBean) session.getAttribute("user");
		depositCondition.setMemberId(userBean.getmId());
		List<DepositBean> dList = getDepositService().select(depositCondition);
		model.addAttribute("dList", dList);
		return "dList.show";
	}
	
	@RequestMapping( path = { "/deposit.do" } )
	public String depositProcess(HttpServletRequest request, String serialNum, DepositBean depositBean, BindingResult bindingResult, Model model) {
		Map<String ,String> messages = new HashMap<>();
		model.addAttribute("depositMessages", messages);
		int dAmount = 0;
		int dPoints = 0;
		if (serialNum != null) {
			if (serialNum.length() != 31) {
				messages.put("serialNumError", "長度不足。");
			} else {
				switch(serialNum) {
					case "5k4ru.4a83g;41;sup6tj656u193m06" :
						dAmount = 100;
						break;
					case "u193j3g6m06cj04t/6xu;3193ug6m06":
						dAmount = 150;
						break;
					case "j6193t/6u3u2u03n4cjo41u042jigl3":
						dAmount = 500;
						break;
					case "g.sup6ufu0m065j4sup6e.4j4m6dj94":
						dAmount = 1000;
						break;
					case "vu,4284ekj6nzpvu;35k4xu;3fu0m06":
						dAmount = 2000;
						break;
					default:
						messages.put("serialNumError", "不明序號。");
						break;
				}
			}
		} else {
			messages.put("serialNumError", "見鬼。required欄位還能讓它用null傳進來controller，太神啦。");
		}
		if (messages != null && !messages.isEmpty()) {
			return "deposit.fail";
		}
		
		// 修改會員所有點數資料
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		dPoints = (int) (dAmount*1.4);
		memberBean.setmPoints(Integer.valueOf(memberBean.getmPoints().intValue() + dPoints));
		getMemberService().update(memberBean);
		
		// 新增儲值記錄
		depositBean.setMemberBean(memberBean);
		depositBean.setDepositDate(new Timestamp(System.currentTimeMillis()));
		depositBean.setDepositAmount(Integer.valueOf(dAmount));
		depositBean.setVirtualPoints(Integer.valueOf(dPoints));
		getDepositService().insert(depositBean);
		messages.put("depositSuccess", "儲值成功");
		return "deposit.success";
	}
}
