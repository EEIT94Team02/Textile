package tw.com.eeit94.textile.model.member.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;

/**
 * 實作驗證信箱存不存在的Annotation。
 * 
 * @author 賴
 * @version 2017/06/16
 * @see {@link MemberService}
 */
public class CheckEmailExistValidator implements ConstraintValidator<CheckEmailExist, String> {
	@Autowired
	private MemberService memberService;
	private String column;

	@Override
	public void initialize(CheckEmailExist constraintAnnotation) {
		this.column = constraintAnnotation.column();

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * 驗證失敗或成功皆回傳boolean值，並且失敗時需修改違反限制的錯誤資訊(覆寫String message())。
	 * 
	 * @author 賴
	 * @version 2017/06/09
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		MemberBean mbean = memberService.selectByEmail(value);
		if (mbean != null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(this.column + "已存在").addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}
}