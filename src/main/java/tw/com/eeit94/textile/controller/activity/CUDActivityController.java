package tw.com.eeit94.textile.controller.activity;

import java.sql.Timestamp;
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

import tw.com.eeit94.textile.model.activity.ActivityBean;
import tw.com.eeit94.textile.model.activity.ActivityService;
import tw.com.eeit94.textile.model.activity_member.Activity_memberBean;
import tw.com.eeit94.textile.model.activity_member.Activity_memberPK;
import tw.com.eeit94.textile.model.activity_member.Activity_memberService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/activity" })
public class CUDActivityController {

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

	@RequestMapping(method = { RequestMethod.POST }, path = { "/create.do" }, consumes = {
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
		String visibility = request.getParameter("visibility");

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
		bean.setVisibility(visibility);
		ActivityBean result = getActivityService().createNewActivity(bean);

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("create", "活動創建失敗");
			return "actInsert.error";
		} else {
			Activity_memberBean man = new Activity_memberBean();
			man.setActivity_memberPK(new Activity_memberPK(result.getActivityno(), user.getmId()));
			man.setActivityBean(bean);
			man.setPosition("發起人");
			Activity_memberBean create = getActivity_memberService().addNewActivityMember(man);
			if (create != null) {
				model.addAttribute("actInsertOK", "新建相簿成功");
				model.addAttribute("ActivityList", getActivityService().customeSelect(bean, null));
				return "Activity.default";
			}
			errors.put("create", "活動創建失敗");
			return "actInsert.error";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String updateprocess(HttpServletRequest request, Model model) throws ParseException {

		// 接收資料
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("activityCRDErrors", errors);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String activityno = request.getParameter("activityno");
		String activityname = request.getParameter("activityname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String place = request.getParameter("place");
		String interpretation = request.getParameter("interpretation");
		String visibility = request.getParameter("visibility");

		// 驗證資料
		if (activityno == null || activityno == "") {
			errors.put("activityno", "請輸入活動編號");
		}
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
			return "actUpdate.error";
		}

		// 轉換資料
		int actNo = Integer.parseInt(activityno);
		java.sql.Timestamp begin = new java.sql.Timestamp(sdf.parse(begintime).getTime());
		java.sql.Timestamp end = new java.sql.Timestamp(sdf.parse(endtime).getTime());

		// 呼叫Model
		ActivityBean bean = new ActivityBean();
		bean.setActivityno(actNo);
		bean.setActivityname(activityname);
		bean.setBegintime(begin);
		bean.setEndtime(end);
		bean.setPlace(place);
		bean.setInterpretation(interpretation);
		bean.setVisibility(visibility);
		ActivityBean result = getActivityService().updateActivity(bean);

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("create", "活動更新失敗");
			return "actUpdate.error";
		} else {
			model.addAttribute("actUpdatetOK", "更新相簿成功");
			model.addAttribute("ActivityList", getActivityService().customeSelect(bean, null));
			return "Activity.default";
		}

	}

	// @RequestMapping(method = { RequestMethod.POST }, path = { "/delete.do" },
	// consumes = {
	// "application/x-www-form-urlencoded ; charset=UTF-8" })
	// public String deleteprocess(HttpServletRequest request, Model model) {

	// // 接收資料
	// Map<String, String> errors = new HashMap<String, String>();
	// model.addAttribute("albumCRDErrors", errors);
	// HttpSession session = request.getSession();
	// MemberBean user = (MemberBean) session.getAttribute("user");
	// int userId = user.getmId();
	// String albumnoString = request.getParameter("albumno");
	//
	// // 轉換資料
	// int albumno = 0;
	// if (albumnoString != null && albumnoString != "") {
	// albumno = Integer.parseInt(albumnoString);
	// }
	// if (errors != null && !errors.isEmpty()) {
	// return "delete.error";
	// }
	//
	// // 呼叫Model
	// Photo_albumBean bean = new Photo_albumBean();
	// bean.setAlbumno(albumno);
	//
	// // 用相簿編號找到要刪除的相簿資料，並刪除
	// List<Photo_albumBean> photo_albumBeans = new
	// ArrayList<Photo_albumBean>();
	// Photo_albumBean photo_albumBean =
	// getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);
	//
	// // 確認相簿存不存在
	// if (photo_albumBean == null) {
	// errors.put("delete", "刪除失敗，請重新確認");
	// return "delete.error";
	// }
	//
	// // 判斷有無權限刪除
	// boolean result = false;
	// if (photo_albumBean.getmId() == userId) {
	// result = getPhoto_albumService().deletePhotoAlbum(photo_albumBean);
	// } else {
	// errors.put("delete", "刪除失敗,您只能刪除屬於您自己的相簿");
	// return "delete.error";
	// }
	//
	// // 根據Model執行結果呼叫View
	// if (!result) {
	// errors.put("delete", "刪除失敗" + photo_albumBean.getAlbumname() +
	// ",請確認是否已相簿是否已清空");
	// return "delete.error";
	// } else {
	// photo_albumBeans.add(photo_albumBean);
	// model.addAttribute("albumdeleteOK", "成功刪除一組相簿");
	// model.addAttribute("AlbumList", photo_albumBeans);
	// return "album.default";
	// }
	// }
	//
	// @RequestMapping(method = { RequestMethod.POST }, path = { "/deleteAll.do"
	// },
	// consumes = {
	// "application/x-www-form-urlencoded ; charset=UTF-8" })
	// public String deleteAllprocess(HttpServletRequest request, Model model) {
	//
	// // 接收資料
	// HttpSession session = request.getSession();
	// MemberBean user = (MemberBean) session.getAttribute("user");
	// Map<String, String> errors = new HashMap<String, String>();
	// model.addAttribute("albumCRDErrors", errors);
	//
	// // 轉換資料
	// // 驗證資料
	// int userId = user.getmId();
	// String IdStrnig = request.getParameter("mId");
	// int memberId = 0;
	// if (IdStrnig != null && IdStrnig != "") {
	// memberId = Integer.parseInt(IdStrnig);
	// } else {
	// errors.put("deleteAll", "刪除失敗, 請確認輸入的資料");
	// }
	// Photo_albumBean bean = new Photo_albumBean();
	// bean.setmId(memberId);
	// List<Photo_albumBean> photo_albumBeans = new
	// ArrayList<Photo_albumBean>();
	// if (memberId != userId) {
	// errors.put("deleteAll", "刪除失敗,您只能刪除屬於您自己的相簿");
	// return "delete.error";
	// } else {
	// // 呼叫Model
	// // 用相簿編號找到要刪除的相簿資料，並刪除
	// photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);
	// // 根據Model執行結果呼叫View
	// if(photo_albumBeans != null && !photo_albumBeans.isEmpty()){
	// boolean result = false;
	// for (Photo_albumBean album : photo_albumBeans) {
	// result = getPhoto_albumService().deletePhotoAlbum(album);
	// if (!result) {
	// errors.put("deleteAll", "刪除失敗" + album.getAlbumname() +
	// ",請確認是否已相簿是否已清空");
	// return "delete.error";
	// }
	// }
	// System.out.println("bbbb");
	// model.addAttribute("albumdelete", "刪除成功");
	// model.addAttribute("AlbumList", photo_albumBeans);
	// } else{
	// errors.put("deleteAll", "刪除失敗,請確認輸入的資料");
	// return "delete.error";
	// }
	//
	// }
	// return "album.default";
	// }

}