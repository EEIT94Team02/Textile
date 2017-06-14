package tw.com.eeit94.textile.model.logs;

import java.util.List;

/**
 * 控制記錄資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/12
 */
public interface LogsDAO {

	public List<LogsBean> selectAll();
	
	public List<LogsBean> deleteAll();

	public List<LogsBean> select(LogsBean lbean);

	public List<LogsBean> insert(LogsBean lbean);

	public List<LogsBean> update(LogsBean lbean);

	public List<LogsBean> delete(LogsBean lbean);
}