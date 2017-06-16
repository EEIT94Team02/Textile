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
 * 自訂驗證信箱存不存在的Annotation。
 * 
 * @author 賴
 * @version 2017/06/16
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailExistValidator.class)
@Documented
public @interface CheckEmailExist {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckEmailExist.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@OverridesAttribute(constraint = CheckEmailExist.class, name = "column")
	String column() default "Email";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckEmailExist[] value();
	}
}