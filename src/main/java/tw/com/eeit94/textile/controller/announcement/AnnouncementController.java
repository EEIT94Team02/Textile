package tw.com.eeit94.textile.controller.announcement;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.dialect.function.StaticPrecisionFspTimestampFunction;
import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationParameter;
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
@RequestMapping(path = { "/announcement" })
public class AnnouncementController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping(method = { RequestMethod.POST }, path = { "/insert.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String insertprocess(HttpServletRequest request, Model model) throws IOException {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("AnnouncementInsertErrors", errors);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// String Announcementid = request.getParameter("a_id");
		String Announcementtype = request.getParameter("a_type");
		String Announcementgist = request.getParameter("gist");
		String Announcementmsg = request.getParameter("msg");
		String AnnouncementstartTime = request.getParameter("startTime");
		String AnnouncementendTime = request.getParameter("endTime");

		if (Announcementtype == null || Announcementtype == "") {
			errors.put("Announcementtype", "請選擇公告種類");
		}
		if (Announcementgist == null || Announcementgist == "") {
			errors.put("Announcementgist", "主旨不能為空!");
		}
		if (Announcementmsg == null || Announcementmsg == "") {
			errors.put("Announcementmsg", "內容不能為空!");
		}
		if (AnnouncementstartTime == null || AnnouncementstartTime == "") {
			errors.put("AnnouncementstartTime", "請輸入開始時間!");
		}
		if (AnnouncementendTime == null || AnnouncementendTime == "") {
			errors.put("AnnouncementendTime", "請輸入結束時間!");
		}
		if (AnnouncementstartTime.equals(AnnouncementendTime)) {
			errors.put("AnnouncementTime", "開始時間不能與結束時間相等");
		}
		try {
			if (sdf.parse(AnnouncementstartTime).after(sdf.parse(AnnouncementendTime))) {
				errors.put("AnnouncementTime", "開始時間不能小於結束時間");
			}
		} catch (ParseException e1) {
			System.out.println("error by AnnouncementTime");
			e1.printStackTrace();
		}

		if (errors != null && !errors.isEmpty()) {
			return "announcement.error";
		}
		AnnouncementBean bean = new AnnouncementBean();
		try {
			bean.setA_type(Announcementtype);
			bean.setGist(Announcementgist);
			bean.setMsg(Announcementmsg);
			bean.setStartTime(sdf.parse(AnnouncementstartTime));
			bean.setEndTime(sdf.parse(AnnouncementendTime));
			bean.setRelTime(new java.sql.Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			errors.put("time", "時間格式錯誤");
		}
		AnnouncementBean result = announcementService.insert(bean);
		if (result == null) {
			errors.put("insert", "新增公告失敗");
			return "announcement.error";
		} else {
			model.addAttribute("insertOK", "新增公告成功");
			model.addAttribute("insert", result);
			return "announcement.success";
		}

	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/list.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String searchprocess(HttpServletRequest request, Model model) throws IOException {
		Map<String, String> errors = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		AnnouncementBean bean = new AnnouncementBean();
		model.addAttribute("AnnouncementSearchErrors", errors);
		String Announcementid = request.getParameter("A_ia");
		String Announcementtype = request.getParameter("a_type");
		String Announcementgist = request.getParameter("gist");
		String AnnouncementstartTime = request.getParameter("startTime");
		int a_id = 0;
		// 轉換資料
		if (Announcementid != null && Announcementid != "") {
			a_id = Integer.parseInt(Announcementid);
		}
		if (errors != null && !errors.isEmpty()) {
			return "alist.error";
		}
		bean.setA_id(a_id);
		bean.setA_type(Announcementtype);
		bean.setGist(Announcementgist);

		try {
			bean.setStartTime(sdf.parse(AnnouncementstartTime));
		} catch (Exception e) {
			errors.put("timeerror", "時間格式錯誤");
		}

		List<AnnouncementBean> result = announcementService.selectByBeginTime(bean);

		if (result == null) {
			errors.put("selecterror", "查詢公告失敗");
			return "alist.error";
		} else {
			model.addAttribute("selectOK", "查詢公告成功");
			model.addAttribute("select", result);
			return "alist.show";
		}

	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String updateprocess(HttpServletRequest request, Model model) throws IOException {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("AnnouncementUpdateErrors", errors);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String Announcementid = request.getParameter("a_id");
		String Announcementtype = request.getParameter("a_type");
		String Announcementgist = request.getParameter("gist");
		String Announcementmsg = request.getParameter("msg");
		String AnnouncementstartTime = request.getParameter("startTime");
		String AnnouncementendTime = request.getParameter("endTime");

		if (Announcementtype == null || Announcementtype == "") {
			errors.put("Announcementtype", "請選擇公告種類");
		}
		if (Announcementgist == null || Announcementgist == "") {
			errors.put("Announcementgist", "主旨不能為空!");
		}
		if (Announcementmsg == null || Announcementmsg == "") {
			errors.put("Announcementmsg", "內容不能為空!");
		}
		if (AnnouncementstartTime == null || AnnouncementstartTime == "") {
			errors.put("AnnouncementstartTime", "請輸入開始時間!");
		}
		if (AnnouncementendTime == null || AnnouncementendTime == "") {
			errors.put("AnnouncementendTime", "請輸入結束時間!");
		}
		if (AnnouncementstartTime.equals(AnnouncementendTime)) {
			errors.put("AnnouncementTime", "開始時間不能與結束時間相等");
		}
		try {
			if (sdf.parse(AnnouncementstartTime).after(sdf.parse(AnnouncementendTime))) {
				errors.put("AnnouncementTime", "開始時間不能小於結束時間");
			}
		} catch (ParseException e1) {
			System.out.println("error by AnnouncementTime");
			e1.printStackTrace();
		}
		if (errors != null && !errors.isEmpty()) {
			return "alist.error";
		}

		if (errors != null && !errors.isEmpty()) {
			return "announcement.error";
		}
		AnnouncementBean bean = new AnnouncementBean();
		try {
			bean.setA_type(Announcementtype);
			bean.setGist(Announcementgist);
			bean.setMsg(Announcementmsg);
			bean.setStartTime(sdf.parse(AnnouncementstartTime));
			bean.setEndTime(sdf.parse(AnnouncementendTime));
			bean.setRelTime(new java.sql.Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			errors.put("time", "時間格式錯誤");
		}
		AnnouncementBean result = announcementService.insert(bean);
		if (result == null) {
			errors.put("insert", "新增公告失敗");
			return "announcement.error";
		} else {
			model.addAttribute("insertOK", "新增公告成功");
			model.addAttribute("insert", result);
			return "announcement.success";
		}

	}

}