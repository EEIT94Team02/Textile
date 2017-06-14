package tw.com.eeit94.textile.model.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 陳
 * @version 2017/06/12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
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
		String month = "";
		String date = "";
		if (mm.toString().length() == 1) {
			month = "0" + mm;
		}
		if (dd.toString().length() == 1) {
			date = "0" + dd;
		}
		return sb.append(yy).append(month).append(date).toString();
	}

	public String getMemberIdString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("00000000").append(id);
		String memberIdString = sb.substring(sb.length() - 8, sb.length());
		return memberIdString;
	}

	public String countphoto(PhotoBean bean) {
		String temp = photoDAO.selectMax(bean);
		temp = temp.substring(temp.length() - 4, temp.length());
		String max = String.valueOf(Integer.parseInt(temp) + 1);
		StringBuilder sb = new StringBuilder();

		String sb1 = sb.append("0000").append(max).substring(sb.length() - 4, sb.length());
		String result = bean.getPhotono() + sb1;
		return result;
	}

	public PhotoBean insertDataToTable(PhotoBean bean) {
		return photoDAO.insert(bean);
	}

	public File uploadPhoto(File file, File rootfolder) {
		File result = null;
		// "C:/Textile/repository/Textile/src/main/webapp/image/Makarova.jpg"
		// rootfolder+file.getName()+".jpg"
		UID photo = new UID();
		File file2 = new File(rootfolder + "/XXXX/" + photo.hashCode() + ".jpg");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			file2.getParentFile().mkdir();
			fis = new FileInputStream(file);
			fos = new FileOutputStream(file2, true);
			int data;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			result = file2;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

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