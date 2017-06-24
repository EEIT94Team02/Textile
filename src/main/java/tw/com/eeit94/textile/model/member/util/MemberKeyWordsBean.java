package tw.com.eeit94.textile.model.member.util;

import java.util.LinkedHashMap;

/**
 * 專門用來備份查詢資料的條件封裝檔。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class MemberKeyWordsBean {
	/**
	 * 依照基本資料作查詢關鍵字。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	private String mEmail;
	private String mName;
	/**
	 * 以下開始為條件查詢使用。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 */
	private String mGender;
	private java.util.Date mBirthdayBegin;
	private java.util.Date mBirthdayEnd;
	// 這裡應該要拼成地址前段，如「臺北市」或「臺北市大安區」。
	private java.util.List<String> mAddress;
	/**
	 * 依照論壇經歷作查詢關鍵字。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	private Integer mScores;
	private java.sql.Timestamp mCreateTimeBegin;
	private java.sql.Timestamp mCreateTimeEnd;
	/**
	 * 依照個人狀況作查詢關鍵字。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	private Integer mCareer;
	private Integer mEducation;
	private Integer mEconomy;
	private Integer mMarriage;
	private Integer mFamily;
	private Integer mBloodType;
	private Integer mConstellation;
	private Integer mReligion;
	/**
	 * 依照個人喜好作查詢關鍵字。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	private Integer i_dMain;
	private String i_dOther;
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

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	public java.util.Date getmBirthdayBegin() {
		return mBirthdayBegin;
	}

	public void setmBirthdayBegin(java.util.Date mBirthdayBegin) {
		this.mBirthdayBegin = mBirthdayBegin;
	}

	public java.util.Date getmBirthdayEnd() {
		return mBirthdayEnd;
	}

	public void setmBirthdayEnd(java.util.Date mBirthdayEnd) {
		this.mBirthdayEnd = mBirthdayEnd;
	}

	public java.util.List<String> getmAddress() {
		return mAddress;
	}

	public void setmAddress(java.util.List<String> mAddress) {
		this.mAddress = mAddress;
	}

	public Integer getmScores() {
		return mScores;
	}

	public void setmScores(Integer mScores) {
		this.mScores = mScores;
	}

	public java.sql.Timestamp getmCreateTimeBegin() {
		return mCreateTimeBegin;
	}

	public void setmCreateTimeBegin(java.sql.Timestamp mCreateTimeBegin) {
		this.mCreateTimeBegin = mCreateTimeBegin;
	}

	public java.sql.Timestamp getmCreateTimeEnd() {
		return mCreateTimeEnd;
	}

	public void setmCreateTimeEnd(java.sql.Timestamp mCreateTimeEnd) {
		this.mCreateTimeEnd = mCreateTimeEnd;
	}

	public Integer getmCareer() {
		return mCareer;
	}

	public void setmCareer(Integer mCareer) {
		this.mCareer = mCareer;
	}

	public Integer getmEducation() {
		return mEducation;
	}

	public void setmEducation(Integer mEducation) {
		this.mEducation = mEducation;
	}

	public Integer getmEconomy() {
		return mEconomy;
	}

	public void setmEconomy(Integer mEconomy) {
		this.mEconomy = mEconomy;
	}

	public Integer getmMarriage() {
		return mMarriage;
	}

	public void setmMarriage(Integer mMarriage) {
		this.mMarriage = mMarriage;
	}

	public Integer getmFamily() {
		return mFamily;
	}

	public void setmFamily(Integer mFamily) {
		this.mFamily = mFamily;
	}

	public Integer getmBloodType() {
		return mBloodType;
	}

	public void setmBloodType(Integer mBloodType) {
		this.mBloodType = mBloodType;
	}

	public Integer getmConstellation() {
		return mConstellation;
	}

	public void setmConstellation(Integer mConstellation) {
		this.mConstellation = mConstellation;
	}

	public Integer getmReligion() {
		return mReligion;
	}

	public void setmReligion(Integer mReligion) {
		this.mReligion = mReligion;
	}

	public Integer getI_dMain() {
		return i_dMain;
	}

	public void setI_dMain(Integer i_dMain) {
		this.i_dMain = i_dMain;
	}

	public String getI_dOther() {
		return i_dOther;
	}

	public void setI_dOther(String i_dOther) {
		this.i_dOther = i_dOther;
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
		linkedHashMap.put("mEmail", this.getmEmail().toString());
		linkedHashMap.put("mName", this.getmName().toString());
		linkedHashMap.put("mGender", this.getmGender().toString());
		linkedHashMap.put("mBirthdayBegin", this.getmBirthdayBegin().toString());
		linkedHashMap.put("mBirthdayEnd", this.getmBirthdayEnd().toString());
		linkedHashMap.put("mAddress", this.getmAddress().toString());
		linkedHashMap.put("mScores", this.getmScores().toString());
		linkedHashMap.put("mCreateTimeBegin", this.getmCreateTimeBegin().toString());
		linkedHashMap.put("mCreateTimeEnd", this.getmCreateTimeEnd().toString());
		linkedHashMap.put("mCareer", this.getmCareer().toString());
		linkedHashMap.put("mEducation", this.getmEducation().toString());
		linkedHashMap.put("mEconomy", this.getmEconomy().toString());
		linkedHashMap.put("mMarriage", this.getmMarriage().toString());
		linkedHashMap.put("mFamily", this.getmFamily().toString());
		linkedHashMap.put("mBloodType", this.getmBloodType().toString());
		linkedHashMap.put("mConstellation", this.getmConstellation().toString());
		linkedHashMap.put("mReligion", this.getmReligion().toString());
		linkedHashMap.put("i_dMain", this.getI_dMain().toString());
		linkedHashMap.put("i_dOther", this.getI_dOther().toString());
		linkedHashMap.put("i_dRecreation", this.getI_dRecreation().toString());
		linkedHashMap.put("i_dOtherRecreation", this.getI_dOtherRecreation().toString());
		linkedHashMap.put("i_dExercises", this.getI_dExercises().toString());
		linkedHashMap.put("i_dOtherExercises", this.getI_dOtherExercises().toString());
		linkedHashMap.put("i_dDiet", this.getI_dDiet().toString());
		linkedHashMap.put("i_dOtherDiet", this.getI_dOtherDiet().toString());
		linkedHashMap.put("i_dArt", this.getI_dArt().toString());
		linkedHashMap.put("i_dOtherArt", this.getI_dOtherArt().toString());
		linkedHashMap.put("i_dDesign", this.getI_dDesign().toString());
		linkedHashMap.put("i_dOtherDesign", this.getI_dOtherDesign().toString());
		linkedHashMap.put("i_dMusic", this.getI_dMusic().toString());
		linkedHashMap.put("i_dOtherMusic", this.getI_dOtherMusic().toString());
		linkedHashMap.put("i_dHobbies", this.getI_dHobbies().toString());
		linkedHashMap.put("i_dOtherHobbies", this.getI_dOtherHobbies().toString());
		linkedHashMap.put("i_dActivities", this.getI_dActivities().toString());
		linkedHashMap.put("i_dOtherActivities", this.getI_dOtherActivities().toString());
		return linkedHashMap.toString();
	}
}