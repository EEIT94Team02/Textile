package tw.com.eeit94.textile.controller.photo;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoService;


/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@EnableWebMvc
@RequestMapping(path = { "/photo/upload.do"})
//@MultipartConfig(
//		location="",
//        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
//        maxFileSize         = 1024 * 1024 * 10, // 10 MB
//        maxRequestSize      = 1024 * 1024 * 15 // 15 MB
//)
public class UploadController {
	
	@Autowired
	private PhotoService photoService;		

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(method = { RequestMethod.POST }, consumes = {"multipart/form-data; charset=UTF-8"})
	@ResponseBody
	public String process(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, Model model, Writer out)
			throws IOException {
	
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int id = user.getmId();	
		
		ServletContext context = request.getServletContext();
        String temdir = context.getRealPath("/album")+ String.valueOf(id);		
		File target = new File(temdir);
		File file = null;
		
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);
		
		String photoname = request.getParameter("photoname");
		String interpretation = request.getParameter("interpretation");
		String position = request.getParameter("position");
		String visibility = request.getParameter("visibility");
		PhotoBean bean = new PhotoBean();
		bean.setAlbumno(id);
		bean.setInterpretation(interpretation);
		bean.setPhotoname(photoname);
		bean.setPosition(position);
		bean.setVisibility(visibility);

		// 轉換資料
		// 驗證資料
		if (bean == null || bean.getPhotoname() == "") {
			errors.put("photoname", "請輸入照片名稱");
		}

		if (errors != null && !errors.isEmpty()) {
			return "product.error";
		}

		// 呼叫Model
		List<PhotoBean> uploadResult = photoService.uploadPhoto(files, target,bean);

		// 根據Model執行結果呼叫View
		if(files.length == uploadResult.size()){
			model.addAttribute("insert", uploadResult);
			return "";
		}else{
			return "";
		}
		
		


		// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
	}
}