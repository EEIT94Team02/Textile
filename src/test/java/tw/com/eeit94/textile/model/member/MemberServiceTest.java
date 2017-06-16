package tw.com.eeit94.textile.model.member;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試MemberService的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class MemberServiceTest {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		MemberService memberService = (MemberService) context.getBean("memberService");

		Map<String, String> dataAndErrorsMap = new LinkedHashMap<>();
		// 信箱測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Email.key());
		// error
		// dataAndErrorsMap.put(FormDataKey.Email.key(), "1@2.3");
		// error
		// dataAndErrorsMap.put(FormDataKey.Email.key(), "a@a..a");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.Email.key(), "9487@94mad.com");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 密碼測試：特殊符號 → !%()*-.[]^`{|}~
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Password.key());
		// error
		// dataAndErrorsMap.put(FormDataKey.Password.key(), "");
		// error
		// dataAndErrorsMap.put(FormDataKey.Password.key(), "9487");
		// error
		// dataAndErrorsMap.put(FormDataKey.Password.key(), "9$9487AA");
		// 不包含$, error
		// dataAndErrorsMap.put(FormDataKey.Password.key(), "9!9487Aa");
		// error
		// dataAndErrorsMap.put(FormDataKey.Password.key(),
		// "9!9487Aa948794mad");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.Password.key(), "9_9487Aa");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 姓名測試：不含非法字元，且特殊符號只有 → .-_~
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Name.key());
		// error
		// dataAndErrorsMap.put(FormDataKey.Name.key(), "");
		// 字數不足, error
		// dataAndErrorsMap.put(FormDataKey.Name.key(), "Tom");
		// correct
		// dataAndErrorsMap.put(FormDataKey.Name.key(), "Tomcat");
		// 字數不足, error
		// dataAndErrorsMap.put(FormDataKey.Name.key(), "唯");
		// correct
		// dataAndErrorsMap.put(FormDataKey.Name.key(), "唯一~");
		// 結尾空白, error
		// dataAndErrorsMap.put(FormDataKey.Name.key(), "Ludwig van Beethoven
		// ");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.Name.key(), "臺灣阿成世界偉人總統");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 生日測試：符合「yyyy-MM-dd」
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Birthday.key());
		// error
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "");
		// correct
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "1-1-1");
		// error
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "1/1/1");
		// error
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "1-1-1a");
		// error
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "1-1-32");
		// correct
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "2000-2-29");
		// error
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "1900-2-29");
		// 不可以是未來, error
		// dataAndErrorsMap.put(FormDataKey.Birthday.key(), "9999-1-1");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.Birthday.key(), "1989-06-04");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 身分證字號測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.IdentityCardNumber.key());
		// correct
		// dataAndErrorsMap.put(ConstFormDataKey.IdentityCardNumber.key(),"A167193516");
		// correct
		// dataAndErrorsMap.put(ConstFormDataKey.IdentityCardNumber.key(),"A296358354");
		// 公式驗證正確, 但第二個字違法, error
		// dataAndErrorsMap.put(ConstFormDataKey.IdentityCardNumber.key(),"A317854236");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.IdentityCardNumber.key(), "A123456789");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 地址測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Address.key());
		// 字數太長, error
		// dataAndErrorsMap.put(ConstFormDataKey.Address.key(), "Hello, my name
		// is ovuvuevuevue enyetuenwuevue ugbemugbem osas.");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.Address.key(), "臺北市大安區復興南路一段390號2,3,15樓");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 手機號碼測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.PhoneNumber.key());
		// error
		// dataAndErrorsMap.put(ConstFormDataKey.PhoneNumber.key(),
		// "0800080080");
		// error
		// dataAndErrorsMap.put(ConstFormDataKey.PhoneNumber.key(),
		// "09123456789");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.PhoneNumber.key(), "0912345678");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 密碼提示問題測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.HintPassword.key());
		// error
		// dataAndErrorsMap.put(ConstFormDataKey.HintPassword.key(),
		// "我爸爸最愛吃的水果是什麼？你們還記得嗎？");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.HintPassword.key(), "我爸爸最愛吃的水果是什麼？");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 密碼提示答案測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.HintAnswer.key());
		// error
		dataAndErrorsMap.put(ConstMemberKey.HintAnswer.key(), "當然是榴槤囉！榴槤超好吃的啦！");
		// correct
		dataAndErrorsMap.put(ConstMemberKey.HintAnswer.key(), "榴槤");
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));

		// 自我介紹測試
		dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.SelfIntroduction.key());
		// error
		// dataAndErrorsMap.put(ConstFormDataKey.SelfIntroduction.key(),
		// getLongSelfIntroduction());
		// correct
		dataAndErrorsMap.put(ConstMemberKey.SelfIntroduction.key(), getShortSelfIntroduction());
		TestUtils.printResult(memberService.checkFormData(dataAndErrorsMap));
		dataAndErrorsMap.remove(dataAndErrorsMap.get(ConstHelperKey.KEY.key()));
		
		// 一次測是所有資料
		dataAndErrorsMap = TestUtils.getNewMemberMap();
		TestUtils.printResult(memberService.checkAllData(dataAndErrorsMap));

		((ConfigurableApplicationContext) context).close();
	}

	public static String getLongSelfIntroduction() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Dodge是一對普通夫婦的兒子。他的母親是一個貧窮石匠的女兒，雖然十分聰明，但卻沒有接受過教育，近似於文盲。");
		sBuilder.append("在她成為高斯父親的第二個妻子之前，她從事女傭工作。他的父親曾做過工頭，商人的助手和一個小保險公司的評估師。");
		sBuilder.append("高斯三歲時便能夠糾正他父親的借債帳目的事情，已經成為一個軼事流傳至今。他曾說，他能夠在腦袋中進行複雜的計算。");
		sBuilder.append("他的職業是園丁，他做事認真。\n高斯10歲時利用很短的時間就計算出了小學老師提出的問題：自然數從1到100的求和。");
		sBuilder.append("他所使用的方法是：對50對構造成和101的數列求和（1＋100，2＋99，3＋98……），同時得到結果：5050。\n");
		sBuilder.append("小時候高斯家裡很窮，且他父親不認為學問有何用，但高斯依舊喜歡看書，話說在小時候，冬天吃完飯後他父親就會要他上床睡覺，");
		sBuilder.append("以節省燃油，但當他上床睡覺時，他會將蕪菁的內部挖空，裡面塞入棉布卷，當成燈來使用，以繼續讀書。\n");
		sBuilder.append("當高斯12歲時，已經開始懷疑幾何原本中的基礎證明。當他16歲時，預測在歐氏幾何之外必然會產生一門完全不同的幾何學，即非歐幾里德幾何學。");
		sBuilder.append("他導出了二項式定理的一般形式，將其成功的運用在無窮級數，並發展了數學分析的理論。\n");
		sBuilder.append("高斯的老師布呂特內爾與他助手馬丁·巴爾特斯 很早就認識到了高斯在數學上異乎尋常的天賦，同時卡爾·威廉·斐迪南·馮·布倫瑞克也對這個天才兒童留下了深刻印象。");
		sBuilder.append("於是他們從高斯14歲起便資助其學習與生活。這也使高斯能夠在公元1792－1795年在Carolinum學院（今天布倫瑞克學院的前身）學習。");
		sBuilder.append("18歲時，高斯轉入哥廷根大學學習。在19歲時，成為第一位只用尺規作圖成功畫出正17邊形的人。\n");
		sBuilder.append("高斯於公元1805年10月5日與來自布倫瑞克的Johanna Elisabeth Rosina Osthoff小姐（1780-1809）結婚。");
		sBuilder.append("在公元1806年8月21日迎來了他生命中的第一個孩子Joseph。此後，他又有兩個孩子。威廉妮（1809－1840）和路易斯（1809－1810）。");
		sBuilder.append("1807年高斯成為哥廷根大學的教授和當地天文台的台長。\n雖然高斯作為一個數學家而聞名於世，但這並不意味著他熱愛教書。");
		sBuilder.append("儘管如此，他越來越多的學生成為有影響的數學家，如後來聞名於世的戴德金和黎曼。\n高斯非常信教且保守。");
		sBuilder.append("他的父親死於1808年4月14日，晚些時候的1809年10月11日，他的第一位妻子Johanna也離開人世。");
		sBuilder.append("次年8月4日高斯迎娶第二位妻子弗雷德妮卡·威廉妮（1788－1831）。他們又有三個孩子：歐根（1811－1896）、威廉（1813－1883）和 特雷瑟（1816-1864）。\n");
		sBuilder.append("1831年9月12日他的第二位妻子也死去，1837年高斯開始學習俄語。1839年4月18日，他的母親在哥廷根逝世，享年95歲。高斯於1855年2月23日凌晨1點在哥廷根去世。");
		return sBuilder.toString();
	}

	public static String getShortSelfIntroduction() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Dodge有著淺紫色的日系中長髮，膚色是偏紅，瞳色一藍一綠。身高175cm，主要的穿著為斗篷，配件是翅膀和兔子娃娃。");
		return sBuilder.toString();
	}
}