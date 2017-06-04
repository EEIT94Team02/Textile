/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo_album;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.activity_member.Activity_memberBean;
import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;


/*
 * Hibernate DAO產生步驟：
 * 1. Hibernate DAO名稱為'"Table名稱" + "DAOHibernate"'。
 * 2. 實作'"Table名稱" + "DAO"'介面，並覆寫方法。
 * 3. 標記@Repository(value = '"Table名稱(第一個字母小寫)" + "DAO"')。
 * 4. 加入Bean元件並標記@Autowired。
 */
@Repository
public class Photo_albumDAOHibernate implements Photo_albumDAO {
	@Autowired
	private SessionFactory sessionFacotry;

	public Photo_albumDAOHibernate(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	private Session getSession() {
		return sessionFacotry.getCurrentSession();
	}

	// 測試程式
	public static void main(String args[]) {

		ApplicationContext context =
//				new ClassPathXmlApplicationContext("beans.config.xml");
				new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFacotry = (SessionFactory) context.getBean("sessionFactory");
		sessionFacotry.getCurrentSession().beginTransaction();
		
		Photo_albumDAO dao = (Photo_albumDAOHibernate) context.getBean("photo_albumDAOHibernate");
		
		List<Photo_albumBean> beans = dao.select();
		System.out.println(beans);
		
//		Photo_albumBean test = new Photo_albumBean();
//		test.setAlbumno(1);
//		Photo_albumBean bean = dao.select(test);
//		System.out.println(bean);
		
//		Photo_albumBean test = new Photo_albumBean();
//		test.setAlbumname("大頭貼");
//		test.setCreatetime(new java.util.Date());
//		test.setIntroduction("大頭貼");
//		test.setMemberid(00000005);
//		test.setVisibility("公開");
//		Photo_albumBean bean = dao.insert(test);
//		System.out.println(bean);
		
//		Photo_albumBean test = new Photo_albumBean();
//		test.setAlbumno(6);
//		test.setAlbumname("大頭貼");
//		test.setCreatetime(new java.util.Date());
//		test.setIntroduction("大頭貼");
//		test.setMemberid(00000005);
//		test.setVisibility("私人");
//		Photo_albumBean bean = dao.update(test);
//		System.out.println(bean);
		
//		Photo_albumBean test = new Photo_albumBean();
//		test.setAlbumno(6);
//		boolean result = dao.delete(test);
//		System.out.println(result);		
		
		sessionFacotry.getCurrentSession().getTransaction().commit();
		sessionFacotry.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}

	@Override
	public List<Photo_albumBean> select() {
		return this.getSession().createQuery("FROM Photo_albumBean", Photo_albumBean.class).getResultList();
	}

	@Override
	public Photo_albumBean select(Photo_albumBean bean) {
		return getSession().get(Photo_albumBean.class , bean.getAlbumno());
	}
	
	@Override
	public Photo_albumBean selectByAlbumName(Photo_albumBean bean) {
//		Query<Photo_albumBean> query = getSession().createQuery("FROM Photo_albumBean where albumname=:albumname", Photo_albumBean.class);
//		query.setParameter("albumname", bean.getAlbumname());		
		return getSession().get(Photo_albumBean.class , bean.getAlbumname());
	}
	
	@Override
	public List<Photo_albumBean> selectByMemberId(Photo_albumBean bean) {
		Query<Photo_albumBean> query = getSession().createQuery("FROM Photo_albumBean where memberid=:memberid", Photo_albumBean.class);
		query.setParameter("memberid", bean.getMemberid());		
		return query.list();
	}

	@Override
	public Photo_albumBean insert(Photo_albumBean bean) {
		if(bean.getAlbumno() == null) {
				this.getSession().save(bean);
				return bean;
		}
		return null;
	}

	@Override
	public Photo_albumBean update(Photo_albumBean bean) {
		if(bean!=null) {
			Photo_albumBean select = this.select(bean);
			if(select!=null) {
				select.setMemberid(bean.getMemberid());
				select.setCreatetime(bean.getCreatetime());
				select.setAlbumname(bean.getAlbumname());
				select.setIntroduction(bean.getIntroduction());
				select.setVisibility(bean.getVisibility());	
				select.setMemberid(bean.getMemberid());				
				return select;
			}
		}
		return null;
	}

	@Override
	public boolean delete(Photo_albumBean bean) {
		if(bean != null){
			if(this.select(bean)!=null){
			getSession().delete(this.select(bean));
			return true;
			}
		}
		return false;
	}

//	private final String SELECT_BY_PRICE_LESS_THAN = "select name from ExampleBean where price<";	
//	@Override
//	public List<PhotoBean> selectByPriceLessThan(double price) {
//		@SuppressWarnings("unused")
//		Query<PhotoBean> query = this.getSession().createQuery(SELECT_BY_PRICE_LESS_THAN + price, PhotoBean.class);
//		/*
//		 * 用Hibernate實作
//		 */
//		return null;
//	}

}