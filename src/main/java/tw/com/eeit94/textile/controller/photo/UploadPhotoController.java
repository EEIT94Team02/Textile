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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class UploadPhotoController {

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

	@RequestMapping(method = { RequestMethod.POST }, path = { "/upload.do" }, consumes = {
			"multipart/form-data;charset=UTF-8" })
	public String process(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		// 接收資料
		String albumString = request.getParameter("albumno");
		String photoname = request.getParameter("photoname");
		String interpretation = request.getParameter("interpretation");
		String position = request.getParameter("position");
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int id = user.getmId();
		int albumno = 0;
		if (albumString != null && albumString != "") {
			albumno = Integer.parseInt(albumString);
		}
		Photo_albumBean photo_albumbean = new Photo_albumBean();
		photo_albumbean.setAlbumno(albumno);
		Photo_albumBean albumbean = getPhoto_albumService().findPhotoAlbumByAlbumNo(photo_albumbean);

		// 轉換資料
		// 驗證資料，給預設值
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("photoCRDErrors", errors);
		if (albumbean == null) {
			errors.put("albumno", "相簿不存在");
		}
		if (albumString == null || albumString == "") {
			errors.put("albumno", "請輸入相簿編號");
		}
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
			return "upload.photo";
		}

		String realpath = "/album/";
		ServletContext context = request.getServletContext();
		String temdir = context.getContextPath() + realpath + java.lang.String.valueOf(id);
		System.out.println("temdir=" + context.getContextPath());
		System.out.println("temdir=" + temdir);
		MultipartFile file = null;
		PhotoBean bean = new PhotoBean();
		bean.setAlbumno(albumno);
		bean.setInterpretation(interpretation);
		bean.setPhotoname(photoname);
		bean.setPosition(position);
		bean.setPhoto_albumBean(albumbean);
		List<PhotoBean> result = new ArrayList<PhotoBean>();

		// 呼叫Model
		InputStream fis = null;
		FileOutputStream fos = null;
		String time = getPhotoService().getTimeString();
		String memberIdString = getPhotoService().getMemberIdString(id);
		int photos = getPhotoService().countphoto(time + memberIdString);
		StringBuilder sb = new StringBuilder();
		String tempno = "";
		String fullname = "";
		String name = "";
		PhotoBean insertBean = new PhotoBean();
		UID photo = null;
		File file2 = null;
		try {
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				photo = new UID();
				fullname = file.getOriginalFilename();
				int a = fullname.lastIndexOf(".");
				name = fullname.substring(a, fullname.length());
				file2 = new File("" + temdir + "/" + photo.hashCode() + name);
				if (!file2.getParentFile().exists()) {
					file2.getParentFile().mkdirs();
				}
				fis = file.getInputStream();
				fos = new FileOutputStream(file2, true);
				int data;
				while ((data = fis.read()) != -1) {
					fos.write(data);
				}
				String path = file2.getPath();
				bean.setRespath(path);
				tempno = sb.append("0000").append(photos).substring(sb.length() - 4, sb.length());
				String newphotono = time + memberIdString + tempno;
				bean.setPhotono(newphotono);
				insertBean = getPhotoService().insertDataToPhoto(bean);
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
			PhotoBean albumPhoto = new PhotoBean();
			albumPhoto.setAlbumno(albumno);
			List<PhotoBean> all = getPhotoService().selectByOthers(albumPhoto);
			model.addAttribute("PhotoList", all);
			return "album.my";
		} else {
			model.addAttribute("PhotoResult", "上傳失敗");
			return "upload.photo";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, path = { "/photo.do" }, consumes = {
			"multipart/form-data;charset=UTF-8" })
	public String photoStickers(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		// 接收資料
		String albumString = request.getParameter("albumno");
		String photoname = request.getParameter("photoname");
		String interpretation = request.getParameter("interpretation");
		String position = request.getParameter("position");
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		int id = user.getmId();
		int albumno = 0;
		if (albumString != null && albumString != "") {
			albumno = Integer.parseInt(albumString);
		}
		Photo_albumBean photo_albumbean = new Photo_albumBean();
		photo_albumbean.setAlbumno(albumno);
		Photo_albumBean albumbean = getPhoto_albumService().findPhotoAlbumByAlbumNo(photo_albumbean);

		// 轉換資料
		// 驗證資料，給預設值
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("photoCRDErrors", errors);
		if (albumbean == null) {
			errors.put("albumno", "相簿不存在");
		}
		if (albumString == null || albumString == "") {
			errors.put("albumno", "請輸入相簿編號");
		}
		if (file == null || file.isEmpty()) {
			errors.put("file", "請選擇至少一張照片");
		}

		if (photoname == "") {
			errors.put("photoname", "請輸入照片名稱");
		}
		if (interpretation == "") {
			interpretation = "" + user.getmName() + "的照片";
		}

		if (errors != null && !errors.isEmpty()) {
			return "upload.photo";
		}

		String realpath = "/album/";
		ServletContext context = request.getServletContext();
		String temdir = context.getContextPath() + realpath + java.lang.String.valueOf(id);
		System.out.println("temdir=" + context.getContextPath());
		System.out.println("temdir=" + temdir);
		PhotoBean bean = new PhotoBean();
		bean.setAlbumno(albumno);
		bean.setInterpretation(interpretation);
		bean.setPhotoname(photoname);
		bean.setPosition(position);
		bean.setPhoto_albumBean(albumbean);
		List<PhotoBean> result = new ArrayList<PhotoBean>();

		// 呼叫Model
		InputStream fis = null;
		FileOutputStream fos = null;
		String time = getPhotoService().getTimeString();
		String memberIdString = getPhotoService().getMemberIdString(id);
		int photos = getPhotoService().countphoto(time + memberIdString);
		StringBuilder sb = new StringBuilder();
		String tempno = "";
		String fullname = "";
		String name = "";
		PhotoBean insertBean = new PhotoBean();
		UID photo = null;
		File file2 = null;
		try {
			photo = new UID();
			fullname = file.getOriginalFilename();
			int a = fullname.lastIndexOf(".");
			name = fullname.substring(a, fullname.length());
			file2 = new File("" + temdir + "/" + photo.hashCode() + name);
			if (!file2.getParentFile().exists()) {
				file2.getParentFile().mkdirs();
			}
			fis = file.getInputStream();
			fos = new FileOutputStream(file2, true);
			int data;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			String path = file2.getPath();
			bean.setRespath(path);
			tempno = sb.append("0000").append(photos).substring(sb.length() - 4, sb.length());
			String newphotono = time + memberIdString + tempno;
			bean.setPhotono(newphotono);
			insertBean = getPhotoService().insertDataToPhoto(bean);
			if (insertBean != null) {
				result.add(insertBean);
				photos++;
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
		if (!result.isEmpty()) {
			
			
			
			
			
			PhotoBean albumPhoto = new PhotoBean();
			albumPhoto.setAlbumno(albumno);
			List<PhotoBean> all = getPhotoService().selectByOthers(albumPhoto);
			model.addAttribute("PhotoList", all);
			return "photo.list";
		} else {
			model.addAttribute("PhotoResult", "上傳失敗");
			return "upload.photo";
		}
	}
}