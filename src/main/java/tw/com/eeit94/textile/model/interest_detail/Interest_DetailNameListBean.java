package tw.com.eeit94.textile.model.interest_detail;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 封裝會員的興趣明細的資料，能夠直接供表單取用。
 * 
 * @author 賴
 * @version 2017/06/19
 */
public class Interest_DetailNameListBean {
	private Map<String, Item> items;

	Interest_DetailNameListBean() {
		items = new LinkedHashMap<>();
		ConstInterest_DetailKey[] constInterest_DetailKey = ConstInterest_DetailKey.values();
		Item item = null;
		for (int i = 0; i < constInterest_DetailKey.length; i++) {
			item = new Item(constInterest_DetailKey[i].key(), null, constInterest_DetailKey[i].primaryKey(), (byte) 0);
			items.put(constInterest_DetailKey[i].key(), item);
		}
	}

	public Map<String, Item> getItems() {
		return items;
	}

	public void setItems(Map<String, Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, Item> linkedHashMap = new LinkedHashMap<>();
		Iterator<String> iterator = items.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Item item = this.items.get(key);
			linkedHashMap.put(key, item);
		}
		return linkedHashMap.toString();
	}

	/**
	 * 此內部類別專門封裝表單興趣明細的每個checkbox和input。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public class Item {
		private String name;
		private String value;
		private Integer key;
		private Byte selected;

		Item(String name, String value, Integer key, Byte selected) {
			this.name = name;
			this.value = value;
			this.key = key;
			this.selected = selected;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public Byte getSelected() {
			return selected;
		}

		public void setSelected(Byte selected) {
			this.selected = selected;
		}

		@Override
		public String toString() {
			LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
			linkedHashMap.put("name", this.name);
			linkedHashMap.put("value", this.value);
			linkedHashMap.put("key", this.key.toString());
			linkedHashMap.put("selected", this.selected.toString());
			return linkedHashMap.toString();
		}
	}
}