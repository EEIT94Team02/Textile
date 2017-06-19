package tw.com.eeit94.textile.model.interest_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 控制興趣明細資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
 * @see {@link MemberRollbackRroviderService}
 */
@Service
public class Interest_DetailService {
	@Autowired
	private Interest_DetailDAO interest_DetailDAO;

	/**
	 * 註冊成功時，初始化使用者個人的興趣明細資料，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 * @see {@link RegisterController}
	 */
	public Interest_DetailBean getNewInterest_DetailBean(MemberBean mbean) {
		Interest_DetailBean i_dbean = new Interest_DetailBean();
		i_dbean.setmId(mbean.getmId());
		i_dbean.setI_dMain(0);
		i_dbean.setI_dOther("[]");
		i_dbean.setI_dRecreation(0);
		i_dbean.setI_dOtherRecreation("[]");
		i_dbean.setI_dExercises(0);
		i_dbean.setI_dOtherExercises("[]");
		i_dbean.setI_dDiet(0);
		i_dbean.setI_dOtherDiet("[]");
		i_dbean.setI_dArt(0);
		i_dbean.setI_dOtherArt("[]");
		i_dbean.setI_dDesign(0);
		i_dbean.setI_dOtherDesign("[]");
		i_dbean.setI_dMusic(0);
		i_dbean.setI_dOtherMusic("[]");
		i_dbean.setI_dHobbies(0);
		i_dbean.setI_dOtherHobbies("[]");
		i_dbean.setI_dActivities(0);
		i_dbean.setI_dOtherActivities("[]");
		return this.interest_DetailDAO.insert(i_dbean).get(0);
	}
}