package tw.com.eeit94.textile.model.member.service;

import java.util.LinkedHashMap;

import tw.com.eeit94.textile.model.interest.InterestService;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailService;
import tw.com.eeit94.textile.model.member.MemberService;

/**
 * 專門用來備份查詢資料的條件封裝檔。
 * 
 * @author 賴
 * @version 2017/06/08
 * @see {@link MemberService}
 * @see {@link Interest_DetailService}
 * @see {@link InterestService}
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
	private String mAddress_County;
	private java.util.List<String> mAddress_Region;
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
	private Integer i_dRecreation;
	private Integer i_dExercises;
	private Integer i_dDiet;
	private Integer i_dArt;
	private Integer i_dDesign;
	private Integer i_dMusic;
	private Integer i_dHobbies;
	private Integer i_dActivities;
	private java.util.List<Integer> i_dOtherInterest;

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

	public String getmAddress_County() {
		return mAddress_County;
	}

	public void setmAddress_County(String mAddress_County) {
		this.mAddress_County = mAddress_County;
	}

	public java.util.List<String> getmAddress_Region() {
		return mAddress_Region;
	}

	public void setmAddress_Region(java.util.List<String> mAddress_Region) {
		this.mAddress_Region = mAddress_Region;
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

	public Integer getI_dRecreation() {
		return i_dRecreation;
	}

	public void setI_dRecreation(Integer i_dRecreation) {
		this.i_dRecreation = i_dRecreation;
	}

	public Integer getI_dExercises() {
		return i_dExercises;
	}

	public void setI_dExercises(Integer i_dExercises) {
		this.i_dExercises = i_dExercises;
	}

	public Integer getI_dDiet() {
		return i_dDiet;
	}

	public void setI_dDiet(Integer i_dDiet) {
		this.i_dDiet = i_dDiet;
	}

	public Integer getI_dArt() {
		return i_dArt;
	}

	public void setI_dArt(Integer i_dArt) {
		this.i_dArt = i_dArt;
	}

	public Integer getI_dDesign() {
		return i_dDesign;
	}

	public void setI_dDesign(Integer i_dDesign) {
		this.i_dDesign = i_dDesign;
	}

	public Integer getI_dMusic() {
		return i_dMusic;
	}

	public void setI_dMusic(Integer i_dMusic) {
		this.i_dMusic = i_dMusic;
	}

	public Integer getI_dHobbies() {
		return i_dHobbies;
	}

	public void setI_dHobbies(Integer i_dHobbies) {
		this.i_dHobbies = i_dHobbies;
	}

	public Integer getI_dActivities() {
		return i_dActivities;
	}

	public void setI_dActivities(Integer i_dActivities) {
		this.i_dActivities = i_dActivities;
	}

	public java.util.List<Integer> getI_dOtherInterest() {
		return i_dOtherInterest;
	}

	public void setI_dOtherInterest(java.util.List<Integer> i_dOtherInterest) {
		this.i_dOtherInterest = i_dOtherInterest;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("mEmail", this.getmEmail() == null ? null : this.getmEmail().toString());
		linkedHashMap.put("mName", this.getmName() == null ? null : this.getmName().toString());
		linkedHashMap.put("mGender", this.getmGender() == null ? null : this.getmGender().toString());
		linkedHashMap.put("mBirthdayBegin",
				this.getmBirthdayBegin() == null ? null : this.getmBirthdayBegin().toString());
		linkedHashMap.put("mBirthdayEnd", this.getmBirthdayEnd() == null ? null : this.getmBirthdayEnd().toString());
		linkedHashMap.put("mAddress_County",
				this.getmAddress_County() == null ? null : this.getmAddress_County().toString());
		linkedHashMap.put("mAddress_Region",
				this.getmAddress_Region() == null ? null : this.getmAddress_Region().toString());
		linkedHashMap.put("mScores", this.getmScores() == null ? null : this.getmScores().toString());
		linkedHashMap.put("mCreateTimeBegin",
				this.getmCreateTimeBegin() == null ? null : this.getmCreateTimeBegin().toString());
		linkedHashMap.put("mCreateTimeEnd",
				this.getmCreateTimeEnd() == null ? null : this.getmCreateTimeEnd().toString());
		linkedHashMap.put("mCareer", this.getmCareer() == null ? null : this.getmCareer().toString());
		linkedHashMap.put("mEducation", this.getmEducation() == null ? null : this.getmEducation().toString());
		linkedHashMap.put("mEconomy", this.getmEconomy() == null ? null : this.getmEconomy().toString());
		linkedHashMap.put("mMarriage", this.getmMarriage() == null ? null : this.getmMarriage().toString());
		linkedHashMap.put("mFamily", this.getmFamily() == null ? null : this.getmFamily().toString());
		linkedHashMap.put("mBloodType", this.getmBloodType() == null ? null : this.getmBloodType().toString());
		linkedHashMap.put("mConstellation",
				this.getmConstellation() == null ? null : this.getmConstellation().toString());
		linkedHashMap.put("mReligion", this.getmReligion() == null ? null : this.getmReligion().toString());
		linkedHashMap.put("i_dMain", this.getI_dMain() == null ? null : this.getI_dMain().toString());
		linkedHashMap.put("i_dRecreation", this.getI_dRecreation() == null ? null : this.getI_dRecreation().toString());
		linkedHashMap.put("i_dExercises", this.getI_dExercises() == null ? null : this.getI_dExercises().toString());
		linkedHashMap.put("i_dDiet", this.getI_dDiet() == null ? null : this.getI_dDiet().toString());
		linkedHashMap.put("i_dArt", this.getI_dArt() == null ? null : this.getI_dArt().toString());
		linkedHashMap.put("i_dDesign", this.getI_dDesign() == null ? null : this.getI_dDesign().toString());
		linkedHashMap.put("i_dMusic", this.getI_dMusic() == null ? null : this.getI_dMusic().toString());
		linkedHashMap.put("i_dHobbies", this.getI_dHobbies() == null ? null : this.getI_dHobbies().toString());
		linkedHashMap.put("i_dActivities", this.getI_dActivities() == null ? null : this.getI_dActivities().toString());
		linkedHashMap.put("I_dOtherInterest",
				this.getI_dOtherInterest() == null ? null : this.getI_dOtherInterest().toString());
		return linkedHashMap.toString();
	}
}