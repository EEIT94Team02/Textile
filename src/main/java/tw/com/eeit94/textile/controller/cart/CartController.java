package tw.com.eeit94.textile.controller.cart;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.controller.product.PrimitiveNumberEditor;
import tw.com.eeit94.textile.model.dealDetail.DealDetailBean;
import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.model.product.ProductService;
import tw.com.eeit94.textile.model.product.ShoppingCart;

@Controller
@RequestMapping(path = { "/store" })
@SessionAttributes(names = { "cartList" })
public class CartController {
	@Autowired
	private ProductService productService;

	@InitBinder
	public void initCart(WebDataBinder binder) {
		PrimitiveNumberEditor integerEditor = new PrimitiveNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, integerEditor);
	}

	public ProductService getProductService() {
		return productService;
	}

	@RequestMapping(path = { "/cart.do" }, method = { RequestMethod.POST })
	public String setCart(HttpServletRequest request, ProductBean pBean, DealDetailBean bean, Model model) {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cartList");
		if (cart == null) {
			cart = new ShoppingCart();
			model.addAttribute("cartList", cart);
		}
		bean.setProductBean(getProductService().select(pBean).get(0));
		cart.addToCart(pBean.getProductId(), bean);
		return "addCart.success";
	}

	@RequestMapping(path = { "/adjust.do" }, method = { RequestMethod.POST })
	public String adjustCart(HttpServletRequest request, String adjustAction, 
								Integer productId, String newAmount, Model model) {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cartList");
		Map<String, String> errorDetail = new HashMap<>();
		Map<Integer, Map<String, String>> errors = new HashMap<>();
		model.addAttribute("errorDetail", errorDetail);
		model.addAttribute("errors", errors);
		Integer amount = null;
		// 轉換
		if (newAmount != null) {
			try {
				amount = Integer.parseInt(newAmount);
			} catch(NumberFormatException e) {
				errorDetail.put("nAmount", "請輸入整數。");
			}
		}
		// 驗證
		if ("Update".equals(adjustAction)) {
			if (amount != null && amount == 0) {
				errorDetail.put("nAmount", "請輸入大於零的數量或是移除此商品。");
			}
		}
		if (errorDetail != null && !errorDetail.isEmpty()) {
			errors.put(productId, errorDetail);
		}
		if (errors != null && !errors.isEmpty()) {
			return "adjust.error";
		}
		// 呼叫model view
		if ("Update".equals(adjustAction)) {
			cart.adjustAmount(productId, amount);
			return "adjust.success";
		} else if ("Delete".equals(adjustAction)) {
			cart.removeProduct(productId);
			return "adjust.success";
		} else {
			errorDetail.put("actionError", "不明操作: " + adjustAction);
			return "adjust.error";
		}
	}
	
	@RequestMapping( path = { "/clearCart.do" }, method = { RequestMethod.POST } )
	public String clearCart(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		ShoppingCart cartList = (ShoppingCart) session.getAttribute("cartList");
		if (cartList != null && cartList.getSize() > 0) {
			cartList.getContent().clear();
			return "adjust.success";
		} else {
			return "adjust.error";
		}
		
	}
}
