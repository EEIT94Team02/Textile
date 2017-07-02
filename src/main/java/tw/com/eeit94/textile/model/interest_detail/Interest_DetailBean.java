package tw.com.eeit94.textile.model.interest_detail;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 封裝興趣明細資料的VO。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Entity
@Table(name = "interest_detail")
public class Interest_DetailBean implements java.io.Serializable {
	private static final long serialVersionUID = -3262249229168882376L;
	
	@Id
	private Integer mId;
	// Java沒有unsigned byte，所以使用範圍稍大的Integer。
	private Integer i_dMain;
	private Integer i_dRecreation;
	private String i_dOtherRecreation;
	private Integer i_dExercises;
	private String i_dOtherExercises;
	private Integer i_dDiet;
	private String i_dOtherDiet;
	private Integer i_dArt;
	private String i_dOtherArt;
	private Integer i_dDesign;
	private String i_dOtherDesign;
	private Integer i_dMusic;
	private String i_dOtherMusic;
	private Integer i_dHobbies;
	private String i_dOtherHobbies;
	private Integer i_dActivities;
	private String i_dOtherActivities;

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public Integer getI_dMain() {
		return i_dMain;
	}

	public void setI_dMain(Integer i_dMain) {
		this.i_dMain = i_dMain;
	}

	public Integer getI_dRecreation() {
		return i_dRecreation;
	}

	public void setI_dRecreation(Integer i_dRecreation) {
		this.i_dRecreation = i_dRecreation;
	}

	public String getI_dOtherRecreation() {
		return i_dOtherRecreation;
	}

	public void setI_dOtherRecreation(String i_dOtherRecreation) {
		this.i_dOtherRecreation = i_dOtherRecreation;
	}

	public Integer getI_dExercises() {
		return i_dExercises;
	}

	public void setI_dExercises(Integer i_dExercises) {
		this.i_dExercises = i_dExercises;
	}

	public String getI_dOtherExercises() {
		return i_dOtherExercises;
	}

	public void setI_dOtherExercises(String i_dOtherExercises) {
		this.i_dOtherExercises = i_dOtherExercises;
	}

	public Integer getI_dDiet() {
		return i_dDiet;
	}

	public void setI_dDiet(Integer i_dDiet) {
		this.i_dDiet = i_dDiet;
	}

	public String getI_dOtherDiet() {
		return i_dOtherDiet;
	}

	public void setI_dOtherDiet(String i_dOtherDiet) {
		this.i_dOtherDiet = i_dOtherDiet;
	}

	public Integer getI_dArt() {
		return i_dArt;
	}

	public void setI_dArt(Integer i_dArt) {
		this.i_dArt = i_dArt;
	}

	public String getI_dOtherArt() {
		return i_dOtherArt;
	}

	public void setI_dOtherArt(String i_dOtherArt) {
		this.i_dOtherArt = i_dOtherArt;
	}

	public Integer getI_dDesign() {
		return i_dDesign;
	}

	public void setI_dDesign(Integer i_dDesign) {
		this.i_dDesign = i_dDesign;
	}

	public String getI_dOtherDesign() {
		return i_dOtherDesign;
	}

	public void setI_dOtherDesign(String i_dOtherDesign) {
		this.i_dOtherDesign = i_dOtherDesign;
	}

	public Integer getI_dMusic() {
		return i_dMusic;
	}

	public void setI_dMusic(Integer i_dMusic) {
		this.i_dMusic = i_dMusic;
	}

	public String getI_dOtherMusic() {
		return i_dOtherMusic;
	}

	public void setI_dOtherMusic(String i_dOtherMusic) {
		this.i_dOtherMusic = i_dOtherMusic;
	}

	public Integer getI_dHobbies() {
		return i_dHobbies;
	}

	public void setI_dHobbies(Integer i_dHobbies) {
		this.i_dHobbies = i_dHobbies;
	}

	public String getI_dOtherHobbies() {
		return i_dOtherHobbies;
	}

	public void setI_dOtherHobbies(String i_dOtherHobbies) {
		this.i_dOtherHobbies = i_dOtherHobbies;
	}

	public Integer getI_dActivities() {
		return i_dActivities;
	}

	public void setI_dActivities(Integer i_dActivities) {
		this.i_dActivities = i_dActivities;
	}

	public String getI_dOtherActivities() {
		return i_dOtherActivities;
	}

	public void setI_dOtherActivities(String i_dOtherActivities) {
		this.i_dOtherActivities = i_dOtherActivities;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("mId", this.getmId().toString());
		linkedHashMap.put("i_dMain", this.getI_dMain().toString());
		linkedHashMap.put("i_dRecreation", this.getI_dRecreation().toString());
		linkedHashMap.put("i_dOtherRecreation", this.getI_dOtherRecreation());
		linkedHashMap.put("i_dExercises", this.getI_dExercises().toString());
		linkedHashMap.put("i_dOtherExercises", this.getI_dOtherExercises());
		linkedHashMap.put("i_dDiet", this.getI_dDiet().toString());
		linkedHashMap.put("i_dOtherDiet", this.getI_dOtherDiet());
		linkedHashMap.put("i_dArt", this.getI_dArt().toString());
		linkedHashMap.put("i_dOtherArt", this.getI_dOtherArt());
		linkedHashMap.put("i_dDesign", this.getI_dDesign().toString());
		linkedHashMap.put("i_dOtherDesign", this.getI_dOtherDesign());
		linkedHashMap.put("i_dMusic", this.getI_dMusic().toString());
		linkedHashMap.put("i_dOtherMusic", this.getI_dOtherMusic());
		linkedHashMap.put("i_dHobbies", this.getI_dHobbies().toString());
		linkedHashMap.put("i_dOtherHobbies", this.getI_dOtherHobbies());
		linkedHashMap.put("i_dActivities", this.getI_dActivities().toString());
		linkedHashMap.put("i_dOtherActivities", this.getI_dOtherActivities());
		return linkedHashMap.toString();
	}
}