package tw.com.eeit94.textile.controller.photo;

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

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumService;
import tw.com.eeit94.textile.model.social.SocialListBean;
import tw.com.eeit94.textile.model.social.SocialListPK;
import tw.com.eeit94.textile.model.social.SocialListService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/photo/album" })
public class ReadAlbumController {
	@Autowired
	private Photo_albumService photo_albumService;
	public Photo_albumService getPhoto_albumService() {
		return photo_albumService;
	}

	@Autowired
	private SocialListService socialListService;
	public SocialListService getSocialListService() {
		return socialListService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/default.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String seclectMIdProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("selectAlbumErrors", errors);
		String IDstring = request.getParameter("mId");

		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();

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
		SocialListBean friendBean = new SocialListBean();
		Photo_albumBean bean = new Photo_albumBean();
		List<Photo_albumBean> photo_albumBeans = new ArrayList<Photo_albumBean>();
		bean.setmId(memberId);
		if (memberId == userId) {
			photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);
		} else {
			friendBean = getSocialListService().select(new SocialListPK(userId, memberId));
			String type = "";
			if (friendBean != null) {
				type = friendBean.getS_type();
			}
			bean.setVisibility(type);
			photo_albumBeans = getPhoto_albumService().findPhotoAlbumByOthers(bean);
		}
		// 根據Model執行結果呼叫View
		if (photo_albumBeans != null && !photo_albumBeans.isEmpty()) {
			model.addAttribute("AlbumList", photo_albumBeans);
		} else {
			errors.put("selecterror", "找不到相簿，或沒有閱讀此相簿的權限");
		}
		return "album.default";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/select.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String seclectAlbumProcess(HttpServletRequest request, Model model) {

		// 接收資料
		String albumnoString = request.getParameter("albumno");
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("selectAlbumErrors", errors);
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();

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
		List<Photo_albumBean> photo_albumBeans = new ArrayList<Photo_albumBean>();
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);
		Photo_albumBean albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);
		int memberId = albumBean.getmId();

		if (memberId == userId) {
			photo_albumBeans.add(getPhoto_albumService().findPhotoAlbumByAlbumNo(bean));
		} else {
			SocialListBean friendBean = new SocialListBean();
			friendBean = getSocialListService().select(new SocialListPK(userId, memberId));
			String type = "";
			if (friendBean != null) {
				friendBean.getS_type();
			}
			if ("公開".equals(albumBean.getVisibility()) || albumBean.getVisibility().equals(type)) {
				photo_albumBeans.add(albumBean);
			} else {
				errors.put("selecterror", "找不到相簿，或沒有閱讀此相簿的權限");
			}
		}
		// 根據Model執行結果呼叫View
		model.addAttribute("AlbumList", photo_albumBeans);
		return "album.default";
	};

	@RequestMapping(method = { RequestMethod.POST }, path = { "/search.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String searchProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		SocialListBean friendBean = new SocialListBean();
		Photo_albumBean bean = new Photo_albumBean();
		model.addAttribute("selectAlbumErrors", errors);
		String albumname = request.getParameter("albumname");
		String introduction = request.getParameter("introduction");
		String IdString = request.getParameter("mId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();

		// 驗證資料
		// 轉換資料
		int mId = 0;
		if (IdString != "" && IdString != null) {
			mId = Integer.parseInt(IdString);
			friendBean = getSocialListService().select(new SocialListPK(userId, mId));
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

		String type = null;
		if (friendBean != null) {
			type = friendBean.getS_type();
		} else {
			type = "";
		}

		bean.setAlbumname(albumname);
		bean.setCreatetime(new java.sql.Timestamp(time.getTime()));
		bean.setIntroduction(introduction);
		bean.setmId(mId);
		bean.setVisibility(type);
		List<Photo_albumBean> result = getPhoto_albumService().findPhotoAlbumByOthers(bean);

		// 根據Model執行結果呼叫View
		if (result != null && result.size() != 0) {
			model.addAttribute("AlbumList", result);
		} else {
			errors.put("selecterror", "找不到相簿，或沒有閱讀此相簿的權限");
		}
		return "album.default";
	}

}