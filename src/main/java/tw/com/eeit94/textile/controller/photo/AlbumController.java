package tw.com.eeit94.textile.controller.photo;

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
		Photo_albumBean result = getPhoto_albumService().createPhotoAlbum(bean);
		System.out.println(result);

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("create", "創建相簿失敗");
			return "album.error";
		} else {
			model.addAttribute("albumbean", getPhoto_albumService().findPhotoAlbumBymId(bean));
			return "album.create";
		}

	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/default.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String seclectprocess(HttpServletRequest request, Model model) {

		// 接收資料
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		String idString = request.getParameter("mId");
		int memberId = user.getmId();

		// 轉換資料
		// 驗證資料
		if (idString != null && idString.length() != 0) {
			memberId = Integer.parseInt(idString);
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setmId(memberId);
		List<Photo_albumBean> photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);

		// 根據Model執行結果呼叫View
		model.addAttribute("albumbean", photo_albumBeans);
		return "album.default";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String updateprocess(HttpServletRequest request, Model model) {
		
		

		// 根據Model執行結果呼叫View
//		if (result == null) {
//			errors.put("create", "更新資訊失敗");
//			return "album.error";
//		} else {
//			model.addAttribute("albumbean", getPhoto_albumService().findPhotoAlbumBymId(bean));
//			return "album.update";
//		}
		return "";
	}

}