package tw.com.eeit94.textile.controller.product;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
@RequestMapping(path = { "/store" })
@SessionAttributes(names = { "pList", "particular"})
public class ProductController {
	@Autowired
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	@RequestMapping(path = { "/list.do" })
	public String productList(Model model) {
		List<ProductBean> pList = getProductService().select(null);
		model.addAttribute("pList", pList);
		return "pList.show";
	}

	@RequestMapping(path = { "/single.do" }, method = { RequestMethod.GET })
	public String productSingle(ProductBean bean, BindingResult bindingResult, Model model) throws IOException {
		List<ProductBean> beans = getProductService().select(bean);
		ProductBean particular = beans.get(0);
		model.addAttribute("particular", particular);
		return "pSingle.show";
	}
	
	@RequestMapping(path = { "/showImg.do" }, method = { RequestMethod.GET })
	public void productShowImg(ProductBean bean, BindingResult bindingResult, HttpServletResponse response) throws IOException {
		ProductBean temp = getProductService().select(bean).get(0);
		OutputStream ops = response.getOutputStream();
		ops.write(temp.getImg());
		ops.close();
	}
	
	@RequestMapping(path = { "/maintain.do" })
	public String productMaintain(String maintainAction, ProductBean bean, BindingResult bindingResult, Model model) {
		return "pMaintenance.show";
	}
}
