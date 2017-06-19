package tw.com.eeit94.textile.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemService;

@Controller
@RequestMapping(path = { "/item" })
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	public ItemService getItemService() {
		return itemService;
	}
	
	@RequestMapping(path = { "/iList.do" })
	public String itemList(Model model) {
		List<ItemBean> iList = getItemService().select(null); 
		model.addAttribute("iList", iList);
		return "iList.show";
	}
}
