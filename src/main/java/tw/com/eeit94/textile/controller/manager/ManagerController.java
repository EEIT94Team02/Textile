package tw.com.eeit94.textile.controller.manager;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.system.common.ConstMapping;

/**
 * 所有重要的系統紀錄可以用這個元件得知，只有超級管理員能夠操作本網頁，並能刪除所有系統記錄。
 * 
 * 列出所有系統記錄：/manager/logs.do
 * 
 * 清除所有系統記錄：/manager/delogs.do
 * 
 * 列出所有會員清單：/manager/users.do
 * 
 * 在線測試MVC的其它元件：/manager/tests.do
 * 
 * @author 賴
 * @version 2017/06/25
 * @throws Exception
 */
@Controller
@RequestMapping(path = { "/manager" })
public class ManagerController {
	@Autowired
	private LogsService logsService;
	@Autowired
	private MemberService memberService;

	/**
	 * 列出所有系統記錄。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@RequestMapping(value = { "/logs.do" }, method = { RequestMethod.GET })
	public String showAllLogs(Model model) {
		model.addAttribute(ConstManagerKey.LOGS.key(), this.logsService.selectAll());
		return ConstMapping.MANAGER_LOGS_SHOW.path();
	}

	/**
	 * 清除所有系統記錄。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@RequestMapping(value = { "/delogs.do" }, method = { RequestMethod.GET })
	public String clearAllLogs(Model model) {
		this.logsService.clearAll();
		model.addAttribute(ConstManagerKey.LOGS.key(), logsService.selectAll());
		return ConstMapping.MANAGER_LOGS_SHOW.path();
	}

	/**
	 * 列出所有會員清單。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @throws Exception
	 */
	@RequestMapping(value = { "/users.do" }, method = { RequestMethod.GET })
	public String showAllUsers(Model model, HttpServletRequest request) throws Exception {
		List<MemberBean> list = this.memberService.selectAll();
		model.addAttribute(ConstManagerKey.USERS.key(), list);

		for (int i = 0; i < list.size(); i++) {
			MemberBean mbean = list.get(i);
			String mOtherProfileUrl = this.memberService.getOtherProfileUrl(mbean, request);
			mOtherProfileUrl.replace(ConstMapping.TRUE_MAIN_PAGE.path(), ConstMapping.REDIRECT_MAIN_PAGE.path());
			mbean.setmOtherProfileUrl(mOtherProfileUrl);
		}

		return ConstMapping.MANAGER_USERS_SHOW.path();
	}

	/**
	 * 在線測試MVC的其它元件。
	 * 
	 * ！！！！！！測試！！！！！！
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @throws Exception
	 */
	@RequestMapping(value = { "/tests.do" }, method = { RequestMethod.GET })
	@ResponseBody
	public void showTestsResult(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws Exception {
		response.setContentType("text/plain; charset=UTF-8");
		String output = "Result: ";
		out.write(output.getBytes());

		// 以下為測試內容
		output = "";
		// 以上為測試內容
		out.write(output.getBytes());
		out.close();
	}
}