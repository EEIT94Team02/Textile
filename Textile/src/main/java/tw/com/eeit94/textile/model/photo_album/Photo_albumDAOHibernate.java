/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo_album;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
				new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		SessionFactory sessionFacotry = (SessionFactory) context.getBean("sessionFactory");
		sessionFacotry.getCurrentSession().beginTransaction();
		
		Photo_albumDAO dao = (Photo_albumDAOHibernate) context.getBean("photo_albumDAOHibernate");
		List<Photo_albumBean> beans = null;
		Photo_albumBean bean = null;
		
		beans = dao.select();
		System.out.println(beans);
		
		Photo_albumBean test = new Photo_albumBean();
		test.setAlbumno(1);
		bean = dao.selectByAlbumNo(test);
		System.out.println(bean);
		
		Photo_albumBean insert = new Photo_albumBean();
		insert.setAlbumname("大頭貼");
		insert.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		insert.setIntroduction("大頭貼");
		insert.setmId(00000005);
		insert.setVisibility("公開");
		bean = dao.insert(insert);
		System.out.println(bean);
		
		Photo_albumBean update = new Photo_albumBean();
		update.setAlbumno(6);
		update.setAlbumname("大頭貼");
		update.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		update.setIntroduction("大頭貼");
		update.setmId(00000005);
		update.setVisibility("私人");
		bean = dao.update(update);
		System.out.println(bean);
		
		Photo_albumBean delete = new Photo_albumBean();
		delete.setAlbumno(6);
		boolean result = dao.delete(delete);
		System.out.println(result);		
		
		sessionFacotry.getCurrentSession().getTransaction().commit();
		sessionFacotry.getCurrentSession().close();
		((ConfigurableApplicationContext) context).close();
	}

	@Override
	public List<Photo_albumBean> select() {
		return this.getSession().createQuery("FROM Photo_albumBean", Photo_albumBean.class).getResultList();
	}

	@Override
	public Photo_albumBean selectByAlbumNo(Photo_albumBean bean) {
		return getSession().get(Photo_albumBean.class , bean.getAlbumno());
	}
	
	@Override
	public List<Photo_albumBean> selectByOthers(Photo_albumBean bean) {	
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Photo_albumBean> query = cb.createQuery(Photo_albumBean.class);
		Root<Photo_albumBean> root = query.from(Photo_albumBean.class);
		Predicate p1 = cb.like(root.<String>get("albumname"), bean.getAlbumname() == null ? "%" : "%"+bean.getAlbumname()+"%");
		Predicate p2 = cb.like(root.<String>get("introduction"), bean.getIntroduction() == null ? "%" : "%"+bean.getIntroduction()+"%");
		Predicate p3 = cb.like(root.<String>get("visibility"), bean.getVisibility() == null ? "%" : "%"+bean.getVisibility()+"%");
		Predicate p4 = cb.equal(root.<Integer>get("mId"), bean.getmId());
		return getSession().createQuery(query.where(p1,p2,p3,p4)).getResultList();
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
			Photo_albumBean select = this.selectByAlbumNo(bean);
			if(select!=null) {
				select.setmId(bean.getmId());
				select.setCreatetime(bean.getCreatetime());
				select.setAlbumname(bean.getAlbumname());
				select.setIntroduction(bean.getIntroduction());
				select.setVisibility(bean.getVisibility());	
				select.setmId(bean.getmId());				
				return select;
			}
		}
		return null;
	}

	@Override
	public boolean delete(Photo_albumBean bean) {
			if(this.selectByAlbumNo(bean)!=null){
				getSession().delete(this.selectByAlbumNo(bean));
				return true;
			}
		return false;
	}


}