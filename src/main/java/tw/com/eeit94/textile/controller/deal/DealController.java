package tw.com.eeit94.textile.controller.deal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.deal.DealConditionUtil;
import tw.com.eeit94.textile.model.deal.DealService;

@Controller
@RequestMapping( path = { "/deal" } )
@SessionAttributes( names = { "dealList" } )
public class DealController {
	@Autowired
	private DealService dealService;
	
	public DealService getDealService() {
		return dealService;
	}
	
	@RequestMapping( path = { "/dealList.do" } )
	public String dealShow(DealConditionUtil dealCondition, Model model) {
		List<DealBean> dealList = getDealService().select(dealCondition);
		model.addAttribute("dealList", dealList);
		return "dealList.show";
	}
}
