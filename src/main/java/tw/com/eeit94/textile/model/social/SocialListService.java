package tw.com.eeit94.textile.model.social;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 
 * @author 周
 * @version 2017/06/12
 */
@Service
public class SocialListService {
	@Autowired
	private SocialListDAO socialListDAO;

	public SocialListService(SocialListDAO socialListDAO) {
		this.socialListDAO = socialListDAO;
	}
	@Transactional
	public List<SocialListBean> selects(){
		
		List<SocialListBean> select = socialListDAO.select();
		
		return select;
	}
	@Transactional
	public SocialListBean select(SocialListPK pk){
		
		SocialListBean select = socialListDAO.select(pk);
		
		return select;
	}
	@Transactional
	public List<SocialListBean> selectByFriend(SocialListBean bean, Timestamp date){

		List<SocialListBean> select=null;
		try {
			select = socialListDAO.selectByFriend(bean, date);
		} catch (ParseException e) {
			System.out.println(e.getMessage()+"error by selectEndTime");
		}
		
		return select;
	}
	@Transactional
	public SocialListBean insert (SocialListBean bean){
		SocialListBean result=null;
		if (bean != null) {
			result=socialListDAO.insert(bean);
		}
		return result;
		
	}
	@Transactional
	public boolean delete (SocialListBean bean){
		
		SocialListPK pk1=new SocialListPK(bean.getSocialListPK().getUserId(),bean.getSocialListPK().getAcquaintenceId());
		SocialListBean resultA = socialListDAO.select(pk1);
		SocialListPK pk2=new SocialListPK(pk1.getAcquaintenceId(),pk1.getUserId());
		SocialListBean resultB = socialListDAO.select(pk2);
		if (resultA != null && resultB!=null) {
			socialListDAO.delete(resultA);
			socialListDAO.delete(resultB);
			return true;
		}
		return false;
		
	}
	@Transactional
	public boolean refuseDelete (SocialListBean bean){
		
		SocialListBean result = socialListDAO.select(bean.getSocialListPK());
		if (result != null ) {
			socialListDAO.delete(result);
			
			return true;
		}
		return false;
		
	}
	@Transactional
	public SocialListBean update(SocialListBean bean){
		SocialListBean result=null;
		if(bean!=null) {
			result = socialListDAO.update(bean);
		}
		return result;
	}
	@Transactional
	public SocialListBean updateTheFriend(SocialListBean bean){
		SocialListBean result=null;
		SocialListPK pk=new SocialListPK(bean.getSocialListPK().getAcquaintenceId(),bean.getSocialListPK().getUserId());
		bean.setSocialListPK(pk);
		if(bean!=null) {
			bean.setS_type("好友");
			result = socialListDAO.update(bean);
		}
		return result;
	}
	
	
}