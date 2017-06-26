package tw.com.eeit94.textile.model.member.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 實作驗證興趣格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/22
 */
public class CheckInterestValidator implements ConstraintValidator<CheckInterest, String> {
	private String regex;
	private String column;

	@Override
	public void initialize(CheckInterest constraintAnnotation) {
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
			return true;
		} else if (value.getBytes().length < 6 || value.getBytes().length > 30) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "字數不足或太長").addConstraintViolation();
			return false;
		} else if (!value.matches(this.regex)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "格式不正確").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}