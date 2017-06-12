package tw.com.eeit94.textile.model.member.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 實作驗證地址格式的Annotation。
 * 
 * 地址的位元(byte)長度必須介於9~60之間。
 * 
 * @author 賴
 * @version 2017/06/10
 */
public class CheckAddressValidator implements ConstraintValidator<CheckAddress, String> {
	private String column;

	@Override
	public void initialize(CheckAddress constraintAnnotation) {
		this.column = constraintAnnotation.column();
	}

	/**
	 * 驗證失敗或成功皆回傳boolean值，並且失敗時需修改違反限制的錯誤資訊(覆寫String message())。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().length() == 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("必須輸入" + this.column).addConstraintViolation();
			return false;
		} else if (value.trim().getBytes().length < 9 || value.trim().getBytes().length > 60) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "字數不足或太長").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}