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
 * 自訂驗證生日格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/09
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckBirthdayValidator.class)
@Documented
public @interface CheckBirthday {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckBirthday.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 日期的格式必須式「yyyy-MM-dd」，且只能包含數字和底線。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 * @see {@link CheckEmail}
	 */
	String pattern() default "yyyy-MM-dd";

	String regex() default "^[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}";

	@OverridesAttribute(constraint = CheckBirthday.class, name = "column")
	String column() default "Birthday";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckBirthday[] value();
	}
}