package tw.com.eeit94.textile.model.socaillist;

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
public class SocailListService {
	@Autowired
	private SocailListDAO socailListDAO;

	public SocailListService(SocailListDAO socailListDAO) {
		this.socailListDAO = socailListDAO;
	}
	@Transactional
	public List<SocailListBean> selects(){
		
		List<SocailListBean> select = socailListDAO.select();
		
		return select;
	}
	@Transactional
	public SocailListBean select(SocailListPK pk){
		
		SocailListBean select = socailListDAO.select(pk);
		
		return select;
	}
	@Transactional
	public List<SocailListBean> selectByFriend(SocailListBean bean, Timestamp date){

		List<SocailListBean> select=null;
		try {
			select = socailListDAO.selectByFriend(bean, date);
		} catch (ParseException e) {
			System.out.println(e.getMessage()+"error by selectEndTime");
		}
		
		return select;
	}
	@Transactional
	public SocailListBean insert (SocailListBean bean){
		SocailListBean result=null;
		if (bean != null) {
			result=socailListDAO.insert(bean);
		}
		return result;
		
	}
	@Transactional
	public boolean delete (SocailListBean bean){
		
		SocailListPK pk1=new SocailListPK(bean.getSocailListPK().getUserId(),bean.getSocailListPK().getAcquaintenceId());
		SocailListBean resultA = socailListDAO.select(pk1);
		SocailListPK pk2=new SocailListPK(pk1.getAcquaintenceId(),pk1.getUserId());
		SocailListBean resultB = socailListDAO.select(pk2);
		if (resultA != null && resultB!=null) {
			socailListDAO.delete(resultA);
			socailListDAO.delete(resultB);
			return true;
		}
		return false;
		
	}
	@Transactional
	public boolean refuseDelete (SocailListBean bean){
		
		SocailListBean result = socailListDAO.select(bean.getSocailListPK());
		if (result != null ) {
			socailListDAO.delete(result);
			
			return true;
		}
		return false;
		
	}
	@Transactional
	public SocailListBean update(SocailListBean bean){
		SocailListBean result=null;
		if(bean!=null) {
			result = socailListDAO.update(bean);
		}
		return result;
	}
	@Transactional
	public SocailListBean updateTheFriend(SocailListBean bean){
		SocailListBean result=null;
		SocailListPK pk=new SocailListPK(bean.getSocailListPK().getAcquaintenceId(),bean.getSocailListPK().getUserId());
		bean.setSocailListPK(pk);
		if(bean!=null) {
			bean.setS_type("好友");
			result = socailListDAO.update(bean);
		}
		return result;
	}
	
	
}