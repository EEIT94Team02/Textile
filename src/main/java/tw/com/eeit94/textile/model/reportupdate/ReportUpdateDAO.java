package tw.com.eeit94.textile.model.reportupdate;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/20
 */
public interface ReportUpdateDAO {
	// 尋找單筆 接收回報編號
	public ReportUpdateBean select(Integer reptNo);

	// 尋找多筆回報 舊往新 
	List<ReportUpdateBean> selectUpDateList(Integer reptNo);
	
	// 尋找多筆回報 新往舊 
	List<ReportUpdateBean> selectListBydesc(Integer reptNo);
	
	// 查詢Reply等於null的
	public List<ReportUpdateBean> selectReplyNull();
	
	// 新增回報 接收ReportBean
	public ReportUpdateBean insertReptUpDate(ReportUpdateBean bean);

	// 回覆回報內容
	public ReportUpdateBean mgrUpdate(String replyUpDetail, Integer reptUpNo);

	// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
	// public List<ReportBean> selectByPriceLessThan(double price);
}