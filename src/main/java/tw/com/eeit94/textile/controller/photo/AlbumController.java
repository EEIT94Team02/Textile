package tw.com.eeit94.textile.controller.photo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@RequestMapping(path = { "/photo/album" })
public class AlbumController {
	@Autowired
	private Photo_albumService photo_albumService;

	public Photo_albumService getPhoto_albumService() {
		return photo_albumService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/create.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String createprocess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumInsertErrors", errors);

		String albumname = request.getParameter("albumname");
		String introduction = request.getParameter("introduction");
		String visibility = request.getParameter("visibility");
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");

		// 轉換資料
		// 驗證資料
		if (albumname == null || albumname == "") {
			errors.put("albumname", "請輸入相簿名稱");
		}
		if (introduction == null || introduction == "") {
			errors.put("introduction", "請簡述相簿用途或向其他人介紹您的相簿");
		}
		if (errors != null && !errors.isEmpty()) {
			return "album.error";
		}

		// 呼叫Model
		int id = user.getmId();
		Photo_albumBean bean = new Photo_albumBean();
		bean.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setAlbumname(albumname);
		bean.setIntroduction(introduction);
		bean.setVisibility(visibility);
		bean.setmId(id);
		System.out.println(getPhoto_albumService());
		Photo_albumBean result = getPhoto_albumService().createPhotoAlbum(bean);

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("create", "創建相簿失敗");
			return "insert.error";
		} else {
			model.addAttribute("insertOK", "新建相簿成功");
			model.addAttribute("AlbumList", getPhoto_albumService().findPhotoAlbumBymId(result));
			return "album.default";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String updateprocess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumupdateErrors", errors);

		String albumnoString = request.getParameter("albumno");
		String albumname = request.getParameter("albumname");
		String introduction = request.getParameter("introduction");
		String visibility = request.getParameter("visibility");

		int albumno = 0;
		// 轉換資料
		if (albumnoString != null && albumnoString != "") {
			albumno = Integer.parseInt(albumnoString);
		}

		// 驗證資料
		if (albumname == null || albumname == "") {
			errors.put("albumname", "請輸入相簿名稱");
		}
		if (introduction == null || introduction == "") {
			errors.put("introduction", "請簡述相簿用途或向其他人介紹您的相簿");
		}
		if (errors != null && !errors.isEmpty()) {
			return "album.error";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);

		// 用相簿編號找到要修改的相簿資料
		Photo_albumBean photo_albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);

		// 修改相簿資訊
		photo_albumBean.setAlbumname(albumname);
		photo_albumBean.setIntroduction(introduction);
		photo_albumBean.setVisibility(visibility);
		Photo_albumBean result = getPhoto_albumService().ChangePhotoAlbumColumn(photo_albumBean);
		System.out.println(result);

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("update", "更新資訊失敗");
			return "update.error";
		} else {
			model.addAttribute("updateOK", "更新資訊成功");
			model.addAttribute("AlbumList", getPhoto_albumService().findPhotoAlbumBymId(result));
			return "album.default";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/delete.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String deleteprocess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumupdateErrors", errors);

		String albumnoString = request.getParameter("albumno");
		int albumno = 0;
		// 轉換資料
		if (albumnoString != null && albumnoString != "") {
			albumno = Integer.parseInt(albumnoString);
		}

		if (errors != null && !errors.isEmpty()) {
			return "album.error";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);

		// 用相簿編號找到要刪除的相簿資料，並刪除
		Photo_albumBean photo_albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);
		boolean result = getPhoto_albumService().deletePhotoAlbum(photo_albumBean);
		System.out.println(result);

		// 根據Model執行結果呼叫View
		if (!result) {
			errors.put("delete", "刪除失敗" + photo_albumBean.getAlbumname() + ",請確認是否已相簿是否已清空");
			return "album.error";
		} else {
			model.addAttribute("albumdelete", "刪除成功");
			return "album.delete";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/deleteAll.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String deleteAllprocess(HttpServletRequest request, Model model) {

		// 接收資料
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int memberID = user.getmId();

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumdeleteErrors", errors);

		if (errors != null && !errors.isEmpty()) {
			return "album.error";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setmId(memberID);

		// 用相簿編號找到要刪除的相簿資料，並刪除
		boolean result = false;
		List<Photo_albumBean> photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);

		// 根據Model執行結果呼叫View
		for (Photo_albumBean album : photo_albumBeans) {
			result = getPhoto_albumService().deletePhotoAlbum(album);
			if (!result) {
				errors.put("deleteAll", "刪除失敗" + album.getAlbumname() + ",請確認是否已相簿是否已清空");
				return "album.error";
			}
		}
		model.addAttribute("albumdelete", "刪除成功");
		return "album.deleteAll";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/default.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String seclectMIdProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("seclectMIdErrors", errors);
		String IDstring = request.getParameter("mId");

		// 轉換資料
		// 驗證資料
		int memberId = 0;
		if (IDstring != null && IDstring != "") {
			memberId = Integer.parseInt(IDstring);
		}
		if (errors != null && !errors.isEmpty()) {
			return "album.error";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setmId(memberId);
		List<Photo_albumBean> photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);

		// 根據Model執行結果呼叫View
		model.addAttribute("AlbumList", photo_albumBeans);
		return "album.default";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/select.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String seclectAlbumProcess(HttpServletRequest request, Model model) {

		// 接收資料
		String albumnoString = request.getParameter("albumno");
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("seclectAlbumErrors", errors);

		int albumno = 0;
		// 轉換資料
		if (albumnoString == null || albumnoString == "") {
			errors.put("seclectAlbum", "請輸入條件");
		} else {
			albumno = Integer.parseInt(albumnoString);
		}

		// 驗證資料
		if (errors != null && !errors.isEmpty()) {
			return "album.error";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);
		Photo_albumBean photo_albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);

		// 根據Model執行結果呼叫View
		model.addAttribute("AlbumList", photo_albumBean);
		return "album.default";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/search.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String searchProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("searchErrors", errors);
		String albumname = request.getParameter("albumname");
		String introduction = request.getParameter("introduction");
		String visibility = request.getParameter("visibility");
		String IdString = request.getParameter("mId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		// 驗證資料
		// 轉換資料
		int mId = 0;
		if (IdString != "" && IdString != null) {
			mId = Integer.parseInt(IdString);
			if (mId <= 0) {
				errors.put("mId", "請輸入正確的會員ID");
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date time = new java.util.Date();
		try {
			time = sdf.parse(year + "-" + month + "-" + day);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumname(albumname);
		bean.setCreatetime(new java.sql.Timestamp(time.getTime()));
		bean.setIntroduction(introduction);
		bean.setmId(mId);
		bean.setVisibility(visibility);
		List<Photo_albumBean> result = getPhoto_albumService().findPhotoAlbumByOthers(bean);

		// 根據Model執行結果呼叫View
		if (result != null && result.size() != 0) {
			model.addAttribute("AlbumList", result);
		} else {
			model.addAttribute("AlbumList", "查無資料");
		}
		return "album.default";
	}

}