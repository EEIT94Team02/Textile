package tw.com.eeit94.textile.model.product;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import tw.com.eeit94.textile.model.dealDetail.DealDetailBean;

/**
 * 封裝購物車資料的bean元件。
 * 
 * @author 李
 * @version 2017/06/21
 */
public class ShoppingCart {
	private Map<Integer, DealDetailBean> cart = new LinkedHashMap<>();

	public Map<Integer, DealDetailBean> getContent() {
		return cart;
	}

	public void addToCart(Integer productId, DealDetailBean incoming) {
		// 新加入購物車
		if (cart.get(productId) == null) {
			cart.put(productId, incoming);
		} else {
			// 商品加購
			DealDetailBean existing = cart.get(productId);
			existing.setAmount(existing.getAmount() + incoming.getAmount());
		}
	}

	// 修改商品數量
	public boolean adjustAmount(Integer productId, Integer newAmount) {
		if (cart.get(productId) != null && newAmount != null && !Integer.valueOf(0).equals(newAmount)) {
			cart.get(productId).setAmount(newAmount);
			return true;
		} else {
			return false;
		}
	}

	// 從購物車中刪除商品
	public boolean removeProduct(Integer productId) {
		if (cart.get(productId) != null) {
			cart.remove(productId);
			return true;
		} else {
			return false;
		}
	}

	// 取得購物車中所有商品的合計價格
	public int getSubtotal() {
		if (!cart.isEmpty()) {
			int subtotal = 0;
			Set<Integer> kSet = cart.keySet();
			for (Integer n : kSet) {
				Integer price = cart.get(n).getProductBean().getUnitPrice();
				Integer amount = cart.get(n).getAmount();
				subtotal += price.intValue() * amount.intValue();
			}
			return subtotal;
		} else {
			return 0;
		}
	}

	// 購物車中商品數量
	public int getSize() {
		return cart.size();

	}
}
