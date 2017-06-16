package tw.com.eeit94.textile.controller.social;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.social.SocialListBean;
import tw.com.eeit94.textile.model.social.SocialListService;

/**
 * 
 * 
 * @author 周
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/social/social.do" } )
public class SocialListController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@Autowired
	public SocialListService socialListService;

	@RequestMapping(method = { RequestMethod.POST })
	public String process(HttpServletRequest request, SocialListBean bean, BindingResult bindingResult, Model model,
			String socialList) throws IOException {
		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);
		// 轉換資料
		if (bindingResult != null && bindingResult.hasErrors()) {
			if (bindingResult.getFieldError("SocialListPK") != null) {
				errors.put("SocialListPK", "SocialListPK必須為整數");
			}
			if (bindingResult.getFieldError("log_in") != null) {
				errors.put("log_in", "log_in必須是擁有YYYY-MM-DD格式的日期");
			}

		}

		// 驗證資料
		if ("Delete".equals(socialList)) {
			if (bean != null && bean.getSocialListPK().getAcquaintenceId() == 0) {
				errors.put("socialListPK", "請輸入朋友Id以便執行" + socialList);
			}
		}
		if ("Insert".equals(socialList)) {
			if (bean != null && bean.getSocialListPK().getAcquaintenceId() == 0) {
				errors.put("socialListPK", "請輸入朋友Id以便執行" + socialList);
			}
			if (bean != null && bean.getS_group().length() > 10) {
				errors.put("S_group()", "群組名稱不能大於10個字" + socialList);
			}
		}

		if (errors != null && !errors.isEmpty()) {
			return "social.error";
		}

		// 呼叫Model, 根據Model執行結果呼叫View
		if ("Select".equals(socialList)) {
			List<SocialListBean> result = socialListService.selects();
			model.addAttribute("selects" + result);
			return "social.v";
		} else if ("Insert".equals(socialList)) {
			bean.setS_type("未確認");
			SocialListBean result = socialListService.insert(bean);

			if (result == null) {
				errors.put("insert", "insert失敗");
			} else {
				model.addAttribute("insert" + result);
			}
			return "social.error";

		}  
		
		
		if ("Yes".equals(socialList)) {
			bean.setS_type("好友");
//			MemberBean mbean= new MemberBean();
//			Integer mId=mbean.getmId();
			SocialListBean result = socialListService.insert(bean);
			SocialListBean update = socialListService.updateTheFriend(bean);
			if (result == null || update==null) {
				if(result == null){
					errors.put("insert", "insert失敗");
				}
				if(update ==null){
					errors.put("updateTheFriend", "updateTheFriend失敗");
				}
			} else {
				model.addAttribute("update" + update);
				model.addAttribute("insert" + result);
			}
			return "social.error";

		} else if ("NO".equals(socialList)) {
			boolean result = socialListService.refuseDelete(bean);
			if (result) {
				model.addAttribute("delete", 1);
			} else {
				model.addAttribute("delete", 0);
			}
			return "social.error";
		}else {
			errors.put("action", "Unknown Action:"+socialList);
			return "social.error";
		}

	}

}
