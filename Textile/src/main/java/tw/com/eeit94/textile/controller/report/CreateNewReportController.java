package tw.com.eeit94.textile.controller.report;

import java.io.IOException;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.report.ReportBean;
import tw.com.eeit94.textile.model.report.ReportService;
import tw.com.eeit94.textile.model.reportimage.ReportImgService;

//@MultipartConfig(
//		location = "/tmp",
//		//byte為單位 最大5MB
//		maxFileSize = 1024*1024*5,
//		//最大上傳兩個 最多10MB
//		maxRequestSize = 1024*1024*5*2
//		)
/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
@Controller
@RequestMapping(path = { "/createNewReport.do" }, produces = { "application/json; charset=UTF-8" })
public class CreateNewReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportImgService reportImgService;

	// Collection<Part> parts =

	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public void processPost(ReportBean bean, BindingResult bindingResult, Model model, Writer out) throws IOException {
		out.write("{ 'key' : 'value' }");
		// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
	}
}