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
import tw.com.eeit94.textile.system.common.ConstHelperKey;
import tw.com.eeit94.textile.system.common.ConstResource;

/**
 * 註冊完寄發驗證信箱的Mail Service。
 * 
 * 定義Mail Service的屬性成員與方法，所有需要模版(Html)的Mail Sender
 * 
 * 皆需要Apache的FreeMarker和Spring支持的JavaMailSender。
 * 
 * @author 賴
 * @version 2017/06/19
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
	 * 實作寄送郵件步驟：
	 * 
	 * 1. 製作model資料，為一JavaBean，要嵌合郵件的Html模版內的資料，即類似JSP的EL。
	 * 
	 * 2. 代入model和Html模版由FreeMarkerTemplateUtils製作郵件內容。
	 * 
	 * 3. 建立MimeMessagePreparator並實作prepare()方法，代入上述郵件內容。
	 * 
	 * 4. 將MimeMessagePreparator放入javaMailSender.send()方法即送出郵件。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public void doSendEmail(Map<String, String> dataAndErrorsMap) {
		Object model = this.getSendedModel(dataAndErrorsMap);
		MimeMessagePreparator mimeMessagePreparator = this.getPreparator(model);
		this.javaMailSender.send(mimeMessagePreparator);
	}

	/**
	 * 設定驗證信所需的參數：信箱、姓名和尾端加密的網址，「而這些要設定在欲重新寄發驗證信的網頁」！
	 * 
	 * 這裡的dataAndErrorsMap可以只放會員資料的信箱，必須確認已由LoginController驗證完帳號密碼。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public Object getSendedModel(Map<String, String> dataAndErrorsMap) {
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
		checkUrl = sBuffer.append(checkUrl).append(ConstHelperKey.QUESTION.key())
				.append(ConstHelperKey.QUERY_EQUAL.key()).append(encryptedMEmail).toString();

		MailRegisterModelBean mrmbean = new MailRegisterModelBean();
		mrmbean.setmEmail(mEmail);
		mrmbean.setmName(mName);
		mrmbean.setCheckUrl(checkUrl);
		return mrmbean;
	}

	/**
	 * 從模板製作郵件內容。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public String getFreeMakeTemplateContent(Object model) {
		StringBuffer contentBuffer = new StringBuffer();
		try {
			contentBuffer.append(FreeMarkerTemplateUtils.processTemplateIntoString(
					this.freeMarkerConfiguration.getTemplate(ConstResource.REGISTER_SUCCESS_EMAIL_TEMPLATE.path()),
					model));
			return contentBuffer.toString();
		} catch (Exception e) {
			StringBuffer sBuffer = new StringBuffer();
			this.logsService.insertNewLog(sBuffer.append("轉換電子郵件模版時發生例外").append(e.getMessage()).toString());
		}
		return null;
	}

	/**
	 * 準備寄送郵件的物件，由JavaMailSender所接收的參數。
	 * 
	 * @author 賴
	 * @version 2017/06/18
	 */
	public MimeMessagePreparator getPreparator(final Object model) {
		final MailRegisterModelBean finalModel = (MailRegisterModelBean) model;

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				String content = getFreeMakeTemplateContent(finalModel);
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setFrom(ConstUserParameter.EMAILFROM.param());
				mimeMessageHelper.setTo(finalModel.getmEmail());
				mimeMessageHelper.setSubject("註冊成功：歡迎您加入Textile交友網的新會員");
				mimeMessageHelper.setSentDate(new java.util.Date(System.currentTimeMillis()));
				// true for multipart message
				mimeMessageHelper.setText(content, true);
				// mimeMessageHelper.addAttachment(attachmentFilename, file);
			}
		};
		return preparator;
	}
}