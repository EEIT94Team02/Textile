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
 * 自訂驗證密碼格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/09
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckPasswordValidator.class)
@Documented
public @interface CheckPassword {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckPassword.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 密碼長度必須介於8~16個字元，且必須為包含英文大寫、小寫、數字和特殊符號!%()*-.[]^_`{|}~各至少一個。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 * @see {@link CheckEmail}
	 */
	String regex() default "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!%()*-.\\[\\]\\^_`{|}~])[A-Za-z\\d!%()*-.\\[\\]\\^_`{|}~]{8,16}$";

	@OverridesAttribute(constraint = CheckPassword.class, name = "column")
	String column() default "Password";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckPassword[] value();
	}
}