package tw.com.eeit94.textile.model.interest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制興趣資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Service
public class InterestService {
	@Autowired
	private InterestDAO interestDAO;
	
	@Transactional
	public List<InterestBean> selectAll() {
		return interestDAO.selectAll();
	}
}