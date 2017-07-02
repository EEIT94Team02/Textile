package tw.com.eeit94.textile.model.product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 * 這裡要寫摘要，為了整合和別人幫忙除錯容易，有關規則一定要先去看controller.example和model.example所有檔案，尤其是Example.java。
 * 
 * @author 李
 * @version 2017/06/12
 */
public class Test {

	public static void main(String[] args) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		BufferedOutputStream bos = null;
		try {
			File file = new File("E:/Project/img/table/heartForLoner.jpg");
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] temp = new byte[128];
			baos = new ByteArrayOutputStream();
			bos = new BufferedOutputStream(baos);
			int data = 0;
			while ((data = bis.read(temp)) != -1) {
				bos.write(temp);
			}
			System.out.println(Base64.getEncoder().encodeToString(baos.toByteArray()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}