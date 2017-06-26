package tw.com.eeit94.textile.controller.deal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.deal.DealBean;
import tw.com.eeit94.textile.model.deal.DealConditionUtil;
import tw.com.eeit94.textile.model.deal.DealService;
import tw.com.eeit94.textile.model.dealDetail.DealDetailBean;
import tw.com.eeit94.textile.model.dealDetail.DealDetailPK;
import tw.com.eeit94.textile.model.dealDetail.DealDetailService;
import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemPK;
import tw.com.eeit94.textile.model.item.ItemService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.product.ShoppingCart;

@Controller
@RequestMapping( path = { "/deal" } )
@SessionAttributes( names = { "dealList" } )
public class DealController {
	@Autowired
	private DealService dealService;
	
	@Autowired
	private DealDetailService dealDetailService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ItemService itemService;
	
	@InitBinder
	public void initMethod(WebDataBinder binder) {
		CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(java.util.Date.class, customDateEditor);
	}
	
	public DealService getDealService() {
		return dealService;
	}
	
	public DealDetailService getDealDetailService() {
		return dealDetailService;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}
	
	public ItemService getItemService() {
		return itemService;
	}
	
	@RequestMapping( path = { "/dealList.do" } )
	public String dealShow(HttpServletRequest request, DealConditionUtil dealCondition, Model model) {
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		dealCondition.setMemberId(memberBean.getmId());
		List<DealBean> dealList = getDealService().select(dealCondition);
		model.addAttribute("dealList", dealList);
		return "dealList.show";
	}
	
	@RequestMapping( path = { "/dealDetail.do" } )
	public String dealDetailShow(DealConditionUtil dealCondition, Model model) {
		List<DealDetailBean> result = getDealDetailService().select(dealCondition);
		model.addAttribute("dealDetailList", result);
		return "dealDetail.show";
	}
	
	// 新增deal、dealDetail資料，更新item、member資料
	@RequestMapping( path = { "/buy.do" } )
	public String productBuy(HttpServletRequest request, DealBean dealBean, Model model) {
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cartList");
		
		// 確認用戶餘額不小於購物金額
		if (memberBean.getmPoints() >= cart.getSubtotal()) {
			// 取得購買商品資料
			Map<Integer, DealDetailBean> cartMap = cart.getContent();
			
			// 扣款
			memberBean.setmPoints(memberBean.getmPoints() - dealBean.getTotalCost());
			getMemberService().update(memberBean);
			
			// 建立交易資料並放入資料庫
			dealBean.setMemberBean(memberBean);
			dealBean.setDealDate(new Timestamp(System.currentTimeMillis()));
			getDealService().insert(dealBean);
			
			// 建立交易明細並放入資料庫
			Set<Integer> keySet = cartMap.keySet();
			for (int n : keySet) {
				DealDetailBean ddBean = cartMap.get(n);
				ddBean.setDealDetailPK(new DealDetailPK(dealBean.getDealId(), ddBean.getProductBean().getProductId()));
				ddBean.setDealBean(dealBean);
				getDealDetailService().insert(ddBean);
				
				// 依使用者道具欄新增或修改道具資料
				ItemBean iBean = getItemService().select(new ItemPK(memberBean.getmId(), ddBean.getProductBean().getProductId())).get(0);
				if (iBean == null) {
					iBean = new ItemBean();
					iBean.setItemPK(new ItemPK(memberBean.getmId(), ddBean.getProductBean().getProductId()));
					iBean.setAmount(ddBean.getAmount());
					getItemService().insert(iBean);
				} else {
					iBean.setAmount(iBean.getAmount().intValue() + ddBean.getAmount().intValue());
					getItemService().update(iBean);
				}
			}
			
			// 交易成功後，移除購物車
			session.removeAttribute("cartList");
			
			// 返回商品頁面
			model.addAttribute("purchaseSuccess", "購買成功。");
			return "deal.success";
		} else {
			model.addAttribute("purchaseError", "餘額不足。");
			return "deal.error";
		}
	}
}
