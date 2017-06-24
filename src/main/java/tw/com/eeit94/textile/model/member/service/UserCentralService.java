package tw.com.eeit94.textile.model.member.service;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.controller.user.ModifyController;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

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

	/**
	 * 檢查Map物件是否含「_error」結尾的Key，如果有則回傳該Value，作為AJAX回傳的錯誤訊息。
	 * 
	 * @author 賴
	 * @version 2017/06/21
	 */
	public String getAJAXCheckResult(Map<String, String> dataAndErrorsMap) {
		String output = ConstHelperKey.SPACE.key();
		Iterator<String> iterator = dataAndErrorsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.endsWith(ConstMemberParameter._ERROR.param())) {
				output = dataAndErrorsMap.get(key);
				break;
			}
		}

		return output;
	}

	/**
	 * 檢查Map物件是否含「_error」結尾的Key，如果有則回傳true，Controller會導向View到原本的輸入頁面。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	public boolean getSubmitCheckFailure(Map<String, String> dataAndErrorsMap) {
		Iterator<String> iterator = dataAndErrorsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.endsWith(ConstMemberParameter._ERROR.param())) {
				return true;
			}
		}

		return false;
	}
}