package tw.com.eeit94.textile.model.giftDetail;

import java.util.List;

/**
 * 提供Spring MVC作為form-backing object使用，由controller取得資料並封裝多個bean元件。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class GiftDetailList {
	private List<GiftDetailBean> giftDetailBeans;

	public void setGiftDetailBeans(List<GiftDetailBean> giftDetailBeans) {
		this.giftDetailBeans = giftDetailBeans;
	}

	public List<GiftDetailBean> getGiftDetailBeanS() {
		return giftDetailBeans;
	}
}