package tw.com.eeit94.textile.controller.socail;

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

import tw.com.eeit94.textile.model.socail.SocailListBean;
import tw.com.eeit94.textile.model.socail.SocailListService;

/**
 * 
 * 
 * @author 周
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/socail/socail.do" }, produces = { "application/json;charset=UTF-8" })
public class SocaiListController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@Autowired
	public SocailListService socailListService;

	@RequestMapping(method = { RequestMethod.POST })
	public String process(HttpServletRequest request, SocailListBean bean, BindingResult bindingResult, Model model,
			String socailList) throws IOException {
		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);
		// 轉換資料
		if (bindingResult != null && bindingResult.hasErrors()) {
			if (bindingResult.getFieldError("SocailListPK") != null) {
				errors.put("SocailListPK", "SocailListPK必須為整數");
			}
			if (bindingResult.getFieldError("log_in") != null) {
				errors.put("log_in", "log_in必須是擁有YYYY-MM-DD格式的日期");
			}

		}

		// 驗證資料
		if ("Delete".equals(socailList)) {
			if (bean != null && bean.getSocailListPK().getAcquaintenceId() == 0) {
				errors.put("SocailListPK", "請輸入朋友Id以便執行" + socailList);
			}
		}
		if ("Insert".equals(socailList)) {
			if (bean != null && bean.getSocailListPK().getAcquaintenceId() == 0) {
				errors.put("SocailListPK", "請輸入朋友Id以便執行" + socailList);
			}
			if (bean != null && bean.getS_group().length() > 10) {
				errors.put("S_group()", "群組名稱不能大於10個字" + socailList);
			}
		}

		if (errors != null && !errors.isEmpty()) {
			return "SocailList.error";
		}

		// 呼叫Model, 根據Model執行結果呼叫View
		if ("Select".equals(socailList)) {
			List<SocailListBean> result = socailListService.selects();
			model.addAttribute("selects" + result);
			return "socaci.v";
		} else if ("Insert".equals(socailList)) {
			bean.setS_type("未確認");
			SocailListBean result = socailListService.insert(bean);

			if (result == null) {
				errors.put("insert", "insert失敗");
			} else {
				model.addAttribute("insert" + result);
			}
			return "socaciList.error";

		}  
		
		
		if ("Yes".equals(socailList)) {
			bean.setS_type("好友");
			SocailListBean result = socailListService.insert(bean);
			SocailListBean update = socailListService.updateTheFriend(bean);
			if (result == null || update == null) {
				errors.put("insert", "insert失敗");
				errors.put("updateTheFriend", "updateTheFriend失敗");
			} else {
				model.addAttribute("update" + update);
				model.addAttribute("insert" + result);
			}
			return "socaciList.error";

		} else if ("NO".equals(socailList)) {
			boolean result = socailListService.refuseDelete(bean);
			if (result) {
				model.addAttribute("delete", 1);
			} else {
				model.addAttribute("delete", 0);
			}
			return "socaciList.error";
		}else {
			errors.put("action", "Unknown Action:"+socailList);
			return "socaciList.error";
		}

	}

}
