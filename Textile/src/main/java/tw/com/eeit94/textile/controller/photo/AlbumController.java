package tw.com.eeit94.textile.controller.photo;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(path = { "/album/create.do" }, produces = { "application/json; charset=UTF-8" })
public class AlbumController {
	@Autowired
	private Photo_albumService photo_albumService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public String process(HttpServletRequest request){
		// 接收資料

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors",errors);		
		
		String albumname = request.getParameter("albumname");
		String introduction = request.getParameter("introduction");
		String visibility = request.getParameter("visibility");
		HttpSession session = request.getSession();
		Object member = session.getAttribute("user");
		
		// 轉換資料
		// 驗證資料
		if (albumname == null || albumname == "") {
			errors.put("albumname", "請輸入相簿名稱");
		}
		if (introduction == null || introduction == "") {
			errors.put("introduction", "請簡述相簿用途或向其他人介紹您的相簿");
		}
		if (errors != null && !errors.isEmpty()) {
			return "product.error";
		}

		// 呼叫Model
		int id = ((MemberBean) member).getmId();
		Photo_albumBean bean = new Photo_albumBean();
		bean.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setAlbumname(albumname);
		bean.setIntroduction(introduction);
		bean.setVisibility(visibility);
		bean.setmId(id);		
		Photo_albumBean result = photo_albumService.createPhotoAlbum(bean);		
		if(result ==null){
			errors.put("create", "創建相簿失敗");
		}
		// 根據Model執行結果呼叫View
		
		
		
		

		return "";
		// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
	}
}