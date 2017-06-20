package tw.com.eeit94.textile.model.interest;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 封裝興趣資料的VO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Entity
@Table(name = "interest")
public class InterestBean implements Serializable {
	private static final long serialVersionUID = -2543597340875928107L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iId;
	private Integer iClass;
	private String iName;

	public Integer getiId() {
		return iId;
	}

	public void setiId(Integer iId) {
		this.iId = iId;
	}

	public Integer getiClass() {
		return iClass;
	}

	public void setiClass(Integer iClass) {
		this.iClass = iClass;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("iId", this.getiId().toString());
		linkedHashMap.put("iClass", this.getiClass().toString());
		linkedHashMap.put("iName", this.getiName());
		return linkedHashMap.toString();
	}
}