package tw.com.eeit94.textile.model.theme;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
public interface ThemeDAO {
	// 查詢單筆
	public ThemeBean select(Integer themeNo);

	// 查詢會員的主題狀態
	public List<ThemeBean> selectBoolan(Integer mId);

	// 查全部
	public List<ThemeBean> selectByOthers(ThemeBean themeBean);

	// 查詢會員擁有主題
	public List<ThemeBean> selectMemberTheme(Integer mId);

	// ThemeBean select(Integer themeNo);
	// 新增主題
	public ThemeBean insertTheme(ThemeBean themeBean);

	// 更新主題
	public ThemeBean updateTheme(Integer themeNo, String themeStyle, Boolean themeStatus);

	// 刪除主圖
	public boolean deleteTheme(Integer themeNo);
	
	// 補充：因應企業邏輯可能要添加DAO的搜尋方式。
	// public List<ReportBean> selectByPriceLessThan(double price);
}