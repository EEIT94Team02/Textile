package tw.com.eeit94.textile.model.secure;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.service.UserCentralService;
import tw.com.eeit94.textile.model.member.util.CheckPassword;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

/**
 * 控制金鑰的Service元件，負責讀取金鑰，並轉換密碼等資訊。
 * 
 * @author 賴
 * @version 2017/06/26
 */
@Service
public class SecureService {
	@Autowired
	private SecureDAO secureDAO;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UserCentralService userCentralService;
	private static final String TRANSFORMATION = ConstSecureParameter.TRANSFORMATION.param();
	private static final String ALGORITHM = ConstSecureParameter.ALGORITHM.param();

	/**
	 * 新增新的金鑰。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	@Transactional
	public void insertNewSecureKey(String sTarget) {
		byte[] initVector = new byte[128 / 8];
		new SecureRandom().nextBytes(initVector);

		SecureBean sbean = new SecureBean();
		sbean.setsKey(this.getRandomCode(16));
		sbean.setsInitVector(initVector);
		sbean.setsTarget(sTarget);

		secureDAO.insert(sbean);
	}

	/**
	 * 重建加密的文字，讓「 」變回「+」。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	public String getRebuiltEncryptedText(String encryptedText) {
		return encryptedText.replace(' ', '+');
	}

	/**
	 * 得到加密的文字。
	 * 
	 * ※注意：使用時，輸入的sTarget資料必須在資料庫Secure表格中存在，否則會拋出例外。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	@Transactional
	public String getEncryptedText(String plainText, String sTarget) throws Exception {
		SecureBean sbean = new SecureBean();
		sbean.setsTarget(sTarget);
		sbean = secureDAO.selectByTarget(sbean).get(0);
		String sKey = sbean.getsKey();
		byte[] initVector = sbean.getsInitVector();

		// 代入轉換的方式，本專案採用「AES/CBC/PKCS5Padding」，得到Cipher的物件。
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		// 代入金鑰的位元組碼及演算法，本專案採用「AES」，得到SecretKeySpec物件。
		SecretKeySpec secretKeySpec = new SecretKeySpec(sKey.getBytes(), ALGORITHM);
		// 初始化Cipher物件，必須告知轉換的模式(加密或解密)、特定的金鑰(含演算法)、特定的初始向量(增強金鑰強度)。
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(initVector));
		// 代入明文得到轉換後的位元組碼。
		byte[] byteArray = cipher.doFinal(plainText.getBytes());
		// 將位元組碼以Base64轉碼為加密的文字。
		String encryptedText = DatatypeConverter.printBase64Binary(byteArray);
		return encryptedText;
	}

	/**
	 * 解除加密的文字，詳細請參考getEncryptedText()方法。
	 * 
	 * ※注意：使用時，輸入的sTarget資料必須在資料庫Secure表格中存在，否則會拋出例外。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 * @throws Exception
	 */
	@Transactional
	public String getDecryptedText(String encryptedText, String sTarget) throws Exception {
		SecureBean sbean = new SecureBean();
		sbean.setsTarget(sTarget);
		sbean = secureDAO.selectByTarget(sbean).get(0);
		String sKey = sbean.getsKey();
		byte[] initVector = sbean.getsInitVector();

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		SecretKeySpec secretKeySpec = new SecretKeySpec(sKey.getBytes(), ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(initVector));
		byte[] byteArray = DatatypeConverter.parseBase64Binary(encryptedText); // 最後的步驟與加密順序相反
		String decryptedText = new String(cipher.doFinal(byteArray));
		return decryptedText;
	}

	/**
	 * 亂數產生英文字母大小寫和數字，需輸入欲產生字元的數量。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	public String getRandomCode(int amount) {
		StringBuffer sBuffer = new StringBuffer();
		boolean isIllegalCode;
		for (int i = 0; i < amount; i++) {
			isIllegalCode = true;
			while (isIllegalCode) {
				int randomNumber = (int) (Math.random() * 256);
				if ((randomNumber >= '0' && randomNumber <= '9') || (randomNumber >= 'A' && randomNumber <= 'Z')
						|| (randomNumber >= 'a' && randomNumber <= 'z')) {
					sBuffer.append((char) randomNumber);
					isIllegalCode = false;
				}
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 亂數產生數字，需輸入欲產生數字的數量。
	 * 
	 * @author 賴
	 * @version 2017/06/23
	 */
	public String getRandomNumber(int amount) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < amount; i++) {
			int randomNumber = (int) (Math.random() * 10);
			sBuffer.append(randomNumber);
		}
		return sBuffer.toString();
	}

	/**
	 * 亂數產生密碼，密碼長度必須介於8~16個字元，且必須為包含英文大寫、小寫、數字和特殊符號!%()*-.[]^_`{|}~各至少一個。
	 * 
	 * @author 賴
	 * @version 2017/06/26
	 * @see {@link CheckPassword}
	 */
	public String getRandomPassword(int amount) {
		String randomPassword;
		while (true) {
			StringBuffer sBuffer = new StringBuffer();
			boolean isIllegalCode;
			for (int i = 0; i < amount; i++) {
				isIllegalCode = true;
				while (isIllegalCode) {
					int randomNumber = (int) (Math.random() * 256);
					isIllegalCode = this.isIllegalPasswordChar(randomNumber);
					if (!isIllegalCode) {
						sBuffer.append((char) randomNumber);
						isIllegalCode = false;
					}
				}
			}

			randomPassword = sBuffer.toString();
			Map<String, String> dataAndErrorsMap = new HashMap<>();
			dataAndErrorsMap.put(ConstHelperKey.KEY.key(), ConstMemberKey.Password.key());
			dataAndErrorsMap.put(ConstMemberKey.Password.key(), randomPassword);
			dataAndErrorsMap = this.memberService.checkFormData(dataAndErrorsMap);
			String output = this.userCentralService.getAJAXCheckResult(dataAndErrorsMap);
			if (output.equals(ConstHelperKey.SPACE.key())) {
				return randomPassword;
			}
		}
	}

	/**
	 * 產生密碼的字元，必須為英文大寫、小寫、數字和特殊符號!%()*-.[]^_`{|}~其中一個。
	 * 
	 * @author 賴
	 * @version 2017/06/26
	 */
	public boolean isIllegalPasswordChar(int randomNumber) {
		if (randomNumber >= '0' && randomNumber <= '9') {
			return false;
		} else if (randomNumber >= 'A' && randomNumber <= 'Z') {
			return false;
		} else if (randomNumber >= 'a' && randomNumber <= 'z') {
			return false;
		} else {
			switch (randomNumber) {
			case '!':
			case '%':
			case '(':
			case ')':
			case '*':
			case '-':
			case '.':
			case '[':
			case ']':
			case '^':
			case '_':
			case '`':
			case '{':
			case '|':
			case '}':
			case '~':
				return false;
			default:
				return true;
			}
		}
	}
}