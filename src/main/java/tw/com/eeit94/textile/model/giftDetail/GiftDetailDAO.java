package tw.com.eeit94.textile.model.giftDetail;

import java.util.List;

/**
 * gift_detail表格CRUD的interface。
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface GiftDetailDAO {

	List<GiftDetailBean> select(Integer giftId);

	GiftDetailBean insert(GiftDetailBean bean);

	GiftDetailBean update(GiftDetailBean bean);

	boolean delete(GiftDetailBean bean);
}