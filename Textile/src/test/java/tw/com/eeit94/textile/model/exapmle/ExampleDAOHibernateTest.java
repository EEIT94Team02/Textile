/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.exapmle;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tw.com.eeit94.textile.model.example.ExampleDAO;
import tw.com.eeit94.textile.model.test.TestUtils;
import tw.com.eeit94.textile.system.spring.SpringJavaConfiguration;

/**
 * 測試ExampleDAOHibernate的程式。
 * 
 * @author 賴
 * @version 2017/06/08
 */
public class ExampleDAOHibernateTest {
	
	public static void main(String args[]){
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		ExampleDAO exampleDAO = (ExampleDAO) context.getBean("exampleDAO");
		
		sessionFactory.getCurrentSession().beginTransaction();
		TestUtils.printResult(exampleDAO.selectAll());
		sessionFactory.getCurrentSession().getTransaction().commit();
		
		((ConfigurableApplicationContext) context).close();
	}
}