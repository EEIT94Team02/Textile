package tw.com.eeit94.textile.model.member;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tw.com.eeit94.textile.model.interest_detail.Interest_DetailBean;

/**
 * 封裝會員基本資料的VO，子表為興趣明細資料。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Entity
@Table(name = "member")
public class MemberBean implements java.io.Serializable {
	private static final long serialVersionUID = 316381097795929237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mId;
	private java.sql.Timestamp mCreateTime;
	private String mEmailValid;
	private String mPhoneValid;
	private String mEmail; // 唯一
	private String mPassword;
	private String mName; // 唯一
	private java.util.Date mBirthday;
	private String mIdentityCardNumber;
	private String mGender;
	private String mAddress;
	private String mPhoneNumber;
	private String mHintPassword;
	private String mHintAnswer;
	private Integer mScores;
	private Integer mPoints;
	// Java沒有unsigned byte，所以使用範圍稍大的Integer。
	private Integer mCareer;
	private Integer mEducation;
	private Integer mEconomy;
	private Integer mMarriage;
	private Integer mFamily;
	private Integer mBloodType;
	private Integer mConstellation;
	private Integer mReligion;
	private String mSelfIntroduction;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "mId")
	private Interest_DetailBean interest_DetailBean;

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public java.util.Date getmCreateTime() {
		return mCreateTime;
	}

	public void setmCreateTime(java.sql.Timestamp mCreateTime) {
		this.mCreateTime = mCreateTime;
	}

	public String getmEmailValid() {
		return mEmailValid;
	}

	public void setmEmailValid(String mEmailValid) {
		this.mEmailValid = mEmailValid;
	}

	public String getmEmail() {
		return mEmail;
	}

	public String getmPhoneValid() {
		return mPhoneValid;
	}

	public void setmPhoneValid(String mPhoneValid) {
		this.mPhoneValid = mPhoneValid;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public java.util.Date getmBirthday() {
		return mBirthday;
	}

	public void setmBirthday(java.util.Date mBirthday) {
		this.mBirthday = mBirthday;
	}

	public String getmIdentityCardNumber() {
		return mIdentityCardNumber;
	}

	public void setmIdentityCardNumber(String mIdentityCardNumber) {
		this.mIdentityCardNumber = mIdentityCardNumber;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public String getmPhoneNumber() {
		return mPhoneNumber;
	}

	public void setmPhoneNumber(String mPhoneNumber) {
		this.mPhoneNumber = mPhoneNumber;
	}

	public String getmHintPassword() {
		return mHintPassword;
	}

	public void setmHintPassword(String mHintPassword) {
		this.mHintPassword = mHintPassword;
	}

	public String getmHintAnswer() {
		return mHintAnswer;
	}

	public void setmHintAnswer(String mHintAnswer) {
		this.mHintAnswer = mHintAnswer;
	}

	public Integer getmScores() {
		return mScores;
	}

	public void setmScores(Integer mScores) {
		this.mScores = mScores;
	}

	public Integer getmPoints() {
		return mPoints;
	}

	public void setmPoints(Integer mPoints) {
		this.mPoints = mPoints;
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

	public String getmSelfIntroduction() {
		return mSelfIntroduction;
	}

	public void setmSelfIntroduction(String mSelfIntroduction) {
		this.mSelfIntroduction = mSelfIntroduction;
	}

	public Interest_DetailBean getInterest_DetailBean() {
		return interest_DetailBean;
	}

	public void setInterest_DetailBean(Interest_DetailBean interest_DetailBean) {
		this.interest_DetailBean = interest_DetailBean;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("mId", this.getmId().toString());
		linkedHashMap.put("mCreateTime", this.getmCreateTime().toString());
		linkedHashMap.put("mEmailValid", this.getmEmailValid().toString());
		linkedHashMap.put("mPhoneValid", this.getmPhoneValid().toString());
		linkedHashMap.put("mEmail", this.getmEmail());
		linkedHashMap.put("mPassword", this.getmPassword());
		linkedHashMap.put("mName", this.getmName());
		linkedHashMap.put("mBirthday", this.getmBirthday().toString());
		linkedHashMap.put("mIdentityCardNumber", this.getmIdentityCardNumber());
		linkedHashMap.put("mGender", this.getmGender());
		linkedHashMap.put("mAddress", this.getmAddress());
		linkedHashMap.put("mPhoneNumber", this.getmPhoneNumber());
		linkedHashMap.put("mHintPassword", this.getmHintPassword());
		linkedHashMap.put("mHintAnswer", this.getmHintAnswer());
		linkedHashMap.put("mScores", this.getmScores().toString());
		linkedHashMap.put("mPoints", this.getmPoints().toString());
		linkedHashMap.put("mCareer", this.getmCareer().toString());
		linkedHashMap.put("mEducation", this.getmEducation().toString());
		linkedHashMap.put("mEconomy", this.getmEconomy().toString());
		linkedHashMap.put("mMarriage", this.getmMarriage().toString());
		linkedHashMap.put("mFamily", this.getmFamily().toString());
		linkedHashMap.put("mBloodType", this.getmBloodType().toString());
		linkedHashMap.put("mConstellation", this.getmConstellation().toString());
		linkedHashMap.put("mReligion", this.getmReligion().toString());
		linkedHashMap.put("mselfIntroduction", this.getmSelfIntroduction());
		linkedHashMap.put("interest_DetailBean",
				this.interest_DetailBean != null ? this.interest_DetailBean.toString() : null);
		return linkedHashMap.toString();
	}
}