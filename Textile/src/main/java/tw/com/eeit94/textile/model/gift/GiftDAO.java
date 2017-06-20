package tw.com.eeit94.textile.model.gift;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface GiftDAO {

	GiftBean select(int giftId);

	List<GiftBean> select();

	GiftBean insert(GiftBean bean);

	GiftBean update(GiftBean bean);

	boolean delete(GiftBean bean);
}