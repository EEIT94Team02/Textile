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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.model.product.ProductService;

@Controller
@SessionAttributes(names = { "pList", "particular", "pMList" })
public class ProductController {
	@Autowired
	private ProductService productService;

	@InitBinder
	public void controllerInit(WebDataBinder binder) {
		PrimitiveNumberEditor integerEditor = new PrimitiveNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, integerEditor);
	}

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
	public void productShowImg(ProductBean bean, BindingResult bindingResult, HttpServletResponse response)
			throws IOException {
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
	public String productMaintain(String maintainAction, String imgFileContent, Integer insertCount, ProductBean bean,
			BindingResult bindingResult, Model model) {
		// 接收
		// 轉換
		Map<Integer, Map<String, String>> errors = new HashMap<>();
		Map<String, String> dataError = new HashMap<>();
		List<ProductBean> result = null;
		model.addAttribute("errors", errors);
		model.addAttribute("dataError", dataError);
		model.addAttribute("icInteger", insertCount);

		if (bindingResult != null && bindingResult.hasErrors()) {
			if (bindingResult.getFieldError("unitPrice") != null) {
				dataError.put("pUP", "請輸入整數。");
			}
			if (bindingResult.getFieldError("rewardPoints") != null) {
				dataError.put("pRP", "請輸入整數。");
			}
		}
		// 驗證圖片
		if (imgFileContent != null && imgFileContent.trim().length() != 0) {
			String imgSub = imgFileContent.substring(imgFileContent.lastIndexOf(',') + 1);
			bean.setImg(Base64.getDecoder().decode(imgSub));
		} else if (bean.getProductId() == null) {
			dataError.put("pImg", "請選擇一張圖片。");
		} else if (bean.getImg() != null) {
			System.out.println("go in getImg");
			bean.setImg(getProductService().select(bean).get(0).getImg());
		}
		// 驗證其他不可空白或不可輸入null值的資料
		if ("修改".equals(maintainAction) || "新增".equals(maintainAction)) {
			if (bean.getProductName() == null || bean.getProductName().trim().length() == 0) {
				dataError.put("pNa", "請輸入商品名稱。");
			}
			if (bean.getUnitPrice() == null || bean.getUnitPrice() == 0) {
				dataError.put("pUP", "請輸入商品單價。");
			}
			if (bean.getIntro() == null || bean.getIntro().trim().length() == 0) {
				dataError.put("pIn", "請輸入商品簡介。");
			}
			if (bean.getRewardPoints() == null || bean.getRewardPoints() == 0) {
				dataError.put("pRP", "請輸入商品點數。");
			}
		}
		// 傳回錯誤訊息
		if (dataError != null && !dataError.isEmpty()) {
			if ("Insert".equals(maintainAction)) {
				errors.put(insertCount, dataError);
			} else {
				errors.put(bean.getProductId(), dataError);
			}
			return "pMaintenance.error";
		}
		// 呼叫model，根據model結果呼叫view
		if ("修改".equals(maintainAction)) {
			getProductService().update(bean);
			result = getProductService().select(null);
			model.addAttribute("pMList", result);
			return "pMaintenance.show.r";
		} else if ("新增".equals(maintainAction)) {
			getProductService().insert(bean);
			result = getProductService().select(null);
			model.addAttribute("pMList", result);
			return "pMaintenance.show.r";
		} else if ("刪除".equals(maintainAction)) {
			getProductService().delete(bean);
			result = getProductService().select(null);
			model.addAttribute("pMList", result);
			return "pMaintenance.show.r";
		} else {
			dataError.put("actionError", "不明操作: " + maintainAction);
			return "pMaintenance.error";
		}
	}
}
