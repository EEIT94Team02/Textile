package tw.com.eeit94.textile.model.interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}