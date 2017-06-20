package tw.com.eeit94.textile.model.secure;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 封裝金鑰資料的VO，資料儲存後不可修改或刪除，往後只能新增或讀取。
 * 
 * @author 賴
 * @version 2017/06/11
 */
@Entity
@Table(name = "secure")
public class SecureBean implements java.io.Serializable {
	private static final long serialVersionUID = 7069449485173775187L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sId;
	private String sKey;
	private byte[] sInitVector;
	private String sTarget;

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	public byte[] getsInitVector() {
		return sInitVector;
	}

	public void setsInitVector(byte[] sInitVector) {
		this.sInitVector = sInitVector;
	}

	public String getsTarget() {
		return sTarget;
	}

	public void setsTarget(String sTarget) {
		this.sTarget = sTarget;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("sId", this.getsId().toString());
		linkedHashMap.put("sKey", this.getsKey().toString());
		linkedHashMap.put("sInitVector", this.getsInitVector().toString());
		linkedHashMap.put("sTarget", this.getsTarget().toString());
		return linkedHashMap.toString();
	}
}