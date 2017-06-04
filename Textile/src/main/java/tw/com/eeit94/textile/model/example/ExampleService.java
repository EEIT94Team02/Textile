/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Service產生步驟：
 * 1. Service名稱為'"Table名稱" + "Service"'。
 * 2. 標記@Service。
 * 3. 加入至少一個Bean元件並標記@Autowired。
 */
@Service
public class ExampleService {
	@Autowired
	private ExampleDAO exampleDAO;

	// 測試程式
	public static void main(String args[]) {

	}

	/*
	 * 實作企業邏輯
	 */
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<ExampleBean> select() {
		return exampleDAO.select();
	}
}