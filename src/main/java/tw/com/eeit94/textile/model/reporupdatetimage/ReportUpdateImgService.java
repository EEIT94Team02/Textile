package tw.com.eeit94.textile.model.reporupdatetimage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/20
 */
@Service
@Transactional
public class ReportUpdateImgService {
	@Autowired
	private ReportUpdateImgDAO reportUpdateImgDAO;

	public ReportUpdateImgBean select(ReportUpdateImgBean reportImgBean) {
		if (reportImgBean != null) {
			return reportUpdateImgDAO.select(reportImgBean.getReptUpNo());
		}
		return null;
	}

	// 查詢某回報所有圖片 接收回報編號
	public List<ReportUpdateImgBean> selectRrptImg(ReportUpdateImgBean reportImgBean) {
		return reportUpdateImgDAO.selectAll(reportImgBean.getReptUpNo());
	}

	// 新增圖片
	public ReportUpdateImgBean InsertNewImg(ReportUpdateImgBean bean) {
		return reportUpdateImgDAO.insertImg(bean);
	}

	// 刪除圖片
	public boolean deleteImg(ReportUpdateImgBean reportImgBean) {
		boolean result = false;
		if (reportImgBean != null) {
			result = reportUpdateImgDAO.deleteImg(reportImgBean.getReptUpImgNo());
		}
		return result;
	}
}