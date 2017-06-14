package tw.com.eeit94.textile.model.dealDetail;

import java.util.List;

/**
 * 提供Spring MVC作為form-backing object使用，由controller取得資料並封裝多個bean元件。
 * 
 * @author 李
 * @version 2017/06/13
 */
public class DealDetailList {
	private List<DealDetailBean> dealDetailBeans;

	public void setDealDetailBeans(List<DealDetailBean> dealDetailBeans) {
		this.dealDetailBeans = dealDetailBeans;
	}

	public List<DealDetailBean> getDealDetailBeans() {
		return this.dealDetailBeans;
	}
}