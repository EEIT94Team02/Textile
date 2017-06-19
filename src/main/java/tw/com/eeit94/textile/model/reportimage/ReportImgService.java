package tw.com.eeit94.textile.model.reportimage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
@Service
@Transactional
public class ReportImgService {
	@Autowired
	private ReportImgDAO reportImgDAO;

	/*
	 * 實作企業邏輯
	 */
	 public ReportImgBean select(ReportImgBean reportImgBean){
		 if(reportImgBean!=null){
		 return reportImgDAO.select(reportImgBean.getReptNo());
		 }
	 return null;
	 }
	// 查詢某回報所有圖片 接收回報編號
	public List<ReportImgBean> selectRrptImg(ReportImgBean reportImgBean) {
//		ReportImgBean result;
//		result = reportImgDAO.select(reportImgBean.getReptNo());
//		if (result != null) {
			return reportImgDAO.selectAll(reportImgBean.getReptNo());
//		}
//		return null;
	}

	// 新增圖片
	public ReportImgBean InsertNewImg(ReportImgBean bean) {
		ReportImgBean result = null;
//		result = reportImgDAO.select(bean.getReptNo());
//		if (result != null) {
			result = reportImgDAO.insertImg(bean);
//		}
		return result;
	}

	// 刪除圖片
	public boolean deleteImg(ReportImgBean reportImgBean) {
		boolean result = false;
		if (reportImgBean != null) {
			result = reportImgDAO.deleteImg(reportImgBean.getReptImgNo());
		}
		return result;
	}
}