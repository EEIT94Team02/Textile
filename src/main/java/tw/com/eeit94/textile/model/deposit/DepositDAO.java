package tw.com.eeit94.textile.model.deposit;

import java.util.List;

/**
 * deposit表格dao的interface
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface DepositDAO {
	
	List<DepositBean> selectConditional(DepositConditionUtil queryCondition);

	DepositBean insert(DepositBean bean);

	DepositBean update(DepositBean bean);

	boolean delete(DepositBean bean);
}