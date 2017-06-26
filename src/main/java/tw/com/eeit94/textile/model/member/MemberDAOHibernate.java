package tw.com.eeit94.textile.model.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.com.eeit94.textile.model.member.service.MemberKeyWordsBean;

/**
 * 控制會員基本資料的DAO，利用Hibernate來實作。
 * 
 * @author 賴
 * @version 2017/06/08
 */
@Repository(value = "memberDAO")
public class MemberDAOHibernate implements MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private List<MemberBean> results;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	private static final String SELECT_ALL = "from MemberBean";

	@Override
	public List<MemberBean> selectAll() {
		Query<MemberBean> query = this.getSession().createQuery(SELECT_ALL, MemberBean.class);
		return query.list();
	}

	@Override
	public List<MemberBean> select(MemberBean mbean) {
		this.results = new ArrayList<>();
		this.results.add(this.getSession().get(MemberBean.class, mbean.getmId()));
		return this.results;
	}

	@Override
	public List<MemberBean> insert(MemberBean mbean) {
		this.getSession().save(mbean);
		return this.select(mbean);
	}

	@Override
	public List<MemberBean> update(MemberBean mbean) {
		this.getSession().update(mbean);
		return this.select(mbean);
	}

	@Override
	public List<MemberBean> delete(MemberBean mbean) {
		this.getSession().delete(mbean);
		return this.select(mbean);
	}

	/**
	 * 特殊查詢：這裡只查詢基本資料、論壇經歷、個人狀況，有關個人喜好的查詢會在回傳後List&lt;MemberBean&gt;，
	 * 逐一利用位元比對。因為要特製化查詢，MemberBean的屬性成員不敷使用，因此使用新創的MemberKeyWordsBean。
	 * 
	 * @author 賴
	 * @version 2017/06/08
	 */
	@Override
	public List<MemberBean> selectByKeyWords(MemberKeyWordsBean mkwbean) {
		// CriteriaBuilder專門建立查詢的邏輯條件，例如：like、equal、or、between......等。
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		// CriteriaQuery專門物件導向化SQL的關鍵字，例如：SELECT、FROM、WHERE、ORDERBY......等。
		CriteriaQuery<MemberBean> query = cBuilder.createQuery(MemberBean.class);
		Root<MemberBean> root = query.from(MemberBean.class);

		// Predicate專門存放物件導向化的SQL Expression。
		List<Predicate> pList = new ArrayList<>();
		Predicate pGender;
		Predicate pBirthday;
		Predicate pAddress_County;
		Predicate pAddress_Region;
		Predicate pScores;
		Predicate pCreateTime;
		Predicate pCareer;
		Predicate pEducation;
		Predicate pEconomy;
		Predicate pMarriage;
		Predicate pFamily;
		Predicate pBloodType;
		Predicate pConstellation;
		Predicate pReligion;

		/*
		 * 如果mkwbean內對應資料欄位的屬性不是null，那麼就建立該SQL Expression的Predicate物件，
		 * 並加入待查詢清單中。
		 */
		if (mkwbean.getmGender() != null) {
			pGender = cBuilder.equal(root.<String>get("mGender"), mkwbean.getmGender());
			pList.add(pGender);
		}
		if (mkwbean.getmBirthdayBegin() != null || mkwbean.getmBirthdayEnd() != null) {
			if (mkwbean.getmBirthdayBegin() == null) {
				pBirthday = cBuilder.between(root.<java.util.Date>get("mBirthday"), new java.util.Date(0),
						mkwbean.getmBirthdayEnd());
			} else if (mkwbean.getmBirthdayEnd() == null) {
				pBirthday = cBuilder.between(root.<java.util.Date>get("mBirthday"), mkwbean.getmBirthdayBegin(),
						new java.util.Date(System.currentTimeMillis()));
			} else {
				pBirthday = cBuilder.between(root.<java.util.Date>get("mBirthday"), mkwbean.getmBirthdayBegin(),
						mkwbean.getmBirthdayEnd());
			}
			pList.add(pBirthday);
		}
		if (mkwbean.getmAddress_County() != null) {
			pAddress_County = cBuilder.equal(root.<String>get("mAddress_County"), mkwbean.getmAddress_County());
			pList.add(pAddress_County);
		}
		if (mkwbean.getmAddress_Region() != null) {
			List<String> mAddress_Region = mkwbean.getmAddress_Region();
			Predicate[] orPs = new Predicate[mAddress_Region.size()];
			for (int i = 0; i < mAddress_Region.size(); i++) {
				orPs[i] = cBuilder.equal(root.<String>get("mAddress_Region"), mkwbean.getmAddress_Region().get(i));
			}
			pAddress_Region = cBuilder.or(orPs);
			pList.add(pAddress_Region);
		}
		if (mkwbean.getmScores() != null) {
			pScores = cBuilder.ge(root.<Integer>get("mScores"), mkwbean.getmScores());
			pList.add(pScores);
		}
		if (mkwbean.getmCreateTimeBegin() != null || mkwbean.getmCreateTimeEnd() != null) {
			if (mkwbean.getmCreateTimeBegin() == null) {
				pCreateTime = cBuilder.between(root.<java.sql.Timestamp>get("mCreateTime"), new java.util.Date(0),
						mkwbean.getmCreateTimeEnd());
			} else if (mkwbean.getmCreateTimeEnd() == null) {
				pCreateTime = cBuilder.between(root.<java.sql.Timestamp>get("mCreateTime"),
						mkwbean.getmCreateTimeBegin(), new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				pCreateTime = cBuilder.between(root.<java.sql.Timestamp>get("mCreateTime"),
						mkwbean.getmCreateTimeBegin(), mkwbean.getmCreateTimeEnd());
			}
			pList.add(pCreateTime);
		}
		if (mkwbean.getmCareer() != null) {
			pCareer = cBuilder.equal(root.<Integer>get("mCareer"), mkwbean.getmCareer());
			pList.add(pCareer);
		}
		if (mkwbean.getmEducation() != null) {
			pEducation = cBuilder.equal(root.<Integer>get("mEducation"), mkwbean.getmEducation());
			pList.add(pEducation);
		}
		if (mkwbean.getmEconomy() != null) {
			pEconomy = cBuilder.equal(root.<Integer>get("mEconomy"), mkwbean.getmEconomy());
			pList.add(pEconomy);
		}
		if (mkwbean.getmMarriage() != null) {
			pMarriage = cBuilder.equal(root.<Integer>get("mMarriage"), mkwbean.getmMarriage());
			pList.add(pMarriage);
		}
		if (mkwbean.getmFamily() != null) {
			pFamily = cBuilder.equal(root.<Integer>get("mFamily"), mkwbean.getmFamily());
			pList.add(pFamily);
		}
		if (mkwbean.getmBloodType() != null) {
			pBloodType = cBuilder.equal(root.<Integer>get("mBloodType"), mkwbean.getmBloodType());
			pList.add(pBloodType);
		}
		if (mkwbean.getmConstellation() != null) {
			pConstellation = cBuilder.equal(root.<Integer>get("mConstellation"), mkwbean.getmConstellation());
			pList.add(pConstellation);
		}
		if (mkwbean.getmReligion() != null) {
			pReligion = cBuilder.equal(root.<Integer>get("mReligion"), mkwbean.getmReligion());
			pList.add(pReligion);
		}

		/*
		 * 將List<Predicate>轉成Predicate[]，才能代入CriteriaQuery<MemberBean>的where(
		 * Predicate...)方法中。
		 */
		Predicate[] pArray = new Predicate[pList.size()];
		for (int i = 0; i < pList.size(); i++) {
			pArray[i] = pList.get(i);
		}

		// 以物件導向的形式完成查詢並回傳List<MemberBean>的結果。
		Order order = cBuilder.asc(root.<Integer>get("mId"));
		query = query.select(root).where(pArray).orderBy(order);
		return this.getSession().createQuery(query).getResultList();
		/*
		 * 有關個人喜好的查詢會在回傳後List<MemberBean>， 再逐一利用位元比對，由呼叫本方法的Service來繼續處理。
		 */
	}

	/**
	 * 特殊查詢：利用姓名搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	@Override
	public List<MemberBean> selectByName(MemberKeyWordsBean mkwbean) {
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<MemberBean> query = cBuilder.createQuery(MemberBean.class);
		Root<MemberBean> root = query.from(MemberBean.class);
		Predicate pName = null;
		if (mkwbean.getmName() != null) {
			pName = cBuilder.equal(root.<String>get("mName"), mkwbean.getmName());
		}
		query = query.select(root).where(pName);
		return this.getSession().createQuery(query).getResultList();
	}

	/**
	 * 特殊查詢：利用相似的姓名搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/10
	 */
	@Override
	public List<MemberBean> selectBySimilarName(MemberKeyWordsBean mkwbean) {
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<MemberBean> query = cBuilder.createQuery(MemberBean.class);
		Root<MemberBean> root = query.from(MemberBean.class);
		Predicate pName = null;
		if (mkwbean.getmName() != null) {
			pName = cBuilder.like(root.<String>get("mName"),
					new StringBuffer().append(mkwbean.getmName()).append("%").toString());
		}
		query = query.select(root).where(pName);
		return this.getSession().createQuery(query).getResultList();
	}

	/**
	 * 特殊查詢：利用帳號搜尋。
	 * 
	 * @author 賴
	 * @version 2017/06/11
	 */
	@Override
	public List<MemberBean> selectByEmail(MemberKeyWordsBean mkwbean) {
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<MemberBean> query = cBuilder.createQuery(MemberBean.class);
		Root<MemberBean> root = query.from(MemberBean.class);
		Predicate pEmail = null;
		if (mkwbean.getmEmail() != null) {
			pEmail = cBuilder.equal(root.<String>get("mEmail"), mkwbean.getmEmail());
		}
		query = query.select(root).where(pEmail);
		return this.getSession().createQuery(query).getResultList();
	}

	/**
	 * 特殊查詢：搜尋會員總數。
	 * 
	 * @author 賴
	 * @version 2017/06/25
	 */
	@Override
	public Long selectMemberCount() {
		CriteriaBuilder cBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> query = cBuilder.createQuery(Long.class);
		Root<MemberBean> root = query.from(MemberBean.class);
		Expression<Long> expression = cBuilder.count(root);
		query = query.select(expression);
		List<Long> list = this.getSession().createQuery(query).getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}