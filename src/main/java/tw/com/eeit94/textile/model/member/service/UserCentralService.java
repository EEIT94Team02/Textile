package tw.com.eeit94.textile.model.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.controller.user.ModifyController;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 讀取個人使用者的資料，且控管其它的Service，即Controller控管使用者最重要的Service元件。
 * 
 * @author 賴
 * @version 2017/06/20
 */
@Service
public class UserCentralService {
	@Autowired
	private Interest_DetailService interest_DetailService;

	/**
	 * 利用MemberBean搜尋其它相關的使用者資料。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @see {@link ModifyController}
	 */
	@Transactional(readOnly = true)
	public MemberBean selectUserAllData(MemberBean mbean) {
		Interest_DetailNameListBean i_d = this.interest_DetailService.getI_DNLBean(mbean.getInterest_DetailBean());
		mbean.setI_d(i_d);
		return mbean;
	}
}