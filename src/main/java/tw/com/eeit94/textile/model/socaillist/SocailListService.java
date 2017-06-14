package tw.com.eeit94.textile.model.socaillist;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
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
}