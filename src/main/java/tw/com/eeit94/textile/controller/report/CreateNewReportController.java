package tw.com.eeit94.textile.controller.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.report.ReportBean;
import tw.com.eeit94.textile.model.report.ReportService;
import tw.com.eeit94.textile.model.reportimage.ReportImgBean;
import tw.com.eeit94.textile.model.reportimage.ReportImgService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */

@Controller
@EnableWebMvc
@RequestMapping(path = { "/report/createNewReport.do" })
public class CreateNewReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportImgService reportImgService;

	@RequestMapping(method = { RequestMethod.POST }, consumes = { "multipart/form-data; charset=UTF-8" })
	public String process(@RequestParam("file") MultipartFile[] files, ReportBean bean, String reptDetail,
			HttpServletRequest request, Model model) throws IOException {

		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		// 上線用
		 bean.setmId(memberBean.getmId());
		 int mId = memberBean.getmId();
		// 測試用
		// int mId = 24;

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		if (bean.getReptType() == null) {
			errors.put("reportType", "請選擇回報種類");
		}
		if (bean.getReptDetail().trim().isEmpty()) {
			errors.put("message", "請輸入回報內容");
		}

		if (errors != null && !errors.isEmpty()) {
			return "report.error";
		}

//		if (request.getParameter("reptType") == null) {
//			errors.put("reportType", "請選擇回報種類");
//		}
//		if (request.getParameter("reptDetail").trim().isEmpty()) {
//			errors.put("message", "請輸入回報內容2");
//		}

		if (errors != null && !errors.isEmpty()) {
			return "report.error";
		}

		// 新增回報測試用
//		ReportBean bean2 = new ReportBean();
//		bean2.setmId(mId);
//		bean2.setReptType(bean.getReptType());
//		bean2.setReptDetail(bean.getReptDetail());
//		System.out.println("會員名稱=" + bean2.getmId() + "回報狀態=" + bean2.getReptType() + "回報內容=" + bean2.getReptDetail());
//		ReportBean result = reportService.createNewReport(bean2);
//		System.out.println(result);
		//
		// ReportImgBean insertNewImg = reportImgService.InsertNewImg(imgBean);
		// 上線用
		ReportBean result = reportService.createNewReport(bean);
		List<ReportImgBean> imgPathList = new ArrayList<ReportImgBean>(); 
		//取得目前回報編號
		Integer imgReptNo = reportService.selectReptByMidTop(mId);
		if (result != null) {
			// 檔案上傳
			ServletContext context = request.getServletContext();
			// 取得路徑 
			Path pathAlbum = Paths.get("/album");
			String reptSystemdir = pathAlbum +"/"+ java.lang.String.valueOf(mId);
			String reptdir = context.getContextPath()+"/apache-tomcat-8.5.15/wtpwebapps/Textile/album/"+ java.lang.String.valueOf(mId);
			//System.out.printf("路徑是 "+reptdir);					
			MultipartFile multipartFile = null;
			InputStream fis = null;
			BufferedInputStream bufferedInputStream = null;
			BufferedOutputStream bufferedOutputStream = null;
			BufferedOutputStream sysbfos = null;
//			BufferedOutputStream bfos = null;
			File sysFile = null;
			File file = null;
//			BufferedReader br = null;
			try {
				for (int i = 0; i < files.length; i++) {
					multipartFile = files[i];
					// 取得獨一無二的ID
					UID uid = new UID();
					// context產生路徑+uid名稱+檔案原始名稱				
					sysFile = new File("" + reptSystemdir + "/" + uid.hashCode() + multipartFile.getOriginalFilename());					
					//取得要存入系統內的圖片名稱含附檔名
					file = new File("" + reptdir + "/" + uid.hashCode() + multipartFile.getOriginalFilename());	
					System.out.println(sysFile);//查看路徑

					//--tomcat快取部分--查詢file父檔案是否存在 存在true 不存在fales
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					//--系統檔案部分
					if (!sysFile.getParentFile().exists()) {
						sysFile.getParentFile().mkdirs();
					}
					
					//檔案寫入系統 
					fis = multipartFile.getInputStream();
					sysbfos = new BufferedOutputStream(new FileOutputStream(sysFile, true));
					// FileOutputStream(file.getPath());
					int sysdata;
					while ((sysdata = fis.read()) != -1) {
						// 寫入到file設定的路徑內
						sysbfos.write(sysdata);
					}
					//將圖片從系統寫入tomcat
					bufferedInputStream = new BufferedInputStream(new FileInputStream(sysFile));
					bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
					int data = 0;
					while(data != -1) {
						data = bufferedInputStream.read();
						bufferedOutputStream.write(data);
					}
					bufferedOutputStream.flush();//强制清除缓冲区的内容
					bufferedInputStream.close();
					bufferedOutputStream.close();
										
					ReportImgBean imgBean = new ReportImgBean();
					imgBean.setReptNo(imgReptNo);
					//--擷取圖片連結
					int pathNo = file.getPath().indexOf("album");
					String path = file.getPath().substring(pathNo);
					//放入圖片路徑		
					ReportImgBean imgPathBean = new ReportImgBean();
					imgPathBean.setImgPath(path);
					//將連結提供給model
					imgPathList.add(imgPathBean);
					//--存放連結到資料庫
					imgBean.setImgPath(sysFile.getPath());
					reportImgService.InsertNewImg(imgBean);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sysbfos != null) {
					try {
						sysbfos.close();
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
			model.addAttribute("image",imgPathList);
			model.addAttribute("success", result);
			return "report.success";
		} else {
			errors.put("action", "新增回報失敗，請重新輸入！");
			return "report.error";
		}
	}
}

// @RequestMapping(path = { "/report/createNewReport.do" }, produces = {
// "application/json; charset=UTF-8" })

// public @ResponseBody String processPost(ReportBean , consumes = {
// "multipart/form-data; charset=UTF-8" }
// bean,HttpServletRequest request,Model model) throws IOException {

// out.write("{ 'key' : 'value' }");
// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
// Model 用途為將資料塞入 session Scope
// 嚴重: Servlet.service() for servlet [dispatcher] in context with path
// [/Textile] threw exception [Could not resolve view with name 'Report.error'
// in servlet with name 'dispatcher'] with root cause
// 如果出現這個錯誤檢查SpringMVCJavaConfiguration 是否有定設view的bean 不要再用views.xml設定
// @Configuration
// @EnableWebMvc 是否有使用
// @ComponentScan(basePackages = { "*.do" })
// 是否有換成全名tw.com.eeit94.textile.controller

////tomcat 將系統當案寫入暫存(檔案來源,格式,輸入);
//bufferedImage = ImageIO.read(fis2);
//ImageIO.write(bufferedImage, formatName, baos);
//baos.flush();
//byte[] originalImgByte = baos.toByteArray();
//baos.close();
////將檔案輸出
//byte[] resizedBytes = baos.toByteArray();
//fos = new BufferedOutputStream(new FileOutputStream(file, true));
//fos.write(resizedBytes);
//fos.close();
////tomcat---

//圖片寫到暫存
//BufferedImage bufferedImage = null;
//ByteArrayOutputStream baos = new ByteArrayOutputStream();			
//BufferedOutputStream fos = null;


//將副檔名取出
//int startIndex = sysFile.getName().lastIndexOf(46)+1;
//String formatName = file.getName().substring(startIndex);
////System.out.println("副檔名 "+formatName);


//@MultipartConfig(
//location = "/tmp",
////byte為單位 最大5MB
//maxFileSize = 1024*1024*5,
////最大上傳兩個 最多10MB
//maxRequestSize = 1024*1024*5*2
//)
