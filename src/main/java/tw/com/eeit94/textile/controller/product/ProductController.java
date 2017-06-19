package tw.com.eeit94.textile.controller.product;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@SessionAttributes(names = { "pList", "particular", "pMList"})
public class ProductController {
	@Autowired
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	@RequestMapping(path = { "/store/pList.do" })
	public String productList(Model model) {
		List<ProductBean> pList = getProductService().select(null);
		model.addAttribute("pList", pList);
		return "pList.show";
	}

	@RequestMapping(path = { "/store/pSingle.do" }, method = { RequestMethod.GET })
	public String productSingle(ProductBean bean, BindingResult bindingResult, Model model) throws IOException {
		List<ProductBean> beans = getProductService().select(bean);
		ProductBean particular = beans.get(0);
		model.addAttribute("particular", particular);
		return "pSingle.show";
	}
	
	@RequestMapping(path = { "/store/pShowImg.do" }, method = { RequestMethod.GET })
	public void productShowImg(ProductBean bean, BindingResult bindingResult, HttpServletResponse response) throws IOException {
		ProductBean temp = getProductService().select(bean).get(0);
		OutputStream ops = response.getOutputStream();
		ops.write(temp.getImg());
		ops.close();
	}
	
	@RequestMapping(path = { "/manager/pShowMaintain.do" })
	public String productMaintainShow(Model model) {
		List<ProductBean> pMaintainList = getProductService().select(null);
		model.addAttribute("pMList", pMaintainList);
		return "pMaintenance.show.r";
	}
	
	@RequestMapping(path = { "/store/pMaintain.do" }, method = { RequestMethod.POST })
	public String productMaintain(String maintainAction, String imgFileContent, ProductBean bean, BindingResult bindingResult, Model model) {
		// 接收
		// 轉換
		Map<Integer, Map<String, String>> errors = new HashMap<>();
		Map<String, String> dataError = new HashMap<>();
		
		model.addAttribute("errors", errors);
		if (bindingResult != null && bindingResult.hasErrors()) {
			if (bindingResult.getFieldError("unitPrice") != null) {
				dataError.put("pUP", "商品單價請輸入整數。");
			}
			if (bindingResult.getFieldError("rewardPoints") != null) {
				dataError.put("pRP", "商品點數請輸入整數。");
			}
			errors.put(bean.getProductId(), dataError);
		}
		if (imgFileContent != null && imgFileContent.trim().length() != 0) {
			String imgSub = imgFileContent.substring(imgFileContent.lastIndexOf(',') + 1);
			bean.setImg(Base64.getDecoder().decode(imgSub));
		} else {
			bean.setImg(getProductService().select(bean).get(0).getImg());
		}
		// 驗證
		if ("Update".equals(maintainAction) || "Delete".equals(maintainAction)) {
			
		}
		if (errors != null && !errors.isEmpty()) {
			return "pMaintenance.show";
		}
		// 呼叫model，根據model結果呼叫view
		if ("Update".equals(maintainAction)) {
			getProductService().update(bean);
			List<ProductBean> result = getProductService().select(null);
			model.addAttribute("pMList", result);
			return "pMaintenance.show.r";
		}
		return "pMaintenance.show";
	}
}
