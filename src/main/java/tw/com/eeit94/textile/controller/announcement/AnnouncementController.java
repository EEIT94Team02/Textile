package tw.com.eeit94.textile.controller.announcement;

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

import tw.com.eeit94.textile.model.announcement.AnnouncementBean;
import tw.com.eeit94.textile.model.announcement.AnnouncementService;

/**
 * 
 * 
 * @author 周
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/announcement/announcement.do" }, produces = { "application/json;charset=UTF-8" })
public class AnnouncementController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping(method = { RequestMethod.POST })
	// @ResponseBody
	public String process(HttpServletRequest request, AnnouncementBean bean, BindingResult bindingResult, Model model,
			String announcement) throws IOException {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);

		// 接收資料
		if (bindingResult != null && bindingResult.hasErrors()) {
			if (bindingResult.getFieldError("startTime") != null) {
				errors.put("startTime", "startTime必須是擁有YYYY-MM-DD格式的日期");
			}
			if (bindingResult.getFieldError("endTime") != null) {
				errors.put("endTime", "endTime必須是擁有YYYY-MM-DD格式的日期");
			}

			// 驗證資料

			if ("Delete".equals(announcement)) {
				if (bean != null && bean.getA_id() == null) {
					errors.put("A_id", "請輸入公告Id以便執行!" + announcement);
				}
			}
			if ("Insert".equals(announcement)) {
				if (bean != null && bean.getA_type() == null) {
					errors.put("A_type()", "請選擇公告類別以便執行!" + announcement);
				}
				if (bean != null && bean.getGist() == null) {
					errors.put("Gist", "請輸入主旨以便執行!" + announcement);
				}
				if (bean != null && bean.getMsg() == null) {
					errors.put("Msg", "公告內容不能為空白!" + announcement);
				}
				if (bean != null && bean.getStartTime() == bean.getEndTime()) {
					errors.put("StartTime", "公告開始時間不能與結束時間相同!" + announcement);
				}
			}

			if (errors != null && !errors.isEmpty()) {
				return "SocailList.error";
			}

			// 呼叫modle
			if ("Select".equals(announcement)) {
				List<AnnouncementBean> result = announcementService.select(bean);
				model.addAttribute("selects" + result);
				return "announcement.v";
			}
			if("Insert".equals(announcement)){
				AnnouncementBean result = announcementService.insert(bean);
				model.addAttribute("insert"+result);
				return "annoucement.v";
			}


		}
		return "";
	}
}