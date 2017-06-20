package tw.com.eeit94.textile.controller.photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import tw.com.eeit94.textile.model.photo_album.Photo_albumBean;
import tw.com.eeit94.textile.model.photo_album.Photo_albumService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Controller
@EnableWebMvc
@RequestMapping(path = { "/photo" })
// @MultipartConfig(
// location="",
// fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
// maxFileSize = 1024 * 1024 * 10, // 10 MB
// maxRequestSize = 1024 * 1024 * 15 // 15 MB
// )
public class UploadController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private Photo_albumService photo_albumService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/upload.do" }, consumes = {
			"multipart/form-data;charset=UTF-8" })
	@ResponseBody
	public String process(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, Model model)
			throws IOException {		

		// 接收資料
		String photoname = request.getParameter("photoname");
		String interpretation = request.getParameter("interpretation");
		String position = request.getParameter("position");
		String visibility = request.getParameter("visibility");		
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		// int id = user.getmId();
		int id = 1;
		Photo_albumBean photo_albumbean = new Photo_albumBean();
		photo_albumbean.setAlbumno(1);
		Photo_albumBean albumbean = photo_albumService.findPhotoAlbumByAlbumNo(photo_albumbean);

		// 轉換資料
		// 驗證資料，給預設值
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute(errors);

		if (files == null || files.length == 0) {
			errors.put("file", "請選擇至少一張照片");
		}

		if (photoname == "") {
			errors.put("photoname", "請輸入照片名稱");
		}
		if (interpretation == "") {
			interpretation = "" + user.getmName() + "的照片";
		}

		if (errors != null && !errors.isEmpty()) {
			return "photo.error";
		}

		ServletContext context = request.getServletContext();
		String temdir = context.getRealPath("/album") + "/" + java.lang.String.valueOf(id);
		MultipartFile file = null;
		PhotoBean bean = new PhotoBean();
		bean.setAlbumno(id);
		bean.setInterpretation(interpretation);
		bean.setPhotoname(photoname);
		bean.setPosition(position);
		bean.setVisibility(visibility);
		bean.setPhoto_albumBean(albumbean);
		List<PhotoBean> result = new ArrayList<PhotoBean>();

		// 呼叫Model
		InputStream fis = null;
		FileOutputStream fos = null;
		String time = photoService.getTimeString();
		String memberIdString = photoService.getMemberIdString(id);
		int photos = photoService.countphoto(time + memberIdString);
		System.out.println(photos);
		StringBuilder sb = new StringBuilder();
		String tempno = "";
		PhotoBean insertBean = new PhotoBean();
		UID photo = null;
		File file2 = null;
		try {
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				photo = new UID();
				file2 = new File("" + temdir + "/" + photo.hashCode() + file.getOriginalFilename());
				if (!file2.getParentFile().exists()) {
					file2.getParentFile().mkdirs();
				}

				fis = file.getInputStream();
				fos = new FileOutputStream(file2, true);
				int data;
				while ((data = fis.read()) != -1) {
					fos.write(data);
				}
				String path = "/" + java.lang.String.valueOf(id) + "/" + photo.hashCode() + file.getOriginalFilename();
				bean.setRespath(path);
				tempno = sb.append("0000").append(photos).substring(sb.length() - 4, sb.length());
				String newphotono = time + memberIdString + tempno;
				bean.setPhotono(newphotono);
				insertBean = photoService.insertDataToPhoto(bean);
				if (insertBean != null) {
					result.add(insertBean);
					photos++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 根據Model執行結果呼叫View
		if (files.length == result.size()) {
			model.addAttribute("insert", result);
			return "photo.run";
		} else {
			return "photo.back";
		}

		// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
	}
}