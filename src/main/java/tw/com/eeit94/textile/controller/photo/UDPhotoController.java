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
@RequestMapping(path = { "/photo" })
public class UDPhotoController {
	@Autowired
	private PhotoService photoService;

	public PhotoService getPhotoService() {
		return photoService;
	}

	@Autowired
	private Photo_albumService photo_albumService;

	public Photo_albumService getPhoto_albumService() {
		return photo_albumService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/update.do" })
	public String seclectMIdProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("updatePhotoErrors", errors);
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");

		String photono = request.getParameter("photono");
		String albumString = request.getParameter("albumno");
		String photoname = request.getParameter("photoname");
		String interpretation = request.getParameter("interpretation");
		String position = request.getParameter("position");

		if (photono == null || photono == "") {
			errors.put("photono", "請確認要修改的照片");
		}
		if (errors != null && !errors.isEmpty()) {
			return "update.photo";
		}

		int albumno = 0;
		if (albumString != null && albumString != "") {
			albumno = Integer.parseInt(albumString);
		}
		Photo_albumBean photo_albumBean = new Photo_albumBean();
		photo_albumBean.setAlbumno(albumno);
		Photo_albumBean checkmember = getPhoto_albumService().findPhotoAlbumByAlbumNo(photo_albumBean);

		if (checkmember != null && checkmember.getmId() != user.getmId()) {
			errors.put("update", "您只能更新屬於您自己的照片");
			return "update.photo";
		}

		PhotoBean bean = new PhotoBean();
		bean.setPhotono(photono);
		PhotoBean check = getPhotoService().selectByphotono(bean);
		if (check != null) {
			bean.setAlbumno(albumno);
			bean.setInterpretation(interpretation);
			bean.setPhotoname(photoname);
			bean.setPosition(position);
			PhotoBean result = getPhotoService().updatePhotoinfo(bean);
			if (result != null) {
				List<PhotoBean> results = getPhotoService().selectByAlbumno(bean);
				model.addAttribute("PhotoList", results);
				return "photo.list";
			}
			errors.put("update", "更新失敗，請重新確認");
			return "update.photo";
		}
		errors.put("update", "更新失敗，請重新確認");
		return "update.photo";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/deleteAll.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String deleteProcess(HttpServletRequest request, Model model) {

		return "";
	}

}