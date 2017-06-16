package tw.com.eeit94.textile.controller.logs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.system.common.ConstMapping;

/**
 * 所有重要的系統紀錄可以用這個元件得知，只有超級管理員能夠操作本網頁，並能刪除所有系統記錄。
 * 
 * 列出所有系統記錄：/record/logs.do
 * 
 * 清除所有系統記錄：/record/delogs.do
 * 
 * @author 賴
 * @version 2017/06/12
 * @throws Exception
 */
@Controller
@RequestMapping(path = { "/manager" })
public class LogsController {
	@Autowired
	private LogsService logsService;

	/**
	 * 列出所有系統記錄。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@RequestMapping(value = { "/logs.do" }, method = { RequestMethod.GET })
	public String showLogs(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(ConstLogsKey.LOGS.key(), this.logsService.selectAll());
		return ConstMapping.LOGS_SUCCESS.path();
	}

	/**
	 * 清除所有系統記錄。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 */
	@RequestMapping(value = { "/delogs.do" }, method = { RequestMethod.GET })
	public String clearLogs(HttpServletRequest request) {
		this.logsService.clearAll();
		HttpSession session = request.getSession();
		session.setAttribute(ConstLogsKey.LOGS.key(), this.logsService.selectAll());
		return ConstMapping.LOGS_SUCCESS.path();
	}
}