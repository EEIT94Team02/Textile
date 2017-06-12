package tw.com.eeit94.textile.model.member.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 實作驗證生日格式的Annotation。
 * 
 * @author 賴
 * @version 2017/06/09
 */
public class CheckBirthdayValidator implements ConstraintValidator<CheckBirthday, String> {
	private String regex;
	private String column;
	private DateFormat dateFormat;
	private java.util.Date date;

	@Override
	public void initialize(CheckBirthday constraintAnnotation) {
		this.regex = constraintAnnotation.regex();
		this.column = constraintAnnotation.column();
		this.dateFormat = new SimpleDateFormat(constraintAnnotation.pattern());
	}

	/**
	 * 驗證失敗或成功皆回傳boolean值，並且失敗時需修改違反限制的錯誤資訊(覆寫String message())。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().length() == 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("必須輸入" + this.column).addConstraintViolation();
			return false;
		} else if (!value.matches(this.regex)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "格式不正確").addConstraintViolation();
			return false;
		} else {
			// 比對轉換日期是否有誤
			try {
				this.date = this.dateFormat.parse(value);
			} catch (Exception e) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(this.column + "格式有誤").addConstraintViolation();
				return false;
			}

			// 比對日期是否超出現在
			if (this.date.after(new java.util.Date(System.currentTimeMillis()))) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(this.column + "不可以是未來").addConstraintViolation();
				return false;
			}

			// 比對年、月、日是否與現實相符，例如閏年的2月有29天，每年的1月只有31天。
			String[] dateSplits = value.split("-");
			int monthFromInput = Integer.parseInt(dateSplits[1]);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(this.date);
			// Calendar的一月定為0
			int monthFromCalendar = calendar.get(Calendar.MONTH) + 1;
			if (monthFromInput != monthFromCalendar) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(this.column + "超出該月份的最大值").addConstraintViolation();
				return false;
			} else {
				return true;
			}
		}
	}
}