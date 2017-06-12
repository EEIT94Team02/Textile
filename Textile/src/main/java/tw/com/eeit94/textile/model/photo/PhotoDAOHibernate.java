package tw.com.eeit94.textile.model.photo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Repository(value = "photoDAO")
public class PhotoDAOHibernate implements PhotoDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public PhotoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String selectMax(PhotoBean bean) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<PhotoBean> query = cb.createQuery(PhotoBean.class);
		Root<PhotoBean> root = query.from(PhotoBean.class);
		Predicate p1 = cb.like(root.<String>get("photono"), bean.getPhotono() + "%");
		List<PhotoBean> temp = getSession().createQuery(query.where(p1)).getResultList();
		if (temp.isEmpty()) {
			return "00000000000000000000";
		}
		String beanPhotono = temp.get(temp.size() - 1).getPhotono();
		return beanPhotono;
	}

	@Override
	public List<PhotoBean> select() {
		return this.getSession().createQuery("FROM PhotoBean", PhotoBean.class).getResultList();
	}

	@Override
	public PhotoBean selectByPrimarykey(PhotoBean bean) {
		return getSession().get(PhotoBean.class, bean.getPhotono());
	}

	@Override
	public List<PhotoBean> selectByAlbumno(PhotoBean bean) {
		return getSession().createQuery("FROM PhotoBean where albumno =" + bean.getAlbumno(), PhotoBean.class)
				.getResultList();
	}

	@Override
	public List<PhotoBean> selectByOthers(PhotoBean bean) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<PhotoBean> query = cb.createQuery(PhotoBean.class);
		Root<PhotoBean> root = query.from(PhotoBean.class);
		Predicate p1 = cb.like(root.<String>get("photoname"),
				bean.getPhotoname() == null ? "%" : "%" + bean.getPhotoname() + "%");
		Predicate p2 = cb.like(root.<String>get("position"),
				bean.getPosition() == null ? "%" : "%" + bean.getPosition() + "%");
		Predicate p3 = cb.like(root.<String>get("visibility"),
				bean.getVisibility() == null ? "%" : "%" + bean.getVisibility() + "%");
		Predicate p6 = cb.like(root.<String>get("interpretation"),
				bean.getInterpretation() == null ? "%" : "%" + bean.getInterpretation() + "%");
		Predicate p4;
		if (bean.getAlbumno() != null) {
			p4 = cb.equal(root.<Integer>get("albumno"), bean.getAlbumno());
		} else {
			p4 = cb.ge(root.<Integer>get("albumno"), 0);
		}
		Predicate p5 = cb.like(root.<String>get("photono"),
				bean.getPhotono() == null ? "%" : "%" + bean.getPhotono() + "%");

		return getSession().createQuery(query.where(p1, p2, p3, p4, p5, p6)).getResultList();
	}

	@Override
	public PhotoBean insert(PhotoBean bean) {
		if (bean != null) {
			PhotoBean select = this.selectByPrimarykey(bean);
			if (select == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public PhotoBean update(PhotoBean bean) {
		PhotoBean select = this.selectByPrimarykey(bean);
		if (select != null) {
			select.setPhotoname(bean.getPhotoname());
			select.setInterpretation(bean.getInterpretation());
			select.setAlbumno(bean.getAlbumno());
			select.setPosition(bean.getPosition());
			select.setVisibility(bean.getVisibility());
		}
		return select;
	}

	@Override
	public boolean delete(PhotoBean bean) {
		if (bean != null) {
			if (this.selectByPrimarykey(bean) != null) {
				getSession().delete(this.selectByPrimarykey(bean));
				return true;
			}
		}
		return false;
	}
}