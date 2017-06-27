package tw.com.eeit94.textile.controller.user;

import java.io.IOException;
import java.io.Writer;
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

import tw.com.eeit94.textile.model.interest.InterestBean;
import tw.com.eeit94.textile.model.interest.InterestService;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.MemberKeyWordsBean;
import tw.com.eeit94.textile.model.member.service.UserCentralService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstMapping;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 * 查詢使用者資料的Controller，主要功能有查詢會員、條件配對和隨機配對。
 * 
 * @author 賴
 * @version 2017/06/23
 * @see {@link MemberService}
 * @see {@link InterestService}
 */
@Controller
@RequestMapping(path = { "/user" })
public class QueryUserController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private Interest_DetailService interest_DetailService;
	@Autowired
	private UserCentralService userCentralService;
	private static final String QUERY_NAME_ERROR = "該會員姓名不存在";
	private static final String QUERY_CONDITION_ERROR = "沒有會員符合條件，請重新搜索。";

	/**
	 * 查詢會員：利用相似姓名查詢。(AJAX)
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 * @throws IOException
	 */
	@RequestMapping(path = { "/queryName.do" }, method = { RequestMethod.GET }, params = { "m=q" })
	public void querySimilarNameProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mName = request.getParameter(ConstHelperKey.QUERY.key());
		List<MemberBean> list = this.memberService.selectBySimilarName(mName);
		// 除去內定名為「系統」的會員
		list = this.memberService.getRidOfSystemMBean(list);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			jsonArray.put(list.get(i).getmName());
		}
		response.setContentType("text/plain; charset=UTF-8");
		Writer out = response.getWriter();
		out.write(jsonArray.toString());
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
		// 除去內定名為「系統」的會員
		list = this.memberService.getRidOfSystemMBean(list);
		if (list.size() == 0) {
			dataAndErrorsMap.put(ConstMemberKey.Name.key() + ConstMemberParameter._ERROR.param(), QUERY_NAME_ERROR);
			return ConstMapping.QUERYNAME_ERROR.path();
		} else {
			MemberBean mbean = list.get(0);
			response.sendRedirect(this.memberService.getOtherProfileUrl(mbean, request));
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
	@RequestMapping(path = { "/queryCondition.do" }, method = { RequestMethod.GET }, params = { "m=mOtherInterest" })
	public void querySimilarInterestProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String mOtherInterest = request.getParameter(ConstHelperKey.QUERY.key());
		List<InterestBean> list = this.interestService.selectBySimilarName(mOtherInterest);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			jsonArray.put(list.get(i).getiName());
		}
		response.setContentType("text/plain; charset=UTF-8");
		Writer out = response.getWriter();
		out.write(jsonArray.toString());
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
		HttpSession session = request.getSession();
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		MemberKeyWordsBean mkwbean = new MemberKeyWordsBean();
		MemberBean mbean = null;
		MemberBean usermBean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());

		this.memberService.encapsulateAllWhenQueryingCondition(dataAndErrorsMap, request);
		mkwbean = this.memberService.setMemberKeyWordsBean(mkwbean, request);
		mkwbean = this.interest_DetailService.setMemberKeyWordsBean(mkwbean, request);
		mkwbean = this.interestService.setMemberKeyWordsBean(mkwbean, request);

		// 先用會員的模糊查尋搜索一次
		List<MemberBean> memberList = this.memberService.selectByKeyWords(mkwbean);
		// 再比較興趣看看(寬鬆：只要每個興趣項目有一符合就加入會員清單)
		memberList = this.interest_DetailService.queryConditionByComparingI_L(mkwbean, memberList);
		// 再比較其它興趣(嚴格：全部符合才加入會員清單)
		memberList = this.interest_DetailService.queryConditionByComparingOI_L(mkwbean, memberList);
		// 最後除去內定名為「系統」的會員
		memberList = this.memberService.getRidOfSystemMBean(memberList);
		mbean = this.memberService.getQueryConditionResult(memberList, usermBean);
		if (mbean == null) {
			dataAndErrorsMap.put(ConstUserKey.queryCondition.key() + ConstMemberParameter._ERROR.param(),
					QUERY_CONDITION_ERROR);
			return ConstMapping.QUERYCONDITION_SHOW.path();
		}

		response.sendRedirect(this.memberService.getOtherProfileUrl(mbean, request));
		return null;
	}

	/**
	 * 查詢會員：利用隨機查詢。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(path = { "/queryRandom.do" }, method = { RequestMethod.GET })
	public void queryRandomProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		HttpSession session = request.getSession();
		MemberBean usermBean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		MemberBean mbean = null;
		do {
			mbean = this.memberService.getQueryRandomResult(usermBean);
			mbean = this.memberService.getRidOfSystemMBean(mbean);
		} while (mbean == null);
		response.sendRedirect(this.memberService.getOtherProfileUrl(mbean, request));
	}
}