package tw.com.eeit94.textile.model.member.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 實作驗證密碼提示答案格式的Annotation。
 * 
 * 密碼提示問題的位元(byte)長度不能大於40。
 * 
 * @author 賴
 * @version 2017/06/10
 */
public class CheckHintAnswerValidator implements ConstraintValidator<CheckHintAnswer, String> {
	private String column;

	@Override
	public void initialize(CheckHintAnswer constraintAnnotation) {
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
		} else if (value.trim().getBytes().length > 40) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "字數太長").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}