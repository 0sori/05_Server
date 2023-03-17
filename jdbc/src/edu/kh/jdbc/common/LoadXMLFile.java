package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {

	public static void main(String[] args) {
		
		// XML 파일 읽어오기 (Properties, FileInputStream)
		
		try {
			Properties prop = new Properties();
			
			// driver.xml 파일 읽어오기 위한 InputStream 객체 생성
			
			FileInputStream fis = new FileInputStream("driver.xml");
			
			prop.loadFromXML(fis);
			
			System.out.println(prop);
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			System.out.println();
			
			// driver.xml 파일에서 읽어온 값들을 이용해서 Connection 생성
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			
			System.out.println(conn);
			
			
		
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
