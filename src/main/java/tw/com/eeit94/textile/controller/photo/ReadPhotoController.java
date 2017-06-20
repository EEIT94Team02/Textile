package tw.com.eeit94.textile.controller.photo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/photo" })
public class ReadPhotoController {
	@Autowired
	private PhotoService photoService;

	public PhotoService getPhotoService() {
		return photoService;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/list.do" })
	public String seclectMIdProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("selectAlbumErrors", errors);
		String albumnoString = request.getParameter("albumno");

		int albumno = 0;
		if (albumnoString != null && albumnoString != "") {
			albumno = Integer.parseInt(albumnoString);
		}
		if (errors != null && !errors.isEmpty()) {
			return "select.photo";
		}
		PhotoBean bean = new PhotoBean();
		bean.setAlbumno(albumno);
		List<PhotoBean> results = getPhotoService().selectByAlbumno(bean);
		model.addAttribute("PhotoList", results);
		return "photo.list";
	}

	@RequestMapping(method = { RequestMethod.GET }, path = { "/list.do" })
	public String doGetProcess(HttpServletRequest request, Model model) {
		return seclectMIdProcess(request, model);
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/search.do" })
	public String searchProcess(HttpServletRequest request, Model model) {

		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("searchPhotoErrors", errors);
		String photoname = request.getParameter("photoname");
		String interpretation = request.getParameter("interpretation");
		String position = request.getParameter("position");
		
		if (photoname == "" && interpretation == "" && position == "") {
			errors.put("search", "請至少輸入一個條件");
		}

		if (errors != null && !errors.isEmpty()) {
			return "search.photo";
		}
		PhotoBean bean = new PhotoBean();
		bean.setPhotoname(photoname);
		bean.setPosition(position);
		bean.setInterpretation(interpretation);

		List<PhotoBean> results = getPhotoService().selectByOthers(bean);
		if(results != null && !results.isEmpty()){
			model.addAttribute("PhotoList", results);
			return "photo.list";
		} else{
			errors.put("search", "查無資料，請重新查詢");
			return "search.photo";
		}
		

	}

}