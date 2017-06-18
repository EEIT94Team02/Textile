package tw.com.eeit94.textile.controller.user;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.model.secure.SecureService;

/**
 * 驗證註冊表單的資料，並回傳註冊成功與否。
 * 
 * @author 賴
 * @version 2017/06/17
 * @see {@link MemberService}
 */
@Controller
@RequestMapping(path = { "/check/register.do" })
public class RegisterController {
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;

	/**
	 * 註冊的過程：註冊完新帳號不會自動登入，所以如果用原帳號登入，Session Scope的資料仍在。
	 * 
	 * 1. 檢查表單資料是否有誤，利用回傳的Map<String, String>物件，檢查是否有任何的Key其結尾附帶「_error」。
	 * 
	 * 
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 */
	@RequestMapping(method = { RequestMethod.POST })
	public String process(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, String> dataAndErrorsMap = new HashMap<>();
		// 下拉式選單的值不用驗證，但是得放入dataAndErrorsMap，發生錯誤時才能一併傳回。
		dataAndErrorsMap.put(ConstMemberKey.Gender.key(), request.getParameter(ConstMemberKey.Gender.key()));
		dataAndErrorsMap.put(ConstMemberKey.Adrress_County.key(),
				request.getParameter(ConstMemberKey.Adrress_County.key()));
		dataAndErrorsMap.put(ConstMemberKey.Adrress_Region.key(),
				request.getParameter(ConstMemberKey.Adrress_Region.key()));

		dataAndErrorsMap = this.memberService.encapsulateAndCheckAllData(dataAndErrorsMap, request);
		dataAndErrorsMap = this.memberService.checkNonexistentEmail(dataAndErrorsMap, request);
		dataAndErrorsMap = this.memberService.checkTheSamePassword(dataAndErrorsMap, request);

		Iterator<String> keys = dataAndErrorsMap.keySet().iterator();
		while (keys.hasNext()) {
			if (keys.next().endsWith(ConstMemberParameter._ERROR.param())) {
				request.setAttribute(ConstUserKey.DATAANDERRORSMAP.key(), dataAndErrorsMap);
				return "register.error";
			}
		}

		MemberBean mbean = this.memberService.getNewMemberBean(dataAndErrorsMap, request);
		mbean.setmId(100);
		System.out.println(mbean);

		return "";
	}
}