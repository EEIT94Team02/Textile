/*
 * 假設"Table名稱"為"example"，套件名稱用tw.com.eeit94.textile.model."Table名稱"。
 */
package tw.com.eeit94.textile.model.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit94.textile.model.spring.SpringJavaConfiguration;

/*
 * Service產生步驟：
 * 1. Service名稱為'"Table名稱" + "Service"'。
 * 2. 標記@Service。
 * 3. 加入至少一個Bean元件並標記@Autowired。
 */
@Service
public class PhotoService {
	@Autowired
	private PhotoDAO photoDAO;

	// 測試程式
	public static void main(String args[]) {
		
		File file1 = new File("C:/Users/Student/Desktop/Textile-etc/photo/Makarova.jpg");
		FileInputStream fis = null ;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file1);
			fos = new FileOutputStream("/image/Makarova.jpg");
			
			int data;
			while((data = fis.read()) != -1){
				fos.write(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	/*
	 * 實作企業邏輯
	 */
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public List<PhotoBean> select() {
		return photoDAO.select();
	}
	
	@Transactional // import org.springframework.transaction.annotation.Transactional;
	public PhotoBean uploadPhoto(File file , PhotoBean bean) {
		

		

		

		
		return null;
	}
	
	
	
	
	
	
}