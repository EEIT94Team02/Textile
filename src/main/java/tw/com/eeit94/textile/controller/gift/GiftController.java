package tw.com.eeit94.textile.controller.gift;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.gift.GiftBean;
import tw.com.eeit94.textile.model.gift.GiftConditionUtil;
import tw.com.eeit94.textile.model.gift.GiftService;

@Controller
@RequestMapping( path = { "/gift" } )
@SessionAttributes( names = { "gList" } )
public class GiftController {
	@Autowired
	private GiftService giftService;
	
	public GiftService getGiftService() {
		return giftService;
	}
	
	@RequestMapping( path = { "/gListAll.do" } )
	public String giftShow(GiftConditionUtil giftCondition, Model model) {
		List<GiftBean> gList = getGiftService().select(giftCondition);
		model.addAttribute("gList", gList);
		return "gListAll.show";
	}
}
