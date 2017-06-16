package tw.com.eeit94.textile.controller.photo;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.com.eeit94.textile.model.photo.PhotoBean;
import tw.com.eeit94.textile.model.photo.PhotoService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/photo/upload.do" }, produces = { "application/json; charset=UTF-8" })
public class UploadController {
	@Autowired
	private PhotoService photoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public String process(PhotoBean bean, BindingResult bindingResult, Model model, Writer out, File file)
			throws IOException {
		// 接收資料

		File rootpath = null;

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);
		// 轉換資料
		// 驗證資料
		if (bean == null || bean.getPhotoname() == "") {
			errors.put("photoname", "請輸入照片名稱");
		}

		if (errors != null && !errors.isEmpty()) {
			return "product.error";
		}

		// 呼叫Model
		int id = bean.getPhoto_albumBean().getmId();
		photoService.getTimeString();
		photoService.getMemberIdString(id);
		photoService.uploadPhoto(file, rootpath);
		photoService.insertDataToTable(bean);

		// 根據Model執行結果呼叫View
		
		
		

		return "";
		// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
	}
}