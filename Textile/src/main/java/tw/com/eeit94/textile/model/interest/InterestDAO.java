package tw.com.eeit94.textile.model.interest;

import java.util.List;

/**
 * 控制興趣資料的DAO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public interface InterestDAO {

	public List<InterestBean> selectAll();

	public List<InterestBean> select(InterestBean ibean);

	public List<InterestBean> insert(InterestBean ibean);

	public List<InterestBean> update(InterestBean ibean);

	public List<InterestBean> delete(InterestBean ibean);
}