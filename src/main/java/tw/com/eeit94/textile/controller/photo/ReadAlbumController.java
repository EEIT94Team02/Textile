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
	private SocialListService socailListService;

	public SocialListService getSocailListService() {
		return socailListService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/list.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String albumIndexProcess(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();
		Photo_albumBean bean = new Photo_albumBean();
		bean.setmId(userId);
		List<Photo_albumBean> index = getPhoto_albumService().findPhotoAlbumBymId(bean);
		model.addAttribute("AlbumList", index);
		return "album.list";
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
		} else {
			errors.put("seclectAlbum", "請輸入條件");
		}

		if (errors != null && !errors.isEmpty()) {
			return "select.album";
		}

		// 呼叫Model
		SocialListBean friendBean = new SocialListBean();
		Photo_albumBean bean = new Photo_albumBean();
		List<Photo_albumBean> photo_albumBeans = new ArrayList<Photo_albumBean>();
		List<Photo_albumBean> resultalbumBeans = new ArrayList<Photo_albumBean>();
		bean.setmId(memberId);
		photo_albumBeans = getPhoto_albumService().findPhotoAlbumBymId(bean);

		if (photo_albumBeans != null && !photo_albumBeans.isEmpty()) {
			if (memberId == userId) {
				resultalbumBeans = photo_albumBeans;
				model.addAttribute("AlbumList", resultalbumBeans);
				return "album.list";
			}
			for (Photo_albumBean aaa : photo_albumBeans) {
				if ("公開".equals(aaa.getVisibility())) {
					resultalbumBeans.add(aaa);
				} else {
					friendBean = getSocailListService().select(new SocialListPK(userId, memberId));
					if (friendBean != null && "好友".equals(friendBean.getS_type())) {
						resultalbumBeans.add(aaa);
					}
				}
			}
			if (resultalbumBeans != null && !resultalbumBeans.isEmpty()) {
				model.addAttribute("AlbumList", resultalbumBeans);
			} else {
				model.addAttribute("Albumresult", "找不到相簿，或沒有閱讀此相簿的權限");
			}
		} else {
			model.addAttribute("Albumresult", "找不到相簿，或沒有閱讀此相簿的權限");
		}
		// 根據Model執行結果呼叫View
		return "album.list";
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
			return "select.album";
		}

		// 呼叫Model
		List<Photo_albumBean> photo_albumBeans = new ArrayList<Photo_albumBean>();
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumno(albumno);
		Photo_albumBean albumBean = getPhoto_albumService().findPhotoAlbumByAlbumNo(bean);
		int memberId = 0;
		if (albumBean != null) {
			memberId = albumBean.getmId();
		} else {
			model.addAttribute("Albumresult", "找不到相簿，或沒有閱讀此相簿的權限");
			return "album.list";
		}
		if (memberId == userId) {
			photo_albumBeans.add(albumBean);
			model.addAttribute("AlbumList", photo_albumBeans);
		} else {
			SocialListBean friendBean = new SocialListBean();
			friendBean = getSocailListService().select(new SocialListPK(userId, memberId));
			String type = "";
			if (friendBean != null) {
				type = friendBean.getS_type();
			}
			if ("公開".equals(albumBean.getVisibility()) || albumBean.getVisibility().equals(type)) {
				photo_albumBeans.add(albumBean);
				model.addAttribute("AlbumList", photo_albumBeans);
			} else {
				model.addAttribute("Albumresult", "找不到相簿，或沒有閱讀此相簿的權限");
			}
		}
		// 根據Model執行結果呼叫View
		return "album.list";
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/search.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	public String searchProcess(HttpServletRequest request, Model model) throws ParseException {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		Photo_albumBean bean = new Photo_albumBean();
		model.addAttribute("selectAlbumErrors", errors);
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();
		String albumname = request.getParameter("albumname");
		String introduction = request.getParameter("introduction");
		String IdString = request.getParameter("mId");
		String createtime = request.getParameter("createtime");
		String visibility = request.getParameter("visibility");

		if (albumname == "" && introduction == "" && IdString == "" && createtime == "" && visibility == "") {
			errors.put("select", "請至少輸入一個條件");
		}

		// 驗證資料
		// 轉換資料
		int mId = 0;
		if (IdString != "" && IdString != null) {
			mId = Integer.parseInt(IdString);
			if (mId <= 0) {
				errors.put("mId", "請輸入正確的會員ID");
			}
		}
		if (errors != null && !errors.isEmpty()) {
			return "select.album";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date time = new java.util.Date();
		if (createtime != "" && createtime != null) {
			time = sdf.parse(createtime);
		} else {
			time = new java.util.Date(0);
		}
		System.out.println(time);
		// 呼叫Model
		bean.setAlbumname(albumname);
		bean.setCreatetime(new java.sql.Timestamp(time.getTime()));
		bean.setIntroduction(introduction);
		bean.setmId(mId);
		bean.setVisibility(visibility);
		System.out.println(bean);
		List<Photo_albumBean> check = getPhoto_albumService().findPhotoAlbumByOthers(bean);
		List<Photo_albumBean> result = new ArrayList<Photo_albumBean>();
		int hostmId = 0;
		for (Photo_albumBean aaa : check) {
			hostmId = aaa.getmId();
			SocialListBean friendBean = getSocailListService().select(new SocialListPK(userId, hostmId));
			if (userId == hostmId) {
				result.add(aaa);
			} else if ("公開".equals(aaa.getVisibility())) {
				result.add(aaa);
			} else if (friendBean != null && "好友".equals(friendBean.getS_type())) {
				result.add(aaa);
			}
		}
		// 根據Model執行結果呼叫View
		if (result != null && result.size() != 0) {
			model.addAttribute("AlbumList", result);
		} else {
			model.addAttribute("Albumresult", "找不到相簿，或沒有閱讀此相簿的權限");
		}
		return "album.list";
	}
}