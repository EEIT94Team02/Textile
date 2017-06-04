/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

/*
 * Hibernate DAO產生步驟：
 * 1. Hibernate DAO名稱為'"Table名稱" + "DAOHibernate"'。
 * 2. 實作'"Table名稱" + "DAO"'介面，並覆寫方法。
 * 3. 標記@Repository(value = '"Table名稱(第一個字母小寫)" + "DAO"')。
 * 4. 加入Bean元件並標記@Autowired。
 */
@Repository
public class PhotoDAOHibernate implements PhotoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public PhotoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 測試程式
	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		
		PhotoDAO dao = (PhotoDAOHibernate) context.getBean("photoDAOHibernate");
		
//		List<PhotoBean> beans = dao.select();
//		System.out.println(beans);	
		
		PhotoBean test = new PhotoBean();
		test.setPhotono("20170527000000010001");
		PhotoBean bean = dao.select(test);
		System.out.println(bean);	
		
//		PhotoBean test = new PhotoBean();
//		test.setPhotono("20170527000000010002");
//		test.setMemberid(00000001);
//		test.setRespath("xxx");
//		test.setPhotoname("Roger");
//		test.setInterpretation("tennis");	
//		test.setAlbumno(1);
//		test.setPosition("大頭貼");
//		test.setVisibility("公開");		
//		PhotoBean bean = dao.insert(test);
//		System.out.println(bean);
		
//		PhotoBean test = new PhotoBean();
//		test.setPhotono("20170527000000010002");
//		test.setMemberid(00000001);
//		test.setRespath("xxx");
//		test.setPhotoname("Roger");
//		test.setInterpretation("tennis");	
//		test.setAlbumno(1);
//		test.setPosition("大頭貼");
//		test.setVisibility("私人");		
//		PhotoBean bean = dao.update(test);
//		System.out.println(bean);
		
//		PhotoBean del = new PhotoBean();
//		del.setPhotono("20170527000000010002");	
//		boolean result = dao.delete(del);
//		System.out.println(result);		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();			
	}

	@Override
	public List<PhotoBean> select() {
		return this.getSession().createQuery("FROM PhotoBean", PhotoBean.class).getResultList();
	}

	@Override
	public PhotoBean select(PhotoBean bean) {
		return getSession().get(PhotoBean.class , bean.getPhotono());
	}

	@Override
	public PhotoBean insert(PhotoBean bean) {
		if(bean!=null) {
			PhotoBean select = this.select(bean);
			if(select==null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public PhotoBean update(PhotoBean bean) {
		if(bean!=null) {
			PhotoBean select = this.select(bean);
			if(select!=null) {
				select.setMemberid(bean.getMemberid());
				select.setRespath(bean.getRespath());
				select.setPhotoname(bean.getPhotoname());
				select.setInterpretation(bean.getInterpretation());
				select.setAlbumno(bean.getAlbumno());
				select.setPosition(bean.getPosition());
				select.setVisibility(bean.getVisibility());				
				return select;
			}
		}
		return null;
	}

	@Override
	public boolean delete(PhotoBean bean) {
		if(bean != null){
			if(this.select(bean)!= null){
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