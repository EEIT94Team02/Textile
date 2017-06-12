package tw.com.eeit94.textile.model.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;

/**
 * TestUtils專門在測試程式使用：
 * 
 * 1. 可以用來獲得新的會員基本資料。
 * 
 * 2. printResult()可以顯示資料，參數可放List<'JavaBean'>或直接放JavaBean。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class TestUtils {

	public static <E> void printResult(List<E> list) {
		if (list != null) {
			for (E e : list) {
				System.out.println(e);
			}
		} else {
			System.out.println("null");
		}
	}

	public static <E> void printResult(E e) {
		if (e != null) {
			System.out.println(e);
		} else {
			System.out.println("null");
		}
	}

	/**
	 * 
	 * 新Member資料以MemberBean的形式，資料為驗證過的。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	public static MemberBean getNewMemberBean() {
		MemberBean mbean = new MemberBean();
		mbean.setmCreateTime(new Timestamp(System.currentTimeMillis()));
		mbean.setmEmailValid("N");
		mbean.setmPhoneValid("N");
		mbean.setmEmail("Dodge3218452@gmail.com");
		mbean.setmPassword("O4iF036PE3TjpWwHPPCSSQ=="); // Aa_12345
		mbean.setmName("Dodge Dodge");

		try {
			mbean.setmBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1989-08-23"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		mbean.setmIdentityCardNumber("A265415600");
		mbean.setmGender("F");
		mbean.setmAddress("105台北市松山區中華里敦化北路199巷18號");
		mbean.setmPhoneNumber("0965127856");
		mbean.setmHintPassword("我最愛的動漫");
		mbean.setmHintAnswer("刀劍神域");
		mbean.setmScores(0);
		mbean.setmPoints(0);
		mbean.setmCareer(11);
		mbean.setmEducation(4);
		mbean.setmEconomy(2);
		mbean.setmMarriage(2);
		mbean.setmFamily(2);
		mbean.setmBloodType(2);
		mbean.setmConstellation(4);
		mbean.setmReligion(4);
		mbean.setmSelfIntroduction(
				"Dodge是女生，擁有淡粉色的眼瞳，淡紫色的公主卷長發，是100%的美女。為人傲嬌、沒禮貌、多疑、溫文儒雅、孝順和冷漠。家室貧窮，喜歡打電動、烹飪、打羽球、唱歌和游泳，討厭鬼、花痴、老鼠、和貓擅長烹飪、跳舞、做手工和做家務。");
		return mbean;
	}

	/**
	 * 
	 * 新Member資料以Map<String, String>的形式，模擬資料為使用者輸入、尚未驗證、準備驗證的。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	public static Map<String, String> getNewMemberMap() {
		Map<String, String> mMap = new HashMap<>();
		mMap.put(ConstMemberKey.Email.key(), "9487@94mad.com");
		mMap.put(ConstMemberKey.Password.key(), "Aa_12345");
		mMap.put(ConstMemberKey.Name.key(), "臺灣阿成世界偉人總統");
		mMap.put(ConstMemberKey.Birthday.key(), "1989-06-04");
		mMap.put(ConstMemberKey.IdentityCardNumber.key(), "A123456789");
		mMap.put(ConstMemberKey.Gender.key(), "M");
		mMap.put(ConstMemberKey.Address.key(), "臺北市大安區復興南路一段390號2,3,15樓");
		mMap.put(ConstMemberKey.PhoneNumber.key(), "0912345678");
		mMap.put(ConstMemberKey.HintPassword.key(), "我爸爸最愛吃的水果是什麼？");
		mMap.put(ConstMemberKey.HintAnswer.key(), "榴槤");
		mMap.put(ConstMemberKey.Career.key(), "11");
		mMap.put(ConstMemberKey.Education.key(), "4");
		mMap.put(ConstMemberKey.Economy.key(), "2");
		mMap.put(ConstMemberKey.Marriage.key(), "2");
		mMap.put(ConstMemberKey.Family.key(), "2");
		mMap.put(ConstMemberKey.BloodType.key(), "2");
		mMap.put(ConstMemberKey.Constellation.key(), "4");
		mMap.put(ConstMemberKey.Religion.key(), "4");
		mMap.put(ConstMemberKey.SelfIntroduction.key(), "Dodge有著淺紫色的日系中長髮，膚色是偏紅，瞳色一藍一綠。身高175cm，主要的穿著為斗篷，配件是翅膀和兔子娃娃。");
		return mMap;
	}
}