package tw.com.eeit94.textile.model.interest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit94.textile.model.interest_detail.ConstInterest_DetailKey;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean.Item;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;

/**
 * 控制興趣資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Service
public class InterestService {
	@Autowired
	private InterestDAO interestDAO;
	private static final byte YES = 1;
	private static final int INPUTS_MAX_PER_CATEGORY = 5;
	private static final int CHECKBOXES_ALL = 8 * 8;
	private static final int RECREATION_CATEGORY = 1;
	private static final int EXERCISES_CATEGORY = 2;
	private static final int DIET_CATEGORY = 3;
	private static final int ART_CATEGORY = 4;
	private static final int DESIGN_CATEGORY = 5;
	private static final int MUSIC_CATEGORY = 6;
	private static final int HOBBIES_CATEGORY = 7;
	private static final int ACTIVITIES_CATEGORY = 8;
	private final ConstInterest_DetailKey[] constInterest_DetailKey = ConstInterest_DetailKey.values();

	/**
	 * 利用相似的興趣名稱來尋找InterestBean。
	 * 
	 * @author 賴
	 * @version 2017/06/24
	 */
	public List<InterestBean> selectBySimilarName(String iName) {
		List<InterestBean> list = this.interestDAO.selectBySimilarName(iName);
		return list;
	}

	/**
	 * 利用興趣名稱來尋找InterestBean。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	public InterestBean selectByName(String iName) {
		List<InterestBean> list = this.interestDAO.selectByName(iName);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 將Interest_DetailNameListBean中新的興趣名稱新增到interest的表格中。
	 * 
	 * 這裡要記得如果表單傳過來時沒有該興趣的Key，應該修改item。
	 * 
	 * 注意：興趣表格中的興趣名稱「iName」，有唯一的限制，必須檢查。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link Interest_DetailNameListBean}
	 */
	public void setNewInterestAndPrimaryKeySetForCheckbox(Map<String, Item> items) {
		Item item = null;
		InterestBean ibean = null;

		for (int i = CHECKBOXES_ALL; i < this.constInterest_DetailKey.length; i++) {
			String key = this.constInterest_DetailKey[i].key();
			item = items.get(key);
			if (item.getSelected() == YES) {
				String iName = item.getValue();
				ibean = this.selectByName(iName);
				if (ibean == null) {
					ibean = new InterestBean();
					ibean.setiClass(this.getClassOfInterest(i));
					ibean.setiName(item.getValue());
					ibean = this.interestDAO.insert(ibean).get(0);
				}
				// 新增完新的興趣後，將primary key記在Item中。
				item.setKey(ibean.getiId());
			}
		}
	}

	/**
	 * 找到從input輸入的其它興趣的name(即o1~o40)，其分別屬於哪一興趣分類。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link Interest_DetalService}
	 */
	public int getClassOfInterest(int i) {
		// 興趣分類序號從1開始，範圍為1到8。
		int iClass = (i - CHECKBOXES_ALL) / INPUTS_MAX_PER_CATEGORY + 1;
		// 這步其實是完全不需要的，但為了讓程式碼更容易管理，故逐一列出。
		switch (iClass) {
		case 1:
			iClass = RECREATION_CATEGORY;
			break;
		case 2:
			iClass = EXERCISES_CATEGORY;
			break;
		case 3:
			iClass = DIET_CATEGORY;
			break;
		case 4:
			iClass = ART_CATEGORY;
			break;
		case 5:
			iClass = DESIGN_CATEGORY;
			break;
		case 6:
			iClass = MUSIC_CATEGORY;
			break;
		case 7:
			iClass = HOBBIES_CATEGORY;
			break;
		case 8:
			iClass = ACTIVITIES_CATEGORY;
			break;
		}

		return iClass;
	}

	/**
	 * Interest_DetailNameListBean建構的最後一個步驟，將所有Key(指primary
	 * key)存在的Item找出它對應的興趣名稱(Value)並存入Item中。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @see {@link Interest_DetailService}
	 */
	public void getI_DNLBeanValueSetForAll(Map<String, Item> items) {
		List<Integer> primaryKeys = new ArrayList<>();
		Iterator<String> iterator = items.keySet().iterator();
		String key = null;
		Integer primaryKey = null;
		Item item = null;
		while (iterator.hasNext()) {
			key = iterator.next();
			primaryKey = items.get(key).getKey();
			if (primaryKey > 0) {
				primaryKeys.add(primaryKey);
			}
		}

		List<InterestBean> list = this.interestDAO.selectByPrimaryKeys(primaryKeys);
		Iterator<String> newIterator = items.keySet().iterator();
		while (newIterator.hasNext()) {
			key = newIterator.next();
			item = items.get(key);
			primaryKey = item.getKey();
			if (primaryKey > 0) {
				int count = 0;
				while (count < list.size()) {
					if (primaryKey == list.get(count).getiId()) {
						item.setValue(list.get(count).getiName());
					}
					count++;
				}
			}
		}
	}
}