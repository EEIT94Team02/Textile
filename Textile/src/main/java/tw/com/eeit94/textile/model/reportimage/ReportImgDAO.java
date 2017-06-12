package tw.com.eeit94.textile.model.reportimage;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
public interface ReportImgDAO {
	public ReportImgBean select(Integer reptNo);

	// 查詢圖片
	public List<ReportImgBean> selectAll(Integer reptNo);

	// 新增圖片 接收圖片路徑
	public ReportImgBean insertImg(ReportImgBean imgBean);

	// 刪除圖片 接收圖片編號
	public boolean deleteImg(Integer reptNo);
	// //更新圖片 接收圖片路徑 編號
	// public ReportImgBean updateImg (String imgPath,Integer reptNo);
	
	// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
	// public List<ReportBean> selectByPriceLessThan(double price);
}