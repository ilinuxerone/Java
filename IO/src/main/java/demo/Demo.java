package demo;

import java.io.File;
import java.io.IOException;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//test1();
		test2();
	}
	
	public static void test2()
	{
		
	}
	
	public  static void test1() throws IOException
	{
		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
		File file = new File("test.txt");
		System.out.println(file.getCanonicalPath());
		//获取文件名
		System.out.println(file.getName());
		//如果是绝对路径则返回完整路径，否则返回相对路径
		System.out.println(file.getPath());
		//返回上级目录,如果是相对则返回null
		System.out.println(file.getParent());
		//返回绝对路径
		System.out.println(file.getAbsolutePath());
	}

}
