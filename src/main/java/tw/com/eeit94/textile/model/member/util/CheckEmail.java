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
 * 自訂驗證信箱格式的Annotation。
 * 
 * 以下為本專案有用到的正則表示式即其簡介：
 * 
 * ^：符合輸入字串的開始位置。
 * 
 * $：符合輸入字串的結束位置。
 * 
 * \w：符合包括底線的任何單詞字元。等價於「[A-Za-z0-9_]」。
 * 
 * \s:符合包括任何屬於空白的字元。等價於「[ \f\n\r\t\v]」。
 * 
 * +：符合前面的子運算式一次或多次。
 * 
 * *：符合前面的子運算式零次或多次。
 * 
 * ?：符合前面的子運算式零次或一次。
 * 
 * .*：略過中間任意字元，如「"1.*1"」，則「"10348521"」會符合。
 * 
 * {2,6}：符合前面的子運算式至少兩次、至多六次。
 * 
 * (?:pattern)：符合「pattern」但不儲存pattern為正則表示式的變數。
 * 
 * (?=pattern)：符合「pattern」但不會消耗字元，也就是往後查詢完時如果符合「pattern」，指標仍在剛開始查詢處。
 * 
 * @author 賴
 * @version 2017/06/09
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
@Documented
public @interface CheckEmail {

	String message() default "{tw.com.eeit94.textile.model.member.util.CheckEmail.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String regex() default "^[\\w]+(?:\\.[\\w]+)*@(?:[a-zA-Z0-9]+(?:[-_]{1}[a-zA-Z0-9]+)?\\.)+[a-zA-Z]{2,6}$";

	@OverridesAttribute(constraint = CheckEmail.class, name = "column")
	String column() default "Email";

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckEmail[] value();
	}
}