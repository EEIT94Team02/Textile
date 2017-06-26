package tw.com.eeit94.textile.model.interest_detail;

import org.springframework.stereotype.Service;

/**
 * 對興趣明細資料作二進位轉換的Service元件。
 * 
 * @author 賴
 * @version 2017/06/18
 * @see {@link Interest_DetailService}
 */
@Service
public class InterestBinaryConvertorService {
	private static final byte NO = 0;
	private static final byte YES = 1;
	private static final int BIT_1 = (int) Math.pow(2, 0);
	private static final int BIT_2 = (int) Math.pow(2, 1);
	private static final int BIT_3 = (int) Math.pow(2, 2);
	private static final int BIT_4 = (int) Math.pow(2, 3);
	private static final int BIT_5 = (int) Math.pow(2, 4);
	private static final int BIT_6 = (int) Math.pow(2, 5);
	private static final int BIT_7 = (int) Math.pow(2, 6);
	private static final int BIT_8 = (int) Math.pow(2, 7);
	private static final int[] BITS = new int[] { BIT_8, BIT_7, BIT_6, BIT_5, BIT_4, BIT_3, BIT_2, BIT_1 };

	/**
	 * 轉換二進位數字如01011010為90。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public int bitsToInt(byte[] bytes) {
		int x = 0;
		for (int i = 0; i < bytes.length; i++) {
			x += bytes[i] * BITS[i];
		}
		return x;
	}

	/**
	 * 轉換整數如90為01011010。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public byte[] intToBits(int x) {
		byte[] bs = new byte[8];
		for (int i = 0; i < bs.length; i++) {
			if ((x & BITS[i]) > 0) {
				bs[i] = YES;
			} else {
				bs[i] = NO;
			}
		}
		return bs;
	}

	/**
	 * 比較兩個整數是否存在至少一組相同的位元(皆為1)，例如01101101和10010010就沒有相同的位元，或兩者完全為0，則上述兩種情況皆傳回正確。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public boolean compareTwoIntsLoosely(int refernce, int compared) {
		if ((refernce & compared) > 0 || refernce == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 比較兩個整數是否完全一樣，一樣才傳回正確。
	 * 
	 * @author 賴
	 * @version 2017/06/19
	 */
	public boolean compareTwoIntsStrictly(int refernce, int compared) {
		if (refernce == compared) {
			return true;
		} else {
			return false;
		}
	}
}