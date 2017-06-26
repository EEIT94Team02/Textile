package tw.com.eeit94.textile.controller.deposit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.deposit.DepositBean;
import tw.com.eeit94.textile.model.deposit.DepositConditionUtil;
import tw.com.eeit94.textile.model.deposit.DepositService;

@Controller
@RequestMapping( path = { "/deposit" } )
@SessionAttributes( names= { "dList" } )
public class DepositController {
	@Autowired
	private DepositService depositService;
	
	public DepositService getDepositService() {
		return depositService;
	}
	
	@RequestMapping( path = { "/dList.do" } )
	public String depositShow(DepositConditionUtil depositCondition, Model model) {
		List<DepositBean> dList = getDepositService().select(depositCondition);
		model.addAttribute("dList", dList);
		return "dList.show";
	}
}
