package tw.com.eeit94.textile.model.product;

import java.util.List;

/**
 * product表格DAO的interface
 * 
 * @author 李
 * @version 2017/06/12
 */
public interface ProductDAO {

	ProductBean select(int productId);

	List<ProductBean> select();

	ProductBean insert(ProductBean bean);

	ProductBean update(ProductBean bean);

	boolean delete(ProductBean bean);
}