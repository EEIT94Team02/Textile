package tw.com.eeit94.textile.model.report;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
public interface ReportDAO {
	// 尋找單筆 接收回報編號
	public ReportBean select(Integer reptNo);

	// 尋找多筆回報 接受會員編號
	List<ReportBean> selectReptByMId(Integer mId);

	// 尋找回覆狀態
	List<ReportBean> selectReptBySituation(Boolean situation);

	// 尋找全部
	List<ReportBean> selectByOthers(ReportBean bean);
	
	// 尋找會員最新回報
	List<ReportBean> selectReptByMidTop(Integer mId);

	// 新增回報 接收ReportBean
	public ReportBean insert(ReportBean bean);

	// 更新回報內容
	// public ReportBean custUpdate(String reptDetail,Integer reptNo);
	public ReportBean custUpdate(ReportBean bean);

	// 回覆回報內容
	public ReportBean mgrUpdate(String replyDetail, Integer reptNo);
	
	// 修改回報狀態
	public ReportBean situationChange ( Integer reptNo, Boolean situation);

	// 刪除回報單 接收回報編號
	public boolean delete(Integer reptNo);

	// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
	// public List<ReportBean> selectByPriceLessThan(double price);
}