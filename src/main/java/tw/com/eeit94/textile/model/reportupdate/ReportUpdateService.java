package tw.com.eeit94.textile.model.reportupdate;

import java.util.Date;
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
public class ReportUpdateService {
	@Autowired
	private ReportUpdateDAO reportUpdateDAO;

	public ReportUpdateService(ReportUpdateDAO reportUpdateDAO) {
		this.reportUpdateDAO = reportUpdateDAO;
	}
	
	//查詢單筆更新回報內容
	public ReportUpdateBean select(ReportUpdateBean bean){
		if(bean!=null){
			return reportUpdateDAO.select(bean.getReptUpNo());
		}
		return bean;
	}
	
	//搜尋某回報編號所有更新內容 
	public List<ReportUpdateBean> selectUpdateList(ReportUpdateBean bean){
		return reportUpdateDAO.selectUpDateList(bean.getReptNo());
	}
	
	// 查詢會員最新回報
	public Integer selectReptUpdateTop(Integer reptNo){
		List<ReportUpdateBean> list = reportUpdateDAO.selectListBydesc(reptNo);
		Integer reportUpNo = null;
		if(reptNo != null){
			for(ReportUpdateBean bean:list){
					reportUpNo = bean.getReptUpNo();
					return reportUpNo;				
			}
		}
		return reportUpNo;
	}
	
	//新增更新回報
	public ReportUpdateBean insert(ReportUpdateBean bean){

			bean.setReptUpDate(new java.sql.Timestamp(new Date().getTime()));
			bean.setReptNo(bean.getReptNo());
			System.out.println(bean);
			
			return reportUpdateDAO.insertReptUpDate(bean);
	}
	
	//管理員回覆
	public ReportUpdateBean reply(ReportUpdateBean bean){
		if(bean!= null){
			return reportUpdateDAO.mgrUpdate(bean.getReplyUpDetail(), bean.getReptUpNo());
		}
		return null;
	}
	
	//查詢未回覆
	public List<ReportUpdateBean> replyNull(){
		return reportUpdateDAO.selectReplyNull();
	}
	
	/*
	 * 實作企業邏輯
	 */
	// 查詢單筆回報內容

}