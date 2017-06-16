package tw.com.eeit94.textile.model.interest_detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制興趣明細資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Service
public class Interest_DetailService {
	@Autowired
	private Interest_DetailDAO interest_DetailDAO;

	@Transactional
	public List<Interest_DetailBean> selectAll() {
		return interest_DetailDAO.selectAll();
	}
}