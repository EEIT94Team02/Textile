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
 * 自訂驗證地址格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/10
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckAddressValidator.class)
@Documented
public @interface CheckAddress {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckAddress.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@OverridesAttribute(constraint = CheckAddress.class, name = "column")
	String column() default "Address";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckAddress[] value();
	}
}