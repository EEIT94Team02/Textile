package tw.com.eeit94.textile.model.theme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 黃
 * @version 2017/06/12
 */
@Service
@Transactional
public class ThemeService {
	@Autowired
	private ThemeDAO themeDAO;

	/*
	 * 實作企業邏輯
	 */
	// 切換主題 接收會員編號 主題編號
	public ThemeBean changeTheme(ThemeBean themeBean) {
		ThemeBean result = null;
		List<ThemeBean> list;
		if (themeBean != null) {
			// 確認主題是否存在
			result = themeDAO.select(themeBean.getThemeNo());
			if (result != null) {
				// 找出這個使用者所有的主題
				list = themeDAO.selectBoolan(themeBean.getmId());
				for (ThemeBean bean : list) {
					if (themeBean.getThemeNo() == bean.getThemeNo()) {
						bean.setThemeStatus(true);
						themeDAO.updateTheme(bean.getThemeNo(), bean.getThemeStyle(), bean.getThemeStatus());
					} else {
						// 將它們狀態都轉成未使用
						bean.setThemeStatus(false);
						themeDAO.updateTheme(bean.getThemeNo(), bean.getThemeStyle(), bean.getThemeStatus());
					}
				}
			}
		}
		return null;
	}

	// 新增主題 會員編號 主題內容 狀態(不一定要 有的話會切換成這個)
	public Boolean createNewTheme(ThemeBean bean) {
		List<ThemeBean> result;
		if (bean != null) {
			result = themeDAO.selectMemberTheme(bean.getmId());
			// 主題最多五個0~4
			if (result.size() <= 4) {
				themeDAO.insertTheme(bean);
				// 如果有設定狀態
				if (true == bean.getThemeStatus()) {
					this.changeTheme(bean);
				}
				return true;
			}
		}
		return false;
	}

	// 刪除主題 接收主題編號 會員編號
	public Boolean deleteTheme(ThemeBean bean) {
		ThemeBean result;
		List<ThemeBean> list;
		// 尋找是否有這主題
		result = themeDAO.select(bean.getThemeNo());
		if (result != null) {
			// 尋找會員所有主題
			list = themeDAO.selectMemberTheme(bean.getmId());
			for (ThemeBean tbBean : list) {
				// 如果有就刪除
				if (bean.getThemeNo() == tbBean.getThemeNo()) {
					themeDAO.deleteTheme(tbBean.getThemeNo());
					return true;
				}
			}
		}
		return false;
	}
}