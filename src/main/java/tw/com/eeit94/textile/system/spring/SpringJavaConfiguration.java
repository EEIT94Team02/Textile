package tw.com.eeit94.textile.system.spring;

import java.io.IOException;
import java.util.Properties;

import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import freemarker.template.TemplateException;

/**
 * Spring Java 組態設定檔。 最底層的Bean Container，Service、DAO或與「Servlet無關」的Bean宣告在此。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Configuration
@ComponentScan(basePackages = { "tw.com.eeit94.textile.model" })
@EnableTransactionManagement
public class SpringJavaConfiguration {

	/**
	 * 單機測試使用org.springframework.jdbc.datasource.DriverManagerDataSource，
	 * 上線測試使用org.springframework.jndi.JndiObjectFactoryBean。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Bean
	public javax.sql.DataSource dataSource() {
		org.springframework.jndi.JndiObjectFactoryBean jndiObjectFactoryBean = new org.springframework.jndi.JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/SQLSDB");
		try {
			jndiObjectFactoryBean.afterPropertiesSet();
		} catch (IllegalArgumentException | NamingException e) {
			throw new RuntimeException(e);
		}
		return (javax.sql.DataSource) jndiObjectFactoryBean.getObject();

		// org.springframework.jdbc.datasource.DriverManagerDataSource
		// driverManagerDataSource = new
		// org.springframework.jdbc.datasource.DriverManagerDataSource();
		// driverManagerDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// driverManagerDataSource.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=textile");
		// driverManagerDataSource.setUrl("jdbc:sqlserver://192.168.43.17:1433;DatabaseName=textile");
		// driverManagerDataSource.setUsername("sa");
		// // driverManagerDataSource.setPassword("sa123456");
		// driverManagerDataSource.setPassword("P@ssw0rd");
		// return driverManagerDataSource;
	}

	/**
	 * 伺服器關閉時，Spring會自動關閉sessionFactory，因為LocalSessionFactoryBean實作了DisposableBean介面。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Bean
	public org.hibernate.SessionFactory sessionFactory() {
		org.springframework.orm.hibernate5.LocalSessionFactoryBean localSessionFactoryBean = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(this.dataSource());
		localSessionFactoryBean.setPackagesToScan(new String[] { "tw.com.eeit94.textile.model" });

		java.util.Properties properties = new java.util.Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		properties.setProperty("hibernate.transaction.coordinator_class",
				"org.hibernate.transaction.JDBCTransactionFactory");
		// 上線使用Spring的交易管理時，下一行要註解掉！
		// properties.setProperty("hibernate.current_session_context_class",
		// "thread");
		localSessionFactoryBean.setHibernateProperties(properties);

		try {
			localSessionFactoryBean.afterPropertiesSet();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return localSessionFactoryBean.getObject();
	}

	/**
	 * Spring Hibernate交易管理員。
	 * 
	 * @author 共同
	 * @version 2017/06/08
	 */
	@Bean
	public org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager() {
		org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager = new org.springframework.orm.hibernate5.HibernateTransactionManager();
		transactionManager.setSessionFactory(this.sessionFactory());
		return transactionManager;
	}

	/**
	 * Hibernate Validator，配合驗證資料的Annotation。
	 * 
	 * @author 共同
	 * @version 2017/06/09
	 */
	@Bean
	public javax.validation.executable.ExecutableValidator executableValidator() {
		javax.validation.ValidatorFactory validatorFactory = javax.validation.Validation.buildDefaultValidatorFactory();
		javax.validation.executable.ExecutableValidator executableValidator = validatorFactory.getValidator()
				.forExecutables();
		return executableValidator;
	}

	/**
	 * 驗證日期專用，形式為「yyyy-MM-dd」。
	 * 
	 * @author 共同
	 * @version 2017/06/09
	 */
	@Bean
	public java.text.SimpleDateFormat simpleDateFormat() {
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat;
	}

	/**
	 * 發送郵件專用。
	 * 
	 * 更多的JavaMailProperties的設定請按：<a href=
	 * "https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html">連結</a>。
	 * 
	 * 必須有自己的STMP伺服器，也可以使用網路上假的STMP伺服器作代理，例如Google的STMP伺服器，需要Gmail帳號。
	 * 
	 * @author 共同
	 * @version 2017/06/19
	 */
	@Bean
	public org.springframework.mail.javamail.JavaMailSender javaMailSender() {
		org.springframework.mail.javamail.JavaMailSenderImpl javaMailSender = new org.springframework.mail.javamail.JavaMailSenderImpl();
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.host", "smtp.gmail.com");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.socketFactory.port", "465");
		javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMailProperties.put("mail.smtp.port", "465");
		javaMailSender.setJavaMailProperties(javaMailProperties);
		javaMailSender.setUsername("textilesystem11");
		javaMailSender.setPassword("SQLP@ssw0rd");
		javaMailSender.setDefaultEncoding("UTF-8");
		return javaMailSender;
	}

	/**
	 * 發送郵件專用，可讀取類似Html文件的模板作郵件內容使用。
	 * 
	 * @author 共同
	 * @version 2017/06/19
	 * @throws TemplateException
	 * @throws IOException
	 */
	@Bean
	public freemarker.template.Configuration freeMarkerConfiguration() {
		org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean();
		freeMarkerConfigurationFactoryBean.setDefaultEncoding("UTF-8");
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/emailtemplate/");
		freemarker.template.Configuration configuration = null;
		try {
			configuration = freeMarkerConfigurationFactoryBean.createConfiguration();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return configuration;
	}

	/**
	 * 上傳多個檔案專用。
	 * 
	 * @author 陳
	 * @version 2017/06/12
	 */
	@Bean
	public org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(50000000);
		multipartResolver.setMaxInMemorySize(10000000);
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
}
