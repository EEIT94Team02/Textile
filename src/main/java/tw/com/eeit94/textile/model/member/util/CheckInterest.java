package tw.com.eeit94.textile.model.member.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;

/**
 * 自訂驗證興趣格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/22
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckInterestValidator.class)
@Documented
public @interface CheckInterest {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckInterest.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 興趣的位元(byte)長度必須介於6~30之間，且只能有中文、英文和數字，且開頭結尾不能是數字。(可以不輸入)
	 * 有關Unicode字元，第00A1(16進位)為開始有意義的文字。
	 * 
	 * @author 賴
	 * @version 2017/06/17
	 * @see {@link CheckEmail}
	 */
	String regex() default "^[A-Za-z\\u00A1-\\uFFFF]([A-Za-z\\d\\u00A1-\\uFFFF]+|([ \\-]{1}[A-Za-z\\d\\u00A1-\\uFFFF])+)*(?:[A-Za-z\\d\\u00A1-\\uFFFF])*$";

	@OverridesAttribute(constraint = CheckInterest.class, name = "column")
	String column() default "Interest";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckInterest[] value();
	}
}