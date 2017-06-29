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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.report.ReportBean;
import tw.com.eeit94.textile.model.report.ReportService;
import tw.com.eeit94.textile.model.reportimage.ReportImgBean;
import tw.com.eeit94.textile.model.reportimage.ReportImgService;
import tw.com.eeit94.textile.model.reportupdate.ReportUpdateBean;
import tw.com.eeit94.textile.model.reportupdate.ReportUpdateService;
import tw.com.eeit94.textile.model.reporupdatetimage.ReportUpdateImgBean;
import tw.com.eeit94.textile.model.reporupdatetimage.ReportUpdateImgService;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/20
 */
@SessionAttributes(names = { "report", "reportImg", "reportDetail", "reportDetailImg" })
@Controller
@EnableWebMvc
@RequestMapping(path = { "/report" })
public class ReportUpDateController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportImgService reportImgService;
	@Autowired
	private ReportUpdateService reportUpdateService;
	@Autowired
	private ReportUpdateImgService reportUpdateImgService;

	@RequestMapping(path = { "/reportUpDate.do" }, method = { RequestMethod.POST }, consumes = {
			"multipart/form-data; charset=UTF-8" })
	public String process(@RequestParam("file") MultipartFile[] files, ReportUpdateBean bean, ReportBean rBean,
			HttpServletRequest request, Model model) throws IOException {

		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		// 上線用
		int mId = memberBean.getmId();

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		if (bean.getReptUpDetail().trim().isEmpty()) {
			errors.put("message", "請輸入回報內容");
		}

		if (errors != null && !errors.isEmpty()) {
			return "report.error";
		}

		// 上線用
		bean.setReptNo(rBean.getReptNo());
		ReportUpdateBean result = reportUpdateService.insert(bean);
		List<ReportUpdateImgBean> imgPathList = new ArrayList<ReportUpdateImgBean>();
		if (result != null) {
			// 檔案上傳
			ServletContext context = request.getServletContext();
			// 取得路徑
			Path pathAlbum = Paths.get("/album");
			String reptSystemdir = pathAlbum + "/" + java.lang.String.valueOf(mId);
//			String reptdir = context.getContextPath() + "/apache-tomcat-8.5.15/wtpwebapps/Textile/album/"
//					+ java.lang.String.valueOf(mId);
			String realpath = "/album/";
			String reptdir = context.getContextPath() + realpath + java.lang.String.valueOf(mId);
			// System.out.printf("路徑是 "+reptdir);
			MultipartFile multipartFile = null;
			InputStream fis = null;
			BufferedInputStream bufferedInputStream = null;
			BufferedOutputStream bufferedOutputStream = null;
			BufferedOutputStream sysbfos = null;
			File sysFile = null;
			File file = null;
			try {
				if (!files[0].getOriginalFilename().isEmpty()) {
					for (int i = 0; i < files.length; i++) {
						multipartFile = files[i];
						// 取得獨一無二的ID
						UID uid = new UID();
						// context產生路徑+uid名稱+檔案原始名稱
						sysFile = new File(
								"" + reptSystemdir + "/" + uid.hashCode() + multipartFile.getOriginalFilename());
						// 取得要存入系統內的圖片名稱含附檔名
						file = new File("" + reptdir + "/" + uid.hashCode() + multipartFile.getOriginalFilename());
						System.out.println(sysFile);// 查看路徑

						// --tomcat快取部分--查詢file父檔案是否存在 存在true 不存在fales
						if (!file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
						}
						// --系統檔案部分
						if (!sysFile.getParentFile().exists()) {
							sysFile.getParentFile().mkdirs();
						}

						// 檔案寫入系統
						fis = multipartFile.getInputStream();
						sysbfos = new BufferedOutputStream(new FileOutputStream(sysFile, true));
						// FileOutputStream(file.getPath());
						int sysdata;
						while ((sysdata = fis.read()) != -1) {
							// 寫入到file設定的路徑內
							sysbfos.write(sysdata);
						}
						// 將圖片從系統寫入tomcat
						bufferedInputStream = new BufferedInputStream(new FileInputStream(sysFile));
						bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
						int data = 0;
						while (data != -1) {
							data = bufferedInputStream.read();
							bufferedOutputStream.write(data);
						}
						bufferedOutputStream.flush();// 强制清除缓冲区的内容
						bufferedInputStream.close();
						bufferedOutputStream.close();

						// 取得目前回報編號
						Integer imgReptNo = reportUpdateService.selectReptUpdateTop(rBean.getReptNo());
						ReportUpdateImgBean imgBean = new ReportUpdateImgBean();
						imgBean.setReptUpNo(imgReptNo);
						// --擷取圖片連結
						int pathNo = file.getPath().indexOf("album");
						String path = file.getPath().substring(pathNo);
						// 放入圖片路徑
						ReportUpdateImgBean imgPathBean = new ReportUpdateImgBean();
						imgPathBean.setImgUpPath(path);
						// 將連結提供給model
						imgPathList.add(imgPathBean);
						// --存放連結到資料庫
						imgBean.setImgUpPath(sysFile.getPath());
						reportUpdateImgService.InsertNewImg(imgBean);
					}
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
			model.addAttribute("image", imgPathList);
			model.addAttribute("success", result);
			// 將狀態修改成未回覆
			rBean.setSituation(false);
			reportService.updateReptByCus(rBean);
			return "reportUpDate.success";
		} else {
			errors.put("action", "新增回報失敗，請重新輸入！");
			return "reportUpdate.show";
		}
	}

	@RequestMapping(path = { "/reportSwitchPage.do" })
	public String replysuccess(ReportBean bean, ReportImgBean imgbean, HttpServletRequest request, Model model)
			throws IOException {
		ReportBean rBean = reportService.select(bean);
		imgbean.setReptNo(bean.getReptNo());
		List<ReportImgBean> beans = reportImgService.selectRrptImg(imgbean);
		ReportUpdateBean upbean = new ReportUpdateBean();
		upbean.setReptNo(bean.getReptNo());
		// 設定來源
		File source = null;
		// 設定目標
		File target = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		List<ReportUpdateImgBean> imgBeans = new ArrayList<ReportUpdateImgBean>();
		ServletContext context = request.getServletContext();
		// 設定路徑 context.getContextPath() = 專案名稱
		String dir = context.getContextPath() + "/apache-tomcat-8.5.15/wtpwebapps/Textile";
		List<ReportUpdateBean> upbeans = reportUpdateService.selectUpdateList(upbean);
		for (ReportUpdateBean updateBean : upbeans) {
			ReportUpdateImgBean reportImgBean = new ReportUpdateImgBean();
			reportImgBean.setReptUpNo(updateBean.getReptUpNo());
			List<ReportUpdateImgBean> list = reportUpdateImgService.selectRrptImg(reportImgBean);
			for (ReportUpdateImgBean imgBean : list) {
				System.out.println("imgBean=" + imgBean.getReptUpNo());
				// 如果沒有就寫入
				source = new File(imgBean.getImgUpPath());
				target = new File(dir + imgBean.getImgUpPath());
				if (!target.getParentFile().exists()) {
					target.getParentFile().mkdirs();
				}
				bufferedInputStream = new BufferedInputStream(new FileInputStream(source));
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(target));
				int data = 0;
				while (data != -1) {
					data = bufferedInputStream.read();
					bufferedOutputStream.write(data);
				}
				bufferedOutputStream.flush();
				bufferedInputStream.close();
				bufferedOutputStream.close();
				System.out.println("imgBean=" + imgBean.getImgUpPath());
				imgBeans.add(imgBean);
			}
		}

		model.addAttribute("reportDetailImg", imgBeans);
		model.addAttribute("reportDetail", upbeans);
		model.addAttribute("reportImg", beans);
		model.addAttribute("report", rBean);
		return "reportUpdate.show";
	}

	// 會員看回報詳細內容
	@RequestMapping(path = { "/reportdetail.do" })
	public String reportDetail(ReportBean bean, ReportImgBean imgbean, HttpServletRequest request, Model model)
			throws IOException {
		ReportBean rBean = reportService.select(bean);
		imgbean.setReptNo(bean.getReptNo());
		List<ReportImgBean> beans = reportImgService.selectRrptImg(imgbean);
		ReportUpdateBean upbean = new ReportUpdateBean();
		upbean.setReptNo(bean.getReptNo());
		// 設定來源
		File source = null;
		// 設定目標
		File target = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		List<ReportUpdateImgBean> imgBeans = new ArrayList<ReportUpdateImgBean>();
		ServletContext context = request.getServletContext();
		// 設定路徑 context.getContextPath() = 專案名稱
		String dir = context.getContextPath() + "/apache-tomcat-8.5.15/wtpwebapps/Textile";
		List<ReportUpdateBean> upbeans = reportUpdateService.selectUpdateList(upbean);
		for (ReportUpdateBean updateBean : upbeans) {
			ReportUpdateImgBean reportImgBean = new ReportUpdateImgBean();
			reportImgBean.setReptUpNo(updateBean.getReptUpNo());
			List<ReportUpdateImgBean> list = reportUpdateImgService.selectRrptImg(reportImgBean);
			for (ReportUpdateImgBean imgBean : list) {
				System.out.println("imgBean=" + imgBean.getReptUpNo());
				// 如果沒有就寫入
				source = new File(imgBean.getImgUpPath());
				target = new File(dir + imgBean.getImgUpPath());
				if (!target.getParentFile().exists()) {
					target.getParentFile().mkdirs();
				}
				bufferedInputStream = new BufferedInputStream(new FileInputStream(source));
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(target));
				int data = 0;
				while (data != -1) {
					data = bufferedInputStream.read();
					bufferedOutputStream.write(data);
				}
				bufferedOutputStream.flush();
				bufferedInputStream.close();
				bufferedOutputStream.close();
				System.out.println("imgBean=" + imgBean.getImgUpPath());
				imgBeans.add(imgBean);
			}
		}

		model.addAttribute("reportDetailImg", imgBeans);
		model.addAttribute("reportDetail", upbeans);
		model.addAttribute("reportImg", beans);
		model.addAttribute("report", rBean);
		return "reportdetail.show";
	}

}
