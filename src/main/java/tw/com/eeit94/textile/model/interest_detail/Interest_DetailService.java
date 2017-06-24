package tw.com.eeit94.textile.model.interest_detail;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.interest.InterestService;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean.Item;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.util.CheckEmailExistValidator;
import tw.com.eeit94.textile.model.member.util.CheckInterest;
import tw.com.eeit94.textile.model.member.util.ConstMemberParameter;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

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
	private Interest_DetailDAO interest_DetailDAO;
	@Autowired
	private InterestService interestService;
	@Autowired
	private ExecutableValidator executableValidator;
	@Autowired
	private InterestBinaryConvertorService interestBinaryConvertorService;
	private static final String DEFAULT_ITEM_VALUE = null;
	private static final int DEFAULT_ITEM_KEY = 0;
	private static final byte DEFAULT_ITEM_SELECTED = 0;
	private static final byte NO = 0;
	private static final byte YES = 1;
	private static final int CATEGORY_MAX = 8;
	private static final int CHECKBOXES_MAX_PER_CATEGORY = 8;
	private static final int INPUTS_MAX_PER_CATEGORY = 5;
	private static final int CHECKBOXES_ALL = 8 * 8;
	private static final String EMPTY_JSONARRAY = "[]";
	private final ConstInterest_DetailKey[] constInterest_DetailKey = ConstInterest_DetailKey.values();

	/**
	 * 利用MemberBean的主鍵搜尋其一對一的興趣明細子表。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	@Transactional
	public Interest_DetailBean selectByPrimaryKey(MemberBean mbean) {
		Interest_DetailBean i_dbean = new Interest_DetailBean();
		i_dbean.setmId(mbean.getmId());
		return this.interest_DetailDAO.select(i_dbean).get(0);
	}

	/**
	 * 修改該會員的興趣明細子表。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	@Transactional
	public void updateInterest_DetailBean(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute("user");
		Interest_DetailBean i_dbean = mbean.getInterest_DetailBean();
		Interest_DetailNameListBean i_dnlbean = mbean.getI_d();
		Map<String, Item> items = i_dnlbean.getItems();
		this.setI_DNLBean(request);
		this.interestService.setNewInterestAndPrimaryKeySetForCheckbox(items);
		this.setInterest_DetailBean(i_dbean, items);
		this.interest_DetailDAO.update(i_dbean);
	}

	/**
	 * 修改或新增興趣時的最後步驟，將資料從Interest_DetailNameListBean抓出來，依照資料庫的設計邏輯，
	 * 
	 * 逐一封裝在Interest_Detail中。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link Interest_DetailNameListBean}
	 */
	public void setInterest_DetailBean(Interest_DetailBean i_dbean, Map<String, Item> items) {
		this.setInterest_DetailBeanForCheckbox(i_dbean, items);
		this.setInterest_DetailBeanForInput(i_dbean, items);
		this.setInterest_DetailBeanForI_DMain(i_dbean);
	}

	/**
	 * 將表單傳回的所有興趣封裝到Interest_DetailNameListBean，Key就是checkbox和input的name。
	 * 
	 * i1~i64記的是0或1，但o1~o40記的是興趣名稱，詳細說明請見this.getI_DNLBean()。
	 * 
	 * 這裡要記得如果表單傳過來時沒有該興趣的Key，應該修改item。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link Interest_DetailNameListBean}
	 */
	public void setI_DNLBean(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());
		Interest_DetailNameListBean i_dnlbean = mbean.getI_d();
		Map<String, Item> items = i_dnlbean.getItems();
		Item item = null;

		// 結束時，興趣名稱尚未有primary key，因為還沒用到InterestService。
		for (int i = 0; i < this.constInterest_DetailKey.length; i++) {
			String key = this.constInterest_DetailKey[i].key();
			item = items.get(key);
			if (request.getParameter(key) != null) {
				if (i < CHECKBOXES_ALL) {
					item.setSelected(YES);
				} else {
					item.setValue(request.getParameter(key));
					item.setSelected(YES);
				}
			} else {
				if (i < CHECKBOXES_ALL) {
					item.setSelected(NO);
				} else {
					item.setValue(DEFAULT_ITEM_VALUE);
					item.setKey(DEFAULT_ITEM_KEY);
					item.setSelected(DEFAULT_ITEM_SELECTED);
				}
			}
		}
	}

	/**
	 * 新增新的興趣時的最後步驟之一，將資料從Interest_DetailNameListBean抓出來，依照資料庫的設計邏輯，
	 * 
	 * 逐一封裝在Interest_Detail中。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link Interest_DetailNameListBean}
	 */
	public void setInterest_DetailBeanForCheckbox(Interest_DetailBean i_dbean, Map<String, Item> items) {
		Item item = null;
		int index = 0;
		int i_dInterest = 0;
		byte[] bs = new byte[CHECKBOXES_MAX_PER_CATEGORY];

		for (int category = 0; category < CATEGORY_MAX; category++) {
			for (int i = 0; i < CHECKBOXES_MAX_PER_CATEGORY; i++) {
				index = i + CHECKBOXES_MAX_PER_CATEGORY * category;
				item = items.get(this.constInterest_DetailKey[index].key());
				bs[i] = item.getSelected();
			}
			i_dInterest = this.interestBinaryConvertorService.bitsToInt(bs);
			this.setFieldForCheckboxPerCatogory(i_dbean, category, i_dInterest);
		}
	}

	/**
	 * 新增新的興趣時的最後步驟之一，將資料從Interest_DetailNameListBean抓出來，依照資料庫的設計邏輯，
	 * 
	 * 逐一封裝在Interest_Detail中。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link Interest_DetailNameListBean}
	 */
	public void setInterest_DetailBeanForInput(Interest_DetailBean i_dbean, Map<String, Item> items) {
		Item item = null;
		int index = 0;
		JSONArray jsonArray = null;

		for (int category = 0; category < CATEGORY_MAX; category++) {
			jsonArray = new JSONArray();
			for (int i = 0; i < INPUTS_MAX_PER_CATEGORY; i++) {
				index = CHECKBOXES_ALL + i + INPUTS_MAX_PER_CATEGORY * category;
				item = items.get(this.constInterest_DetailKey[index].key());
				if (item.getKey() > 0) {
					jsonArray.put(item.getKey());
				}
			}
			this.setFieldForInputPerCatogory(i_dbean, category, jsonArray);
		}
	}

	/**
	 * 新增新的興趣時的最後步驟之一，將i_dMain欄位數值更新(八大興趣的總指數)。
	 * 
	 * 如果在該「興趣分類」中，其數值大於0或其它興趣存在時，則byte陣列相對應的位數設為1。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	public void setInterest_DetailBeanForI_DMain(Interest_DetailBean i_dbean) {
		byte[] bs = new byte[CATEGORY_MAX];
		for (int category = 0; category < CATEGORY_MAX; category++) {
			int i_dInterest = this.getFieldForCheckboxPerCatogory(i_dbean, category);
			String i_dOtherInterest = this.getFieldForInputPerCatogory(i_dbean, category).toString();
			if (i_dInterest > 0 || !i_dOtherInterest.equals(EMPTY_JSONARRAY)) {
				bs[category] = YES;
			}
		}
		int x = this.interestBinaryConvertorService.bitsToInt(bs);
		i_dbean.setI_dMain(x);
	}

	/**
	 * 將興趣明細中與checkbox有關的int資料存入Interest_DetailBean。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	public void setFieldForCheckboxPerCatogory(Interest_DetailBean i_dbean, int category, Integer i_dInterest) {
		switch (category) {
		case 0:
			i_dbean.setI_dRecreation(i_dInterest);
			break;
		case 1:
			i_dbean.setI_dExercises(i_dInterest);
			break;
		case 2:
			i_dbean.setI_dDiet(i_dInterest);
			break;
		case 3:
			i_dbean.setI_dArt(i_dInterest);
			break;
		case 4:
			i_dbean.setI_dDesign(i_dInterest);
			break;
		case 5:
			i_dbean.setI_dMusic(i_dInterest);
			break;
		case 6:
			i_dbean.setI_dHobbies(i_dInterest);
			break;
		case 7:
			i_dbean.setI_dActivities(i_dInterest);
			break;
		}
	}

	/**
	 * 將興趣明細中與input有關的JSONArray資料存入Interest_DetailBean。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 */
	public void setFieldForInputPerCatogory(Interest_DetailBean i_dbean, int category, JSONArray jsonArray) {
		switch (category) {
		case 0:
			i_dbean.setI_dOtherRecreation(jsonArray.toString());
			break;
		case 1:
			i_dbean.setI_dOtherExercises(jsonArray.toString());
			break;
		case 2:
			i_dbean.setI_dOtherDiet(jsonArray.toString());
			break;
		case 3:
			i_dbean.setI_dOtherArt(jsonArray.toString());
			break;
		case 4:
			i_dbean.setI_dOtherDesign(jsonArray.toString());
			break;
		case 5:
			i_dbean.setI_dOtherMusic(jsonArray.toString());
			break;
		case 6:
			i_dbean.setI_dOtherHobbies(jsonArray.toString());
			break;
		case 7:
			i_dbean.setI_dOtherActivities(jsonArray.toString());
			break;
		}
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
	 * @see {@link InterestService}
	 */
	@Transactional
	public Interest_DetailNameListBean getI_DNLBean(Interest_DetailBean i_dbean) {
		Interest_DetailNameListBean i_dnlbean = new Interest_DetailNameListBean();
		Map<String, Item> items = i_dnlbean.getItems();
		this.getI_DNLBeanSelectedSetForCheckbox(i_dbean, items);
		this.getI_DNLBeanSelectedSetForInput(i_dbean, items);
		this.interestService.getI_DNLBeanValueSetForAll(items);
		return i_dnlbean;
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
			int index = 0;
			for (int i = 0; i < INPUTS_MAX_PER_CATEGORY; i++) {
				index = CHECKBOXES_ALL + i + INPUTS_MAX_PER_CATEGORY * category;
				if (i < jsonArray.length()) {
					Item item = items.get(this.constInterest_DetailKey[index].key());
					item.setKey(jsonArray.getInt(i));
					item.setSelected(YES);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 從Interest_DetailBean得到興趣明細中與checkbox有關的int資料。
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
	 * 從Interest_DetailBean得到興趣明細中與input有關的JSONArray格式。
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

	/**
	 * 驗證表單單一的資料，從HttpRequest封裝表單與會員有關的所有資料至Map物件，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * request參數中的「m」(METHOD)就是屬性名稱，「q」(QUERY)就是該屬性的值。(AJAX專用)
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link MemberService}
	 */
	public Map<String, String> encapsulateAndCheckOneData(Map<String, String> dataAndErrorsMap,
			HttpServletRequest request) {
		String mField = request.getParameter(ConstHelperKey.METHOD.key());
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), mField);
		dataAndErrorsMap.put(mField, request.getParameter(ConstHelperKey.QUERY.key()));
		dataAndErrorsMap = this.checkFormData(dataAndErrorsMap);
		dataAndErrorsMap.remove(ConstHelperKey.KEY.key());
		return dataAndErrorsMap;
	}

	/**
	 * 驗證表單單一興趣的資料，傳回封裝「錯誤資訊」和「表單原始資料」的Map物件。
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link MemberService}
	 */
	public Map<String, String> checkFormData(Map<String, String> dataAndErrorsMap) {
		// 自動找尋要驗證的資料，該Map的鍵「預設」從Map的「"KEY"」對應的Value來得到。
		String currentKey = dataAndErrorsMap.get(ConstHelperKey.KEY.key());
		String currentValue = dataAndErrorsMap.get(currentKey);

		// 自動找尋對應該資料的方法，參數的形態必須依序輸入。
		String firstCharRemovedKey = currentKey.substring(1, currentKey.length());
		Method method;
		try {
			method = Interest_DetailService.class.getMethod(new StringBuffer()
					.append(ConstMemberParameter.CHECK.param()).append(firstCharRemovedKey).toString(), String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 參數的值必須依序輸入。
		Object returnValue = currentValue;

		// 執行後回傳裝有是否違反限制條件的訊息，限制條件為方法的回傳值必須符合自己規定的條件。
		Set<ConstraintViolation<Interest_DetailService>> constraintViolations = this.executableValidator
				.validateReturnValue(this, method, returnValue);

		// 如果有錯誤訊息，自動封裝錯誤到Map，鍵值為原本該資料的Key加上「_error」。
		Iterator<ConstraintViolation<Interest_DetailService>> iterator = constraintViolations.iterator();
		while (iterator.hasNext()) {
			dataAndErrorsMap.put(
					new StringBuffer().append(currentKey).append(ConstMemberParameter._ERROR.param()).toString(),
					iterator.next().getMessage());
		}

		return dataAndErrorsMap;
	}

	/**
	 * 協助驗證表單的資料，傳回值會受到對應的Annotation檢驗。
	 * 
	 * 這樣的寫法對於國際化的方面有些寫死，若要國際化可能要在實作Validator的initialize()方法中想辦法取得MessageSource。
	 * 
	 * SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this)
	 * do this magic.
	 * 
	 * @author 賴
	 * @version 2017/06/22
	 * @see {@link MemberService}
	 * @see {@link CheckEmailExistValidator}
	 */
	@CheckInterest(column = "興趣")
	public String checkOtherRecreation(String otherRecreation) {
		return otherRecreation;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherExercises(String otherExercises) {
		return otherExercises;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherDiet(String otherDiet) {
		return otherDiet;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherArt(String otherArt) {
		return otherArt;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherDesign(String otherDesign) {
		return otherDesign;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherMusic(String otherMusic) {
		return otherMusic;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherHobbies(String otherHobbies) {
		return otherHobbies;
	}

	@CheckInterest(column = "興趣")
	public String checkOtherActivities(String otherActivities) {
		return otherActivities;
	}
}