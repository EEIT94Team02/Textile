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
 * 自訂驗證密碼提示答案格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/10
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckHintAnswerValidator.class)
@Documented
public @interface CheckHintAnswer {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckHintAnswer.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@OverridesAttribute(constraint = CheckHintAnswer.class, name = "column")
	String column() default "HintAnswer";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckHintAnswer[] value();
	}
}