package tw.com.eeit94.textile.model.dealDetail;

import java.util.List;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface DealDetailDAO {

	DealDetailBean select(DealDetailPK dealDetailPK);

	List<DealDetailBean> select();

	DealDetailBean insert(DealDetailBean bean);

	DealDetailBean update(DealDetailBean bean);

	boolean delete(DealDetailBean bean);
}