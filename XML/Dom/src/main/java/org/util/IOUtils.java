package org.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {
	public static void main(String [] args){ 
		IOUtils util = new IOUtils();
		InputStream input = util.getClass().getResourceAsStream("/resource/XpathDemo.xml");
		InputStream input2 = util.getClass().getClassLoader().getResourceAsStream("resource/XpathDemo.xml");
		BufferedReader fir = new BufferedReader(new  InputStreamReader(input));

		byte[] line = new byte[1024*1024];  
		try {  
			input2.read(line);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
		 String str = new String(line);  
         System.out.println(str.trim());  
		
		System.out.println(util.getClass().getResource("/resource/XpathDemo.xml"));
		
		
		
	}
}
