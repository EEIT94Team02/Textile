/*
 * 假設"Url Pattern名稱"為"http://localhost:8080/Textile/example"，則套件名稱用tw.com.eeit94.textile.controller.example。
 */
package tw.com.eeit94.textile.controller.exmaple;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.example.ExampleBean;

/*
 * 1. ！重要：看最上方套件名稱的說明！
 * 
 * 2. ！重要：套件名稱*.controller."example"，這個"example"是Url Pattern的開頭，也是放JSP網頁的資料夾名稱！
 * 
 * 3. Url Pattern儘量不要用Table名稱，以會員資料member和聊天室資料chatroom為範例：
 * 
 *    (*.do是為了配合Filter和Interceptor，不用再用*.controller了)
 *    
 *    登入(POST)：/user/login.do
 *    
 *    修改(POST)：/user/modify.do
 *    
 *    註冊(POST)：/user/register.do
 *    
 *    查詢(GET)：/user/search.do?q=Dodge&m=name // q是query string，m是method，個人決定，自己看得懂就好，若不想網址太長或讓外人看得懂，就可以儘量簡寫。
 *    
 *    驗證(GET):/ajax/check.do?q=Dodge&n=name // n是name。
 *    
 *    開聊天室(GET)：/chat.do?q=ZA8kzXc0U0LmXLLJeajdZA== // 因為用primary key，所以要轉換，網址不要直接用primary key！
 *    
 *    發聊天訊息(POST)：/ajax/messaging.do
 * 
 * 4. RequestMethod不要都用GET，傳送隱密的資料如密碼則用POST，新增資料都用POST，修改資料用PUT或PATCH。
 * 
 * 5. consumes或produces若串流是檔案則用multipart/form-data，是JOSN資料則用application/json，純文字則用application/x-www-form-urlencoded。
 * 
 * 6. 轉往其它網頁不用加上@ResponseBody，加上@ResponseBody是為了網頁部分更新(AJAX)，且可以自動帶入Writer。
 * 
 * 7. 加上javadoc的註解，在Spring或Hibernate的Annotation上方，@author和@version必寫，@version為日期。
 * 
 */
/**
 * [空行]
 * 本欄未必須由Shift+Alt+J產生，複製貼上也可(要改內容)，這裡簡單敘述這個元件的功能或地位，上面的註解不用留著，example的套件留著就好。
 * [空行]
 * 
 * @author 賴
 * @version 2017/06/10
 */
@Controller
@RequestMapping(path = { "/example.do" }, produces = { "application/json; charset=UTF-8" })
public class ExampleController {

	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public void process(ExampleBean bean, BindingResult bindingResult, Model model, Writer out) throws IOException {
		out.write("{ 'key' : 'value' }");
		/*
		 * 要產生View元件則要return "'Url Pattern'"的相對或絕對路徑。
		 */
	}
}