package tw.com.eeit94.textile.controller.user;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.eeit94.textile.model.interest.InterestBean;
import tw.com.eeit94.textile.model.interest.InterestService;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.UserCentralService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 查詢使用者資料的Controller，主要功能有查詢會員、條件配對和隨機配對。
 * 
 * @author 賴
 * @version 2017/06/23
 * @see {@link MemberService}
 * @see {@link Interest_DetailService}
 */
@Controller
@RequestMapping(path = { "/user" })
public class QueryUserController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private UserCentralService userCentralService;
	private static final String QUERY_USER_ERROR = "該會員姓名不存在";

	/**
	 * 查詢會員：利用相似姓名查詢。(AJAX)
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 * @throws IOException
	 */
	@RequestMapping(path = { "/queryName.do" }, method = { RequestMethod.GET }, params = { "m=q" }, produces = {
			"text/plain; charset=UTF-8" })
	@ResponseBody
	public void querySimilarNameProcess(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws IOException {
		String mName = request.getParameter(ConstHelperKey.QUERY.key());
		List<MemberBean> list = this.memberService.selectBySimilarName(mName);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			jsonArray.put(list.get(i).getmName());
		}
		out.write(jsonArray.toString().getBytes());
		out.close();
	}

	/**
	 * 查詢會員：利用相同姓名查詢，並導向會員主頁。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 * @throws Exception
	 */
	@RequestMapping(path = { "/queryName.do" }, method = { RequestMethod.POST }, params = { "m=queryName" })
	public String queryNameProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mName = request.getParameter(ConstHelperKey.QUERY.key());
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
		dataAndErrorsMap.put(ConstMemberKey.Name.key(), mName);

		List<MemberBean> list = this.memberService.selectByName(mName);
		if (list.size() == 0) {
			dataAndErrorsMap.put(ConstMemberKey.Name.key() + ConstMemberParameter._ERROR.param(), QUERY_USER_ERROR);
			return ConstMapping.QUERYNAME_ERROR.path();
		} else {
			MemberBean mbean = list.get(0);
			String encryptedMId = this.secureService.getEncryptedText(mbean.getmId().toString(),
					ConstSecureParameter.MEMBERID.param());
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(request.getContextPath()).append(ConstMapping.PROFILE_OTHERUSER_PAGE.path());
			sBuffer.append(ConstHelperKey.QUESTION.key()).append(ConstHelperKey.QUERY_EQUAL.key());
			sBuffer.append(encryptedMId);
			response.sendRedirect(sBuffer.toString());
		}
		return null;
	}

	/**
	 * 查詢會員：利用條件配對。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 */
	@RequestMapping(path = { "/queryCondition.v" }, method = { RequestMethod.GET })
	public String conditionViewProcess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		mbean = this.userCentralService.selectUserAllData(mbean);
		return ConstMapping.QUERYCONDITION_SHOW.path();
	}

	/**
	 * 查詢會員：利用相似的興趣查詢。(AJAX)
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 * @throws IOException
	 */
	@RequestMapping(path = { "/queryCondition.do" }, method = { RequestMethod.GET }, params = {
			"m=mOtherInterest" }, produces = { "text/plain; charset=UTF-8" })
	@ResponseBody
	public void querySimilarInterestProcess(HttpServletRequest request, HttpServletResponse response, OutputStream out)
			throws IOException {
		String mOtherInterest = request.getParameter(ConstHelperKey.QUERY.key());
		List<InterestBean> list = this.interestService.selectBySimilarName(mOtherInterest);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			jsonArray.put(list.get(i).getiName());
		}
		out.write(jsonArray.toString().getBytes());
		out.close();
	}

	/**
	 * 查詢會員：利用條件配對，並導向會員主頁。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 * @throws Exception
	 */
	@RequestMapping(path = { "/queryCondition.do" }, method = { RequestMethod.POST }, params = { "m=queryCondition" })
	public String queryConditionProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		dataAndErrorsMap = this.memberService.encapsulateAllWhenQueryingCondition(dataAndErrorsMap, request);
		return ConstMapping.QUERYCONDITION_SHOW.path();
	}
}