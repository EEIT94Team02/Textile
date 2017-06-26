package tw.com.eeit94.textile.controller.gift;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.com.eeit94.textile.model.gift.GiftBean;
import tw.com.eeit94.textile.model.gift.GiftConditionUtil;
import tw.com.eeit94.textile.model.gift.GiftService;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailBean;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailPK;
import tw.com.eeit94.textile.model.giftDetail.GiftDetailService;
import tw.com.eeit94.textile.model.item.ItemBean;
import tw.com.eeit94.textile.model.item.ItemPK;
import tw.com.eeit94.textile.model.item.ItemService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.product.ProductBean;
import tw.com.eeit94.textile.model.product.ProductService;
import tw.com.eeit94.textile.model.social.SocialListBean;
import tw.com.eeit94.textile.model.social.SocialListService;

@Controller
@RequestMapping(path = { "/gift" })
@SessionAttributes(names = { "gList", "iList" })
public class GiftController {
	@Autowired
	private GiftService giftService;

	@Autowired
	private GiftDetailService giftDetailService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private SocialListService socialListService;

	@InitBinder
	public void initMethod(WebDataBinder binder) {
		CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(java.util.Date.class, customDateEditor);
	}

	public GiftService getGiftService() {
		return giftService;
	}

	public GiftDetailService getGiftDetailService() {
		return giftDetailService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public SocialListService getSocialListService() {
		return socialListService;
	}

	@RequestMapping(path = { "/gListAll.do" })
	public String giftShow(HttpServletRequest request, GiftConditionUtil giftCondition, Model model) {
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("user");
		giftCondition.setGiverId(memberBean.getmId());
		List<GiftBean> gList = getGiftService().select(giftCondition);
		model.addAttribute("gList", gList);
		return "gListAll.show";
	}

	@RequestMapping(path = { "/gCondition.do" })
	public String giftConditional(GiftConditionUtil giftCondition, BindingResult bindingResult, Model model) {
		System.out.println(
				giftCondition.getGiftId() + ", " + giftCondition.getGiverId() + ", " + giftCondition.getGiverName()
						+ ", " + giftCondition.getRecipientId() + ", " + giftCondition.getRecipientName() + ", "
						+ giftCondition.getGiveDateAfter() + ", " + giftCondition.getGiveDateBefore());
		List<GiftBean> gList = getGiftService().select(giftCondition);
		model.addAttribute("gList", gList);
		return "gListAll.show";
	}

	@RequestMapping(path = { "/giftDetail.do" })
	public String giftDetail(GiftBean giftBean, Model model) {
		List<GiftDetailBean> result = getGiftDetailService().select(giftBean);
		model.addAttribute("giftDetailList", result);
		return "giftDetail.show";
	}

	@RequestMapping(path = { "/headToGiftSending.do" })
	public String headToSending(HttpServletRequest request, ItemPK itemPK, Model model) {
		Integer userId = ((MemberBean) request.getSession().getAttribute("user")).getmId();
		itemPK.setMemberId(userId);
		model.addAttribute("iList", getItemService().select(itemPK));
		return "giftSendingPage.show";
	}

	@RequestMapping(path = { "/giftSendingProceed.do" })
	public String giftSending(HttpServletRequest request, Integer recipientId, GiftBean giftBean,
			@RequestParam(value = "productId") Integer[] productId, @RequestParam(value = "amount") Integer[] amount,
			Model model) {
		MemberBean userBean = ((MemberBean) request.getSession().getAttribute("user"));
		MemberBean recipientBean = getMemberService().selectByPrimaryKey(recipientId);

		// 新增送禮記錄
		giftBean.setGiverBean(userBean);
		giftBean.setRecipientBean(recipientBean);
		giftBean.setGiveDate(new Timestamp(System.currentTimeMillis()));
		getGiftService().insert(giftBean);

		int reward = 0;

		// 新增送禮明細、修改或刪除物品欄
		for (int i = 0; i < productId.length; i++) {
			GiftDetailBean gdB = new GiftDetailBean();
			gdB.setGiftDetailPK(new GiftDetailPK(giftBean.getGiftId(), productId[i]));
			gdB.setAmount(amount[i]);
			getGiftDetailService().insert(gdB);

			// 修改或刪除會員物品欄中的物品
			ItemBean itemBean = getItemService().select(new ItemPK(userBean.getmId(), productId[i])).get(0);
			if (((itemBean.getAmount().intValue()) - (amount[i].intValue())) == 0) {
				getItemService().delete(itemBean);
			} else {
				itemBean.setAmount(Integer.valueOf(itemBean.getAmount().intValue()) - (amount[i].intValue()));
				getItemService().update(itemBean);
			}

			// 計算總積分
			reward += ((gdB.getProductBean().getRewardPoints().intValue()) * (amount[i].intValue()));

		}
		// 修改會員積分
		recipientBean.setmScores(Integer.valueOf((recipientBean.getmScores().intValue() + reward)));

		return "giftSending.success";
	}

	@RequestMapping(path = { "/socialList.do" })
	public void socialList(@RequestParam(value = "sTypes[]") List<String> sTypes, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		Integer userId = ((MemberBean) request.getSession().getAttribute("user")).getmId();
		List<SocialListBean> sList = getSocialListService().selectAllFriend(userId, sTypes);
		if (sList != null && sList.size() != 0) {
			JSONArray slja = new JSONArray();
			for (SocialListBean slBean : sList) {
				JSONObject slbj = new JSONObject();
				slbj.put("acquaintanceId", slBean.getSocialListPK().getAcquaintenceId());
				slbj.put("acquaintanceName", slBean.getMbean().getmName());
				slbj.put("type", slBean.getS_type());
				slja.put(slbj);
			}
			response.getWriter().write(slja.toString());
		} else {
			JSONObject errorJ = new JSONObject().put("error", "查無資料");
			response.getWriter().write(errorJ.toString());
		}
	}
}
