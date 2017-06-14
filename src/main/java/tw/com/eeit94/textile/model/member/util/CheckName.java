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
 * 自訂驗證姓名格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/09
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckNameValidator.class)
@Documented
public @interface CheckName {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckName.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 姓名的位元(byte)長度必須介於3~30之間，且只能有英文、數字、空白、文字和特殊符號.-_~，但開頭結尾不得空白。
	 * 有關Unicode字元，第00A1(16進位)為開始有意義的文字。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 * @see {@link CheckEmail}
	 */
	String regex() default "^[^\\s][A-za-z\\d .-_~\u00A1-\uFFFF]+[^\\s]$";

	@OverridesAttribute(constraint = CheckName.class, name = "column")
	String column() default "Name";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckName[] value();
	}
}