package tw.com.eeit94.textile.controller.photo;

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
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoService;
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
public class CUDAlbumController {
	@Autowired
	private Photo_albumService photo_albumService;

	public Photo_albumService getPhoto_albumService() {
		return photo_albumService;
	}
	
	@Autowired
	private PhotoService photoService;

	public PhotoService getPhotoService() {
		return photoService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/create.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String createprocess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumCRDErrors", errors);

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
			return "insert.album";
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

		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("create", "創建相簿失敗");
			return "insert.album";
		} else {
			model.addAttribute("Albumresult", "新建相簿成功");
			model.addAttribute("AlbumList", getPhoto_albumService().findPhotoAlbumBymId(result));
			return "album.list";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String updateprocess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumCRDErrors", errors);
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userID = user.getmId();

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
			return "update.album";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);

		// 用相簿編號找到要修改的相簿資料
		Photo_albumBean photo_albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);
		Photo_albumBean result = null;
		// 修改相簿資訊
		if (photo_albumBean.getmId() == userID) {
			photo_albumBean.setAlbumname(albumname);
			photo_albumBean.setIntroduction(introduction);
			photo_albumBean.setVisibility(visibility);
			result = getPhoto_albumService().ChangePhotoAlbumColumn(photo_albumBean);
		} else {
			errors.put("update", "您只能更新屬於您自己的相簿");
			return "update.album";
		}
		// 根據Model執行結果呼叫View
		if (result == null) {
			errors.put("update", "更新資訊失敗");
			return "update.album";
		} else {
			model.addAttribute("Albumresult", photo_albumBean.getAlbumname() + "相簿更新成功");
			model.addAttribute("AlbumList", (getPhoto_albumService().findPhotoAlbumBymId(result)));
		}
		return "album.list";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/delete.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String deleteprocess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("albumCRDErrors", errors);
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();
		String albumnoString = request.getParameter("albumno");

		// 轉換資料
		int albumno = 0;
		if (albumnoString != null && albumnoString != "") {
			albumno = Integer.parseInt(albumnoString);
		}
		if (errors != null && !errors.isEmpty()) {
			return "delete.album";
		}

		// 呼叫Model
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);

		// 用相簿編號找到要刪除的相簿資料，並刪除
		List<Photo_albumBean> photo_albumBeans = new ArrayList<Photo_albumBean>();
		Photo_albumBean photo_albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);

		// 確認相簿存不存在
		if (photo_albumBean == null) {
			errors.put("delete", "刪除失敗，請重新確認");
			return "delete.album";
		}

		// 判斷有無權限刪除
		boolean result = false;
		if (photo_albumBean.getmId() == userId) {
			PhotoBean photoBean = new PhotoBean();
			photoBean.setAlbumno(albumno);			
			getPhotoService().removeAllPhoto(photoBean);
			result = getPhoto_albumService().deletePhotoAlbum(photo_albumBean);
		} else {
			errors.put("delete", "刪除失敗,您只能刪除屬於您自己的相簿");
			return "delete.album";
		}

		// 根據Model執行結果呼叫View
		if (!result) {
			errors.put("delete", "刪除失敗" + photo_albumBean.getAlbumname() + ",請確認是否已相簿是否已清空");
			return "delete.album";
		} else {
			photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(photo_albumBean);
			model.addAttribute("Albumresult", "成功刪除一組相簿");
			model.addAttribute("AlbumList", photo_albumBeans);
			return "album.list";
		}
	}

//	@RequestMapping(method = { RequestMethod.POST }, path = { "/deleteAll.do" }, consumes = {
//			"application/x-www-form-urlencoded ; charset=UTF-8" })
//	public String deleteAllprocess(HttpServletRequest request, Model model) {
//
//		// 接收資料
//		HttpSession session = request.getSession();
//		MemberBean user = (MemberBean) session.getAttribute("user");
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("albumCRDErrors", errors);
//
//		// 轉換資料
//		// 驗證資料
//		int userId = user.getmId();
//		String IdStrnig = request.getParameter("mId");
//		int memberId = 0;
//		if (IdStrnig != null && IdStrnig != "") {
//			memberId = Integer.parseInt(IdStrnig);
//		} else {
//			errors.put("deleteAll", "刪除失敗, 請確認輸入的資料");
//		}
//		Photo_albumBean bean = new Photo_albumBean();
//		bean.setmId(memberId);
//		List<Photo_albumBean> photo_albumBeans = new ArrayList<Photo_albumBean>();
//		if (memberId != userId) {
//			errors.put("deleteAll", "刪除失敗,您只能刪除屬於您自己的相簿");
//			return "delete.album";
//		} else {
//			// 呼叫Model
//			// 用相簿編號找到要刪除的相簿資料，並刪除
//			photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);
//			// 根據Model執行結果呼叫View
//			if(photo_albumBeans != null && !photo_albumBeans.isEmpty()){
//				boolean result = false;
//				for (Photo_albumBean album : photo_albumBeans) {
//					result = getPhoto_albumService().deletePhotoAlbum(album);
//					if (!result) {
//						errors.put("deleteAll", "刪除失敗" + album.getAlbumname() + ",請確認是否已相簿是否已清空");
//						return "delete.album";
//					}
//				}
//				model.addAttribute("Albumresult", "刪除成功");
//				model.addAttribute("AlbumList", photo_albumBeans);
//			} else{
//				errors.put("deleteAll", "刪除失敗,請確認輸入的資料");
//				return "delete.album";
//			}	
//		}
//		return "album.list";
//	}
	
}