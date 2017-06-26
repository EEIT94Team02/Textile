package tw.com.eeit94.textile.controller.photo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumService;
import tw.com.eeit94.textile.model.secure.SecureService;
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
	private SecureService secureService;

	public SecureService getSecureService() {
		return secureService;
	}

	@Autowired
	private SocialListService socialListService;

	public SocialListService getSocialListService() {
		return socialListService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/list.do" }, consumes = {
			"application/x-www-form-urlencoded ; charset=UTF-8" })
	@ResponseStatus(value = HttpStatus.OK)
	public String albumIndexProcess(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int userId = user.getmId();
		Photo_albumBean bean = new Photo_albumBean();
		bean.setmId(userId);
		List<Photo_albumBean> index = getPhoto_albumService().findPhotoAlbumBymId(bean);
		if (index == null || index.isEmpty()) {
			session.setAttribute("Albumresult", "目前無相簿資訊");
		}
		String securemId = getSecureService().getEncryptedText(String.valueOf(userId), "mId");
		session.setAttribute("mysecuremId", securemId);
		for(Photo_albumBean each : index){
			each.setIntroduction(getSecureService().getEncryptedText(String.valueOf(each.getAlbumno()),"mId"));
		}		
		session.setAttribute("AlbumList", index);
		return "album.my";
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/list.do" })
	public String doGetProcess(HttpServletRequest request, Model model) throws Exception {
		return albumIndexProcess(request, model);
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/default.do" })
	public String seclectMIdProcess(HttpServletRequest request, Model model) throws Exception {
		// 接收資料
		HttpSession session = request.getSession();
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("selectAlbumErrors", errors);
		String IDstring = request.getParameter("mId");
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
				for(Photo_albumBean each : resultalbumBeans){
					each.setIntroduction(getSecureService().getEncryptedText(String.valueOf(each.getAlbumno()),"mId"));
				}
				model.addAttribute("AlbumList", resultalbumBeans);
				return "album.list";
			}
			for (Photo_albumBean aaa : photo_albumBeans) {
				if ("公開".equals(aaa.getVisibility())) {
					resultalbumBeans.add(aaa);
				} else {
					friendBean = getSocialListService().select(new SocialListPK(userId, memberId));
					if (friendBean != null && "好友".equals(friendBean.getS_type())) {
						resultalbumBeans.add(aaa);
					}
				}
			}
			if (resultalbumBeans != null && !resultalbumBeans.isEmpty()) {
				for(Photo_albumBean each : resultalbumBeans){
					each.setIntroduction(getSecureService().getEncryptedText(String.valueOf(each.getAlbumno()),"mId"));
				}
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

	@RequestMapping(method = { RequestMethod.POST }, path = { "/select.do" })
	public String seclectAlbumProcess(HttpServletRequest request, Model model) throws Exception {
		// 接收資料
		HttpSession session = request.getSession();
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("selectAlbumErrors", errors);
		String visibility = "公開";
		Photo_albumBean bean = new Photo_albumBean();
		bean.setAlbumname("");
		bean.setCreatetime(new Timestamp(0));
		bean.setIntroduction("");
		bean.setVisibility(visibility);
		bean.setmId(0);
		List<Photo_albumBean> check = getPhoto_albumService().findPhotoAlbumByOthers(bean);		
		if (check != null && check.size() != 0) {
			session.setAttribute("OthersAlbum", check);
			String secureAlbumno = "";
			for(Photo_albumBean each: check ){
				secureAlbumno = getSecureService().getEncryptedText(String.valueOf(each.getAlbumno()),"mId");
				each.setIntroduction(secureAlbumno);
			}
		} else {
			session.setAttribute("OthersAlbum", check);
		}
		// 根據Model執行結果呼叫View
		return "album.list";
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/select.do" })
	public String doGetseclectAlbum(HttpServletRequest request, Model model) throws Exception {
		return seclectAlbumProcess(request, model);
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/search.do" })
	public String searchProcess(HttpServletRequest request, Model model) throws Exception {

		// 接收資料
		HttpSession session = request.getSession();
		Map<String, String> errors = new HashMap<String, String>();
		Photo_albumBean bean = new Photo_albumBean();
		model.addAttribute("selectAlbumErrors", errors);
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
			SocialListBean friendBean = getSocialListService().select(new SocialListPK(userId, hostmId));
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
			for(Photo_albumBean each : result){
				each.setIntroduction(getSecureService().getEncryptedText(String.valueOf(each.getAlbumno()),"mId"));
			}
			model.addAttribute("AlbumList", result);
		} else {
			model.addAttribute("Albumresult", "找不到相簿，或沒有閱讀此相簿的權限");
		}
		return "album.list";
	}
}