package com.ljq.assets.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @email 867170960@qq.com
 * @author:刘俊秦
 * @date: 2018/10/26 0026
 * @time: 下午 4:40
 */
public class FileUpload {

	/*
	 * 通用文件上传
	 */
	public static void addFileUpload(MultipartFile file, String imgpath) {
		try {

			/* 文件上传 */
			if (file.getSize() > 0) {
				InputStream is = file.getInputStream();
				OutputStream os = new FileOutputStream(imgpath);

				byte[] tong = new byte[1024];
				int len = 0;

				while ((len = is.read(tong)) != -1) {
					os.write(tong, 0, len);
				}

				os.flush();

				os.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 通用文件复制
	 */
	public static void copyFile(File file, String imgpath) {
		try {
			InputStream is = new FileInputStream(file);
			OutputStream os = new FileOutputStream(imgpath);

			byte[] tong = new byte[1024];
			int len = 0;

			while ((len = is.read(tong)) != -1) {
				os.write(tong, 0, len);
			}

			os.flush();

			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
