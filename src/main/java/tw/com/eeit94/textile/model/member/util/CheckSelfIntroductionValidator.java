package tw.com.eeit94.textile.model.member.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 實作驗證自我介紹格式的Annotation。
 * 
 * 自我介紹的位元(byte)長度不能大於600，只有輸入的時候才會檢查。
 * 
 * @author 賴
 * @version 2017/06/10
 */
public class CheckSelfIntroductionValidator implements ConstraintValidator<CheckSelfIntroduction, String> {
	private String column;

	@Override
	public void initialize(CheckSelfIntroduction constraintAnnotation) {
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
		if (value != null && value.trim().getBytes().length > 600) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "字數太長").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}