package tw.com.eeit94.textile.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.model.product.ProductService;

@Controller
@RequestMapping
@SessionAttributes(names = { "pList", "particular" })
public class ProductController {
	@Autowired
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	@RequestMapping(path = { "/product/list.do" })
	public String productList(Model model) {
		List<ProductBean> pList = getProductService().select(null);
		model.addAttribute("pList", pList);
		return "pList.show";
	}

	@RequestMapping(path = { "/product/single.do" }, method = { RequestMethod.GET })
	public String productSingle(ProductBean bean, BindingResult bindingResult, Model model) {
		List<ProductBean> beans = getProductService().select(bean);
		ProductBean particular = beans.get(0);
		model.addAttribute("particular", particular);
		return "pSingle.show";
	}
}
