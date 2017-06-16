package tw.com.eeit94.textile.model.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Service
public class PhotoService {
	@Autowired
	private PhotoDAO photoDAO;

	public PhotoService(PhotoDAO photoDAO) {
		this.photoDAO = photoDAO;
	}

	@Transactional(readOnly = false)
	public List<PhotoBean> select() {
		return photoDAO.select();
	}

	@Transactional(readOnly = true)
	public List<PhotoBean> selectByAlbumno(PhotoBean bean) {
		return photoDAO.selectByAlbumno(bean);
	}

	@Transactional(readOnly = true)
	public PhotoBean selectByphotono(PhotoBean bean) {
		return photoDAO.selectByPrimarykey(bean);
	}

	public String getTimeString() {
		Calendar today = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		Integer yy = today.get(Calendar.YEAR);
		Integer mm = today.get(Calendar.MONTH) + 1;
		Integer dd = today.get(Calendar.DATE);
		String month = mm.toString();
		String date = dd.toString();
		if (month.length() == 1) {
			month = "0" + month;
		}		
		if (date.length() == 1) {
			date = "0" + date;
		}	
		return sb.append(yy).append(month).append(date).toString();
	}

	public String getMemberIdString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("00000000").append(id);
		String memberIdString = sb.substring(sb.length() - 8, sb.length());
		return memberIdString;
	}

	public int countphoto(String beanno) {
		PhotoBean bean = new PhotoBean();
		bean.setPhotono(beanno);
		String temp = photoDAO.selectMax(bean);
		temp = temp.substring(temp.length() - 4, temp.length());
		int max = Integer.parseInt(temp) + 1;
		return max;
	}
	
	@Transactional
	public PhotoBean insertDataToPhoto(PhotoBean bean) {
		return photoDAO.insert(bean);
	}

	@Transactional
	public PhotoBean updatePhotoinfo(PhotoBean bean) {
		PhotoBean result = null;
		PhotoBean phonebean = this.selectByphotono(bean);
		if (phonebean != null) {
			phonebean.setPhotoname(bean.getPhotoname() == null ? phonebean.getPhotoname() : bean.getPhotoname());
			phonebean.setPosition(bean.getPosition() == null ? phonebean.getPosition() : bean.getPosition());
			phonebean.setVisibility(bean.getVisibility() == null ? phonebean.getVisibility() : bean.getVisibility());
			phonebean.setAlbumno(bean.getAlbumno() == null ? phonebean.getAlbumno() : bean.getAlbumno());
			phonebean.setInterpretation(
					bean.getInterpretation() == null ? phonebean.getInterpretation() : bean.getInterpretation());
			result = photoDAO.update(phonebean);
		}
		return result;
	}

	@Transactional
	public boolean removePhoto(PhotoBean bean) {
		boolean result = false;
		PhotoBean phonebean = photoDAO.selectByPrimarykey(bean);
		if (phonebean != null) {
			result = photoDAO.delete(phonebean);
			if (result) {
				File file = new File(phonebean.getRespath());
				result = file.delete();
			}
		}
		return result;
	}
}