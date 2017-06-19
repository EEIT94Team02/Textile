package tw.com.eeit94.textile.controller.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
@RequestMapping(path = { "/showphoto.v" })
public class ShowPhotoController {

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

	@RequestMapping(method = { RequestMethod.GET })
	public void process(HttpServletRequest request,HttpServletResponse response, Model model)
			throws IOException {
		// 接收資料
		String photono = request.getParameter("photono");	
		PhotoBean bean = new PhotoBean();
		bean.setPhotono(photono);
		PhotoBean result =  getPhotoService().selectByphotono(bean);
		FileInputStream fis = new FileInputStream(new File(result.getRespath()));
		response.setContentType("image/*");
		OutputStream os = response.getOutputStream();
		int data;
		while ((data = fis.read()) != -1) {
			os.write(data);
		}
		os.close();
		fis.close();
//			PhotoBean outputphoto = new PhotoBean();
//			outputphoto.setAlbumno(albumno);
//			List<PhotoBean> outputphotos = getPhotoService().selectByAlbumno(outputphoto);
//			System.out.println(outputphotos);			
//			for(PhotoBean bytes: outputphotos){
//				System.out.println("path= " +bytes.getRespath());
//				FileInputStream a = new FileInputStream(new File((bytes.getRespath())));
//				int size = a.available();
//				byte[] data = new byte[size];
//				a.read(data);
//				a.close();
//				OutputStream out =response.getOutputStream();
//				response.setContentType("image/*");
//				out.write(data);
//				out.close();				
//			}			

	}
}