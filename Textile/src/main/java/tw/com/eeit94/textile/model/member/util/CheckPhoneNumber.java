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
 * 自訂驗證手機號碼格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/10
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckPhoneNumberValidator.class)
@Documented
public @interface CheckPhoneNumber {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckPhoneNumber.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 手機號碼為台灣的格式，前兩號必為09，共10碼。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 * @see {@link CheckEmail}
	 */
	String regex() default "^09[0-9]{8}$";

	@OverridesAttribute(constraint = CheckPhoneNumber.class, name = "column")
	String column() default "PhoneNumber";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckPhoneNumber[] value();
	}
}