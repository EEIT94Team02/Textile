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
 * 自訂驗證自我介紹格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/10
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckSelfIntroductionValidator.class)
@Documented
public @interface CheckSelfIntroduction {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckSelfIntroduction.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@OverridesAttribute(constraint = CheckSelfIntroduction.class, name = "column")
	String column() default "SelfIntroduction";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckSelfIntroduction[] value();
	}
}