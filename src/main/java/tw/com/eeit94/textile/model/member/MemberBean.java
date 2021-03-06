package tw.com.eeit94.textile.model.member;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tw.com.eeit94.textile.model.chatroom_member.Chatroom_MemberBean;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailBean;
import tw.com.eeit94.textile.model.interest_detail.Interest_DetailNameListBean;

/**
 * 封裝會員基本資料的VO，子表為興趣明細資料。
 * 
 * 地址分成三段：縣市、區鄉鎮市、尾段。
 * 
 * @author 賴
 * @version 2017/06/21
 */
@Entity
@Table(name = "member")
public class MemberBean implements java.io.Serializable {
	private static final long serialVersionUID = 316381097795929237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mId;
	private java.sql.Timestamp mCreateTime;
	private String mValidEmail;
	private String mValidPhone;
	private String mValidManager;
	private String mKeepLogin;
	private String mEmail; // 唯一
	private String mPassword;
	private String mName; // 唯一
	private java.util.Date mBirthday;
	private String mIdentityCardNumber;
	private String mGender;
	private String mAddress_County;
	private String mAddress_Region;
	private String mAddress;
	private String mPhoneNumber;
	private String mHintPassword;
	private String mHintAnswer;
	private Integer mScores;
	private Integer mPoints;
	/**
	 * Java沒有unsigned byte，所以使用範圍稍大的Integer。
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
	private String mSelfIntroduction;
	private String mPhotono;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "mId")
	private Interest_DetailBean interest_DetailBean;
	@OneToMany(cascade = {
			CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "chatroom_MemberPK.mId", targetEntity = Chatroom_MemberBean.class)
	private List<Chatroom_MemberBean> chatroom_MemberBean;
	private transient Interest_DetailNameListBean i_d;
	private transient String mOtherProfileUrl;

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

	public String getmValidEmail() {
		return mValidEmail;
	}

	public void setmValidEmail(String mValidEmail) {
		this.mValidEmail = mValidEmail;
	}

	public String getmValidPhone() {
		return mValidPhone;
	}

	public void setmValidPhone(String mValidPhone) {
		this.mValidPhone = mValidPhone;
	}

	public String getmValidManager() {
		return mValidManager;
	}

	public void setmValidManager(String mValidManager) {
		this.mValidManager = mValidManager;
	}

	public String getmKeepLogin() {
		return mKeepLogin;
	}

	public void setmKeepLogin(String mKeepLogin) {
		this.mKeepLogin = mKeepLogin;
	}

	public String getmEmail() {
		return mEmail;
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

	public String getmAddress_County() {
		return mAddress_County;
	}

	public void setmAddress_County(String mAddress_County) {
		this.mAddress_County = mAddress_County;
	}

	public String getmAddress_Region() {
		return mAddress_Region;
	}

	public void setmAddress_Region(String mAddress_Region) {
		this.mAddress_Region = mAddress_Region;
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

	public List<Chatroom_MemberBean> getChatroom_MemberBean() {
		return chatroom_MemberBean;
	}

	public void setChatroom_MemberBean(List<Chatroom_MemberBean> chatroom_MemberBean) {
		this.chatroom_MemberBean = chatroom_MemberBean;
	}

	public Interest_DetailNameListBean getI_d() {
		return i_d;
	}

	public void setI_d(Interest_DetailNameListBean i_d) {
		this.i_d = i_d;
	}

	public String getmOtherProfileUrl() {
		return mOtherProfileUrl;
	}

	public void setmOtherProfileUrl(String mOtherProfileUrl) {
		this.mOtherProfileUrl = mOtherProfileUrl;
	}

	public String getmPhotono() {
		return mPhotono;
	}

	public void setmPhotono(String mPhotono) {
		this.mPhotono = mPhotono;
	}

	@Override
	public String toString() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("mId", this.getmId().toString());
		linkedHashMap.put("mCreateTime", this.getmCreateTime().toString());
		linkedHashMap.put("mValidEmail", this.getmValidEmail().toString());
		linkedHashMap.put("mValidPhone", this.getmValidPhone().toString());
		linkedHashMap.put("mValidManager", this.getmValidManager().toString());
		linkedHashMap.put("mKeepLogin", this.getmKeepLogin().toString());
		linkedHashMap.put("mEmail", this.getmEmail());
		linkedHashMap.put("mPassword", this.getmPassword());
		linkedHashMap.put("mName", this.getmName());
		linkedHashMap.put("mBirthday", this.getmBirthday().toString());
		linkedHashMap.put("mIdentityCardNumber", this.getmIdentityCardNumber());
		linkedHashMap.put("mGender", this.getmGender());
		linkedHashMap.put("mAddress_County", this.getmAddress_County());
		linkedHashMap.put("mAddress_Region", this.getmAddress_Region());
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
		linkedHashMap.put("mPhotono", this.mPhotono != null ? this.mPhotono : null);
		linkedHashMap.put("interest_DetailBean",
				this.interest_DetailBean != null ? this.interest_DetailBean.toString() : null);
		linkedHashMap.put("chatroom_MemberBean",
				this.chatroom_MemberBean != null ? this.chatroom_MemberBean.toString() : null);
		linkedHashMap.put("i_d", this.i_d != null ? this.i_d.toString() : null);
		linkedHashMap.put("mOtherProfileUrl", this.mOtherProfileUrl != null ? this.mOtherProfileUrl : null);
		return linkedHashMap.toString();
	}
}