package tw.com.eeit94.textile.model.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ReportService {
	@Autowired
	private ReportDAO reportDAO;
	private DateFormat sdf;

	public ReportService(ReportDAO reportDAO) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.reportDAO = reportDAO;
		this.sdf = sdf;
	}

	/*
	 * 實作企業邏輯
	 */
	// 查詢某會員所有回報單
	public List<ReportBean> selectReptByMId(ReportBean reportBean) {
		return reportDAO.selectReptByMId(reportBean.getmId());
	}

	// 查詢回報狀態未回覆
	public List<ReportBean> selectReptBySituation(Boolean situation) {
		return reportDAO.selectReptBySituation(situation);
	}

	// 會員新增回報單 會員編號 內容 回報型態
	public ReportBean createNewReport(ReportBean bean) {
		if (bean != null) {
			if (bean.getmId() != null) {
				bean.setmId(bean.getmId());
				bean.setReptDate(new java.sql.Timestamp(new Date().getTime()));
				bean.setReptDetail(bean.getReptDetail() + new Date().toString());
				bean.setSituation(false);
				return reportDAO.insert(bean);
			}

		}
		return null;
	}

	// 會員更新回報 接收會員編號 回報內容 回報編號
	public ReportBean updateReptByCus(ReportBean bean) {
		ReportBean result = null;
		List<ReportBean> list;
		if (bean != null) {
			// 尋找是否有這張回報單
			result = reportDAO.select(bean.getReptNo());
			if (result != null) {
				// 找出會員所有回報資料
				list = reportDAO.selectReptByMId(bean.getmId());
				if (list != null) {
					for (ReportBean reportBean : list) {
						// 判斷該會員是否有該筆回報
						if (bean.getReptNo() == reportBean.getReptNo()) {
							result.setReptDetail(
									result.getReptDetail() + sdf.format(new Date()) + bean.getReptDetail());
							result.setReptNo(bean.getReptNo());
							return reportDAO.custUpdate(result);
						}
					}
				}
			}
		}
		return null;
	}

	// 管理員回覆
	public ReportBean updateReptByMgr(ReportBean bean) {
		ReportBean reportBean = reportDAO.select(bean.getReptNo());
		if (reportBean != null) {
			reportBean.setReplyDetail(
					reportBean.getReplyDetail() + bean.getReplyDetail() + "管理員於" + sdf.format(new Date()) + "回應");

			return reportDAO.mgrUpdate(reportBean.getReplyDetail(), reportBean.getReptNo());
		}
		return null;
	}

	// 刪除回報
	public boolean deleteRept(ReportBean bean) {
		ReportBean reportBean = reportDAO.select(bean.getReptNo());
		if (reportBean != null) {
			return reportDAO.delete(bean.getReptNo());
		}
		return false;
	}
}