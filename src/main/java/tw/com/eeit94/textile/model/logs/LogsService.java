package tw.com.eeit94.textile.model.logs;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制紀錄資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/13
 */
@Service
public class LogsService {
	@Autowired
	private LogsDAO logsDAO;

	@Transactional(readOnly = true)
	public List<LogsBean> selectAll() {
		return this.logsDAO.selectAll();
	}

	/**
	 * 外部自動新增系統記錄。
	 * 
	 * @author 賴
	 * @version 2017/06/12
	 * @see {@link LogsDAOHibernate}
	 */
	@Transactional
	public List<LogsBean> insertNewLog(String lLog) {
		LogsBean lbean = new LogsBean();
		lbean.setlCreateTime(new Timestamp(System.currentTimeMillis()));
		lbean.setlLog(lLog);
		return this.logsDAO.insert(lbean);
	}

	/**
	 * 清除所有系統記錄。
	 * 
	 * @author 賴
	 * @version 2017/06/13
	 * @see {@link LogsDAOHibernate}
	 */
	@Transactional
	public List<LogsBean> clearAll() {
		this.logsDAO.deleteAll();
		return this.selectAll();
	}
}