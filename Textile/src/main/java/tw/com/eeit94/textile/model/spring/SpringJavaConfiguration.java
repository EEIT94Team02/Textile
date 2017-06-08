package tw.com.eeit94.textile.model.spring;

import java.io.IOException;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/*
 * 最底層的Bean Container，Service、DAO或與「Servlet無關」的Bean宣告在此。
 */
@Configuration
@ComponentScan(basePackages = { "tw.com.eeit94.textile.model"})
public class SpringJavaConfiguration {

	/*
	 * 單機測試使用org.springframework.jdbc.datasource.DriverManagerDataSource，
	 * 上線測試使用org.springframework.jndi.JndiObjectFactoryBean。
	 */
	@Bean
	public javax.sql.DataSource dataSource() {
		
//		  org.springframework.jndi.JndiObjectFactoryBean jndiObjectFactoryBean = new org.springframework.jndi.JndiObjectFactoryBean();
//		  jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/SQLSDB"); 
//		  try {
//		  jndiObjectFactoryBean.afterPropertiesSet(); 
//		  } catch (IllegalArgumentException e) {
//			  throw new RuntimeException(); 
//		  } catch (NamingException e) {
//			  throw new RuntimeException(); 
//		  }
//		  return (javax.sql.DataSource) jndiObjectFactoryBean.getObject();		 

		org.springframework.jdbc.datasource.DriverManagerDataSource driverManagerDataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		driverManagerDataSource.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=Textile");
		driverManagerDataSource.setUsername("sa");
		driverManagerDataSource.setPassword("sa123456");
		return driverManagerDataSource;
	}

	// 伺服器關閉時，Spring會自動關閉sessionFactory，因為LocalSessionFactoryBean實作了DisposableBean介面。
	@Bean
	public org.hibernate.SessionFactory sessionFactory() {
		org.springframework.orm.hibernate5.LocalSessionFactoryBean localSessionFactoryBean = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(this.dataSource());
		localSessionFactoryBean.setPackagesToScan(new String[] { "*.model" });

		java.util.Properties properties = new java.util.Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		properties.setProperty("hibernate.transaction.coordinator_class",
				"org.hibernate.transaction.JDBCTransactionFactory");
		// 上線使用Spring的交易管理時，下一行要註解掉！
		properties.setProperty("hibernate.current_session_context_class", "thread");
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setAnnotatedClasses(tw.com.eeit94.textile.model.photo_album.Photo_albumBean.class,tw.com.eeit94.textile.model.photo.PhotoBean.class,tw.com.eeit94.textile.model.activity_member.Activity_memberBean.class,tw.com.eeit94.textile.model.activity.ActivityBean.class);
				
		try {
			localSessionFactoryBean.afterPropertiesSet();
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return localSessionFactoryBean.getObject();
	}
	
	@Bean
	public org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager() {
		org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager = new org.springframework.orm.hibernate5.HibernateTransactionManager();
		transactionManager.setSessionFactory(this.sessionFactory());
		return transactionManager;
	}
}