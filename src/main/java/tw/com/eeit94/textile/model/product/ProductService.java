package tw.com.eeit94.textile.model.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * product表格CRUD的Service。
 * 
 * @author 李
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
public class ProductService {
	@Autowired
	private ProductDAO productDAO = null;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	@Transactional(readOnly = true)
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if (bean != null && bean.getProductId() != null && !Integer.valueOf(0).equals(bean.getProductId())) {
			bean = getProductDAO().select(bean.getProductId());
			if (bean != null) {
				result = new ArrayList<ProductBean>();
				result.add(bean);
			} else {
				result = getProductDAO().select();
			}
		} else {
			result = getProductDAO().select();
		}
		return result;
	}

	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = getProductDAO().insert(bean);
		}
		return result;
	}

	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = getProductDAO().update(bean);
		}
		return result;
	}

	public boolean delete(ProductBean bean) {
		if (bean != null) {
			return getProductDAO().delete(bean);
		}
		return false;
	}
}