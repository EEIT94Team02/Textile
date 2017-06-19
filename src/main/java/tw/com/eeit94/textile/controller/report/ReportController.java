/*
 * 假設"Url Pattern名稱"為"http://localhost:8080/Textile/example"，則套件名稱用tw.com.eeit94.textile.controller.example。
 */
package tw.com.eeit94.textile.controller.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.report.ReportBean;
import tw.com.eeit94.textile.model.report.ReportService;
import tw.com.eeit94.textile.model.reportimage.ReportImgBean;
import tw.com.eeit94.textile.model.reportimage.ReportImgService;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/*
 * 一、命名規則：
 * 
 *    ！重要：看最上方套件名稱的說明！
 * 
 *    ！重要：套件名稱*.controller."example"，這個"example"是Url Pattern的開頭，也是放JSP網頁的資料夾名稱！
 * 
 *    Url Pattern儘量不要用Table名稱。
 *    
 * 二、請求路徑規則(Controller)：
 * 
 *    (*.do是為了配合Filter和Interceptor，不用再用*.controller了)
 *    
 *    RequestMethod不要都用GET，傳送隱密的資料如密碼則用POST，新增資料都用POST，修改資料用PUT或PATCH。
 *    
 *    consumes或produces若串流是檔案則用multipart/form-data，是JOSN資料則用application/json，純文字則用application/x-www-form-urlencoded。
 *    
 *    轉往其它網頁不用加上@ResponseBody，加上@ResponseBody是為了網頁部分更新(AJAX)，且可以自動帶入Writer。
 *    
 *    以會員資料member和聊天室資料chatroom為範例：
 *    
 *    登入(POST)：/user/login.do
 *    
 *    修改(POST)：/user/modify.do
 *    
 *    註冊(POST)：/user/register.do
 *    
 *    查詢(GET)：/user/search.do?q=Dodge&m=name // q是query string，m是method，個人決定，自己看得懂就好，若不想網址太長或讓外人看得懂，就可以儘量簡寫。
 *    
 *    驗證(GET):/ajax/check.do?q=Dodge&n=name // n是name。
 *    
 *    開聊天室(GET)：/chat.do?q=ZA8kzXc0U0LmXLLJeajdZA== // 因為用primary key，所以要轉換，網址不要直接用primary key！
 *    
 *    發聊天訊息(POST)：/ajax/messaging.do
 *    
 * 三、請求路徑規則(View)：
 *    
 *    (*.v是讀取jsp檔，不要使用*.jsp，每個目錄的根目錄「/」的請求會自動導向該目錄的「/index.jsp」)
 *    
 *    讀取各目錄下的首頁(GET)：/user/ → 自動轉成 /user/index.jsp → 再自動轉成 /user/index.v
 *    
 *    讀取各目錄下的頁面(GET)：/user/modify.v
 *    
 *    讀取各目錄下的頁面(GET)：/user/modify.jsp → /error/404.v
 *    
 *    將頁面自動重新導向(GET)：/user/modify.r → 自動轉成/user/modify.v(附加redirect的功能)
 *
 * 四、其它：
 *    
 *    加上javadoc的註解，在Spring或Hibernate的Annotation上方，@author和@version必寫，@version為日期。
 * 
 */
/**
 * [空行]
 * 本欄未必須由Shift+Alt+J產生，複製貼上也可(要改內容)，這裡簡單敘述這個元件的功能或地位，上面的註解不用留著，example的套件留著就好。
 * [空行]
 * 
 * @author 黃
 * @version 2017/06/16
 */
@Controller
@RequestMapping(path = { "/report" })
public class ReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportImgService reportImgService;

	// 會員查詢自己所有回報記錄
	@RequestMapping(path = { "/reportlist.do" })
	public String reptList(HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		ReportBean bean = new ReportBean();
		// 設定會員ID
		bean.setmId(memberBean.getmId());
		List<ReportBean> reportList = reportService.selectReptByMId(bean);
		model.addAttribute("reportList", reportList);
		return "reportList.show";
	}

	// 管理員查詢所有未回覆紀錄
	@RequestMapping(path = { "/situationlist.do" })
	public String situationList(HttpServletRequest request,Model model) throws IOException {
		List<ReportBean> beans = reportService.selectReptBySituation(false);
		List<ReportImgBean> imgBeans = new ArrayList<ReportImgBean>();
		//設定來源
		File source = null;
		//設定目標
		File target = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		ServletContext context = request.getServletContext();
		//設定路徑 context.getContextPath() = 專案名稱
		String dir = context.getContextPath()+"/apache-tomcat-8.5.15/wtpwebapps/Textile";
		for (ReportBean bean : beans) {
			ReportImgBean reportImgBean = new ReportImgBean();
			reportImgBean.setReptNo(bean.getReptNo());
			List<ReportImgBean> list = reportImgService.selectRrptImg(reportImgBean);			
			for(ReportImgBean imgBean:list){
//				System.out.println("imgBean="+imgBean.getReptNo()+
//						imgBean.getReptImgNo()+
//						imgBean.getImgPath());
				//如果沒有就寫入
				source = new File(imgBean.getImgPath());
				target = new File(dir+imgBean.getImgPath());
				if(!target.getParentFile().exists()){
					target.getParentFile().mkdirs();
				}
				bufferedInputStream = new BufferedInputStream(new FileInputStream(source));
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(target));
				int data = 0;
				while(data != -1){
					data = bufferedInputStream.read();
					bufferedOutputStream.write(data);
				}
				bufferedOutputStream.flush();
				bufferedInputStream.close();
				bufferedOutputStream.close();
				imgBeans.add(imgBean);
			}
		}

		model.addAttribute("reportimg", imgBeans);
		model.addAttribute("situationList", beans);
		return "situationList.show";
	}
	
	@RequestMapping(path = { "/reportreply.do" })
	public String reportReply(ReportBean bean,ReportImgBean imgbean,HttpServletRequest request,Model model) throws IOException {
		ReportBean rBean = reportService.select(bean);
		imgbean.setReptNo(bean.getReptNo());
		List<ReportImgBean> beans = reportImgService.selectRrptImg(imgbean);
		model.addAttribute("reportImg",beans);
		model.addAttribute("report",rBean);
		return "reply.show";
	}
	
	@RequestMapping(path = { "/replysuccess.do" })
	public String replysuccess(ReportBean bean,ReportImgBean imgbean,HttpServletRequest request,Model model) throws IOException {		
		ReportBean reportBean = reportService.select(bean);
		System.out.println("reportBean="+reportBean.getReptNo());
//		model.addAttribute("reportImg",beans);
//		model.addAttribute("report",result);
		return "replysuccess.show";
	}
	
}