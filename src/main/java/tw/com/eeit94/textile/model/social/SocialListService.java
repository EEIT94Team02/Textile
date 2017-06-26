package tw.com.eeit94.textile.model.social;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.member.MemberBean;
import tw.com.eeit94.textile.model.member.service.MemberRollbackProviderService;
import tw.com.eeit94.textile.model.secure.ConstSecureParameter;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.supervisor.ConstFilterKey;

/**
 *
 * 
 * @author 周
 * @version 2017/06/12
 */
@Service
public class SocialListService {
	@Autowired
	private SocialListDAO socialListDAO;
	@Autowired
	private SecureService secureService;
	@Autowired
	private MemberRollbackProviderService memberRollbackProviderService;

	public SocialListService(SocialListDAO socialListDAO) {
		this.socialListDAO = socialListDAO;
	}

	@Transactional
	public List<SocialListBean> selects() {

		List<SocialListBean> select = socialListDAO.select();

		return select;
	}

	@Transactional
	public SocialListBean select(SocialListPK pk) {

		SocialListBean select = socialListDAO.select(pk);

		return select;
	}

	@Transactional
	public List<SocialListBean> selectcheck(Integer acquaintenceId, String s_type) {
		List<SocialListBean> selectcheck = this.socialListDAO.getRelativeToMeList(acquaintenceId, s_type);
		return selectcheck;
	}

	@Transactional
	public List<SocialListBean> searchfriend(Integer userId, String s_type, String s_group, Timestamp s_login) {
		List<SocialListBean> searchfriend = this.socialListDAO.searchFriend(userId, s_type, s_group, s_login);

		return searchfriend;
	}

	@Transactional
	public List<SocialListBean> selectAllFriend(Integer userId, String s_type) {
		List<SocialListBean> selectAllFriend = this.socialListDAO.selectAllFriend(userId, s_type);

		return selectAllFriend;
	}

	@Transactional
	public SocialListBean insert(SocialListBean bean) {
		SocialListBean result = null;
		if (bean != null) {

			result = socialListDAO.insert(bean);
		}
		return result;
	}

	@Transactional
	public boolean delete(SocialListBean bean) {

		SocialListPK pk1 = new SocialListPK(bean.getSocialListPK().getUserId(),
				bean.getSocialListPK().getAcquaintenceId());
		SocialListBean resultA = socialListDAO.select(pk1);
		SocialListPK pk2 = new SocialListPK(bean.getSocialListPK().getAcquaintenceId(),
				bean.getSocialListPK().getUserId());
		SocialListBean resultB = socialListDAO.select(pk2);
		if (resultA != null && resultB != null) {
			socialListDAO.delete(resultA);
			socialListDAO.delete(resultB);
			return true;
		}
		return false;

	}

	@Transactional
	public boolean refuseDelete(SocialListBean bean) {

		SocialListBean result = socialListDAO.select(bean.getSocialListPK());
		if (result != null) {
			socialListDAO.delete(result);

			return true;
		}
		return false;

	}

	@Transactional
	public SocialListBean update(SocialListBean bean) {
		SocialListBean result = null;
		if (bean != null) {
			result = socialListDAO.update(bean);
		}
		return result;
	}

	@Transactional
	public SocialListBean updateTheFriend(SocialListBean bean) {
		SocialListBean result = null;
		SocialListPK pk = new SocialListPK(bean.getSocialListPK().getAcquaintenceId(),
				bean.getSocialListPK().getUserId());
		if (bean != null) {
			bean.setSocialListPK(pk);
			bean.setS_type("好友");
			result = socialListDAO.insert(bean);
		}
		return result;
	}

	@Transactional
	public void setLinksListInSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberBean mbean = (MemberBean) session.getAttribute(ConstFilterKey.USER.key());

		List<LinksBean> friendList = this.getLinksList(mbean, "好友", request);
		List<LinksBean> blackList = this.getLinksList(mbean, "黑單", request);
		List<LinksBean> trackList = this.getLinksList(mbean, "追蹤", request);
		List<LinksBean> unconfirmedList = this.getLinksList(mbean, "未確認", request);

		session.setAttribute("friendList", friendList);
		session.setAttribute("blackList", blackList);
		session.setAttribute("trackList", trackList);
		session.setAttribute("unconfirmedList", unconfirmedList);
	}

	@Transactional
	public List<LinksBean> getLinksList(MemberBean mbean, String s_type, HttpServletRequest request) throws Exception {
		Integer userId = mbean.getmId();
		List<SocialListBean> socialList = this.selectAllFriend(userId, s_type);
		List<LinksBean> linksList = new ArrayList<>();
		for (SocialListBean sbean : socialList) {
			LinksBean linksBean = new LinksBean();
			linksBean.setChatroomURL(this.memberRollbackProviderService.getChatUrlWithRollbackProvider(mbean,
					sbean.getSocialListPK().getAcquaintenceId(), request));
			linksBean.setProfileURL(this.secureService.getEncryptedText(
					sbean.getSocialListPK().getAcquaintenceId().toString(), ConstSecureParameter.MEMBERID.param()));
			linksBean.setmName(sbean.getMbean().getmName());
			linksList.add(linksBean);
		}
		return linksList;
	}

	public List<SocialListBean> selectAllFriend(int userId, List<String> s_type) {
		List<SocialListBean> selectAllFriend = this.socialListDAO.selectAll(userId, s_type);
		return selectAllFriend;
	}
}