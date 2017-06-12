package tw.com.eeit94.textile.model.member.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 實作驗證身分證字號格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/09
 */
public class CheckIdentityCardNumberValidator implements ConstraintValidator<CheckIdentityCardNumber, String> {
	private String regex;
	private String column;

	@Override
	public void initialize(CheckIdentityCardNumber constraintAnnotation) {
		this.regex = constraintAnnotation.regex();
		this.column = constraintAnnotation.column();
	}

	/**
	 * 驗證失敗或成功皆回傳boolean值，並且失敗時需修改違反限制的錯誤資訊(覆寫String message())。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().length() == 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("必須輸入" + this.column).addConstraintViolation();
			return false;
		} else if (value.trim().length() != 10) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "字數不足或太長").addConstraintViolation();
			return false;
		} else if (!value.matches(this.regex)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "格式不正確").addConstraintViolation();
			return false;
		} else if (!isLastNumberValid(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "不合法").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 最後一碼的驗證規則請參考：<a href=
	 * "https://zh.wikipedia.org/wiki/%E4%B8%AD%E8%8F%AF%E6%B0%91%E5%9C%8B%E5%9C%8B%E6%B0%91%E8%BA%AB%E5%88%86%E8%AD%89">中華民國國民身分證</a>
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 */
	private boolean isLastNumberValid(String value) {
		String[] valueSplits = value.split("");
		int[] values = new int[valueSplits.length + 1];
		for (int i = 0; i < valueSplits.length; i++) {
			if (i == 0) {
				// 這個enum類別定義一對一的身分證字號第一個英文字母及其轉換的數字。
				ConstIdentityCardAlphabet constIdentityCardAlphabet = ConstIdentityCardAlphabet.valueOf(valueSplits[i]);
				int convertedNumber = constIdentityCardAlphabet.number();
				// 英文轉換的數字必為兩位數，拆開後分別放入陣列，故總共11個數字。
				values[i] = convertedNumber / 10;
				values[i + 1] = convertedNumber % 10;
			} else {
				values[i + 1] = Integer.parseInt(valueSplits[i]);
			}
		}

		int sum = 0;
		// 驗證公式，即先全部加起來，特定的位數要先乘以特定權數。
		for (int i = 0; i < valueSplits.length + 1; i++) {
			if (i > 0 && i < valueSplits.length - 1) {
				sum += values[i] * (valueSplits.length - i);
			} else {
				sum += values[i];
			}
		}

		// 總和如果能被十整除，身分證字號即合法。
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}
}