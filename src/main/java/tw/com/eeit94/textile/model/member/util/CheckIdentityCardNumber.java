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
 * 自訂驗證身分證字號格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/09
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckIdentityCardNumberValidator.class)
@Documented
public @interface CheckIdentityCardNumber {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckIdentityCardNumber.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 身分證字號的格式為第一個字必須是大寫英文字母，第二個字為數字且只能是一或二，其餘八個字皆為數字， 且最後一個數字為檢驗碼，需符合模10的驗證。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 * @see {@link CheckEmail}
	 */
	String regex() default "^[A-Z]{1}[12]{1}[0-9]{8}$";

	@OverridesAttribute(constraint = CheckIdentityCardNumber.class, name = "column")
	String column() default "IdentityCardNumber";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckIdentityCardNumber[] value();
	}
}