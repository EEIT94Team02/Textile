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
 * 4. 加上javadoc的註解，在Spring或Hibernate的Annotation上方，@author和@version必寫，@version為日期。
 * 5. 不要寫建構子，要測試請參考tw.com.eeit94.textile.model.exapmle.ExampleDAOHibernateTest.java這支測試程式。
 * 6. 要寫測試程式，但不要寫在這裡！
 */
/**
 * [空行] 
 * 本欄未必須由Shift+Alt+J產生，複製貼上也可(要改內容)，這裡簡單敘述這個元件的功能或地位，上面的註解不用留著，example的套件留著就好。
 * [空行]
 * @author 賴
 * @version 2017/06/10
 */
@Service
public class ExampleService {
	@Autowired
	private ExampleDAO exampleDAO;

	/*
	 * 不要寫測試程式，建構子不要寫。
	 */
	
	/*
	 * 實作企業邏輯
	 */
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<ExampleBean> selectAll() {
		return exampleDAO.selectAll();
	}
}