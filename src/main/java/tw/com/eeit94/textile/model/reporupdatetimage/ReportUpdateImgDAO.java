package tw.com.eeit94.textile.model.reporupdatetimage;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
public interface ReportUpdateImgDAO {
	public ReportUpdateImgBean select(Integer reptUpNo);

	// 查詢圖片
	public List<ReportUpdateImgBean> selectAll(Integer reptUpNo);

	// 新增圖片 接收圖片路徑
	public ReportUpdateImgBean insertImg(ReportUpdateImgBean imgBean);

	// 刪除圖片 接收圖片編號
	public boolean deleteImg(Integer reptUpNo);
}