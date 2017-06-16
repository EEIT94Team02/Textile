package tw.com.eeit94.textile.controller.announcement;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.id.IncrementGenerator;
import org.hibernate.loader.plan.build.spi.MetamodelDrivenLoadPlanBuilder;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.announcement.AnnouncementBean;
import tw.com.eeit94.textile.model.announcement.AnnouncementService;
import tw.com.eeit94.textile.model.member.MemberBean;

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
//	@ResponseBody
	public String process(HttpServletRequest request, AnnouncementBean bean, BindingResult bindingResult, 
			Model model)
			throws IOException {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);
		
		//接收資料
		if(bindingResult!=null && bindingResult.hasErrors()){
			if(bindingResult.getFieldError("startTime")!=null){
				errors.put("startTime", "startTime必須是擁有YYYY-MM-DD格式的日期");
			}
			if(bindingResult.getFieldError("endTime")!=null){
				errors.put("endTime", "endTime必須是擁有YYYY-MM-DD格式的日期");
			}
			
			}
			if(bean.getStartTime()==bean.getEndTime()){
			errors.put("startTime", "開始時間不能與結束時間相同,必須相隔至少一日");
			}
		
	
		
	
		//驗證資料
//		if("Insert".equals(announcement) ) {
//				if(temp1==null || temp1.length()==0){
//					errors.put("a_id", "請輸入id以便執行");
//			}
//			if(temp2==null || temp2.length()==0) {
//				errors.put("startTime", "請輸入開始時間以便執行");
//			}
//			if(temp3==null || temp3.length()==0) {
//				errors.put("endTime", "請輸入結束時間以便執行");
//			}
//			if(gist==null || gist.length()==0) {
//				errors.put("gist", "請輸入公告主旨以便執行");
//			}
//			if(msg==null || msg.length()==0) {
//				errors.put("msg", "請輸入公告內容以便執行");
//			}
//			if(a_type==null || a_type.length()==0) {
//				errors.put("a_type", "請選擇公告類別以便執行");
//			}
//			if(gist.length()>50){
//				errors.put("gist", "主旨不能超過50個字");
//			}
//			if(temp2==temp3){
//				errors.put("startTime", "開始時間不能等於結束時間,必須間隔至少一日");
//			}
//		}
//			
//			if("Update".equals(announcement)){
//				if(temp1==null || temp1.length()==0){
//					errors.put("a_id", "請輸入id以便執行");
//				}
//				if(temp2==temp3){
//					errors.put("startTime", "開始時間不能等於結束時間,必須間隔至少一日");
//				}
//			}
//			if (errors != null && !errors.isEmpty()) {
//				return "announcement.error";
//			}
//		
//		//轉換資料
//		int a_id=0;
//		try {
//			if (temp1 != null && temp1.length() != 0) {
//				a_id = Integer.parseInt(temp1);
//			} 
//		} catch (Exception e) {
//			e.printStackTrace();
//			errors.put("a_id", "id必須為整數");
//		}
//		
//		java.util.Date startTime=null;
//		if(temp2!=null && temp2.length()!=0){
//			try {
//				startTime=sdf.parse(temp2);
//			} catch (ParseException e) {
//				e.printStackTrace();
//				errors.put("startTime", "startTime必須為日期");
//			}
//			
//		}
//		
//		java.util.Date endTime=null;
//		if(temp3!=null && temp3.length()!=0){
//			try {
//				startTime=sdf.parse(temp3);
//			} catch (ParseException e) {
//				e.printStackTrace();
//				errors.put("endTime", "endTime必須為日期");
//			}
//			
//		}
//		
//		
//		AnnouncementBean bean=new AnnouncementBean();
//		bean.setA_id(a_id);
//		bean.setA_type(a_type);
//		bean.setGist(gist);
//		bean.setMsg(msg);
//		bean.setStartTime(startTime);
//		bean.setEndTime(endTime);
//		bean.setRelTime(new java.util.Date());
//		
//		//呼叫modle
//		
//		
//		
//		//根據Model執行結果呼叫View
//		if(announcement.equals(startTime)){
//			List<AnnouncementBean> result=announcementService.select(bean);
//			model.addAttribute("Select",result);
//			
//			return "announcement.v";
//		}else{
//			return "announcement.error";
//		}
		
		return "";
	
}
	
}
