package tw.com.eeit94.textile.model.member.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import tw.com.eeit94.textile.controller.user.ConstUserKey;
import tw.com.eeit94.textile.controller.user.ConstUserParameter;
import tw.com.eeit94.textile.model.logs.LogsService;
import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.MemberService;
import tw.com.eeit94.textile.model.member.util.ConstMemberKey;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;

/**
 * 註冊完寄發驗證信箱的Mail Service。
 * 
 * 定義Mail Service的屬性成員與方法，所有需要模版(Html)的Mail Sender
 * 
 * 皆需要Apache的VelocityEngine和Spring支持的JavaMailSender。
 * 
 * @author 賴
 * @version 2017/06/18
 */
@Service
public class MailRegisterService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Configuration freeMarkerConfiguration;
	@Autowired
	private LogsService logsService;
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberService memberService;

	/**
	 * 設定驗證信所需的參數：信箱、姓名和尾端加密的網址，「而這些要設定在欲重新寄發驗證信的網頁」！
	 * 
	 * 這裡的dataAndErrorsMap可以只放會員資料的信箱，必須確認已由LoginController驗證完帳號密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public Map<String, String> getSendedMap(Map<String, String> dataAndErrorsMap) {
		// 使用者帳號密碼經驗證一致，但無法登入因需驗證信箱，只能由信箱將會員資料一一找出。
		String mEmail = dataAndErrorsMap.get(ConstMemberKey.Email.key());
		MemberBean mbean = this.memberService.selectByEmail(mEmail);
		String mName = mbean.getmName();
		String encryptedMEmail = "";
		try {
			encryptedMEmail = this.secureService.getEncryptedText(mEmail, ConstSecureParameter.EMAIL.param());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String checkUrl = dataAndErrorsMap.get(ConstUserKey.CHECKURL.key());
		StringBuffer sBuffer = new StringBuffer();
		checkUrl = sBuffer.append(checkUrl).append("?q=").append(encryptedMEmail).toString();

		dataAndErrorsMap.put(ConstMemberKey.Name.key(), mName);
		dataAndErrorsMap.put(ConstUserKey.CHECKURL.key(), checkUrl);
		return dataAndErrorsMap;
	}

	public String getFreeMakeTemplateContent(Map<String, String> emailMap) {
		StringBuffer contentBuffer = new StringBuffer();
		try {
			contentBuffer.append(FreeMarkerTemplateUtils.processTemplateIntoString(
					this.freeMarkerConfiguration.getTemplate("register_success.html"), emailMap));
			return contentBuffer.toString();
		} catch (Exception e) {
			StringBuffer sBuffer = new StringBuffer();
			this.logsService.insertNewLog(sBuffer.append("轉換電子郵件模版時發生例外").append(e.getMessage()).toString());
		}
		return null;
	}

	public MimeMessagePreparator getPreparator(Map<String, String> emailMap) {
		final Map<String, String> finalEmailMap = emailMap;

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				String content = getFreeMakeTemplateContent(finalEmailMap);
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setFrom(ConstUserParameter.EMAILFROM.param());
				mimeMessageHelper.setTo(emailMap.get(ConstMemberKey.Email.key()));
				// true for multipart message
				mimeMessageHelper.setText(content, true);
				// mimeMessageHelper.addAttachment(attachmentFilename, file);
			}
		};
		return preparator;
	}

	public void doSendEmail(Map<String, String> dataAndErrorsMap) {
		Map<String, String> emailMap = this.getSendedMap(dataAndErrorsMap);
		MimeMessagePreparator mimeMessagePreparator = this.getPreparator(emailMap);
		this.javaMailSender.send(mimeMessagePreparator);
	}
}