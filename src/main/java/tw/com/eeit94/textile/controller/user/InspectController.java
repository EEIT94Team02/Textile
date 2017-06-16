package tw.com.eeit94.textile.controller.user;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

/**
 * 此控制元件檢驗瀏覽器送來的單一資料，q為資料，m為該資料對應MemberBean的屬性。
 * 
 * @author 賴
 * @version 2017/06/16
 * @throws Exception
 * @see {@link MemberService}
 */
@Controller
@RequestMapping(path = { "/check" })
public class InspectController {
	@Autowired
	private MemberService memberService;

	/**
	 * 驗證資料的方式與註冊和登入的元件相同，還能檢驗帳號是否已存在。
	 * 
	 * @author 賴
	 * @version 2017/06/16
	 * @throws IOException
	 * @see {@link LoginController}
	 */
	@RequestMapping(path = { "/inspect.do" }, method = { RequestMethod.GET }, produces = {
			"text/plain; charset=UTF-8" })
	@ResponseBody
	public void inspect(HttpServletRequest request, HttpServletResponse response, OutputStream out) throws IOException {
		String mField = request.getParameter(ConstHelperKey.METHOD.key());
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), mField);
		dataAndErrorsMap.put(mField, request.getParameter(ConstHelperKey.QUERY.key()));

		String output;
		int mapSize = dataAndErrorsMap.size();
		dataAndErrorsMap = memberService.checkFormData(dataAndErrorsMap);
		if (mapSize < dataAndErrorsMap.size()) {
			output = dataAndErrorsMap.get(mField + ConstMemberParameter._ERROR.param());
		} else {
			output = ConstHelperKey.SPACE.key();
		}
		out.write(output.getBytes());
		out.close();
	}
}