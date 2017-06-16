package tw.com.eeit94.textile.model.secure;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberDAO;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 控制金鑰的Service元件，負責讀取金鑰，並轉換密碼等資訊。
 * 
 * @author 賴
 * @version 2017/06/11
 */
public class SecureServiceTest {

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		// MemberDAO memberDAO = (MemberDAO) context.getBean("memberDAO");
		SecureDAO secureDAO = (SecureDAO) context.getBean("secureDAO");
		SecureService secureService = (SecureService) context.getBean("secureService");

		/*
		 * 儲存所有新的金鑰，只能使用一次，除非刪除表格和重建所有會員基本資料的「密碼」。
		 */
		// sessionFactory.getCurrentSession().beginTransaction();
		// storeOriginalKeys(secureService);
		// sessionFactory.getCurrentSession().getTransaction().commit();

		/**
		 * 將儲存在資料庫會員基本資料的「密碼」轉換為加密的文字並儲存，只能使用一次，除非刪除表格和重建所有會員基本資料的「密碼」。
		 */
		// sessionFactory.getCurrentSession().beginTransaction();
		// convertOriginalPasswordToEncryptedText(secureService, memberDAO);
		// sessionFactory.getCurrentSession().getTransaction().commit();

		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(secureDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();

		// 測試亂碼產生器是否正常
		TestUtils.printResult(secureService.getRandomCode(16));

		// 測試加密與解密是否正常
		sessionFactory.getCurrentSession().beginTransaction();
		String sTarget = ConstSecureParameter.CHATROOMID.param();
		String plainText = "1";
		TestUtils.printResult("欲加密的字串為" + plainText);
		String encryptedText = secureService.getEncryptedText(plainText, sTarget);
		TestUtils.printResult("加密後的字串為" + encryptedText);
		TestUtils.printResult("解密後的字串為" + secureService.getDecryptedText(encryptedText, sTarget));
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}

	/**
	 * 儲存所有新的金鑰，只能使用一次，除非刪除表格和重建所有會員基本資料的「密碼」。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	public static void storeOriginalKeys(SecureService secureService) {
		secureService.insertNewSecureKey(ConstSecureParameter.PASSWORD.param());
		secureService.insertNewSecureKey(ConstSecureParameter.EMAIL.param());
		secureService.insertNewSecureKey(ConstSecureParameter.CHATROOMID.param());
	}

	/**
	 * 將儲存在資料庫會員基本資料的「密碼」轉換為加密的文字並儲存，只能使用一次，除非刪除表格和重建所有會員基本資料的「密碼」。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 * @throws Exception
	 */
	public static void convertOriginalPasswordToEncryptedText(SecureService secureService, MemberDAO memberDAO)
			throws Exception {
		List<MemberBean> list = memberDAO.selectAll();
		for (int i = 0; i < list.size(); i++) {
			MemberBean mbean = list.get(i);
			mbean.setmPassword(
					secureService.getEncryptedText(mbean.getmPassword(), ConstSecureParameter.PASSWORD.param()));
		}
	}
}