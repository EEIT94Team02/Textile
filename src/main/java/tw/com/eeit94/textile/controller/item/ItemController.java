package tw.com.eeit94.textile.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemConditionUtil;
import tw.com.eeit94.textile.model.item.ItemService;

@Controller
@RequestMapping(path = { "/item" })
@SessionAttributes(names = { "iList" })
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	public ItemService getItemService() {
		return itemService;
	}
	
	@RequestMapping(path = { "/iList.do" }, method = { RequestMethod.GET })
	public String itemList(ItemConditionUtil itemCondition, Model model) {
		List<ItemBean> iList = getItemService().select(itemCondition); 
		model.addAttribute("iList", iList);
		return "iList.show";
	}
}
