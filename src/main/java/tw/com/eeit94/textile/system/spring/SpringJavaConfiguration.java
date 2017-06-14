package tw.com.eeit94.textile.system.spring;

import java.io.IOException;

import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
			throw new RuntimeException();
		}
		return (javax.sql.DataSource) jndiObjectFactoryBean.getObject();
		 

//		org.springframework.jdbc.datasource.DriverManagerDataSource driverManagerDataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		driverManagerDataSource.setUrl("jdbc:sqlserver://192.168.119.128:1433;DatabaseName=Proj");
//		driverManagerDataSource.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=Proj");
//		driverManagerDataSource.setUsername("sa");
//		driverManagerDataSource.setPassword("passw0rd");
//		return driverManagerDataSource;
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
//		properties.setProperty("hibernate.current_session_context_class", "thread");
		localSessionFactoryBean.setHibernateProperties(properties);

		try {
			localSessionFactoryBean.afterPropertiesSet();
		} catch (IOException e) {
			throw new RuntimeException();
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
}