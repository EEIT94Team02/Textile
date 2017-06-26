package tw.com.eeit94.textile.controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemPK;
import tw.com.eeit94.textile.model.item.ItemService;
import tw.com.eeit94.textile.model.member.MemberBean;

@Controller
@RequestMapping(path = { "/item" })
@SessionAttributes(names = { "iList" })
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	public ItemService getItemService() {
		return itemService;
	}
	
	@RequestMapping(path = { "/iList.do" }, method = { RequestMethod.POST })
	public String itemList(HttpServletRequest request, ItemPK itemPK, Model model) {
		HttpSession session = request.getSession();
		MemberBean userBean = (MemberBean) session.getAttribute("user");
		itemPK.setMemberId(userBean.getmId());
		List<ItemBean> iList = getItemService().select(itemPK); 
		model.addAttribute("iList", iList);
		return "iList.show";
	}
}
