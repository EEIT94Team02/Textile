package tw.com.eeit94.textile.model.interest_detail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit94.textile.model.interest.InterestBean;
import tw.com.eeit94.textile.model.interest.InterestDAO;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean.Item;
import tw.com.eeit94.textile.model.member.MemberBean;

/**
 * 控制興趣明細資料的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
 * @see {@link MemberRollbackProviderService}
 * @see {@link InterestBinaryConvertorService}
 */
@Service
public class Interest_DetailService {
	@Autowired
	private InterestDAO interestDAO;
	@Autowired
	private Interest_DetailDAO interest_DetailDAO;
	@Autowired
	private InterestBinaryConvertorService interestBinaryConvertorService;
	private static final byte YES = 1;
	private static final int CATEGORY_MAX = 8;
	private static final int CHECKBOXES_MAX_PER_CATEGORY = 8;
	private static final int INPUTS_MAX_PER_CATEGORY = 5;
	private static final int CHECKBOXES_ALL = 8 * 8;
	private final ConstInterest_DetailKey[] constInterest_DetailKey = ConstInterest_DetailKey.values();

	/**
	 * 利用MemberBean的主鍵搜尋其一對一的興趣明細子表。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public Interest_DetailBean selectByPrimaryKey(MemberBean mbean) {
		Interest_DetailBean i_dbean = new Interest_DetailBean();
		i_dbean.setmId(mbean.getmId());
		return this.interest_DetailDAO.select(i_dbean).get(0);
	}

	/**
	 * 利用興趣明細資料得到更詳細的會員興趣明細表供表單呈現和封裝，Interest_DetailNameListBean建構時會做初始化，接著一系列的參數儲存。
	 * 
	 * 含checkbox和input(可以自己輸入)的興趣各分8類，其中前者各自又有8項，後者至多5項，兩者分開處理。
	 * 
	 * 流程為：先存入checkbox相關的興趣，再存入input相關的興趣，再存入所有興趣的名稱。
	 * 
	 * @author 賴
	 * @version 2017/06/20
	 * @see {@link Interest_DetailNameListBean}
	 */
	public Interest_DetailNameListBean getI_DNLBean(Interest_DetailBean i_dbean) {
		Interest_DetailNameListBean i_dnlbean = new Interest_DetailNameListBean();
		Map<String, Item> items = i_dnlbean.getItems();
		this.getI_DNLBeanSelectedSetForCheckbox(i_dbean, items);
		this.getI_DNLBeanSelectedSetForInput(i_dbean, items);
		this.getI_DNLBeanValueSetForAll(items);
		return i_dnlbean;
	}

	/**
	 * Interest_DetailNameListBean建構的最後一個步驟，將所有Key(指primary
	 * key)存在的Item找出它對應的興趣名稱(Value)並存入Item中。
	 * 
	 * @author 賴
	 * @version 2017/06/20
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
		int count = 0;
		while (newIterator.hasNext()) {
			key = newIterator.next();
			item = items.get(key);
			primaryKey = item.getKey();
			if (primaryKey > 0) {
				item.setValue(list.get(count).getiName());
				count++;
			}
		}
	}

	/**
	 * 得到興趣明細中與checkbox有關的int資料。
	 * 
	 * ConstInterest_DetailKey[]陣列中的索引，每8個進入下一個分類。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public void getI_DNLBeanSelectedSetForCheckbox(Interest_DetailBean i_dbean, Map<String, Item> items) {
		for (int category = 0; category < CATEGORY_MAX; category++) {
			int x = this.getFieldForCheckboxPerCatogory(i_dbean, category);
			byte[] bs = this.interestBinaryConvertorService.intToBits(x);
			int index = 0;
			for (int i = 0; i < CHECKBOXES_MAX_PER_CATEGORY; i++) {
				index = i + CHECKBOXES_MAX_PER_CATEGORY * category;
				Item item = items.get(this.constInterest_DetailKey[index].key());
				item.setSelected(bs[i]);
			}
		}
	}

	/**
	 * 得到興趣明細中與input有關的int資料(要先從JSONArray取出其值)。
	 * 
	 * ConstInterest_DetailKey[]陣列中的索引，每5個進入下一個分類，注意必須從64開始。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public void getI_DNLBeanSelectedSetForInput(Interest_DetailBean i_dbean, Map<String, Item> items) {
		for (int category = 0; category < CATEGORY_MAX; category++) {
			JSONArray jsonArray = this.getFieldForInputPerCatogory(i_dbean, category);
			int index = CHECKBOXES_ALL;
			for (int i = 0; i < INPUTS_MAX_PER_CATEGORY; i++) {
				index = i + INPUTS_MAX_PER_CATEGORY * category;
				if (i < jsonArray.length()) {
					Item item = items.get(this.constInterest_DetailKey[index].key());
					item.setKey(jsonArray.getInt(index));
					item.setSelected(YES);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 得到興趣明細中與checkbox有關的int資料。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public int getFieldForCheckboxPerCatogory(Interest_DetailBean i_dbean, int category) {
		int x = 0;
		switch (category) {
		case 0:
			x = i_dbean.getI_dRecreation();
			break;
		case 1:
			x = i_dbean.getI_dExercises();
			break;
		case 2:
			x = i_dbean.getI_dDiet();
			break;
		case 3:
			x = i_dbean.getI_dArt();
			break;
		case 4:
			x = i_dbean.getI_dDesign();
			break;
		case 5:
			x = i_dbean.getI_dMusic();
			break;
		case 6:
			x = i_dbean.getI_dHobbies();
			break;
		case 7:
			x = i_dbean.getI_dActivities();
			break;
		}
		return x;
	}

	/**
	 * 得到興趣明細中與input有關的JSONArray格式。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public JSONArray getFieldForInputPerCatogory(Interest_DetailBean i_dbean, int category) {
		JSONArray jsonArray = null;
		switch (category) {
		case 0:
			jsonArray = new JSONArray(i_dbean.getI_dOtherRecreation());
			break;
		case 1:
			jsonArray = new JSONArray(i_dbean.getI_dOtherExercises());
			break;
		case 2:
			jsonArray = new JSONArray(i_dbean.getI_dOtherDiet());
			break;
		case 3:
			jsonArray = new JSONArray(i_dbean.getI_dOtherArt());
			break;
		case 4:
			jsonArray = new JSONArray(i_dbean.getI_dOtherDesign());
			break;
		case 5:
			jsonArray = new JSONArray(i_dbean.getI_dOtherMusic());
			break;
		case 6:
			jsonArray = new JSONArray(i_dbean.getI_dOtherHobbies());
			break;
		case 7:
			jsonArray = new JSONArray(i_dbean.getI_dOtherActivities());
			break;
		}
		return jsonArray;
	}

	/**
	 * 註冊成功時，初始化使用者個人的興趣明細資料，交易統一由MemberRollbackRroviderService管理。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 * @see {@link RegisterController}
	 */
	public Interest_DetailBean getNewInterest_DetailBean(MemberBean mbean) {
		Interest_DetailBean i_dbean = new Interest_DetailBean();
		i_dbean.setmId(mbean.getmId());
		i_dbean.setI_dMain(0);
		i_dbean.setI_dRecreation(0);
		i_dbean.setI_dOtherRecreation("[]");
		i_dbean.setI_dExercises(0);
		i_dbean.setI_dOtherExercises("[]");
		i_dbean.setI_dDiet(0);
		i_dbean.setI_dOtherDiet("[]");
		i_dbean.setI_dArt(0);
		i_dbean.setI_dOtherArt("[]");
		i_dbean.setI_dDesign(0);
		i_dbean.setI_dOtherDesign("[]");
		i_dbean.setI_dMusic(0);
		i_dbean.setI_dOtherMusic("[]");
		i_dbean.setI_dHobbies(0);
		i_dbean.setI_dOtherHobbies("[]");
		i_dbean.setI_dActivities(0);
		i_dbean.setI_dOtherActivities("[]");
		return this.interest_DetailDAO.insert(i_dbean).get(0);
	}
}